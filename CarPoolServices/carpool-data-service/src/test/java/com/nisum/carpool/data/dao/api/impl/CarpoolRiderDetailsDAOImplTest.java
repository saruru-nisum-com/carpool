package com.nisum.carpool.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.data.dao.impl.CarpoolRiderDetailsDAOImpl;
import com.nisum.carpool.data.domain.CarpoolRiderNotifications;
import com.nisum.carpool.data.repository.CarpoolRiderDetailsRepository;
import com.nisum.carpool.data.repository.CarpoolRiderNotificationsRepository;
import com.nisum.carpool.data.repository.CarpooldetailsRepository;
import com.nisum.carpool.data.util.Constants;
import com.nisum.carpool.data.util.Pool_Status;
import com.nisum.carpool.data.util.Ride_Status;

@RunWith(MockitoJUnitRunner.class)
public class CarpoolRiderDetailsDAOImplTest {
	@Mock
	CarpoolRiderDetailsRepository carpoolRiderDetailsRepository;
	@InjectMocks
	CarpoolRiderDetailsDAOImpl carpoolRiderDetailsDAOImpl;
	@Mock
	CarpoolRiderNotificationsRepository carpoolRiderNotificationsRepository;
	@Mock
	CarpooldetailsRepository carpooldetailsRepository;
	/**
	* author Mahesh Bheemanapalli
	*/
	@Test
	public void addRewardsTest() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String rewardedDate = currentDate.format(formatter);
		List<Integer> list = Arrays.asList(1,2);
		when(carpooldetailsRepository.getCarpooldetailsByToDateAndStatus(Pool_Status.CLOSED.getValue(), rewardedDate)).thenReturn(list);
		assertEquals(list.size(), 2);
		when(carpoolRiderDetailsRepository.getListOfClosedRiders(Ride_Status.APPROVED.getValue(),1)).thenReturn(1);
		Set<Integer> set=new HashSet<>(list);
		String expected = Constants.ADDED_REWARDS_TO_RIDER;
		when(carpoolRiderDetailsRepository.udpateRiderRewardPoints(1.00, set)).thenReturn(1);
		String actual = carpoolRiderDetailsDAOImpl.addRewards(1.00);
		assertEquals(expected, actual);
	}
	/**
	* author Mahesh Bheemanapalli
	*/
	@Test
	public void addRewardsFailureTest() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String rewardedDate = currentDate.format(formatter);
		List<Integer> list = Arrays.asList(1,2);
		when(carpooldetailsRepository.getCarpooldetailsByToDateAndStatus(Pool_Status.CLOSED.getValue(), rewardedDate)).thenReturn(list);
		assertEquals(list.size(), 2);
		when(carpoolRiderDetailsRepository.getListOfClosedRiders(Ride_Status.APPROVED.getValue(),1)).thenReturn(0);
		Set<Integer> set=new HashSet<>(list);
		String expected = Constants.REWARDS_NOT_ADDED_RIDER;
		when(carpoolRiderDetailsRepository.udpateRiderRewardPoints(1.00, set)).thenReturn(1);
		String actual = carpoolRiderDetailsDAOImpl.addRewards(1.00);
		assertEquals(expected, actual);
	}
	/**
	* author Mahesh Bheemanapalli
	*/
	@Test
	public void cleanCarpoolRiderNotificationsTest() {
		long beforeClean=2;
		long afterClean=0;
		String expected = Constants.CARPOOL_RIDER_NOTIFICATION_CLEANED;
		when(carpoolRiderDetailsRepository.count()).thenReturn(beforeClean);
		when(carpoolRiderNotificationsRepository.CleanCarpoolriderNotifications()).thenReturn(1);
		when(carpoolRiderDetailsRepository.count()).thenReturn(afterClean);
		String carpoolRiderNotifications = carpoolRiderDetailsDAOImpl.cleanCarpoolRiderNotifications();
		assertEquals(expected, carpoolRiderNotifications);
	}
	/**
	* author Mahesh Bheemanapalli
	*/
	@Test
	public void cleanCarpoolRiderNotificationsFailureTest() {
		CarpoolRiderNotifications carpoolRiderNotifications = new CarpoolRiderNotifications();
		carpoolRiderNotifications.setCpid(1);
		carpoolRiderNotifications.setEmailid("mbheemanapalli@nisum.com");
		carpoolRiderNotifications.setId(1);
		carpoolRiderNotifications.setNotified(true);
		long beforeClean=4;
		long afterClean=4;
		
		String expected = Constants.CARPOOL_RIDER_NOTIFICATION_NOT_CLEANED;
		when(carpoolRiderDetailsRepository.count()).thenReturn(beforeClean);
		when(carpoolRiderNotificationsRepository.CleanCarpoolriderNotifications()).thenReturn(0);
		when(carpoolRiderDetailsRepository.count()).thenReturn(afterClean);
		String actual = carpoolRiderDetailsDAOImpl.cleanCarpoolRiderNotifications();
		assertEquals(expected, actual);
	}
}
