package com.nisum.carpool.data.repository;


import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.domain.RegisterDomain;
import java.lang.Integer;



@Repository
public interface CarpooldetailsRepository extends CassandraRepository<Carpooldetails>{
	
	@Query("select count(*) from cp_carpooldetails where userid=:userid and fromdate=:date ALLOW FILTERING")
	public int findEntriesWithDate(@Param("userid") String userid, @Param("date") String date);
	
	@Query("select count(*) from cp_carpooldetails where parentid=?0 ALLOW FILTERING")
	Long countByParentid(int parentid);
	
	@Query("select count(*) from cp_carpooldetails where id=?0 ALLOW FILTERING")
	Long findById(int id);
	
	@Query("select * from cp_carpooldetails where parentid=?0 ALLOW FILTERING")
	List<Carpooldetails> findByParentid(Integer parentid);
	
	@Query("select id from cp_carpooldetails where parentid=?0 allow filtering")
	List<Integer> getListOfIdsByParentid(int parentid);
	
	@Query("update cp_carpooldetails set fromtime=:#{#carpoolDetails.fromtime}, totime=:#{#carpoolDetails.toTime},"
			+ "noofseats=:#{#carpoolDetails.noofseats}, modifieddate=:#{#carpoolDetails.modifieddate}, vehicletype=:#{#carpoolDetails.vehicleType} where id IN (:listOfIds)")
	Integer udpateMultipleCarpoolDetails(@Param("carpoolDetails") Carpooldetails carpoolDetails, @Param("listOfIds") List<Integer> listOfIds);

	@Query("select * from cp_userregistration where userid=:?0  ALLOW FILTERING")
	public RegisterDomain findByEmail(String email);
	
	@Query("select * from cp_userregistration where location=?0  ALLOW FILTERING")
	public List<RegisterDomain> findByLocation(@Param("location")String location);
	
	@Query("select * from cp_carpooldetails where userid=?0 allow filtering")
	List<Carpooldetails> getCarPoolsByEmail(String email);
	
	
}


