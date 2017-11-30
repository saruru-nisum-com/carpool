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
	
	@Query("select count(*) from cp_carpooldetails where emailid=:emailid and fromdate=:date ALLOW FILTERING")
	public int findEntriesWithDate(@Param("emailid") String emailid, @Param("date") String date);

	@Query("select count(*) from cp_carpooldetails where parentid=?0 ALLOW FILTERING")
	Long countByParentid(int parentid);
	
	@Query("select count(*) from cp_carpooldetails where id=?0 ALLOW FILTERING")
	Long findById(int id);
	
	//@Query("select * from cp_carpooldetails where parentid=?0 ALLOW FILTERING")
	//public Carpooldetails findByParentid(int parentid);
	@Query("select * from cp_carpooldetails where parentid=?0 ALLOW FILTERING")
	List<Carpooldetails> findByParentid(Integer parentid);
	
	@Query("select id from cp_carpooldetails where parentid=?0 allow filtering")
	List<Integer> getListOfIdsByParentid(int parentid);
	
	@Query("update cp_carpooldetails set fromtime=:#{#carpoolDetails.fromtime}, totime=:#{#carpoolDetails.toTime},"
			+ "noofseats=:#{#carpoolDetails.noofseats}, modifieddate=:#{#carpoolDetails.modifieddate}, vehicletype=:#{#carpoolDetails.vehicleType} where id IN (:listOfIds)")
	Integer udpateMultipleCarpoolDetails(@Param("carpoolDetails") Carpooldetails carpoolDetails, @Param("listOfIds") List<Integer> listOfIds);

	@Query("select * from cp_carpooldetails where emailid=?0 allow filtering")
	List<Carpooldetails> getCarPoolsByEmail(String email);
	

	@Query("update cp_carpooldetails set modifieddate=:#{#carpoolDetails.modifieddate}, status=:#{#carpoolDetails.status} where parentid=:#{#carpoolDetails.parentid}")
	Integer cancelMultipleCarpoolDetails(@Param("carpoolDetails") Carpooldetails carpoolDetails);
	
	public Carpooldetails findCarpoolDetailsById(int id);
}
