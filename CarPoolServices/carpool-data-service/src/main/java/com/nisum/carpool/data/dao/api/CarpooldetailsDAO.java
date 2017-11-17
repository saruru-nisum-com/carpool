package com.nisum.carpool.data.dao.api;

import java.util.List;

import com.nisum.carpool.data.domain.Carpooldetails;

public interface CarpooldetailsDAO {
	Carpooldetails updateCarpooldetails(Carpooldetails carpooldetails);
	
	public String addCarpoolDetails(List<Carpooldetails> carpooldetails);
}
