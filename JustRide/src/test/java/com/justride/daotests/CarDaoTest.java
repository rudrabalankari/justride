package com.justride.daotests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.justride.dao.CarDao;
import com.justride.dao.LocationDao;
import com.justride.models.Car;

public class CarDaoTest {
	private CarDao carDao;
	private LocationDao locationDao;
	
	@Before
	public void setUp() {
		carDao = new CarDao();
		locationDao = Mockito.mock(LocationDao.class);
		when(locationDao.getLocationById(1)).thenReturn("UNC, Charlotte");
		
	}

	@Test
	public void getAllCarIdsTest() {
		ArrayList<Integer> carIds = new ArrayList<Integer>();
		carIds = carDao.getAllCarIds();
		assertEquals(26, carIds.size());

	}
	
	@Test
	public void getCarbyIdTest() {
		
		Car car = carDao.getCarbyId(1);
		assertEquals("nano",car.getCarName());

		
	}
	
}
