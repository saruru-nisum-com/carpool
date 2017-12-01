package com.nisum.carpool.service.api;

import java.util.List;

import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;

public interface RegisterService {
	
	public ServiceStatusDto registerDriverorRider(RegisterDTO registerDTO);
	public List<RegisterDTO> getUserRegistrationProfile(String emailId) ;
	public String searchLocation(RegisterDTO regDto)throws Exception;


}
