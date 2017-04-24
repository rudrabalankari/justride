package com.justride.servicetests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.justride.dao.BookingDao;
import com.justride.dao.CarDao;
import com.justride.dao.LocationDao;
import com.justride.dao.UserDao;
import com.justride.models.User;
import com.justride.service.UserService;

public class UserServiceTest {
	private UserService userService;
	private UserDao userDao;
	private User user;
	
	@Before
	public void testSetUp() {
		String testEmailID = "rbalanka3@uncc.edu";
		String testPassword = "pass";
		userService = new UserService();
		userDao = Mockito.mock(UserDao.class);
		user = new User("Rudra","Balankari","rbalanka3@uncc.edu","pass","9803193433","12345","28262","pass");
		when(userDao.validateEmail(testEmailID)).thenReturn(true);
		when(userDao.validateUser(testEmailID, testPassword)).thenReturn("home2");
		when(userDao.registrationSubmit(user)).thenReturn("existingEmail");		
	}
	

	@Test
	public void validateEmailTest() {
		String testEmailID = "rbalanka3@uncc.edu";
		Boolean existingIDValue = userService.validateEmail(testEmailID);
		assertEquals(true, existingIDValue);
	}
	
	@Test
	public void registrationSubmitTest() {
		String registrationStatus = userService.registrationSubmit(user);
		assertEquals("existingEmail", registrationStatus);
	}
	

	@Test
	public void validateUserTest() {
		String testEmailID = "rbalanka3@uncc.edu";
		String testPassword = "pass";
		String validUserValue = userService.validateUser(testEmailID, testPassword);
		assertEquals("home2", validUserValue);
	}
	

}
