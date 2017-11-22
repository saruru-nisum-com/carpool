package com.nisum.carpool.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.data.dao.impl.CarpooldetailsDAOImpl;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.repository.CarpooldetailsRepository;


@RunWith(MockitoJUnitRunner.class)
public class CarpooldetailsDAOTest {
	@InjectMocks
	CarpooldetailsDAOImpl carpooldetailsDAOImpl;
	
	@Mock
	CarpooldetailsRepository carpooldetailsRepository;
	
	@Test
	public void updateCarpooldetailsTest1() {
		Timestamp createdDate=new Timestamp(1511249628);
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		Carpooldetails carpooldetails = new Carpooldetails();
		carpooldetails.setId(1);
		carpooldetails.setParentid(1);
		carpooldetails.setUserid("mbheemanapalli@nisum.com");
		carpooldetails.setVehicleType(2);
		carpooldetails.setNoofseats(1);
		carpooldetails.setFromDate("2017-11-23");
		carpooldetails.setToDate("2017-11-23");
		carpooldetails.setFromtime("12:30");
		carpooldetails.setToTime("13:30");
		carpooldetails.setStatus(1);
		carpooldetails.setCreateddate(createdDate);
		carpooldetails.setModifieddate(modifiedDate);
		when(carpooldetailsRepository.countByParentid(carpooldetails.getParentid())).thenReturn(1L);
		when(carpooldetailsRepository.save(carpooldetails)).thenReturn(carpooldetails);
		String actual=carpooldetailsDAOImpl.updateCarpooldetails(carpooldetails);
		String expected="Current record Updated Successfully !!";
		assertEquals(expected, actual);
	}
	@Test
	public void updateCarpooldetailsTest2() {
		Timestamp createdDate=new Timestamp(1511249628);
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		Carpooldetails carpooldetails = new Carpooldetails();
		carpooldetails.setId(1);
		carpooldetails.setParentid(1);
		carpooldetails.setUserid("mbheemanapalli@nisum.com");
		carpooldetails.setVehicleType(2);
		carpooldetails.setNoofseats(1);
		carpooldetails.setFromDate("2017-11-23");
		carpooldetails.setToDate("2017-11-23");
		carpooldetails.setFromtime("12:30");
		carpooldetails.setToTime("13:30");
		carpooldetails.setStatus(1);
		carpooldetails.setCreateddate(createdDate);
		carpooldetails.setModifieddate(modifiedDate);
		List<Integer> list= Arrays.asList(1,2);
		when(carpooldetailsRepository.getListOfIdsByParentid(carpooldetails.getParentid())).thenReturn(list);
		when(carpooldetailsRepository.udpateMultipleCarpoolDetails(carpooldetails, list)).thenReturn(2);
		String actual=carpooldetailsDAOImpl.updateCarpooldetails(carpooldetails);
		String expected="All CarpoolDetails Updated Successfully !!";
		assertEquals(expected, actual);
	}
}
