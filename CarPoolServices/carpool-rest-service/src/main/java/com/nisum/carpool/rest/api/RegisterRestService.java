package com.nisum.carpool.rest.api;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.service.api.RegisterService;

import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.exception.RegisterServiceException;
import com.nisum.carpool.util.CommonsUtil;

@RestController
@RequestMapping(value = "/v1/carpool/")
public class RegisterRestService {

	
	private static Logger logger = LoggerFactory.getLogger(RegisterRestService.class);
	
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping(value = "/registerdriver", method = RequestMethod.POST,  consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> registerDriver(@RequestBody RegisterDTO registerDTO ) throws RegisterServiceException {
		
		logger.debug("Entering into " + getClass() + " registerDriver():::");
		ResponseEntity<?> responseEntity = null;
		try {
			// Duplicate Check need to add it on later point of time 
			
			// Hard Coded Data
			List<Integer> list = new ArrayList<>();
			list.add(4);
			
//			registerDTO.setRegistrationId(76763);
//			registerDTO.setIsRider(0);
//			registerDTO.setLatitude("23322323");
//			registerDTO.setLocation("dsdnmn");
//			registerDTO.setMobile("8943434434");
//			registerDTO.setUserId("test@tes.com");
//			registerDTO.setVehicleType(list);
//			registerDTO.setNearby("NELO");
//			registerDTO.setCreatedDate(CommonsUtil.getCurrentDateTime());
//			registerDTO.setModifiedDate(CommonsUtil.getCurrentDateTime());
//			registerDTO.setEmailNotification(false);
			ServiceStatusDto statusDto = registerService.registerDriverorRider(registerDTO);
			if(statusDto.isStatus()) {
				responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
			}
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("Errors-DriverRegister");
			error.setErrorMessage("Something went wrong while Driver Register");
			responseEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
		
		
		
	}
	
	@RequestMapping(value = "/registerrider", method = RequestMethod.POST,  consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> registerRider(@RequestBody RegisterDTO registerDTO ) throws RegisterServiceException {
		
		logger.debug("Entering into " + getClass() + " registerDriver():::");
		ResponseEntity<?> responseEntity = null;
		try {
			// Duplicate Check need to add it on later point of time 
			
			// Hard Coded Data
//			List<Integer> list = new ArrayList<>();
//			list.add(4);
//			
//			registerDTO.setRegistrationId(76763);
//			registerDTO.setIsRider(0);
//			registerDTO.setLatitude("23322323");
//			registerDTO.setLocation("dsdnmn");
//			registerDTO.setMobile("8943434434");
//			registerDTO.setUserId("test@tes.com");
//			registerDTO.setVehicleType(list);
//			registerDTO.setNearby("NELO");
//			registerDTO.setCreatedDate(CommonsUtil.getCurrentDateTime());
//			registerDTO.setModifiedDate(CommonsUtil.getCurrentDateTime());
//			registerDTO.setEmailNotification(false);
			ServiceStatusDto statusDto = registerService.registerDriverorRider(registerDTO);
			if(statusDto.isStatus()) {
				responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
			}
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("Errors-RiderRegister");
			error.setErrorMessage("Something went wrong while Driver Register");
			responseEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
		
		
	}
	
}
