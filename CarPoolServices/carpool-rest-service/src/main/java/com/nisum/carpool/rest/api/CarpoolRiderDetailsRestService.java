package com.nisum.carpool.rest.api; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.data.util.Constants;
import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.CPCancellationReasons;

import com.nisum.carpool.service.exception.CarpooldetailsServiceException;


@RestController
@RequestMapping(value = "/v1/carpool")
public class CarpoolRiderDetailsRestService {
	
	private static Logger logger = LoggerFactory.getLogger(CarpoolRiderDetailsRestService.class);
	static Map<Integer, String> cancelReasonMapObj = new HashMap<Integer, String>();

	@Autowired
	CarpoolRiderDetailsService carpoolRiderDetailsService;
	
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


	@RequestMapping(value = "/getRiderBookingDetails/{emailId:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> getRiderBookingDetails(@PathVariable("emailId") String emailId) throws CarpooldetailsServiceException 
		 {
		logger.info("CarpoolRiderDetailsRestService::getRiderBookingDetails::start");
		Errors error = new Errors();
		try {
		
	    List<RiderBookingDetailsDTO> poolList = carpoolRiderDetailsService.getRiderBookingDetails(emailId);
	    if(poolList.isEmpty()) {
	      	error.setErrorCode("204");
			error.setErrorMessage(Constants.ERROR_MESSAGE);
			return new ResponseEntity<Errors>(error, HttpStatus.NO_CONTENT);
	    }else
	    

		return new ResponseEntity<List<RiderBookingDetailsDTO>>(poolList, HttpStatus.OK);
		
		}catch(Exception e) {
			throw new CarpooldetailsServiceException("Error-Message");
		}
	}

	@RequestMapping(value = "/findCarpoolRiderDetailsByCPId/{cpid}", method = RequestMethod.GET)
	public ResponseEntity<List<CarpoolRiderDetailsDTO>> findCarpoolRiderDetailsByCPId(@PathVariable("cpid") int cpid) {
		
		List<CarpoolRiderDetailsDTO> poolList = carpoolRiderDetailsService.findCarpoolRiderDetailsByCPId(cpid);
		return new ResponseEntity<List<CarpoolRiderDetailsDTO>>(poolList, HttpStatus.OK);

	}
	
	/*
	 * methodAuthor: @Rajesh Sekhamuri
	 * methodName: riderStatus()
	 * @Params 
	 * return Map<Integer, String> reason code with name form of key and value pair
	 */
	
	@RequestMapping(value = "/loadRiderStatusReasons", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, String>> loadRiderStatusReasons() {
		logger.info("Start of loadRiderStatusReasons() method in RiderStatusRestService"); 
		if(cancelReasonMapObj.isEmpty()) { 
			cancelReasonMapObj = CPCancellationReasons.readRiderStatusReasonCodes();
		}
		logger.info("End of loadRiderStatusReasons() method in RiderStatusRestService");
		return new ResponseEntity<Map<Integer, String>>(cancelReasonMapObj, HttpStatus.OK);
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used for cancelling a ride by rider
	 */

	@RequestMapping(value = "/cancelRiderBookingDetails", method = RequestMethod.POST)
	public ResponseEntity<?> cancelRiderBookingDetails(@RequestBody List<CarpoolRiderDetailsDTO> rides) {
		logger.info("cancelling a ride");
		try {

			List<CarpoolRiderDetailsDTO> cprdto = carpoolRiderDetailsService.cancelRiderBookingdetails(rides);

			if (cprdto == null) {

				logger.info("carpoolriderdetailsrestservice:Canceling ride failed");
				ServiceStatusDto statusDto = new ServiceStatusDto();
				statusDto.setStatus(false);
				statusDto.setMessage(Constants.CANCELING_RIDE_FAILED);
				ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto,
						HttpStatus.BAD_REQUEST);
				return entity;
			}

			else {
				logger.info("carpoolriderdetailsrestservice:Successfully cancelled a ride");
				ResponseEntity<List<CarpoolRiderDetailsDTO>> entity = new ResponseEntity<List<CarpoolRiderDetailsDTO>>(
						cprdto, HttpStatus.OK);
				return entity;

			}

		} catch (Exception e) {

			logger.info(e.getMessage());
			ServiceStatusDto statusDto = new ServiceStatusDto();
			statusDto.setStatus(false);
			statusDto.setMessage(Constants.CANCELING_RIDE_FAILED);
			ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto,
					HttpStatus.BAD_REQUEST);
			return entity;

		}

	}

}
