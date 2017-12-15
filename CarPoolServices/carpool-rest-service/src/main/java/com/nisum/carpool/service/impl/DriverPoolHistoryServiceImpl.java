/**
 * 
 */
package com.nisum.carpool.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.IDriverPoolHistoryDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.service.api.IDriverPoolHistoryService;
import com.nisum.carpool.service.dto.CarpooldetailsDto;

/**
 * @author vkallada CreatedDate 14th Dec
 */
@Service
public class DriverPoolHistoryServiceImpl implements IDriverPoolHistoryService {

	static final Logger LOGGER = LoggerFactory.getLogger(DriverPoolHistoryServiceImpl.class);

	@Autowired
	IDriverPoolHistoryDAO driverPoolHistoryDAO;

	/**
	 * @author vkallada Service method to fetch and build the driver pool
	 *         history with different search parameters.
	 * @param carpooldetailsDto
	 * @return {@link CarpooldetailsDto}
	 */
	@Override
	public List<CarpooldetailsDto> getDriverPoolHistory(CarpooldetailsDto carpoolDetailsDTO) {
		final String methodName = "[DriverPoolHistoryServiceImpl :: getDriverPoolHistory]";
		LOGGER.debug(methodName + " Started");
		List<CarpooldetailsDto> driverPoolHistoryResDTO = Collections.emptyList();
		try {
			//Building the request object
			Carpooldetails carpooldetails = buildUserSearchParamsData(carpoolDetailsDTO);

			List<Carpooldetails> driverPoolHistoryData = driverPoolHistoryDAO.getDriverPoolHistory(carpooldetails);
			
			driverPoolHistoryResDTO = buildDriverPoolHistoryRes(driverPoolHistoryData);
			LOGGER.debug("Size of the driver pool history :: " + driverPoolHistoryData.size());
		} catch (Exception e) {
			LOGGER.error("Error fetching the driver pool histor @ service layer ", e);
		}

		LOGGER.debug(methodName + " End");

		return driverPoolHistoryResDTO;
	}

	/**
	 * @author vkallada Method to build the request parameters to fetch the
	 *         driver pool history
	 * @param carpoolDetailsDTO
	 * @return carpoolDetails
	 */
	private Carpooldetails buildUserSearchParamsData(CarpooldetailsDto carpoolDetailsDTO) {

		Carpooldetails carpoolDetails = new Carpooldetails();
		final String emailId = carpoolDetailsDTO.getEmailId();
		final String fromDate = carpoolDetailsDTO.getFromDate();
		final String toDate = carpoolDetailsDTO.getToDate();
//		final int status = carpoolDetailsDTO.getStatus();
		final String location = carpoolDetailsDTO.getLocation();
		
		// TODO: Replace the below object build with ORIKA mapping.
		if (null != emailId)
			carpoolDetails.setEmailId(emailId);
		if (null != fromDate)
			carpoolDetails.setFromDate(fromDate);
		if (null != toDate)
			carpoolDetails.setToDate(toDate);
		if (null != location)
			carpoolDetails.setLocation(location);
//		carpoolDetails.setStatus(status);

		return carpoolDetails;
	}

	/**
	 * @author vkallada 
	 * Method to build the Driver pool history response data
	 * @param driverPoolHistoryData
	 * @return driverPoolHistoryDataRes
	 */
	private List<CarpooldetailsDto> buildDriverPoolHistoryRes(List<Carpooldetails> driverPoolHistoryData) {
		final String methodName = "[DriverPoolHistoryServiceImpl :: buildDriverPoolHistoryRes]";
		LOGGER.debug(methodName + " Started");
		List<CarpooldetailsDto> driverPoolHistoryDataRes = new ArrayList<>();
		for (Carpooldetails carpoolsDetailsObj : driverPoolHistoryData) {
			CarpooldetailsDto carpoolDetailsResObj = new CarpooldetailsDto();
			
			carpoolDetailsResObj.setEmailId(carpoolsDetailsObj.getEmailId());
			carpoolDetailsResObj.setLocation(carpoolsDetailsObj.getLocation());
			carpoolDetailsResObj.setFromDate(carpoolsDetailsObj.getFromDate());
			carpoolDetailsResObj.setToDate(carpoolsDetailsObj.getToDate());
			carpoolDetailsResObj.setStatus(carpoolsDetailsObj.getStatus());
			
			driverPoolHistoryDataRes.add(carpoolDetailsResObj);
		}
		LOGGER.debug(methodName + " End");
		return driverPoolHistoryDataRes;
	}

}
