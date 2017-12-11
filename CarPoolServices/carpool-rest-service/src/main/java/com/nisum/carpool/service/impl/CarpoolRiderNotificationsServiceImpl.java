package com.nisum.carpool.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.CarpoolRiderNotificationsDAO;
import com.nisum.carpool.data.domain.CarpoolRiderNotifications;
import com.nisum.carpool.service.api.CarpoolRiderNotificationsService;
import com.nisum.carpool.service.dto.CarpoolRiderNotificationsDTO;
import com.nisum.carpool.util.CarpoolRiderNotificationsUtil;

@Service
public class CarpoolRiderNotificationsServiceImpl implements  CarpoolRiderNotificationsService{


	private static final Logger logger = LoggerFactory.getLogger(CarpoolRiderNotificationsServiceImpl.class);

	@Autowired
	CarpoolRiderNotificationsDAO carpoolRiderNotificationsDAO;

	@Override
	public List<CarpoolRiderNotifications> saveRiderNotifications(
			List<CarpoolRiderNotificationsDTO> carpoolRiderNotificationsDTOs) {

		List<CarpoolRiderNotifications> savedRiderNotificationDetails=null;
		List<CarpoolRiderNotifications> carpoolRiderNotifications=	CarpoolRiderNotificationsUtil.convertRiderNotificationDtoToDao(carpoolRiderNotificationsDTOs);

		try
		{
			logger.info("CarpoolRiderNotificationsServiceImpl::saveRiderNotifications");

			if(carpoolRiderNotifications != null && carpoolRiderNotifications.size() !=0)

				savedRiderNotificationDetails = carpoolRiderNotificationsDAO.saveRiderNotification(carpoolRiderNotifications);
		}
		catch (Exception e) {
		}

		return savedRiderNotificationDetails;
	}

}
