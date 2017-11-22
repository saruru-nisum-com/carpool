package com.nisum.carpool.util;

import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.service.dto.RegisterDTO;

public class RegisterServiceUtil {

	public static RegisterDomain convertDtoObjectTODomain(RegisterDTO registerDTO) {
		RegisterDomain registerDomain = new RegisterDomain();
		registerDomain.setRegistrationid(registerDTO.getRegistrationId());
		registerDomain.setUserid(registerDTO.getUserId());
		registerDomain.setVehicletype(registerDTO.getVehicleType());
		registerDomain.setLocation(registerDTO.getLocation());
		registerDomain.setLatitude(registerDTO.getLatitude());
		registerDomain.setLongitude(registerDTO.getLongitude());
		registerDomain.setNearby(registerDTO.getNearby());
		registerDomain.setMobile(registerDTO.getMobile());
		registerDomain.setEmailnotification(registerDTO.isEmailNotification());
		registerDomain.setIsrider(registerDTO.getIsRider());
		registerDomain.setCreateddate(registerDTO.getCreatedDate());
		registerDomain.setModifieddate(registerDTO.getModifiedDate());
		
		return registerDomain;
	}
}
