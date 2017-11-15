/**
 * 
 */
package com.nisum.carpool.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Raunak
 *
 */
public class DateUtil {
	
	public static Date getSQLDate(String dateString){
		java.util.Date utilDate;
		java.sql.Date sqlDate = null;
		try {
			utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			sqlDate = new java.sql.Date(utilDate.getTime()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	
	public static Timestamp getSQLDateAndTime(String dateString){
		java.util.Date utilDate;
		Timestamp timestamp = null;
		try {
			utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
		    timestamp = new java.sql.Timestamp(utilDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(timestamp);
		return timestamp;
	}
	
	public static Timestamp getCurrentDateAndTime(){
		
		java.util.Date parsedDate = null;
		try {
			java.util.Date curDate = new java.util.Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String DateToStr = format.format(curDate);
			System.out.println(DateToStr);
			parsedDate = format.parse(DateToStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		return timestamp;
	}

}
