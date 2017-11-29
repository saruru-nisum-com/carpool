package com.nisum.carpool.data.util;

public enum Pool_Status {
	
	OPEN(1),
	PARTIALLY_COMPLETED(2),
	COMPLETED(3),
	CLOSED(4);
	
	int value;
	
	Pool_Status(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	

}
