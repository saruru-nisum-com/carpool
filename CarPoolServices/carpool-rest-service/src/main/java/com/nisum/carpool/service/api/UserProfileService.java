package com.nisum.carpool.service.api;


import com.nisum.carpool.service.dto.UserDTO;
import com.nisum.carpool.service.dto.UserProfileDTO;
import com.nisum.carpool.data.domain.User;

public interface UserProfileService {

	public String updateUserProfile(UserDTO profile);

	// UserDTO getUserProfileByEmail(String email);
	public User getUserByMailID(String mailID);

	UserDTO userDTO = null;
	
	public UserDTO getUserProfile(UserProfileDTO userProfileDto);
	
	public int deleteCategory(UserProfileDTO userProfileDto);

}
