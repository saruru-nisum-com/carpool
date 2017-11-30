package com.nisum.carpool.data.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.carpool.data.dao.api.CarpoolRiderDetailsDAO;
import com.nisum.carpool.data.domain.CarpoolRiderDetails;

import com.nisum.carpool.data.repository.CarpoolRiderDetailsRepository;

@Configuration
public class CarpoolRiderDetailsDAOImpl implements CarpoolRiderDetailsDAO {

	@Autowired
	CarpoolRiderDetailsRepository carpoolRiderDetailsRepository;

	@Override
	public List<CarpoolRiderDetails> getRiderBookingDetails(String emailId) {

		return (List<CarpoolRiderDetails>) carpoolRiderDetailsRepository.getRiderBookingDetails(emailId);
	}

	@Override
	public List<CarpoolRiderDetails> findCarpoolRiderDetailsByCPId(int cpid) {

		return carpoolRiderDetailsRepository.findCarpoolRiderDetailsByCPId(cpid);
	}

}
