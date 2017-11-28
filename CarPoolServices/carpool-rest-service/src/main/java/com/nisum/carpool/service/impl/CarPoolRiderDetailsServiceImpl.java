package com.nisum.carpool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.CarpoolRiderDetailsDAO;
import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.util.CarpoolRiderDetailsServiceUtil;

@Service
public class CarPoolRiderDetailsServiceImpl implements CarpoolRiderDetailsService {

	
	@Autowired
	CarpoolRiderDetailsDAO carpoolRiderdetailsDAO;
	
	@Override
	public List<CarpoolRiderDetailsDTO> getRiderBookingDetails(String emailId) {
		// TODO Auto-generated method stub
		 List<CarpoolRiderDetails> cpList = carpoolRiderdetailsDAO.getRiderBookingDetails(emailId);
		
		return CarpoolRiderDetailsServiceUtil.convertDaoTODto(cpList);
	}

}
