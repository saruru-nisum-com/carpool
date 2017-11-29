package com.nisum.carpool.data.util;

public enum Reject_Reason {
	
	ON_VACATION(1),
	VEHICLE_NOT_AVAILABLE(2),
	RIDE_ROUTE_CHANGED(3),
	WORK_TIMINGS_CHANGED(4),
	UNABLE_TO_SHARE_RIDE(5),
	OTHER_REASON(6);
	
	int value;
	
	Reject_Reason(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	

}
