package com.nisum.carpool.service.api;

import java.util.List;

import com.nisum.carpool.data.domain.CarpoolRiderNotifications;
import com.nisum.carpool.service.dto.CarpoolRiderNotificationsDTO;

public interface CarpoolRiderNotificationsService {

	public List<CarpoolRiderNotifications> saveRiderNotifications(List<CarpoolRiderNotificationsDTO> carpoolRiderNotificationsDTOs);
}
