package com.nisum.carpool.rest.api;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.carpool.service.api.CarpoolRiderDetailsService;
import com.nisum.carpool.service.api.CarpooldetailsService;
import com.nisum.carpool.service.dto.CarpooldetailsDto;
import com.nisum.carpool.service.dto.CustomerCarpooldetailsDto;
import com.nisum.carpool.service.dto.DriverCarPoolDto;
import com.nisum.carpool.service.dto.Errors;
import com.nisum.carpool.service.dto.ParentCarpoolDetailsDto;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.service.exception.CarpooldetailsServiceException;
import com.nisum.carpool.util.Constants;



@RestController
@RequestMapping(value="/v1/carpool")
public class CarpooldetailsRestService {
	

	private static Logger logger = LoggerFactory.getLogger(CarpooldetailsRestService.class);
	@Autowired
	CarpooldetailsService carpooldetailsService;
	
	@Autowired
	CarpoolRiderDetailsService carpoolRiderService;
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public ResponseEntity<?> updateCarpooldetails(@RequestBody CarpooldetailsDto carpooldetailsDto){
		logger.info("CarpooldetailsRestService :: updateCarpooldetails");
		ResponseEntity<?> responseEntity = null;
		try {
			ServiceStatusDto statusDto = carpooldetailsService.updateCarpooldetails(carpooldetailsDto);
			if(statusDto.isStatus()) {
				responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
		}
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("500");
			error.setErrorMessage(e.getMessage());
			responseEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		return responseEntity;
		
	}
	
	@RequestMapping(value="/cancel",method=RequestMethod.PUT)
	public ResponseEntity<?> cancelCarpooldetails(@RequestBody CarpooldetailsDto carpooldetailsDto){
		logger.info("Enter CarpooldetailsRestService :: cancel Carpooldetails");
		logger.info("in cancel pool Id="+carpooldetailsDto.getId()+"parentId=="+carpooldetailsDto.getParentid()+"total seats="+carpooldetailsDto.getTotalNoOfSeats());
		ResponseEntity<?> responseEntity = null;
		try {
			ServiceStatusDto statusDto = carpooldetailsService.cancelCarpooldetails(carpooldetailsDto);
			if(statusDto.isStatus()) {
				responseEntity = new ResponseEntity<ServiceStatusDto>(statusDto, HttpStatus.OK);
		}
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("BAD REQUEST");
			error.setErrorMessage(Constants.MSG_CANCEL_CARPOOL_FAILED);
			responseEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		
		//update in Carpool rider
		try {
			String cancelRider=	carpoolRiderService.cancelCarpoolRiderDetails(carpooldetailsDto.getId());
			logger.info("msg for Carpoll rider cancel"+cancelRider);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseEntity;
		
	}
	
	/**
	 * @author Manohar Dhavala
	 * 
	 *         This method is used to for creating car pool
	 */

	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createCarPool(@RequestBody CarpooldetailsDto carpooldetailsDto) {

		logger.info("CarPoolRestService :: createCarPool :: Creating Car Pool");

		return carpooldetailsService.createCarPooldetails(carpooldetailsDto);

	}
	
	
	@RequestMapping(value = "/getCarPoolDetails", method = RequestMethod.GET)
	public ResponseEntity<?> getCarPoolDetails(@RequestParam(required = false, value = "location") String location)
			{
		
		List<CustomerCarpooldetailsDto> poolList=null;
		try
		{
			poolList=carpooldetailsService.getCarPoolDetails(location);
			
			/*if(poolList==null || poolList.isEmpty())
			{
				return new ResponseEntity<String>(Constants.NO_RECORDS_FOUND, HttpStatus.OK);	
			}*/
			return new ResponseEntity<List<CustomerCarpooldetailsDto>>(poolList, HttpStatus.OK);
			
		}
		catch (Exception e) {
			
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * @author Harish Kumar Gudivada
	 * @param id
	 * @return ResponseEntity
	 */
	
	@RequestMapping(value="/getCarpoolRideData/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getCarpoolDetailsById(@PathVariable int id){
		CarpooldetailsDto carpoolDto=null;
		logger.info("Entered into CarpooldetailsRestService :: getCarpoolDetailsById");
		try {
			carpoolDto=carpooldetailsService.loadCarpoolDetailsById(id);
			if(carpoolDto!=null && carpoolDto.getId()==0) {
				return new ResponseEntity<String>("Data Is Not Available", HttpStatus.NO_CONTENT);
			}
		}catch (Exception e) {
			logger.error("Exception Occured in Class:CarpooldetailsRestService Method:getCarpoolDetailsById Message:"+e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		logger.info("Exit from CarpooldetailsRestService :: getCarpoolDetailsById");
		return new ResponseEntity<CarpooldetailsDto>(carpoolDto, HttpStatus.OK);
	}
	
	/**
	 * @author suresh valavala
	 * @param emailId
	 * @return list of carpools
	 */
	
	@RequestMapping(value="/getLoggedInUserCarpoolDetails/{emailId:.+}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getCarpoolDetailsByEmailId(@PathVariable String emailId){
		List<CarpooldetailsDto> carpoolDtoList=null;
		try {
			carpoolDtoList = carpooldetailsService.loadCarpoolDetailsByEmailId(emailId);
			if(CollectionUtils.isEmpty(carpoolDtoList)) {
				return new ResponseEntity<String>("Data Is Not Available", HttpStatus.NO_CONTENT);
			}
		}catch (Exception e) {
			Errors error = new Errors();
			error.setErrorCode("500");
			error.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(error, HttpStatus.BAD_REQUEST);
		}
		 ResponseEntity<List<CarpooldetailsDto>> entity = new ResponseEntity<List<CarpooldetailsDto>>(carpoolDtoList, HttpStatus.OK);
		 return entity;
	}
	
	

	@RequestMapping(value = "/getMySharedRides/{email:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCarpoolsByDriver(@PathVariable("email")String email) 
	{
		logger.info("BEGIN: getAllCarpoolsByDriver() in the CarpooldetailsRestService");
		try {
	return new ResponseEntity<List<ParentCarpoolDetailsDto>>(carpooldetailsService.getCarpoolsByDriver(email),HttpStatus.OK);
		} catch (CarpooldetailsServiceException ex) {
			logger.error("ERROR:some thing went wrong while fetching getAllCarpoolsByDriver");
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/getSharedRidesByParentId/{parentID}", method = RequestMethod.GET)
	public ResponseEntity<?> getCarpoolsByParentID(@PathVariable("parentID")Integer id) 
	{
		logger.info("BEGIN: getAllParentCpsByDrievrID() in the CarpooldetailsRestService");
		try {
			return new ResponseEntity<List<DriverCarPoolDto>>(carpooldetailsService.getCarPoolsByParentId(id),HttpStatus.OK);
		} catch (CarpooldetailsServiceException ex) {
			logger.error("ERROR:some thing went wrong while fetching getAllCarpoolsByDriver");
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
}
