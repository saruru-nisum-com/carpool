package com.nisum.carpool.service.api;

import java.util.List;

import com.nisum.carpool.service.dto.UserRoleDTO;
import com.nisum.carpool.service.exception.UserRoleServiceException;
import com.nisum.carpool.data.domain.UserRole;

public interface UserRoleService {
	
	public UserRole addUserRole(UserRoleDTO userRoleDto) throws UserRoleServiceException;
	public boolean deleteUserRole(Integer id) throws UserRoleServiceException;
	List<UserRoleDTO> getUserRole();
	public UserRole updateUserRole(UserRole userRole);
	public UserRole findUserById(Integer roleId);
	

}
