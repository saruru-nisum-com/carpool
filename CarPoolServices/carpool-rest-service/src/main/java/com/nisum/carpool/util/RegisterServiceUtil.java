package com.nisum.carpool.util;

import java.sql.Timestamp;

import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.service.dto.RegisterDTO;

public class RegisterServiceUtil {


	/**
	 * @author Harish Kumar Gudivada
	 * 
	 * This method is used to convert RegisterDomain object to RegisterDto object
	 * @param reg
	 * @return
	 */
	
	public static RegisterDTO convertDaoObjectToDto(RegisterDomain reg) {
		RegisterDTO dto=new RegisterDTO();
		dto.setRegistrationId(reg.getRegistrationid());
		dto.setCreatedDate(CommonsUtil.convertLocalDateTimeToTimeStamp(reg.getCreateddate()));
		dto.setEmailNotification(reg.isEmailnotification());
		dto.setIsRider(reg.getIsrider());
		dto.setLatitude(reg.getLatitude());
		dto.setLongitude(reg.getLongitude());
		dto.setLocation(reg.getLocation());
		dto.setMobile(reg.getMobile());
		dto.setModifiedDate(Timestamp.valueOf(reg.getModifieddate()));
		dto.setNearby(reg.getNearby());
		dto.setEmailId(reg.getEmailid());
		dto.setVehicleType(reg.getVehicletype());
		return dto;
	}
	
	/**
	 * @author Harish Kumar Gudivada
	 * 
	 * This method is used to convert RegisterDto object to RegisterDomain object
	 * @param reg
	 * @return
	 */
	public static RegisterDomain convertDaoObjectToDto(RegisterDTO dto) {
		RegisterDomain userReg=new RegisterDomain();
		userReg.setRegistrationid(dto.getRegistrationId());
		userReg.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(dto.getCreatedDate()));
		userReg.setEmailnotification(dto.isEmailNotification());
		userReg.setIsrider(dto.getIsRider());
		userReg.setLatitude(dto.getLatitude());
		userReg.setLongitude(dto.getLongitude());
		userReg.setLocation(dto.getLocation());
		userReg.setMobile(dto.getMobile());
		userReg.setModifieddate(dto.getModifiedDate().toLocalDateTime());
		userReg.setNearby(dto.getNearby());
		userReg.setEmailid(dto.getEmailId());
		userReg.setVehicletype(dto.getVehicleType());
		return userReg;
	}

}
