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

@Configuration
public class CarpoolRiderDetailsDAOImpl implements CarpoolRiderDetailsDAO {
	
	@Autowired
	CarpoolRiderDetailsRepository carpoolRiderDetailsRepository;

	
	@Override
	public List<CarpoolRiderDetails> getRiderBookingDetails(String emailId) {
		// TODO Auto-generated method stub
		return  (List<CarpoolRiderDetails>) carpoolRiderDetailsRepository.getRiderBookingDetails(emailId);
	}


	@Override
	public String cancelCarpoolRiderDetails(int cpid) {
		
		List<CarpoolRiderDetails> carPoolData=carpoolRiderDetailsRepository.getRiderDetailsByCpId(cpid);
		System.out.println("in cancelCarpool Rider daoImpl**");
		System.out.println("carPoll rider cancel size=="+carPoolData.size());
		
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		if(carPoolData!=null) {
			  if (CollectionUtils.isNotEmpty(carPoolData)) {
				  carPoolData.forEach(c->{
						c.setStatus(4);
					  c.setModifieddate(modifiedDate.toLocalDateTime());
					  carpoolRiderDetailsRepository.save(c);
					});
				 }
		  } 
	  
		return Constants.MSG_CANCEL_CARPOOL_RIDER;
	}

}
