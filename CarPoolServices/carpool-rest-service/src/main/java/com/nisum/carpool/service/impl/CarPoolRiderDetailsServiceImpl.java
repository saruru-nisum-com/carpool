package com.nisum.carpool.service.impl;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.CarpoolRiderDetailsDAO;
import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.dao.api.RegisterDAO;
import com.nisum.carpool.data.dao.api.UserDAO;
import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.data.domain.User;
import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.CustomerCarpooldetailsDto;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.util.CarpoolRiderDetailsServiceUtil;

@Service
public class CarPoolRiderDetailsServiceImpl implements CarpoolRiderDetailsService {

	@Autowired
	UserDAO userDAO;
	@Autowired
	RegisterDAO registerDAO;
	@Autowired
	CarpooldetailsDAO carpooldetailsDAO;
	@Autowired
	CarpoolRiderDetailsDAO carpoolRiderdetailsDAO;




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

	

	/**
	 * author Radhika pujari
	 */
	
	@Override
	public List<RiderBookingDetailsDTO> getRiderBookingDetails(String emailId) {
		// TODO Auto-generated method stub
		List<CarpoolRiderDetails> carpoolRiderLists = new ArrayList<>();


		// List<RiderBookingDetailsDTO> carpoolListbyLocation = new ArrayList<>();
		List<RiderBookingDetailsDTO> riderBookingdetailsDtoList = new ArrayList<>();

		// List<String> useridsList=null;
		Set<Integer> cpids = null;
		// useridsList= carpooldetailsDAO.getAllUserIDs();
		carpoolRiderLists = (List<CarpoolRiderDetails>) carpoolRiderdetailsDAO.getRiderBookingDetails(emailId);

		if (carpoolRiderLists != null) {
			for (CarpoolRiderDetails car : carpoolRiderLists) {

				RiderBookingDetailsDTO carpoolRiderdetailsDto = new RiderBookingDetailsDTO();
				int cpid = car.getCpid();

				Carpooldetails carpool = carpooldetailsDAO.getCarPoolByCpID(cpid);
				String email = carpool.getEmailId();
				User user = userDAO.findByEmailId(email);
				List<RegisterDomain> registerDomain = registerDAO.findUserRegistrationByUserId(email);
				carpoolRiderdetailsDto.setEmail(email);
				if (user != null && registerDomain.get(0).getIsrider() == 0) {
					carpoolRiderdetailsDto.setUserName(user.getUserName());
				}

				if (registerDomain != null && registerDomain.size() > 0) {

					carpoolRiderdetailsDto.setMobile(registerDomain.get(0).getMobile());
				}
				// riderBookingdetailsDtoList.add(carpoolRiderdetailsDto);
				carpoolRiderdetailsDto.setCreateddate(Timestamp.valueOf(car.getCreateddate()));
				carpoolRiderdetailsDto.setReason(car.getReason());
				carpoolRiderdetailsDto.setStatus(car.getStatus());
				carpoolRiderdetailsDto.setLocation(car.getLocation());
				riderBookingdetailsDtoList.add(carpoolRiderdetailsDto);
			}
			
			
		}

		// List<CarpoolRiderDetails> cpList =
		// carpoolRiderdetailsDAO.getRiderBookingDetails(emailId);

		// return CarpoolRiderDetailsServiceUtil.convertDaoTODto(cpList)

		return riderBookingdetailsDtoList;
	}
}
