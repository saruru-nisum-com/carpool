package com.nisum.carpool.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;
//import java.util.Set;

import org.springframework.data.cassandra.mapping.Indexed;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private int userId;
	@Indexed
	private String emailId;
	private String userName;
	private Timestamp loginDate;
	private String activeStatus;
	private int roleId;
	private Timestamp createDate;
	private String image;
	//private byte[] imageIcon;
	private String profileName;
	private String notifications;
	
	public User() {
		
	}

	public User(int userId, String emailId, String userName, Timestamp loginDate, String activeStatus, Timestamp createDate,
			String image, int roleId) {
		super();
		this.userId = userId;
		this.emailId = emailId;
		this.userName = userName;
		this.loginDate = loginDate;
		this.activeStatus = activeStatus;
		this.createDate = createDate;
		this.image = image;
		this.roleId = roleId;
	}
	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getNotifications() {
		return notifications;
	}

	public void setNotifications(String notifications) {
		this.notifications = notifications;
	}

//	public byte[] getImageIcon() {
//		return imageIcon;
//	}
//
//	public void setImageIcon(byte[] imageIcon) {
//		this.imageIcon = imageIcon;
//	}

	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

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

	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", emailId=" + emailId + ", userName=" + userName + ", loginDate=" + loginDate
				+ ", activeStatus=" + activeStatus + ", createDate=" + createDate + ", image=" + image + ", roleId="
				+ roleId + "]";
	}

	

	
	
//	public User(int userId) {
//		this.userId = userId;
//	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}