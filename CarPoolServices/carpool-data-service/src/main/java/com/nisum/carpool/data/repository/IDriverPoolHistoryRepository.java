/**
 * 
 */
package com.nisum.carpool.data.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.nisum.carpool.data.domain.Carpooldetails;

/**
 * @author Nisum
 *
 */
public interface IDriverPoolHistoryRepository extends CassandraRepository<Carpooldetails>{
	
	@Query("select * from cp_carpooldetails where emailid=?0 allow filtering")
	List<Carpooldetails> getCarPoolsByEmail(final String email);
}
