package com.nisum.carpool.service.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
public class DriverCarPoolDto {
	private Integer parentId;
	private String location;
	private String fromDate;
	private String toDate;
	private String status;
	private String mobile;
	private String name;
	private String email;
	private Integer totalSeats;
	private Integer filledSeats;
	List<TodayRiderDetailsDTO> todayRiderDetailsDTOs=new ArrayList<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public List<TodayRiderDetailsDTO> getTodayRiderDetailsDTOs() {
		return todayRiderDetailsDTOs;
	}

	public void setTodayRiderDetailsDTOs(List<TodayRiderDetailsDTO> todayRiderDetailsDTOs) {
		this.todayRiderDetailsDTOs = todayRiderDetailsDTOs;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getFilledSeats() {
		return filledSeats;
	}

	public void setFilledSeats(int filledSeats) {
		this.filledSeats = filledSeats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		result = prime * result + ((totalSeats == null) ? 0 : totalSeats.hashCode());
		result = prime * result + ((filledSeats == null) ? 0 : filledSeats.hashCode());
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
		DriverCarPoolDto other = (DriverCarPoolDto) obj;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
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
		if (totalSeats == null) {
			if (other.totalSeats != null)
				return false;
		} else if (!totalSeats.equals(other.totalSeats))
			return false;
		if (filledSeats == null) {
			if (other.filledSeats != null)
				return false;
		} else if (!filledSeats.equals(other.filledSeats))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DriverCarPoolDto [location=" + location + ", fromDate=" + fromDate + ", toDate=" + toDate + ", status="
				+ status + ", mobile=" + mobile + ", name=" + name + ", totalSeats=" + totalSeats + ", filledSeats=" + filledSeats + "]";
	}

}
