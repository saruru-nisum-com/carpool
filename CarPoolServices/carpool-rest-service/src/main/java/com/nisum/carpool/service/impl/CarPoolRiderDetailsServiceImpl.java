package com.nisum.carpool.service.impl;

import static org.assertj.core.api.Assertions.catchThrowable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.nisum.carpool.data.util.Pool_Status;
import com.nisum.carpool.data.util.Ride_Status;
import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.dto.CarpoolRiderOptedDetailsDto;
import com.nisum.carpool.service.dto.RiderBookingDetailsDTO;
import com.nisum.carpool.service.dto.RiderStatusDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.CarpoolRiderDetailsServiceUtil;

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
	CarpoolRiderDetailsDAO carpoolRiderDetailsDAO;

	@Override
	public List<CarpoolRiderDetailsDTO> findCarpoolRiderDetailsByCPId(int cpid) {

		List<CarpoolRiderDetails> cpList = carpoolRiderDetailsDAO.findCarpoolRiderDetailsByCPId(cpid);
		return CarpoolRiderDetailsServiceUtil.convertDaoTODto(cpList);
	}

	@Override
	public String cancelCarpoolRiderDetails(int cpid) {
		// TODO Auto-generated method stub

		String cancelRiderStatus = carpoolRiderDetailsDAO.cancelCarpoolRiderDetails(cpid);

		return cancelRiderStatus;
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

			SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
			String date = sd.format(date1);

			System.out.println("Date" + date);

			List<CarpoolRiderDetails> carpoolRiderLists = (List<CarpoolRiderDetails>) carpoolRiderDetailsDAO
					.getRiderBookingDetails(emailId);

			logger.info("" + carpoolRiderLists);
			if (carpoolRiderLists != null) {
				for (CarpoolRiderDetails car : carpoolRiderLists) {

					int cpid = car.getCpid();

					logger.info("cpid" + cpid + "date : " + date);

					List<Carpooldetails> carpoolList = carpooldetailsDAO.getCarPoolByCpIDandDate(cpid, date);

					for (Carpooldetails carPool : carpoolList) {

						RiderBookingDetailsDTO carpoolRiderdetailsDto = new RiderBookingDetailsDTO();
						emailid = carPool.getEmailId();
						System.out.println(emailid);
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
						
						Ride_Status ride_Status = Ride_Status.values()[(car.getStatus()-1)];
						
						
						carpoolRiderdetailsDto.setStatusName(ride_Status.toString());
						System.out.println(ride_Status.toString());
						

						//carpoolRiderdetailsDto.setStatus(car.getStatus());
						//carpoolRiderdetailsDto.setLocation(car.getLocation());
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
	 * MethodAuthor: @Rajesh Sekhamuri (non-Javadoc)
	 * 
	 * @see
	 * com.nisum.carpool.service.api.CarpoolRiderDetailsService#updateRiderStatus(
	 * java.util.List)
	 */
	@Override
	public void updateRiderStatus(List<RiderStatusDTO> riderStatusDtoListObj) {
		List riderStatusListObj = null;
		try {
			riderStatusListObj = riderStatusDtoListObj;
			int riderObjSize = riderStatusListObj.size();
			System.out.println("Input Rider Status object list size " + riderObjSize);
			for (int riderVar = 0; riderVar <= riderObjSize - 1; riderVar++) {
				RiderStatusDTO riderStatusObj = (RiderStatusDTO) riderStatusListObj.get(riderVar); // Downcast into
																									// RiderStatusDTO
				System.out.println("Real object " + riderStatusObj);
				CarpoolRiderDetails carpoolRiderDaoObj = CarpoolRiderDetailsServiceUtil
						.convertRiderStatusDtoToDao(riderStatusObj);
				System.out.println("DAO Object " + carpoolRiderDaoObj.getId() + " " + carpoolRiderDaoObj.getEmailid());
				Integer riderStatusCount = 0; // carpoolRiderdetailsDAO.updateRiderStatus(carpoolRiderDaoObj);
				if (riderStatusCount > 0) { // If rider status record updated into db, should send mail to rider
					// Email send code start
					System.out.println("Email implementation **** " + riderStatusCount);
				}
			}
		} catch (Exception e) {

		}
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
		List<CarpoolRiderOptedDetailsDto> riderOptedDetailsDto = new ArrayList<>();
		for (Carpooldetails details : carpooldetails) {
			List<CarpoolRiderDetails> carpoolRiderDetails = carpoolRiderDetailsDAO
					.findCarpoolRiderDetailsByCPId(details.getId());
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
	 * @author Mahesh Bheemanapalli
	 *	This method is used to clean the carpoolriderNotification data in cp_carpoolridernotifications table	
	 * @return ServiceStatusDto class with status and message
	 */
	@Override
	public ServiceStatusDto cleanCarpoolRiderNotifications() {
		// TODO Auto-generated method stub
		logger.info("CarPoolRiderDetailsServiceImpl : cleanCarpoolRiderNotifications");
		ServiceStatusDto statusDto = new ServiceStatusDto();
			String carpoolRiderNotifications = carpoolRiderDetailsDAO.cleanCarpoolRiderNotifications();
			logger.info("CarPoolRiderDetailsServiceImpl : cleanCarpoolRiderNotifications : "+carpoolRiderNotifications);
			if(ObjectUtils.anyNotNull(carpoolRiderNotifications)) {
				statusDto.setMessage(carpoolRiderNotifications);
				statusDto.setStatus(true);
			}
			return statusDto;
	}
}
