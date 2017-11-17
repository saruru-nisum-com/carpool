package com.nisum.carpool.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.service.dto.CarpooldetailsDto;


public class CarpooldetailsServiceUtil {
	public static List<CarpooldetailsDto> convertDaoTODto(List<Carpooldetails> carpooldetailsList) {
		List<CarpooldetailsDto> carpooldetailsDtos = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(carpooldetailsList)) {
			carpooldetailsList.forEach(c->{
				CarpooldetailsDto carpooldetailsDto = new CarpooldetailsDto();
				carpooldetailsDto.setId(c.getId());
				carpooldetailsDto.setParentid(c.getParentid());
				carpooldetailsDto.setUserid(c.getUserid());
				carpooldetailsDto.setVehicleType(c.getVehicleType());
				carpooldetailsDto.setTotalNoOfSeats(c.getNoofseats());
				carpooldetailsDto.setFromDate(c.getFromDate());
				carpooldetailsDto.setToDate(c.getToDate());
				carpooldetailsDto.setStartTime(c.getStartTime());
				carpooldetailsDto.setToTime(c.getToTime());
				carpooldetailsDto.setStatus(c.getStatus());
				carpooldetailsDto.setCreateddate(c.getCreateddate());
				carpooldetailsDto.setModifieddate(c.getModifieddate());
				carpooldetailsDtos.add(carpooldetailsDto);
			});
		}
		return carpooldetailsDtos;

	}

	public static CarpooldetailsDto convertDaoToDtoInstance(Carpooldetails carpooldetails) {

		CarpooldetailsDto carpooldetailsDto = new CarpooldetailsDto();
		if (ObjectUtils.anyNotNull(carpooldetails)) {
			carpooldetailsDto.setId(carpooldetails.getId());
			carpooldetailsDto.setParentid(carpooldetails.getParentid());
			carpooldetailsDto.setUserid(carpooldetails.getUserid());
			carpooldetailsDto.setVehicleType(carpooldetails.getVehicleType());
			carpooldetailsDto.setTotalNoOfSeats(carpooldetails.getNoofseats());
			carpooldetailsDto.setFromDate(carpooldetails.getFromDate());
			carpooldetailsDto.setToDate(carpooldetails.getToDate());
			carpooldetailsDto.setStartTime(carpooldetails.getStartTime());
			carpooldetailsDto.setToTime(carpooldetails.getToTime());
			carpooldetailsDto.setStatus(carpooldetails.getStatus());
			carpooldetailsDto.setCreateddate(carpooldetails.getCreateddate());
			carpooldetailsDto.setModifieddate(carpooldetails.getModifieddate());
		}
		return carpooldetailsDto;
	}

	public static Carpooldetails convertDtoTODao(CarpooldetailsDto carpooldetailsDto) {

		Carpooldetails carpooldetails = new Carpooldetails();
		carpooldetails.setId(carpooldetailsDto.getId());
		carpooldetails.setParentid(carpooldetailsDto.getParentid());
		carpooldetails.setUserid(carpooldetailsDto.getUserid());
		carpooldetails.setVehicleType(carpooldetailsDto.getVehicleType());
		carpooldetails.setNoofseats(carpooldetailsDto.getTotalNoOfSeats());
		carpooldetails.setFromDate(carpooldetailsDto.getFromDate());
		carpooldetails.setToDate(carpooldetailsDto.getToDate());
		carpooldetails.setStartTime(carpooldetailsDto.getStartTime());
		carpooldetails.setToTime(carpooldetailsDto.getToTime());
		carpooldetails.setStatus(carpooldetailsDto.getStatus());
		carpooldetails.setCreateddate(carpooldetailsDto.getCreateddate());
		carpooldetails.setModifieddate(carpooldetailsDto.getModifieddate());
		return carpooldetails;

	}

}
