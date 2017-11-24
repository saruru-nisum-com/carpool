package com.nisum.carpool.rest.api;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.nisum.carpool.service.api.CarpooldetailsService;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.Constants;


@RunWith(MockitoJUnitRunner.class)
public class CarpooldetailsRestServiceTest {
	@InjectMocks
	CarpooldetailsRestService carpooldetailsRestService;
	@Mock
	CarpooldetailsService carpooldetailsService;
	@Test
	public void updateCarpooldetailsTest() {
		CarpooldetailsDto carpooldetailsDto=new CarpooldetailsDto();
		ServiceStatusDto serviceStatusExpected=new ServiceStatusDto();
		serviceStatusExpected.setStatus(true);
		serviceStatusExpected.setMessage("Current record Updated Successfully !!");
		when(carpooldetailsService.updateCarpooldetails(carpooldetailsDto)).thenReturn(serviceStatusExpected);
		ResponseEntity<?> responseEntity = carpooldetailsRestService.updateCarpooldetails(carpooldetailsDto);
		assertEquals(serviceStatusExpected, responseEntity.getBody());
	}
	@Test
	public void updateCarpooldetailsFailureTest() {
		CarpooldetailsDto carpooldetailsDto=new CarpooldetailsDto();
		when(carpooldetailsService.updateCarpooldetails(carpooldetailsDto)).thenThrow(NullPointerException.class);
		Errors error = new Errors();
		error.setErrorCode("BAD REQUEST");
		error.setErrorMessage(Constants.MSG_UPDATE_CARPOOL_FAILED);
		ResponseEntity<?> expectedResponseEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		ResponseEntity<?> actualResponseEntity = carpooldetailsRestService.updateCarpooldetails(carpooldetailsDto);
		assertEquals(expectedResponseEntity.getStatusCode(), actualResponseEntity.getStatusCode());
		assertEquals(expectedResponseEntity.getBody().toString(), actualResponseEntity.getBody().toString());
	}
	@Test
	public void createCarPoolTest() {
		List<CarpooldetailsDto>  carpooldetailsDtoList=new ArrayList<CarpooldetailsDto>();
		
		CarpooldetailsDto carpooldetailsDto=new CarpooldetailsDto();
		carpooldetailsDto.setCreateddate(new Timestamp(System.currentTimeMillis()));
		carpooldetailsDto.setFromDate("13254345");
		carpooldetailsDto.setId(1);
		carpooldetailsDto.setModifieddate(new Timestamp(System.currentTimeMillis()));
		carpooldetailsDto.setTotalNoOfSeats(20);
		carpooldetailsDto.setParentid(112);
		carpooldetailsDto.setStartTime("23467");
		carpooldetailsDto.setStatus(1);
		carpooldetailsDto.setToDate("14356u756i7op");
		carpooldetailsDto.setToTime("987");
		carpooldetailsDto.setUserid("wefgre@jh.com");
		carpooldetailsDto.setVehicleType(2);
		
		carpooldetailsDtoList.add(carpooldetailsDto);
		
		when(carpooldetailsService.createCarPooldetails(carpooldetailsDto)).thenReturn(carpooldetailsDtoList);
		
		ResponseEntity<List<CarpooldetailsDto>> entity = new ResponseEntity<List<CarpooldetailsDto>>(carpooldetailsDtoList, HttpStatus.OK);
		ResponseEntity<?> actual = carpooldetailsRestService.createCarPool(carpooldetailsDto);
		assertEquals(entity.getBody(), actual.getBody());
		
		
		
	}
	
	@Test
	public void createCarPoollistNullTest() {
		//List<CarpooldetailsDto>  carpooldetailsDtoList=new ArrayList<CarpooldetailsDto>();
		CarpooldetailsDto carpooldetailsDto=new CarpooldetailsDto();
		
		
		ServiceStatusDto statusDto = new ServiceStatusDto();
        statusDto.setStatus(false);
        statusDto.setMessage(Constants.MSG_CARPOOL_FAILED);
		
		when(carpooldetailsService.createCarPooldetails(carpooldetailsDto)).thenReturn(null);
		
		
        ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.BAD_REQUEST);
        
        ResponseEntity<ServiceStatusDto> actual = (ResponseEntity<ServiceStatusDto>) carpooldetailsRestService.createCarPool(carpooldetailsDto);
        //assertEquals(entity.getBody(), actual.getBody());
        
        assertThat(actual.getBody()).isEqualToComparingFieldByField(entity.getBody());
	}
	
	
	
}
