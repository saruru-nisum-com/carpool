package com.nisum.carpool.service.dto;

import java.io.Serializable;
/**
 * 
 * @author Rajendra Prasad Dava :: CPL-036 : Building reusable mail component for
 *         carpool
 *      GenericMailDto to construct the body of mail
 */
public class GenericEmailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String location;
	private String date;
	private String startTime;
	private String returnTime;
	private int status;
	private String remarks;
	private Integer isRider;
	private String riderUserName;
	private boolean notifyMe;

	/**
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return teh startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return the returnTime
	 */
	public String getReturnTime() {
		return returnTime;
	}

	/**
	 * 
	 * @param returnTime
	 *            the returnTime to set
	 */
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 * 
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 
	 * @return the isRider
	 */
	public Integer getIsRider() {
		return isRider;
	}

	/**
	 * 
	 * @param isRider
	 *            the isRider to set
	 */
	public void setIsRider(Integer isRider) {
		this.isRider = isRider;
	}

	/**
	 * 
	 * @return the riderUserName
	 */
	public String getRiderUserName() {
		return riderUserName;
	}

	/**
	 * 
	 * @param riderUserName
	 *            the riderUserName to set
	 */
	public void setRiderUserName(String riderUserName) {
		this.riderUserName = riderUserName;
	}

	/**
	 * 
	 * @return the notifyMe
	 */
	public boolean isNotifyMe() {
		return notifyMe;
	}

	/**
	 * 
	 * @param notifyMe
	 *            the notifyMe to set
	 */
	public void setNotifyMe(boolean notifyMe) {
		this.notifyMe = notifyMe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((isRider == null) ? 0 : isRider.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + (notifyMe ? 1231 : 1237);
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((returnTime == null) ? 0 : returnTime.hashCode());
		result = prime * result + ((riderUserName == null) ? 0 : riderUserName.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + status;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericEmailDto other = (GenericEmailDto) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (isRider == null) {
			if (other.isRider != null)
				return false;
		} else if (!isRider.equals(other.isRider))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (notifyMe != other.notifyMe)
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (returnTime == null) {
			if (other.returnTime != null)
				return false;
		} else if (!returnTime.equals(other.returnTime))
			return false;
		if (riderUserName == null) {
			if (other.riderUserName != null)
				return false;
		} else if (!riderUserName.equals(other.riderUserName))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenericEmailDto [userName=" + userName + ", location=" + location + ", date=" + date + ", startTime="
				+ startTime + ", returnTime=" + returnTime + ", status=" + status + ", remarks=" + remarks
				+ ", isRider=" + isRider + ", riderUserName=" + riderUserName + ", notifyMe=" + notifyMe + "]";
	}
}
