package com.nisum.carpool.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;


import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;


@Table
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private int roleId;
	private String role;
	private Timestamp createdDate;

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

	/**
	 * Returns role created time
	 * @return
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets role created time
	 * @param date
	 */
	public void setCreatedDate(Timestamp date) {
		this.createdDate = date;
	}

	 @Override
	 public String toString() {
	 return "UserRole [roleId=" + roleId + ", role=" + role + ", createdDate=" +
	 createdDate + "]";
	 }

	
}