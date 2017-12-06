package com.nisum.carpool.service.api;

import java.util.List;
import java.util.Map;

import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.CarpoolRiderOptedDetailsDto;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.dto.RiderStatusDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;

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

	ServiceStatusDto addRewards(double rewards);

	Map<String, List<CarpoolRiderOptedDetailsDto>> findCarpoolRiderDetailsByParentId(int parentid);
}
