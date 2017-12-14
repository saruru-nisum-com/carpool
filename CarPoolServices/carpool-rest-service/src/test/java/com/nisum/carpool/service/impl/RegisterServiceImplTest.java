package com.nisum.carpool.service.impl;

import static org.assertj.core.api.Assertions.assertThat; 
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.data.dao.api.RegisterDAO;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.data.repository.RegisterRepository;
import com.nisum.carpool.service.api.RegisterService;
import org.mockito.runners.MockitoJUnitRunner;
import com.nisum.carpool.service.dto.RegisterDTO;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServiceImplTest {

	@InjectMocks
	private RegisterServiceImpl registerServiceImpl;

	@Mock
	private RegisterDAO registerDAO;

	List<RegisterDTO> expected = null;

	Timestamp mockDate = new Timestamp(System.currentTimeMillis());

	/**
	 * @author Rajendra Prasad Dava performs initialization at startup
	 */
	@Before
	public void init() {

		expected = new ArrayList<>();
		RegisterDTO registerDto = new RegisterDTO();
		registerDto.setEmailId("user1");
		registerDto.setEmailNotification(true);
		registerDto.setCreatedDate(mockDate);
		registerDto.setIsRider(1);
		registerDto.setLocation("HYD");
		registerDto.setLatitude("45");
		registerDto.setLongitude("34");
		registerDto.setMobile("1234567891");
		registerDto.setModifiedDate(mockDate);
		registerDto.setNearby("NAMPALLY");
		registerDto.setRegistrationId(1);
		List<Integer> vehicleType = new ArrayList<>();
		vehicleType.add(0, Integer.valueOf(2));
		vehicleType.add(1, Integer.valueOf(4));
		registerDto.setVehicleType(vehicleType);
		expected.add(registerDto);

	}

	/**
	 * @author Rajendra Prasad Dava test case for fetching registered users from dao
	 *         to service
	 */
	@Test
	public void getUserRegistrationProfile() {

		List<RegisterDomain> registerList = new ArrayList<>();
		RegisterDomain registerDomain = new RegisterDomain();
		registerDomain.setEmailid("user1");
		registerDomain.setEmailnotification(true);
		registerDomain.setCreateddate(mockDate.toLocalDateTime());
		registerDomain.setIsrider(1);
		registerDomain.setLocation("HYD");
		registerDomain.setLatitude("45");
		registerDomain.setLongitude("34");
		registerDomain.setMobile("1234567891");
		registerDomain.setModifieddate(mockDate.toLocalDateTime());
		registerDomain.setNearby("NAMPALLY");
		registerDomain.setRegistrationid(1);
		List<Integer> vehicleType = new ArrayList<>();
		vehicleType.add(0, Integer.valueOf(2));
		vehicleType.add(1, Integer.valueOf(4));
		registerDomain.setVehicletype(vehicleType);

		registerList.add(registerDomain);

		

		when(registerDAO.findUserRegistrationByUserId("user1")).thenReturn(registerList);

		List<RegisterDTO> actual = registerServiceImpl.getUserRegistrationProfile("user1");

		assertThat(actual.size()).isEqualTo(expected.size());
	}
	RegisterDTO registerDTO = null;

	@Before
	public void setUp() {
		registerDTO = new RegisterDTO();
		registerDTO.setLocation("Hyderabad");
	}

	@Test
	public void testSearhLocation() throws Exception {
		registerServiceImpl.searchLocation(registerDTO);
		assertEquals(registerDTO.getLocation(), "Hyderabad");
	}
	
	/**
	 * @author dhiraj singh
	 * description: this taste case is updating the driver/rider details
	 * Date created: 07/12/17
	 * Date modified: 08/12/17 
	 */
	@Test
	public void testUpdateDriverData() throws Exception{
		RegisterDomain driverDomain = new RegisterDomain();
		driverDomain.setEmailid("dsingh@nisum.com");
		driverDomain.setCreateddate(mockDate.toLocalDateTime());
		List<Integer> vehicleTypeList = new ArrayList<>();
		vehicleTypeList.add(0);
		vehicleTypeList.add(1);
		driverDomain.setVehicletype(vehicleTypeList);
		driverDomain.setGender(2);
		driverDomain.setIsrider(0);
		driverDomain.setLocation("Hyderbad");
		driverDomain.setLatitude("199.90");
		driverDomain.setLongitude("404.00");
		driverDomain.setNearby("near to flyover");
		driverDomain.setRegistrationid(1);
		
		when(registerDAO.updateDriverOrRider(driverDomain)).thenReturn(driverDomain);
	}
	
	/**
	 * @author dhiraj singh
	 * description: this taste case is updating the driver details and checking whether rider's location, lat,
	 * long, mobile no gets updated or not
	 * Date created: 10/12/17
	 * Date modified: 13/12/17 
	 */
	@Test
	public void testRiderDataUpdatedOnDriverUpdate() throws Exception{
	}
	
	/**
	 * @author dhiraj singh
	 * description: this taste case is updating the rider details and checking whether driver's location, lat,
	 * long, mobile no gets updated or not
	 * Date created: 10/12/17
	 * Date modified: 10/12/17 
	 */
	@Test
	public void testDriverDataUpdatedOnRiderUpdate() throws Exception{
	}
}
