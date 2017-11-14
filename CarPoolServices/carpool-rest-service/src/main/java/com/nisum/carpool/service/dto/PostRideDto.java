package com.nisum.carpool.service.dto;


public class PostRideDto {
	private Integer postRideId;
	private String vehicleType;
	private Integer totalNoOfSeats;
	private String fromDate;
	private String toDate;
	private String startTime;
	private String endTime;
	public Integer getPostRideId() {
		return postRideId;
	}
	public void setPostRideId(Integer postRideId) {
		this.postRideId = postRideId;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
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
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		result = prime * result + ((totalNoOfSeats == null) ? 0 : totalNoOfSeats.hashCode());
		result = prime * result + ((vehicleType == null) ? 0 : vehicleType.hashCode());
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
		PostRideDto other = (PostRideDto) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (postRideId != other.postRideId)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		if (totalNoOfSeats == null) {
			if (other.totalNoOfSeats != null)
				return false;
		} else if (!totalNoOfSeats.equals(other.totalNoOfSeats))
			return false;
		if (vehicleType == null) {
			if (other.vehicleType != null)
				return false;
		} else if (!vehicleType.equals(other.vehicleType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PostRideDto [postRideId=" + postRideId + ", vehicleType=" + vehicleType + ", totalNoOfSeats="
				+ totalNoOfSeats + ", fromDate=" + fromDate + ", toDate=" + toDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
}
