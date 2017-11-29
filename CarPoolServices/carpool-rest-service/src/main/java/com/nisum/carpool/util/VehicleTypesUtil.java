package com.nisum.carpool.util;

import java.util.ArrayList;
import java.util.List;

import com.nisum.carpool.data.domain.VehicleTypes;
import com.nisum.carpool.service.dto.VehicleTypesDTO;

public class VehicleTypesUtil {
	
	/**
	 * @author durga manjari narni
	 * Converts DAO object values to DTo object values
	 * @param vehicleTypesDao
	 * @return list of vehicleTypesDto
	 */
	public static List<VehicleTypesDTO> convertDaoToDto(List<VehicleTypes> vehicleTypesDao) {
		
		List<VehicleTypesDTO> vehicleTypesDto = new ArrayList<VehicleTypesDTO>();
		for(VehicleTypes vehicleType:vehicleTypesDao) {
			VehicleTypesDTO vehicleTypes = new VehicleTypesDTO();
			vehicleTypes.setId(vehicleType.getId());
			vehicleTypes.setNoofseats(vehicleType.getNoofseats());
			vehicleTypes.setVehicletype(vehicleType.getVehicletype());
			vehicleTypesDto.add(vehicleTypes);
		}
		return vehicleTypesDto;
		
		
	}
}
