package com.nisum.carpool.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;

@RestController
@RequestMapping(value="/v1/carpool")
public class CarpoolRiderDetailsRestService {
	
	@Autowired
	CarpoolRiderDetailsService carpoolRiderDetailsService;
	
	@RequestMapping(value = "/getRiderBookingDetails/{emailID}", method = RequestMethod.GET)
	public ResponseEntity<List<CarpoolRiderDetailsDTO>> getRiderBookingDetails(@PathVariable("emailID") String emailID){
		
		String emailId=emailID+".com";
		
		System.out.println("emailId"+emailId);
		
		List<CarpoolRiderDetailsDTO> poolList = carpoolRiderDetailsService.getRiderBookingDetails(emailId);
		return new ResponseEntity<List<CarpoolRiderDetailsDTO>>(poolList, HttpStatus.OK);
		
	}

}
