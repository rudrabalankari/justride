package com.justride.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.justride.dao.BookingDao;
import com.justride.dao.UserDao;
import com.justride.models.Car;
import com.justride.models.User;
import com.justride.util.GetConnection;

public class BookingService implements IBookingService {

	BookingDao dao = new BookingDao();
	UserDao userdao = new UserDao();

	@Override
	public boolean validateDate(String InDateStamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("current time" + formatter.format(now));
		LocalDateTime inDateTime = LocalDateTime.parse(InDateStamp, formatter);
		if ((now.isBefore(inDateTime))) {
			System.out.println("Returning true");
			return true;
		}
		System.out.println("Returning false");
		return false;
	}

	@Override
	public ArrayList<Car> getValidCarList(String intimeStamp, String outTimeStamp, ArrayList<String> locationList) {
		return dao.getValidCarList(intimeStamp, outTimeStamp, locationList);
	}

	@Override
	public boolean validateTimePeriod(String intimeStamp2, String outtimeStamp2) {
		DateTimeFormatter formatter;
		LocalDateTime dateTime;
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		dateTime = LocalDateTime.parse(intimeStamp2, formatter);
		LocalDateTime dateTime2 = LocalDateTime.parse(outtimeStamp2, formatter);
		LocalDateTime dateTimeplus30 = dateTime.plusMinutes(30);

		return dateTimeplus30.isBefore(dateTime2);
	}

	@Override
	public ArrayList<Car> CarsByLocCatSeat(String[] locations, String[] categories, String[] seats) {
		if (locations != null && categories != null && seats != null) {
			return dao.CarsByLocCatSeat(locations, categories, seats);
		} else {
			System.out.println("Atleast One of the location, categoties, seats is null");
		}
		return dao.CarsByLocCatSeat(locations, categories, seats);
	}

	@Override
	public ArrayList<Car> CarsByLoc(String[] locations) {
		return dao.CarsByLoc(locations);
	}

	@Override
	public ArrayList<Car> CarsByLocCat(String[] locations, String[] categories) {
		return dao.CarsByLocCat(locations, categories);
	}

	@Override
	public ArrayList<Car> CarsByLocSeats(String[] locations, String[] seats) {
		return dao.CarsByLocSeats(locations, seats);
	}

	@Override
	public ArrayList<Car> CarsBySeatsCat(String[] categories, String[] seats) {
		return dao.CarsBySeatsCat(categories, seats);
	}

	@Override
	public ArrayList<Car> CarsByCat(String[] categories) {
		return dao.CarsByCat(categories);
	}

	@Override
	public ArrayList<Car> CarsBySeats(String[] seats) {
		return dao.CarsBySeats(seats);
	}

}
