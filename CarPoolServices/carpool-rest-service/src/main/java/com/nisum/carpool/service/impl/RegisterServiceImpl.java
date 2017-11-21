package com.nisum.carpool.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.RegisterDAO;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.service.api.RegisterService;
import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.Constants;
import com.nisum.carpool.util.RegisterServiceUtil;
@Service
public class RegisterServiceImpl  implements RegisterService{

	private static Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
	@Autowired
	private RegisterDAO registerDAO;
	public ServiceStatusDto registerDriverorRider(RegisterDTO registerDTO) {
		logger.info("RegisterServiceImpl: registerDriver:::");
		
		RegisterDomain registerDomain = RegisterServiceUtil.convertDtoObjectTODomain(registerDTO);
		RegisterDomain rDomain =  registerDAO.registerDriverorRider(registerDomain);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		if(ObjectUtils.anyNotNull(rDomain)) {
			logger.info("RegisterServiceImpl:registerDriver ::" + rDomain.toString());
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(Constants.MSG_RECORD_ADD);
		}
		return serviceStatusDto;
	}
	

}
