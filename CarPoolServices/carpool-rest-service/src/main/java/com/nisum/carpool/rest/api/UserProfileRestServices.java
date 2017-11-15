package com.nisum.carpool.rest.api;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.service.api.UserProfileService;
//import com.nisum.carpool.service.dto.CategoriesDTO;
//import com.nisum.carpool.service.dto.ProfileSettingDto;
import com.nisum.carpool.service.dto.UserDTO;
import com.nisum.carpool.service.dto.UserProfileDTO;
import com.nisum.carpool.service.dto.UserRoleDTO;
import com.nisum.carpool.service.exception.UserServiceException;
import com.nisum.carpool.util.ExceptionConstans;
//import com.nisum.carpool.data.domain.Categories;
//import com.nisum.carpool.data.domain.ProfileSetting;
import com.nisum.carpool.data.domain.User;

@RestController
@RequestMapping(value = "/v1/userprofile/")
public class UserProfileRestServices {

	@Autowired
	UserProfileService userProfileService;

	private static Logger logger = LoggerFactory.getLogger(UserProfileRestServices.class);

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody UserProfileDTO userProfileDto) throws UserServiceException {
		return null;
	}

	@RequestMapping(value = "/getProfile", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserDTO> getUserProfile(@RequestBody UserProfileDTO userProfileDto)
			throws UserServiceException {
		logger.info("UserProfileRestService :: users profile::: get");

		UserDTO userdto = userProfileService.getUserProfile(userProfileDto);

		return new ResponseEntity<UserDTO>(userdto, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteCategory(@RequestBody UserProfileDTO userProfileDto) throws UserServiceException {
		logger.info("UserProfileRestService :: users profile::: get");

		// System.out.println("getUserId..."+userProfileDto.getUserId());
		Integer userdto = userProfileService.deleteCategory(userProfileDto);

		return new ResponseEntity<Integer>(userdto, HttpStatus.OK);

	}

}
