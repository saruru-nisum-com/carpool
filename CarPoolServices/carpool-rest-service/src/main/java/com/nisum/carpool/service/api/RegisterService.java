package com.nisum.carpool.service.api;

import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;

public interface RegisterService {
	
	public ServiceStatusDto registerDriverorRider(RegisterDTO registerDTO);



}
