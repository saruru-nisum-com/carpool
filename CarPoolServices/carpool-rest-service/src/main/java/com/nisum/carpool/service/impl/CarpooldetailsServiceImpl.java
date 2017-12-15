package com.nisum.carpool.service.impl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.CarpoolRiderDetailsDAO;
import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.dao.api.RegisterDAO;
import com.nisum.carpool.data.dao.api.UserDAO;
import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.data.domain.User;
import com.nisum.carpool.data.util.Pool_Status;
import com.nisum.carpool.service.api.CarpooldetailsService;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.CustomerCarpooldetailsDto;
import com.nisum.carpool.service.dto.DriverCarPoolDto;
import com.nisum.carpool.service.dto.OptRideDto;
import com.nisum.carpool.service.dto.ParentCarpoolDetailsDto;
import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.dto.TodayRiderDetailsDTO;
import com.nisum.carpool.service.exception.CarpooldetailsServiceException;
import com.nisum.carpool.util.CarpooldetailsServiceUtil;
import com.nisum.carpool.util.Constants;
import com.nisum.carpool.util.OptARideServiceUtil;
import com.nisum.carpool.util.RegisterServiceUtil;


@Service
public class CarpooldetailsServiceImpl implements CarpooldetailsService{

	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsServiceImpl.class);
	@Autowired
	CarpooldetailsDAO carpooldetailsDAO;
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	RegisterDAO registerDAO;
	
	@Autowired
	CarpoolRiderDetailsDAO carpoolRiderDAO;
	
	Timestamp currentDate = new Timestamp(System.currentTimeMillis());
	@Override
	public ServiceStatusDto updateCarpooldetails(CarpooldetailsDto carpooldetailsDto) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsServiceImpl : updateCarpooldetails");
		List<CarpoolRiderDetails> carpoolRidersList = carpoolRiderDAO.getRidersByCpID(carpooldetailsDto.getId());
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		
		if(carpooldetailsDto.getTotalNoOfSeats() < getFilledSeatsInPool(carpoolRidersList)) {
			logger.info("CarpooldetailsServiceImpl : updateCarpooldetails :: selected seats less than filled seats");
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(Constants.MSG_CARPOOL_SELECTED_LESS_SEATS);
			return serviceStatusDto;
		}
			
		carpooldetailsDto.setModifieddate(currentDate);
		Carpooldetails carpooldetails = CarpooldetailsServiceUtil.convertDtoTODao(carpooldetailsDto);
		String updateCarpooldetails = carpooldetailsDAO.updateCarpooldetails(carpooldetails);
		
		if(ObjectUtils.anyNotNull(updateCarpooldetails)){
			logger.info("CarpooldetailsServiceImpl : updateCarpooldetails ::"+updateCarpooldetails);
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(updateCarpooldetails);
		}
		return serviceStatusDto;
	}
	
	@Override
	public ServiceStatusDto cancelCarpooldetails(CarpooldetailsDto carpooldetailsDto) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsServiceImpl : cancel Carpooldetails");
		
		carpooldetailsDto.setModifieddate(currentDate);
		
		String cancelCarpooldetails=null;
		try {
			Carpooldetails carpooldetails = CarpooldetailsServiceUtil.convertUpdateDtoTODao(carpooldetailsDto);
			cancelCarpooldetails = carpooldetailsDAO.cancelCarpooldetails(carpooldetails);
			logger.info("Carpooldetails after cancel  dao message::"+cancelCarpooldetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		if(ObjectUtils.anyNotNull(cancelCarpooldetails))
		{
			logger.info("CarpooldetailsServiceImpl : cancel Carpooldetails set message::"+cancelCarpooldetails);
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(cancelCarpooldetails);
		}
		
		return serviceStatusDto;
	}
	
	
	@Override
	public ResponseEntity<?> createCarPooldetails(CarpooldetailsDto carpooldetailsDto) {
		// to create car pool service method
		logger.info("CarpooldetailsServiceImpl:createCarPool");

		carpooldetailsDto.setCreateddate(new Timestamp(System.currentTimeMillis()));

		// code added by Harish Kumar Gudivada on 30th November 2017
		//for loading the location from user registration and saving in the carpool details
		if("".equals(carpooldetailsDto.getLocation())){
			RegisterDomain regDomain=null;
			List<RegisterDomain> userList = registerDAO.getLocationOfRegisteredUser(carpooldetailsDto.getEmailId());
			if(userList!=null) {
				for(RegisterDomain registedDao:userList) {
					if(registedDao.getIsrider()==0)
						regDomain=registedDao;
				}
			}
			if(regDomain!=null) {
				carpooldetailsDto.setLocation(regDomain.getLocation());
				carpooldetailsDto.setLongitude(regDomain.getLongitude());
				carpooldetailsDto.setLatitude(regDomain.getLatitude());
			}
		}
		//end

		Carpooldetails carpooldetails = CarpooldetailsServiceUtil.convertDtoTODao(carpooldetailsDto);

		String validstatus = checkValidCarpool(carpooldetails);
		logger.info("validity status of creating car pool " + validstatus);

		if (validstatus.equals(Constants.CARPOOLEXISTS)) {

			logger.info("CarpooldetailsServiceImpl: car pool already exists for the driver for the given date(s) ");

			ServiceStatusDto statusDto = new ServiceStatusDto();
			statusDto.setStatus(false);
			statusDto.setMessage(Constants.CARPOOLEXISTS);
			ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto,
					HttpStatus.BAD_REQUEST);
			return entity;
		}

		if (validstatus.equals(Constants.DRIVER_IS_REGISTERED_AS_RIDER)) {

			logger.info("PostRideServiceImpl: driver is already registered as rider for the date(s) ");

			ServiceStatusDto statusDto = new ServiceStatusDto();
			statusDto.setStatus(false);
			statusDto.setMessage(Constants.DRIVER_IS_REGISTERED_AS_RIDER);
			ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto,
					HttpStatus.BAD_REQUEST);
			return entity;

		}

		logger.info("Status for creating car pool is valid");
		
		List<Carpooldetails> carPoolList = processPostRideDomain(carpooldetails);
		String msg = carpooldetailsDAO.addCarpoolDetails(carPoolList);

		if (msg == Constants.MSG_CARPOOL_ADD) {
			logger.info("Car pool has been created succesfully");
			try {
				List<CarpooldetailsDto> cpdtolist = CarpooldetailsServiceUtil
						.convertDaoTODto(carpooldetailsDAO.getCarPoolByMailIDAndFutureDates(carpooldetails.getEmailId()));
				ResponseEntity<List<CarpooldetailsDto>> entity = new ResponseEntity<List<CarpooldetailsDto>>(cpdtolist,
						HttpStatus.OK);
				return entity;
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
			
		}

		else {
			
			logger.info("Creating car pool failed");
			ServiceStatusDto statusDto = new ServiceStatusDto();
			statusDto.setStatus(false);
			statusDto.setMessage(Constants.MSG_CARPOOL_FAILED);
			ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto,
					HttpStatus.BAD_REQUEST);
			return entity;
		}

	}
	
	/**
	 * @author Manohar Dhavala : CPL005: Create Car Pools (Post a ride)
	 * 
	 *         This method is used to check if carpool can be created or not
	 *         @param carpooldetails
	 *         @return String
	 */

	public String checkValidCarpool(Carpooldetails carpooldetails) {

		// to check if carpool can be created for the driver within the given dates

		logger.info("CarpooldetailsServiceImpl:checking if status for creating Carpool is valid");

		return carpooldetailsDAO.checkValidCarpool(carpooldetails.getEmailId(), carpooldetails.getFromDate(),
				carpooldetails.getToDate());

	}
	
	/**
	 * @author Manohar Dhavala : CPL005: Create Car Pools (Post a ride)
	 * 
	 *         This method is used to for creating domain objects to be sent to data access layer
	 */

	public static List<Carpooldetails> processPostRideDomain(Carpooldetails carpooldetails) {


		List<Carpooldetails> carPoolList = new ArrayList<Carpooldetails>();

		int parentid = 0;

		//start is a flag for adding from date and to date for parent record and individual dates for 
		//child records
		
		//pdone is a flag to check if parent record is created, so value i can be initialized for 
		//creating n records for n number of days
		
		boolean start = true;
		boolean pdone = false;

		logger.info("from date " + carpooldetails.getFromDate());
		logger.info("to date " + carpooldetails.getToDate());

		int days = CarpooldetailsServiceUtil.getNo_of_days(carpooldetails.getFromDate(), carpooldetails.getToDate());
		days = days + 1;

		logger.info("Number of days " + days);
		for (int i = 0; i < days; i++) {

			// checking if parent record is added to list, if yes then start again to add
			// child records

			if (pdone) {
				i = 0;
				pdone = false;
			}

			Carpooldetails cp = new Carpooldetails();
			int id = CarpooldetailsServiceUtil.getRandomInt();

			// setting original fromdate and todate for parent record and parentid to id in carpool db

			if (start) {
				parentid = id;
				cp.setFromDate(carpooldetails.getFromDate());
				cp.setToDate(carpooldetails.getToDate());
				start = false;
				pdone = true;

			}

			// setting individual fromdate and todate for child records in carpool db

			else {

				cp.setFromDate(CarpooldetailsServiceUtil.getAddedDate(carpooldetails.getFromDate(), i));
				cp.setToDate(CarpooldetailsServiceUtil.getAddedDate(carpooldetails.getFromDate(), i));

			}

			cp.setId(id);
			cp.setParentid(parentid);
			cp.setFromtime(carpooldetails.getFromtime());
			cp.setToTime(carpooldetails.getToTime());
			cp.setCreateddate(carpooldetails.getCreateddate());
			cp.setModifieddate(carpooldetails.getModifieddate());
			cp.setNoofseats(carpooldetails.getNoofseats());
			cp.setStatus(1);
			cp.setEmailId(carpooldetails.getEmailId());
			cp.setVehicleType(carpooldetails.getVehicleType());
			cp.setLocation(carpooldetails.getLocation());
			cp.setLongitude(carpooldetails.getLongitude());
			cp.setLatitude(carpooldetails.getLatitude());
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
	
	/**
	 * Modified By 
	 * Durga Manjari Narni
	 * @param location 
	 * @return List<CustomerCarpooldetailsDto>
	 */
	public List<CustomerCarpooldetailsDto> getCarPoolDetails(String location, String emailId) {
		try {
			List<Carpooldetails> carpoolLists = new ArrayList<>();

			List<CustomerCarpooldetailsDto> customerCarpooldetailsDtoList = new ArrayList<>();

			if (location == null) {
				carpoolLists = (List<Carpooldetails>) carpooldetailsDAO.getAllCarPoolDetails();
			} else {
				if (location.equalsIgnoreCase("null")) {
					return customerCarpooldetailsDtoList;
				} else {
					carpoolLists = (List<Carpooldetails>) carpooldetailsDAO.getCarPoolsByLocation(location);
				}
			}
			

			if (carpoolLists != null) {
				for (Carpooldetails car : carpoolLists) {
					if (car.getId().equals(car.getParentid())) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String StrTodayDate = sdf.format(new Date());

						Date toDate = sdf.parse(car.getToDate());
						Date currentDate = sdf.parse(StrTodayDate);
						if (toDate.before(currentDate) || emailId.equals(car.getEmailId()) ||
								car.getStatus() == 5) {
							continue;
						} else {
							User user = userDAO.findByEmailId(car.getEmailId());
							if (user != null) {
									CustomerCarpooldetailsDto carpooldetailsDto = new CustomerCarpooldetailsDto();
									CarpooldetailsDto carDto = CarpooldetailsServiceUtil.convertDaoToDtoInstance(car);
									carpooldetailsDto.setUserName(user.getUserName());
									carpooldetailsDto.setLocation(car.getLocation());
									carpooldetailsDto.setCarpoolDetails(carDto);
									customerCarpooldetailsDtoList.add(carpooldetailsDto);
							}
						}
					}

				}
			}

			return customerCarpooldetailsDtoList;

		} catch (Exception e) {
			logger.info("Entered into catch block :: Exception occurred");
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @author Harish Kumar Gudivada
	 * 
	 * Param carpoolId
	 * Return CarpooldetailsDto
	 * This method is to get the carpool ride details from CarpoolDetailsDAO based on carpool id and convert the Carpooldetails class CarpooldetailsDto
	 */
	@Override
	public CarpooldetailsDto loadCarpoolDetailsById(int carpoolId) {
		logger.error("Entered into CarpooldetailsServiceImpl :: loadCarpoolDetailsById"); 
		CarpooldetailsDto carpoolDetsDto=null;
		try {
			Carpooldetails carpooldets= carpooldetailsDAO.loadCarpoolDetailsById(carpoolId);
			carpoolDetsDto= CarpooldetailsServiceUtil.convertDaoToDtoInstance(carpooldets);
		}catch (Exception e) {
			logger.error("Exception Occured in Class:CarpooldetailsServiceImpl Method:loadCarpoolDetailsById Message:"+e.getMessage());
		}
		logger.error("Exit from CarpooldetailsServiceImpl :: loadCarpoolDetailsById");
		return carpoolDetsDto;
	}
	
	/*
	 * @author Suresh Valavala
	 * 
	 */
	@Override
	public List<CarpooldetailsDto> loadCarpoolDetailsByEmailId(String emailId) {
		List<CarpooldetailsDto> carpooldetailsDtoList=null;
		logger.info("CarpooldetailsServiceImpl:loadCarpoolDetailsByEmailId");

		try {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			List<Carpooldetails> carpooldetailsList = carpooldetailsDAO.getCarPoolsByEmailAndCurrentDate(emailId,format.format(date));
		    carpooldetailsDtoList= CarpooldetailsServiceUtil.convertDaoTODto(carpooldetailsList);
		}catch (Exception e) {
			logger.error("Exception Occured in Class:CarpooldetailsServiceImpl Method:loadCarpoolDetailsByEmailId Message:"+e.getMessage());
		}
		
		return carpooldetailsDtoList;
	}
	
	
	public List<ParentCarpoolDetailsDto> getCarpoolsByDriver(String email) throws CarpooldetailsServiceException {
		logger.debug("BEGIN:getCarpoolsByDriver in the " + this.getClass().getName());
		List<Carpooldetails> carpoolsList = null;
		List<Integer> parentIdsList = new ArrayList();
		List<ParentCarpoolDetailsDto> parentCarpoolDetailsDtos = new ArrayList<>();
		try {
			parentIdsList = carpooldetailsDAO.getCarPoolParentIds(email);
			Set<Integer> parentIdsSet = new HashSet<>(parentIdsList);
			for (int p : parentIdsSet) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String currentdate = sdf.format(new Date());

				// getting CarpoolDetais By ParentID
				List<Carpooldetails> carpoolsByParentId = carpooldetailsDAO.getCarpoolsByParentId(p);
				for (Carpooldetails carpooldetails : carpoolsByParentId) {
					if (carpooldetails.getId().equals(carpooldetails.getParentid())) {
						Date toDate = sdf.parse(carpooldetails.getToDate());
						Date currentDtae = sdf.parse(currentdate);
						if (carpooldetails.getStatus() == 5 || toDate.before(currentDtae)) {
							break;
						}

						ParentCarpoolDetailsDto parentCarpoolDetailsDto = new ParentCarpoolDetailsDto();
						parentCarpoolDetailsDto.setFromDate(carpooldetails.getFromDate());
						parentCarpoolDetailsDto.setToDate(carpooldetails.getToDate());
						parentCarpoolDetailsDto.setLocation(carpooldetails.getLocation());
						parentCarpoolDetailsDto.setParentid(carpooldetails.getParentid());
						parentCarpoolDetailsDto.setStatus(String.valueOf(carpooldetails.getStatus()));
						Pool_Status pool_Status = Pool_Status.values()[(carpooldetails.getStatus()-1)];
						parentCarpoolDetailsDto.setStatusName(pool_Status.toString());
						parentCarpoolDetailsDtos.add(parentCarpoolDetailsDto);
						break;
					}

				}

			}
			return parentCarpoolDetailsDtos;
		} catch (Exception e) {
			logger.error("ERROR: some thing went wrong getting getCarpoolsByDriver");
			throw new CarpooldetailsServiceException(e.getMessage());
		}

	}

	
	public  List<DriverCarPoolDto> getCarPoolsByParentId(int parentId) throws CarpooldetailsServiceException
	{
		logger.debug("BEGIN: getCarPoolsByParentId in the class" + this.getClass().getName());
		List<DriverCarPoolDto> driverCarPoolDtoList = new ArrayList();
		try {
			List<Carpooldetails> carpools = carpooldetailsDAO.getCarpoolsByParentId(parentId);

			List<CarpoolRiderDetails> ridersList = null; 

			// driverCarPoolDto.setLocation(location);
			for (Carpooldetails carpooldetails : carpools) {
					DriverCarPoolDto driverCarPoolDto = new DriverCarPoolDto();
					ridersList = carpoolRiderDAO.getRidersByCpID(carpooldetails.getId());
					driverCarPoolDto.setFromDate(carpooldetails.getFromDate());
					driverCarPoolDto.setToDate(carpooldetails.getToDate());
					driverCarPoolDto.setLocation(carpooldetails.getLocation());
	                driverCarPoolDto.setTotalSeats(carpooldetails.getNoofseats());
	                driverCarPoolDto.setFilledSeats(getFilledSeatsInPool(ridersList));
	                Pool_Status pool_Status = Pool_Status.values()[(carpooldetails.getStatus()-1)];
					driverCarPoolDto.setStatus(pool_Status.toString());
					if(carpools.size() == 1) {
						driverCarPoolDtoList.add(driverCarPoolDto);
					}else if(!carpooldetails.getId().equals(carpooldetails.getParentid())){
						driverCarPoolDtoList.add(driverCarPoolDto);
					}
					
			}
			logger.debug("END: getCarPoolsByParentId in the class" + this.getClass().getName());
		} catch (Exception ex) {
			logger.error("Some thing went wrong while fetching getCarpoolsByDriver::");
			throw new CarpooldetailsServiceException(ex.getMessage());

		}
		return driverCarPoolDtoList;
	}
	
	
	public	void UpdatecarpoolStatus(Integer carpoolId) throws CarpooldetailsServiceException
	{
		logger.debug("BEGIN: UpdatecarpoolStatus in the class" + this.getClass().getName());
		List<CarpoolRiderDetails> carpoolRidersList = null;
		List<Carpooldetails> carpoolDetailsList = null;
		Carpooldetails carpooldetails = carpooldetailsDAO.getCarpoolByPoolID(carpoolId);
		if (carpooldetails != null) {
			int pid = 0;
			int parentId = 0;

			carpoolRidersList = carpoolRiderDAO.getRidersByCpID(carpoolId);

			int poolStatus = getRidersStatus(carpoolRidersList, carpooldetails);
			// carpooldetails.setStatus(status);
			carpooldetailsDAO.updateCarpoolStatusByPoolId(poolStatus, carpooldetails.getId());
			if (carpooldetails.getId() == carpooldetails.getParentid()) {
				return;
			} else {
				carpoolDetailsList = carpooldetailsDAO.getCarpoolsByParentId(carpooldetails.getParentid());
				for (Carpooldetails carpool : carpoolDetailsList) {

					if (carpool.getId() == carpool.getParentid()) {
						pid = carpool.getId();
						parentId = carpool.getParentid();
						break;
					}

				}
				if (carpoolDetailsList != null && carpoolDetailsList.size() > 0) {
					int status = carPoolStatus(carpoolDetailsList);
					carpooldetailsDAO.upateCarPoolStatusByIdandParentID(pid, status);
				}
			}
		}
		logger.debug("END: getCarPoolsByParentId in the class"+this.getClass().getName());
	}
	
	 
	 private int getRidersStatus(List<CarpoolRiderDetails> ridersList,Carpooldetails carpooldetails)
	{
		if (ridersList != null && ridersList.size() > 0) {
			int requestedCount = 0;
			int approvedCount = 0;
			int rejectedCount = 0;

			for (CarpoolRiderDetails rider : ridersList) {

				switch (rider.getStatus()) {
				case 1:// requested
					++requestedCount;
					break;
				case 2:// approved
					++approvedCount;
					break;
				case 3:// rejected
					++rejectedCount;
				case 4:// canceled
					++rejectedCount;
					break;

				}
			}
			int status = 0;
			if (approvedCount == carpooldetails.getNoofseats()) {
				status = 3;
			} else if ((approvedCount + requestedCount) == carpooldetails.getNoofseats()) {
				status = 3;
			} else if ((approvedCount + requestedCount) > 0
					&& (approvedCount + requestedCount) < carpooldetails.getNoofseats()) {
				status = 2;
			} else {
				status = 1;
			}
			return status;
		}
		return 1;

	}

	 
	 private int carPoolStatus(List<Carpooldetails> driverCarpoolList)
	{

		int openCount = 0;
		int completedCount = 0;
		int partiallyCompletedCount = 0;
		String status = null;

		if (driverCarpoolList.size() == 1) {
			return driverCarpoolList.get(0).getStatus();
		}

		for (Carpooldetails c : driverCarpoolList) {
			if (c.getId().equals(c.getParentid()))

			{

				continue;
			}

			switch (c.getStatus()) {
			case 1:// open
				return 1;

			case 2:// partially Completed
				++partiallyCompletedCount;
				break;
			case 3:// completed
				++completedCount;

			}

		}
		if (completedCount == driverCarpoolList.size() - 1) {
			
			return 3;
		}
		if ((completedCount + partiallyCompletedCount) <= driverCarpoolList.size() - 1) {
			return 2;
		}
		return 0;
	}
	 /**
		 * @author Mahesh Bheemanapalli
		 * addRewards() for service Layer
		 * @param rewards
		 * @return ServiceStatusDto class with status and message
		 */
	@Override
	public ServiceStatusDto addRewards(double rewards) {
			// TODO Auto-generated method stub
			logger.info("BEGIN :: CarpooldetailsServiceImpl : addRewards : To Driver");
			ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
			String updaterewardPointsWithId = carpooldetailsDAO.addRewards(rewards);
			if(ObjectUtils.anyNotNull(updaterewardPointsWithId)) {
				serviceStatusDto.setStatus(true);
				serviceStatusDto.setMessage(updaterewardPointsWithId);
				logger.info("CLOSED :: CarpooldetailsServiceImpl : addRewards : To Driver :Status :"+serviceStatusDto.getMessage());
			}
			return serviceStatusDto;
	}
	
	 /**
		 * @author Harish Kumar Gudivada
		 * @param emailId
		 * @return location
		 */
		public RegisterDTO getDriverLocationByEmailId(String emailId)throws CarpooldetailsServiceException {
			logger.info("Entered into CarpooldetailsServiceImpl Method:getLocationByEmailId");
			String location="";
			RegisterDomain domain=null;
			RegisterDTO regDto=null;
			try {
				List<RegisterDomain>  userList=registerDAO.getLocationOfRegisteredUser(emailId);
				if(userList!=null) {
					for(RegisterDomain registedDao:userList) {
						if(registedDao.getIsrider()==0)
							location=registedDao.getLocation();
						domain=registedDao;
					}
				}
				regDto= RegisterServiceUtil.convertRegisterDomainObjectToRegisterDto(domain);
			}catch (Exception ex) {
				logger.error("Exception Occured in Class:CarpooldetailsServiceImpl Method:getLocationByEmailId Message:"+ex.getMessage());
				throw new CarpooldetailsServiceException(ex.getMessage());
			}
			logger.info("Exit from CarpooldetailsServiceImpl Method:getLocationByEmailId");
		//	return location;
			return regDto;
		}
		
		 /**
		 * @author Chandra Sekhar Gapti
		 * @param emailId,
		 * @param UserType we need to pass the param as d=driver,r=rider,B=Both 
		 * to get the today list
		 * 
		 * @return TodayRiderDetailsDTO
		 */
		
		public List<TodayRiderDetailsDTO> getRidesForDrivers(String email,String userType) throws Exception
		 {
			
			logger.info("Entered into CarpooldetailsServiceImpl Method:getRidesForDrivers");
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String currentdate=sdf.format(new Date());
			 Carpooldetails carpooldetails= carpooldetailsDAO.getCarpoolByDateAndEmail(currentdate,email);
			
			
				 List<TodayRiderDetailsDTO> riderDetails=null;
				 if(carpooldetails!=null)
				 {
					 riderDetails=new ArrayList<>();
					 List<CarpoolRiderDetails> ridersList=	 carpoolRiderDAO.getRidersByCpID(carpooldetails.getId());
					 for(CarpoolRiderDetails carpoolRiderDetails:ridersList)
					 {
						User user= userDAO.findByEmailId(carpoolRiderDetails.getEmailid());
						String mobile=  registerDAO.getMobileNumberByEmail(carpoolRiderDetails.getEmailid());         
						
						TodayRiderDetailsDTO carpoolRiderDetailsDTO=new TodayRiderDetailsDTO();
						 
						 carpoolRiderDetailsDTO.setCpid(carpoolRiderDetails.getCpid());
						 carpoolRiderDetailsDTO.setId(carpoolRiderDetails.getId());
						 carpoolRiderDetailsDTO.setEmailid(carpoolRiderDetails.getEmailid());
						 carpoolRiderDetailsDTO.setStatus(carpoolRiderDetails.getStatus());
						 carpoolRiderDetailsDTO.setRewards(carpoolRiderDetails.getRewards());
						 carpoolRiderDetailsDTO.setReason(carpoolRiderDetails.getReason());
						 carpoolRiderDetailsDTO.setMoblie(mobile);
						 if(user!=null)
						 carpoolRiderDetailsDTO.setName(user.getUserName());
						
						 riderDetails.add(carpoolRiderDetailsDTO);
			 
					 }
				
				  }
				 return	 riderDetails;
				 
				 
			 }
		public DriverCarPoolDto getDriversByRider(String email,String userType) throws Exception
		{
			
			logger.info("Entered into CarpooldetailsServiceImpl Method:getRidesForDrivers");
			List<Carpooldetails> caList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String currentdate = sdf.format(new Date());
			List<Integer> allCarpoolIds = carpooldetailsDAO.getCarpoolByDate(currentdate);
			CarpoolRiderDetails carpoolRiderDetails = carpoolRiderDAO.getRidesByMailandAllCarpoolIds(email, allCarpoolIds);
			DriverCarPoolDto driverCarPoolDto = null;
			if (carpoolRiderDetails != null) {

				Carpooldetails carpooldetails = carpooldetailsDAO.loadCarpoolDetailsById(carpoolRiderDetails.getCpid());
				
				if (carpooldetails != null) {
					driverCarPoolDto=new DriverCarPoolDto();
                     driverCarPoolDto.setParentId(carpooldetails.getParentid());
					driverCarPoolDto.setFromDate(carpooldetails.getFromDate());
					driverCarPoolDto.setLocation(carpooldetails.getLocation());
					driverCarPoolDto.setStatus(String.valueOf(carpooldetails.getStatus()));
					driverCarPoolDto.setToDate(carpooldetails.getToDate());
					driverCarPoolDto.setEmail(carpooldetails.getEmailId());
					User user = userDAO.findByEmailId(carpoolRiderDetails.getEmailid());
					String mobile = registerDAO.getMobileNumberByEmail(carpoolRiderDetails.getEmailid());
					if (user != null)
						driverCarPoolDto.setName(user.getUserName());
					driverCarPoolDto.setMobile(mobile);

				}

			}
			return driverCarPoolDto;
		}
		
		
		public List<OptRideDto> getCarpoolsDataNotOptedOrOptedByMe(int parentId, String emilId, Boolean OptedData)
				throws CarpooldetailsServiceException {
			logger.debug("BEGIN: getCarpoolsDataNotOptedOrOptedByMe in the class" + this.getClass().getName());
			List<OptRideDto> optRideDataList = new ArrayList<OptRideDto>();
			try {
				List<Carpooldetails> carPoolDataList = carpooldetailsDAO.getCarpoolsByParentId(parentId);
				if(carPoolDataList.size() > 1) {
				carPoolDataList = OptARideServiceUtil.removeParentIdFromCarPoolList(carPoolDataList, parentId);
			}
				if (OptedData) {
					optRideDataList = constructListOfOptRides(emilId, carPoolDataList);
				} else {
					optRideDataList = constructListOfNotOptRides(emilId, carPoolDataList);
				}
				logger.debug("END: getCarpoolsDataNotOptedOrOptedByMe in the class" + this.getClass().getName());
			} catch (Exception ex) {
				logger.error("Some thing went wrong while fetching getCarpoolsDataNotOptedOrOptedByMe::");
				throw new CarpooldetailsServiceException(ex.getMessage());
			}
			return optRideDataList;
		}

		private List<OptRideDto> constructListOfNotOptRides(String emailId, List<Carpooldetails> carPoolDataList) {
			List<CarpoolRiderDetails> carPoolRiderDetailsList = new ArrayList<CarpoolRiderDetails>();
			List<OptRideDto> optRideDtoList = new ArrayList<OptRideDto>();
			int optedCount = 0;
			for (Carpooldetails carppol : carPoolDataList) {
				carPoolRiderDetailsList = carpoolRiderDAO.getNotOptedRiderDeatils(carppol.getId());
				if (carPoolRiderDetailsList.size() > 0) {
					for(CarpoolRiderDetails carPoolRiderDetail :carPoolRiderDetailsList)
					{
					Boolean isFind = OptARideServiceUtil.findCpIdRiderEmailId(emailId, carPoolRiderDetail);
					if (!isFind) {
					    optedCount = optedCount + 1;	
					}	
				}	
				}
				else
				{
					Carpooldetails acceptCarPoolDeatilsList = OptARideServiceUtil.acceptCarPoolStatusList(carppol);
					if(acceptCarPoolDeatilsList != null) {
					OptRideDto optRideDto = OptARideServiceUtil.convertToOptRideDtoNotOptedPool(acceptCarPoolDeatilsList, optedCount);
					optRideDtoList.add(optRideDto);	
					}
				}
			}
			return optRideDtoList;
		}

		

		private List<OptRideDto> constructListOfOptRides(String emailId, List<Carpooldetails> carPoolDataList) throws Exception {
			CarpoolRiderDetails carPoolRiderDetails;
			List<OptRideDto> optRideDtoList = new ArrayList<OptRideDto>();
			int optedCount = 0;
			for (Carpooldetails carppol : carPoolDataList) {
				carPoolRiderDetails = carpoolRiderDAO.getOptedRiderDeatils(carppol.getId(), emailId);
				
				if (carPoolRiderDetails != null) {
					optedCount = optedCount+1;
					OptRideDto optRideDto = OptARideServiceUtil.convertToOptRideDtoOptedPool(carppol, optedCount, carPoolRiderDetails);
					optRideDtoList.add(optRideDto);
				}
			}
			return optRideDtoList;
		}

		
		/**
		 * @author Mahesh Bheemanapalli : CPL049: Functionality to update car pool
		 *         status using scheduler
		 * 
		 *         This method is used update the carpool status as "Closed" except
		 *         "Cancelled" where todate is Less than or equal to Current Date.
		 * @return ServiceStatusDto
		 */
	@Override
	public ServiceStatusDto updateCarpoolStatus() {
		// TODO Auto-generated method stub
		logger.info("BEGIN :: CarpooldetailsServiceImpl : updateCarpoolStatus");
		ServiceStatusDto statusDto = new ServiceStatusDto(); 
		try {
			String statusToClosed = carpooldetailsDAO.updateCarpoolStatusToClosed();
			if(ObjectUtils.anyNotNull(statusToClosed)) {
				statusDto.setStatus(true);
				statusDto.setMessage(statusToClosed);
			}
			logger.info("CLOSED :: CarpooldetailsServiceImpl : updateCarpoolStatus : Status : "+statusDto.getMessage());
		}
		catch(Exception e) {
			logger.error("CLOSED :: CarpooldetailsServiceImpl : updateCarpoolStatus"+e.getMessage());
			statusDto.setStatus(false);
			statusDto.setMessage(e.getMessage());
		}
		return statusDto;
	}
		/*
		 * @author: Suresh Valavala
		 * @Param: List<CarpoolRiderDetails>
		 * @return: count of filled seats in a carpool
		 */
		private int getFilledSeatsInPool(List<CarpoolRiderDetails> ridersList) {
			int count = 0;
			
			if (ridersList != null && ridersList.size() > 0) {
				int requestedCount = 0;
				int approvedCount = 0;
				for (CarpoolRiderDetails rider : ridersList) {

					if(rider.getStatus() == 1) {
						++requestedCount;
					}else if(rider.getStatus() == 2) {
						++approvedCount;
					}
				}
				count = requestedCount + approvedCount ;
			}
			
			return count;
		}

		@Override
		public ServiceStatusDto cancelCarpooldetailsByParentId(CarpooldetailsDto carpooldetailsDto) {
			// TODO Auto-generated method stub
			logger.info("CarpooldetailsServiceImpl ***:: : cancel Carpooldetails");
			
			//carpooldetailsDto.setModifieddate(currentDate);
			
			String cancelCarpooldetails=null;
			try {
				
				Carpooldetails carpooldetails = new  Carpooldetails();
				carpooldetails.setParentid(carpooldetailsDto.getParentid());
				cancelCarpooldetails = carpooldetailsDAO.cancelCarpooldetailsByParentId(carpooldetails);
				logger.info("Carpooldetails after cancel  dao message::"+cancelCarpooldetails);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
			if(ObjectUtils.anyNotNull(cancelCarpooldetails))
			{
				logger.info("CarpooldetailsServiceImpl : cancel Carpooldetails set message::"+cancelCarpooldetails);
				serviceStatusDto.setStatus(true);
				serviceStatusDto.setMessage(cancelCarpooldetails);
			}
			
			return serviceStatusDto;

}
}