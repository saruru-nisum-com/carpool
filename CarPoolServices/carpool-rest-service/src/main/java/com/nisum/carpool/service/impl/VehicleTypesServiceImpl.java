package com.nisum.carpool.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.VehicleTypesDAO;
import com.nisum.carpool.data.domain.VehicleTypes;
import com.nisum.carpool.service.api.VehicleTypesService;
import com.nisum.carpool.service.dto.VehicleTypesDTO;
import com.nisum.carpool.util.VehicleTypesUtil;

@Service
public class VehicleTypesServiceImpl implements VehicleTypesService{
	
	private static Logger logger = LoggerFactory.getLogger(VehicleTypesServiceImpl.class);
	@Autowired
	VehicleTypesDAO vehicleTypesDAO;

	/**
	 * @author durga manjari narni
	 * To get all registered vehicle details 
	 */
	@Override
	public List<VehicleTypesDTO> getVehicleTypes() {
		logger.info("Enter into VehicleTypesServiceImpl :: getVehicleTypes :: get all vehicle details");
		List<VehicleTypes> vehiclesTypesDao = null;
		try {
			vehiclesTypesDao = vehicleTypesDAO.getVehicleTypes();
		} catch(Exception e) {
			logger.info("Entered  into VehicleTypesServiceImpl Exception :: getVehicleTypes :: Error occurred");
			e.printStackTrace();
		}
		logger.info("Exit from VehicleTypesServiceImpl :: getVehicleTypes :: get all vehicle details");
		return VehicleTypesUtil.convertDaoToDto(vehiclesTypesDao);
	}

}
