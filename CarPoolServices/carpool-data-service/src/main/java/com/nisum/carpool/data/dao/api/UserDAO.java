package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.User;

/**
 * 
 * @author nisum
 *
 */
public interface UserDAO {

	/**
	 * Updates single user
	 * @param user
	 * @return
	 */
	User updateUser(User user);

	/**
	 * Deletes single user
	 * @param userId
	 * @return No.of users deleted
	 */
	int deleteUser(int userId);

	/**
	 * Returns list of users
	 * @return List of Objects
	 */
	List<User> getUsers();

	/**
	 * Finds user for given userId
	 * @param userId
	 * @return user object if found in the database 
	 */
	User findUserById(int userId);
	
	/**
	 * Returns existed users count
	 * @return No.of users count
	 */
	long getUserCount();
	

	/**
	 * 
	 * @param user
	 */
	void saveUser(User user);
    
	/**
	 * 
	 * @param emailId
	 * @return
	 */
	User findByEmailId(String emailId);

}
