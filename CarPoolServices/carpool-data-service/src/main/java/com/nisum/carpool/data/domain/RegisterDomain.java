package com.nisum.carpool.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;
@Table("cp_userregistration")
public class RegisterDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private Integer registrationid;
	private String userid;
	private List<Integer> vehicletype;
	private String location;
	private String latitude;
	private String longitude;
	private String nearby;
	private String mobile;
	private boolean emailnotification;
	private int isrider;
	private Timestamp createddate;
	private Timestamp modifieddate;
	/**
	 * @return the registrationid
	 */
	public Integer getRegistrationid() {
		return registrationid;
	}
	/**
	 * @param registrationid the registrationid to set
	 */
	public void setRegistrationid(Integer registrationid) {
		this.registrationid = registrationid;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the vehicletype
	 */
	public List<Integer> getVehicletype() {
		return vehicletype;
	}
	/**
	 * @param vehicletype the vehicletype to set
	 */
	public void setVehicletype(List<Integer> vehicletype) {
		this.vehicletype = vehicletype;
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
	 * @return the emailnotification
	 */
	public boolean isEmailnotification() {
		return emailnotification;
	}
	/**
	 * @param emailnotification the emailnotification to set
	 */
	public void setEmailnotification(boolean emailnotification) {
		this.emailnotification = emailnotification;
	}
	/**
	 * @return the isrider
	 */
	public int getIsrider() {
		return isrider;
	}
	/**
	 * @param isrider the isrider to set
	 */
	public void setIsrider(int isrider) {
		this.isrider = isrider;
	}
	/**
	 * @return the createddate
	 */
	public Timestamp getCreateddate() {
		return createddate;
	}
	/**
	 * @param createddate the createddate to set
	 */
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	/**
	 * @return the modifieddate
	 */
	public Timestamp getModifieddate() {
		return modifieddate;
	}
	/**
	 * @param modifieddate the modifieddate to set
	 */
	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
