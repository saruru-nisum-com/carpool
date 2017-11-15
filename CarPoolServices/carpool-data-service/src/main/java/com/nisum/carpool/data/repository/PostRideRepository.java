package com.nisum.carpool.data.repository;


import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.domain.PostRideDomain;

@Repository
public interface PostRideRepository extends CassandraRepository<PostRideDomain>{

}
