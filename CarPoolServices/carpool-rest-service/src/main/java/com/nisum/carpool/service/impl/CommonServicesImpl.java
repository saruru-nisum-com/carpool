/**
 * @author Harish Kumar Gudivada
 * created Date 6th December 2017
 */

package com.nisum.carpool.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.UserDAO;
import com.nisum.carpool.data.domain.User;
import com.nisum.carpool.service.api.CommonServices;

@Service
public class CommonServicesImpl implements CommonServices{

	private static Logger logger = LoggerFactory.getLogger(CommonServicesImpl.class);
	
	@Autowired
	UserDAO userDAO;
	
	/**
	 * @author Harish Kumar Gudivada
	 * This method is used to check whether the given emailid is valid mail id or not
	 */
	@Override
	public boolean checkValidUserEmailId(String emailId) {
		boolean flag=false;
		logger.info("Entered into Class:CommonServicesImpl Method:checkValidUserEmailId");
		try {
			User user=userDAO.findByEmailId(emailId);
			if(user!=null)
				flag=true;	
		}catch (Exception e) {
			logger.error("Exception Occured in Class:CommonServicesImpl Method:checkValidUserEmailId Message:"+e.getMessage());
		}
		logger.info("Exit from Class:CommonServicesImpl Method:checkValidUserEmailId");
		return flag;
	}

}
