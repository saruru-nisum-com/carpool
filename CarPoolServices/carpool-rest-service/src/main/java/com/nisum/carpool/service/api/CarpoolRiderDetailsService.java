package com.nisum.carpool.service.api;

import java.util.List;

import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;

public interface CarpoolRiderDetailsService {
<<<<<<< HEAD
	
	List<CarpoolRiderDetailsDTO> getRiderBookingDetails(String emailId); 
	
	String cancelCarpoolRiderDetails(int cpid);
=======

	List<CarpoolRiderDetailsDTO> getRiderBookingDetails(String emailId);

	List<CarpoolRiderDetailsDTO> findCarpoolRiderDetailsByCPId(int cpid);
>>>>>>> 3c494a29fd65290cf7fa7b18e170aedf92300cdf

}
