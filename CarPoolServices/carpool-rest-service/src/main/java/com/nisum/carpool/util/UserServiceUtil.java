package com.nisum.carpool.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.carpool.service.dto.UserDTO;
import com.nisum.carpool.service.dto.UserRegistrationDto;
import com.nisum.carpool.service.dto.UserRoleDTO;
import com.nisum.carpool.data.domain.User;
import com.nisum.carpool.data.domain.UserRegistration;
import com.nisum.carpool.data.domain.UserRole;

public class UserServiceUtil {

	/**
	 * Converts List of UserDAO objects to UserDTO objects
	 * 
	 * @param users
	 * @return List of UserDTO objects
	 */
	public static List<UserDTO> convertDaoListToDto(List<User> users) {
		List<UserDTO> userDTO = new ArrayList<UserDTO>();

		if (CollectionUtils.isNotEmpty(users)) {
			for (User user : users) {
				UserDTO userDto = new UserDTO();
				userDto.setUserId(user.getUserId());
				userDto.setActiveStatus(user.getActiveStatus());
				userDto.setEmailId(user.getEmailId());
				userDto.setLoginDate(Timestamp.valueOf(user.getLoginDate()));
				userDto.setUserName(user.getUserName());
				userDto.setImage(user.getImage());
				userDto.setCreateDate(Timestamp.valueOf(user.getCreateDate()));
				UserRoleDTO userRoleDTO = new UserRoleDTO();
			//	userRoleDTO.setCreatedDate(user.getRole().getCreatedDate());
				//userRoleDTO.setRole(user.getRole().getRole());
				//userRoleDTO.setRoleId(user.getRole().getRoleId());
				userDto.setRoleId(user.getRoleId());
				userDto.setNotifications(user.getNotifications());
				userDto.setProfileName(user.getProfileName());
				userDto.setProfileName(user.getProfileName());
				//Set<Categories> categories = new HashSet<Categories>();

				/*
				 * for (ProfileSetting setting : profileSetting) { Categories category =
				 * setting.getCategoryId(); categories.add(category); }
				 */

				// ========================
				//Set<ProfileSettingDto> profileSettingsDto = new HashSet<>();

				//userDto.setProfileSettings(profileSettingsDto);
				userDTO.add(userDto);
			}
		}
		return userDTO;
	}

	/**
	 * Converts UserDAO object to UserDTO object
	 * 
	 * @param user
	 * @return UserDTO object
	 */
	public static UserDTO convertDaoObjectToDto(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setUserName(user.getUserName());
		userDTO.setEmailId(user.getEmailId());
		userDTO.setImage(user.getImage());
		userDTO.setLoginDate(Timestamp.valueOf(user.getLoginDate()));
		userDTO.setCreateDate(Timestamp.valueOf(user.getCreateDate()));
		userDTO.setActiveStatus(user.getActiveStatus());
		//userDTO.setImageIcon(user.getImageIcon());
		UserRoleDTO userRoleDTO = new UserRoleDTO();
	//	userRoleDTO.setCreatedDate(user.getRole().getCreatedDate());
	//	userRoleDTO.setRole(user.getRole().getRole());
	//	userRoleDTO.setRoleId(user.getRole().getRoleId());
		userDTO.setRoleId(user.getRoleId());
		userDTO.setNotifications(user.getNotifications());
		userDTO.setProfileName(user.getProfileName());
		
		
	//	Set<Categories> categories = new HashSet<Categories>();
		// Set<ProfileSetting> profileSettings = user.getProfileSettings();
		/*
		 * for (ProfileSetting setting : profileSetting) { Categories category =
		 * setting.getCategoryId(); categories.add(category); }
		 */

		// ========================
		//Set<ProfileSettingDto> profileSettingsDto = new HashSet<>();

		
		// ==============================
		//userDTO.setProfileSettings(profileSettingsDto);
		return userDTO;

	}

	/**
	 * Converts list of user dto objects to dao list
	 * 
	 * @param userDtoList
	 * @return List of user objects
	 */
	public static List<User> convertDtoListTODao(List<UserDTO> userDtoList) {
		List<User> users = new ArrayList<User>();
		if (CollectionUtils.isNotEmpty(userDtoList)) {
			for (UserDTO userlist : userDtoList) {
				User user = new User();
				user.setUserId(UUID.randomUUID().hashCode());
				user.setActiveStatus(userlist.getActiveStatus());
				user.setEmailId(userlist.getEmailId());
				user.setLoginDate(userlist.getLoginDate().toLocalDateTime());
				user.setCreateDate(userlist.getCreateDate().toLocalDateTime());
				user.setUserName(userlist.getUserName());
				user.setImage(userlist.getImage());
				UserRole userRole = new UserRole();
				//userRole.setCreatedDate(userlist.getRole().getCreatedDate());
				//userRole.setRole(userlist.getRole().getRole());
				//userRole.setRoleId(userlist.getRole().getRoleId());
				//user.setRole(userRole);
				user.setNotifications(userlist.getNotifications());
				user.setProfileName(userlist.getProfileName());
				//Set<ProfileSetting> profileSet = new HashSet<ProfileSetting>();
				/*
				 * for (Categories category:userlist.getProfileSettings()) { ProfileSetting
				 * profileSetting = new ProfileSetting();
				 * profileSetting.setCategoryId(category);
				 * //profileSetting.setUserId(userlist.getUserId());
				 * profileSet.add(profileSetting); }
				 */
				//user.setProfileSettings(user.getProfileSettings());
				users.add(user);
			}
		}
		return users;
	}

	/**
	 * Converts dto object to dao object
	 * 
	 * @param userdto
	 * @return user object
	 */
	public static User convertDtoObjectTODao(UserDTO userdto) {
		User user = new User();
		UUID idOne = UUID.randomUUID();
		//user.setUserId(userdto.getUserId());
		user.setUserId(idOne.hashCode());
		user.setUserName(userdto.getUserName());
		user.setEmailId(userdto.getEmailId());
		user.setLoginDate(userdto.getLoginDate().toLocalDateTime());
		user.setCreateDate(userdto.getCreateDate().toLocalDateTime());
		user.setImage(userdto.getImage());
		System.out.println("image iconnnn::"+userdto.getImageIcon());
		//user.setImageIcon(userdto.getImageIcon());
		user.setActiveStatus(userdto.getActiveStatus());
		user.setNotifications(userdto.getNotifications());
		user.setProfileName(userdto.getProfileName());
		user.setRoleId(2);
		
		UserRole userRole = new UserRole();
		userRole.setCreatedDate(userdto.getLoginDate().toLocalDateTime());
		userRole.setRoleId(2);
		userRole.setRole("user");
		// ========================
		//Set<ProfileSetting> profileSettings = new HashSet<>();

		//Iterator itr = userdto.getProfileSettings().iterator();
//		while (itr.hasNext()) {
//
//			ProfileSetting profileSetting = new ProfileSetting();
//
//			ProfileSettingDto profileSettingDto = (ProfileSettingDto) itr.next();
//			CategoriesDTO categoryDto = profileSettingDto.getCategoryId();
//			UserDTO profileUserDto = profileSettingDto.getUserId();
//
//			Categories categories = new Categories();
//			categories.setCategoryId(categoryDto.getCategoryId());
//			profileUserDto.setUserId(userdto.getUserId());
//			User profileUser = new User();
//
//			profileUser.setUserId(profileUserDto.getUserId());
//
//			profileSetting.setCategoryId(categories);
//			profileSetting.setUserId(profileUser);
//
//			profileSettings.add(profileSetting);

	//}

		// ========================

		/*
		 * for (Categories category :userdto.getProfileSettings()) { ProfileSetting
		 * profileSetting = new ProfileSetting();
		 * profileSetting.setCategoryId(category); profileSetting.setUserId(user);
		 * profileSet.add(profileSetting); }
		 */
		//user.setProfileSettings(profileSettings);
		return user;
	}
	public static User convertUpdateUserDtoTODao(UserDTO userdto) {
		User user = new User();
		//UUID idOne = UUID.randomUUID();
		//user.setUserId(userdto.getUserId());
		//user.setUserId(idOne.hashCode());
		user.setUserId(userdto.getUserId());
		user.setUserName(userdto.getUserName());
		user.setEmailId(userdto.getEmailId());
		user.setLoginDate(userdto.getLoginDate().toLocalDateTime());
		user.setCreateDate(userdto.getCreateDate().toLocalDateTime());
		user.setImage(userdto.getImage());
		System.out.println("image iconnnn::"+userdto.getImageIcon());
		//user.setImageIcon(userdto.getImageIcon());
		user.setActiveStatus(userdto.getActiveStatus());
		user.setNotifications(userdto.getNotifications());
		user.setProfileName(userdto.getProfileName());
		user.setRoleId(2);
		
		UserRole userRole = new UserRole();
		userRole.setCreatedDate(userdto.getLoginDate().toLocalDateTime());
		userRole.setRoleId(2);
		userRole.setRole("user");
		return user;
	}

	public static UserRegistrationDto convertDaoObjectToDto(UserRegistration reg) {
		UserRegistrationDto dto=new UserRegistrationDto();
		dto.setRegistrationId(reg.getRegistrationId());
		dto.setCreatedDate(reg.getCreatedDate());
		dto.setEmailNotification(reg.isEmailNotification());
		dto.setIsRider(reg.getIsRider());
		dto.setLatitude(reg.getLatitude());
		dto.setLongitude(reg.getLongitude());
		dto.setLocation(reg.getLocation());
		dto.setMobile(reg.getMobile());
		dto.setModifiedDate(reg.getModifiedDate());
		dto.setNearby(reg.getNearby());
		dto.setUserId(reg.getUserId());
		dto.setVehicleType(reg.getVehicleType());
		return dto;
	}
	
	
	public static UserRegistration convertDaoObjectToDto(UserRegistrationDto dto) {
		UserRegistration userReg=new UserRegistration();
		userReg.setRegistrationId(dto.getRegistrationId());
		userReg.setCreatedDate(dto.getCreatedDate());
		userReg.setEmailNotification(dto.isEmailNotification());
		userReg.setIsRider(dto.getIsRider());
		userReg.setLatitude(dto.getLatitude());
		userReg.setLongitude(dto.getLongitude());
		userReg.setLocation(dto.getLocation());
		userReg.setMobile(dto.getMobile());
		userReg.setModifiedDate(dto.getModifiedDate());
		userReg.setNearby(dto.getNearby());
		userReg.setUserId(dto.getUserId());
		userReg.setVehicleType(dto.getVehicleType());
		return userReg;
	}
	
	
}