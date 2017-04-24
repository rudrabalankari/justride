package com.justride.dao;

import java.util.ArrayList;

public interface ILocationDao {
	public String getLocationById(int carId);

	public ArrayList<Integer> getCarIdsBylocation(ArrayList<String> locations);
}
