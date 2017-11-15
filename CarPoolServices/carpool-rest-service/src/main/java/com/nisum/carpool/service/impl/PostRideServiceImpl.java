package com.nisum.carpool.service.impl;


import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.PostRideDao;
import com.nisum.carpool.data.domain.PostRideDomain;
import com.nisum.carpool.rest.api.PostRideRestService;
import com.nisum.carpool.service.api.PostRideService;
import com.nisum.carpool.service.dto.PostRideDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.Constants;
import com.nisum.carpool.util.PostRideServiceUtil;

@Service
public class PostRideServiceImpl implements PostRideService{

	private static Logger logger = LoggerFactory.getLogger(PostRideServiceImpl.class);
	@Autowired
	PostRideDao postRideDao;
	@Override
	public ServiceStatusDto updatePostRide(PostRideDto postRideDto) {
		// TODO Auto-generated method stub
		logger.info("PostRideServiceImpl:updatePostRide");
		PostRideDomain postRideDomain = PostRideServiceUtil.convertDtoTODao(postRideDto);
		PostRideDomain updatePostRide = postRideDao.updatePostRide(postRideDomain);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		if(ObjectUtils.anyNotNull(updatePostRide))
		{
			logger.info("PostRideServiceImpl:updatePostRide ::"+updatePostRide.toString());
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(Constants.MSG_RECORD_UPDATE);
		}
		return serviceStatusDto;
	}
	
}
