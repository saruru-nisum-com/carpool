package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;


public interface CarpoolRiderDetailsDAO {

	public List<CarpoolRiderDetails> getRiderBookingDetails(String emailId);
	
<<<<<<< HEAD
	String cancelCarpoolRiderDetails(int cpid);
=======
	public List<CarpoolRiderDetails> findCarpoolRiderDetailsByCPId(int cpid);
>>>>>>> 3c494a29fd65290cf7fa7b18e170aedf92300cdf
}
