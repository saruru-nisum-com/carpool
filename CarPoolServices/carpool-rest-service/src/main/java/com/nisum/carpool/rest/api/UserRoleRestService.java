package com.nisum.carpool.rest.api;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.service.api.UserRoleService;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.dto.UserRoleDTO;
import com.nisum.carpool.service.exception.UserRoleServiceException;
import com.nisum.carpool.util.Constants;
import com.nisum.carpool.data.domain.UserRole;

@RestController
@RequestMapping("/v1/userrole")
public class UserRoleRestService {

	@Autowired
	private UserRoleService userRoleService;

	private static Logger logger = LoggerFactory.getLogger(UserRoleRestService.class);

	//This method will add  User Role into database 
	@RequestMapping(value="/create", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addUserRole(@RequestBody UserRoleDTO userRoleDto) throws UserRoleServiceException {	
		logger.info("UserRoleRestService :: Entered into addUserRole()");
		ServiceStatusDto serviceStatusDto=new ServiceStatusDto();
		try {
			
			if(userRoleDto.getRole().trim().isEmpty()) {
				Errors error = new Errors();
				error.setErrorCode("Errors-UserRole");
				error.setErrorMessage(Constants.USER_ROLE_EMPTY);
				ResponseEntity<Errors> rsEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
				return rsEntity;
			
			}
				UserRole user =userRoleService.addUserRole(userRoleDto);
		
			serviceStatusDto.setStatus(true);;
			serviceStatusDto.setMessage(Constants.USER_ROLE_ADDED);
		}catch(Exception e) {	
			logger.error("UserRoleRestService :: User Role "+userRoleDto.getRole()+" exists already");
			
			Errors error = new Errors();
			error.setErrorCode("Errors-UserRole");
			error.setErrorMessage(Constants.USER_ROLE_EXISTS);
			ResponseEntity<Errors> rsEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
			return rsEntity;
		}
		logger.info("UserRoleRestService :: Given User Role Added Successfully");
		return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
		
	}


	//This method will delete the existing user role from database
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserRole(@PathVariable Integer id) throws UserRoleServiceException {
		logger.info("UserRoleRestService :: Entered into deleteUserRole()");
		ServiceStatusDto serviceStatusDto=new ServiceStatusDto();
		try {
				userRoleService.deleteUserRole(id);
				serviceStatusDto.setStatus(true);;
				serviceStatusDto.setMessage(Constants.USER_ROLE_DELETED);
		}catch(Exception e) {	
			logger.error("UserRoleRestService :: User Role with Given "+id+" Doesn't Exists");
			
			Errors error = new Errors();
			error.setErrorCode("Errors-UserRole");
			error.setErrorMessage(Constants.USER_ROLE_NOT_EXISTS);
			ResponseEntity<Errors> rsEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
			return rsEntity;
		}	
		logger.info("UserRoleRestService :: Existing User Role Deleted Successfully");		
		return new ResponseEntity<Object>(serviceStatusDto, HttpStatus.OK);
	}


	/**
	 * get userRole
	 * @return
	 * @throws UserRoleServiceException
	 */
	@RequestMapping(value="/retrieve",method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<UserRoleDTO>> getUserRole()  throws UserRoleServiceException{
		logger.info("UserRoleService :: userrole::");	
		try {
			List<UserRoleDTO> userRoleList=userRoleService.getUserRole();

			if(userRoleList.isEmpty()) {	 		 
				return new ResponseEntity<List<UserRoleDTO>>(userRoleList,HttpStatus.NO_CONTENT);	     
			} else {
				return new ResponseEntity<List<UserRoleDTO>>(userRoleList,HttpStatus.OK);	
			}	
		} catch(Exception e) {
			throw new UserRoleServiceException("Error Message");
		}
	}	 
	/**
	 * Updates userRole
	 * @param userRole
	 * @return
	 * @throws UserRoleServiceException
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT, consumes="application/json",produces="application/json")
	public ResponseEntity<ServiceStatusDto> updateUserRole(@RequestBody UserRole userRole) throws UserRoleServiceException{					
		logger.info("UserRoleService :: userrole");	 
		try {
		boolean roleExisted = false;
		String newRoleName=userRole.getRole();
		Integer roleId=userRole.getRoleId();
		UserRole role = userRoleService.findUserById(roleId);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		if(role!=null && newRoleName!= null && newRoleName.trim().length() > 0 ){

		    List<UserRoleDTO> existingRoles = userRoleService.getUserRole();
		    for(UserRoleDTO roleDto : existingRoles) {

		   	  if(roleDto.getRole().equalsIgnoreCase(newRoleName)) {

		   	  roleExisted = true;

		   	  break;

		   	  }

		    }
		    if(!roleExisted){

		userRoleService.updateUserRole(userRole);

		serviceStatusDto.setMessage(Constants.USER_ROLE_UPDATED);

		return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);

		    }else {

		        serviceStatusDto.setMessage(Constants.USER_ROLE_CANNOTBE_SAME);

		return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.EXPECTATION_FAILED);

		    }

		} else {

		serviceStatusDto.setMessage(Constants.USER_ROLE_CANNOTBE_SAME);

		return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.EXPECTATION_FAILED);
		}

		} catch (Exception e) {

		throw new UserRoleServiceException(Constants.INTERNALSERVERERROR);

		}

		}
}