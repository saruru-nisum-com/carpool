package com.nisum.carpool.data.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		logger.info("in parent cancel");
		// update Parent record
		try {
			logger.info("in Parent update.parent Id.." + carpooldetails.getId());

			List<Carpooldetails> poolData = carpooldetailsRepository.findByParentid(carpooldetails.getId());
			logger.info("poolData size for parent=="+poolData.size());
			if(poolData.size()>1) {
			if (poolData != null) {
				if (CollectionUtils.isNotEmpty(poolData)) {
					poolData.forEach(c -> {
						c.setStatus(Pool_Status.CANCELLED.getValue());
						c.setModifieddate(modifiedDate.toLocalDateTime());
						carpooldetailsRepository.save(c);
					});
				}
				
			}
			return Constants.MSG_CARPOOL_CANCEL_MULTI;
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//child update
		try {
			Long countByParentid = carpooldetailsRepository.findById(carpooldetails.getId());
			logger.info("in daoimpl cancel by getby Id" + countByParentid);
			if (countByParentid ==1) {
				logger.info("in child cancel");
				carpooldetails.setModifieddate(modifiedDate.toLocalDateTime());
				carpooldetails.setStatus(Pool_Status.CANCELLED.getValue());
				carpooldetailsRepository.save(carpooldetails);
				return Constants.MSG_CARPOOL_CANCEL;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return Constants.MSG_CARPOOL_CANCEL;
		
	}
	
	/**
	 * @author Manohar Dhavala : CPL005: Create Car Pools (Post a ride)
	 * 
	 *         This method is used for creating carpool records in db
	 *         @param carpooldetails
	 *         @return String
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
	 * @author Manohar Dhavala : CPL005: Create Car Pools (Post a ride)
	 * 
	 *         This method is used to check if carpool can be created or not
	 *         @param emailid, fromdate, todate
	 *         @return String
	 */
	@Override
	public String checkValidCarpool(String emailid, String fromdate, String todate) {

		logger.info("CarpooldetailsDAOImpl: checkValidCarpool");

		int count1 = 0;
		int count2 = 0;


		
		//to check if there are entries in db with the given email and dates

		count1 = carpooldetailsRepository.findEntriesWithDateIfNotCancelled(emailid, fromdate, todate);
			
			if (count1 != 0)
 
				return Constants.CARPOOLEXISTS;

			else
			{

			//to get all car pools within the given dates

			List<Carpooldetails> carpooldetails = carpooldetailsRepository.getCarPoolsByDateNotCancelled(fromdate, todate);
			if (carpooldetails != null) {
				for (Carpooldetails cpd : carpooldetails) {
					int cpid = cpd.getId();
					
					//to check if the driver is also a rider for any pool within the given dates
					count2 = carpoolriderdetailsrepository.checkWhetherDriverIsRiderWithStatus(emailid, cpid);

					if (count2 !=0)
						return Constants.DRIVER_IS_REGISTERED_AS_RIDER;

				}

			}

				return Constants.CARPOOL_VALID;

		}
	}

	/**
	 * @author Mahesh Bheemanapalli : CPL040: Adding reward points to to driver
	 * 
	 *         Parameter: rewards (reading the reward points from
	 *         "application.properties").
	 * 
	 *         This method is used to update the reward points to driver when the
	 *         carpool status is "Closed"
	 * 
	 *         returntype:String statement from
	 *         com.nisum.carpool.data.util.Constants;
	 */
	@Override
	public String addRewards(double rewards) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsDAOImpl : addRewards : To Driver");
		String rewardedDate = this.formateDate();
		List<Integer> listOfIds = carpooldetailsRepository.getCarpooldetailsByToDateAndStatus(Pool_Status.CLOSED.getValue(),
				rewardedDate);
		if (listOfIds.size() > 0) {
			carpooldetailsRepository.udpateRewardPoints(rewards, listOfIds);
			return Constants.ADDED_REWARDS_TO_DRIVER;
		}
		return Constants.REWARDS_NOT_ADDED_DRIVER;
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


	
	/**
	 * @author Manohar Dhavala : CPL005: Create Car Pools (Post a ride)
	 * 
	 *         This method is used to find the driver email with the given car pool id
	 *         @param cpid
	 *         @return String
	 */

	@Override
	public String getDriverEmailByCPId(int cpid) {
		// to find the driver email with the given car pool id
		return carpooldetailsRepository.getDriverEmailByCPId(cpid);
	}
     /**
      * Author:Radhika Pujari
      */
	@Override
	public List<Carpooldetails> getCarPoolByCpIDandDate(int cpId, String date) {
		logger.info("Entered into CarpooldetailsDAOImpl :: getCarPoolByCpIDandDate");
		try {
		return carpooldetailsRepository.getCarPoolsByCpIdandDate(cpId, date);
	}catch (Exception e) {
		logger.info("Entered into CarpooldetailsDAOImpl :: getCarPoolByCpIDandDate :: error"+e.getMessage());
		e.printStackTrace();
		return null;
	}
	}

	@Override
	public Carpooldetails getCarpoolByDateAndEmail(String date, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getCarpoolByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Carpooldetails> getCarPoolsByLocation(String location) {
		logger.info("Entered into CarpooldetailsDAOImpl :: getCarPoolsByLocation"); 
		try {
			return carpooldetailsRepository.getCarpoolsByLocation(location);
		} catch(Exception e) {
			logger.info("Entered into CarpooldetailsDAOImpl :: getCarPoolsByLocation:: Exception occurred"); 
			e.printStackTrace();
			return null;
		}
}
	/**
	 * @author Mahesh Bheemanapalli : CPL049: Functionality to update car pool
	 *         status using scheduler
	 * 
	 *         This method is used update the carpool status as "Closed" except
	 *         "Cancelled" where todate is Less than or equal to Current Date.
	 * @return String
	 */
	@Override
	public String updateCarpoolStatusToClosed() {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed");
		String todate = this.formateDate();
		logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed :" + todate);
		List<Carpooldetails> carpoolsByToDate = carpooldetailsRepository.getCarpoolsByToDate(todate);
		logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed :" + carpoolsByToDate.size());
		Set<Integer> setOfPoolIds = carpoolsByToDate.stream()
				.filter(p -> p.getStatus() != Pool_Status.CLOSED.getValue()
						&& p.getStatus() != Pool_Status.CANCELLED.getValue())
				.map(p -> p.getId()).collect(Collectors.toSet());
		logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed :" + setOfPoolIds.size());
		if (setOfPoolIds.size() > 0) {
			Integer countBeforeUpdate = carpooldetailsRepository
					.checkUpdateCarpoolStatusClosedCount(Pool_Status.CLOSED.getValue());
			logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed : countBeforeUpdate" + countBeforeUpdate);
			carpooldetailsRepository.updateCarpoolStatusBySetOfPoolIds(Pool_Status.CLOSED.getValue(), setOfPoolIds);
			logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed : CarpoolStatusUpdateToClose");
			Integer countAfterUpdate = carpooldetailsRepository
					.checkUpdateCarpoolStatusClosedCount(Pool_Status.CLOSED.getValue());
			logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed : countAfterUpdate" + countAfterUpdate);
			if (countBeforeUpdate < countAfterUpdate) {
				logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed :" + Constants.CARPOOL_STATUS_UPDATED);
				return Constants.CARPOOL_STATUS_UPDATED;
			}
			logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed :" + Constants.CARPOOL_STATUS_NOT_UPDATED);
			return Constants.CARPOOL_STATUS_NOT_UPDATED;
		}

		logger.info("CarpooldetailsDAOImpl : updateCarpoolStatusToClosed :" + Constants.NO_CARPOOLS_TO_UPDATE_STATUS);
		return Constants.NO_CARPOOLS_TO_UPDATE_STATUS;
	}
	
	/*@author Suresh valavala
	 * (non-Javadoc)
	 * @see com.nisum.carpool.data.dao.api.CarpooldetailsDAO#getCarPoolsByEmailAndCurrentDate(java.lang.String, java.lang.String)
	 */
	
	public List<Carpooldetails> getCarPoolsByEmailAndCurrentDate(String emailId, String date){
		return carpooldetailsRepository.getCarPoolsByEmailAndCurrentDate(emailId, date);
	}
	
	 /**
	 * @author Mahesh Bheemanapalli 
	 * This is the Common method to formate current date into String formate
	 * 
	 * @return String
	 */
	private String formateDate() {
		logger.info("CarpooldetailsDAOImpl : formateDate : in String");
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return currentDate.format(formatter);
	}

	@Override
	public List<Carpooldetails> findCarpoolDetailsByParentId(int parentid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelCarpooldetailsByParentId(Carpooldetails carpooldetails) {
		// TODO Auto-generated method stub
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		logger.info("in parent cancel:::");
		// update Parent record
		try {
			logger.info("in Parent update.parent Id.." + carpooldetails.getParentid());

			List<Carpooldetails> poolData = carpooldetailsRepository.findByParentid(carpooldetails.getParentid());
			logger.info("poolData size for parent=="+poolData.size());
			if(poolData.size()>1) {
			if (poolData != null) {
				if (CollectionUtils.isNotEmpty(poolData)) {
					poolData.forEach(c -> {
						c.setStatus(Pool_Status.CANCELLED.getValue());
						c.setModifieddate(modifiedDate.toLocalDateTime());
						carpooldetailsRepository.save(c);
					});
				}
				
			}
			return Constants.MSG_CARPOOL_CANCEL;
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return Constants.MSG_CARPOOL_CANCEL_MULTI;
	
}
}