package com.nisum.carpool.data.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;


public interface CarpoolRiderDetailsRepository extends  CassandraRepository<CarpoolRiderDetails>{
	@Query("select * from cp_carpoolriderdetails where emailid=:emailId ALLOW FILTERING")
	List<CarpoolRiderDetails>  getRiderBookingDetails(@Param("emailId") String emailId);

}
