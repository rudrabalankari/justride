package com.ssdi.justride;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.justride.models.Car;
import com.justride.models.User;
import com.justride.service.JustRideService;

public class ViewCarsTest {

	private JustRideService justRideService;

	@Before
	public void testSetUp() {

		justRideService = new JustRideService();
	}

	@Test
	public void carsValidation() {

		ArrayList<Car> validCarListTest = new ArrayList<Car>();

		ArrayList<String> locationListTest = new ArrayList<String>();
		locationListTest.add("UNC, Charlotte");
		assertEquals(1, locationListTest.size());

		validCarListTest = justRideService.getValidCarList("2017-10-27 20:30:10", "2017-10-27 20:30:10",
				locationListTest);
		assertEquals(12, validCarListTest.size());

		// to check if car already booked is not being returned
		validCarListTest = justRideService.getValidCarList("2017-10-25 10:30:10", "2017-10-25 18:30:10",
				locationListTest);
		assertEquals(9, validCarListTest.size());

		// if selected time interval falls inside the duration of a car already
		// booked
		validCarListTest = justRideService.getValidCarList("2017-10-25 09:30:10", "2017-10-25 19:30:10",
				locationListTest);
		assertEquals(9, validCarListTest.size());

		// if the booked duration of a car already booked falls inside the
		// selected time interval in home page
		validCarListTest = justRideService.getValidCarList("2017-10-25 11:30:10", "2017-10-25 13:30:10",
				locationListTest);
		assertEquals(11, validCarListTest.size());

		// to check different locations
		ArrayList<String> locationListTest1 = new ArrayList<String>();
		locationListTest1.add("Down Town");
		validCarListTest = justRideService.getValidCarList("2017-10-27 20:30:10", "2017-10-27 20:30:10",
				locationListTest1);
		assertEquals(8, validCarListTest.size());

		ArrayList<String> locationListTest2 = new ArrayList<String>();
		locationListTest2.add("Concord  Mills");
		validCarListTest = justRideService.getValidCarList("2017-10-27 20:30:10", "2017-10-27 20:30:10",
				locationListTest2);
		assertEquals(6, validCarListTest.size());

		Car carIDTest = justRideService.getCarbyID(1);
		assertEquals("nano", carIDTest.getCarName());

	}

}
