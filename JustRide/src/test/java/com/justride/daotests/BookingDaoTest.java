package com.justride.daotests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.justride.dao.BookingDao;
import com.justride.dao.CarDao;
import com.justride.dao.LocationDao;
import com.justride.models.Car;

public class BookingDaoTest {

	private BookingDao dao;
	private LocationDao locationDao;
	private CarDao carDao;


	@Before
	public void testSetUp() {
		dao = new BookingDao();
		locationDao = Mockito.mock(LocationDao.class);
		carDao = Mockito.mock(CarDao.class);	
		
		ArrayList<Integer> allCarIdList = new ArrayList<Integer>();
		ArrayList<String> allLocationsList = new ArrayList<String>();
		ArrayList<Integer> locationIdList = new ArrayList<Integer>();


		allCarIdList.add(1);
		allCarIdList.add(2);
		allCarIdList.add(3);
		allCarIdList.add(4);
		allCarIdList.add(5);
		allCarIdList.add(6);
		allCarIdList.add(7);
		allCarIdList.add(8);
		allCarIdList.add(9);
		allCarIdList.add(10);
		allCarIdList.add(11);
		allCarIdList.add(12);
		allCarIdList.add(13);
		allCarIdList.add(14);
		allCarIdList.add(15);
		allCarIdList.add(16);
		allCarIdList.add(17);
		allCarIdList.add(18);
		allCarIdList.add(19);
		allCarIdList.add(20);
		allCarIdList.add(21);
		allCarIdList.add(22);
		allCarIdList.add(23);
		allCarIdList.add(24);
		allCarIdList.add(25);
		allCarIdList.add(26);
		
		locationIdList.add(1);
		locationIdList.add(3);
		locationIdList.add(5);
		locationIdList.add(7);
		locationIdList.add(10);
		locationIdList.add(12);
		locationIdList.add(14);
		locationIdList.add(17);
		locationIdList.add(19);
		locationIdList.add(21);
		locationIdList.add(24);
		locationIdList.add(26);

		allLocationsList.add("UNC, Charlotte");

		when(carDao.getAllCarIds()).thenReturn(allCarIdList);
		when(locationDao.getCarIdsBylocation(allLocationsList)).thenReturn(locationIdList);
	}

	@Test
	public void getValidCarListTest() {

		ArrayList<Car> validCarListTest = new ArrayList<Car>();

		ArrayList<String> locationListTest = new ArrayList<String>();
		locationListTest.add("UNC, Charlotte");
		assertEquals(1, locationListTest.size());

		validCarListTest = dao.getValidCarList("2017-10-27 20:30:10", "2017-10-27 20:30:10",locationListTest);
		assertEquals(12, validCarListTest.size());

		// to check if car already booked is not being returned
		validCarListTest = dao.getValidCarList("2017-10-25 10:30:10", "2017-10-25 18:30:10",
				locationListTest);
		assertEquals(8, validCarListTest.size());

		// if selected time interval falls inside the duration of a car already
		// booked
		validCarListTest = dao.getValidCarList("2017-10-25 09:30:10", "2017-10-25 19:30:10",
				locationListTest);
		assertEquals(8, validCarListTest.size());

		// if the booked duration of a car already booked falls inside the
		// selected time interval in home page
		validCarListTest = dao.getValidCarList("2017-10-25 11:30:10", "2017-10-25 13:30:10",
				locationListTest);
		assertEquals(10, validCarListTest.size());

		// to check different locations
		ArrayList<String> locationListTest1 = new ArrayList<String>();
		locationListTest1.add("Down Town");
		validCarListTest = dao.getValidCarList("2017-10-27 20:30:10", "2017-10-27 20:30:10",
				locationListTest1);
		assertEquals(8, validCarListTest.size());

		ArrayList<String> locationListTest2 = new ArrayList<String>();
		locationListTest2.add("Concord Mills");
		validCarListTest = dao.getValidCarList("2017-10-27 20:30:10", "2017-10-27 20:30:10",
				locationListTest2);
		assertEquals(6, validCarListTest.size());

	}
	
	@Test
	public void CarsByLocCatSeatTest() {
		String[] locations = {"UNC, Charlotte"};
		String[] categories = {"Economy"};
		String[] seats = {"5"};
		
		ArrayList<Car> validCarListbyCategoryAndSeats = new ArrayList<Car>();
		validCarListbyCategoryAndSeats = dao.CarsByLocCatSeat(locations, categories, seats);
		assertEquals(5, validCarListbyCategoryAndSeats.size());
	}
	
	@Test

	public void CarsByLocCatTest() {
		String[] locations = {"UNC, Charlotte"};
		String[] categories = {"Economy"};
		
		ArrayList<Car> validCarListbyLocationAndCategory = new ArrayList<Car>();
		validCarListbyLocationAndCategory = dao.CarsByLocCat(locations, categories);
		assertEquals(5, validCarListbyLocationAndCategory.size());
	
	
	}
	
	@Test

	public void CarsByLocSeatsTest() {
		String[] locations = {"UNC, Charlotte"};
		String[] seats = {"7"};
		
		ArrayList<Car> validCarListbyLocationAndSeats = new ArrayList<Car>();
		validCarListbyLocationAndSeats = dao.CarsByLocSeats(locations, seats);
		assertEquals(2, validCarListbyLocationAndSeats.size());

	}
	
	@Test

	public void CarsBySeatsCatTest() {
		String[] categories = {"Economy"};
		String[] seats = {"7"};
		
		ArrayList<Car> validCarListbyCategoryAndSeats = new ArrayList<Car>();
		validCarListbyCategoryAndSeats = dao.CarsBySeatsCat(categories, seats);
		assertEquals(0, validCarListbyCategoryAndSeats.size());

	}
	
	@Test

	public void CarsByLocationsTest() {
		String[] locations = {"Concord Mills"};
		
		ArrayList<Car> validCarListbyLocations = new ArrayList<Car>();
		validCarListbyLocations = dao.CarsByLoc(locations);
		assertEquals(6, validCarListbyLocations.size());

	}
	
	@Test

	public void CarsByCategoriesTest() {
		String[] Categories = {"Economy"};
	
		ArrayList<Car> validCarListbyCategories = new ArrayList<Car>();
		validCarListbyCategories = dao.CarsByLoc(Categories);
		assertEquals(0, validCarListbyCategories.size());

	}
	
	@Test

	public void CarsBySeats() {
		String[] seats = {"5"};
	
		ArrayList<Car> validCarListbySeats = new ArrayList<Car>();
		validCarListbySeats = dao.CarsBySeats(seats);
		assertEquals(0, validCarListbySeats.size());

	}
	
	
	
}
