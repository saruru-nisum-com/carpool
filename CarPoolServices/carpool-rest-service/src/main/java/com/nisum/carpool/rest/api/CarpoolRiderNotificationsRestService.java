package com.nisum.carpool.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.data.domain.CarpoolRiderNotifications;
import com.nisum.carpool.data.util.Constants;
import com.nisum.carpool.service.api.CarpoolRiderNotificationsService;
import com.nisum.carpool.service.dto.CarpoolRiderNotificationsDTO;
import com.nisum.carpool.service.dto.Errors;
@RestController
@RequestMapping(value = "/v1/carpool")
public class CarpoolRiderNotificationsRestService {
	
	private static Logger logger = LoggerFactory.getLogger(CarpoolRiderDetailsRestService.class);
	
	@Autowired
	CarpoolRiderNotificationsService carpoolRiderNotificationsService;

	@RequestMapping(value = "/notified", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveNotifiedRider(
			@RequestBody    List<CarpoolRiderNotificationsDTO > carpoolRiderNotificationsDTO) {
		logger.info("CarpoolRiderNotificationsRestService :: saveNotifiedRider");

		List<CarpoolRiderNotifications> saveNotifiedRiderDetails = null;
		ResponseEntity<?> responseEntity = null;
		
		
		try {
			logger.info("Succesfully Notifed Rider");
			saveNotifiedRiderDetails=carpoolRiderNotificationsService.saveRiderNotifications(carpoolRiderNotificationsDTO);
			responseEntity=	 new ResponseEntity<List<CarpoolRiderNotifications>>(saveNotifiedRiderDetails, HttpStatus.OK);
		
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode(e.getMessage());
			error.setErrorMessage(Constants.CARPOOL_RIDER_NOTIFICATIONS_NOT_SAVED);
			responseEntity=new ResponseEntity<Errors>(error, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
		

	}
}
