package com.nisum.carpool.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
public class UserRegistration  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private int registrationId;
	private String userId;
	private List<Integer> vehicleType;
	private String location ;
	private String latitude;
	private String longitude ;
	private String nearby;
	private String mobile ;
	private boolean emailNotification ;
	private int isRider;
	private Timestamp createdDate ;
	private Timestamp modifiedDate;
	public int getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Integer> getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(List<Integer> vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getNearby() {
		return nearby;
	}
	public void setNearby(String nearby) {
		this.nearby = nearby;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public boolean isEmailNotification() {
		return emailNotification;
	}
	public void setEmailNotification(boolean emailNotification) {
		this.emailNotification = emailNotification;
	}
	public int getIsRider() {
		return isRider;
	}
	public void setIsRider(int isRider) {
		this.isRider = isRider;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	
}
