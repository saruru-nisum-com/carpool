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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.util.Pool_Status;
import com.nisum.carpool.rest.api.CarpooldetailsRestService;
import com.nisum.carpool.service.dto.CarpooldetailsDto;



public class CarpooldetailsServiceUtil {
	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsServiceUtil.class);
	public static List<CarpooldetailsDto> convertDaoTODto(List<Carpooldetails> carpooldetailsList) {
		Pool_Status poolStatus;
		List<CarpooldetailsDto> carpooldetailsDtos = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(carpooldetailsList)) {
			carpooldetailsList.forEach(c->{
				CarpooldetailsDto carpooldetailsDto = new CarpooldetailsDto();
				carpooldetailsDto.setId(c.getId());
				carpooldetailsDto.setParentid(c.getParentid());
				carpooldetailsDto.setEmailId(c.getEmailId());
				carpooldetailsDto.setVehicleType(c.getVehicleType());
				carpooldetailsDto.setTotalNoOfSeats(c.getNoofseats());
				carpooldetailsDto.setFromDate(c.getFromDate());
				carpooldetailsDto.setToDate(c.getToDate());
				carpooldetailsDto.setStartTime(c.getFromtime());
				carpooldetailsDto.setToTime(c.getToTime());
				Pool_Status pool_Status = Pool_Status.values()[(c.getStatus()-1)];
				carpooldetailsDto.setStatusName(pool_Status.toString());
				carpooldetailsDto.setStatus(c.getStatus());
				carpooldetailsDto.setLocation(c.getLocation());
				carpooldetailsDto.setLongitude(c.getLongitude());
				carpooldetailsDto.setLatitude(c.getLatitude());
				if(c.getCreateddate()!=null)
				carpooldetailsDto.setCreateddate(CommonsUtil.convertLocalDateTimeToTimeStamp(c.getCreateddate()));
				if(c.getModifieddate()!=null)
				carpooldetailsDto.setModifieddate(CommonsUtil.convertLocalDateTimeToTimeStamp(c.getModifieddate()));
				carpooldetailsDtos.add(carpooldetailsDto);
			});
		}
		return carpooldetailsDtos;

	}

	public static CarpooldetailsDto convertDaoToDtoInstance(Carpooldetails carpooldetails) throws Exception{
		CarpooldetailsDto carpooldetailsDto = new CarpooldetailsDto();
		try {
		if (ObjectUtils.anyNotNull(carpooldetails)) {
			carpooldetailsDto.setId(carpooldetails.getId());
			carpooldetailsDto.setParentid(carpooldetails.getParentid());
			carpooldetailsDto.setEmailId(carpooldetails.getEmailId());
			carpooldetailsDto.setVehicleType(carpooldetails.getVehicleType());
			carpooldetailsDto.setTotalNoOfSeats(carpooldetails.getNoofseats());
			carpooldetailsDto.setFromDate(carpooldetails.getFromDate());
			carpooldetailsDto.setToDate(carpooldetails.getToDate());
			carpooldetailsDto.setStartTime(carpooldetails.getFromtime());
			carpooldetailsDto.setToTime(carpooldetails.getToTime());
			Pool_Status pool_Status = Pool_Status.values()[(carpooldetails.getStatus()-1)];
			carpooldetailsDto.setStatusName(pool_Status.toString());
			carpooldetailsDto.setStatus(carpooldetails.getStatus());
			carpooldetailsDto.setLocation(carpooldetails.getLocation());
			carpooldetailsDto.setLongitude(carpooldetails.getLongitude());
			carpooldetailsDto.setLatitude(carpooldetails.getLatitude());
			if(carpooldetails.getCreateddate()!=null)
			carpooldetailsDto.setCreateddate(CommonsUtil.convertLocalDateTimeToTimeStamp(carpooldetails.getCreateddate()));
			if(carpooldetails.getModifieddate()!=null)
			carpooldetailsDto.setModifieddate(CommonsUtil.convertLocalDateTimeToTimeStamp(carpooldetails.getModifieddate()));
		}
		}catch (Exception e) {
			throw e;
		}
		return carpooldetailsDto;
	}

	public static Carpooldetails convertDtoTODao(CarpooldetailsDto carpooldetailsDto) {

		Carpooldetails carpooldetails = new Carpooldetails();
		carpooldetails.setId(carpooldetailsDto.getId());
		carpooldetails.setParentid(carpooldetailsDto.getParentid());
		carpooldetails.setEmailId(carpooldetailsDto.getEmailId());
		carpooldetails.setVehicleType(carpooldetailsDto.getVehicleType());
		carpooldetails.setNoofseats(carpooldetailsDto.getTotalNoOfSeats());
		carpooldetails.setFromDate(carpooldetailsDto.getFromDate());
		carpooldetails.setToDate(carpooldetailsDto.getToDate());
		carpooldetails.setFromtime(carpooldetailsDto.getStartTime());
		carpooldetails.setToTime(carpooldetailsDto.getToTime());
		carpooldetails.setStatus(carpooldetailsDto.getStatus());
		carpooldetails.setLocation(carpooldetailsDto.getLocation());
		carpooldetails.setLongitude(carpooldetailsDto.getLongitude());
		carpooldetails.setLatitude(carpooldetailsDto.getLatitude());
		if(carpooldetailsDto.getCreateddate()!=null)
		carpooldetails.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(carpooldetailsDto.getCreateddate()));
		if(carpooldetailsDto.getModifieddate()!=null)
		carpooldetails.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(carpooldetailsDto.getModifieddate()));
		return carpooldetails;

	}
	
	public static Carpooldetails convertUpdateDtoTODao(CarpooldetailsDto carpooldetailsDto) {
		Carpooldetails carpooldetails = new Carpooldetails();
		try {
			
			carpooldetails.setId(carpooldetailsDto.getId());
			carpooldetails.setParentid(carpooldetailsDto.getParentid());
			carpooldetails.setEmailId(carpooldetailsDto.getEmailId());
			carpooldetails.setVehicleType(carpooldetailsDto.getVehicleType());
			carpooldetails.setNoofseats(carpooldetailsDto.getTotalNoOfSeats());
			carpooldetails.setFromDate(carpooldetailsDto.getFromDate());
			carpooldetails.setToDate(carpooldetailsDto.getToDate());
			carpooldetails.setFromtime(carpooldetailsDto.getStartTime());
			carpooldetails.setToTime(carpooldetailsDto.getToTime());
			carpooldetails.setStatus(carpooldetailsDto.getStatus());
			carpooldetails.setLocation(carpooldetailsDto.getLocation());
			carpooldetails.setLongitude(carpooldetailsDto.getLongitude());
			carpooldetails.setLatitude(carpooldetailsDto.getLatitude());
			if(carpooldetailsDto.getModifieddate()!=null)
			carpooldetails.setModifieddate(carpooldetailsDto.getModifieddate().toLocalDateTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return carpooldetails;

	}
	/**
	 * @author Manohar Dhavala : CPL005: Create Car Pools (Post a ride)
	 * 
	 *         This method is used for finding the number of days between fromdate and todate
	 */
	public static int getNo_of_days(String fromDate, String toDate) {
		
		//to find number of days between two dates
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

	/**
	 * @author Manohar Dhavala : CPL005: Create Car Pools (Post a ride)
	 * 
	 *         This method is used for finding a random int
	 */

	public static int getRandomInt() {
		// to find random generated int value
		Random r = new Random();
		return (10000000 + r.nextInt(89999999));
	}
	
	/**
	 * @author Manohar Dhavala : CPL005: Create Car Pools (Post a ride)
	 * 
	 *         This method is used to for adding n number of days to a given date
	 */
	public static String getAddedDate(String date, int no_of_days) {
		// to add days to a given date
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
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
		
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		// Output "Wed Sep 26 14:23:28 EST 2012"

		String formatted = format1.format(cal.getTime());
		return formatted;
	}


}
