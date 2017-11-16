package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.UserRole;

public interface UserRoleDAO {
	UserRole addUserRole(UserRole userRole);
	boolean deleteUserRole(int id);
	List<UserRole> getUserRole();
	public UserRole updateUserRole(UserRole userRole);
	public UserRole findUserById(Integer roleId);
	
}
