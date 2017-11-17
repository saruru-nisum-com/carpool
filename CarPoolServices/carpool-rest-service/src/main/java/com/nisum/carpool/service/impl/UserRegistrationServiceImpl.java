package com.nisum.carpool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.UserRegistrationDAO;
import com.nisum.carpool.data.domain.UserRegistration;
import com.nisum.carpool.service.api.UserRegistrationService;
import com.nisum.carpool.service.dto.UserRegistrationDto;
import com.nisum.carpool.util.UserServiceUtil;
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{

	
	@Autowired
	UserRegistrationDAO regDao;
	
	@Override
	public UserRegistrationDto getUserRegistrationProfile(UserRegistrationDto dto) {
		UserRegistration reg=regDao.findUserRegistrationByUserId(dto.getUserId(),dto.getIsRider());
		UserRegistrationDto userRegDto = UserServiceUtil.convertDaoObjectToDto(reg);
		return userRegDto;
	}

}
