package com.nisum.carpool.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.carpool.service.api.RegisterService;
import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.exception.UserServiceException;
import com.nisum.carpool.service.impl.RegisterServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RegisterRestServiceTest {
	@InjectMocks
	private RegisterRestService registerRestService;

	@Mock
	private RegisterServiceImpl registerServiceImpl;

	/**
	 * @author Nisum test case to fetch list of registered users from service to
	 *         rest
	 * @throws UserServiceException
	 */
	@Test
	public void getUserProfile() throws UserServiceException {

		List<RegisterDTO> regList = new ArrayList<>();
		RegisterDTO registerDto = new RegisterDTO();
		registerDto.setEmailId("user1");
		registerDto.setEmailNotification(true);
		registerDto.setIsRider(1);
		registerDto.setLocation("HYD");
		registerDto.setLatitude("45");
		registerDto.setLongitude("34");
		registerDto.setMobile("1234567891");
		registerDto.setNearby("NAMPALLY");
		registerDto.setRegistrationId(1);
		List<Integer> vehicleType = new ArrayList<>();
		vehicleType.add(0, Integer.valueOf(2));
		vehicleType.add(1, Integer.valueOf(4));
		registerDto.setVehicleType(vehicleType);
		regList.add(registerDto);

		ResponseEntity<List<RegisterDTO>> expected = new ResponseEntity<>(regList, HttpStatus.OK);

		String emailId = "user1";
		RegisterDTO regDto = new RegisterDTO();
		regDto.setEmailId(emailId);

		when(registerServiceImpl.getUserRegistrationProfile(regDto)).thenReturn(regList);

		ResponseEntity<List<RegisterDTO>> actual = registerRestService.getUserProfile(emailId);

		assertEquals(expected.getStatusCode(), actual.getStatusCode());
	}

}
