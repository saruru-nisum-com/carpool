package com.nisum.carpool.data.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.nisum.carpool.data.domain.VehicleTypes;

public interface VehicleTypesRepository extends CassandraRepository<VehicleTypes>{

}
