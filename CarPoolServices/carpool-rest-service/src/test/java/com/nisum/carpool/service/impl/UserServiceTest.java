package com.nisum.carpool.service.impl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

	User user=new User();
	UserDTO userDTO =new UserDTO();

	Timestamp timestamp;
	UserServiceUtil serviceUtil= new UserServiceUtil();

	@Before
	public void setUp()throws Exception{
		Timestamp createdDate=new Timestamp(1511249628);
		MockitoAnnotations.initMocks(this);
		userDTO.setUserId(1222);
		userDTO.setEmailId("abc@nisum.com");
		userDTO.setImage("");
		userDTO.setUserName("abc");
		userDTO.setLoginDate(CommonsUtil.getCurrentDateTime());
		userDTO.setCreateDate(CommonsUtil.getCurrentDateTime());

		user= new User(1222, "abc@nisum.com", "", createdDate.toLocalDateTime(), "", 
				createdDate.toLocalDateTime(),
				"", 2);
	}

	@Test
	public void testGetByUserId() {
		when(userDao.findUserById(userDTO.getUserId())).thenReturn(user);
		User user1 =	userServiceImpl.findUserById(userDTO.getUserId());
		assertNotNull(user1);
		assertEquals(user.getUserId(), user1.getUserId());

	}

	@Test
	public void testGetByEmailId() {

		when(userDao.findByEmailId(userDTO.getEmailId())).thenReturn(user);
		//UserDTO U=UserServiceUtil.convertDaoObjectToDto(user);
		UserDTO userDTO1=	userServiceImpl.findByEmailId(userDTO.getEmailId());

		assertNotNull(userDTO1);
		assertEquals(userDTO.getEmailId(), userDTO1.getEmailId());

	}

	@Test
	public void testAddUser() {
		doNothing().when(userDao).saveUser(any(user.getClass()));
		userServiceImpl.saveUser(userDTO);

	}

	@Test
		public void testUpdateUser() {
			userDTO.setUserName("john");
			User updateUser = UserServiceUtil.convertUpdateUserDtoTODao(userDTO);
			//userDTO=UserServiceUtil.convertDaoObjectToDto(user);
			when(userServiceImpl.findUserById(updateUser.getUserId())).thenReturn(user);
			//when(userDao.findUserById(userDTO.getUserId())).thenReturn(user);
			when(userDao.updateUser(updateUser)).thenReturn(user);
			UserDTO resultUserDTO=	userServiceImpl.updateUserDetails(userDTO);
			assertEquals(userDTO.getUserName(),resultUserDTO.getUserName());
			
		}
	//	



}
