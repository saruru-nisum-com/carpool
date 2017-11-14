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
				postRideDto.setPostRideId(postRide.getPostRideId());
				postRideDto.setVehicleType(postRide.getVehicleType());
				postRideDto.setTotalNoOfSeats(postRide.getTotalNoOfSeats());
				postRideDto.setFromDate(postRide.getFromDate());
				postRideDto.setToDate(postRide.getToDate());
				postRideDto.setStartTime(postRide.getStartTime());
				postRideDto.setEndTime(postRide.getEndTime());
				postRideDtos.add(postRideDto);
			});
		}
		return postRideDtos;

	}

	public static PostRideDto convertDaoToDtoInstance(PostRideDomain postRideDomain) {

		PostRideDto postRideDto = new PostRideDto();
		if (ObjectUtils.anyNotNull(postRideDomain)) {
			postRideDto.setPostRideId(postRideDomain.getPostRideId());
			postRideDto.setVehicleType(postRideDomain.getVehicleType());
			postRideDto.setTotalNoOfSeats(postRideDomain.getTotalNoOfSeats());
			postRideDto.setFromDate(postRideDomain.getFromDate());
			postRideDto.setToDate(postRideDomain.getToDate());
			postRideDto.setStartTime(postRideDomain.getStartTime());
			postRideDto.setEndTime(postRideDomain.getEndTime());
		}
		return postRideDto;
	}

	public static PostRideDomain convertDtoTODao(PostRideDto postRideDto) {

		PostRideDomain postRideDomain = new PostRideDomain();
		postRideDomain.setPostRideId(postRideDto.getPostRideId());
		postRideDomain.setVehicleType(postRideDto.getVehicleType());
		postRideDomain.setTotalNoOfSeats(postRideDto.getTotalNoOfSeats());
		postRideDomain.setFromDate(postRideDto.getFromDate());
		postRideDomain.setToDate(postRideDto.getToDate());
		postRideDomain.setStartTime(postRideDto.getStartTime());
		postRideDomain.setEndTime(postRideDto.getEndTime());
		return postRideDomain;

	}

}
