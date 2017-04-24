package com.justride.service;

import java.time.LocalDateTime;

import com.justride.models.Car;

public interface ICarService {
	public Car getCarbyId(int carId);

	public float calculateRent(int carId, LocalDateTime startDateTime, LocalDateTime endDateTime);

	
}
