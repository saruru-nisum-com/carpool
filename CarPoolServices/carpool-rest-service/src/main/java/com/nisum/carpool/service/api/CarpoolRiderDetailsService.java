package com.nisum.carpool.service.api;

import java.util.List;

import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.dto.RiderStatusDTO;

public interface CarpoolRiderDetailsService {


	//List<CarpoolRiderDetailsDTO> getRiderBookingDetails(String emailId); 
	
	String cancelCarpoolRiderDetails(int cpid);

	List<CarpoolRiderDetailsDTO> findCarpoolRiderDetailsByCPId(int cpid);


	List<RiderBookingDetailsDTO> getRiderBookingDetails(String emailId); 

	List<CarpoolRiderDetailsDTO> cancelRiderBookingdetails(List<CarpoolRiderDetailsDTO> rides) throws Exception; 

	/*
	 * methodAuthor: @Rajesh Sekhamuri
	 */
	public void updateRiderStatus(List<RiderStatusDTO> riderStatusDtoListObj);
	


}
