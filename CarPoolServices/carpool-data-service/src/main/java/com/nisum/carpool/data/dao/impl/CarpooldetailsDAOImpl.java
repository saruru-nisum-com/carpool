package com.nisum.carpool.data.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.repository.CarpooldetailsRepository;
import com.nisum.carpool.data.util.Constants;
//import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.data.util.Pool_Status;



@Configuration
public class CarpooldetailsDAOImpl implements CarpooldetailsDAO {
	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsDAOImpl.class);
	@Autowired
	CarpooldetailsRepository carpooldetailsRepository;
	@Override
public String updateCarpooldetails(Carpooldetails carpooldetails) {
		
		logger.info("CarpooldetailsDAOImpl: updateCarpooldetails");
		Long countByParentid = carpooldetailsRepository.countByParentid(carpooldetails.getId());
		
        if(countByParentid == 1) {
        	    carpooldetailsRepository.save(carpooldetails);
			return Constants.MSG_CARPOOL_UPDATE_SINGLE;
		}
         List<Integer> listOfIds = carpooldetailsRepository.getListOfIdsByParentid(carpooldetails.getId());
         carpooldetailsRepository.udpateMultipleCarpoolDetails(carpooldetails, listOfIds);
         return Constants.MSG_CARPOOL_UPDATE_MULTI;
			
	}
	
	
	
	@Override
	public String cancelCarpooldetails(Carpooldetails carpooldetails) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsDAOImpl: cancel Carpooldetails getby Id"+carpooldetails.getId());
		List<Integer> listOfIds =null;
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		Long countByParentid = carpooldetailsRepository.findById(carpooldetails.getId());
		logger.info("in daoimpl cancel by parentId"+countByParentid);
		   if(countByParentid == 1) {
			   logger.info("in parent cancel");
			   carpooldetails.setModifieddate(modifiedDate.toLocalDateTime());
			   carpooldetails.setStatus(Pool_Status.CLOSED.getValue());
       	    carpooldetailsRepository.save(carpooldetails);
		}
		    listOfIds = carpooldetailsRepository.getListOfIdsByParentid(carpooldetails.getId());
		    
		    logger.info("listOfIds in cancel pool daoimpl:"+listOfIds.size());
		  try {
			  logger.info("in child update.parentId.."+carpooldetails.getId());
			  List<Carpooldetails> poolData=carpooldetailsRepository.findByParentid(carpooldetails.getId());
			  if(poolData!=null) {
					  if (CollectionUtils.isNotEmpty(poolData)) {
						  poolData.forEach(c->{
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
	
	
	
	@Override
	public List<Carpooldetails> addCarpoolDetails(List<Carpooldetails> carpooldetails) {
		
		logger.info("CarpooldetailsDAOImpl: createCarpooldetails");	
		
		try {
			
			for(Carpooldetails cp:carpooldetails) {
				carpooldetailsRepository.save(cp);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
			
		
			return carpooldetailsRepository.getCarPoolsByEmail(carpooldetails.get(0).getEmailId());
		
	}
	
	@Override
	public String checkValidCarpool(Carpooldetails carpooldetails) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsDAOImpl: checkValidCarpool");
		
		
		String userid = carpooldetails.getEmailId();
		String fromdate = carpooldetails.getFromDate();
		String todate = carpooldetails.getToDate();
		
		int countFromdate = carpooldetailsRepository.findEntriesWithDate(userid, fromdate);
		int countTodate = carpooldetailsRepository.findEntriesWithDate(userid, todate);
		
		logger.info("countFromdate " + countFromdate);
		logger.info("countTodate " + countTodate);
		
		if(countFromdate !=0 || countTodate !=0) 
			return Constants.CARPOOLEXISTS;
			else 
				return Constants.VALID;	

		
	}
	
	@Override
	public List<Carpooldetails> getAllCarPoolDetails() {
		logger.info("PostRideDaoImpl: getAllCarPoolDetails ::");
		try {
			return  (List<Carpooldetails>) carpooldetailsRepository.findAll();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
			

}
	
	public List<Carpooldetails> getCarPoolByMailID(String email)
	{
	return	carpooldetailsRepository.getCarPoolsByEmail(email);
	}

	@Override
	public String updaterewardPointsWithId(Integer rewards,Integer status) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsDAOImpl : updaterewardPointsWithId");
		LocalDate currentDate=LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String rewardedDate = currentDate.format(formatter);
		List<Integer> listOfIds = carpooldetailsRepository.getCarpooldetailsByFromDate(status,rewardedDate);
		if(listOfIds.size()>0) {
			carpooldetailsRepository.udpateRewardPoints(rewards, listOfIds);
			carpooldetailsRepository.udpateRewardPoints(rewards, listOfIds);
			return Constants.ADDED_REWARDS_TO_DRIVER;
		}
		return Constants.REWARDS_NOT_ADDED_DRIVER;
	}
	
	/**
	 * @author Harish Kumar Gudivada
	 * 
	 * This method is used to load the carpool ride details based on carpool id from Repository
	 * 
	 * Param carpoolId
	 *Return carpoolDets
	 */
	@Override
	public Carpooldetails loadCarpoolDetailsById(int carpoolId) throws Exception{
		Carpooldetails carpoolDets=null;
		logger.info("Entered into CarpooldetailsDAOImpl :: loadCarpoolDetailsById");
		try {
			carpoolDets=carpooldetailsRepository.findCarpoolDetailsById(carpoolId);
		}catch (Exception e) {
			logger.error("Exception Occured in Class:CarpooldetailsDAOImpl Method:loadCarpoolDetailsById Message:"+e.getMessage());
			throw e;
		}
		logger.info("Exit from CarpooldetailsDAOImpl :: loadCarpoolDetailsById");
		return carpoolDets;
	}

}
