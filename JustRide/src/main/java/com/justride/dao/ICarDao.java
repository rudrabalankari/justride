package com.justride.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.justride.models.Car;

public interface ICarDao {
	public List<Integer> getAllCarIds();

	public Car getCarbyId(int carId);

	public float calculateRent(int carId, LocalDateTime startDateTime, LocalDateTime endDateTime);

	public long getTimeDiffSec(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
