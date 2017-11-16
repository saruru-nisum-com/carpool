package com.nisum.carpool.data.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nisum.carpool.data.dao.api.UserRegistrationDAO;
import com.nisum.carpool.data.domain.UserRegistration;
import com.nisum.carpool.data.repository.UserRegistrationRepository;

public class UserRegistrationDaoImpl implements UserRegistrationDAO{

	private static Logger logger = LoggerFactory.getLogger(UserRegistrationDaoImpl.class);

	@Autowired
	UserRegistrationRepository repository;
	
	@Override
	public UserRegistration findUserRegistrationByUserId(String userId,int isRider) {
		logger.info("UserRegistrationDaoImpl :: findUserRegistrationByUserId :: Finding user by userId");
		return repository.findByUserIdAndIsRider(userId,isRider);
	}

	
	
}
