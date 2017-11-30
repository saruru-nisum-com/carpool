package com.nisum.carpool.data.dao.api.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void getVehicleDetails() {
		List<VehicleTypes> vehicleTypeDaoList = new ArrayList<VehicleTypes>();
		VehicleTypes vehicleTypes = new VehicleTypes();
		vehicleTypes.setId(1);
		vehicleTypes.setNoofseats(2);
		vehicleTypes.setVehicletype("Two wheeler");
		vehicleTypeDaoList.add(vehicleTypes);
		when(vehicleTypesRepository.findAll()).thenReturn(vehicleTypeDaoList);
		List<VehicleTypes> actual =vehicleTypesDAOImpl.getVehicleTypes();
		assertEquals(vehicleTypeDaoList, actual);
	}
}
