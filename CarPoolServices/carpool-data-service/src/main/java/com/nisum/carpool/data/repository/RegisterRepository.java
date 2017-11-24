package com.nisum.carpool.data.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.domain.RegisterDomain;

@Repository
 public interface RegisterRepository extends CassandraRepository<RegisterDomain> {

	@Query("select * from cp_userregistration where userid= ?0 allow filtering")
	List<RegisterDomain> findByUserId(String userId);
	
}
