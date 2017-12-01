package com.nisum.carpool.data.util;

public enum Pool_Status {
	
	OPEN(1),
	PARTIALLY_COMPLETED(2),
	COMPLETED(3),
	CLOSED(4);
	
	Integer value;
	
	Pool_Status(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	

}
