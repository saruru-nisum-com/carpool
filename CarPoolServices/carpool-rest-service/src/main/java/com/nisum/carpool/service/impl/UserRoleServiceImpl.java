package com.nisum.carpool.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.service.api.UserRoleService;
import com.nisum.carpool.service.dto.UserRoleDTO;
import com.nisum.carpool.service.exception.UserRoleServiceException;
//import com.nisum.carpool.util.UserRoleCache;
import com.nisum.carpool.util.UserRoleServiceUtil;
import com.nisum.carpool.data.dao.api.UserRoleDAO;
import com.nisum.carpool.data.domain.UserRole;

@Service
public class UserRoleServiceImpl implements UserRoleService, InitializingBean{
	
	private static Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	
	@Autowired
	private UserRoleDAO userRoleDao;
	
	//UserRoleCache cache=UserRoleCache.getInstance();
	
	/**
	 * Adds New UserRole into Database
	 */
	@Override
	public UserRole addUserRole(UserRoleDTO userRoleDto) throws UserRoleServiceException {
		logger.info("UserRoleServiceImpl :: Enter into addUser(UserRoleDto)");
		
		UserRole userRole1=null;
		UserRole addUserRole=null; 
//		if(cache.verifyUserRoleToCache(userRoleDto)) {
//			logger.error("UserRoleServiceImpl :: Exception Raised As the Given User Role Exists");
//			throw new UserRoleServiceException("User Role Exists Already");
//		}else {
//			logger.info("UserRoleServiceImpl :: method call to UserRoleServiceUtil.convertDtoToDao(userRoleDto)");
			
		userRole1=UserRoleServiceUtil.convertDtoToDao(userRoleDto);	
			logger.info("User Roile :"+userRole1);
			addUserRole=(UserRole)userRoleDao.addUserRole(userRole1);
			//System.out.println(userRoleDao.addUserRole(userRole1));
			logger.info("UserRole After Adding into DB"+addUserRole); 
			
			//cache.put("user-role",userRoleDao.getUserRole());
			logger.info("added new user role to cache"); 
		
		return addUserRole;  
	}
	
	/**
	 * Deletes userRole from database and returns boolean value
	 */
	@Override
	public boolean deleteUserRole(Integer id) throws UserRoleServiceException {
		logger.info("UserRoleServiceImpl :: entered into deleteUserRole("+id+")");
		logger.info("UserRoleServiceImpl ::Method call to userRoleDao.deleteUserRole("+id+")");
			return userRoleDao.deleteUserRole(id);	
			
	}
     /**
      * get userRole from database and returns List
      */
     @Override
	public List<UserRoleDTO> getUserRole() {	
    	   logger.info("UserRoleServiceImpl :: getUserRole");
		List<UserRole>  userRoleList=userRoleDao.getUserRole();
		return UserRoleServiceUtil.convertDaoTODto(userRoleList);
	}

     /**
      * update userRole from database and returns UserRole
      */
	@Override
	public UserRole updateUserRole(UserRole userRole) {
		logger.info("UserRoleServiceImpl :: updateUserRole");
		return userRoleDao.updateUserRole(userRole);
	}

	/**
	 * loads the data into Cache from Database during application  start up o
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("loading data from db to cache");
		List<UserRole> rolesList=userRoleDao.getUserRole();
	//	UserRoleCache instance=UserRoleCache.getInstance();
		//instance.put("user-role", rolesList);
	}

	
	    /**
	     * loads the data from database
	     */
	    @Override
	    public UserRole findUserById(Integer roleId) {
		logger.info("loading data from db");
		UserRole role=userRoleDao.findUserById(roleId);
		return role;
	}

	
	    
}
