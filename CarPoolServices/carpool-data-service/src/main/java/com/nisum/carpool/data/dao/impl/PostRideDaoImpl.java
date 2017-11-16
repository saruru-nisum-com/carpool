package com.nisum.carpool.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.carpool.data.dao.api.PostRideDao;
import com.nisum.carpool.data.domain.PostRideDomain;
import com.nisum.carpool.data.repository.PostRideRepository;
@Configuration
public class PostRideDaoImpl implements PostRideDao {
	private static Logger logger = LoggerFactory.getLogger(PostRideDaoImpl.class);
	@Autowired
	PostRideRepository postRideRepository;
	@Override
	public PostRideDomain updatePostRide(PostRideDomain postRideDomain) {
		try{
			logger.info("PostRideDaoImpl: updatePostRideDao ::");
		  postRideRepository.save(postRideDomain);
		}catch (Exception e) {
			e.printStackTrace();
		}
		 return postRideDomain;
	}
	@Override
	public String addPostRide(List<PostRideDomain> postRideDomain) {
		
			
			for(PostRideDomain cp:postRideDomain) {
				postRideRepository.save(cp);
				
			}
		
			return" saved suceesfully";
		
	}
	
	
	

}
