package com.nisum.carpool.data.repository;


import org.springframework.data.repository.CrudRepository;

import com.nisum.carpool.data.domain.UserRegistration;

public interface UserRegistrationRepository extends CrudRepository<UserRegistration,Integer>{

	UserRegistration findByUserIdAndIsRider(String userId,int isRider);

}
