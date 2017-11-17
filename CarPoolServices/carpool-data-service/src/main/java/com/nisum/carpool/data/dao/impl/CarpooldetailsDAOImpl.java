package com.nisum.carpool.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.repository.CarpooldetailsRepository;
@Configuration
public class CarpooldetailsDAOImpl implements CarpooldetailsDAO {
	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsDAOImpl.class);
	@Autowired
	CarpooldetailsRepository carpooldetailsRepository;
	@Override
	public Carpooldetails updateCarpooldetails(Carpooldetails carpooldetails) {
	/*	try{
			logger.info("PostRideDaoImpl: updatePostRideDao ::");
		  postRideRepository.save(carpooldetails);
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		logger.info("CarpooldetailsDAOImpl: updateCarpooldetails");
		 return carpooldetailsRepository.save(carpooldetails);
	}
	@Override
	public String addCarpoolDetails(List<Carpooldetails> carpooldetails) {
		
			
			for(Carpooldetails cp:carpooldetails) {
				carpooldetailsRepository.save(cp);
				
			}
		
			return" saved suceesfully";
		
	}
	
	
	

}
