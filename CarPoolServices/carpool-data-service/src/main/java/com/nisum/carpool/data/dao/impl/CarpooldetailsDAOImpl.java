package com.nisum.carpool.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.repository.CarpooldetailsRepository;
import com.nisum.carpool.data.util.Constants;



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
		
		logger.info("CarpooldetailsDAOImpl: createCarpooldetails");	
		
			for(Carpooldetails cp:carpooldetails) {
				carpooldetailsRepository.save(cp);
				
			}
			
			logger.info("CarpooldetailsDAOImpl: createCarpooldetails");
		
			return Constants.MSG_CARPOOL_ADD;
		
	}
	
	@Override
	public String checkValidCarpool(Carpooldetails carpooldetails) {
		// TODO Auto-generated method stub
		logger.info("CarpooldetailsDAOImpl: checkValidCarpool");
		
		
		String userid = carpooldetails.getUserid();
		String fromdate = carpooldetails.getFromDate();
		String todate = carpooldetails.getToDate();
		
		int countFromdate = carpooldetailsRepository.findEntriesWithDate(userid, fromdate);
		int countTodate = carpooldetailsRepository.findEntriesWithDate(userid, todate);
		
		logger.info("countFromdate " + countFromdate);
		logger.info("countTodate " + countTodate);
		
		if(countFromdate !=0 || countTodate !=0) 
			return Constants.CARPOOLEXISTS;
			else 
				return Constants.VALID;	

		
	}
	
}
