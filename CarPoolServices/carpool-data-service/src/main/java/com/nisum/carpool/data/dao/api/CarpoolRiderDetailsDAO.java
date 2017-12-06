package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.data.domain.CarpoolRiderNotifications;
import com.nisum.carpool.data.domain.Carpooldetails;


public interface CarpoolRiderDetailsDAO {

	public List<CarpoolRiderDetails> getRiderBookingDetails(String emailId);
	

	String cancelCarpoolRiderDetails(int cpid);

	public List<CarpoolRiderDetails> findCarpoolRiderDetailsByCPId(int cpid);
	
	
	public List<CarpoolRiderDetails> getRidersByCpID(Integer poolid) ;
	
	public List<CarpoolRiderDetails> cancelRiderBookingdetails(List<CarpoolRiderDetails> carpoolriderdetailslist);
	
	public List<CarpoolRiderNotifications> findRidersToNotifyByCPId(int cpid);
	
	public void updatecpridernotifications(CarpoolRiderNotifications cpridernotify);
	
	public String addRewards(double rewards);

	/*
	 * MethodAuthor: @Rajesh Sekhamuri
	 */


	public Integer updateRiderStatus(CarpoolRiderDetails carpoolRiderDaoObj);
	
Carpooldetails getCarpoolByDateAndEmail(String date, String email);
	
	public List<Integer> getCarpoolByDate(String date);
	public CarpoolRiderDetails getRidesByMailandAllCarpoolIds(String email, List<Integer> allCarpoolIds);
		
	public List<CarpoolRiderDetails> getNotOptedRiderDeatils(int cpid);
	public CarpoolRiderDetails getOptedRiderDeatils(int id, String email);

	public List<CarpoolRiderDetails> getOptedRiderDeatils(int id);
	
	public String cleanCarpoolRiderNotifications();
	
	}

	

	
	
	

