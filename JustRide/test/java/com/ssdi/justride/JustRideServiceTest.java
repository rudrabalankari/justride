package com.ssdi.justride;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.support.JstlUtils;

import com.justride.models.User;

public class JustRideServiceTest {
	// JustRideService justRideService = new JustRideService();

	@Test
	public void emailValidation() {

		// boolean emailValidation =
		// justRideService.validateEmail("rbalanka2@uncc.edu");
		// assertEquals(true, emailValidation);

	}

	// @Test
	// public void registrationSubmitTest() {
	// User testUser = new User("testUser", "testLastname", "test@testttttt",
	// "testPass", "123456", "1234-123",
	// "1234");
	// String insertStatus = justRideService.registrationSubmit(testUser);
	// // each time we run the test, we have to change the parameter email
	// // because it is inserted each time the user is inserted into the
	// // database.
	// assertEquals("inserted", insertStatus);
	// }

	// car location

	// @Test
	// public void userValidation() {
	// User testUser = new User("testUser", "testLastname", "test@test",
	// "ere","testPass", "123456", "1234-123", "1234");
	// String email = testUser.getEmail();
	// String password = testUser.getPassword();
	// String validation = justRideService.validateUser(email, password);
	// assertEquals("home2", validation);
	//
	// // invalidPwd
	// validation = justRideService.validateUser(email, "wrong password");
	// assertEquals("invalidPwd", validation);
	//
	// // invalidEmail
	// boolean invalidEmail =
	// justRideService.validateEmail("invalidEMail@test");
	// assertEquals(false, invalidEmail);
	//
	// }

}
