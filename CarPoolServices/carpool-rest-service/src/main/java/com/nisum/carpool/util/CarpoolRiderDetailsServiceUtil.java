package com.nisum.carpool.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.data.util.Ride_Status;
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
	
	public static List<CarpoolRiderDetails> convertDtoTODao(List<CarpoolRiderDetailsDTO> carpoolRiderdetailsDtoList) {
		List<CarpoolRiderDetails> carpooldetailslist = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(carpoolRiderdetailsDtoList)) {
			carpoolRiderdetailsDtoList.forEach(c->{
				CarpoolRiderDetails carpoolRiderdetails = new CarpoolRiderDetails();
				carpoolRiderdetails.setId(c.getId());
				carpoolRiderdetails.setCpid(c.getCpid());
				carpoolRiderdetails.setEmailid(c.getEmailid());
				carpoolRiderdetails.setStatus(c.getStatus());
				carpoolRiderdetails.setReason(c.getReason());
				carpoolRiderdetails.setRewards(c.getRewards());
				if(c.getModifieddate()!=null)
				carpoolRiderdetails.setModifieddate(c.getModifieddate().toLocalDateTime());
				if(c.getCreateddate()!=null)
				carpoolRiderdetails.setCreateddate(c.getCreateddate().toLocalDateTime());
				
				carpooldetailslist.add(carpoolRiderdetails);
			});
		}
		return carpooldetailslist;

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

	public static List<CarpoolRiderDetails> convertOptedRiderDtoToDao(List<CarpoolRiderDetailsDTO> carpoolRiderDetailsDTO) {
		
		List<CarpoolRiderDetails> listOfCarpools=new ArrayList<>();
		if (CollectionUtils.isNotEmpty(carpoolRiderDetailsDTO)) {
			carpoolRiderDetailsDTO.forEach(c->{
				CarpoolRiderDetails carpoolRiderdetails = new CarpoolRiderDetails();
				carpoolRiderdetails.setId(CarpooldetailsServiceUtil.getRandomInt());
				carpoolRiderdetails.setCpid(c.getCpid());
				carpoolRiderdetails.setEmailid(c.getEmailid());
				carpoolRiderdetails.setStatus(Ride_Status.REQUESTED.getValue());
				carpoolRiderdetails.setReason(c.getReason());
				carpoolRiderdetails.setRewards(c.getRewards());
				carpoolRiderdetails.setLocation(c.getLocation());
				if(c.getModifieddate()!=null)
				carpoolRiderdetails.setModifieddate(c.getModifieddate().toLocalDateTime());
				try {
				if(c.getCreateddate()!=null)
				carpoolRiderdetails.setCreateddate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
				}catch (Exception e) {
					e.getMessage();
				}
				//System.out.println("timstamp"+new Timestamp(System.currentTimeMillis()).toLocalDateTime());
				listOfCarpools.add(carpoolRiderdetails);
			});
		}
		return listOfCarpools;
	}
}
