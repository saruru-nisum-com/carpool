package com.nisum.carpool.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class RegisterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer registrationId; 
	private String emailId;
	private List<Integer> vehicleType;
	private String location;
	private String   latitude;
	private String   longitude;
	private String   nearby;
	private String   mobile;
	private boolean   emailNotification;
	private Integer   isRider;
	private Timestamp   createdDate;
	private Timestamp  modifiedDate;
	private int gender;
	/**
	 * @return the registrationId
	 */
	public Integer getRegistrationId() {
		return registrationId;
	}
	/**
	 * @param registrationId the registrationId to set
	 */
	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
	}
	/**
	 * @return the userId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the vehicleType
	 */
	public List<Integer> getVehicleType() {
		return vehicleType;
	}
	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(List<Integer> vehicleType) {
		this.vehicleType = vehicleType;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the nearby
	 */
	public String getNearby() {
		return nearby;
	}
	/**
	 * @param nearby the nearby to set
	 */
	public void setNearby(String nearby) {
		this.nearby = nearby;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the emailNotification
	 */
	public boolean isEmailNotification() {
		return emailNotification;
	}
	/**
	 * @param emailNotification the emailNotification to set
	 */
	public void setEmailNotification(boolean emailNotification) {
		this.emailNotification = emailNotification;
	}
	/**
	 * @return the isRider
	 */
	public Integer getIsRider() {
		return isRider;
	}
	/**
	 * @param isRider the isRider to set
	 */
	public void setIsRider(Integer isRider) {
		this.isRider = isRider;
	}
	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the modifiedDate
	 */
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	

}
