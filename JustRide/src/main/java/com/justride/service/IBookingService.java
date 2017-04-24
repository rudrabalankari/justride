package com.justride.service;

import java.util.ArrayList;

import com.justride.models.Car;
import com.justride.models.User;

public interface IBookingService {
	public boolean validateDate(String InDateStamp);

	public ArrayList<Car> getValidCarList(String intimeStamp, String outTimeStamp, ArrayList<String> locationList);

	public boolean validateTimePeriod(String intimeStamp, String outtimeStamp);

	public ArrayList<Car> CarsByLocCatSeat(String[] locations, String[] categories, String[] seats);

	public ArrayList<Car> CarsByLocCat(String[] locations, String[] categories);

	public ArrayList<Car> CarsByLocSeats(String[] locations, String[] seats);

	public ArrayList<Car> CarsBySeatsCat(String[] categories, String[] seats);

	public ArrayList<Car> CarsByLoc(String[] locations);

	public ArrayList<Car> CarsByCat(String[] categories);

	public ArrayList<Car> CarsBySeats(String[] categories);

}
