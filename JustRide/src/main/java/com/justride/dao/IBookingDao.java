package com.justride.dao;

import java.util.ArrayList;

import com.justride.models.Car;
import com.justride.models.User;

public interface IBookingDao {

	public boolean insertCar(int carId, String InDateStamp, String outDateStamp);

	public float calculateAmount(String InDateStamp, String outDateStamp, int carId);

//	public Car getCarbyId(int id);

	public ArrayList<Car> filterCars(ArrayList<Car> cars);

	//public String registrationSubmit(User user);

	//public boolean validateEmail(String email);

	public ArrayList<Car> getValidCarList(String intimeStamp, String outTimeStamp, ArrayList<String> locationList);

	//public String validateUser(String emailId, String password);

	//public ArrayList<Integer> getCarIdsBylocation(ArrayList<String> locations);

	public ArrayList<Car> CarsByLocCatSeat(String[] locations, String[] categories, String[] seats);

	public ArrayList<Car> CarsByLocCat(String[] locations, String[] categories);

	public ArrayList<Car> CarsByLocSeats(String[] locations, String[] seats);

	public ArrayList<Car> CarsBySeatsCat(String[] categories, String[] seats);

	public ArrayList<Car> CarsByLoc(String[] locations);

	public ArrayList<Car> CarsByCat(String[] categories);
	
	public ArrayList<Car> CarsBySeats(String[] seats);


}
