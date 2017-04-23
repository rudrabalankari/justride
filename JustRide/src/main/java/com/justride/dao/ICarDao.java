package com.justride.dao;

import java.util.List;

import com.justride.models.Car;

public interface ICarDao {
	public List<Integer> getAllCarIds();
	public Car getCarbyId(int carId);

}
