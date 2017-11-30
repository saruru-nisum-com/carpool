package com.nisum.carpool.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.data.dao.api.CarpooldetailsDAO;
import com.nisum.carpool.data.domain.Carpooldetails;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.CarpooldetailsServiceUtil;

@RunWith(MockitoJUnitRunner.class)
public class CarpooldetailsServiceImplTest {
	
	@Mock
	CarpooldetailsDAO carpooldetailsDAO;
	@Mock
	ObjectUtils objectUtils;
	@InjectMocks
	CarpooldetailsServiceImpl carpooldetailsServiceImpl;

	@Test
	public void updateCarpooldetailsTest() {
		Timestamp createdDate = new Timestamp(1511249628);
		Timestamp modifiedDate = carpooldetailsServiceImpl.modifiedDate;
		CarpooldetailsDto carpooldetailsDto = new CarpooldetailsDto();
		carpooldetailsDto.setId(1);
		carpooldetailsDto.setParentid(1);
		carpooldetailsDto.setEmailId("mbheemanapalli@nisum.com");
		carpooldetailsDto.setVehicleType(2);
		carpooldetailsDto.setTotalNoOfSeats(1);
		carpooldetailsDto.setFromDate("2017-11-23");
		carpooldetailsDto.setToDate("2017-11-23");
		carpooldetailsDto.setStartTime("12:30");
		carpooldetailsDto.setToTime("13:30");
		carpooldetailsDto.setStatus(1);
		carpooldetailsDto.setCreateddate(createdDate);
		carpooldetailsDto.setModifieddate(modifiedDate);
		Carpooldetails convertDtoTODao = CarpooldetailsServiceUtil.convertDtoTODao(carpooldetailsDto);
		when(carpooldetailsDAO.updateCarpooldetails(convertDtoTODao))
				.thenReturn("Current record Updated Successfully !!");
		// when(objectUtils.allNotNull(convertDtoTODao)).thenReturn(true);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setStatus(true);
		serviceStatusDto.setMessage("Current record Updated Successfully !!");
		ServiceStatusDto actualStatus = carpooldetailsServiceImpl.updateCarpooldetails(carpooldetailsDto);
		assertEquals(serviceStatusDto.getMessage(), actualStatus.getMessage());
	}
}
