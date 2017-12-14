/**
 * 
 */
package com.nisum.carpool.data.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.carpool.data.domain.Carpooldetails;

/**
 * @author Nisum
 *
 */
public interface IDriverPoolHistoryRepository extends CassandraRepository<Carpooldetails>{
	
	@Query("select * from cp_carpooldetails where emailid=?0 and fromDate<= :currentDate and toDate<= :currentDate allow filtering")
	List<Carpooldetails> getCarPoolsByEmail(@Param("email") String email, @Param("currentDate") String currentDate);
}
