/**
 * 
 */
package com.nisum.carpool.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.carpool.data.dao.api.CarpoolRiderDetailsDAO;
import com.nisum.carpool.data.domain.CarpoolRiderDetails;
import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.dto.CarpoolRiderDetailsDTO;
import com.nisum.carpool.service.impl.CarPoolRiderDetailsServiceImpl;
import com.nisum.carpool.util.CarpoolRiderDetailsServiceUtil;

/**
 * @author Lakshmi Kiran
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CarpoolRiderDetailsTest {

	@InjectMocks
	CarpoolRiderDetailsRestService carpoolRiderDetailsRestService;

	@Mock
	CarPoolRiderDetailsServiceImpl carPoolRiderDetailsServiceImpl;;

	@Test
	public void findCarpoolRiderDetailsByCPIdTest() {
		List<CarpoolRiderDetailsDTO> list = new ArrayList<>();
		CarpoolRiderDetailsDTO riderDetailsDTO = new CarpoolRiderDetailsDTO();
		riderDetailsDTO.setCpid(1);
		riderDetailsDTO.setCreateddate(new Timestamp(1512049465));
		riderDetailsDTO.setEmailid("ltirumallasetti@nisum.com0");
		riderDetailsDTO.setId(1);
		riderDetailsDTO.setModifieddate(new Timestamp(1512049465));
		riderDetailsDTO.setNotifyme(false);
		riderDetailsDTO.setReason(1);
		riderDetailsDTO.setRewards(2.0);
		riderDetailsDTO.setStatus(1);
		list.add(riderDetailsDTO);
		
		ResponseEntity<List<CarpoolRiderDetailsDTO>> expected = new ResponseEntity<List<CarpoolRiderDetailsDTO>>(list, HttpStatus.OK);
		when(carPoolRiderDetailsServiceImpl.findCarpoolRiderDetailsByCPId(1)).thenReturn(list);
		ResponseEntity<List<CarpoolRiderDetailsDTO>> actual = carpoolRiderDetailsRestService.findCarpoolRiderDetailsByCPId(1);
		assertEquals(expected, actual);
	}
}
