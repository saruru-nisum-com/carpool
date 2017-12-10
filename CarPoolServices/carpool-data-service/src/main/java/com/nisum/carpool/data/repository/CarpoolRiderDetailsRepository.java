package com.nisum.carpool.data.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;

public interface CarpoolRiderDetailsRepository extends CassandraRepository<CarpoolRiderDetails> {
	
	@Query("select * from cp_carpoolriderdetails where emailid=:emailId ALLOW FILTERING")
	List<CarpoolRiderDetails>  getRiderBookingDetails(@Param("emailId") String emailId);
	
	@Query("select * from cp_carpoolriderdetails where cpid=:cpid ALLOW FILTERING")
	List<CarpoolRiderDetails> getRiderDetailsByCpId(@Param("cpid")int cpid);
	
	@Query("select * from cp_carpoolriderdetails where cpid=:cpid ALLOW FILTERING")
	List<CarpoolRiderDetails> findCarpoolRiderDetailsByCPId(@Param("cpid") int cpid);
	
	@Query("select * from cp_carpoolriderdetails where cpid=:poolid ALLOW FILTERING")
	List<CarpoolRiderDetails>  getRidersByPoolID(@Param("poolid") int poolid);

	@Query("select count(*) from cp_carpoolriderdetails where emailid=:emailid and cpid=:cpid ALLOW FILTERING")
	int checkWhetherDriverIsRider(@Param("emailid")String emailid, @Param("cpid") int cpid);

	@Query("update cp_carpoolriderdetails set status=:#{#carpoolRiderDetails.status}, reason=:#{#carpoolRiderDetails.reason} where id=:#{#carpoolRiderDetails.id}")
	Integer udpateCarpoolRiderStatus(@Param("carpoolRiderDetails") CarpoolRiderDetails carpoolRiderDetails);
		
	@Query("select reason, status from cp_carpoolriderdetails where id>= :id allow filtering")
	public List<CarpoolRiderDetails> getRiderDetailsById(@Param("id") Integer id); 

	@Query("select count(*) from cp_carpoolriderdetails where emailid=:emailid and cpid=:cpid and status<=2 ALLOW FILTERING")
	int checkWhetherDriverIsRiderWithStatus(@Param("emailid")String emailid, @Param("cpid") int cpid);
	
	@Query("select id from cp_carpoolriderdetails where status=:status and rewards=0 and cpid=:cpid allow filtering;")
	Integer getListOfClosedRiders(@Param("status") int status,@Param("cpid") int cpid);

	@Query("update cp_carpoolriderdetails set rewards=:rewards where id IN (:listOfIds)")
	String udpateRiderRewardPoints(@Param("rewards") double rewards, @Param("listOfIds") Set<Integer> listOfIds);
	
	@Query("select * from cp_carpoolriderdetails where emailid=?0 and cpid=?1 ALLOW FILTERING")
	CarpoolRiderDetails getRidesByMailandAllCarpoolIds(String email, int allCarpoolIds);
	
	@Query("select * from cp_carpoolriderdetails where cpid=:cpid ALLOW FILTERING")
	List<CarpoolRiderDetails> getNotOptedRiderDetails(@Param("cpid") int cpid);

	@Query("select * from cp_carpoolriderdetails where cpid=:cpid and emailid=:emailid ALLOW FILTERING")
	CarpoolRiderDetails getOptedRiderDetails(@Param("cpid") int cpid, @Param("emailid") String emailid);

	@Query("select * from cp_carpoolriderdetails where cpid=:cpid ALLOW FILTERING")
	List<CarpoolRiderDetails> getOptedRiderDetails(@Param("cpid") int cpid);

}
