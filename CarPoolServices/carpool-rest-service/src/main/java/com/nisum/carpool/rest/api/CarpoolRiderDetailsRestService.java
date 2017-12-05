package com.nisum.carpool.rest.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.CPCancellationReasons;
import com.nisum.carpool.util.Constants;


@RestController
@RequestMapping(value = "/v1/carpool")
public class CarpoolRiderDetailsRestService {

	@Autowired
	CarpoolRiderDetailsService carpoolRiderDetailsService;

	
	private static Logger loggerObj = Logger.getLogger(CarpoolRiderDetailsRestService.class);
	static Map<Integer, String> cancelReasonMapObj = new HashMap<Integer, String>();
	
	@RequestMapping(value = "/getRiderBookingDetails/{emailID}", method = RequestMethod.GET)
	public ResponseEntity<List<RiderBookingDetailsDTO>> getRiderBookingDetails(
			@PathVariable("emailID") String emailID) {

		String emailId = emailID + ".com";

		System.out.println("emailId==" + emailId);

		List<RiderBookingDetailsDTO> poolList = carpoolRiderDetailsService.getRiderBookingDetails(emailId);
		return new ResponseEntity<List<RiderBookingDetailsDTO>>(poolList, HttpStatus.OK);

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
		loggerObj.info("Start of loadRiderStatusReasons() method in RiderStatusRestService"); 
		if(cancelReasonMapObj.isEmpty()) { 
			cancelReasonMapObj = CPCancellationReasons.readRiderStatusReasonCodes();
		}
		loggerObj.info("End of loadRiderStatusReasons() method in RiderStatusRestService");
		return new ResponseEntity<Map<Integer, String>>(cancelReasonMapObj, HttpStatus.OK);
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used for cancelling a ride by rider
	 */

	@RequestMapping(value = "/cancelRiderBookingDetails", method = RequestMethod.POST)
	public ResponseEntity<?> cancelRiderBookingDetails(@RequestBody List<CarpoolRiderDetailsDTO> rides) {
		loggerObj.info("cancelling a ride");
		try {

			List<CarpoolRiderDetailsDTO> cprdto = carpoolRiderDetailsService.cancelRiderBookingdetails(rides);

			if (cprdto == null) {

				loggerObj.info("carpoolriderdetailsrestservice:Canceling ride failed");
				ServiceStatusDto statusDto = new ServiceStatusDto();
				statusDto.setStatus(false);
				statusDto.setMessage(Constants.CANCELING_RIDE_FAILED);
				ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto,
						HttpStatus.BAD_REQUEST);
				return entity;
			}

			else {
				loggerObj.info("carpoolriderdetailsrestservice:Successfully cancelled a ride");
				ResponseEntity<List<CarpoolRiderDetailsDTO>> entity = new ResponseEntity<List<CarpoolRiderDetailsDTO>>(
						cprdto, HttpStatus.OK);
				return entity;

			}

		} catch (Exception e) {

			loggerObj.info(e.getMessage());
			ServiceStatusDto statusDto = new ServiceStatusDto();
			statusDto.setStatus(false);
			statusDto.setMessage(Constants.CANCELING_RIDE_FAILED);
			ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto,
					HttpStatus.BAD_REQUEST);
			return entity;

		}

	}

}
