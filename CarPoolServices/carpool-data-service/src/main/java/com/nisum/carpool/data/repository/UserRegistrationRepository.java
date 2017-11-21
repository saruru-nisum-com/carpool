package com.nisum.carpool.data.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.domain.UserRegistration;

@Repository
public interface UserRegistrationRepository extends CrudRepository<UserRegistration,Integer>{

	UserRegistration findByUserIdAndIsRider(String userId,int isRider);

}
