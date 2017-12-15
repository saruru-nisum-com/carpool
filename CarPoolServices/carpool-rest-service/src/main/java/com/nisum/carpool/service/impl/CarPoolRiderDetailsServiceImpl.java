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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.nisum.carpool.data.util.Reject_Reason;
import com.nisum.carpool.data.util.Ride_Status;
import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.api.CarpooldetailsService;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.CarpoolRiderOptedDetailsDto;
import com.nisum.carpool.service.dto.GenericEmailDto;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.dto.RiderStatusDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.exception.CarpooldetailsServiceException;
import com.nisum.carpool.util.CarpoolRiderDetailsServiceUtil;
import com.nisum.carpool.util.Constants;
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
	CarpooldetailsService carpooldetailsservice;

	@Autowired
	GenericMailTemplate genericMailTemplateObj;
	
	Timestamp currentDate = new Timestamp(System.currentTimeMillis());

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

						carpoolRiderdetailsDto.setCpid(cpid);
						carpoolRiderdetailsDto.setRidermail(emailId);

						carpoolRiderdetailsDto.setStatus(car.getStatus());
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
	 * 
	 * @param List<RiderStatusDTO>
	 * 
	 * @return Boolean isRiderUpdated, isMailSent
	 * 
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
		logger.debug("Input Rider Status object list size " + riderObjSize);
		for (int riderVar = 0; riderVar <= riderObjSize - 1; riderVar++) {
			RiderStatusDTO riderStatusObj = (RiderStatusDTO) riderStatusListObj.get(riderVar); // Downcast into
			logger.debug("Each rider object " + riderStatusObj);
			System.out.println("Real object " + riderStatusObj);
			CarpoolRiderDetails carpoolRiderDaoObj = CarpoolRiderDetailsServiceUtil
					.convertRiderStatusDtoToDao(riderStatusObj);
			logger.debug(
					"Convert DTO to DAO Object  " + carpoolRiderDaoObj.getId() + " " + carpoolRiderDaoObj.getEmailid());
			Integer riderStatusCount = 0; //
			riderStatusCount = carpoolRiderdetailsDAO.updateRiderStatus(carpoolRiderDaoObj);
			logger.debug("Rider Status updated " + riderStatusCount);
			if (riderStatusCount > 0) { // If rider status record updated into db, should send mail to rider
				// Email send code start
				logger.debug("Email send implementation starts **** " + riderStatusCount);
				int id = riderStatusObj.getCpid();
				String emailId = riderStatusObj.getRiderEmailId() != "" ? riderStatusObj.getRiderEmailId() : "";
				if (emailId.equals("") || emailId == null) {
					logger.debug("Invalid email id " + emailId);
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
					Timestamp timestampObj = riderStatusObj.getCurrentDate();
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
	public ResponseEntity<?> cancelRiderBookingdetails(List<RiderBookingDetailsDTO> rides) {
		logger.info("carpoolriderdetailsserviceimpl:cancelling a ride");

		Map<String, GenericEmailDto> riderMap = new HashMap<String, GenericEmailDto>();
		Map<String, GenericEmailDto> driverMap = new HashMap<String, GenericEmailDto>();

		List<CarpoolRiderDetails> carpoolriderdetailslist = new ArrayList<CarpoolRiderDetails>();

		for (RiderBookingDetailsDTO ride : rides) {

			int cpid = ride.getCpid();
			String ridermail = ride.getRidermail();

			CarpoolRiderDetails crd = carpoolRiderdetailsDAO.findRideByCPIdAndEmail(cpid, ridermail);
			logger.info("cprider from table " + crd);
			carpoolriderdetailslist.add(crd);
		}

		String msg = carpoolRiderDetailsDAO.cancelRiderBookingdetails(carpoolriderdetailslist);

		String riderEmail = "";
		String driverEmail = "";

		if (msg.equals(Constants.CANCELING_RIDE_FAILED)) {

			logger.info("carpoolriderdetailsrestservice:Canceling ride failed");
			ServiceStatusDto statusDto = new ServiceStatusDto();
			statusDto.setStatus(false);
			statusDto.setMessage(Constants.CANCELING_RIDE_FAILED);
			ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto,
					HttpStatus.BAD_REQUEST);
			return entity;

		}

		else

		{
			logger.info("carpoolriderdetailsserviceimpl: carpool list is not null");

			for (CarpoolRiderDetails cprider : carpoolriderdetailslist) {

				logger.info("updating car pool status");

				try {
					carpooldetailsservice.UpdatecarpoolStatus(cprider.getCpid());
				} catch (CarpooldetailsServiceException e1) {
					// TODO Auto-generated catch block
					logger.info("error updating pool status");
					logger.info(e1.getMessage());
				}

				// end

				// sending mail to other notified riders

				List<CarpoolRiderNotifications> cpridernotifications = null;

				try {
					cpridernotifications = carpoolRiderDetailsDAO.findRidersToNotifyByCPId(cprider.getCpid());

				} catch (Exception e) {

					logger.info("Error finding riders to notify in cp_carpoolnotifications table");
					logger.info(e.getMessage());

				}

				logger.info("cpid " + cprider.getCpid());
				for (CarpoolRiderNotifications cpridernotify : cpridernotifications) {
					GenericEmailDto gmaildto = new GenericEmailDto();

					logger.info("cpridernotifications not null");
					if (!cpridernotify.isNotified()) {
						logger.info("cp rider notify value is 0");
						riderEmail = cpridernotify.getEmailid();

						Carpooldetails cpd = null;

						try {
							cpd = carpooldetailsDAO.getCarpoolByPoolID(cprider.getCpid());

						} catch (Exception e) {
							logger.info("Error finding carpool in cp_carpooldetails table");
							logger.info(e.getMessage());
						}

						if (cpd != null) {
							gmaildto.setDate(cpd.getFromDate());
							gmaildto.setLocation(cpd.getLocation());
							gmaildto.setStartTime(cpd.getFromtime());
							gmaildto.setReturnTime(cpd.getToTime());
							gmaildto.setStatus(Ride_Status.CANCELLED.getValue());
							gmaildto.setRemarks(Reject_Reason.values()[cprider.getReason()].toString());
							gmaildto.setUserName(userDAO.findByEmailId(riderEmail).getUserName());

							gmaildto.setIsRider(0);
							gmaildto.setNotifyMe(true);
							riderMap.put(riderEmail, gmaildto);

							// setting notify status to true so the rider wont recieve notification
							// again when another rider cancels a ride
							logger.info("setting notify");
							cpridernotify.setNotified(true);
							logger.info("cpridernotify " + cpridernotify.getEmailid() + " " + cpridernotify.getCpid()
									+ " " + cpridernotify.getId() + cpridernotify.isNotified());
							carpoolRiderDetailsDAO.updatecpridernotifications(cpridernotify);

						}
					}

				}

				// end

				Carpooldetails cpd = null;
				logger.info("sending mail to driver");
				try {
					driverEmail = carpooldetailsDAO.getDriverEmailByCPId(cprider.getCpid());

				} catch (Exception e) {
					logger.info("Error finding driver email in cp_carpooldetails table");
					logger.info(e.getMessage());
				}
				logger.info("driver mail" + driverEmail);
				try {
					cpd = carpooldetailsDAO.getCarpoolByPoolID(cprider.getCpid());
				} catch (Exception e) {
					logger.info("Error finding carpool in cp_carpooldetails table");
					logger.info(e.getMessage());
				}

				if (cpd != null) {

					GenericEmailDto gmaildto = new GenericEmailDto();

					gmaildto.setDate(cpd.getFromDate());
					gmaildto.setLocation(cpd.getLocation());
					gmaildto.setStartTime(cpd.getFromtime());
					gmaildto.setReturnTime(cpd.getToTime());
					gmaildto.setStatus(Ride_Status.CANCELLED.getValue());

					User userRider = null;
					User userDriver = null;

					try {

						userRider = userDAO.findByEmailId(carpoolriderdetailslist.get(0).getEmailid());

					} catch (Exception e) {
						logger.info("Error finding user details in user table");
						logger.info(e.getMessage());
					}

					if (userRider != null)
						gmaildto.setRiderUserName(userRider.getUserName());
					gmaildto.setRemarks(Reject_Reason.values()[cprider.getReason()].toString());

					try {

						userDriver = userDAO.findByEmailId(driverEmail);

					} catch (Exception e) {

						logger.info("Error finding user details in user table");
						logger.info(e.getMessage());
					}

					if (userDriver != null)
						gmaildto.setUserName(userDriver.getUserName());
					gmaildto.setNotifyMe(false);
					gmaildto.setIsRider(1);

					logger.info("gmail dto " + gmaildto.toString());

					driverMap.put(driverEmail, gmaildto);

					// end

				}

			}

		}
		logger.info("mail sender");
		try {
			if (riderEmail != null && !riderEmail.equals(""))
				genericMailTemplateObj.sendGenericMail("mdhavala@nisum.com", riderMap);
			if (driverEmail != null && !driverEmail.equals(""))
				genericMailTemplateObj.sendGenericMail("mdhavala@nisum.com", driverMap);
		} catch (Exception e) {
			logger.info(e.getMessage());
			ServiceStatusDto statusDto = new ServiceStatusDto();
			statusDto.setStatus(false);
			statusDto.setMessage(Constants.SENDING_MAIL_FAILED);
			ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto,
					HttpStatus.BAD_REQUEST);
			return entity;
		}

		List<RiderBookingDetailsDTO> rbdlist = getRiderBookingDetails(carpoolriderdetailslist.get(0).getEmailid());

		logger.info("carpoolriderdetailsrestservice:Successfully cancelled a ride");
		ResponseEntity<List<RiderBookingDetailsDTO>> entity = new ResponseEntity<List<RiderBookingDetailsDTO>>(rbdlist,
				HttpStatus.OK);
		return entity;

	}

	/**
	 * @author Mahesh Bheemanapalli
	 * @param rewards
	 * @return ServiceStatusDto class with status and message
	 */
	@Override
	public ServiceStatusDto addRewards(double rewards) {
		// TODO Auto-generated method stub
		logger.info("BEGIN :: CarPoolRiderDetailsServiceImpl : addRewards : To Rider");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		String updaterewardPointsWithId = carpoolRiderDetailsDAO.addRewards(rewards);
		if (ObjectUtils.anyNotNull(updaterewardPointsWithId)) {
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(updaterewardPointsWithId);
			logger.info("CLOSED :: CarPoolRiderDetailsServiceImpl : addRewards : To Rider : "
					+ serviceStatusDto.getMessage());
		}
		return serviceStatusDto;
	}

	@Override
	public Map<String, List<CarpoolRiderOptedDetailsDto>> findCarpoolRiderDetailsByParentId(int parentid) {

		List<Carpooldetails> carpooldetails = carpooldetailsDAO.findCarpoolDetailsByParentId(parentid);
		System.out.println("list if carpool details by parent id" + carpooldetails);
		List<CarpoolRiderOptedDetailsDto> riderOptedDetailsDto = new ArrayList<>();
		for (Carpooldetails details : carpooldetails) {
			System.out.println(details.getParentid());
			List<CarpoolRiderDetails> carpoolRiderDetails = carpoolRiderDetailsDAO
					.findCarpoolRiderDetailsByCPId(details.getParentid());

			System.out.println("carpool rider details by parentId:" + carpoolRiderDetails);
			for (CarpoolRiderDetails riderDetails : carpoolRiderDetails) {
				CarpoolRiderOptedDetailsDto optedDetailsDto = new CarpoolRiderOptedDetailsDto();
				RegisterDomain domain = registerDAO.findByemailId(riderDetails.getEmailid());
				User user = userDAO.findByEmailId(riderDetails.getEmailid());
				optedDetailsDto.setEmailId(domain.getEmailid());
				optedDetailsDto.setMobile(domain.getMobile());
				optedDetailsDto.setName(user.getUserName());
				optedDetailsDto.setReason(riderDetails.getReason());
				Ride_Status ride_Status = Ride_Status.values()[(riderDetails.getStatus() - 1)];

				optedDetailsDto.setStatusName(ride_Status.toString());

				// optedDetailsDto.setStatus(riderDetails.getStatus());
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
		logger.info("BEGIN :: CarPoolRiderDetailsServiceImpl : cleanCarpoolRiderNotifications");
		ServiceStatusDto statusDto = new ServiceStatusDto();
		String carpoolRiderNotifications = carpoolRiderDetailsDAO.cleanCarpoolRiderNotifications();
		if (ObjectUtils.anyNotNull(carpoolRiderNotifications)) {
			statusDto.setMessage(carpoolRiderNotifications);
			statusDto.setStatus(true);
			logger.info("CLOSED :: CarPoolRiderDetailsServiceImpl : cleanCarpoolRiderNotifications : status : "
					+ statusDto.getMessage());
		}
		return statusDto;
	}

	@Override
	public List<CarpoolRiderDetails> saveOptedRiderDetails(List<CarpoolRiderDetailsDTO> carpoolRiderDetailsDTO) {

		List<CarpoolRiderDetails> saveOptedRiderDetails = null;
		
      for (CarpoolRiderDetailsDTO carpoolRiderDetails : carpoolRiderDetailsDTO) {
  	  carpoolRiderDetails.setLocation(carpooldetailsDAO.findCarpoolDetailsByCpId(carpoolRiderDetails.getCpid()).getLocation());
  	 
  	  
	}

		List<CarpoolRiderDetails> carpoolRiderDetails = CarpoolRiderDetailsServiceUtil
				.convertOptedRiderDtoToDao(carpoolRiderDetailsDTO);

		try {
			logger.info("CarPoolRiderDetailsServiceImpl::saveOptedRiderDetails");

			if (carpoolRiderDetails != null && carpoolRiderDetails.size() != 0)

				saveOptedRiderDetails = carpoolRiderdetailsDAO.saveOptedRiderDetails(carpoolRiderDetails);
		} catch (Exception e) {
      

 
		}
		return saveOptedRiderDetails;
	}

	/**
	 * @author Simhadri Naidu Guntreddi
	 * 
	 *         This method is used for MyRides Details History based on emailID
	 * 
	 */

	@Override
	public List<RiderBookingDetailsDTO> getRiderBookingHistory(String email, RiderBookingDetailsDTO riderHistoryDTO)
			throws Exception {
		logger.info("BEGIN :: CarPoolRiderDetailsServiceImpl : myRiderDetailsHistory");

		List<RiderBookingDetailsDTO> riderBookingDetailsDtoList = new ArrayList<>();
		List<CarpoolRiderDetails> carpoolRiderDetailsList = carpoolRiderDetailsDAO.getRiderBookingDetails(email);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		Date today = sdf.parse(date);
		List<RiderBookingDetailsDTO> riderBookingDetailsDtoList1 = new ArrayList<>();

		for (CarpoolRiderDetails carpoolRiderDetails : carpoolRiderDetailsList) {
			RiderBookingDetailsDTO riderBookingDetailsHistoryDTO = new RiderBookingDetailsDTO();
			Carpooldetails carpooldetails = carpooldetailsDAO.getCarpoolByPoolID(carpoolRiderDetails.getCpid());
			if (carpooldetails != null) {
				riderBookingDetailsHistoryDTO.setEmail(carpooldetails.getEmailId());
				riderBookingDetailsHistoryDTO.setFromDate(carpooldetails.getFromDate());
				riderBookingDetailsHistoryDTO.setToDate(carpooldetails.getToDate());
				riderBookingDetailsHistoryDTO.setLocation(carpooldetails.getLocation());
				// riderBookingDetailsHistoryDTO.setStatus(carpoolRiderDetails.getStatus());
				Ride_Status ride_Status = Ride_Status.values()[(carpoolRiderDetails.getStatus() - 1)];
				riderBookingDetailsHistoryDTO.setStatusName(ride_Status.toString());
				riderBookingDetailsHistoryDTO.setReason(carpoolRiderDetails.getReason());
				riderBookingDetailsHistoryDTO.setVehicleType(carpooldetails.getVehicleType());
				User user = userDAO.findByEmailId(carpooldetails.getEmailId());
				if (user != null) {
					riderBookingDetailsHistoryDTO.setUserName(user.getUserName());
				}
				List<RegisterDomain> registerDomainList = registerDAO
						.findUserRegistrationByUserId(carpooldetails.getEmailId());

				if (registerDomainList != null && registerDomainList.size() > 0) {
					for (RegisterDomain registerDomain : registerDomainList) {
						if (registerDomain.getIsrider() == 1) {
							riderBookingDetailsHistoryDTO.setMobile(registerDomain.getMobile());
						}
					}
				}

			}
			riderBookingDetailsDtoList.add(riderBookingDetailsHistoryDTO);
		}
		if ((riderHistoryDTO.getToDate() != null && riderHistoryDTO.getFromDate() != null)
				|| riderHistoryDTO.getLocation() != null || riderHistoryDTO.getStatus() != 0) {

			for (RiderBookingDetailsDTO dto : riderBookingDetailsDtoList) {
				if (riderHistoryDTO.getLocation() != null) {
					if (!(dto.getLocation().equals(riderHistoryDTO.getLocation()))) {
						continue;

					}
				}
				if (riderHistoryDTO.getFromDate() != null && riderHistoryDTO.getToDate() != null) {
					Date fromDate = sdf.parse(riderHistoryDTO.getFromDate());
					Date toDate = sdf.parse(riderHistoryDTO.getToDate());

					if ((fromDate.after(today) || toDate.after(today) || toDate.before(fromDate)
							|| fromDate.before(fromDate)))
						continue;

				}
				if (riderHistoryDTO.getStatus() != 0) {
					if (!(dto.getStatus() == riderHistoryDTO.getStatus())) {
						continue;

					}
				}

				riderBookingDetailsDtoList1.add(dto);
			}

		}
		logger.info("END :: CarPoolRiderDetailsServiceImpl::myRiderDetailsHistory");

		return riderBookingDetailsDtoList1;
	}
}
