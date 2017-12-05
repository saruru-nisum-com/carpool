package com.nisum.carpool.data.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.carpool.data.dao.api.CarpoolRiderDetailsDAO;
import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.data.repository.CarpoolRiderDetailsRepository;
import com.nisum.carpool.data.util.Constants;
import com.nisum.carpool.data.util.Pool_Status;

@Configuration
public class CarpoolRiderDetailsDAOImpl implements CarpoolRiderDetailsDAO {

	@Autowired
	CarpoolRiderDetailsRepository carpoolRiderDetailsRepository;

	@Override
	public List<CarpoolRiderDetails> getRiderBookingDetails(String emailId) {

		return (List<CarpoolRiderDetails>) carpoolRiderDetailsRepository.getRiderBookingDetails(emailId);
	}

	@Override
	public List<CarpoolRiderDetails> findCarpoolRiderDetailsByCPId(int cpid) {

		return carpoolRiderDetailsRepository.findCarpoolRiderDetailsByCPId(cpid);
	}

	//cancel Rider details when Driver cancel carpool
	@Override
	public String cancelCarpoolRiderDetails(int cpid) {
		System.out.println("in cancelCarpool Rider daoImpl cpId=**"+cpid);
		List<CarpoolRiderDetails> carPoolData=carpoolRiderDetailsRepository.getRiderDetailsByCpId(cpid);
		
		System.out.println("carPoll rider cancel size=="+carPoolData.size());
		
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

}
