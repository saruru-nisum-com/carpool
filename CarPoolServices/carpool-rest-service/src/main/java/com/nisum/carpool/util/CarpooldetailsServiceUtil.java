package com.nisum.carpool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
				carpooldetailsDto.setStartTime(c.getFromtime());
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
			carpooldetailsDto.setStartTime(carpooldetails.getFromtime());
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
		carpooldetails.setFromtime(carpooldetailsDto.getStartTime());
		carpooldetails.setToTime(carpooldetailsDto.getToTime());
		carpooldetails.setStatus(carpooldetailsDto.getStatus());
		carpooldetails.setCreateddate(carpooldetailsDto.getCreateddate());
		carpooldetails.setModifieddate(carpooldetailsDto.getModifieddate());
		return carpooldetails;

	}
	
	public static int getNo_of_days(String fromDate, String toDate) {
		
		//to find number of days between two dates
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Date dateStart = null;
		Date dateEnd = null;
		
		try {
			dateStart = simpleDateFormat.parse(fromDate);
		
			dateEnd = simpleDateFormat.parse(toDate);
	    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    int diff = (int) Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
	    
		return diff;
	}

	public static int getRandomInt() {
		// to find random generated int value
		Random r = new Random();
		return (10000000 + r.nextInt(89999999));
	}

	public static String getAddedDate(String date, int no_of_days) {
		// to add days to a given date
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		Date dateStart = null;
			
		try {
			dateStart = simpleDateFormat.parse(date);
	    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateStart);
		cal.add( Calendar.DATE, no_of_days );
		
		
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println(cal.getTime());
		// Output "Wed Sep 26 14:23:28 EST 2012"

		String formatted = format1.format(cal.getTime());
		System.out.println(formatted);

		return formatted;
	}

}
