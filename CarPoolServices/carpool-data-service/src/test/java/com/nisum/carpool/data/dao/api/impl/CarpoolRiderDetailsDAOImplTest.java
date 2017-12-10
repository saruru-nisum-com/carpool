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
import com.nisum.carpool.data.repository.CarpoolRiderDetailsRepository;
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
	CarpooldetailsRepository carpooldetailsRepository;
	
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
		when(carpoolRiderDetailsRepository.udpateRiderRewardPoints(1.00, set)).thenReturn(expected);
		String actual = carpoolRiderDetailsDAOImpl.addRewards(1.00);
		assertEquals(expected, actual);
	}
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
		when(carpoolRiderDetailsRepository.udpateRiderRewardPoints(1.00, set)).thenReturn(expected);
		String actual = carpoolRiderDetailsDAOImpl.addRewards(1.00);
		assertEquals(expected, actual);
	}
}
