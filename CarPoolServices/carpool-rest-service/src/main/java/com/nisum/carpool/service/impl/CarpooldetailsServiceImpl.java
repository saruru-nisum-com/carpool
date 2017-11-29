package com.nisum.carpool.service.impl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.dao.api.RegisterDAO;
import com.nisum.carpool.data.dao.api.UserDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.data.domain.User;
import com.nisum.carpool.service.api.CarpooldetailsService;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.CustomerCarpooldetailsDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.CarpooldetailsServiceUtil;
import com.nisum.carpool.util.Constants;

@Service
public class CarpooldetailsServiceImpl implements CarpooldetailsService{

	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsServiceImpl.class);
	@Autowired
	CarpooldetailsDAO carpooldetailsDAO;
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	RegisterDAO registerDAO;
	
	
	Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
	@Override
	public ServiceStatusDto updateCarpooldetails(CarpooldetailsDto carpooldetailsDto) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsServiceImpl : updateCarpooldetails");
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
    public List<CarpooldetailsDto> createCarPooldetails(CarpooldetailsDto carpooldetailsDto) {
        // TODO Auto-generated method stub
        logger.info("CarpooldetailsServiceImpl:createCarPool");
        
       Carpooldetails carpooldetails = CarpooldetailsServiceUtil.convertDtoTODao(carpooldetailsDto);
        
       String validstatus = checkValidCarpool(carpooldetails);
        logger.info("validstatus " + validstatus);
        
       if(validstatus.equals(Constants.CARPOOLEXISTS)) {
            
           
           logger.info("PostRideServiceImpl: posting a ride failed ");
            return null;

           
       }
        
       logger.info("valid code");
        List<Carpooldetails> carPoolList = processPostRideDomain(carpooldetails);
        
       List<Carpooldetails> cpd = carpooldetailsDAO.addCarpoolDetails(carPoolList);
        

       return CarpooldetailsServiceUtil.convertDaoTODto(cpd);
      
    }
	public String checkValidCarpool(Carpooldetails carpooldetails) {
		
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
	    	cp.setCreateddate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
	    	cp.setModifieddate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
	    	cp.setNoofseats(carpooldetails.getNoofseats());
	    cp.setStatus(1);
	    	cp.setUserid(carpooldetails.getUserid());
	    	cp.setVehicleType(carpooldetails.getVehicleType());
	    	
	    	carPoolList.add(cp);
	}
	    
	    return carPoolList;
	    
	}
	/*public List<CustomerCarpooldetailsDto> getCarPoolDetails(String location)
	{
		List<Carpooldetails> carpoolList = new ArrayList<>();
		
		List<CustomerCarpooldetailsDto> carpoolListbyLocation = new ArrayList<>();
		List<CustomerCarpooldetailsDto> customerCarpooldetailsDtoList = new ArrayList<>();

	

	carpoolList = (List<Carpooldetails>) carpooldetailsDAO.getAllCarPoolDetails();
	
		
		if (carpoolList != null) {
			//List<User> usersList = userDAO.getUsers();

			//for (User user : usersList) {
				for (Carpooldetails carpool : carpoolList) {
					
					//if (user.getEmailId().equals(carpool.getUserid())) {
					User user=userDAO.findByEmailId(carpool.getUserid());
					List<RegisterDomain> registerDomain=	registerDAO.findUserRegistrationByUserId(carpool.getUserid());
				
						CustomerCarpooldetailsDto customerCarpooldetailsDto = new CustomerCarpooldetailsDto();
						customerCarpooldetailsDto.setId(carpool.getId());
						// customerCarpooldetailsDto.setCreateddate(carpool.getCreateddate());
						//customerCarpooldetailsDto.getFromDate(carpool.getFromDate());
						
						customerCarpooldetailsDto.setParentid(carpool.getParentid());
						customerCarpooldetailsDto.setTotalNoOfSeats(carpool.getNoofseats());
						customerCarpooldetailsDto.setStatus(carpool.getStatus());
						customerCarpooldetailsDto.setStartTime(carpool.getToDate());
						customerCarpooldetailsDto.setFromDate(carpool.getFromDate());
						customerCarpooldetailsDto.setToTime(carpool.getToTime());
						customerCarpooldetailsDto.setVehicleType(carpool.getVehicleType());
						customerCarpooldetailsDto.setUserid(carpool.getUserid());
						if(user!=null)
						customerCarpooldetailsDto.setUserName(user.getUserName());
if(registerDomain!=null && registerDomain.size()>0) {
						customerCarpooldetailsDto.setLocation(registerDomain.get(0).getLocation());
                           if(registerDomain.get(0).getMobile()!=null)
						customerCarpooldetailsDto.setMobile(registerDomain.get(0).getMobile());
}
						customerCarpooldetailsDtoList.add(customerCarpooldetailsDto);

					}
				}

			
	if(location!=null)
	{
		for(CustomerCarpooldetailsDto customerCarpooldetailsDto:customerCarpooldetailsDtoList)
		{
			if(location.equalsIgnoreCase(customerCarpooldetailsDto.getLocation()))
			{
				carpoolListbyLocation.add(customerCarpooldetailsDto);
			}
		}
		return carpoolListbyLocation;
	}

		return customerCarpooldetailsDtoList;

	}*/
	
	public List<CustomerCarpooldetailsDto> getCarPoolDetails(String location)
	{
		List<Carpooldetails> carpoolLists = new ArrayList<>();
		
		List<CustomerCarpooldetailsDto> carpoolListbyLocation = new ArrayList<>();
		List<CustomerCarpooldetailsDto> customerCarpooldetailsDtoList = new ArrayList<>();
		
		//List<String> useridsList=null;
         Set<String> useridsSet=null;
		//useridsList=	carpooldetailsDAO.getAllUserIDs();
         carpoolLists= (List<Carpooldetails>) carpooldetailsDAO.getAllCarPoolDetails();
         
		if(carpoolLists!=null)
		{
			useridsSet=new HashSet<>();
		for(Carpooldetails car:carpoolLists)
		{
	
			
			useridsSet.add(car.getUserid());
		}
			
			for(String userid:useridsSet)
			{
				List<Carpooldetails> carpoolList=	carpooldetailsDAO.getCarPoolByMailID(userid);
				User user=userDAO.findByEmailId(userid);
				List<RegisterDomain> registerDomain=	registerDAO.findUserRegistrationByUserId(userid);
				CustomerCarpooldetailsDto carpooldetailsDto=new CustomerCarpooldetailsDto();
				carpooldetailsDto.setListCarpoolDetails(carpoolList);
				if(user!=null)
				carpooldetailsDto.setUserName(user.getUserName());
				if(registerDomain!=null && registerDomain.size()>0) {
				carpooldetailsDto.setLocation(registerDomain.get(0).getLocation());
				carpooldetailsDto.setMobile(registerDomain.get(0).getMobile());
				}
				customerCarpooldetailsDtoList.add(carpooldetailsDto);
				
			
			}
			if(location!=null)
			{
				for(CustomerCarpooldetailsDto customerCarpooldetailsDto:customerCarpooldetailsDtoList)
				{
					if(location.equalsIgnoreCase(customerCarpooldetailsDto.getLocation()))
							{
						carpoolListbyLocation.add(customerCarpooldetailsDto);
							}
				}
				return carpoolListbyLocation;
				
			}
			return customerCarpooldetailsDtoList;

		}

	
	
		return customerCarpooldetailsDtoList;
	}
	
	
	
	
}
