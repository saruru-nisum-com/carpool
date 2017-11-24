package com.nisum.carpool.service.impl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.carpool.data.dao.api.UserDAO;
import com.nisum.carpool.data.domain.User;
import com.nisum.carpool.service.dto.UserDTO;
import com.nisum.carpool.service.impl.UserServiceImpl;
import com.nisum.carpool.util.CommonsUtil;
import com.nisum.carpool.util.UserServiceUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	UserDAO userDao;
	
	User user = new User();
	UserDTO userDTO =new UserDTO();
	
	
	UserServiceUtil serviceUtil= new UserServiceUtil();
	
	@Before
	public void setUp()throws Exception{
		MockitoAnnotations.initMocks(this);
		userDTO.setUserId(1222);
		userDTO.setActiveStatus("YES");
		userDTO.setEmailId("abc@nisum.com");
		userDTO.setImage("");
		userDTO.setLoginDate(CommonsUtil.getCurrentDateTime());
		userDTO.setCreateDate(CommonsUtil.getCurrentDateTime());
		
	}
	
	@Test
	public void testGetByUserId() {
	when(userDao.findUserById(userDTO.getUserId())).thenReturn(user);
	User user1 =	userServiceImpl.findUserById(userDTO.getUserId());
	assertNotNull(user);
	assertEquals(user.getUserId(), user1.getUserId());
		
	}
	public void testGetByEmailId() {
		when(userDao.findByEmailId(userDTO.getEmailId())).thenReturn(user);
		UserDTO userDTO1=	userServiceImpl.findByEmailId(userDTO.getEmailId());
		
		assertNotNull(userDTO1);
		assertEquals(user.getEmailId(), userDTO1.getEmailId());
		
	}
	
	@Test
	public void testAddUser() {
		doNothing().when(userDao).saveUser(any(user.getClass()));
		userServiceImpl.saveUser(userDTO);
		
	}

	
	
	
}
