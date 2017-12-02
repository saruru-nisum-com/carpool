package com.nisum.carpool.service.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.CustomerCarpooldetailsDto;
import com.nisum.carpool.service.dto.DriverCarPoolDto;
import com.nisum.carpool.service.dto.ParentCarpoolDetailsDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.exception.CarpooldetailsServiceException;


public interface CarpooldetailsService {
	ServiceStatusDto updateCarpooldetails(CarpooldetailsDto carpooldetailsDto);

	ResponseEntity<?> createCarPooldetails(CarpooldetailsDto carpooldetailsDto);

	List<CustomerCarpooldetailsDto> getCarPoolDetails(String location);

	ServiceStatusDto cancelCarpooldetails(CarpooldetailsDto carpooldetailsDto);

	CarpooldetailsDto loadCarpoolDetailsById(int carpoolId);
	List<ParentCarpoolDetailsDto> getCarpoolsByDriver(String email) throws CarpooldetailsServiceException;
	
	List<DriverCarPoolDto> getCarPoolsByParentId(int parentId) throws CarpooldetailsServiceException;
	
	


}
