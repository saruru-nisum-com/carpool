package com.nisum.carpool.data.repository;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.domain.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer>{

}
