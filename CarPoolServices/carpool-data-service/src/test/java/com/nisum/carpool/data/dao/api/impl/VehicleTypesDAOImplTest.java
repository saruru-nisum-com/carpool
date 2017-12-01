package com.nisum.carpool.data.dao.api.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.data.dao.impl.VehicleTypesDAOImpl;
import com.nisum.carpool.data.domain.VehicleTypes;
import com.nisum.carpool.data.repository.VehicleTypesRepository;

@RunWith(MockitoJUnitRunner.class)
public class VehicleTypesDAOImplTest {

	@Mock
	VehicleTypesRepository vehicleTypesRepository;
	@InjectMocks
	VehicleTypesDAOImpl vehicleTypesDAOImpl;
	
	List<VehicleTypes> vehicleTypeDaoList = new ArrayList<VehicleTypes>();
	
	@Before
	public void setUp() {
		VehicleTypes vehicleTypes = new VehicleTypes();
		vehicleTypes.setId(1);
		vehicleTypes.setNoofseats(2);
		vehicleTypes.setVehicletype("Two wheeler");
		vehicleTypeDaoList.add(vehicleTypes);
	}
	@Test
	public void getVehicleDetails() {
		when(vehicleTypesRepository.findAll()).thenReturn(vehicleTypeDaoList);
		List<VehicleTypes> actual =vehicleTypesDAOImpl.getVehicleTypes();
		assertEquals(vehicleTypeDaoList, actual);
	}
}
