package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.RegisterDomain;

public interface RegisterDAO {

	
	public RegisterDomain registerDriverorRider(RegisterDomain registerDomain);
	List<RegisterDomain> findUserRegistrationByUserId(String emailId);
<<<<<<< HEAD
	public String getLocationOfRegisteredUser(String emailId);
=======
>>>>>>> 6f1d8c4ac69fb41e5d2c674f3b982340f17cc30e
}
