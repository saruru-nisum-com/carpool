package com.nisum.carpool.util;

import java.util.ArrayList;
import java.util.List;

import com.nisum.carpool.data.domain.CarpoolRiderNotifications;
import com.nisum.carpool.service.dto.CarpoolRiderNotificationsDTO;

public class CarpoolRiderNotificationsUtil {
	public static List<CarpoolRiderNotifications> convertRiderNotificationDtoToDao(List<CarpoolRiderNotificationsDTO> carpoolRiderNotificationsDTOs) {
		
		List<CarpoolRiderNotifications> listOfCarpools=new ArrayList<>();
		for (CarpoolRiderNotificationsDTO carpoolRiderNotificationsDTO : carpoolRiderNotificationsDTOs) {
			CarpoolRiderNotifications carpoolRiderNotifications = new CarpoolRiderNotifications();
			carpoolRiderNotifications.setCpid(carpoolRiderNotificationsDTO.getCpid());
			carpoolRiderNotifications.setEmailid(carpoolRiderNotificationsDTO.getEmailid());
			carpoolRiderNotifications.setId(carpoolRiderNotificationsDTO.getId());
			carpoolRiderNotifications.setNotified(carpoolRiderNotificationsDTO.isNotified());

			listOfCarpools.add(carpoolRiderNotifications);
			
		}
		return listOfCarpools;
	}
}
