package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.Carpooldetails;

public interface CarpooldetailsDAO {
	
	public String updateCarpooldetails(Carpooldetails carpooldetails);

	public String cancelCarpooldetails(Carpooldetails carpooldetails);

	public List<Carpooldetails> addCarpoolDetails(List<Carpooldetails> carpooldetails);

	public String checkValidCarpool(Carpooldetails carpooldetails);

	List<Carpooldetails> getAllCarPoolDetails();

	public List<Carpooldetails> getCarPoolByMailID(String email);
	
	public Carpooldetails  loadCarpoolDetailsById(int carpoolId)throws Exception;
	
	public Carpooldetails getCarPoolByCpID(int cpId);
}
