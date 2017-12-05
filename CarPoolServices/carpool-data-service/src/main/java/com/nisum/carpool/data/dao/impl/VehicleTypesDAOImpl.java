package com.nisum.carpool.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.nisum.carpool.data.dao.api.VehicleTypesDAO;
import com.nisum.carpool.data.domain.VehicleTypes;
import com.nisum.carpool.data.repository.VehicleTypesRepository;

@Configuration
public class VehicleTypesDAOImpl implements VehicleTypesDAO{

	private static Logger logger = LoggerFactory.getLogger(VehicleTypesDAOImpl.class);
	@Autowired
	VehicleTypesRepository vehicleTypesRepository;
	
	/**
	 * @author durga manjari narni
	 * Finds and returns list of vehicle details
	 */
	public List<VehicleTypes> getVehicleTypes() {
		logger.info("VehicleTypesDAOImpl :: getVehicleTypes :: finding all vehicle details");
		try {
			return (List<VehicleTypes>) vehicleTypesRepository.findAll();
		} catch(Exception e) {
			logger.info("Entred into VehicleTypesDAOImpl Exception :: getVehicleTypes :: Exception occurred");
			return null;
		}
	}

}
