package com.nisum.carpool.data.repository;


import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.domain.Carpooldetails;


@Repository
public interface CarpooldetailsRepository extends CassandraRepository<Carpooldetails>{

}
