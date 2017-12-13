/**
 * 
 */
package com.nisum.carpool.service.dto;

/**
 * @author Lakshmi Kiran
 *
 */
public class CarpoolRiderOptedDetailsDto {
	
	private int carpoolRiderDetailsId;
	private int carpoolId;
	private String name;
	private String emailId;
	private String mobile;
	private int status;
	private int reason;
	private String fromdate;
	private String statusName;
	
	public int getCarpoolId() {
		return carpoolId;
	}
	public void setCarpoolId(int carpoolId) {
		this.carpoolId = carpoolId;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public int getCarpoolRiderDetailsId() {
		return carpoolRiderDetailsId;
	}
	public void setCarpoolRiderDetailsId(int carpoolRiderDetailsId) {
		this.carpoolRiderDetailsId = carpoolRiderDetailsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carpoolId;
		result = prime * result + carpoolRiderDetailsId;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((fromdate == null) ? 0 : fromdate.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + reason;
		result = prime * result + status;
		result = prime * result + ((statusName == null) ? 0 : statusName.hashCode());
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
		CarpoolRiderOptedDetailsDto other = (CarpoolRiderOptedDetailsDto) obj;
		if (carpoolId != other.carpoolId)
			return false;
		if (carpoolRiderDetailsId != other.carpoolRiderDetailsId)
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (fromdate == null) {
			if (other.fromdate != null)
				return false;
		} else if (!fromdate.equals(other.fromdate))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reason != other.reason)
			return false;
		if (status != other.status)
			return false;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CarpoolRiderOptedDetailsDto [carpoolRiderDetailsId=" + carpoolRiderDetailsId + ", carpoolId="
				+ carpoolId + ", name=" + name + ", emailId=" + emailId + ", mobile=" + mobile + ", status=" + status
				+ ", reason=" + reason + ", fromdate=" + fromdate + ", statusName=" + statusName + "]";
	}
	
	
	
}
