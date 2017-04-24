package com.justride.service;

import java.time.LocalDateTime;

import com.justride.dao.CarDao;
import com.justride.models.Car;

public class CarServie implements ICarService {

	CarDao carDao = new CarDao();

	@Override
	public Car getCarbyId(int carId) {

		return carDao.getCarbyId(carId);
	}

	@Override
	public float calculateRent(int carId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		return carDao.calculateRent(carId, startDateTime, endDateTime);
	}

}
