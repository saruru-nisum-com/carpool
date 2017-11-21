package com.nisum.carpool.service.impl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.service.api.CarpooldetailsService;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.Constants;
import com.nisum.carpool.util.CarpooldetailsServiceUtil;

@Service
public class CarpooldetailsServiceImpl implements CarpooldetailsService{

	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsServiceImpl.class);
	@Autowired
	CarpooldetailsDAO carpooldetailsDAO;
	@Override
	public ServiceStatusDto updateCarpooldetails(CarpooldetailsDto carpooldetailsDto) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsServiceImpl : updateCarpooldetails");
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		carpooldetailsDto.setModifieddate(modifiedDate);
		Carpooldetails carpooldetails = CarpooldetailsServiceUtil.convertDtoTODao(carpooldetailsDto);
		String updateCarpooldetails = carpooldetailsDAO.updateCarpooldetails(carpooldetails);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		if(ObjectUtils.anyNotNull(updateCarpooldetails))
		{
			logger.info("CarpooldetailsServiceImpl : updateCarpooldetails ::"+updateCarpooldetails);
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(updateCarpooldetails);
		}
		return serviceStatusDto;
	}
	
	@Override
	public ServiceStatusDto createCarPooldetails(CarpooldetailsDto carpooldetailsDto) {
		// TODO Auto-generated method stub
		logger.info("PostRideServiceImpl:createCarPool");
		
		Carpooldetails carpooldetails = CarpooldetailsServiceUtil.convertDtoTODao(carpooldetailsDto);
		
		String validstatus = checkValidCarpool(carpooldetails);
		logger.info("validstatus " + validstatus);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		
		if(validstatus.equals(Constants.CARPOOLEXISTS)) {
			
			logger.info("PostRideServiceImpl: posting a ride failed ");
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(Constants.CARPOOLEXISTS);
			return serviceStatusDto;

			
		}
		
		logger.info("valid code");
		List<Carpooldetails> carPoolList = processPostRideDomain(carpooldetails);

	    String status = carpooldetailsDAO.addCarpoolDetails(carPoolList);
	    if(status.equals(Constants.MSG_CARPOOL_ADD))
		{
			logger.info("CarpooldetailsServiceImpl: posted a ride succesfully ");
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(Constants.MSG_CARPOOL_ADD);
		}
		return serviceStatusDto;

	}

	private String checkValidCarpool(Carpooldetails carpooldetails) {
		
		//to check if the carpool is already available in db for the user with the given fromdate and todate
		
		logger.info("CarpooldetailsServiceImpl:checkValidCarpool");
		
		return carpooldetailsDAO.checkValidCarpool(carpooldetails);
		
	}
	
	public static List<Carpooldetails> processPostRideDomain(Carpooldetails carpooldetails) {
		
		// to create a list of carpool domain objects to be sent to data access layer
		
		List<Carpooldetails> carPoolList = new ArrayList<Carpooldetails>();
	    
	    int parentid = 0;
	    
	    boolean start = true;
	    boolean pdone = false;
	    
	    logger.info("from date " + carpooldetails.getFromDate());
	    logger.info("to date " + carpooldetails.getToDate());
	    
	    int days = CarpooldetailsServiceUtil.getNo_of_days(carpooldetails.getFromDate(), carpooldetails.getToDate());
	    days = days + 1;
	    
	    logger.info("Number of days " + days);
	    for(int i=0;i<days;i++) {
	    	
	    	//checking if parent record is added to list, if yes then start again to add child records
	    	
	    	if(pdone == true) {
	    		i = 0;
	    		pdone = false;
	    	}
	    	
	    	Carpooldetails cp = new Carpooldetails();
	    	int id = CarpooldetailsServiceUtil.getRandomInt();
	    	
	    	//checking if parentid is set, else set parentid to id
	    	
	    	
	    	if(parentid==0)
	    	parentid = id;
	    	cp.setId(id);
	    	cp.setParentid(parentid);
	    	
	    	//setting original fromdate and todate for parent record in carpool db
	    	
	    	if(start == true) {
	    		
	    		cp.setFromDate(carpooldetails.getFromDate());
	    	    cp.setToDate(carpooldetails.getToDate());
	    		start = false;
	    		pdone = true;
	    			
	    	}
	    	
	    	//setting individual fromdate and todate for child records in carpool db
	    	
	    	else {
	    	
	    	cp.setFromDate(CarpooldetailsServiceUtil.getAddedDate(carpooldetails.getFromDate(), i));
	    cp.setToDate(CarpooldetailsServiceUtil.getAddedDate(carpooldetails.getFromDate(), i));
	    
	    	} 
	    	
	    	cp.setFromtime(carpooldetails.getFromtime());
	    	cp.setToTime(carpooldetails.getToTime());
	    cp.setCreateddate(carpooldetails.getCreateddate());
	    	cp.setModifieddate(carpooldetails.getModifieddate());
	    	cp.setNoofseats(carpooldetails.getNoofseats());
	    cp.setStatus(carpooldetails.getStatus());
	    	cp.setUserid(carpooldetails.getUserid());
	    	cp.setVehicleType(carpooldetails.getVehicleType());
	    	
	    	carPoolList.add(cp);
	}
	    
	    return carPoolList;
	    
	}
	
	public List<CarpooldetailsDto> getCarPoolDetails() {
		List<CarpooldetailsDto> dto=new ArrayList<CarpooldetailsDto>();
		try {
			
			SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			List<Carpooldetails> listPost=carpooldetailsDAO.getAllCarPoolDetails();
			for(Carpooldetails rd:listPost) {
				CarpooldetailsDto cr=new CarpooldetailsDto();
				cr.setUserid(rd.getUserid());
				cr.setTotalNoOfSeats(rd.getNoofseats());
				cr.setVehicleType(rd.getVehicleType());
				cr.setParentid(rd.getParentid());
			    cr.setStatus(rd.getStatus());
			    cr.setFromDate(rd.getFromDate());
			    cr.setStartTime(rd.getFromtime());
			    cr.setToDate(rd.getToDate());
			    cr.setToTime(rd.getToTime());
				
			    cr.setCreateddate(rd.getCreateddate());
			   // cr.getModifieddate(rd.getModifieddate().getNanos())
			    
				/*String cst = dmyFormat.format(rd.getCreateddate().);
				System.out.println("cst"+cst.length());
				Timestamp csttimestamp = Timestamp.valueOf(cst);
				cr.setCreateddate(csttimestamp);
				
				
				//cr.setModifieddate(rd.getModifieddate());
				String mst = dmyFormat.format(rd.getModifieddate().getTimezoneOffset());
				Timestamp msttimestamp = Timestamp.valueOf(mst);
				cr.setModifieddate(msttimestamp);
				dto.add(cr);*/
			}
			
		}catch(Exception e) {
			System.out.println("postRide Service imple ::getCarPoolDetails"+e.getMessage());
		}
		
		
		return dto;
	}
	
}
