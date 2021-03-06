package com.nisum.carpool.rest.api; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.data.util.Constants;
import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.api.RewardPoints;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.CarpoolRiderOptedDetailsDto;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.ReasonsDTO;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.dto.RiderStatusDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.exception.CarpooldetailsServiceException;
import com.nisum.carpool.util.CPCancellationReasons;



@RestController
@RequestMapping(value = "/v1/carpool")
public class CarpoolRiderDetailsRestService {
	
	private static Logger logger = LoggerFactory.getLogger(CarpoolRiderDetailsRestService.class);
	
	@Autowired
	CarpoolRiderDetailsService carpoolRiderDetailsService;
	
	@Autowired
	private RewardPoints rewardPoints;
	
	static Map<Integer, String> cancelReasonMapObj = new HashMap<Integer, String>();
	
	static List<ReasonsDTO> reasonsdtolist = new ArrayList<ReasonsDTO>();
	
	/**
	 * author Radhika pujari
	 */
	
	/**
	 * getRiderBookingdetails() for  RestService 
	 * Parameter: emailID
	 * This method is used to load the future opt a ride details based on emailId for rider see the future rides 
	 * returntype:Rsponse entity
	 * @throws CarpooldetailsServiceException 
	 */

	// private static Logger logger =
	// LoggerFactory.getLogger(CarpoolRiderDetailsRestService.class);

	
	

	@RequestMapping(value = "/getRiderBookingDetails/{emailId:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> getRiderBookingDetails(@PathVariable("emailId") String emailId) throws CarpooldetailsServiceException 
		 {
		logger.info("CarpoolRiderDetailsRestService::getRiderBookingDetails::start");
		Errors error = new Errors();
		try {
			logger.info("CarpoolRiderDetailsRestService::getRiderBookingDetails::list is empty");
	    List<RiderBookingDetailsDTO> poolList = carpoolRiderDetailsService.getRiderBookingDetails(emailId);
	    if(poolList.isEmpty()) {
	      	error.setErrorCode("204");
			error.setErrorMessage(Constants.ERROR_MESSAGE);
			return new ResponseEntity<Errors>(error, HttpStatus.NO_CONTENT);
	    }else
	    
	    	logger.info("CarpoolRiderDetailsRestService::getRiderBookingDetails::sucess Response");
		return new ResponseEntity<List<RiderBookingDetailsDTO>>(poolList, HttpStatus.OK);
		
		}catch(Exception e) {
			logger.error("CarpoolRiderDetailsRestService::getRiderBookingDetails::sucess Response"+e.getMessage());
			throw new CarpooldetailsServiceException("Error-Message");
		}
	}

	@RequestMapping(value = "/findCarpoolRiderDetailsByCPId/{cpid}", method = RequestMethod.GET)
	public ResponseEntity<List<CarpoolRiderDetailsDTO>> findCarpoolRiderDetailsByCPId(@PathVariable("cpid") int cpid) {

		List<CarpoolRiderDetailsDTO> poolList = carpoolRiderDetailsService.findCarpoolRiderDetailsByCPId(cpid);
		return new ResponseEntity<List<CarpoolRiderDetailsDTO>>(poolList, HttpStatus.OK);

	}

	/*
	 * methodAuthor: @Rajesh Sekhamuri methodName: riderStatus()
	 * 
	 * @Params return Map<Integer, String> reason code with name form of key and
	 * value pair
	 */

	@RequestMapping(value = "/loadRiderStatusReasons", method = RequestMethod.GET)
	public ResponseEntity<List<ReasonsDTO>> loadRiderStatusReasons() {
		logger.info("Start of loadRiderStatusReasons() method in RiderStatusRestService");
		if (reasonsdtolist.isEmpty()) {
			reasonsdtolist = CPCancellationReasons.readRiderStatusReasonCodes();
			System.out.println("res status "+reasonsdtolist);
		}
		System.out.println("ABC ******** "+cancelReasonMapObj.size());
		logger.info("End of loadRiderStatusReasons() method in RiderStatusRestService");
		return new ResponseEntity<List<ReasonsDTO>>(reasonsdtolist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateRiderStatus", method = RequestMethod.POST) 
	public ResponseEntity<?> updateRiderStatus(@RequestBody List<RiderStatusDTO> updateRiderStatus) {
		logger.info("Start of updateRiderStatus() method in RiderStatusRestService");
		try {
			if(updateRiderStatus == null || updateRiderStatus.isEmpty()) {
				ServiceStatusDto statusDto = new ServiceStatusDto();
				statusDto.setStatus(false);
				statusDto.setMessage(Constants.CP_MSG_APPROVE_REJECT_UPDATE_FAILURE);
				ResponseEntity<ServiceStatusDto> updateRiderStatusEntityObj = new ResponseEntity<ServiceStatusDto>(statusDto,
						HttpStatus.BAD_REQUEST);
				logger.info("End of updateRiderStatus() method in RiderStatusRestService");
				return updateRiderStatusEntityObj;
			} else {
				carpoolRiderDetailsService.updateRiderStatus(updateRiderStatus);
				ServiceStatusDto statusDto = new ServiceStatusDto();
				statusDto.setStatus(true);
				statusDto.setMessage(Constants.CP_MSG_APPROVE_REJECT_UPDATE_SUCCESS);
				ResponseEntity<ServiceStatusDto> updateRiderStatusEntityObj = new ResponseEntity<ServiceStatusDto>(statusDto,
						HttpStatus.OK);
				logger.info("End of updateRiderStatus() method in RiderStatusRestService");
				return updateRiderStatusEntityObj;
			}
			
		} catch(Exception exceptionObj) {
			ServiceStatusDto statusDto = new ServiceStatusDto();
			statusDto.setStatus(false);
			statusDto.setMessage(Constants.CP_MSG_APPROVE_REJECT_UPDATE_FAILURE);
			ResponseEntity<ServiceStatusDto> updateRiderStatusEntityObj = new ResponseEntity<ServiceStatusDto>(statusDto,
					HttpStatus.BAD_REQUEST);
			logger.info("End of updateRiderStatus() method in RiderStatusRestService");
			return updateRiderStatusEntityObj;
		}
		
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used for cancelling a ride by rider
	 */

	@RequestMapping(value = "/cancelRiderBookingDetails", method = RequestMethod.POST)
	public ResponseEntity<?> cancelRiderBookingDetails(@RequestBody List<RiderBookingDetailsDTO> rides) {
		logger.info("cancelling a ride");
		
		
		return carpoolRiderDetailsService.cancelRiderBookingdetails(rides);

	

	}
	/**
	 * @author Mahesh Bheemanapalli
	 */
	@Scheduled(cron = "${schedular.job.cron.addrewardtorider}")
	@RequestMapping(value = "/addRiderRewardPoints", method = RequestMethod.GET)
	public ResponseEntity<?> addRewardPointsToRider() {
		logger.info("BEGIN :: CarpoolRiderDetailsRestService : addRewardPointsToRider");
		ResponseEntity<?> responseEntity = null;
		try {
			String riderRewardPoints = rewardPoints.getRiderRewardPoints();
			double rewards = Double.parseDouble(riderRewardPoints);
			ServiceStatusDto statusDto = carpoolRiderDetailsService.addRewards(rewards);
			responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
			logger.info("CLOSED :: CarpoolRiderDetailsRestService : addRewardPointsToRider : status : "+statusDto.getMessage());

		} catch (Exception e) {
			logger.error("CarpoolRiderDetailsRestService : addRewardPointsToRider : Inside Catch Block"+e.getMessage());
			Errors error = new Errors();
			error.setErrorCode("500");
			error.setErrorMessage(e.getMessage());
			responseEntity = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;

	}
	
	@RequestMapping(value = "/parent/{parentid}", method = RequestMethod.GET)
	public Map<String, List<CarpoolRiderOptedDetailsDto>> findCarpoolRiderDetailsByParentId(@PathVariable("parentid") int parentid) {
		
		return carpoolRiderDetailsService.findCarpoolRiderDetailsByParentId(parentid);

	}
	/**
	 * @author Mahesh Bheemanapalli
	 */
	@Scheduled(cron = "${schedular.job.cron.cleancarpoolridernotifications}")
	@RequestMapping(value = "/cleanCarpoolRiderNotifications", method = RequestMethod.GET)
	public ResponseEntity<?> cleanCarpoolRiderNotifications() {
		logger.info("BEGIN :: CarpoolRiderDetailsRestService : cleanCarpoolRiderNotifications");
		ResponseEntity<?> responseEntity = null;
		try {
			ServiceStatusDto statusDto = carpoolRiderDetailsService.cleanCarpoolRiderNotifications();
			responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
			logger.info("CLOSED :: CarpoolRiderDetailsRestService : cleanCarpoolRiderNotifications : "+statusDto.getMessage());
		} catch (Exception e) {
			logger.error("CarpoolRiderDetailsRestService : addRewardPointsToRider : Inside Catch Block"+e.getMessage());
			Errors error = new Errors();
			error.setErrorCode("500");
			error.setErrorMessage(e.getMessage());
			responseEntity = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
	}
	
	/**
	 * @author Bala Kota
	 */
	@RequestMapping(value = "/optedRider", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveOptedRider(
			@RequestBody @Valid   List<CarpoolRiderDetailsDTO> carpoolRiderDetailsDTO,BindingResult bindingResult) {
		logger.info("CarpoolRiderDetailsRestService :: saveOptedRider");

		List<CarpoolRiderDetails> saveOptedRiderDetails = null;
		ResponseEntity<?> responseEntity = null;
		
		
		try {
			logger.info("Succesfully Opted Rider");
			saveOptedRiderDetails=	 carpoolRiderDetailsService.saveOptedRiderDetails(carpoolRiderDetailsDTO);
			responseEntity=	 new ResponseEntity<List<CarpoolRiderDetails>>(saveOptedRiderDetails, HttpStatus.OK);
		
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode(e.getMessage());
			error.setErrorMessage(Constants.CARPOOL_OPTED_RIDER_NOT_SAVED);
			responseEntity=new ResponseEntity<Errors>(error, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
		

	}
	/**
	 * @author Simhadri Naidu Guntreddi
	 * 
	 *         This method is used for get My Rides Details History 
	 */

	@RequestMapping(value = "/myrider/history/{email:.+}", method = RequestMethod.POST)
	public ResponseEntity<?> getRiderBookingDetailsHistory(@PathVariable("email") String email,@RequestBody RiderBookingDetailsDTO historyDTO) throws Exception {
		logger.info("My Rides History");
		try {
		 List<RiderBookingDetailsDTO>  myRidesHistoryList = carpoolRiderDetailsService.getRiderBookingHistory(email, historyDTO);
		 if(myRidesHistoryList==null ||myRidesHistoryList.size()==0 )
		 {
				logger.info("carpoolriderdetailsrestservice: please enter details");

			 return new ResponseEntity<List<RiderBookingDetailsDTO>>(myRidesHistoryList, HttpStatus.NO_CONTENT);
		 }
			logger.info("carpoolriderdetailsrestservice:My Rides History Details");

		return new ResponseEntity<List<RiderBookingDetailsDTO>>(myRidesHistoryList, HttpStatus.OK);

		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ResponseEntity<List<RiderBookingDetailsDTO>>(HttpStatus.BAD_REQUEST);

		}
	}
	
}
