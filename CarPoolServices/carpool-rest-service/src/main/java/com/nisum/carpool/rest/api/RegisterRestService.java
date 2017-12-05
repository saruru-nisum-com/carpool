package com.nisum.carpool.rest.api;


import java.util.List;

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

import com.nisum.carpool.service.api.RegisterService;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.exception.RegisterServiceException;
import com.nisum.carpool.service.exception.UserServiceException;

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
	
	/*
	 * @Author: Dhiraj Singh
	 * Description: update driver/rider functionality
	 */
	@RequestMapping(value="/updateDriver", method = RequestMethod.PUT,  consumes = "application/json", produces="application/json")
	public ResponseEntity<RegisterDTO> updateDriver(@RequestBody RegisterDTO registerDTO) throws RegisterServiceException{
		
		logger.info("Entering into " + getClass() + " updateDriver():::");
		
		ResponseEntity<RegisterDTO> responseEntity = null;

		try {
			RegisterDTO updatedRegisterDTO = registerService.updateDriverRiderData(registerDTO);
			responseEntity = new ResponseEntity<RegisterDTO>(updatedRegisterDTO, HttpStatus.OK);
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("Errors-RiderDriverUpdate");
			error.setErrorMessage("Something went wrong while Driver/rider updating");
			responseEntity = new ResponseEntity<RegisterDTO>(HttpStatus.NOT_MODIFIED);
		}
		
		return responseEntity;
		
	}
	
	
	/**
	 * @author Harish Kumar Gudivada
	 * @param emailId
	 * @return ResponseEntity
	 * @throws UserServiceException
	 */

	@RequestMapping(value = "/getProfile/{emailId:.+}", method = RequestMethod.GET,  produces = "application/json")
	public ResponseEntity<?> getUserProfile(@PathVariable String emailId) throws UserServiceException {
		logger.info("UserProfileRestService :: users profile::: get");
		List<RegisterDTO> list =null;
		try {
			list = registerService.getUserRegistrationProfile(emailId);
			if(list==null) {
				Errors error = new Errors();
				error.setErrorCode(HttpStatus.NO_CONTENT+"");
				error.setErrorMessage("Rider and Driver Details is not available for the given emailid");
				return new ResponseEntity<Errors>(error, HttpStatus.NO_CONTENT);
			}
		}catch (Exception e) {
			logger.error("Exception Occured in Class:UserProfileRestService Method:getUserProfile Message:"+e.getMessage());
			Errors error = new Errors();
			error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR+"");
			error.setErrorMessage("Something went wrong while Loading Registerd Driver Rider Details");
			return new ResponseEntity<Errors>(error, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		logger.info("Exit from UserProfileRestService :: getUserProfile ");
		return new ResponseEntity<List<RegisterDTO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/locations", method = RequestMethod.POST)
	public String getLocation(@RequestBody RegisterDTO regDto) throws Exception {
		System.out.println("coming to rest.......");
		return registerService.searchLocation(regDto);

	}

}

