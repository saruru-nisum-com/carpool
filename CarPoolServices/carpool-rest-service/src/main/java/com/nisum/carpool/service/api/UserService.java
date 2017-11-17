package com.nisum.carpool.service.api;

import java.util.Map;

import com.nisum.carpool.data.domain.User;
import com.nisum.carpool.service.dto.UserDTO;

/**
 * 
 * @author nisum
 *
 */
public interface UserService {

	/**
	 * Deletes given user
	 * @param userId
	 * @return No.of deleted users count
	 */
	public int deleteUser(int userId);

	/**
	 * Updates given user
	 * @param user
	 * @return 
	 */

	public UserDTO updateUserDetails(User user);

	/**
	 * Find user given by userId
	 * @param userId
	 * @return user activeStatus
	 */
	public User findUserById(int userId);

	/**
	 * Returns list of users
	 * @return list of userDto objects
	 */
	Map<String, UserDTO> getUsers();

	/**
	 * Returns users count
	 * @return No.of users count
	 */
	long getUserCount();

	/**
	 * 
	 * @param userDto
	 */

	public void saveUser(UserDTO userDto);
	/**
	 * 
	 * @param strEmail1
	 * @return
	 */
	public UserDTO findByEmailId(String strEmail1);
	/**
	 * 
	 * @param userDto
	 */
	public void create(UserDTO userDto);
}
