package com.nisum.carpool.service.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class RewardPoints {
	@Value("${driver.reward.points}")
	private String driverRewardPoints;
	
	@Value("${rider.reward.points}")
	private String riderRewardPoints;
	
	public String getDriverRewardPoints() {
		return driverRewardPoints;
	}

	public void setDriverRewardPoints(String driverRewardPoints) {
		this.driverRewardPoints = driverRewardPoints;
	}

	public String getRiderRewardPoints() {
		return riderRewardPoints;
	}

	public void setRiderRewardPoints(String riderRewardPoints) {
		this.riderRewardPoints = riderRewardPoints;
	}
	
	
}
