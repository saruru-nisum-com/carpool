package com.nisum.carpool.data.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nisum.carpool.data.domain.Carpooldetails;
import java.lang.Integer;



@Repository
public interface CarpooldetailsRepository extends CassandraRepository<Carpooldetails>{
	
	@Query("select count(*) from cp_carpooldetails where emailid=:emailid and fromdate>= :fromdate and todate<= :todate and status>=1 and status<4 ALLOW FILTERING")
	public int findEntriesWithDateIfNotCancelled(@Param("emailid") String emailid,@Param("fromdate") String fromdate, @Param("todate") String todate);
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

	@Query("select * from cp_carpooldetails where emailid=?0 allow filtering")
	List<Carpooldetails> getCarPoolsByEmail(String email);
	
    @Query("select * from cp_carpooldetails where emailid=:emailId and todate>=:date and status<5 allow filtering")
    List<Carpooldetails> getCarPoolsByEmailAndCurrentDate(@Param("emailId") String emailId,@Param("date") String date); 
    
	@Query("update cp_carpooldetails set modifieddate=:#{#carpoolDetails.modifieddate}, status=:#{#carpoolDetails.status} where parentid=:#{#carpoolDetails.parentid}")
	Integer cancelMultipleCarpoolDetails(@Param("carpoolDetails") Carpooldetails carpoolDetails);

	public Carpooldetails findCarpoolDetailsById(int id);
	
	@Query("select id from cp_carpooldetails where rewards=0 and status=:status and todate<= :rewardedDate allow filtering")
	List<Integer> getCarpooldetailsByToDateAndStatus(@Param("status") int status,@Param("rewardedDate") String rewardedDate);
	
	@Query("update cp_carpooldetails set rewards=:rewards where id IN (:listOfIds)")
	Integer udpateRewardPoints(@Param("rewards") double rewards, @Param("listOfIds") List<Integer> listOfIds);
	
	@Query("select parentid from cp_carpooldetails where emailid=?0 allow filtering")
	public List<Integer> getParentIdByEmail(@Param("email")String email);
	
	@Query("select * from cp_carpooldetails where parentid=?0 allow filtering")
	public List<Carpooldetails> getCaroolsByParentId(int poolId);

	@Query("select * from cp_carpooldetails where fromdate>= :fromdate and todate<= :todate and status>=1 and status<4 allow filtering")
	public List<Carpooldetails> getCarPoolsByDateNotCancelled(@Param("fromdate") String fromdate, @Param("todate") String todate);
	
	@Query("select * from cp_carpooldetails where id=?0 allow filtering")
	public Carpooldetails getCarpoolByPoolID(Integer carpoolId);
	   
	@Query("update cp_carpooldetails set status=:poolStatus where id=:id ")
	public void updateCarpoolStatusByPoolId(@Param("poolStatus")int poolStatus,@Param("id") Integer id);
		
	@Query("update cp_carpooldetails set status=:status where id=:pid ")
	public void updateCarpoolStatusByPoolId(@Param("pid")int pid, @Param("status")int status);

	@Query("select emailid from cp_carpooldetails where parentid=:cpid allow filtering")
	public String getDriverEmailByCPId(@Param("cpid") int cpid);
	
	@Query("select * from cp_carpooldetails where id=:cpId and fromdate>:date allow filtering")
	List<Carpooldetails>  getCarPoolsByCpIdandDate(@Param("cpId")int cpId,@Param("date") String date);
	
	@Query("select  *  from cp_carpooldetails where fromdate=:fromdate and emailid=:emailid allow filtering")
	public Carpooldetails getCarpoolByDateAndEmail(@Param("fromdate")String fromdate, @Param("emailid")String emailid);
	
	@Query("select id from cp_carpooldetails where fromdate=?0  allow filtering")
	public List<Integer> getCarpoolByDate(String date);
	@Query("select * from cp_carpooldetails where location=:location allow filtering") 
	List<Carpooldetails> getCarpoolsByLocation(@Param("location") String location);
	
	@Query("select * from cp_carpooldetails where todate<= :todate allow filtering")
	List<Carpooldetails> getCarpoolsByToDate(@Param("todate") String todate);
	
	@Query("update cp_carpooldetails set status=:poolStatus where id IN (:cpids)")
	Integer updateCarpoolStatusBySetOfPoolIds(@Param("poolStatus") int poolStatus,@Param("cpids") Set<Integer> cpids);
	
	@Query("select count(*) from cp_carpooldetails where status=:poolStatus allow filtering")
	Integer checkUpdateCarpoolStatusClosedCount(@Param("poolStatus") int poolStatus);
	
	@Query("select * from cp_carpooldetails where emailid=:emailId and id=:id allow filtering")
    List<Carpooldetails> getCPDetailsByCPID(@Param("emailId") String emailId,@Param("id") Integer id); 
    
	//int checkUpdateCarpoolStatusClosedCount(@Param("poolStatus") int poolStatus);
}
