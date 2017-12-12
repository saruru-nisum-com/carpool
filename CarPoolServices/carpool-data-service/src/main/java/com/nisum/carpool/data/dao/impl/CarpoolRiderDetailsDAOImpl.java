package com.nisum.carpool.data.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.carpool.data.dao.api.CarpoolRiderDetailsDAO;
import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.data.domain.CarpoolRiderNotifications;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.repository.CarpoolRiderDetailsRepository;
import com.nisum.carpool.data.repository.CarpoolRiderNotificationsRepository;
import com.nisum.carpool.data.repository.CarpooldetailsRepository;
import com.nisum.carpool.data.util.Constants;
import com.nisum.carpool.data.util.Pool_Status;
import com.nisum.carpool.data.util.Ride_Status;


@Configuration
public class CarpoolRiderDetailsDAOImpl implements CarpoolRiderDetailsDAO {
	
	private static Logger logger = LoggerFactory.getLogger(CarpoolRiderDetailsDAOImpl.class);
	@Autowired
	CarpooldetailsRepository carpooldetailsRepository;

	@Autowired
	CarpoolRiderDetailsRepository carpoolRiderDetailsRepository;
	
	@Autowired
	CarpoolRiderNotificationsRepository carpoolridernotificationsrepository;

	/*
	 * MethodAuthor: @Radhika Pujari
	 */

	/**
	 * getRiderBookingDetails  for Dao Layer
	 * parameter: emailId
	 * This method used for load the data from repository
	 * returntype:List<CarpoolRiderDetails>
	 */
	
	@Override
	public List<CarpoolRiderDetails> getRiderBookingDetails(String emailId) {
		logger.info("CarpoolRiderDetailsDAOImpl::getRiderBookingDetails::emailId");
		try {
		
      return (List<CarpoolRiderDetails>) carpoolRiderDetailsRepository.getRiderBookingDetails(emailId);
		}catch(Exception e) {
			logger.info("CarpoolRiderDetailsDAOImpl::getRiderBookingDetails::error"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CarpoolRiderDetails> findCarpoolRiderDetailsByCPId(int cpid) {

		return carpoolRiderDetailsRepository.findCarpoolRiderDetailsByCPId(cpid);
	}

	// cancel Rider details when Driver cancel carpool
	@Override
	public List<CarpoolRiderDetails> cancelCarpoolRiderDetails(int cpid) {
		System.out.println("in cancelCarpool Rider daoImpl cpId=**" + cpid);
		List<CarpoolRiderDetails> carPoolRiderData = carpoolRiderDetailsRepository.getRiderDetailsByCpId(cpid);

		System.out.println("carPoll rider cancel size**==" + carPoolRiderData.size());

		try {
			Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
			if(carPoolRiderData!=null) {
				  if (CollectionUtils.isNotEmpty(carPoolRiderData)) {
					  carPoolRiderData.forEach(c->{
							c.setStatus(Ride_Status.CANCELLED.getValue());
						  c.setModifieddate(modifiedDate.toLocalDateTime());
						  carpoolRiderDetailsRepository.save(c);
						});
					  
					 }
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(" cancelling rider rides failed");
			e.printStackTrace();
		} 
	  
		return carPoolRiderData;
	}

	@Override
	public List<CarpoolRiderDetails> getRidersByCpID(Integer poolid) {
		// TODO Auto-generated method stub
		return carpoolRiderDetailsRepository.getRidersByPoolID(poolid);
	}
	
	/*
	 * MethodAuthor: @Rajesh Sekhamuri
	 */
	
	public Integer updateRiderStatus(CarpoolRiderDetails carpoolRiderDetailsObj) {
		System.out.println("Start of updateRiderStatus() method in CarpoolRiderDetailsDAOImpl");
		Integer updateCount  = 0;
		System.out.println("Read input data ***** "+carpoolRiderDetailsObj);
		Integer reasonTemp = carpoolRiderDetailsObj.getReason();
		Integer statusTemp = carpoolRiderDetailsObj.getStatus();
		updateCount = carpoolRiderDetailsRepository.udpateCarpoolRiderStatus(carpoolRiderDetailsObj);
		System.out.println("Is updated count "+updateCount);  
		
		//Cassendra not support return value when update query use.
		List<CarpoolRiderDetails> carpoolRiderListObj = carpoolRiderDetailsRepository.getRiderDetailsById(carpoolRiderDetailsObj.getId());
		System.out.println("return object "+carpoolRiderListObj.size());
		for(int poolObj=0; poolObj<=carpoolRiderListObj.size()-1; poolObj++) {
			CarpoolRiderDetails cpRiderObj = carpoolRiderListObj.get(poolObj);
			if(cpRiderObj.getReason() == reasonTemp && cpRiderObj.getStatus() == statusTemp) {
				updateCount = 1;
				break;
			} 
		}
		System.out.println(" now update count "+updateCount);
		System.out.println("End of updateRiderStatus() method in CarpoolRiderDetailsDAOImpl");
		return updateCount;
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used for updating reason id and status as cancelled in 
	 *         cp_carpoolriderdetails db when rider cancels a ride
	 */
	@Override
	public List<CarpoolRiderDetails> cancelRiderBookingdetails(List<CarpoolRiderDetails> carpoolriderdetailslist) {
		logger.info("carpoolriderdetailsdaoimpl:cancelling a ride");
		
		try {
			
		for(CarpoolRiderDetails cprider: carpoolriderdetailslist) {
			cprider.setStatus(Ride_Status.CANCELLED.getValue());
			carpoolRiderDetailsRepository.save(cprider);
			
		}
		
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Canceling ride(s) failed");
			return null;
		}
		
		//returning the list of all booked rides by the rider
		return getRiderBookingDetails(carpoolriderdetailslist.get(0).getEmailid());
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used for finding all riders who want to be notified
	 *         when pool is reopen because of a rider cancelling a ride
	 */

	@Override
	public List<CarpoolRiderNotifications> findRidersToNotifyByCPId(int cpid) {
		logger.info("carpoolriderdetailsdaoimpl: findRidersToNotifyByCPId");
		return carpoolridernotificationsrepository.getNotifiedRidersByCPId(cpid);
		
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used for updating notify field in carpoolridernotifications table 
	 *         so another rider wont send notification when he cancels a ride
	 *		   
	 */

	@Override
	public void updatecpridernotifications(CarpoolRiderNotifications cpridernotify) {
		//updating notify field in carpoolridernotifications table so another rider wont send notification 
		//when he cancels a ride
		carpoolridernotificationsrepository.save(cpridernotify);
		
	}
	/**
	* author Mahesh Bheemanapalli
	*/

	/**
	* addRewards() for  dao layer 
	* Parameter: rewards (reading the reward points from "application.properties")
	* This method is used to update the reward points to rider when the carpool status is "Closed" and carpoolrider is "Approved" 
	* returntype:String statement from com.nisum.carpool.data.util.Constants;   
	*/
	@Override
	public String addRewards(double rewards) {
		// TODO Auto-generated method stub
		logger.info("BEGIN :: CarpoolRiderDetailsDAOImpl : updaterewardPointsWithId : To Rider");
		Set<Integer> riders = this.getSetOfCarpoolRiders();
		if (riders.size() > 0) {
			carpoolRiderDetailsRepository.udpateRiderRewardPoints(rewards, riders);
			logger.info("CLOSED :: CarpoolRiderDetailsDAOImpl : updaterewardPointsWithId : To Rider : status : "+Constants.ADDED_REWARDS_TO_RIDER);
			return Constants.ADDED_REWARDS_TO_RIDER;
		}
		logger.info("CLOSED :: CarpoolRiderDetailsDAOImpl : updaterewardPointsWithId : To Rider : status : "+Constants.REWARDS_NOT_ADDED_RIDER);
		return Constants.REWARDS_NOT_ADDED_RIDER;
	}
	/**
	* author Mahesh Bheemanapalli
	*/

	/**
	 * getListOfCarpoolRiders() for dao layer This method is used to get Set of
	 * Rider id's where rewards are "0", carpool status is "Closed", carpoolRider
	 * Status is "Approved" and Date less than current date. 
	 * returntype:Set<Integer> i.e carpoolrider id's;
	 */
	public Set<Integer> getSetOfCarpoolRiders() {
		logger.info("BEGIN :: CarpoolRiderDetailsDAOImpl : getSetOfCarpoolRiders");
		Set<Integer> SetOfClosedRiders = new HashSet<>();
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String rewardedDate = currentDate.format(formatter);
		logger.info("STEP1 :: CarpoolRiderDetailsDAOImpl : getSetOfCarpoolRiders : Current Date : "+rewardedDate);
		List<Integer> carpoolClosedCpids = carpooldetailsRepository
				.getCarpooldetailsByToDateAndStatus(Pool_Status.CLOSED.getValue(), rewardedDate);
		logger.info("STEP2 :: CarpoolRiderDetailsDAOImpl : getSetOfCarpoolRiders : No-of Carpools Having rewards Zero where status is closed and todate is less or equal to current date : "+carpoolClosedCpids.size());
		if (carpoolClosedCpids.size() > 0) {
			for (int i = 0; i < carpoolClosedCpids.size(); i++) {
				List<Integer> ridersId = carpoolRiderDetailsRepository.getListOfClosedRiders(Ride_Status.APPROVED.getValue(),
						carpoolClosedCpids.get(i));
		logger.info("STEP3 :: CarpoolRiderDetailsDAOImpl : getSetOfCarpoolRiders : No-of Riders where status Approved and carpool status closed : "+ridersId.size());
				if(CollectionUtils.isNotEmpty(ridersId)&&ridersId.size()>0) {
					SetOfClosedRiders.addAll(ridersId);
				}
			}
		}
		return SetOfClosedRiders;
	}

	
	@Override
	public Carpooldetails getCarpoolByDateAndEmail(String date, String email) {
		// TODO Auto-generated method stub
		return  carpooldetailsRepository.getCarpoolByDateAndEmail(date, email);
	}
@Override
	public List<Integer> getCarpoolByDate(String date) {
		// TODO Auto-generated method stub
		return carpooldetailsRepository.getCarpoolByDate(date);
	}

public CarpoolRiderDetails getRidesByMailandAllCarpoolIds(String email, List<Integer> allCarpoolIds){
	CarpoolRiderDetails carpoolRiderDetails=null;
	
	for(int i=0;i<allCarpoolIds.size();i++)
	{
		carpoolRiderDetails= carpoolRiderDetailsRepository.getRidesByMailandAllCarpoolIds(email,allCarpoolIds.get(i));
		if(carpoolRiderDetails!=null)
		{
		return	carpoolRiderDetails;
			
		}
	}
	return carpoolRiderDetails;
}

@Override
public List<CarpoolRiderDetails> getNotOptedRiderDeatils(int cpid) {
	// TODO Auto-generated method stub
	return carpoolRiderDetailsRepository.getNotOptedRiderDetails(cpid);
}

@Override
public CarpoolRiderDetails getOptedRiderDeatils(int id, String email) {
	// TODO Auto-generated method stub
	return carpoolRiderDetailsRepository.getOptedRiderDetails(id, email);
}

@Override
public List<CarpoolRiderDetails> getOptedRiderDeatils(int id) {
	// TODO Auto-generated method stub
	return carpoolRiderDetailsRepository.getOptedRiderDetails(id);
}
/**
 * @author Mahesh Bheemanapalli
 * 
 *         This method is used clean carpool notifications data when scheduler runs  
 */

@Override
public String cleanCarpoolRiderNotifications() {
	// TODO Auto-generated method stub
	logger.info("BEGIN:: CarpoolRiderDetailsDAOImpl : cleanCarpoolRiderNotifications");
	String status=null;
	try {
		long beforeClean = carpoolridernotificationsrepository.count();
		logger.info("STEP1 :: CarpoolRiderDetailsDAOImpl : cleanCarpoolRiderNotifications : No-of records Before cleanCarpoolRiderNotifications() "+beforeClean);
		carpoolridernotificationsrepository.CleanCarpoolriderNotifications();
		long afterClean = carpoolridernotificationsrepository.count();
		logger.info("STEP2 :: CarpoolRiderDetailsDAOImpl : cleanCarpoolRiderNotifications : No-of records After cleanCarpoolRiderNotifications() "+afterClean);
		if(beforeClean>0&&afterClean==0) {
			status=Constants.CARPOOL_RIDER_NOTIFICATION_CLEANED;
			logger.info("CLOSED :: CarpoolRiderDetailsDAOImpl : cleanCarpoolRiderNotifications : status :"+status);
			return status;
		}
		status=Constants.CARPOOL_RIDER_NOTIFICATION_NOT_CLEANED;
		logger.info("CLOSED :: CarpoolRiderDetailsDAOImpl : cleanCarpoolRiderNotifications : status :"+status);
		return status;
	}
	catch(Exception e) {
		logger.error("CarpoolRiderDetailsDAOImpl : cleanCarpoolRiderNotifications : Inside Catch Block and Have a Exception is :"+e.getMessage());
	}
	return status;
}


	@Override
	public List<CarpoolRiderDetails> saveOptedRiderDetails(List<CarpoolRiderDetails> carpoolRiderDetails) {
		try {
			logger.info("CarpoolRiderDetailsDAOImpl::saveOptedRiderDetails");
			 carpoolRiderDetailsRepository.save(carpoolRiderDetails);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return carpoolRiderDetails;
	}

	/**
	 *  @author:Rajesh Sekhamuri
	 */
	@Override
	public List<Carpooldetails> getCPDetailsByCPID(String emailId, Integer id) {
		return carpooldetailsRepository.getCPDetailsByCPID(emailId, id);
	}

	
}
