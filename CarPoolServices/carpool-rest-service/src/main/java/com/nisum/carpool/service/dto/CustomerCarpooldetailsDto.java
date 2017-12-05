package com.nisum.carpool.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.nisum.carpool.data.domain.Carpooldetails;

public class CustomerCarpooldetailsDto {
	private String userName;
	private String location;
	private String mobile;
	List<CarpooldetailsDto> listCarpoolDetails;
	public CustomerCarpooldetailsDto()
	{
		listCarpoolDetails=new ArrayList<>();
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<CarpooldetailsDto> getListCarpoolDetails() {
		return listCarpoolDetails;
	}

	public void setListCarpoolDetails(List<CarpooldetailsDto> listCarpoolDetails) {
		this.listCarpoolDetails = listCarpoolDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listCarpoolDetails == null) ? 0 : listCarpoolDetails.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		CustomerCarpooldetailsDto other = (CustomerCarpooldetailsDto) obj;
		if (listCarpoolDetails == null) {
			if (other.listCarpoolDetails != null)
				return false;
		} else if (!listCarpoolDetails.equals(other.listCarpoolDetails))
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
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerCarpooldetailsDto [userName=" + userName + ", location=" + location + ", mobile=" + mobile
				+ ", listCarpoolDetails=" + listCarpoolDetails + "]";
	}

}
