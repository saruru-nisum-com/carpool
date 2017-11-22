package com.nisum.carpool.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.nisum.carpool.data.dao.api.UserRegistrationDAO;
import com.nisum.carpool.data.domain.UserRegistration;
import com.nisum.carpool.service.api.UserRegistrationService;
import com.nisum.carpool.service.dto.UserRegistrationDto;
import com.nisum.carpool.util.UserServiceUtil;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{

	
	@Autowired
	UserRegistrationDAO regDao;
	
	
	@Override
	public UserRegistrationDto getUserRegistrationProfile(UserRegistrationDto dto) {
		UserRegistration reg = regDao.findUserRegistrationByUserId(dto.getUserId(), dto.getIsRider());
		UserRegistrationDto userRegDto = UserServiceUtil.convertDaoObjectToDto(reg);
		return userRegDto;
	}

	@Override
	public String searchLocation(UserRegistrationDto userRegistrationDto) throws Exception {

		JSONObject jsonObject = new JSONObject();
		String latitude = null;
		String longitude = null;
		try {
			UserRegistration userRegistration = new UserRegistration();
			JSONObject response = getLatLong(userRegistrationDto.getLocation());
			latitude = response.get("latitude").toString();
			longitude = response.get("longitude").toString();
			userRegistration.setUserId(userRegistrationDto.getUserId());
			userRegistration.setLocation(userRegistrationDto.getLocation());
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
