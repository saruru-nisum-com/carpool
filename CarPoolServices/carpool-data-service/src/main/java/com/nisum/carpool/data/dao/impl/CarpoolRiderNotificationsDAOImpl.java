package com.nisum.carpool.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.carpool.data.dao.api.CarpoolRiderNotificationsDAO;
import com.nisum.carpool.data.domain.CarpoolRiderNotifications;
import com.nisum.carpool.data.repository.CarpoolRiderNotificationsRepository;
@Configuration
public class CarpoolRiderNotificationsDAOImpl implements CarpoolRiderNotificationsDAO{

	private Logger logger=LoggerFactory.getLogger(CarpoolRiderNotificationsDAOImpl.class);
	
	@Autowired
	CarpoolRiderNotificationsRepository carpoolRiderNotificationsRepository;
	@Override
	public List<CarpoolRiderNotifications> saveRiderNotification(
			List<CarpoolRiderNotifications> carpoolRiderNotifications) {
		try {
			logger.info("CarpoolRiderNotificationsDAOImpl::saveRiderNotification");
			carpoolRiderNotificationsRepository.save(carpoolRiderNotifications);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return carpoolRiderNotifications;
		
		
	}

}
