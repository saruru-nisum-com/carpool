package com.nisum.carpool.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;

import com.nisum.carpool.data.dao.impl.RegisterDAOImpl;
import com.nisum.carpool.data.domain.RegisterDomain;
import com.nisum.carpool.data.repository.RegisterRepository;

@RunWith(MockitoJUnitRunner.class)
public class RegisterDAOImplTest {

	@InjectMocks
	private RegisterDAOImpl registerDAOImpl;

	@Mock
	private RegisterRepository registerRepository;

	/**
	 * @author Rajendra Prasad Dava test case for fetching registered users from
	 *         repository to dao
	 */
	@Test
	public void findUserRegistrationByUserId() {
		List<RegisterDomain> registerListExpected = new ArrayList<>();
		RegisterDomain registerDomain = new RegisterDomain();
		registerDomain.setEmailid("user1");
		registerDomain.setEmailnotification(true);
		registerDomain.setCreateddate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
		registerDomain.setIsrider(1);
		registerDomain.setLocation("HYD");
		registerDomain.setLatitude("45");
		registerDomain.setLongitude("34");
		registerDomain.setMobile("1234567891");
		registerDomain.setModifieddate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
		registerDomain.setNearby("NAMPALLY");
		registerDomain.setRegistrationid(1);
		List<Integer> vehicleType = new ArrayList<>();
		vehicleType.add(0, Integer.valueOf(2));
		vehicleType.add(1, Integer.valueOf(4));
		registerDomain.setVehicletype(vehicleType);

		registerListExpected.add(registerDomain);

		when(registerRepository.findByEmailId("user1")).thenReturn(registerListExpected);

		List<RegisterDomain> actual = registerDAOImpl.findUserRegistrationByUserId("user1");

		assertEquals(registerListExpected, actual);
		;
	}
	

	@Test	
	public void testLocationOfRegisteredUser() {
		try {
			
			RegisterDomain regDom=new RegisterDomain();
			regDom.setLocation("Hyderabad");
			regDom.setLongitude("11.908");
			regDom.setLatitude("90.889");
			regDom.setIsrider(0);
			regDom.setEmailid("user1@gmail.com");
			
			List<RegisterDomain> domainObj =new ArrayList<>();
			domainObj.add(regDom);
			
			when(registerRepository.findByEmailId("user1@gmail.com")).thenReturn(domainObj);
			
			List<RegisterDomain> domain = registerDAOImpl.getLocationOfRegisteredUser("user1@gmail.com");
			
			RegisterDomain regUser=null;
			if(domain!=null) {
				for(RegisterDomain registedDao:domain) {
					if(registedDao!=null && registedDao.getIsrider() !=null && registedDao.getIsrider()==0)
						regUser=registedDao;
				}
			}
			assertEquals(regDom,regUser);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
