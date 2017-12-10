package com.nisum.carpool.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.util.Pool_Status;
import com.nisum.carpool.data.util.Ride_Status;
import com.nisum.carpool.service.dto.OptRideDto;

public class OptARideServiceUtil {

	public static OptRideDto convertToOptRideDtoOptedPool(Carpooldetails carpool, int optedCount, CarpoolRiderDetails carPoolRiderDetails) {
		OptRideDto optRideDto = new OptRideDto();
		optRideDto.setDate(carpool.getFromDate());
		optRideDto.setTotalSeats(carpool.getNoofseats());
		optRideDto.setOptedSeats(optedCount);
	    Ride_Status ride_Status = Ride_Status.values()[(carPoolRiderDetails.getStatus()-1)];
	    optRideDto.setStatus(ride_Status.toString());
		return optRideDto;
	}
	public static OptRideDto convertToOptRideDtoNotOptedPool(Carpooldetails carpool, int optedCount) {
		OptRideDto optRideDto = new OptRideDto();
		optRideDto.setDate(carpool.getFromDate());
		optRideDto.setTotalSeats(carpool.getNoofseats());
		optRideDto.setOptedSeats(optedCount);
		Pool_Status pool_Status = Pool_Status.values()[(carpool.getStatus()-1)];
	    optRideDto.setStatus(pool_Status.toString());
		return optRideDto;
	}

	public static CarpoolRiderDetails acceptRiderStatusList(CarpoolRiderDetails carpoolRiderDetails) {
		if (carpoolRiderDetails.getStatus() == Ride_Status.APPROVED.getValue()
				|| carpoolRiderDetails.getStatus() == Ride_Status.REQUESTED.getValue()) {
			return carpoolRiderDetails;
		}
		return null;
	}
	
	
	public static CarpoolRiderDetails acceptCarPoolStatusList(CarpoolRiderDetails carpoolRiderDetails) {
		if (carpoolRiderDetails.getStatus() == Pool_Status.OPEN.getValue()
				|| carpoolRiderDetails.getStatus() == Pool_Status.CLOSED.getValue()||
				carpoolRiderDetails.getStatus() == Pool_Status.COMPLETED.getValue()|| carpoolRiderDetails.getStatus() == Pool_Status.PARTIALLY_COMPLETED.getValue()) {
			return carpoolRiderDetails;
		}
		return null;
	}

	public static List<Carpooldetails> removeParentIdFromCarPoolList(List<Carpooldetails> carPoolsDataList,
			int parentId) {
		Iterator<Carpooldetails> itr = carPoolsDataList.iterator();
		while (itr.hasNext()) {
			if ((itr.next().getId().equals(parentId))) {
				itr.remove();
				break;
			}
		}
		return carPoolsDataList;
	}

	public static boolean findCpIdRiderEmailId(String emailId, CarpoolRiderDetails carPoolRiderDetails) {
		// TODO Auto-generated method stub
		
			if ((carPoolRiderDetails.getEmailid().equals(emailId))) {
				return true;
			}
		
		return false;
	}

}
