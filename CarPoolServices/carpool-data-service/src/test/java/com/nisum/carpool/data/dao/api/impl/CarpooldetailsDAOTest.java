package com.nisum.carpool.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.data.dao.impl.CarpooldetailsDAOImpl;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.repository.CarpooldetailsRepository;
import com.nisum.carpool.data.util.Constants;


@RunWith(MockitoJUnitRunner.class)
public class CarpooldetailsDAOTest {
	@InjectMocks
	CarpooldetailsDAOImpl carpooldetailsDAOImpl;
	
	@Mock
	CarpooldetailsRepository carpooldetailsRepository;
	
        Carpooldetails carpooldetails;

	
	List<Carpooldetails> carpooldetailsExpectedList;
	
	@Before
	public void setup() {
		carpooldetailsExpectedList=new ArrayList<Carpooldetails>();
        carpooldetails=new Carpooldetails();
		carpooldetails.setCreateddate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
		carpooldetails.setFromDate("13254345");
		carpooldetails.setId(1);
		carpooldetails.setModifieddate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
		carpooldetails.setNoofseats(20);
		carpooldetails.setParentid(112);
		carpooldetails.setFromtime("23467");
		carpooldetails.setStatus(1);
		carpooldetails.setToDate("14356u756i7op");
		carpooldetails.setToTime("987");
		carpooldetails.setEmailId("wefgre@jh.com");
		carpooldetails.setVehicleType(2);
		
	}
	
	
	@Test
	public void updateCarpooldetailsTest1() {
		Timestamp createdDate=new Timestamp(1511249628);
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		Carpooldetails carpooldetails = new Carpooldetails();
		carpooldetails.setId(1);
		carpooldetails.setParentid(1);
		carpooldetails.setEmailId("mbheemanapalli@nisum.com");
		carpooldetails.setVehicleType(2);
		carpooldetails.setNoofseats(1);
		carpooldetails.setFromDate("2017-11-23");
		carpooldetails.setToDate("2017-11-23");
		carpooldetails.setFromtime("12:30");
		carpooldetails.setToTime("13:30");
		carpooldetails.setStatus(1);
		carpooldetails.setCreateddate(createdDate.toLocalDateTime());
		carpooldetails.setModifieddate(modifiedDate.toLocalDateTime());
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
		carpooldetails.setEmailId("mbheemanapalli@nisum.com");
		carpooldetails.setVehicleType(2);
		carpooldetails.setNoofseats(1);
		carpooldetails.setFromDate("2017-11-23");
		carpooldetails.setToDate("2017-11-23");
		carpooldetails.setFromtime("12:30");
		carpooldetails.setToTime("13:30");
		carpooldetails.setStatus(1);
		carpooldetails.setCreateddate(createdDate.toLocalDateTime());
		carpooldetails.setModifieddate(modifiedDate.toLocalDateTime());
		List<Integer> list= Arrays.asList(1,2);
		when(carpooldetailsRepository.getListOfIdsByParentid(carpooldetails.getParentid())).thenReturn(list);
		when(carpooldetailsRepository.udpateMultipleCarpoolDetails(carpooldetails, list)).thenReturn(2);
		String actual=carpooldetailsDAOImpl.updateCarpooldetails(carpooldetails);
		String expected="All CarpoolDetails Updated Successfully !!";
		assertEquals(expected, actual);
	}
	
	@Test
	public void addCarpoolDetailsTest() {
		
	 carpooldetailsExpectedList.add(carpooldetails);
		//String response=Constants.MSG_CARPOOL_ADD;
		
		
		
		
		when(carpooldetailsRepository.save(carpooldetails)).thenReturn(carpooldetails);

 List<Carpooldetails> actual = carpooldetailsDAOImpl.addCarpoolDetails(carpooldetailsExpectedList);

		assertEquals(carpooldetailsExpectedList, actual);
		
		
		
	}
	
	@Test
	public void checkValidCarpoolTest() {
		String userid ="radhi@nisum.com"; 
		String fromdate ="23-02-2017"; 
		String todate = "24-09-2017";
		
		carpooldetails.setEmailId("radhi@nisum.com");
		carpooldetails.setFromDate("23-02-2017");
		carpooldetails.setToDate("24-09-2017");
		
		
		
		String response=Constants.VALID;
		
	    when(carpooldetailsRepository.findEntriesWithDate(userid, fromdate)).thenReturn(0);
		when(carpooldetailsRepository.findEntriesWithDate(userid, todate)).thenReturn(0);
	     String actual = carpooldetailsDAOImpl.checkValidCarpool(carpooldetails);
	

		assertEquals(response,actual);
		
		
		
	}
	
	@Test
	public void checkValidCarpoolFailTest() {
		String userid ="radhi@nisum.com"; 
		String fromdate ="23-02-2017"; 
		String todate = "24-09-2017";
		
		carpooldetails.setEmailId("radhi@nisum.com");
		carpooldetails.setFromDate("23-02-2017");
		carpooldetails.setToDate("24-09-2017");
		
		
		String response=Constants.CARPOOLEXISTS;
		
	    when(carpooldetailsRepository.findEntriesWithDate(userid, fromdate)).thenReturn(5);
		when(carpooldetailsRepository.findEntriesWithDate(userid, todate)).thenReturn(5);
	     String actual = carpooldetailsDAOImpl.checkValidCarpool(carpooldetails);
	
      
		assertEquals(response,actual);
		
		
	}	
	
}
