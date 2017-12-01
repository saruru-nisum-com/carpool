package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;


public interface CarpoolRiderDetailsDAO {

	public List<CarpoolRiderDetails> getRiderBookingDetails(String emailId);
	

	String cancelCarpoolRiderDetails(int cpid);

	public List<CarpoolRiderDetails> findCarpoolRiderDetailsByCPId(int cpid);
	
	
	public List<CarpoolRiderDetails> getRidersByCpID(Integer poolid) ;

}
