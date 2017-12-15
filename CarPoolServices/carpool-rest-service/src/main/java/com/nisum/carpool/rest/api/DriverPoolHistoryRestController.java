package com.nisum.carpool.rest.api;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.data.util.Constants;
import com.nisum.carpool.service.api.IDriverPoolHistoryService;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.exception.CarpooldetailsServiceException;

/**
 * @author vkallada
 * CreatedDate 14th Dec.
 */
@RestController
@RequestMapping(value = "/v1/carpool")
public class DriverPoolHistoryRestController {
	
	static final Logger LOGGER = LoggerFactory.getLogger(DriverPoolHistoryRestController.class);

	
	@Autowired
	IDriverPoolHistoryService driverPoolHistoryService;
	
	/**
	 * @author vkallada
	 * Rest method to fetch all the pools posted by driver
	 * @param carpoolDetailsDTO
	 * @return driverPoolHistoryData
	 * @throws CarpooldetailsServiceException
	 */
	@RequestMapping(value = "/driverPoolHistory/{emailId:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> getdriverPoolHistory(@PathVariable String emailId)
			throws CarpooldetailsServiceException {
		final String methodName = "[DriverPoolHistoryService :: getdriverPoolHistory]";
		ResponseEntity<?> responseEntity = null;
		LOGGER.debug(methodName+" Started");
		CarpooldetailsDto carpoolDetailsDTO = new CarpooldetailsDto();
		carpoolDetailsDTO.setEmailId(emailId);
		try {
			if (StringUtils.isEmpty(carpoolDetailsDTO.getEmailId())) {
				throw new CarpooldetailsServiceException("Invalid EmailId");
			}
			List<CarpooldetailsDto> driverPoolHistoryData = driverPoolHistoryService
					.getDriverPoolHistory(carpoolDetailsDTO);
			LOGGER.debug("Driver pool history size :: " + driverPoolHistoryData.size());

			responseEntity = new ResponseEntity<List<CarpooldetailsDto>>(driverPoolHistoryData, HttpStatus.OK);
		} catch (CarpooldetailsServiceException cdse) {
			Errors error = new Errors();
			error.setErrorCode("204");
			error.setErrorMessage(cdse.getMessage());

			responseEntity = new ResponseEntity<Errors>(error, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Error while fetching the driver pool histroy ", e);
			Errors error = new Errors();
			error.setErrorCode("400");
			error.setErrorMessage(Constants.ERROR_MESSAGE);

			responseEntity = new ResponseEntity<Errors>(error, HttpStatus.BAD_REQUEST);
		}
				
		LOGGER.debug(methodName+" End");
		return responseEntity;
	}

}
