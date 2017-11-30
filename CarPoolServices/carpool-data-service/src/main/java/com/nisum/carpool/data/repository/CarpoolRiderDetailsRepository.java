package com.nisum.carpool.data.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;

public interface CarpoolRiderDetailsRepository extends CassandraRepository<CarpoolRiderDetails> {
	
	@Query("select * from cp_carpoolriderdetails where emailid=:emailId ALLOW FILTERING")
<<<<<<< HEAD
	List<CarpoolRiderDetails>  getRiderBookingDetails(@Param("emailId") String emailId);
	
	@Query("select * from cp_carpoolriderdetails where cpid=:cpid ALLOW FILTERING")
	List<CarpoolRiderDetails> getRiderDetailsByCpId(@Param("cpid")int cpid);
	
	
=======
	List<CarpoolRiderDetails> getRiderBookingDetails(@Param("emailId") String emailId);
	
	@Query("select * from cp_carpoolriderdetails where cpid=:cpid ALLOW FILTERING")
	List<CarpoolRiderDetails> findCarpoolRiderDetailsByCPId(@Param("cpid") int cpid);
>>>>>>> 3c494a29fd65290cf7fa7b18e170aedf92300cdf

}
