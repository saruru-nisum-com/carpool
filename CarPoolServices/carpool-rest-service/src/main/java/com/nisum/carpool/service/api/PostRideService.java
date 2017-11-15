package com.nisum.carpool.service.api;

import com.nisum.carpool.service.dto.PostRideDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;

public interface PostRideService {
	ServiceStatusDto updatePostRide(PostRideDto postRideDto);
}
