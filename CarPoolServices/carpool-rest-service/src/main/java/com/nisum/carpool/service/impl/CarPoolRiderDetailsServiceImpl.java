package com.nisum.carpool.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.carpool.data.dao.api.CarpoolRiderDetailsDAO;
import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.dao.api.RegisterDAO;
import com.nisum.carpool.data.dao.api.UserDAO;
import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.data.domain.CarpoolRiderNotifications;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.data.domain.User;
import com.nisum.carpool.data.util.Ride_Status;
import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.CarpoolRiderOptedDetailsDto;
import com.nisum.carpool.service.dto.GenericEmailDto;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.dto.RiderStatusDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.CarpoolRiderDetailsServiceUtil;
import com.nisum.carpool.util.GenericMailTemplate;

@Service
public class CarPoolRiderDetailsServiceImpl implements CarpoolRiderDetailsService {

	private static Logger logger = LoggerFactory.getLogger(CarPoolRiderDetailsServiceImpl.class);

	@Autowired
	UserDAO userDAO;

	@Autowired
	RegisterDAO registerDAO;

	@Autowired
	CarpooldetailsDAO carpooldetailsDAO;
	@Autowired
	CarpoolRiderDetailsDAO carpoolRiderdetailsDAO;

	@Autowired
	CarpoolRiderDetailsDAO carpoolRiderDetailsDAO;

	@Autowired
	GenericMailTemplate genericMailTemplateObj;

	@Override
	public List<CarpoolRiderDetailsDTO> findCarpoolRiderDetailsByCPId(int cpid) {

		List<CarpoolRiderDetails> cpList = carpoolRiderDetailsDAO.findCarpoolRiderDetailsByCPId(cpid);
		return CarpoolRiderDetailsServiceUtil.convertDaoTODto(cpList);
	}

	@Override
	public List<CarpoolRiderDetailsDTO> cancelCarpoolRiderDetails(int cpid) {
		// TODO Auto-generated method stub

		List<CarpoolRiderDetails> cpList = carpoolRiderDetailsDAO.cancelCarpoolRiderDetails(cpid);
		return CarpoolRiderDetailsServiceUtil.convertDaoTODto(cpList);
	}

	/**
	 * author Radhika pujari
	 */

	/**
	 * getRiderBookingdetails() for service layer Parameter: emailID This method is
	 * used to load the future opt a ride details based on emailId for rider see the
	 * future rides returntype:List<RiderBookingDetailsDTO>
	 */

	@Override
	public List<RiderBookingDetailsDTO> getRiderBookingDetails(String emailId) {
		String emailid = null;
		logger.info("CarPoolRiderDetailsServiceImpl::getRiderBookingDetails::start");
		List<RiderBookingDetailsDTO> riderBookingdetailsDtoList = new ArrayList<>();

		try {

			Date date1 = new Date();

			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			String date = sd.format(date1);

			System.out.println("Date" + date);

			List<CarpoolRiderDetails> carpoolRiderLists = (List<CarpoolRiderDetails>) carpoolRiderDetailsDAO
					.getRiderBookingDetails(emailId);

			
			if (carpoolRiderLists != null) {
				for (CarpoolRiderDetails car : carpoolRiderLists) {

					int cpid = car.getCpid();

					

					List<Carpooldetails> carpoolList = carpooldetailsDAO.getCarPoolByCpIDandDate(cpid, date);

					for (Carpooldetails carPool : carpoolList) {

						RiderBookingDetailsDTO carpoolRiderdetailsDto = new RiderBookingDetailsDTO();
						emailid = carPool.getEmailId();
						
						carpoolRiderdetailsDto.setEmail(emailid);

						carpoolRiderdetailsDto.setLocation(carPool.getLocation());

						carpoolRiderdetailsDto.setFromDate(carPool.getFromDate());
						carpoolRiderdetailsDto.setEmail(emailid);

						List<RegisterDomain> registerDomain = registerDAO.findUserRegistrationByUserId(emailid);
						if (registerDomain != null && registerDomain.size() > 0) {
							for (RegisterDomain registerDomain2 : registerDomain) {
								if (registerDomain2.getIsrider() == 0) {

									carpoolRiderdetailsDto.setMobile(registerDomain2.getMobile());
									break;
								}

							}
						}
						User user = userDAO.findByEmailId(emailid);
						if (user != null) {
							carpoolRiderdetailsDto.setUserName(user.getUserName());
						}

						carpoolRiderdetailsDto.setReason(car.getReason());

						Ride_Status ride_Status = Ride_Status.values()[(car.getStatus() - 1)];

						carpoolRiderdetailsDto.setStatusName(ride_Status.toString());
						System.out.println(ride_Status.toString());
						
						

						// carpoolRiderdetailsDto.setStatus(car.getStatus());
						// carpoolRiderdetailsDto.setLocation(car.getLocation());
						riderBookingdetailsDtoList.add(carpoolRiderdetailsDto);

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("CarPoolRiderDetailsServiceImpl::getRiderBookingDetail::end");
		return riderBookingdetailsDtoList;
	}

	/*
	 * @author: Rajesh Sekhamuri (non-Javadoc)
	 * @param List<RiderStatusDTO>
	 * @return Boolean isRiderUpdated, isMailSent
	 * @see
	 * com.nisum.carpool.service.api.CarpoolRiderDetailsService#updateRiderStatus(
	 * java.util.List)
	 * 
	 */
	@Override
	public Boolean updateRiderStatus(List<RiderStatusDTO> riderStatusDtoListObj) throws Exception {
		Boolean isRiderUpdated = false;
		Boolean isMailSent = false;
		List riderStatusListObj = null;
		logger.info("Start of updateRiderStatus() method in CarPoolRiderDetailsServiceImpl"); 
		riderStatusListObj = riderStatusDtoListObj;
		int riderObjSize = riderStatusListObj.size();
		logger.debug("Input Rider Status object list size "+riderObjSize);
		for (int riderVar = 0; riderVar <= riderObjSize - 1; riderVar++) {
			RiderStatusDTO riderStatusObj = (RiderStatusDTO) riderStatusListObj.get(riderVar); // Downcast into
			logger.debug("Each rider object "+riderStatusObj);
			System.out.println("Real object " + riderStatusObj);
			CarpoolRiderDetails carpoolRiderDaoObj = CarpoolRiderDetailsServiceUtil
					.convertRiderStatusDtoToDao(riderStatusObj);
			logger.debug("Convert DTO to DAO Object  " + carpoolRiderDaoObj.getId() + " " + carpoolRiderDaoObj.getEmailid());
			Integer riderStatusCount = 0; //
			riderStatusCount = carpoolRiderdetailsDAO.updateRiderStatus(carpoolRiderDaoObj);
			logger.debug("Rider Status updated "+riderStatusCount);
			if (riderStatusCount > 0) { // If rider status record updated into db, should send mail to rider
				// Email send code start
				logger.debug("Email send implementation starts **** " + riderStatusCount);
				int id = riderStatusObj.getCpid();
				String emailId = riderStatusObj.getRiderEmailId() != "" ? riderStatusObj.getRiderEmailId() : "";
				if(emailId.equals("") || emailId == null ) {
					logger.debug("Invalid email id "+emailId);
					return isRiderUpdated;
				}
				List<Carpooldetails> cpDetailsListObj = carpoolRiderdetailsDAO.getCPDetailsByCPID(emailId, id);
				logger.debug("Get basic ride details from carpooldetails " + cpDetailsListObj.size());
				for (int cpDetVar = 0; cpDetVar < cpDetailsListObj.size(); cpDetVar++) {
					Carpooldetails cpDetObject = cpDetailsListObj.get(cpDetVar);
					GenericEmailDto genericEmailDTOObj = new GenericEmailDto(); 
					genericEmailDTOObj.setUserName(riderStatusObj.getRiderName());
					genericEmailDTOObj.setLocation(cpDetObject.getLocation());
					genericEmailDTOObj.setStatus(carpoolRiderDaoObj.getStatus());
					genericEmailDTOObj.setIsRider(0); // static optional value
					genericEmailDTOObj.setStartTime(cpDetObject.getFromtime());
					genericEmailDTOObj.setReturnTime(cpDetObject.getToTime());
					SimpleDateFormat sDateFormateObj = new SimpleDateFormat("dd/mm/yyyy");
					Timestamp timestampObj =riderStatusObj.getCurrentDate();
					Date dateObj = new Date(timestampObj.getTime());
					String currentDateStrObj = sDateFormateObj.format(dateObj);
					genericEmailDTOObj.setDate(currentDateStrObj);
					Map<String, GenericEmailDto> mailObj = new HashMap<String, GenericEmailDto>();
					mailObj.put(emailId, genericEmailDTOObj);
					isMailSent = genericMailTemplateObj.sendGenericMail("", mailObj);
					logger.debug("Mail send successfully? " + isMailSent);
					logger.info("End of updateRiderStatus() method in CarPoolRiderDetailsServiceImpl"); 
					return isMailSent;
				}

			} else {
				logger.debug("Unable to update carpool rider status into db ");
				logger.info("End of updateRiderStatus() method in CarPoolRiderDetailsServiceImpl"); 
				return isRiderUpdated;
			}
		}
		logger.info("End of updateRiderStatus() method in CarPoolRiderDetailsServiceImpl"); 
		return isMailSent;
	}

	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used for cancelling a ride by rider
	 */

	@Override
	public List<CarpoolRiderDetailsDTO> cancelRiderBookingdetails(List<CarpoolRiderDetailsDTO> rides) throws Exception {
		logger.info("carpoolriderdetailsserviceimpl:cancelling a ride");

		List<CarpoolRiderDetails> carpoolriderdetailslist = CarpoolRiderDetailsServiceUtil.convertDtoTODao(rides);

		List<CarpoolRiderDetails> cpriderlist = carpoolRiderDetailsDAO
				.cancelRiderBookingdetails(carpoolriderdetailslist);

		if (cpriderlist == null)
			return null;

		else

		{
			logger.info("carpoolriderdetailsserviceimpl: carpool list is not null");
			for (CarpoolRiderDetails cprider : carpoolriderdetailslist) {

				List<CarpoolRiderNotifications> cpridernotifications = carpoolRiderDetailsDAO
						.findRidersToNotifyByCPId(cprider.getCpid());
				logger.info("cpid " + cprider.getCpid());
				for (CarpoolRiderNotifications cpridernotify : cpridernotifications) {
					logger.info("cpridernotifications not null");
					if (!cpridernotify.isNotified()) {
						String riderEmail = cpridernotify.getEmailid();
						/*
						 
						 
						 
						 
						 */

						// logic to send mail to rider(s)

						// setting notify status to true so the rider wont recieve notification
						// again when another rider cancels a ride
						cpridernotify.setNotified(true);
						carpoolRiderDetailsDAO.updatecpridernotifications(cpridernotify);
					}
				}

			}

			logger.info("cpid " + carpoolriderdetailslist.get(0).getCpid());

			String driverEmail = carpooldetailsDAO.getDriverEmailByCPId(carpoolriderdetailslist.get(0).getCpid());

			logger.info("logic to send mail to driver");

			return CarpoolRiderDetailsServiceUtil.convertDaoTODto(cpriderlist);

		}
	}

	/**
	 * @author Mahesh Bheemanapalli
	 * @param rewards
	 * @return ServiceStatusDto class with status and message
	 */
	@Override
	public ServiceStatusDto addRewards(double rewards) {
		// TODO Auto-generated method stub
		logger.info("CarPoolRiderDetailsServiceImpl : addRewards : To Rider");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		String updaterewardPointsWithId = carpoolRiderDetailsDAO.addRewards(rewards);
		if (ObjectUtils.anyNotNull(updaterewardPointsWithId)) {
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(updaterewardPointsWithId);
		}
		return serviceStatusDto;
	}

	@Override
	public Map<String, List<CarpoolRiderOptedDetailsDto>> findCarpoolRiderDetailsByParentId(int parentid) {

		List<Carpooldetails> carpooldetails = carpooldetailsDAO.findCarpoolDetailsByParentId(parentid);
		System.out.println("list if carpool details by parent id"+carpooldetails);
		List<CarpoolRiderOptedDetailsDto> riderOptedDetailsDto = new ArrayList<>();
		for (Carpooldetails details : carpooldetails) {
			System.out.println(details.getParentid());
			List<CarpoolRiderDetails> carpoolRiderDetails = carpoolRiderDetailsDAO
					.findCarpoolRiderDetailsByCPId(details.getParentid());
			
			System.out.println("carpool rider details by parentId:"+carpoolRiderDetails);
			for (CarpoolRiderDetails riderDetails : carpoolRiderDetails) {
				CarpoolRiderOptedDetailsDto optedDetailsDto = new CarpoolRiderOptedDetailsDto();
				RegisterDomain domain = registerDAO.findByemailId(riderDetails.getEmailid());
				User user = userDAO.findByEmailId(riderDetails.getEmailid());
				optedDetailsDto.setEmailId(domain.getEmailid());
				optedDetailsDto.setMobile(domain.getMobile());
				optedDetailsDto.setName(user.getUserName());
				optedDetailsDto.setReason(riderDetails.getReason());
				optedDetailsDto.setStatus(riderDetails.getStatus());
				optedDetailsDto.setCarpoolRiderDetailsId(riderDetails.getId());
				optedDetailsDto.setFromdate(details.getFromDate());
				optedDetailsDto.setCarpoolId(details.getId());
				riderOptedDetailsDto.add(optedDetailsDto);
			}

		}
		return riderOptedDetailsDto.stream().collect(Collectors.groupingBy(CarpoolRiderOptedDetailsDto::getFromdate));
	}

	/**
	 * @author Mahesh Bheemanapalli This method is used to clean the
	 *         carpoolriderNotification data in cp_carpoolridernotifications table
	 * @return ServiceStatusDto class with status and message
	 */
	@Override
	public ServiceStatusDto cleanCarpoolRiderNotifications() {
		// TODO Auto-generated method stub
		logger.info("CarPoolRiderDetailsServiceImpl : cleanCarpoolRiderNotifications");
		ServiceStatusDto statusDto = new ServiceStatusDto();
		String carpoolRiderNotifications = carpoolRiderDetailsDAO.cleanCarpoolRiderNotifications();
		logger.info("CarPoolRiderDetailsServiceImpl : cleanCarpoolRiderNotifications : " + carpoolRiderNotifications);
		if (ObjectUtils.anyNotNull(carpoolRiderNotifications)) {
			statusDto.setMessage(carpoolRiderNotifications);
			statusDto.setStatus(true);
		}
		return statusDto;
	}

	@Override
	public List<CarpoolRiderDetails> saveOptedRiderDetails(List<CarpoolRiderDetailsDTO> carpoolRiderDetailsDTO) {

		List<CarpoolRiderDetails> saveOptedRiderDetails = null;
		List<CarpoolRiderDetails> carpoolRiderDetails = CarpoolRiderDetailsServiceUtil
				.convertOptedRiderDtoToDao(carpoolRiderDetailsDTO);

		// The list of carpoolRiderDetails save in to db
		try {
			logger.info("CarPoolRiderDetailsServiceImpl::saveOptedRiderDetails");

			if (carpoolRiderDetails != null && carpoolRiderDetails.size() != 0)

				saveOptedRiderDetails = carpoolRiderdetailsDAO.saveOptedRiderDetails(carpoolRiderDetails);
		} catch (Exception e) {
		}
		return saveOptedRiderDetails;
	}

}
