package com.nisum.carpool.service.api;

import java.util.List;

import com.nisum.carpool.service.dto.CarpooldetailsDto;

/**
 * @author vkallada
 * CreatedDate 14th Dec
 */
public interface IDriverPoolHistoryService {
	
	public List<CarpooldetailsDto> getDriverPoolHistory(CarpooldetailsDto carpooldetailsDto);
	
}
