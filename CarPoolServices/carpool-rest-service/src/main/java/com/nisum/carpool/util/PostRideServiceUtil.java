package com.nisum.carpool.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import com.nisum.carpool.data.domain.PostRideDomain;
import com.nisum.carpool.service.dto.PostRideDto;


public class PostRideServiceUtil {
	public static List<PostRideDto> convertDaoTODto(List<PostRideDomain> postRideList) {
		List<PostRideDto> postRideDtos = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(postRideList)) {
			postRideList.forEach(postRide->{
				PostRideDto postRideDto = new PostRideDto();
				postRideDto.setId(postRide.getId());
				postRideDto.setParentid(postRide.getParentid());
				postRideDto.setUserid(postRide.getUserid());
				postRideDto.setVehicleType(postRide.getVehicleType());
				postRideDto.setTotalNoOfSeats(postRide.getNoofseats());
				postRideDto.setFromDate(postRide.getFromDate());
				postRideDto.setToDate(postRide.getToDate());
				postRideDto.setStartTime(postRide.getStartTime());
				postRideDto.setToTime(postRide.getToTime());
				postRideDto.setStatus(postRide.getStatus());
				postRideDto.setCreateddate(postRide.getCreateddate());
				postRideDto.setModifieddate(postRide.getModifieddate());
				postRideDtos.add(postRideDto);
			});
		}
		return postRideDtos;

	}

	public static PostRideDto convertDaoToDtoInstance(PostRideDomain postRideDomain) {

		PostRideDto postRideDto = new PostRideDto();
		if (ObjectUtils.anyNotNull(postRideDomain)) {
			postRideDto.setId(postRideDomain.getId());
			postRideDto.setParentid(postRideDomain.getParentid());
			postRideDto.setUserid(postRideDomain.getUserid());
			postRideDto.setVehicleType(postRideDomain.getVehicleType());
			postRideDto.setTotalNoOfSeats(postRideDomain.getNoofseats());
			postRideDto.setFromDate(postRideDomain.getFromDate());
			postRideDto.setToDate(postRideDomain.getToDate());
			postRideDto.setStartTime(postRideDomain.getStartTime());
			postRideDto.setToTime(postRideDomain.getToTime());
			postRideDto.setStatus(postRideDomain.getStatus());
			postRideDto.setCreateddate(postRideDomain.getCreateddate());
			postRideDto.setModifieddate(postRideDomain.getModifieddate());
		}
		return postRideDto;
	}

	public static PostRideDomain convertDtoTODao(PostRideDto postRideDto) {

		PostRideDomain postRideDomain = new PostRideDomain();
		postRideDomain.setId(postRideDto.getId());
		postRideDomain.setParentid(postRideDto.getParentid());
		postRideDomain.setUserid(postRideDto.getUserid());
		postRideDomain.setVehicleType(postRideDto.getVehicleType());
		postRideDomain.setNoofseats(postRideDto.getTotalNoOfSeats());
		postRideDomain.setFromDate(postRideDto.getFromDate());
		postRideDomain.setToDate(postRideDto.getToDate());
		postRideDomain.setStartTime(postRideDto.getStartTime());
		postRideDomain.setToTime(postRideDto.getToTime());
		postRideDomain.setStatus(postRideDto.getStatus());
		postRideDomain.setCreateddate(postRideDto.getCreateddate());
		postRideDomain.setModifieddate(postRideDto.getModifieddate());
		return postRideDomain;

	}

}
