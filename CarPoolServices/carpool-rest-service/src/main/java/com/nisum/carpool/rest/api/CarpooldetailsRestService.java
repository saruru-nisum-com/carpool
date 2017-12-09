package com.nisum.carpool.rest.api;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.api.CarpooldetailsService;
import com.nisum.carpool.service.api.CommonServices;
import com.nisum.carpool.service.api.RewardPoints;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.CustomerCarpooldetailsDto;
import com.nisum.carpool.service.dto.DriverCarPoolDto;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.OptRideDto;
import com.nisum.carpool.service.dto.ParentCarpoolDetailsDto;
import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.dto.TodayRiderDetailsDTO;
import com.nisum.carpool.service.exception.CarpooldetailsServiceException;
import com.nisum.carpool.util.Constants;

@RestController
@RequestMapping(value = "/v1/carpool")
public class CarpooldetailsRestService {

	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsRestService.class);
	@Autowired
	CarpooldetailsService carpooldetailsService;
	private static RewardPoints rewardPoints;
	@Autowired
	CarpoolRiderDetailsService carpoolRiderService;

	@Autowired
	CommonServices commonService;
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCarpooldetails(@RequestBody CarpooldetailsDto carpooldetailsDto) {
		logger.info("CarpooldetailsRestService :: updateCarpooldetails");
		ResponseEntity<?> responseEntity = null;
		try {
			ServiceStatusDto statusDto = carpooldetailsService.updateCarpooldetails(carpooldetailsDto);
				responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("500");
			error.setErrorMessage(e.getMessage());
			responseEntity = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;

	}
	
	@RequestMapping(value="/cancel",method=RequestMethod.PUT)
	public ResponseEntity<?> cancelCarpooldetails(@RequestBody CarpooldetailsDto carpooldetailsDto){
		logger.info("Enter CarpooldetailsRestService :: cancel Carpooldetails");
		logger.info("in cancel pool Id="+carpooldetailsDto.getId()+"parentId=="+carpooldetailsDto.getParentid()+"total seats="+carpooldetailsDto.getTotalNoOfSeats());
		ResponseEntity<?> responseEntity = null;
		try {
			ServiceStatusDto statusDto = carpooldetailsService.cancelCarpooldetails(carpooldetailsDto);
			if (statusDto.isStatus()) {
				responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
			}
		} catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("BAD REQUEST");
			error.setErrorMessage(Constants.MSG_CANCEL_CARPOOL_FAILED);
			responseEntity = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}

		// update in Carpool rider
		try {
			String cancelRider = carpoolRiderService.cancelCarpoolRiderDetails(carpooldetailsDto.getId());
			logger.info("msg for Carpoll rider cancel" + cancelRider);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseEntity;

	}

	@RequestMapping(value="/cancelByParentId",method=RequestMethod.PUT)
	public ResponseEntity<?> cancelCarpooldetailsByParentId(@RequestBody CarpooldetailsDto carpooldetailsDto){
		logger.info("Enter CarpooldetailsRestService :::: cancel Carpooldetails");
		logger.info("parentId=="+carpooldetailsDto.getParentid());
		ResponseEntity<?> responseEntity = null;
		try {
			ServiceStatusDto statusDto = carpooldetailsService.cancelCarpooldetailsByParentId(carpooldetailsDto);
			if (statusDto.isStatus()) {
				responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
			}
		} catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("BAD REQUEST");
			error.setErrorMessage(Constants.MSG_CANCEL_CARPOOL_FAILED);
			responseEntity = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}

		// update in Carpool rider
		try {
			String cancelRider = carpoolRiderService.cancelCarpoolRiderDetails(carpooldetailsDto.getParentid());
			logger.info("msg for Carpoll rider cancel" + cancelRider);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseEntity;

	}
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used to for creating car pool
	 *         @param carpooldetailsDto
	 *         @return ResponseEntity
	 */

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createCarPool(@RequestBody CarpooldetailsDto carpooldetailsDto) {

		logger.info("CarPoolRestService :: createCarPool :: Creating Car Pool");

		return carpooldetailsService.createCarPooldetails(carpooldetailsDto);
}
	/**
	 * Modified By 
	 * Durga Manjari narni
	 * @param location
	 * @return List<CustomerCarpooldetailsDto
	 */
	
	@RequestMapping(value = "/getCarPoolDetails", method = RequestMethod.GET)
	public ResponseEntity<?> getCarPoolDetails(@RequestParam(required = false, value = "location") String location,
			@RequestParam(required = true, value = "emailId") String emailId)
			{
		
		List<CustomerCarpooldetailsDto> poolList=null;
		try {
			poolList=carpooldetailsService.getCarPoolDetails(location, emailId);
			return new ResponseEntity<List<CustomerCarpooldetailsDto>>(poolList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * @author Harish Kumar Gudivada
	 * @param id
	 * @return ResponseEntity
	 */
	
	@RequestMapping(value="/getCarpoolPoolData/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getCarpoolDetailsById(@PathVariable int id){
		CarpooldetailsDto carpoolDto=null;
		logger.info("Entered into CarpooldetailsRestService :: getCarpoolDetailsById");
		try {
			carpoolDto=carpooldetailsService.loadCarpoolDetailsById(id);
			if(carpoolDto!=null && carpoolDto.getId()==0) {
				return new ResponseEntity<String>("CarpoolDetails Is Not Available", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error("Exception Occured in Class:CarpooldetailsRestService Method:getCarpoolDetailsById Message:"
					+ e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		logger.info("Exit from CarpooldetailsRestService :: getCarpoolDetailsById");
		return new ResponseEntity<CarpooldetailsDto>(carpoolDto, HttpStatus.OK);
	}
	
	/**
	 * @author suresh valavala
	 * @param emailId
	 * @return list of carpools
	 */
	
	@RequestMapping(value="/getLoggedInUserCarpoolDetails/{emailId:.+}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getCarpoolDetailsByEmailId(@PathVariable String emailId){
		List<CarpooldetailsDto> carpoolDtoList=null;
		try {
			carpoolDtoList = carpooldetailsService.loadCarpoolDetailsByEmailId(emailId);
			if(CollectionUtils.isEmpty(carpoolDtoList)) {
				return new ResponseEntity<String>("Data Is Not Available", HttpStatus.NO_CONTENT);
			}
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("500");
			error.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(error, HttpStatus.BAD_REQUEST);
		}
		 ResponseEntity<List<CarpooldetailsDto>> entity = new ResponseEntity<List<CarpooldetailsDto>>(carpoolDtoList, HttpStatus.OK);
		 return entity;
	}
	
	/**
	 * @author Sure Harish
	 * @param the Email
	 * this method will return all the carpool's shared by driver  .
	 */

	@RequestMapping(value = "/getMySharedRides/{email:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCarpoolsByDriver(@PathVariable("email") String email) {
		logger.info("BEGIN: getAllCarpoolsByDriver() in the CarpooldetailsRestService");
		try {
			return new ResponseEntity<List<ParentCarpoolDetailsDto>>(carpooldetailsService.getCarpoolsByDriver(email),
					HttpStatus.OK);
		} catch (CarpooldetailsServiceException ex) {
			logger.error("ERROR:some thing went wrong while fetching getAllCarpoolsByDriver");
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	/**
	 * @author Sure Harish
	 * @param carpool Parent ID
	 *  this method will return all the carpool's in the given parent ID
	 */

	@RequestMapping(value = "/getSharedRidesByParentId/{parentID}", method = RequestMethod.GET)
	public ResponseEntity<?> getCarpoolsByParentID(@PathVariable("parentID") Integer id) {
		logger.info("BEGIN: getAllParentCpsByDrievrID() in the CarpooldetailsRestService");
		try {
			return new ResponseEntity<List<DriverCarPoolDto>>(carpooldetailsService.getCarPoolsByParentId(id),
					HttpStatus.OK);
		} catch (CarpooldetailsServiceException ex) {
			logger.error("ERROR:some thing went wrong while fetching getAllCarpoolsByDriver");
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	/** @author Sure Harish
	 * @param the carpoolID
	 * this method updates the status of carool and parentCarpool.
	 */
	
	@RequestMapping(value = "/updateCarpoolStatus/{carpoolId}", method = RequestMethod.GET)
	public ResponseEntity<?> UpdatecarpoolStatutsByPoolID(@PathVariable("carpoolId")Integer carpoolId) 
	{
		logger.info("BEGIN: getAllParentCpsByDrievrID() in the CarpooldetailsRestService");
		try {
			carpooldetailsService.UpdatecarpoolStatus(carpoolId);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (CarpooldetailsServiceException ex) {
			logger.error("ERROR:some thing went wrong while fetching getAllCarpoolsByDriver");
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	
	/**
	 * @author Harish Kumar Gudivada
	 * @param emailId
	 * @return
	 */
	@RequestMapping(value = "/getUserLocation/{emailId:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserLocationByEmailId(@PathVariable("emailId")String emailId) {
		logger.info("Entered into Class: CarpooldetailsRestService Method: getUserLocationByEmailId");
		
		if(!commonService.checkValidUserEmailId(emailId)) {
			return new ResponseEntity<String>("Not a valid mailid", HttpStatus.BAD_REQUEST);
		}
		try {
			RegisterDTO regDto=carpooldetailsService.getDriverLocationByEmailId(emailId);
			return new ResponseEntity<RegisterDTO>(regDto,HttpStatus.OK);
		} catch (CarpooldetailsServiceException ex) {
			logger.error("Exception Occured in Class:CarpooldetailsRestService Method:getUserLocationByEmailId Message:"+ex.getMessage());
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * @author Mahesh Bheemanapalli
	 */
		

	//seconds minutes hours dayofthemonth month dayoftheweek
	@Scheduled(cron = "0 45 23 * * ?")
	@RequestMapping(value = "/addDriverRewardPoints", method = RequestMethod.GET)
	public ResponseEntity<?> addRewardsToDriver() {
		logger.info("CarpooldetailsRestService : addRewardsToDriver");
		try {
			String driverRewardPoints = rewardPoints.getDriverRewardPoints();
			double rewards = Double.parseDouble(driverRewardPoints);
			ServiceStatusDto statusDto = carpooldetailsService.addRewards(rewards);
			return new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("CarpooldetailsRestService : addRewardPointsToDriver : Inside Catch Block" +e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	/**
	 * @author Mahesh Bheemanapalli
	 * @param rewardPoints
	 * @return 
	 */
	
	@Autowired
	public void setEmailAccount(RewardPoints rewardPoints) {
		CarpooldetailsRestService.rewardPoints = rewardPoints;
	}
	
	/**
	 * @author chandra sekhar gapti
	 * @param emailid,UserType:we need to pass type as d=driver,r=rider or b=both for today drive
	 * get Today Rider details.
	 * @return TodayRiderDetailsDTO,DriverCarPoolDto
	 */
	
	

	@RequestMapping(value = "/getMySharedRides/{email:.+}/{userType}", method = RequestMethod.GET)
	public ResponseEntity<?> getTodayRides(@PathVariable("email")String email,@PathVariable("userType")String userType) 
	{
		logger.info("BEGIN: getTodayRides() in the CarpooldetailsRestService");
		
			try {
				if("D".equalsIgnoreCase(userType) || "B".equalsIgnoreCase(userType))
				{
					
				List<TodayRiderDetailsDTO> ridersList =	carpooldetailsService.getRidesForDrivers(email, userType);
			if(ridersList!=null && ridersList.size()>0 )
			{
				DriverCarPoolDto driverCarPoolDto=carpooldetailsService.getDriversByRider(email, userType);
				driverCarPoolDto.setTodayRiderDetailsDTOs(ridersList);
				return new ResponseEntity<DriverCarPoolDto>(driverCarPoolDto,HttpStatus.OK);
			}
				
				}
				
				if("r".equalsIgnoreCase(userType) || "B".equalsIgnoreCase(userType))
				{
					DriverCarPoolDto driverCarPoolDto=carpooldetailsService.getDriversByRider(email, userType);
					if(driverCarPoolDto!=null)
					{
						List<TodayRiderDetailsDTO> riders=	carpooldetailsService.getRidesForDrivers(driverCarPoolDto.getEmail(), userType);
						driverCarPoolDto.setTodayRiderDetailsDTOs(riders);
					return new ResponseEntity<DriverCarPoolDto>(driverCarPoolDto,HttpStatus.OK);
				
				}
				
				}
				else
				{
					return new ResponseEntity<String>("No rides found.",HttpStatus.OK);
				}
			} catch (Exception ex) {
				logger.error("Exception Occured in Class:CarpooldetailsRestService getTodayRides Message:"+ex.getMessage());
				ex.printStackTrace();
			}
			return null;
	}
	


	
	
	@RequestMapping(value = "/getCarpoolsDataNotOptedOrOptedByMe/{parentID}", method = RequestMethod.GET)
	public ResponseEntity<?> getCarpoolsDataNotOptedOrOptedByMe(@PathVariable("parentID") Integer id,
			@RequestParam(required = false, value = "emailId") String emailId,
			@RequestParam(required = false, value = "optedData") Boolean optedData) {
		logger.info("BEGIN: getCarpoolsDataNotOptedOrOptedByMe() in the CarpooldetailsRestService");
		try {
			return new ResponseEntity<List<OptRideDto>>(
					carpooldetailsService.getCarpoolsDataNotOptedOrOptedByMe(id, emailId, optedData), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("ERROR:some thing went wrong while fetching getCarpoolsDataNotOptedOrOptedByMe");
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	
	/**
	 * @author Mahesh Bheemanapalli
	 * @param Change Status to "Close", if status other than "Cancel" & "Close"
	 * @return 
	 */
	@Scheduled(cron = "0 0 23 * * ?")
	@RequestMapping(value = "/updateCarpoolStatusToClosed", method = RequestMethod.GET)
	public ResponseEntity<?> updateCarpoolStatus() {
		logger.info("CarpooldetailsRestService : updateCarpoolStatus");
		try {
			ServiceStatusDto statusDto = carpooldetailsService.updateCarpoolStatus();
			logger.info("CarpooldetailsRestService : updateCarpoolStatus : "+statusDto.getMessage());
			return new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("CarpooldetailsRestService : updateCarpoolStatus : Inside Catch Block" +e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}
