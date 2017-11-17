package com.nisum.carpool.data.dao.api;

import com.nisum.carpool.data.domain.UserRegistration;

public interface UserRegistrationDAO {
	
	
	UserRegistration findUserRegistrationByUserId(String userId,int isRider);
	
}
