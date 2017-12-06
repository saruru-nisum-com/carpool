package com.nisum.carpool.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.nisum.carpool.data.domain.Carpooldetails;

public class CustomerCarpooldetailsDto {
	private String userName;
	private String location;
	CarpooldetailsDto carpoolDetails;
	/**
	 * @return the carpoolDetails
	 */
	public CarpooldetailsDto getCarpoolDetails() {
		return carpoolDetails;
	}

	/**
	 * @param carpoolDetails the carpoolDetails to set
	 */
	public void setCarpoolDetails(CarpooldetailsDto carpoolDetails) {
		this.carpoolDetails = carpoolDetails;
	}

	public CustomerCarpooldetailsDto()
	{
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carpoolDetails == null) ? 0 : carpoolDetails.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		CustomerCarpooldetailsDto other = (CustomerCarpooldetailsDto) obj;
		if (carpoolDetails == null) {
			if (other.carpoolDetails != null)
				return false;
		} else if (!carpoolDetails.equals(other.carpoolDetails))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerCarpooldetailsDto [userName=" + userName + ", location=" + location + ", carpoolDetails="
				+ carpoolDetails + "]";
	}

}
