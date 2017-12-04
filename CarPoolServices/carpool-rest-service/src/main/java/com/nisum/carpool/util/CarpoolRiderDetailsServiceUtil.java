package com.nisum.carpool.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.RiderStatusDTO;


public class CarpoolRiderDetailsServiceUtil {
	
	public static List<CarpoolRiderDetailsDTO> convertDaoTODto(List<CarpoolRiderDetails> carpoolRiderdetailsList) {
		List<CarpoolRiderDetailsDTO> carpooldetailsDtos = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(carpoolRiderdetailsList)) {
			carpoolRiderdetailsList.forEach(c->{
				CarpoolRiderDetailsDTO carpoolRiderdetailsDto = new CarpoolRiderDetailsDTO();
				carpoolRiderdetailsDto.setId(c.getId());
				carpoolRiderdetailsDto.setCpid(c.getCpid());
				carpoolRiderdetailsDto.setEmailid(c.getEmailid());
				carpoolRiderdetailsDto.setStatus(c.getStatus());
				carpoolRiderdetailsDto.setNotifyme(c.isNotifyme());
				carpoolRiderdetailsDto.setReason(c.getReason());
				carpoolRiderdetailsDto.setRewards(c.getRewards());
				carpoolRiderdetailsDto.setModifieddate(Timestamp.valueOf(c.getModifieddate()));
				carpoolRiderdetailsDto.setCreateddate(Timestamp.valueOf(c.getCreateddate()));
				
				carpooldetailsDtos.add(carpoolRiderdetailsDto);
			});
		}
		return carpooldetailsDtos;

	}

	
	public static CarpoolRiderDetails convertRiderStatusDtoToDao(RiderStatusDTO carpoolRideStatusrDtoObj) {
		CarpoolRiderDetails carpoolRiderDetailsObj = new CarpoolRiderDetails();
		carpoolRiderDetailsObj.setId(carpoolRideStatusrDtoObj.getId());
//		carpoolRiderDetailsObj.setCpid(carpoolRideStatusrDtoObj.getCpid());
		carpoolRiderDetailsObj.setEmailid(carpoolRideStatusrDtoObj.getRiderEmailId());
		carpoolRiderDetailsObj.setStatus(carpoolRideStatusrDtoObj.getRiderStatus());
		carpoolRiderDetailsObj.setReason(carpoolRideStatusrDtoObj.getReasonCode());
		return carpoolRiderDetailsObj;
	}

	
}
