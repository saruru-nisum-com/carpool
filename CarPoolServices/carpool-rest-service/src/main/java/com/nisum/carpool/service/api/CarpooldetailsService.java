package com.nisum.carpool.service.api;

import java.util.List;

import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;

public interface CarpooldetailsService {
	ServiceStatusDto updateCarpooldetails(CarpooldetailsDto carpooldetailsDto);
	
	List<CarpooldetailsDto> createCarPooldetails(CarpooldetailsDto carpooldetailsDto);
	
	 List<CarpooldetailsDto> getCarPoolDetails();
}
