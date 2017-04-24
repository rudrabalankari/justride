package com.justride.servicetests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.justride.dao.BookingDao;
import com.justride.dao.LocationDao;
import com.justride.models.Car;
import com.justride.service.BookingService;

public class BookingServiceTest {
	
	private BookingService bookingService;
	private BookingDao bookingDao;

	@Before
	public void setUp() {
		bookingService = new BookingService();
		bookingDao = Mockito.mock(BookingDao.class);
		ArrayList<String> locationList = new ArrayList<String>();
		locationList.add("UNC, Charlotte");
		String[] locations = {"UNC, Charlotte"};
		String[] categories = {"Economy"};
		String[] seats = {"5"};
		Car car = new Car(1, 20, "nano", "Economy", "65mph", 5);
		ArrayList<Car> validCars = new ArrayList<Car>();
		validCars.add(car);
		when(bookingDao.getValidCarList("2017-10-27 20:30:10", "2017-10-27 21:30:10",locationList)).thenReturn(validCars);
		when(bookingDao.CarsByLocCatSeat(locations,categories,seats)).thenReturn(validCars);
		when(bookingDao.CarsByLocCat(locations,categories)).thenReturn(validCars);
		when(bookingDao.CarsByLocSeats(locations, seats)).thenReturn(validCars);
		when(bookingDao.CarsBySeatsCat(categories, seats)).thenReturn(validCars);
		when(bookingDao.CarsByLoc(locations)).thenReturn(validCars);
		when(bookingDao.CarsByCat(categories)).thenReturn(validCars);
		when(bookingDao.CarsBySeats(seats)).thenReturn(validCars);

	}
	
	@Test
	public void validateDateTest() {
		
		assertEquals(true,bookingService.validateDate("2017-10-27 20:30"));
		assertEquals(false,bookingService.validateDate("2017-04-21 20:30"));
	}
	
	@Test
	public void validateTimePeriodTest() {
		assertEquals(true,bookingService.validateTimePeriod("2017-10-27 20:30", "2017-10-27 21:30"));
		assertEquals(false,bookingService.validateTimePeriod("2017-10-27 20:30", "2017-10-27 20:45"));
	}
	
	@Test
	public void getValidCarListTest() {
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<Car> validCarListTest = new ArrayList<Car>();
		locationList.add("UNC, Charlotte");
		validCarListTest = bookingService.getValidCarList("2017-10-27 20:30", "2017-10-27 21:30", locationList);
		assertEquals(12, validCarListTest.size());
	}

	@Test
	public void CarsByLocCatSeatTest() {
		String[] locations = {"UNC, Charlotte"};
		String[] categories = {"Economy"};
		String[] seats = {"5"};
		ArrayList<Car> validCarListbyCategoryAndSeats = new ArrayList<Car>();
		validCarListbyCategoryAndSeats = bookingService.CarsByLocCatSeat(locations,categories,seats);
		assertEquals(5, validCarListbyCategoryAndSeats.size());
	}
	
	@Test
	public void CarsByLocCatTest() {
		String[] locations = {"UNC, Charlotte"};
		String[] categories = {"Economy"};
		
		ArrayList<Car> validCarListbyLocationAndCategory = new ArrayList<Car>();
		validCarListbyLocationAndCategory = bookingService.CarsByLocCat(locations, categories);
		assertEquals(5, validCarListbyLocationAndCategory.size());
	}
	
	@Test

	public void CarsByLocSeatsTest() {
		String[] locations = {"UNC, Charlotte"};
		String[] seats = {"7"};
		
		ArrayList<Car> validCarListbyLocationAndSeats = new ArrayList<Car>();
		validCarListbyLocationAndSeats = bookingService.CarsByLocSeats(locations, seats);
		assertEquals(2, validCarListbyLocationAndSeats.size());

	}
	
	@Test

	public void CarsBySeatsCatTest() {
		String[] categories = {"Economy"};
		String[] seats = {"7"};
		
		ArrayList<Car> validCarListbyCategoryAndSeats = new ArrayList<Car>();
		validCarListbyCategoryAndSeats = bookingService.CarsBySeatsCat(categories, seats);
		assertEquals(0, validCarListbyCategoryAndSeats.size());

	}
	
	@Test

	public void CarsByLocationsTest() {
		String[] locations = {"Concord Mills"};
		
		ArrayList<Car> validCarListbyLocations = new ArrayList<Car>();
		validCarListbyLocations = bookingService.CarsByLoc(locations);
		assertEquals(6, validCarListbyLocations.size());

	}
	
	@Test

	public void CarsByCategoriesTest() {
		String[] Categories = {"Premium"};
	
		ArrayList<Car> validCarListbyCategories = new ArrayList<Car>();
		validCarListbyCategories = bookingService.CarsByLoc(Categories);
		assertEquals(0, validCarListbyCategories.size());

	}
	
	@Test

	public void CarsBySeatsTest() {
		String[] seats = {"5"};
		//int[] seatsi = Integer.parseInt(seats[0]);

		ArrayList<Car> validCarListbySeats = new ArrayList<Car>();
		validCarListbySeats = bookingService.CarsBySeats(seats);
		assertEquals(0, validCarListbySeats.size());

	}
}
