package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.RegisterDomain;

public interface RegisterDAO {

	
	public RegisterDomain registerDriverorRider(RegisterDomain registerDomain);
	List<RegisterDomain> findUserRegistrationByUserId(String emailId);

	public RegisterDomain getLocationOfRegisteredUser(String emailId);
	
	public RegisterDomain updateDriverOrRider(RegisterDomain regDomain);
	
	public String getMobileNumberByEmail(String emailId);
}
