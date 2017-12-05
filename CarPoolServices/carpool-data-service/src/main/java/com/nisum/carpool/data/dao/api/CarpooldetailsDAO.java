package com.nisum.carpool.data.dao.api;

import java.util.Date;
import java.util.List;

import com.nisum.carpool.data.domain.Carpooldetails;

public interface CarpooldetailsDAO {

	public String updateCarpooldetails(Carpooldetails carpooldetails);

	public String cancelCarpooldetails(Carpooldetails carpooldetails);

	public String addCarpoolDetails(List<Carpooldetails> carpooldetails);

	public String checkValidCarpool(String emailId, String fromDate, String toDate);

	List<Carpooldetails> getAllCarPoolDetails();

	public List<Carpooldetails> getCarPoolByMailID(String email);

	public Carpooldetails loadCarpoolDetailsById(int carpoolId) throws Exception;

	List<Carpooldetails> getCarpoolsByParentId(int parentId);

	public List<Integer> getCarPoolParentIds(String email);

	public Carpooldetails getCarpoolByPoolID(Integer carpoolId);

	public void updateCarpoolStatusByPoolId(int poolStatus, Integer id);
	
	public void upateCarPoolStatusByIdandParentID(int pid, int status);
	
	public String getDriverEmailByCPId(int cpid);
	



	public List<Carpooldetails> getCarPoolByCpIDandDate(int cpId,String date);

}

