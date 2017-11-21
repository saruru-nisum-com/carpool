package com.nisum.carpool.data.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.domain.RegisterDomain;

@Repository
 public interface RegisterRepository extends CassandraRepository<RegisterDomain> {

	public RegisterDomain findByuserid(String userid);
	
}
