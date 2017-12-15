/**
 * 
 */
package com.nisum.carpool.data.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.carpool.data.dao.api.IDriverPoolHistoryDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.repository.IDriverPoolHistoryRepository;

/**
 * @author vkallada
 * CreatedDate 14th Dec
 */
@Configuration
public class DriverPoolHistoryDAOImpl implements IDriverPoolHistoryDAO {
	
	static final Logger LOGGER = LoggerFactory.getLogger(DriverPoolHistoryDAOImpl.class);

	@Autowired
	IDriverPoolHistoryRepository driverPoolHistoryRepository;
	/**
	 * @author vkallada
	 * Method to fetch the driver pool history data from DB with the user search parameters
	 * @param carpooldetails
	 * @return {@link Carpooldetails}
	 */
	@Override
	public List<Carpooldetails> getDriverPoolHistory(final Carpooldetails carpoolDetails) {
		final String methodName = "[DriverPoolHistoryDAOImpl :: getDriverPoolHistory]";
		LOGGER.debug(methodName+" Started");
		List<Carpooldetails> driverPoolHistoryData = Collections.emptyList();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = format.format(new Date());
		try {
			driverPoolHistoryData = driverPoolHistoryRepository.getCarPoolsByEmail(carpoolDetails.getEmailId(), currentDate);
			LOGGER.debug("Size of Driver pool history data :: "+driverPoolHistoryData.size());
		} catch (Exception e) {
			LOGGER.error("Exception fetching the driver pool history :: ", e);
		}
		LOGGER.debug(methodName+" End");
		return driverPoolHistoryData;
	}

}
