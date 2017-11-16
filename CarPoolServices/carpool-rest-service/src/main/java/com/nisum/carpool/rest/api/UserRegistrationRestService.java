package com.nisum.carpool.rest.api;

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

import com.nisum.carpool.service.api.UserRegistrationService;
import com.nisum.carpool.service.dto.UserDTO;
import com.nisum.carpool.service.dto.UserProfileDTO;
import com.nisum.carpool.service.dto.UserRegistrationDto;
import com.nisum.carpool.service.exception.UserServiceException;

@RestController
@RequestMapping(value = "/v1/userRegisteration")
public class UserRegistrationRestService {

	private static Logger logger = LoggerFactory.getLogger(UserRestService.class);
	
	@Autowired
	UserRegistrationService service;
	
	
	@RequestMapping(value = "/getProfile/{isRider}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserRegistrationDto> getUserProfile(@RequestBody UserRegistrationDto userRegDto,@PathVariable int isRider)
			throws UserServiceException {
		logger.info("UserProfileRestService :: users profile::: get");
		userRegDto.setIsRider(isRider);
		UserRegistrationDto userdto = service.getUserRegistrationProfile(userRegDto);

		return new ResponseEntity<UserRegistrationDto>(userRegDto, HttpStatus.OK);
	}

	
	
}
