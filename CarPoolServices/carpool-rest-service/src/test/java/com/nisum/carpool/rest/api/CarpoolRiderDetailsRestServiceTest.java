/**
 * 
 */
package com.nisum.carpool.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.api.RewardPoints;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.ServiceStatusDto;

/**
 * @author Lakshmi Kiran
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CarpoolRiderDetailsRestServiceTest {

	@InjectMocks
	CarpoolRiderDetailsRestService carpoolRiderDetailsRestService;

	@Mock
	CarpoolRiderDetailsService carpoolRiderDetailsService;
	
	@Mock
	RewardPoints rewardPoints;

	@Test
	public void findCarpoolRiderDetailsByCPIdTest() {
		List<CarpoolRiderDetailsDTO> list = new ArrayList<>();
		CarpoolRiderDetailsDTO riderDetailsDTO = new CarpoolRiderDetailsDTO();
		riderDetailsDTO.setCpid(1);
		riderDetailsDTO.setCreateddate(new Timestamp(1512049465));
		riderDetailsDTO.setEmailid("ltirumallasetti@nisum.com0");
		riderDetailsDTO.setId(1);
		riderDetailsDTO.setModifieddate(new Timestamp(1512049465));
		riderDetailsDTO.setNotifyme(false);
		riderDetailsDTO.setReason(1);
		riderDetailsDTO.setRewards(2.0);
		riderDetailsDTO.setStatus(1);
		list.add(riderDetailsDTO);
		
		ResponseEntity<List<CarpoolRiderDetailsDTO>> expected = new ResponseEntity<List<CarpoolRiderDetailsDTO>>(list, HttpStatus.OK);
		when(carpoolRiderDetailsService.findCarpoolRiderDetailsByCPId(1)).thenReturn(list);
		ResponseEntity<List<CarpoolRiderDetailsDTO>> actual = carpoolRiderDetailsRestService.findCarpoolRiderDetailsByCPId(1);
		assertEquals(expected, actual);
	}
	/**
	 * @author Mahesh Bheemanapalli
	 */
	@Test
	public void addRewardsToRiderTest() {
		String riderRewardPoints="0.25";
		double rewards = 0.25;
		ServiceStatusDto statusDto = new ServiceStatusDto();
		statusDto.setStatus(true);
		statusDto.setMessage("Reward Points added to Rider Successfully !!");
		when(carpoolRiderDetailsService.addRewards(rewards)).thenReturn(statusDto);
		ResponseEntity<ServiceStatusDto> expected = new ResponseEntity<ServiceStatusDto>(statusDto,HttpStatus.OK);
		when(rewardPoints.getRiderRewardPoints()).thenReturn(riderRewardPoints);
		

		ResponseEntity<?> actual = carpoolRiderDetailsRestService.addRewardPointsToRider();
		//assertEquals(expected.getStatusCode(), actual.getStatusCode());
		System.out.println("actual :"+actual);
		assertEquals(expected.getBody(), actual.getBody());
	}
	/**
	 * @author Mahesh Bheemanapalli
	 */
	@Test
	public void addRewardsToRiderFailureTest() {
		double rewards=0.25;
		when(carpoolRiderDetailsService.addRewards(rewards)).thenThrow(NullPointerException.class);
		Errors error = new Errors();
		error.setErrorCode("BAD REQUEST");
		error.setErrorMessage("Reward Points are not added to Rider !!");
		ResponseEntity<?> expected = new ResponseEntity<Errors>(error,HttpStatus.NOT_ACCEPTABLE);
		ResponseEntity<?> actual = carpoolRiderDetailsRestService.addRewardPointsToRider();
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
	}
	/**
	 * @author Mahesh Bheemanapalli
	 */
	@Test
	public void cleanCarpoolRiderNotificationsTest() {
		ServiceStatusDto statusDto=new ServiceStatusDto();
		statusDto.setStatus(true);
		statusDto.setMessage("CarpoolriderNotifications Cleaned Successfully !!");
		when(carpoolRiderDetailsService.cleanCarpoolRiderNotifications()).thenReturn(statusDto);
		ResponseEntity<ServiceStatusDto> expected = new ResponseEntity<ServiceStatusDto>(statusDto,HttpStatus.OK);
		ResponseEntity<?> actual = carpoolRiderDetailsRestService.cleanCarpoolRiderNotifications();
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertEquals(expected.getBody(), actual.getBody());
	}
	/**
	 * @author Mahesh Bheemanapalli
	 */
	@Test
	public void cleanCarpoolRiderNotificationsFailureTest() {
		Errors error = new Errors();
		error.setErrorCode("500");
		error.setErrorMessage(null);
		when(carpoolRiderDetailsService.cleanCarpoolRiderNotifications()).thenThrow(NullPointerException.class);
		ResponseEntity<Errors> expected = new ResponseEntity<Errors>(error,HttpStatus.NOT_ACCEPTABLE);
		ResponseEntity<?> actual = carpoolRiderDetailsRestService.cleanCarpoolRiderNotifications();
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
	}
}
