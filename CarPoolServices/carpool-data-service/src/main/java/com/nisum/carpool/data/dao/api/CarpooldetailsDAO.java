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
	
<<<<<<< HEAD
	public Carpooldetails  loadCarpoolDetailsById(int carpoolId)throws Exception;
	
=======
>>>>>>> 6f1d8c4ac69fb41e5d2c674f3b982340f17cc30e
	public Carpooldetails getCarPoolByCpID(int cpId);
}
