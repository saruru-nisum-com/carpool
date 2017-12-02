package com.nisum.carpool.data.util;

public enum Ride_Status {
	
	REQUESTED(1),
	APPROVED(2),
	REJECTED(3),
	CANCELLED(4);
	
	int value;
	
	Ride_Status(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

}
