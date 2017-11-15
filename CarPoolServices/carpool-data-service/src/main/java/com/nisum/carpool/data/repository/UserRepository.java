package com.nisum.carpool.data.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import com.nisum.carpool.data.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
  
	
	User findByEmailId(String emailId);
	
	public User findByUserId(int userId);

}
