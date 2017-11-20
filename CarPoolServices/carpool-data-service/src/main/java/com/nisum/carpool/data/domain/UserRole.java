package com.nisum.carpool.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;


@Table
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private int roleId;
	private String role;
	private LocalDateTime createdDate;

	/**
	 * Returns roleId
	 * @return
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * Sets roleId
	 * @param roleId
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * Returns roleName
	 * @return
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets roleName
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Returns role created time
	 * @return
	 */
	

	
	
}