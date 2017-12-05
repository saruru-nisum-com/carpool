package com.nisum.carpool.data.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.MapId;

import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.repository.CarpoolRiderDetailsRepository;
import com.nisum.carpool.data.repository.CarpooldetailsRepository;
import com.nisum.carpool.data.util.Constants;
//import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.data.util.Pool_Status;

@Configuration
public class CarpooldetailsDAOImpl implements CarpooldetailsDAO {
	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsDAOImpl.class);
	@Autowired
	CarpooldetailsRepository carpooldetailsRepository;

	@Autowired
	CarpoolRiderDetailsRepository carpoolriderdetailsrepository;

	/*
	 * @author Suresh valavala
	 * 
	 * @see
	 * com.nisum.carpool.data.dao.api.CarpooldetailsDAO#updateCarpooldetails(com.
	 * nisum.carpool.data.domain.Carpooldetails)
	 * 
	 * @ for updating existing carpooldetails
	 */

	@Override
	public String updateCarpooldetails(Carpooldetails carpooldetails) {

		logger.info("CarpooldetailsDAOImpl: updateCarpooldetails");
		Long countByParentid = carpooldetailsRepository.countByParentid(carpooldetails.getId());

		if (countByParentid == 1) {
			logger.info("CarpooldetailsDAOImpl: updateCarpooldetails : Single CarpoolDetails update");
			carpooldetailsRepository.save(carpooldetails);
			return Constants.MSG_CARPOOL_UPDATE_SINGLE;
		}
		logger.info("CarpooldetailsDAOImpl: updateCarpooldetails : Multiple CarpoolDetails update");
		List<Carpooldetails> listOfCarpools = carpooldetailsRepository.findByParentid(carpooldetails.getId());
		if (CollectionUtils.isNotEmpty(listOfCarpools)) {
			listOfCarpools.forEach(cp -> {
				cp.setFromtime(carpooldetails.getFromtime());
				cp.setToTime(carpooldetails.getToTime());
				cp.setNoofseats(carpooldetails.getNoofseats());
				carpooldetailsRepository.save(cp);
			});
		}
		return Constants.MSG_CARPOOL_UPDATE_MULTI;

	}

	@Override
	public String cancelCarpooldetails(Carpooldetails carpooldetails) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsDAOImpl: cancel Carpooldetails getby Id" + carpooldetails.getId());
		List<Integer> listOfIds = null;
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		logger.info("in parent cancel");
		// update Parent record
		try {
			Long countByParentid = carpooldetailsRepository.findById(carpooldetails.getId());
			logger.info("in daoimpl cancel by parentId" + countByParentid);
			if (countByParentid == 1) {
				carpooldetails.setModifieddate(modifiedDate.toLocalDateTime());
				carpooldetails.setStatus(Pool_Status.CLOSED.getValue());
				carpooldetailsRepository.save(carpooldetails);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// update child records
		try {
			listOfIds = carpooldetailsRepository.getListOfIdsByParentid(carpooldetails.getParentid());
			logger.info("in child update.parentId.." + carpooldetails.getParentid());
			logger.info("listOfIds ** in cancel pool daoimpl:" + listOfIds.size());

			List<Carpooldetails> poolData = carpooldetailsRepository.findByParentid(carpooldetails.getParentid());
			if (poolData != null) {
				if (CollectionUtils.isNotEmpty(poolData)) {
					poolData.forEach(c -> {
						c.setStatus(Pool_Status.CLOSED.getValue());
						c.setModifieddate(modifiedDate.toLocalDateTime());
						carpooldetailsRepository.save(c);
					});
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Constants.MSG_CARPOOL_CANCEL;
	}

	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used for creating carpool records in db
	 */

	@Override
	public String addCarpoolDetails(List<Carpooldetails> carpooldetails) {

		logger.info("CarpooldetailsDAOImpl: createCarpooldetails");

		try {

			for (Carpooldetails cp : carpooldetails) {
				carpooldetailsRepository.save(cp);

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			return Constants.MSG_CARPOOL_FAILED;
		}

		return Constants.MSG_CARPOOL_ADD;

	}

	@Override
	public List<Carpooldetails> getAllCarPoolDetails() {
		logger.info("PostRideDaoImpl: getAllCarPoolDetails ::");
		try {
			return (List<Carpooldetails>) carpooldetailsRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Carpooldetails> getCarPoolByMailID(String email) {
		return carpooldetailsRepository.getCarPoolsByEmail(email);
	}

	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used to check if carpool can be created or not
	 */

	@Override
	public String checkValidCarpool(String emailid, String fromdate, String todate) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsDAOImpl: checkValidCarpool");

		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		boolean flag = false;

		count1 = carpooldetailsRepository.findEntriesWithDate(emailid, fromdate, todate);

		if (count1 != 0) {

			count2 = carpooldetailsRepository.findEntriesWithDateIfNotCancelled(emailid, fromdate, todate);

			if (count2 != 0)

				return Constants.CARPOOLEXISTS;

			else
				return Constants.VALID;
		}

		else {

			List<Carpooldetails> carpooldetails = carpooldetailsRepository.getCarPoolsByDate(fromdate, todate);
			if (carpooldetails != null) {
				for (Carpooldetails cpd : carpooldetails) {
					int cpid = cpd.getId();

					count3 = carpoolriderdetailsrepository.checkWhetherDriverIsRider(emailid, cpid);

					if (count3 != 0)
						flag = true;

				}

			}

			if (flag)
				return Constants.DRIVER_IS_REGISTERED_AS_RIDER;

			else

				return Constants.VALID;

		}

	}

	@Override
	public List<Carpooldetails> getCarpoolsByParentId(int parentId) {
		// TODO Auto-generated method stub
		return carpooldetailsRepository.getCaroolsByParentId(parentId);
	}

	@Override
	public List<Integer> getCarPoolParentIds(String email) {
		// TODO Auto-generated method stub
		return carpooldetailsRepository.getParentIdByEmail(email);
	}

	/**
	 * @author Harish Kumar Gudivada
	 * 
	 *         This method is used to load the carpool ride details based on carpool
	 *         id from Repository
	 * 
	 *         Param carpoolId Return carpoolDets
	 */
	@Override
	public Carpooldetails loadCarpoolDetailsById(int carpoolId) throws Exception {
		Carpooldetails carpoolDets = null;
		logger.info("Entered into CarpooldetailsDAOImpl :: loadCarpoolDetailsById");
		try {
			carpoolDets = carpooldetailsRepository.findCarpoolDetailsById(carpoolId);
		} catch (Exception e) {
			logger.error("Exception Occured in Class:CarpooldetailsDAOImpl Method:loadCarpoolDetailsById Message:"
					+ e.getMessage());
			throw e;
		}
		logger.info("Exit from CarpooldetailsDAOImpl :: loadCarpoolDetailsById");
		return carpoolDets;
	}

	public Carpooldetails getCarpoolByPoolID(Integer carpoolId) {

		return carpooldetailsRepository.getCarpoolByPoolID(carpoolId);
	}

	public void updateCarpoolStatusByPoolId(int poolStatus, Integer id) {
		
		carpooldetailsRepository.updateCarpoolStatusByPoolId(poolStatus, id);
	}

	public void upateCarPoolStatusByIdandParentID(int pid, int status) {
		carpooldetailsRepository.updateCarpoolStatusByPoolId(pid, status);
	}

}
