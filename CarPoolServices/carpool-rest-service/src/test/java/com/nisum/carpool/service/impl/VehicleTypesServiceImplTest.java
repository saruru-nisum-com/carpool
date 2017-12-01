package com.nisum.carpool.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.data.dao.api.VehicleTypesDAO;
import com.nisum.carpool.data.domain.VehicleTypes;
import com.nisum.carpool.service.dto.VehicleTypesDTO;
import com.nisum.carpool.util.VehicleTypesUtil;

@RunWith(MockitoJUnitRunner.class)
public class VehicleTypesServiceImplTest {

	@Mock
	VehicleTypesDAO vehicleTypesDAO;
	@InjectMocks
	VehicleTypesServiceImpl vehicleTypesServiceImpl;
	
	List<VehicleTypes> vehicleTypeDaoList = new ArrayList<VehicleTypes>();
	List<VehicleTypesDTO> vehicleTypeDtoList = new ArrayList<VehicleTypesDTO>();
	
	@Before
	public void setUp() {
		VehicleTypes vehicleTypes = new VehicleTypes();
		VehicleTypesDTO vehicleTypesDTO = new VehicleTypesDTO();
		
		vehicleTypes.setId(1);
		vehicleTypes.setNoofseats(2);
		vehicleTypes.setVehicletype("Two wheeler");
		vehicleTypeDaoList.add(vehicleTypes);
		
		vehicleTypesDTO.setId(1);
		vehicleTypesDTO.setNoofseats(2);
		vehicleTypesDTO.setVehicletype("Two wheeler");
		vehicleTypeDtoList.add(vehicleTypesDTO);
	}
	
	@Test
	public void getVehicleTypesImpl() {
		when(vehicleTypesDAO.getVehicleTypes()).thenReturn(vehicleTypeDaoList);
		List<VehicleTypesDTO> vehicleTypesDTOUtil = VehicleTypesUtil.convertDaoToDto(vehicleTypeDaoList);
		List<VehicleTypesDTO> actual = vehicleTypesServiceImpl.getVehicleTypes();
		assertEquals(vehicleTypesDTOUtil, actual);
	}
}
