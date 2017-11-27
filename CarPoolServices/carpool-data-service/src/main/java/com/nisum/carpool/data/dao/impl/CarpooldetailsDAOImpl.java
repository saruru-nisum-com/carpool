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
public String updateCarpooldetails(Carpooldetails carpooldetails) {
		
		logger.info("CarpooldetailsDAOImpl: updateCarpooldetails");
		Long countByParentid = carpooldetailsRepository.countByParentid(carpooldetails.getId());
		
        if(countByParentid == 1) {
        	    carpooldetailsRepository.save(carpooldetails);
			return Constants.MSG_CARPOOL_UPDATE_SINGLE;
		}
         List<Integer> listOfIds = carpooldetailsRepository.getListOfIdsByParentid(carpooldetails.getId());
         carpooldetailsRepository.udpateMultipleCarpoolDetails(carpooldetails, listOfIds);
         return Constants.MSG_CARPOOL_UPDATE_MULTI;
			
	}
	
	@Override
	public List<Carpooldetails> addCarpoolDetails(List<Carpooldetails> carpooldetails) {
		
		logger.info("CarpooldetailsDAOImpl: createCarpooldetails");	
		
			for(Carpooldetails cp:carpooldetails) {
				carpooldetailsRepository.save(cp);
				
			}
			
			logger.info("CarpooldetailsDAOImpl: createCarpooldetails");
		
			return carpooldetails;
		
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
	
	@Override
	public List<Carpooldetails> getAllCarPoolDetails() {
		logger.info("PostRideDaoImpl: getAllCarPoolDetails ::");
			return  (List<Carpooldetails>) carpooldetailsRepository.findAll();

}
	
	public List<Carpooldetails> getCarPoolByMailID(String email)
	{
	return	carpooldetailsRepository.getCarPoolsByEmail(email);
	}
	
}
