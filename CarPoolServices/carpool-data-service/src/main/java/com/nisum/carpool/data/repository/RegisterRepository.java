package com.nisum.carpool.data.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.domain.RegisterDomain;

@Repository
 public interface RegisterRepository extends CassandraRepository<RegisterDomain> {

	@Query("select * from cp_userregistration where emailid= ?0 allow filtering")
	List<RegisterDomain> findByUserId(String emailId);
	

	@Query("select * from cp_userregistration where emailid=?0  ALLOW FILTERING")
	public RegisterDomain findByEmail(String email);
	
	@Query("select * from cp_userregistration where location=?0  ALLOW FILTERING")
	public List<RegisterDomain> findByLocation(@Param("location")String location);
}
