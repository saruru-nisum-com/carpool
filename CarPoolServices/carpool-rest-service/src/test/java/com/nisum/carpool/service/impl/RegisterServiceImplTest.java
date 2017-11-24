package com.nisum.carpool.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.service.dto.RegisterDTO;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServiceImplTest {

	@InjectMocks
	RegisterServiceImpl registerServiceImpl;
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

}
