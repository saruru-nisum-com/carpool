package com.nisum.carpool.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.nisum.carpool.data.util.Pool_Status;


@RunWith(MockitoJUnitRunner.class)
public class CarpooldetailsDAOTest {
	@InjectMocks
	CarpooldetailsDAOImpl carpooldetailsDAOImpl;
	
	@Mock
	CarpooldetailsRepository carpooldetailsRepository;
	
    private Carpooldetails carpooldetails;
	
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
		carpooldetails.setStatus(Pool_Status.OPEN.getValue());
		carpooldetails.setToDate(new SimpleDateFormat("yyyy.MM.dd").format(new Timestamp(System.currentTimeMillis())));
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
		carpooldetails.setStatus(Pool_Status.OPEN.getValue());
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
		carpooldetails.setStatus(Pool_Status.OPEN.getValue());
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

 //List<Carpooldetails> actual = carpooldetailsDAOImpl.addCarpoolDetails(carpooldetailsExpectedList);

		//assertEquals(carpooldetailsExpectedList, actual);
		
		
		
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
		/*
	    when(carpooldetailsRepository.findEntriesWithDate(userid, fromdate)).thenReturn(0);
		when(carpooldetailsRepository.findEntriesWithDate(userid, todate)).thenReturn(0);
	     String actual = carpooldetailsDAOImpl.checkValidCarpool(carpooldetails);
		

		assertEquals(response,actual);
		*/
		
		
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
		/*
	    when(carpooldetailsRepository.findEntriesWithDate(userid, fromdate)).thenReturn(5);
		when(carpooldetailsRepository.findEntriesWithDate(userid, todate)).thenReturn(5);
	     String actual = carpooldetailsDAOImpl.checkValidCarpool(carpooldetails);
	
      
		assertEquals(response,actual);
		*/
		
	}	
	/**
	* author Mahesh Bheemanapalli
	*/
	@Test
	public void addRewardsTest() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String rewardedDate = currentDate.format(formatter);
		Integer status=Pool_Status.CLOSED.getValue();
		double rewards=1.00;
		String expectedResult = Constants.ADDED_REWARDS_TO_DRIVER;
		List<Integer> list = Arrays.asList(1,2);
		when(carpooldetailsRepository.getCarpooldetailsByToDateAndStatus(status, rewardedDate)).thenReturn(list);
		when(carpooldetailsRepository.udpateRewardPoints(rewards, list)).thenReturn(1);
		String actualResult = carpooldetailsDAOImpl.addRewards(rewards);
		assertEquals(expectedResult, actualResult);
	}
	/**
	* author Mahesh Bheemanapalli
	*/
	@Test
	public void addRewardsFailureTest() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String rewardedDate = currentDate.format(formatter);
		Integer status=Pool_Status.CLOSED.getValue();
		double rewards=1.00;
		String expectedResult = Constants.REWARDS_NOT_ADDED_DRIVER;
		List<Integer> list = Arrays.asList();
		when(carpooldetailsRepository.getCarpooldetailsByToDateAndStatus(status, rewardedDate)).thenReturn(list);
		when(carpooldetailsRepository.udpateRewardPoints(rewards, list)).thenReturn(0);
		String actualResult = carpooldetailsDAOImpl.addRewards(rewards);
		assertEquals(expectedResult, actualResult);
	}
	/**
	* author Mahesh Bheemanapalli
	*/
	@Test
	public void addRewardsFailureTest2() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String rewardedDate = currentDate.format(formatter);
		Integer status=Pool_Status.CLOSED.getValue();
		double rewards=1.00;
		List<Integer> list = Arrays.asList();
		when(carpooldetailsRepository.getCarpooldetailsByToDateAndStatus(status, rewardedDate)).thenThrow(NullPointerException.class);
		String actualResult = carpooldetailsDAOImpl.addRewards(rewards);
		assertEquals(null, actualResult);
	}
	/**
	* author Mahesh Bheemanapalli
	*/
	@Test
	public void updateCarpoolStatusToClosedTest() {
		Timestamp createdDate=new Timestamp(1513082462);
		Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todate = currentDate.format(formatter);
		Integer openStatus = Pool_Status.OPEN.getValue();
		Integer status=Pool_Status.CLOSED.getValue();
		
		List<Carpooldetails> carpoolDetailsList=new ArrayList<>();
		Carpooldetails carpooldetails1=new Carpooldetails();
		
		carpooldetails1.setId(1);
		carpooldetails1.setParentid(1);
		carpooldetails1.setEmailId("mbheemanapalli@nisum.com");
		carpooldetails1.setVehicleType(2);
		carpooldetails1.setNoofseats(1);
		carpooldetails1.setFromDate("2017-11-23");
		carpooldetails1.setToDate(todate);
		carpooldetails1.setFromtime("12:30");
		carpooldetails1.setToTime("13:30");
		carpooldetails1.setStatus(openStatus);
		carpooldetails1.setCreateddate(createdDate.toLocalDateTime());
		carpooldetails1.setModifieddate(modifiedDate.toLocalDateTime());
		
		Carpooldetails carpooldetails2=new Carpooldetails();
		
		carpooldetails2.setId(2);
		carpooldetails2.setParentid(2);
		carpooldetails2.setEmailId("mbheemanapalli@nisum.com");
		carpooldetails2.setVehicleType(4);
		carpooldetails2.setNoofseats(1);
		carpooldetails2.setFromDate("2017-11-23");
		carpooldetails2.setToDate(todate);
		carpooldetails2.setFromtime("12:30");
		carpooldetails2.setToTime("13:30");
		carpooldetails2.setStatus(openStatus);
		carpooldetails2.setCreateddate(createdDate.toLocalDateTime());
		carpooldetails2.setModifieddate(modifiedDate.toLocalDateTime());
		
		carpoolDetailsList.add(carpooldetails1);
		carpoolDetailsList.add(carpooldetails2);
		
		when(carpooldetailsRepository.getCarpoolsByToDate(todate)).thenReturn(carpoolDetailsList);

		Set<Integer> setOfPoolIds = carpoolDetailsList.stream().filter(p -> p.getStatus() != Pool_Status.CLOSED.getValue()).map(p -> p.getId()).collect(Collectors.toSet());
		assertEquals(setOfPoolIds.size(), 2);
		String expectedResult=Constants.CARPOOL_STATUS_UPDATED;
		Integer beforeUpdate=0;
		Integer afterUpdate=2;
		when(carpooldetailsRepository.checkUpdateCarpoolStatusClosedCount(status)).thenReturn(beforeUpdate);
		when(carpooldetailsRepository.updateCarpoolStatusBySetOfPoolIds(status, setOfPoolIds)).thenReturn(1);
		when(carpooldetailsRepository.checkUpdateCarpoolStatusClosedCount(status)).thenReturn(afterUpdate);
		
		String actualResult = carpooldetailsDAOImpl.updateCarpoolStatusToClosed();
		assertEquals(expectedResult, actualResult);
	}
}
