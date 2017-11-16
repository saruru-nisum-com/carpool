package com.nisum.carpool.data.dao.api;

import java.util.List;


import com.nisum.carpool.data.domain.PostRideDomain;

public interface PostRideDao {
	PostRideDomain updatePostRide(PostRideDomain postRideDomain);
	
	public String addPostRide(List<PostRideDomain> postRideDomain);
}
