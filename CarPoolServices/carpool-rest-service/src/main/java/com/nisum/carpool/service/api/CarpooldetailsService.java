package com.nisum.carpool.service.api;

import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;

public interface CarpooldetailsService {
	ServiceStatusDto updateCarpooldetails(CarpooldetailsDto carpooldetailsDto);
}
