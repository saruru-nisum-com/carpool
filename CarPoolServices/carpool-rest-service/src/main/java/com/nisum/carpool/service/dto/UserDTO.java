package com.nisum.carpool.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.nisum.carpool.service.dto.UserRoleDTO;

/**
 * 
 * @author nisum
 *
 */
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;
	private String emailId;
	private String userName;
	private Timestamp loginDate;
	private int roleId; //===============
	private String activeStatus;
	private Timestamp createDate;
	private String image;
	private byte[] imageIcon;
	private String notifications;
	private String profileName;

	
	public byte[] getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(byte[] imageIcon) {
		this.imageIcon = imageIcon;
	}

	/**
	 * Returns user id
	 * 
	 * @return
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets user id
	 * 
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Sets user email
	 * 
	 * @return
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Returns user email
	 * 
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Returns user created date
	 * 
	 * @return
	 */
	public Timestamp getLoginDate() {
		return loginDate;
	}

	/**
	 * Sets user role
	 * 
	 * @param loginDate
	 */
	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * Returns user active status
	 * 
	 * @return
	 */
	public String getActiveStatus() {
		return activeStatus;
	}

	/**
	 * Sets user active status
	 * 
	 * @param activeStatus
	 */
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNotifications() {
		return notifications;
	}

	public void setNotifications(String notifications) {
		this.notifications = notifications;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

}
