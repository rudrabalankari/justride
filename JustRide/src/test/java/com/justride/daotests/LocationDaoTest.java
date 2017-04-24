package com.justride.daotests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.justride.dao.LocationDao;

public class LocationDaoTest {

	private  LocationDao locationDao;
	
	@Before
	public void setUp() {
		locationDao = new LocationDao();
	}
	
	@Test
	public void getLocationByIdTest() {
		
		assertEquals("UNC, Charlotte", locationDao.getLocationById(1));		
	}

	@Test
	public void getCarIdsBylocationTest() {
		ArrayList<String> locationListTest = new ArrayList<String>();
		ArrayList<Integer> CarIdsByLocationListTest = new ArrayList<Integer>();
		locationListTest.add("UNC, Charlotte");
		CarIdsByLocationListTest = locationDao.getCarIdsBylocation(locationListTest);		
		assertEquals(12, CarIdsByLocationListTest.size());		

	}
}
