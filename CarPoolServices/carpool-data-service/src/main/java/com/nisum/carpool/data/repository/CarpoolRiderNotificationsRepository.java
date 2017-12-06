package com.nisum.carpool.data.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.carpool.data.domain.CarpoolRiderNotifications;

public interface CarpoolRiderNotificationsRepository extends CassandraRepository<CarpoolRiderNotifications>{

	@Query("select * from cp_carpoolridernotifications where cpid=:cpid allow filtering")
	public List<CarpoolRiderNotifications> getNotifiedRidersByCPId(@Param("cpid") int cpid);
	
	@Query("TRUNCATE cp_carpoolridernotifications")
	void CleanCarpoolriderNotifications();
}
