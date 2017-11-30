package com.nisum.carpool.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void getVehicleTypesImpl() {
		List<VehicleTypes> vehicleTypeDaoList = new ArrayList<VehicleTypes>();
		VehicleTypes vehicleTypes = new VehicleTypes();
		vehicleTypes.setId(1);
		vehicleTypes.setNoofseats(2);
		vehicleTypes.setVehicletype("Two wheeler");
		vehicleTypeDaoList.add(vehicleTypes);
		
		List<VehicleTypesDTO> vehicleTypeDtoList = new ArrayList<VehicleTypesDTO>();
		VehicleTypesDTO vehicleTypesDTO = new VehicleTypesDTO();
		vehicleTypesDTO.setId(1);
		vehicleTypesDTO.setNoofseats(2);
		vehicleTypesDTO.setVehicletype("Two wheeler");
		vehicleTypeDtoList.add(vehicleTypesDTO);
		
		when(vehicleTypesDAO.getVehicleTypes()).thenReturn(vehicleTypeDaoList);
		List<VehicleTypesDTO> vehicleTypesDTOUtil = VehicleTypesUtil.convertDaoToDto(vehicleTypeDaoList);
		List<VehicleTypesDTO> actual = vehicleTypesServiceImpl.getVehicleTypes();
		assertEquals(vehicleTypesDTOUtil, actual);
	}
}
