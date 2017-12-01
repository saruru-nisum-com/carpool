package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.RegisterDomain;

public interface RegisterDAO {

	
	public RegisterDomain registerDriverorRider(RegisterDomain registerDomain);
	List<RegisterDomain> findUserRegistrationByUserId(String emailId);

	public String getLocationOfRegisteredUser(String emailId);
}
