package com.nisum.carpool.service.api;

import java.util.List;

import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;


public interface CarpoolRiderDetailsService {
	
	List<CarpoolRiderDetailsDTO> getRiderBookingDetails(String emailId); 

}
