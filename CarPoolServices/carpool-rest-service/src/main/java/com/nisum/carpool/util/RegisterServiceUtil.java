package com.nisum.carpool.util;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.service.dto.RegisterDTO;

public class RegisterServiceUtil {

	private static Logger logger = LoggerFactory.getLogger(RegisterServiceUtil.class);
	
	/**
	 * @author Harish Kumar Gudivada
	 * 
	 * This method is used to convert RegisterDomain object to RegisterDto object
	 * @param reg
	 * @return
	 */
	
	public static RegisterDTO convertRegisterDomainObjectToRegisterDto(RegisterDomain reg) {
		RegisterDTO dto=new RegisterDTO();
		logger.info("Entered into RegisterServiceUtil :: convertRegisterDomainObjectToRegisterDto");
		try {
			if(reg!=null) {
				dto.setRegistrationId(reg.getRegistrationid());
				if(reg.getCreateddate()!=null)
					dto.setCreatedDate(CommonsUtil.convertLocalDateTimeToTimeStamp(reg.getCreateddate()));
				dto.setEmailNotification(reg.isEmailnotification());
				dto.setIsRider(reg.getIsrider());
				dto.setLatitude(reg.getLatitude());
				dto.setLongitude(reg.getLongitude());
				dto.setLocation(reg.getLocation());
				dto.setMobile(reg.getMobile());
				if(reg.getModifieddate()!=null)
					dto.setModifiedDate(Timestamp.valueOf(reg.getModifieddate()));
				dto.setNearby(reg.getNearby());
				dto.setEmailId(reg.getEmailid());
				dto.setVehicleType(reg.getVehicletype());
			}
		}catch (Exception e) {
			logger.error("Exception Occured in Class:RegisterServiceUtil Method:convertRegisterDomainObjectToRegisterDto Message:"+e.getMessage());
		}
		logger.info("Exit from RegisterServiceUtil :: convertRegisterDomainObjectToRegisterDto");
		return dto;
	}
	
	/**
	 * @author Harish Kumar Gudivada
	 * 
	 * This method is used to convert RegisterDto object to RegisterDomain object
	 * @param reg
	 * @return
	 */
	public static RegisterDomain convertRegisterDtoObjectToRegisterDomain(RegisterDTO dto) {
		logger.info("Entered into RegisterServiceUtil ::convertRegisterDtoObjectToRegisterDomain");
		RegisterDomain userReg=new RegisterDomain();
		try {
			userReg.setRegistrationid(dto.getRegistrationId());
			if(dto.getCreatedDate()!=null)
			userReg.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(dto.getCreatedDate()));
			userReg.setEmailnotification(dto.isEmailNotification());
			userReg.setIsrider(dto.getIsRider());
			userReg.setLatitude(dto.getLatitude());
			userReg.setLongitude(dto.getLongitude());
			userReg.setLocation(dto.getLocation());
			userReg.setMobile(dto.getMobile());
			if(dto.getModifiedDate()!=null)
			userReg.setModifieddate(dto.getModifiedDate().toLocalDateTime());
			userReg.setNearby(dto.getNearby());
			userReg.setEmailid(dto.getEmailId());
			userReg.setVehicletype(dto.getVehicleType());
		}catch (Exception e) {
			logger.error("Exception Occured in Class:RegisterServiceUtil Method:convertRegisterDtoObjectToRegisterDomain Message:"+e.getMessage());
		}
		logger.info("Exit from RegisterServiceUtil ::convertRegisterDtoObjectToRegisterDomain");
		return userReg;
	}

}
