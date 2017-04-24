package com.justride.dao;

import java.util.ArrayList;

import com.justride.models.Booking;
import com.justride.models.Car;

public interface IBookingDao {

	public boolean insertCar(int carId, String InDateStamp, String outDateStamp);

	public float calculateAmount(String InDateStamp, String outDateStamp, int carId);

	public ArrayList<Car> filterCars(ArrayList<Car> cars);

	public ArrayList<Car> getValidCarList(String intimeStamp, String outTimeStamp, ArrayList<String> locationList);

	public ArrayList<Car> CarsByLocCatSeat(String[] locations, String[] categories, String[] seats);

	public ArrayList<Car> CarsByLocCat(String[] locations, String[] categories);

	public ArrayList<Car> CarsByLocSeats(String[] locations, String[] seats);

	public ArrayList<Car> CarsBySeatsCat(String[] categories, String[] seats);

	public ArrayList<Car> CarsByLoc(String[] locations);

	public ArrayList<Car> CarsByCat(String[] categories);

	public ArrayList<Car> CarsBySeats(String[] seats);

	public int insertBooking(Booking booking);

}
