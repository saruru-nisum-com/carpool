package com.nisum.carpool.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.dao.api.RegisterDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.data.util.Constants;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.impl.CarpooldetailsServiceImpl;
import com.nisum.carpool.util.CarpooldetailsServiceUtil;
import com.nisum.carpool.util.CommonsUtil;

/**
 * @author Manohar Dhavala
 * 
 *         This class is used for testing all methods as part of 
 *         Create Car Pool functionality in CarpooldetailsServiceImpl class
 */

@SpringBootTest
@RunWith(PowerMockRunner.class)
@PrepareForTest( {CarpooldetailsServiceUtil.class,CarpooldetailsServiceImpl.class} )
public class CreateCarpoolTest {
	
	
	@Mock
	CarpooldetailsDAO carpooldetailsdao;
	
	@Mock
	RegisterDAO registerDAO;
	
	@InjectMocks
	CarpooldetailsServiceImpl carpooldetailsserviceimpl;
	
	@Before
	public void initModelObjects() {
		
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This test is used for testing createCarPooldetails method 
	 *         in CarpooldetailsServiceImpl class
	 */

	@Test
	public void createCarPooldetails_CarpooldetailsServiceImpl_Test() {
		
		
		Carpooldetails cpd = new Carpooldetails();
		
		
		CarpooldetailsDto dto = new CarpooldetailsDto();
		
		PowerMockito.mockStatic(CarpooldetailsServiceUtil.class);
		
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		
		dto.setId(12345);
		dto.setParentid(12345);
		dto.setFromDate("05/11/2017");
		dto.setToDate("05/14/2017");
		dto.setCreateddate(t1);
		dto.setModifieddate(t1);
		dto.setTotalNoOfSeats(4);
		dto.setStatus(1);
		dto.setStartTime("1600");
		dto.setToTime("2000");
		dto.setEmailId("mdak@gmail.com");

		dto.setVehicleType(4);
		dto.setLocation("hyderabad");
		
		cpd.setId(12345);
		cpd.setParentid(12345);
		cpd.setFromDate("05/11/2017");
		cpd.setToDate("05/14/2017");
		cpd.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		cpd.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		cpd.setNoofseats(4);
		cpd.setStatus(1);
		cpd.setFromtime("1600");
		cpd.setToTime("2000");
		cpd.setEmailId("mdak@gmail.com");

		cpd.setVehicleType(4);
		cpd.setLocation("hyderabad");
		
		PowerMockito.when(CarpooldetailsServiceUtil.convertDtoTODao(dto)).thenReturn(cpd);
		
		when(carpooldetailsdao.checkValidCarpool("mdak@gmail.com",cpd.getFromDate(),cpd.getToDate())).thenReturn(Constants.VALID);
		
		List<Carpooldetails> cplist = new ArrayList<Carpooldetails>();
		
		
		Carpooldetails c1 = new Carpooldetails();
		
		c1.setId(12345);
		c1.setParentid(12345);
		c1.setFromDate("05/11/2017");
		c1.setToDate("05/14/2017");
		c1.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c1.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c1.setNoofseats(4);
		c1.setStatus(1);
		c1.setFromtime("1600");
		c1.setToTime("2000");
		c1.setEmailId("mdak@gmail.com");

		c1.setVehicleType(4);
		c1.setLocation("hyderabad");
		
		
		Carpooldetails c2 = new Carpooldetails();
		
		c2.setId(87698);
		c2.setParentid(12345);
		c2.setFromDate("05/11/2017");
		c2.setToDate("05/11/2017");
		c2.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c2.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c2.setNoofseats(4);
		c2.setStatus(1);
		c2.setFromtime("1600");
		c2.setToTime("2000");
		c2.setEmailId("mdak@gmail.com");

		c2.setVehicleType(4);
		c2.setLocation("hyderabad");
		
		Carpooldetails c3 = new Carpooldetails();
		
		c3.setId(56438);
		c3.setParentid(12345);
		c3.setFromDate("05/12/2017");
		c3.setToDate("05/12/2017");
		c3.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c3.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c3.setNoofseats(4);
		c3.setStatus(1);
		c3.setFromtime("1600");
		c3.setToTime("2000");
		c3.setEmailId("mdak@gmail.com");
		c3.setVehicleType(4);
		c3.setLocation("hyderabad");
		
		Carpooldetails c4 = new Carpooldetails();
		
		c4.setId(41232);
		c4.setParentid(12345);
		c4.setFromDate("05/13/2017");
		c4.setToDate("05/13/2017");
		c4.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c4.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c4.setNoofseats(4);
		c4.setStatus(1);
		c4.setFromtime("1600");
		c4.setToTime("2000");
		c4.setEmailId("mdak@gmail.com");
		c4.setVehicleType(4);
		c4.setLocation("hyderabad");
		
		Carpooldetails c5 = new Carpooldetails();
		
		c5.setId(32113);
		c5.setParentid(12345);
		c5.setFromDate("05/14/2017");
		c5.setToDate("05/14/2017");
		c5.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c5.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c5.setNoofseats(4);
		c5.setStatus(1);
		c5.setFromtime("1600");
		c5.setToTime("2000");
		c5.setEmailId("mdak@gmail.com");
		c5.setVehicleType(4);
		c5.setLocation("hyderabad");
		
		cplist.add(c1);
		cplist.add(c2);
		cplist.add(c3);
		cplist.add(c4);
		cplist.add(c5);
		
		String location ="hyderabad";
		String longitude ="1110000";
		String latitude ="222000";
		
		RegisterDomain regDomain=new RegisterDomain();
		regDomain.setLocation("hyderabad");
		regDomain.setLongitude("1110000");
		regDomain.setLatitude("222000");
		
		
		when(registerDAO.getLocationOfRegisteredUser("mdak@gmail.com")).thenReturn(regDomain);
		RegisterDomain domain = registerDAO.getLocationOfRegisteredUser("mdak@gmail.com");
		
		assertEquals(location,domain.getLocation());
		assertEquals(longitude,domain.getLongitude());
		assertEquals(latitude,domain.getLatitude());
		
		
		PowerMockito.mockStatic(CarpooldetailsServiceImpl.class);
		
		when(CarpooldetailsServiceImpl.processPostRideDomain(cpd)).thenReturn(cplist);
		
		when(carpooldetailsdao.addCarpoolDetails(cplist)).thenReturn(Constants.MSG_CARPOOL_ADD);
		
		List<CarpooldetailsDto> cpdtolist = new ArrayList<CarpooldetailsDto>();
	
		CarpooldetailsDto c11 = new CarpooldetailsDto();
		
		c11.setId(12345);
		c11.setParentid(12345);
		c11.setFromDate("05/11/2017");
		c11.setToDate("05/14/2017");
		c11.setCreateddate(t1);
		c11.setModifieddate(t1);
		c11.setTotalNoOfSeats(4);
		c11.setStatus(1);
		c11.setStartTime("1600");
		c11.setToTime("2000");
		c11.setEmailId("mdak@gmail.com");

		c11.setVehicleType(4);
		c11.setLocation("hyderabad");
		
		CarpooldetailsDto c22 = new CarpooldetailsDto();
		
		c22.setId(87698);
		c22.setParentid(12345);
		c22.setFromDate("05/11/2017");
		c22.setToDate("05/11/2017");
		c22.setCreateddate(t1);
		c22.setModifieddate(t1);
		c22.setTotalNoOfSeats(4);
		c22.setStatus(1);
		c22.setStartTime("1600");
		c22.setToTime("2000");
		c22.setEmailId("mdak@gmail.com");
		c22.setVehicleType(4);
		c22.setLocation("hyderabad");
		
		CarpooldetailsDto c33 = new CarpooldetailsDto();
		
		c33.setId(56438);
		c33.setParentid(12345);
		c33.setFromDate("05/12/2017");
		c33.setToDate("05/12/2017");
		c33.setCreateddate(t1);
		c33.setModifieddate(t1);
		c33.setTotalNoOfSeats(4);
		c33.setStatus(1);
		c33.setStartTime("1600");
		c33.setToTime("2000");
		c33.setEmailId("mdak@gmail.com");
		c33.setVehicleType(4);
		c33.setLocation("hyderabad");
		
		CarpooldetailsDto c44 = new CarpooldetailsDto();
		
		c44.setId(41232);
		c44.setParentid(12345);
		c44.setFromDate("05/13/2017");
		c44.setToDate("05/13/2017");
		c44.setCreateddate(t1);
		c44.setModifieddate(t1);
		c44.setTotalNoOfSeats(4);
		c44.setStatus(1);
		c44.setStartTime("1600");
		c44.setToTime("2000");
		c44.setEmailId("mdak@gmail.com");
		c44.setVehicleType(4);
		c44.setLocation("hyderabad");
		
		CarpooldetailsDto c55 = new CarpooldetailsDto();
		
		c55.setId(32113);
		c55.setParentid(12345);
		c55.setFromDate("05/14/2017");
		c55.setToDate("05/14/2017");
		c55.setCreateddate(t1);
		c55.setModifieddate(t1);
		c55.setTotalNoOfSeats(4);
		c55.setStatus(1);
		c55.setStartTime("1600");
		c55.setToTime("2000");
		c55.setEmailId("mdak@gmail.com");
		c55.setVehicleType(4);
		c55.setLocation("hyderabad");
		
		cpdtolist.add(c11);
		cpdtolist.add(c22);
		cpdtolist.add(c33);
		cpdtolist.add(c44);
		cpdtolist.add(c55);
		
		when(carpooldetailsdao.getCarPoolByMailID("mdak@gmail.com")).thenReturn(cplist);

		PowerMockito.when(CarpooldetailsServiceUtil.convertDaoTODto(cplist)).thenReturn(cpdtolist);
		
		ResponseEntity<List<CarpooldetailsDto>> entity = new ResponseEntity<List<CarpooldetailsDto>>(cpdtolist,
				HttpStatus.OK);
		
		assertEquals(entity, carpooldetailsserviceimpl.createCarPooldetails(dto));

		
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This test is used for testing processPostRideDomain method 
	 *         in CarpooldetailsServiceImpl class
	 */

	

	@Test
	public void processPostRideDomain_CarpooldetailsServiceImpl_Test() {
		
		PowerMockito.mockStatic(CarpooldetailsServiceUtil.class);
		
		PowerMockito.when(CarpooldetailsServiceUtil.getNo_of_days("05/11/2017", "05/14/2017")).thenReturn(3);
		
		PowerMockito.when(CarpooldetailsServiceUtil.getRandomInt()).thenReturn(12345).thenReturn(87698).thenReturn(56438)
		.thenReturn(41232).thenReturn(32113);
		
		PowerMockito.when(CarpooldetailsServiceUtil.getAddedDate("05/11/2017", 0)).thenReturn("05/11/2017");
		
		PowerMockito.when(CarpooldetailsServiceUtil.getAddedDate("05/11/2017", 1)).thenReturn("05/12/2017");
		
		PowerMockito.when(CarpooldetailsServiceUtil.getAddedDate("05/11/2017", 2)).thenReturn("05/13/2017");
		
		PowerMockito.when(CarpooldetailsServiceUtil.getAddedDate("05/11/2017", 3)).thenReturn("05/14/2017");
		
		List<Carpooldetails> cplist = new ArrayList<Carpooldetails>();
		
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		
		PowerMockito.mockStatic(System.class);
		
		PowerMockito.when(System.currentTimeMillis()).thenReturn(t1.getTime());
		
		Carpooldetails c1 = new Carpooldetails();
		
		c1.setId(12345);
		c1.setParentid(12345);
		c1.setFromDate("05/11/2017");
		c1.setToDate("05/14/2017");
		c1.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c1.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c1.setNoofseats(4);
		c1.setStatus(1);
		c1.setFromtime("1600");
		c1.setToTime("2000");
		c1.setEmailId("mdak@gmail.com");
		c1.setVehicleType(4);
		
		
		Carpooldetails c2 = new Carpooldetails();
		
		c2.setId(87698);
		c2.setParentid(12345);
		c2.setFromDate("05/11/2017");
		c2.setToDate("05/11/2017");
		c2.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c2.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c2.setNoofseats(4);
		c2.setStatus(1);
		c2.setFromtime("1600");
		c2.setToTime("2000");
		c2.setEmailId("mdak@gmail.com");
		c2.setVehicleType(4);
		
		Carpooldetails c3 = new Carpooldetails();
		
		c3.setId(56438);
		c3.setParentid(12345);
		c3.setFromDate("05/12/2017");
		c3.setToDate("05/12/2017");
		c3.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c3.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c3.setNoofseats(4);
		c3.setStatus(1);
		c3.setFromtime("1600");
		c3.setToTime("2000");
		c3.setEmailId("mdak@gmail.com");
		c3.setVehicleType(4);
		
		Carpooldetails c4 = new Carpooldetails();
		
		c4.setId(41232);
		c4.setParentid(12345);
		c4.setFromDate("05/13/2017");
		c4.setToDate("05/13/2017");
		c4.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c4.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c4.setNoofseats(4);
		c4.setStatus(1);
		c4.setFromtime("1600");
		c4.setToTime("2000");
		c4.setEmailId("mdak@gmail.com");
		c4.setVehicleType(4);
		
		Carpooldetails c5 = new Carpooldetails();
		
		c5.setId(32113);
		c5.setParentid(12345);
		c5.setFromDate("05/14/2017");
		c5.setToDate("05/14/2017");
		c5.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c5.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c5.setNoofseats(4);
		c5.setStatus(1);
		c5.setFromtime("1600");
		c5.setToTime("2000");
		c5.setEmailId("mdak@gmail.com");
		c5.setVehicleType(4);
		
		cplist.add(c1);
		cplist.add(c2);
		cplist.add(c3);
		cplist.add(c4);
		cplist.add(c5);
		
		Carpooldetails cpd = new Carpooldetails();
		
		cpd.setId(12345);
		cpd.setParentid(12345);
		cpd.setFromDate("05/11/2017");
		cpd.setToDate("05/14/2017");
		cpd.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		cpd.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		cpd.setNoofseats(4);
		cpd.setStatus(1);
		cpd.setFromtime("1600");
		cpd.setToTime("2000");
		cpd.setEmailId("mdak@gmail.com");
		cpd.setVehicleType(4);
		
		assertEquals(cplist,CarpooldetailsServiceImpl.processPostRideDomain(cpd));
		
		
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This test is used for testing success case in checkValidCarpool method 
	 *         in CarpooldetailsServiceImpl class
	 */

	
	@Test
	public void testcheckValidCarpoolSuccess_CarpooldetailsServiceImpl_Test() {
		
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		
		Carpooldetails c1 = new Carpooldetails();
		
		c1.setId(12345);
		c1.setParentid(12345);
		c1.setFromDate("05/11/2017");
		c1.setToDate("05/14/2017");
		c1.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c1.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c1.setNoofseats(4);
		c1.setStatus(1);
		c1.setFromtime("1600");
		c1.setToTime("2000");
		c1.setEmailId("mdak@gmail.com");
		c1.setVehicleType(4);
		
		when(carpooldetailsdao.checkValidCarpool("mdak@gmail.com",c1.getFromDate(),c1.getToDate())).thenReturn(Constants.VALID);
		
		assertEquals(Constants.VALID, carpooldetailsserviceimpl.checkValidCarpool(c1));
		
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This test is used for testing invalid case in checkValidCarpool method 
	 *         in CarpooldetailsServiceImpl class
	 */
	
	@Test
	public void testcheckValidCarpoolFail_CarpooldetailsServiceImpl_Test() {
		
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		
		Carpooldetails c1 = new Carpooldetails();
		
		c1.setId(12345);
		c1.setParentid(12345);
		c1.setFromDate("05/11/2017");
		c1.setToDate("05/14/2017");
		c1.setCreateddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c1.setModifieddate(CommonsUtil.convertTimeStampToLocalDateTime(t1));
		c1.setNoofseats(4);
		c1.setStatus(1);
		c1.setFromtime("1600");
		c1.setToTime("2000");
		c1.setEmailId("mdak@gmail.com");
		c1.setVehicleType(4);
		
		when(carpooldetailsdao.checkValidCarpool("mdak@gmail.com",c1.getFromDate(),c1.getToDate())).thenReturn(Constants.CARPOOLEXISTS);
		
		assertEquals(Constants.CARPOOLEXISTS, carpooldetailsserviceimpl.checkValidCarpool(c1));
		
	}

}



	

	
