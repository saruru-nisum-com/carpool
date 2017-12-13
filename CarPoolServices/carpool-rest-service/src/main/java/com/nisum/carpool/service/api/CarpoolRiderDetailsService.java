package com.nisum.carpool.service.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.CarpoolRiderOptedDetailsDto;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.dto.RiderStatusDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;


public interface CarpoolRiderDetailsService {


	//List<CarpoolRiderDetailsDTO> getRiderBookingDetails(String emailId); 
	
	//String cancelCarpoolRiderDetails(int cpid);
	
	List<CarpoolRiderDetailsDTO> cancelCarpoolRiderDetails(int cpid);

	List<CarpoolRiderDetailsDTO> findCarpoolRiderDetailsByCPId(int cpid);


	List<RiderBookingDetailsDTO> getRiderBookingDetails(String emailId); 

	ResponseEntity<?> cancelRiderBookingdetails(List<RiderBookingDetailsDTO> rides);  

	Boolean updateRiderStatus(List<RiderStatusDTO> riderStatusDtoListObj) throws Exception;
	
	List<CarpoolRiderDetails> saveOptedRiderDetails(List<CarpoolRiderDetailsDTO> carpoolRiderDetailsDTO);
	

	ServiceStatusDto addRewards(double rewards);

	Map<String, List<CarpoolRiderOptedDetailsDto>> findCarpoolRiderDetailsByParentId(int parentid);
	
	ServiceStatusDto cleanCarpoolRiderNotifications();
}
