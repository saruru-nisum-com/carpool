package com.nisum.carpool.service.dto;

import java.sql.Timestamp;

public class CarpooldetailsDto {
	private Integer id;
	private Integer parentid;
	private String emailId="";
	private Integer vehicleType;
	private Integer totalNoOfSeats;
	private String fromDate="";
	private String toDate="";
	private String startTime="";
	private String toTime="";
	private Integer status;
	private double rewards;
	private Timestamp createddate;
	private Timestamp modifieddate;
	private String location="";
	private String latitude="";
	private String longitude="";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public double getRewards() {
		return rewards;
	}
	public void setRewards(double rewards) {
		this.rewards = rewards;
	}
	public Integer getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Integer getTotalNoOfSeats() {
		return totalNoOfSeats;
	}
	public void setTotalNoOfSeats(Integer totalNoOfSeats) {
		this.totalNoOfSeats = totalNoOfSeats;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public Timestamp getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createddate == null) ? 0 : createddate.hashCode());
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modifieddate == null) ? 0 : modifieddate.hashCode());
		result = prime * result + ((parentid == null) ? 0 : parentid.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rewards);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		result = prime * result + ((toTime == null) ? 0 : toTime.hashCode());
		result = prime * result + ((totalNoOfSeats == null) ? 0 : totalNoOfSeats.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((vehicleType == null) ? 0 : vehicleType.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		CarpooldetailsDto other = (CarpooldetailsDto) obj;
		if (createddate == null) {
			if (other.createddate != null)
				return false;
		} else if (!createddate.equals(other.createddate))
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modifieddate == null) {
			if (other.modifieddate != null)
				return false;
		} else if (!modifieddate.equals(other.modifieddate))
			return false;
		if (parentid == null) {
			if (other.parentid != null)
				return false;
		} else if (!parentid.equals(other.parentid))
			return false;
		if (Double.doubleToLongBits(rewards) != Double.doubleToLongBits(other.rewards))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		if (toTime == null) {
			if (other.toTime != null)
				return false;
		} else if (!toTime.equals(other.toTime))
			return false;
		if (totalNoOfSeats == null) {
			if (other.totalNoOfSeats != null)
				return false;
		} else if (!totalNoOfSeats.equals(other.totalNoOfSeats))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (vehicleType == null) {
			if (other.vehicleType != null)
				return false;
		} else if (!vehicleType.equals(other.vehicleType))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
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
		return true;
	}
	@Override
	public String toString() {
		return "CarpooldetailsDto [id=" + id + ", parentid=" + parentid + ", emailId=" + emailId + ", vehicleType="
				+ vehicleType + ", totalNoOfSeats=" + totalNoOfSeats + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", startTime=" + startTime + ", toTime=" + toTime + ", status=" + status + ", createddate="
				+ createddate + ", modifieddate=" + modifieddate + ",Location:"+location+ ",Longitude:"+longitude+ ",Latitude:"+latitude+"]";
	}
}
