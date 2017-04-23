package com.ssdi.justride;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.justride.dao.BookingDao;
import com.justride.models.Car;

public class ViewCarsFilterTest {
	
	private BookingDao dao;
	
	@Before
	public void testSetUp(){
	dao = new BookingDao();
	}
	
	@Test
	public void getCarByIdTest() {
		
	}
	
	@Test


	public void CarsByLocCatSeatTest() {
		String[] locations = {"UNC, Charlotte"};
		String[] categories = {"Premium"};
		String[] seats = {"5"};
		
		ArrayList<Car> validCarListbyCategoryAndSeats = new ArrayList<Car>();
		validCarListbyCategoryAndSeats = dao.CarsByLocCatSeat(locations, categories, seats);
		assertEquals(4, validCarListbyCategoryAndSeats.size());
	}
	
	@Test

	public void CarsByLocCat() {
		String[] locations = {"UNC, Charlotte"};
		String[] categories = {"Premium"};
		
		ArrayList<Car> validCarListbyCategoryAndSeats = new ArrayList<Car>();
		validCarListbyCategoryAndSeats = dao.CarsByLocCat(locations, categories);
		assertEquals(12, validCarListbyCategoryAndSeats.size());
	}
	
	
}

