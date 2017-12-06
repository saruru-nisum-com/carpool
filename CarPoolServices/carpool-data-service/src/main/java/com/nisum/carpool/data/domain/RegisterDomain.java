package com.nisum.carpool.data.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
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
	private String emailid;
	private List<Integer> vehicletype;
	private String location;
	private String latitude;
	private String longitude;
	private String nearby;
	private String mobile;
	private boolean emailnotification;
	private Integer isrider;
	private LocalDateTime createddate;
	private LocalDateTime modifieddate;
	private Integer gender;
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createddate == null) ? 0 : createddate.hashCode());
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
		result = prime * result + (emailnotification ? 1231 : 1237);
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((isrider == null) ? 0 : isrider.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((modifieddate == null) ? 0 : modifieddate.hashCode());
		result = prime * result + ((nearby == null) ? 0 : nearby.hashCode());
		result = prime * result + ((registrationid == null) ? 0 : registrationid.hashCode());
		result = prime * result + ((vehicletype == null) ? 0 : vehicletype.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterDomain other = (RegisterDomain) obj;
		if (createddate == null) {
			if (other.createddate != null)
				return false;
		} else if (!createddate.equals(other.createddate))
			return false;
		if (emailid == null) {
			if (other.emailid != null)
				return false;
		} else if (!emailid.equals(other.emailid))
			return false;
		if (emailnotification != other.emailnotification)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (isrider == null) {
			if (other.isrider != null)
				return false;
		} else if (!isrider.equals(other.isrider))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (modifieddate == null) {
			if (other.modifieddate != null)
				return false;
		} else if (!modifieddate.equals(other.modifieddate))
			return false;
		if (nearby == null) {
			if (other.nearby != null)
				return false;
		} else if (!nearby.equals(other.nearby))
			return false;
		if (registrationid == null) {
			if (other.registrationid != null)
				return false;
		} else if (!registrationid.equals(other.registrationid))
			return false;
		if (vehicletype == null) {
			if (other.vehicletype != null)
				return false;
		} else if (!vehicletype.equals(other.vehicletype))
			return false;
		return true;
	}
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
	
	
//	public String getUserid() {
//		return userid;
//	}
//	public void setUserid(String userid) {
//		this.userid = userid;
//	}
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
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
	
	public LocalDateTime getCreateddate() {
		return createddate;
	}
	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}
	public LocalDateTime getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(LocalDateTime modifieddate) {
		this.modifieddate = modifieddate;
	}
	
	
	
	/**
	 * @return the isrider
	 */
	public Integer getIsrider() {
		return isrider;
	}
	/**
	 * @param isrider the isrider to set
	 */
	public void setIsrider(Integer isrider) {
		this.isrider = isrider;
	}
	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegisterDomain [registrationid=" + registrationid + ", emailid=" + emailid + ", vehicletype="
				+ vehicletype + ", location=" + location + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", nearby=" + nearby + ", mobile=" + mobile + ", emailnotification=" + emailnotification
				+ ", isrider=" + isrider + ", createddate=" + createddate + ", modifieddate=" + modifieddate
				+ ", gender=" + gender + "]";
	}
		
}
