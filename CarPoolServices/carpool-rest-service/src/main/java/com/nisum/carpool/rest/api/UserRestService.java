package com.nisum.carpool.rest.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

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

import com.nisum.carpool.data.dao.api.RegisterDAO;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.data.domain.User;
//import com.nisum.carpool.service.api.EmailAccount;
import com.nisum.carpool.service.api.UserRoleService;
import com.nisum.carpool.service.api.UserService;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.dto.UserDTO;
import com.nisum.carpool.service.dto.UserRoleDTO;
import com.nisum.carpool.service.exception.QuestionariesServiceException;
import com.nisum.carpool.service.exception.UserServiceException;
import com.nisum.carpool.util.CommonsUtil;
import com.nisum.carpool.util.Constants;
//import com.nisum.carpool.util.MailSender;
import com.nisum.carpool.util.UserServiceUtil;

@RestController
@RequestMapping(value = "/v1/user")
public class UserRestService {

	private static Logger logger = LoggerFactory.getLogger(UserRestService.class);

	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	RegisterRestService userRegService;
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	RegisterDAO registerDAO;
	
	//private static EmailAccount emailAccount;

	/**
	 * 
	 * @param userDto
	 * @return
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public UserDTO addUser(@RequestBody UserDTO userDto) throws UserServiceException {

		UserDTO userInfo = null;
		User 	userData=null;
		String userLocation=null;
		logger.info("UserRestService :: Creating Users :::");
		logger.info("User Rest service"+userDto.getUserName()+"emailId::"+userDto.getEmailId());
		
		try {

			String strEmail1 = null;
			strEmail1 = userDto.getEmailId();
			try {
				logger.info("userInfo findBy emailId** ");
			userInfo = userService.findByEmailId(strEmail1);
				//logger.info("find byuserId:::"+userDto.getUserId());
				//userData=userService.findUserById(userDto.getUserId());
				logger.info("userData fetched by emailId**"+userInfo);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			if (userInfo != null && strEmail1.equals(userInfo.getEmailId())) {
				logger.info("UserRestService ** in update User details :::");
				userInfo.setLoginDate(CommonsUtil.getCurrentDateTime());
				userService.updateUserDetails(userInfo);

			} else {
				logger.info("UserRestService :: save User details :::");
				 BufferedImage image = null;
				 URL url = new URL(userDto.getImage());
				 image = ImageIO.read(url);

				 ByteArrayOutputStream baos = new ByteArrayOutputStream();
				 ImageIO.write(image, "jpg", baos);
				 baos.flush();
				 byte[] imageBytes = baos.toByteArray();
				 baos.close();
				 userDto.setImageIcon(imageBytes);
				
				userDto.setLoginDate(CommonsUtil.getCurrentDateTime());
				userDto.setCreateDate(CommonsUtil.getCurrentDateTime());

				List<UserRoleDTO> roleDTOs = userRoleService.getUserRole();

				UserRoleDTO role = null;

				for (UserRoleDTO dto : roleDTOs) {

					if (Constants.USER_TYPE.equalsIgnoreCase(dto.getRole())) {

						role = new UserRoleDTO();
						role.setCreatedDate(dto.getCreatedDate());
						role.setRole(dto.getRole());
						role.setRoleId(dto.getRoleId());
					}
				}
				//userDto.setRoleId(dto.getRoleId());
				userDto.setActiveStatus(Constants.USER_STATUS);
				userService.saveUser(userDto);

				//MailSender.sendEmail(emailAccount.getAdminemail(), emailAccount.getAdminpassword(), strEmail1,null,
				//	emailAccount.getSubject(), MailSender.messageBody(userDto.getUserName()));
				
				//Get User location from profile
				try {
					logger.info("get user location from userReg Service###"+userDto.getEmailId());
					userLocation = registerDAO.getLocationOfRegisteredUser(userDto.getEmailId()).getLocation();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(userLocation!=null) {
					logger.info("user location from profile**"+userLocation);
					userDto.setLocation(userLocation);
				}
				
				httpRequest.setAttribute("userSession", userDto);
				userInfo = userDto;
			}

		}

		catch (Exception e) {
			logger.info("UserRestService :: Creating Users :: Internal Server Error");
			throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
		}

		return userInfo;
	}


	/**
	 * 
	 * @param userId
	 * @return
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId) throws UserServiceException {
		logger.info("UserRestService :: deleteUser :: Deleting User");
		Errors error = new Errors();
		try {
			User user = userService.findUserById(userId);
			ServiceStatusDto serviceStatusDTO = new ServiceStatusDto();
			if (user == null || user.getActiveStatus().equalsIgnoreCase("No")) {
				error.setErrorCode("417");
				error.setErrorMessage(Constants.USER_NOT_EXISTS);
				return new ResponseEntity<Errors>(error, HttpStatus.EXPECTATION_FAILED);
			} 
			else {
				userService.deleteUser(userId);
			//	serviceStatusDTO.setMessage(Constants.USER_DELETED);
				//return new ResponseEntity<ServiceStatusDto>(serviceStatusDTO, HttpStatus.OK);
				return null;
			}
		} catch (Exception e) {
			logger.info("UserRestService :: deleteUser :: Internal Server Error");
			throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
		}
	}

	/**
	 * Returns list of users
	 * 
	 * @return
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getUsers() throws UserServiceException {
		logger.info("UserRestService :: users");
		Errors error = new Errors();
		try {
			Map<String, UserDTO> users = userService.getUsers();
			if (users.isEmpty()) {
				error.setErrorCode("204");
				error.setErrorMessage(Constants.USERS_NOT_AVALIABLE);
				return new ResponseEntity<Errors>(error, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Collection<UserDTO>>(users.values(), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("UserRestService :: getUsers :: Internal Server Error");
			throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
		}
	}

	/**
	 * Updates single user
	 * @param userDto
	 * @return
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceStatusDto> updateUser(@RequestBody UserDTO userDto) throws UserServiceException {
		logger.info("UserRestService :: users::: update");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
			//User user = UserServiceUtil.convertDtoObjectTODao(userDto);
			Object obj = userService.updateUserDetails(userDto);
			if (obj.equals(null)) {
				serviceStatusDto.setMessage(Constants.USER_NOT_EXISTS);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NOT_FOUND);
			} else {
				serviceStatusDto.setMessage(Constants.USER_UPDATED);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("UserRestService :: UpdateUser :: Internal Server Error");
			throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
		}
	}

	/**
	 * Updates list of users
	 * 
	 * @param usersDTO
	 * @return
	 * @throws UserServiceException
	 */

	@RequestMapping(value = "/updateUsers", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceStatusDto> updateUsers(@RequestBody List<UserDTO> usersDTO)
			throws UserServiceException {
		logger.info("UserRestService :: multiple users update :::");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
			for (UserDTO userDto : usersDTO) {
				userService.updateUserDetails(userDto);
			}
			serviceStatusDto.setMessage(Constants.USER_UPDATED);
			return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("UserRestService :: Update multiple Users :: Internal Server Error");
			throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
		}
	}

	// @RequestMapping(value = "/updateUsers",method=RequestMethod.PUT,consumes
	// = "application/json",produces="application/json")
	// public ResponseEntity<ServiceStatusDto> updateUsers(@RequestBody
	// List<UserDTO> usersDTO) throws UserServiceException{
	// logger.info("UserRestService :: multiple users update :::");
	// ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
	// try {
	// for(UserDTO userDto : usersDTO)
	// {
	// userService.updateUserDetails(userDto);
	// }
	// serviceStatusDto.setMessage(Constants.USER_UPDATED);
	// return new
	// ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);
	// }
	// catch(Exception e)
	// {
	// logger.info("UserRestService :: Update multiple Users :: Internal Server
	// Error");
	// throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
	// }
	// }

	/**
	 * getUserCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieveCount", method = RequestMethod.GET)
	public Object retrieveCount() throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: getUserCount");
		return userService.getUserCount();
	}

		/**
	 * Exception handler
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		logger.info("UserRestService :: exceptionHandler :: caught exception");
		Errors errors = new Errors();
		errors.setErrorCode("Error-User");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}

//	@Autowired
//	public void setEmailAccount(EmailAccount emailAccount) {
//		UserRestService.emailAccount = emailAccount;
//	}
}
