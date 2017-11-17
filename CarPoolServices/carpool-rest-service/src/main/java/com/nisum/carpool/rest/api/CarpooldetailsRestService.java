package com.nisum.carpool.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.service.api.CarpooldetailsService;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.Constants;



@RestController
@RequestMapping(value="/v1/carpool")
public class CarpooldetailsRestService {
	

	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsRestService.class);
	@Autowired
	CarpooldetailsService carpooldetailsService;
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public ResponseEntity<?> updateCarpooldetails(@RequestBody CarpooldetailsDto carpooldetailsDto){
		logger.info("CarpooldetailsRestService :: updateCarpooldetails");
		ResponseEntity<?> responseEntity = null;
		try {
			ServiceStatusDto statusDto = carpooldetailsService.updateCarpooldetails(carpooldetailsDto);
			if(statusDto.isStatus()) {
				responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
		}
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("Errors-UserRole");
			error.setErrorMessage(Constants.CARPOOLDETAILS_EXISTS);
			responseEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
		
	}
	
}
