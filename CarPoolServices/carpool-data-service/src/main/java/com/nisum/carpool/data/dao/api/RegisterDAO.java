package com.nisum.carpool.data.dao.api;

import com.nisum.carpool.data.domain.RegisterDomain;

public interface RegisterDAO {

	
	public RegisterDomain registerDriverorRider(RegisterDomain registerDomain);
	public RegisterDomain findByUserId(String userId);
}
