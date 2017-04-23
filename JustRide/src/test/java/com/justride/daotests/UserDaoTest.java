package com.justride.daotests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.justride.dao.UserDao;
import com.justride.models.User;
import static org.mockito.Mockito.when;

public class UserDaoTest {

	private UserDao userDao;
	private User user;
	String testEmailID = "rbalanka3@uncc.edu";
	String testPassword = "pass";
	
	@Before
	public void setUp() throws Exception {
		userDao = new UserDao();
		user = Mockito.mock(User.class);
		when(user.getFirstName()).thenReturn("Rudra");
		when(user.getLastName()).thenReturn("Balankari");
		when(user.getEmail()).thenReturn("rbalanka3@uncc.edu");
		when(user.getPassword()).thenReturn("pass");
		when(user.getPhone()).thenReturn("9803193433");
		when(user.getCardNo()).thenReturn("12345");
		when(user.getZip()).thenReturn("28262");
	}

	@Test
	public void registrationSubmitTest() {
		String registrationStatus = userDao.registrationSubmit(user);
		assertEquals("existingEmail", registrationStatus);
	}
	
	@Test
	public void validateEmailTest() {
		Boolean existingIDValue = userDao.validateEmail(testEmailID);
		assertEquals(true, existingIDValue);		
		boolean invalidEmail = userDao.validateEmail("invalidEMail@test");
		assertEquals(false, invalidEmail);
	}
	
	@Test
	public void validateUserTest() {
		String validUserValue = userDao.validateUser(testEmailID, testPassword);
		assertEquals("home2", validUserValue);
		String validation = userDao.validateUser(testEmailID, "wrong password");
		assertEquals("invalidPwd", validation);
	}

}
