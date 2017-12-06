package com.nisum.carpool.data.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
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



	/**
	 * getRiderBookingDetails  for Dao Layer
	 * parameter: emailId
	 * This method used for load the data from repository
	 * returntype:List<CarpoolRiderDetails>
	 */
	
	@Override
	public List<CarpoolRiderDetails> getRiderBookingDetails(String emailId) {
		logger.info("CarpoolRiderDetailsDAOImpl::getRiderBookingDetails::emailId");
      return (List<CarpoolRiderDetails>) carpoolRiderDetailsRepository.getRiderBookingDetails(emailId);
	}

	@Override
	public List<CarpoolRiderDetails> findCarpoolRiderDetailsByCPId(int cpid) {

		return carpoolRiderDetailsRepository.findCarpoolRiderDetailsByCPId(cpid);
	}

	// cancel Rider details when Driver cancel carpool
	@Override
	public String cancelCarpoolRiderDetails(int cpid) {
		System.out.println("in cancelCarpool Rider daoImpl cpId=**" + cpid);
		List<CarpoolRiderDetails> carPoolData = carpoolRiderDetailsRepository.getRiderDetailsByCpId(cpid);

		System.out.println("carPoll rider cancel size==" + carPoolData.size());

		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		if(carPoolData!=null) {
			  if (CollectionUtils.isNotEmpty(carPoolData)) {
				  carPoolData.forEach(c->{
					  System.out.println("pool value=="+Pool_Status.CLOSED.getValue());
						c.setStatus(Pool_Status.CANCELLED.getValue());
					  c.setModifieddate(modifiedDate.toLocalDateTime());
					  carpoolRiderDetailsRepository.save(c);
					});
				 }
		  } 
	  
		return Constants.MSG_CANCEL_CARPOOL_RIDER;
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
		logger.info("CarpoolRiderDetailsDAOImpl : updaterewardPointsWithId : To Rider");
		Set<Integer> riders = this.getSetOfCarpoolRiders();
		if (riders.size() > 0) {
			carpoolRiderDetailsRepository.udpateRiderRewardPoints(rewards, riders);
			return Constants.ADDED_REWARDS_TO_RIDER;
		}
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
		logger.info("CarpoolRiderDetailsDAOImpl : getSetOfCarpoolRiders");
		Set<Integer> SetOfClosedRiders = new HashSet<>();
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String rewardedDate = currentDate.format(formatter);
		List<Integer> carpoolClosedCpids = carpooldetailsRepository
				.getCarpooldetailsByFromDate(Pool_Status.CLOSED.getValue(), rewardedDate);
		if (carpoolClosedCpids.size() > 0) {
			for (int i = 0; i < carpoolClosedCpids.size(); i++) {
				Integer ridersId = carpoolRiderDetailsRepository.getListOfClosedRiders(Ride_Status.APPROVED.getValue(),
						carpoolClosedCpids.get(i));
				if (ObjectUtils.anyNotNull(ridersId)&&ridersId>0) {
					SetOfClosedRiders.add(ridersId);
					logger.info("CarpoolRiderDetailsDAOImpl : getListOfCarpoolRiders"+ridersId);
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
		
}
