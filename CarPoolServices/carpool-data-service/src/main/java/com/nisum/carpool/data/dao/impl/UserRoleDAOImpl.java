package com.nisum.carpool.data.dao.impl;


import java.util.List; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.dao.api.UserRoleDAO;
import com.nisum.carpool.data.domain.UserRole;
import com.nisum.carpool.data.repository.UserRoleRepository;


//@Repository
@Configuration
public class UserRoleDAOImpl implements UserRoleDAO{
	
	private static Logger logger = LoggerFactory.getLogger(UserRoleDAOImpl.class);
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	/**
	 * Adds the user role into data base 
	 */
	@Override
	public UserRole addUserRole(UserRole userRole) {
		logger.info("UserRoleDAOImpl :: entered into addUserRole("+userRole+")");
		return userRoleRepository.save(userRole);	
	}

	/**
	 * deletes the exisiting user role from database
	 */
	@Override
	public boolean deleteUserRole(int id) {
		logger.info("userRoleDAOImpl: entered into deleteUserRole("+id+")");
			userRoleRepository.delete(id);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nisum.portal.data.dao.api.UserRoleDAO#getUserRole(com.nisum.portal
	 * .data.domain.UserRole)
	 */
	@Override
	public List<UserRole> getUserRole() {
		logger.info("UserRoleDAOImpl :: getUsers :: Get list of userRoles");
		//return userRoleRepository.findAll();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nisum.portal.data.dao.api.UserRoleDAO#updateUserRole(com.nisum.portal
	 * .data.domain.UserRole)
	 */
	@Override
	public UserRole updateUserRole(UserRole userRole) 
	{  
		logger.info("UserRoleDAOImpl :: updateUser :: Updating userRole");
		UserRole userRole1=userRoleRepository.findOne(userRole.getRoleId());
		userRole.setCreatedDate(userRole1.getCreatedDate());	
		return userRoleRepository.save(userRole);
		}

	@Override
	public UserRole findUserById(Integer roleId) {
		return userRoleRepository.findOne(roleId);
	}

	

}
	
