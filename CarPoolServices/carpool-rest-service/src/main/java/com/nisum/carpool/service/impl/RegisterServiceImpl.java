package com.nisum.carpool.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.ObjectUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.nisum.carpool.data.dao.api.RegisterDAO;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.service.api.RegisterService;
import com.nisum.carpool.service.dto.RegisterDTO;
import com.nisum.carpool.service.dto.ServiceStatusDto;
import com.nisum.carpool.util.Constants;
import com.nisum.carpool.util.RegisterServiceUtil;
import com.nisum.carpool.util.UserServiceUtil;
@Service
public class RegisterServiceImpl  implements RegisterService{

	private static Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
	@Autowired
	private RegisterDAO registerDAO;
	public ServiceStatusDto registerDriverorRider(RegisterDTO registerDTO) {
		logger.info("RegisterServiceImpl: registerDriver:::");
		
		RegisterDomain registerDomain = RegisterServiceUtil.convertDaoObjectToDto(registerDTO);
		RegisterDomain rDomain =  registerDAO.registerDriverorRider(registerDomain);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		if(ObjectUtils.anyNotNull(rDomain)) {
			logger.info("RegisterServiceImpl:registerDriver ::" + rDomain.toString());
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(Constants.MSG_RECORD_ADD);
		}
		return serviceStatusDto;
	}
	

	/**
	 * @author Harish Kumar Gudivada
	 * 
	 * This method is used to load the user details based on user id there may be multiple records because the user may be a rider and a driver
	 */
	@Override
	public List<RegisterDTO> getUserRegistrationProfile(RegisterDTO dto) {
		List<RegisterDomain> list=registerDAO.findUserRegistrationByUserId(dto.getEmailId());
		List<RegisterDTO> listDto=new ArrayList<>();
		for(RegisterDomain reg:list) {
			RegisterDTO userRegDto = RegisterServiceUtil.convertDaoObjectToDto(reg);
			listDto.add(userRegDto);
		}
		return listDto;
	}

		
	@Override
	public String searchLocation(RegisterDTO regDto) throws Exception {

		JSONObject jsonObject = new JSONObject();
		String latitude = null;
		String longitude = null;
		try {
			RegisterDomain userRegistration = new RegisterDomain();
			JSONObject response = getLatLong(regDto.getLocation());
			latitude = response.get("latitude").toString();
			longitude = response.get("longitude").toString();
			userRegistration.setEmailid(regDto.getEmailId());
			userRegistration.setLocation(regDto.getLocation());
			userRegistration.setLatitude(latitude);
			userRegistration.setLongitude(longitude);
			// userRegistrationRepository.save(userRegistration);
			System.out.println("location...."+userRegistration.toString());
			return userRegistration.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return jsonObject.toString();

		}

	}

	@SuppressWarnings("unchecked")
	private JSONObject getLatLong(String address) throws Exception {
		JSONObject res = new JSONObject();
		int responseCode = 0;
		String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8")
				+ "&sensor=true";
		System.out.println("URL : " + api);
		URL url = new URL(api);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.connect();
		responseCode = httpConnection.getResponseCode();
		if (responseCode == 200) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(httpConnection.getInputStream());
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/GeocodeResponse/status");
			String status = (String) expr.evaluate(document, XPathConstants.STRING);
			if (status.equals("OK")) {
				expr = xpath.compile("//geometry/location/lat");
				String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
				expr = xpath.compile("//geometry/location/lng");
				String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
				res.put("latitude", latitude);
				res.put("longitude", longitude);
			} else {
				res.put("location", "Location not found");
				throw new Exception("Error from the API - response status: " + status);
			}

		}

		return res;

	}

	
}
