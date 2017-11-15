package com.nisum.carpool.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.dao.api.UserDAO;
import com.nisum.carpool.data.domain.User;
import com.nisum.carpool.data.repository.UserRepository;

//@Repository

@Configuration
public class UserDAOImpl implements UserDAO{

	private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findUserById(int userId) {
		logger.info("UserDAOImpl :: findUserById :: Finding user by userId");
		return userRepository.findOne(userId);
	}
	
	@Override
	public List<User> getUsers() {
		logger.info("UserDAOImpl :: getUsers :: Get list of users");
		//return userRepository.findAll();
		return null;
	}
	
	@Override
	public User updateUser(User user) {
		logger.info("UserDAOImpl :: updateUser :: Updating user");
			//return userRepository.save(user);
		return null;
	}
	@Override
	public int deleteUser(int userId) {
		logger.info("UserDAOImpl :: deleteUser :: Deleting user");
	//	return userRepository.deleteUser(userId);
		return 0;
	}

	@Override
	public long getUserCount() {
		//return userRepository.count();
		return 0;
	}

	@Override
	public User findByEmailId(String emailId) {
		//return userRepository.findByEmailId(emailId);
		return null;
	}


}
