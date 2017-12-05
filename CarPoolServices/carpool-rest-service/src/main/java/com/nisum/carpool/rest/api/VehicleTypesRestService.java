package com.nisum.carpool.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.service.api.VehicleTypesService;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.VehicleTypesDTO;
import com.nisum.carpool.service.exception.VehicleTypesException;
import com.nisum.carpool.util.Constants;

@RestController
@RequestMapping(value="/v1/carpool")
public class VehicleTypesRestService {
	
	private static Logger logger = LoggerFactory.getLogger(VehicleTypesRestService.class);
	@Autowired
	VehicleTypesService vehicleTypesService;
	
	/**
	 * @author durga manjari narni
	 * To get list of available vehicle details
	 * @return list of available vehicle details
	 * @throws VehicleTypesException
	 */
	@RequestMapping(value="/getVehicleTypes", method=RequestMethod.GET)
	public ResponseEntity<?> getVehicleTypes() throws VehicleTypesException {
		logger.info("Enter into VehicleTypesRestService :: getVehicleTypes :: get all vehicle details");
		Errors error = new Errors();
		try {
			List<VehicleTypesDTO> vehicleDetails = vehicleTypesService.getVehicleTypes();
			System.out.println(vehicleDetails);
			if (vehicleDetails != null && vehicleDetails.size() > 0) {
				System.out.println("in if");
				logger.info("Exit from VehicleTypesRestService :: getVehicleTypes :: get all vehicle details");
				return new ResponseEntity<List<VehicleTypesDTO>>(vehicleDetails, HttpStatus.OK);
			} else {
				System.out.println("in else");
				error.setErrorCode("204");
				error.setErrorMessage(Constants.NO_RECORDS_FOUND);
				logger.info("Exit from VehicleTypesRestService :: getVehicleTypes :: get all vehicle details");
				return new ResponseEntity<Errors>(error, HttpStatus.NO_CONTENT);
			}
		} catch(Exception e) {
			logger.info("VehicleTypesRestService :: getVehicleTypes::Internal server error");
			throw new VehicleTypesException(e);
		}
		
		
	}
	
	/**
	 * exceptionHandler
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(VehicleTypesException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		Errors errors = new Errors();

		errors.setErrorCode("Errors-VehicleDetails");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
