package com.nisum.carpool.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.data.dao.api.CarpoolRiderDetailsDAO;
import com.nisum.carpool.data.util.Constants;
import com.nisum.carpool.service.dto.ServiceStatusDto;
@RunWith(MockitoJUnitRunner.class)
public class CarPoolRiderDetailsServiceImplTest {

	@InjectMocks
	CarPoolRiderDetailsServiceImpl carPoolRiderDetailsServiceImpl;
	
	@Mock
	CarpoolRiderDetailsDAO carpoolRiderDetailsDAO;
	
	/**
	 * @author Mahesh Bheemanapalli
	 * 
	 * Test cases to update rewards in CarpoolRiderDetails
	 */
	@Test
	public void addRewardsTest() {
		double rewards=1.00;
		String statusMessage = Constants.ADDED_REWARDS_TO_DRIVER;
		ServiceStatusDto expected = new ServiceStatusDto();
		expected.setStatus(true);
		expected.setMessage(statusMessage);
		when(carpoolRiderDetailsDAO.addRewards(rewards)).thenReturn(statusMessage);
		ServiceStatusDto actual = carPoolRiderDetailsServiceImpl.addRewards(rewards);
		assertEquals(expected.getMessage(), actual.getMessage());
	}
	/**
	 * @author Mahesh Bheemanapalli
	 */
	@Test
	public void cleanCarpoolRiderNotificationsTest() {
		String notificationCleaned = Constants.CARPOOL_RIDER_NOTIFICATION_CLEANED;
		when(carpoolRiderDetailsDAO.cleanCarpoolRiderNotifications()).thenReturn(notificationCleaned);
		ServiceStatusDto expected = new ServiceStatusDto();
		expected.setStatus(true);
		expected.setMessage(notificationCleaned);
		ServiceStatusDto actual = carPoolRiderDetailsServiceImpl.cleanCarpoolRiderNotifications();
		assertEquals(expected.getMessage(), actual.getMessage());
	}
}
