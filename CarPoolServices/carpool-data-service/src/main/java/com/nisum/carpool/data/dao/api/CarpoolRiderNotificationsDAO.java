package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.CarpoolRiderNotifications;

public interface CarpoolRiderNotificationsDAO {

	public List<CarpoolRiderNotifications> saveRiderNotification(List<CarpoolRiderNotifications> carpoolRiderNotifications);
	
}
