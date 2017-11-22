package com.nisum.carpool.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
	
}
