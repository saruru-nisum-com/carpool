package com.nisum.carpool.data.repository;


import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nisum.carpool.data.domain.Carpooldetails;



@Repository
public interface CarpooldetailsRepository extends CassandraRepository<Carpooldetails>{
	
	@Query("select count(*) from cp_carpooldetails where userid=:userid and fromdate=:date ALLOW FILTERING")
	public int findEntriesWithDate(@Param("userid") String userid, @Param("date") String date);
	

}
