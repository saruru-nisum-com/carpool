package com.nisum.carpool.service.dto;

public class OptRideDto {

	private Integer optedSeats;
	private Integer totalSeats;
	private String date;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOptedSeats() {
		return optedSeats;
	}

	public void setOptedSeats(Integer optedSeats) {
		this.optedSeats = optedSeats;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "OptRideDto [optedSeats=" + optedSeats + ", totalSeats=" + totalSeats + ", date=" + date + ", status="
				+ status + "]";
	}

	
}
