package com.nisum.carpool.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CommonsUtil {
	
	public static String getErrorStacktrace(Exception ex){
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	
	public  static Timestamp getCurrentDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); 
		Date date = new Date();
		Timestamp timestamp= Timestamp.valueOf(dateFormat.format(date));
		return timestamp;
	}

	
	public static Timestamp convertLocalDateTimeToTimeStamp(LocalDateTime localTime) {
		return Timestamp.valueOf(localTime);
	}
	
	
	public static LocalDateTime convertTimeStampToLocalDateTime(Timestamp time) {
		return time.toLocalDateTime();
	}
}
