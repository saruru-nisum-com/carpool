package com.nisum.carpool.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.carpool.service.api.VehicleTypesService;
import com.nisum.carpool.service.dto.VehicleTypesDTO;
import com.nisum.carpool.service.exception.VehicleTypesException;

@RunWith(MockitoJUnitRunner.class)
public class VehicleTypeRestServiceTest {

	@Mock
	VehicleTypesService vehicleTypesService;
	@InjectMocks
	VehicleTypesRestService vehicleTypesRestService;
	
	@Test
	public void getVehicleTpyes() throws VehicleTypesException {
		List<VehicleTypesDTO> vehicleTypeDtoList = new ArrayList<VehicleTypesDTO>();
		VehicleTypesDTO vehicleTypesDTO = new VehicleTypesDTO();
		vehicleTypesDTO.setId(1);
		vehicleTypesDTO.setNoofseats(2);
		vehicleTypesDTO.setVehicletype("Two wheeler");
		vehicleTypeDtoList.add(vehicleTypesDTO);
		ResponseEntity<List<VehicleTypesDTO>> expected = new ResponseEntity<List<VehicleTypesDTO>>(vehicleTypeDtoList,HttpStatus.OK);
		when(vehicleTypesService.getVehicleTypes()).thenReturn(vehicleTypeDtoList);
		ResponseEntity<?> actual = vehicleTypesRestService.getVehicleTypes();
		assertEquals(expected, actual);
	}
}
