package com.nisum.carpool.service.impl;

import java.sql.Timestamp;
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

		List<CarpoolRiderDetails> cpList = carpoolRiderdetailsDAO.getRiderBookingDetails(emailId);
		return CarpoolRiderDetailsServiceUtil.convertDaoTODto(cpList);
	}

	@Override
	public List<CarpoolRiderDetailsDTO> findCarpoolRiderDetailsByCPId(int cpid) {

		List<CarpoolRiderDetails> cpList = carpoolRiderdetailsDAO.findCarpoolRiderDetailsByCPId(cpid);
		return CarpoolRiderDetailsServiceUtil.convertDaoTODto(cpList);
	}

	@Override
	public String cancelCarpoolRiderDetails(int cpid) {
		// TODO Auto-generated method stub
		
	String cancelRiderStatus=	carpoolRiderdetailsDAO.cancelCarpoolRiderDetails(cpid);
		
		return cancelRiderStatus;
	}

}
