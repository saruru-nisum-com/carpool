package com.nisum.carpool.data.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.MapId;

import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.repository.CarpooldetailsRepository;
import com.nisum.carpool.data.util.Constants;
//import com.nisum.carpool.service.dto.CarpooldetailsDto;



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
	public String cancelCarpooldetails(Carpooldetails carpooldetails) {
		// TODO Auto-generated method stub
		
		logger.info("CarpooldetailsDAOImpl: cancel Carpooldetails getby Id"+carpooldetails.getId());
		List<Integer> listOfIds =null;
		Long countByParentid = carpooldetailsRepository.findById(carpooldetails.getId());
		//carpooldetailsRepository.findOne(
		System.out.println("in daoimpl cancel by parentId"+countByParentid);
		   if(countByParentid == 1) {
			   System.out.println("in parent cancel");
       	    carpooldetailsRepository.save(carpooldetails);
		}
		    listOfIds = carpooldetailsRepository.getListOfIdsByParentid(carpooldetails.getId());
		    Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		   System.out.println("listOfIds in cancel pool daoimpl:"+listOfIds.size());
		  try {
			  System.out.println("in child update.parentId.."+carpooldetails.getId());
			  List<Carpooldetails> poolData=carpooldetailsRepository.findByParentid(carpooldetails.getId());
			  if(poolData!=null) {
					  if (CollectionUtils.isNotEmpty(poolData)) {
						  poolData.forEach(c->{
								c.setStatus(4);
							  c.setModifieddate(modifiedDate.toLocalDateTime());
								carpooldetailsRepository.save(c);
							});
						 }
				  } 
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //  }
		   return Constants.MSG_CARPOOL_CANCEL;
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
		
		
		String userid = carpooldetails.getEmailId();
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

	
	/**
	 * @author Harish Kumar Gudivada
	 * 
	 * This method is used to load the carpool ride details based on carpool id from Repository
	 */
	@Override
	public Carpooldetails loadCarpoolDetailsById(int carpoolId) throws Exception{
		Carpooldetails carpoolDets=null;
		try {
			carpoolDets=carpooldetailsRepository.findCarpoolDetailsById(carpoolId);
		}catch (Exception e) {
			logger.error("Exception Occured in Class:CarpooldetailsDAOImpl Method:loadCarpoolDetailsById Message:"+e.getMessage());
			throw e;
		}
		return carpoolDets;
	}
	
	@Override
	public Carpooldetails getCarPoolByCpID(int cpId) {
		return carpooldetailsRepository.getCarPoolsByCpId(cpId);
	}
	

	@Override
	public Carpooldetails getCarPoolByCpID(int cpId) {
		return carpooldetailsRepository.getCarPoolsByCpId(cpId);
	}
	

}
