package com.justride.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.justride.models.Car;
import com.justride.models.User;
import com.justride.util.GetConnection;

public class BookingDao implements IBookingDao {
	
	LocationDao locationDao = new LocationDao();
	CarDao carDao = new CarDao();

	ArrayList<Car> validCarsList = new ArrayList<Car>();
	String inTimeStamp2 = "", outTimeStamp2 = "";
	ArrayList<String> locationList = new ArrayList<String>();

	@Override
	public boolean insertCar(int carId, String InDateStamp, String outDateStamp) {
		return false;
	}

	@Override
	public float calculateAmount(String InDateStamp, String outDateStamp, int carId) {
		return 0;
	}

	@Override
	public ArrayList<Car> getValidCarList(String intimeStamp, String outTimeStamp, ArrayList<String> locationList) {

		Connection con;
		Set<Integer> invalidCars;
		ArrayList<Integer> carIds;
		ArrayList<Integer> locationCarIds = new ArrayList<Integer>();
		ArrayList<Car> validCars = new ArrayList<Car>();
		carIds = new ArrayList<Integer>();
		this.inTimeStamp2 = intimeStamp;
		this.outTimeStamp2 = outTimeStamp;

		invalidCars = new HashSet<Integer>();
		try {

			con = GetConnection.getConnection();
			String sql = "select carid from bookinginfo where intimestamp < ? and outtimestamp >= ?";
			String sql2 = "select carid from bookinginfo where outtimestamp > ? and outtimestamp <= ?";
			String sql3 = "select carid from bookinginfo where intimestamp >= ?  and intimestamp < ?";

			java.sql.PreparedStatement stmt = con.prepareStatement(sql);
			java.sql.PreparedStatement stmt2 = con.prepareStatement(sql2);
			java.sql.PreparedStatement stmt3 = con.prepareStatement(sql3);
			stmt.setString(1, intimeStamp);
			stmt.setString(2, outTimeStamp);
			stmt2.setString(1, intimeStamp);
			stmt2.setString(2, outTimeStamp);
			stmt3.setString(1, intimeStamp);
			stmt3.setString(2, outTimeStamp);

			carIds = carDao.getAllCarIds();


			ResultSet rs = stmt.executeQuery();
			ResultSet rs2 = stmt2.executeQuery();
			ResultSet rs3 = stmt3.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					int carId = rs.getInt("carid");
					System.out.println(carId);
					invalidCars.add(carId);
				}
			}
			if (rs2 != null) {
				while (rs2.next()) {
					int carId = rs2.getInt("carid");
					System.out.println("2 list " + carId);
					invalidCars.add(carId);
				}
			}
			if (rs3 != null) {
				while (rs3.next()) {
					int carId = rs3.getInt("carid");
					System.out.println("3 list " + carId);
					invalidCars.add(carId);
				}
			}

			System.out.println("Invalid car Ids==========" + invalidCars.toArray().toString());
			for (int s : invalidCars) {
				System.out.println(s);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Total car Ids=======" + carIds.size());
		if (invalidCars != null) {
			carIds.removeAll(invalidCars);
		}
		System.out.println("Total car Ids valid at all locations=======" + carIds.size());
		locationCarIds = locationDao.getCarIdsBylocation(locationList);
		carIds.retainAll(locationCarIds);
		Iterator<Integer> iter = carIds.iterator();
		while (iter.hasNext()) {
			Integer carId = (Integer) iter.next();
			validCars.add(carDao.getCarbyId(carId));
		}
		if (validCars != null) {
			System.out.println("Valid cars length at given location(s)====" + validCars.size());
		}

		validCarsList = validCars;
		return validCars;

	}

	
	@Override
	public ArrayList<Car> filterCars(ArrayList<Car> cars) {
		return null;
	}

	
	@Override
	public ArrayList<Car> CarsByLocCatSeat(String[] locations, String[] categories, String[] seats) {

		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<String> categoryList = new ArrayList<String>();
		ArrayList<Integer> seatsList = new ArrayList<Integer>();

		for (int i = 0; i < locations.length; i++) {
			locationList.add(locations[i]);
		}

		for (int i = 0; i < categories.length; i++) {
			categoryList.add(categories[i]);
			System.out.println("Category :" + categoryList);

		}

		for (int i = 0; i < seats.length; i++) {
			seatsList.add(Integer.parseInt(seats[i]));
			System.out.println("Seats :" + seatsList);
		}

		System.out.println("In time stamp========" + this.inTimeStamp2 + "Out time stamp======" + this.outTimeStamp2);
		ArrayList<Car> validCarList = getValidCarList(inTimeStamp2, outTimeStamp2, locationList);
		ArrayList<Car> validCarListbyCategoryAndSeats = new ArrayList<Car>();
		Iterator<Car> iterator = validCarList.iterator();
		while (iterator.hasNext()) {
			Car car = (Car) iterator.next();
			System.out.println("seats:" + car.getSeats());
			System.out.println("category:" + car.getCategory());

			if (categoryList.contains(car.getCategory()) && seatsList.contains(car.getSeats())) {
				System.out.println("Inside the loop");
				validCarListbyCategoryAndSeats.add(car);
			}
		}

		System.out.println("Test +++++++++++++" + locationList.get(0));
		return validCarListbyCategoryAndSeats;
	}

	@Override
	public ArrayList<Car> CarsByLocCat(String[] locations, String[] categories) {

		System.out.println("Category and locations selcted");

		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<String> categoryList = new ArrayList<String>();

		for (int i = 0; i < locations.length; i++) {
			locationList.add(locations[i]);
		}

		for (int i = 0; i < categories.length; i++) {
			categoryList.add(categories[i]);
			System.out.println("Category :" + categoryList);

		}

		System.out.println("In time stamp========" + this.inTimeStamp2 + "Out time stamp======" + this.outTimeStamp2);
		ArrayList<Car> validCarList = getValidCarList(inTimeStamp2, outTimeStamp2, locationList);
		ArrayList<Car> validCarListbyCategory = new ArrayList<Car>();
		Iterator<Car> iterator = validCarList.iterator();
		while (iterator.hasNext()) {
			Car car = (Car) iterator.next();
			System.out.println("category:" + car.getCategory());

			if (categoryList.contains(car.getCategory())) {
				System.out.println("Inside the loop");
				validCarListbyCategory.add(car);
			}
			// validCarsByLoc = validCarListbyCategory;
			// model.addAttribute("locationList", locationList2);
			// model.addAttribute("carList", validCarListbyCategory);
		}

		return validCarListbyCategory;
	}

	@Override
	public ArrayList<Car> CarsByLocSeats(String[] locations, String[] seats) {
		System.out.println("Seats and locations selected");
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<Integer> seatsList = new ArrayList<Integer>();

		for (int i = 0; i < locations.length; i++) {
			locationList.add(locations[i]);
		}
		for (int i = 0; i < seats.length; i++) {
			seatsList.add(Integer.parseInt(seats[i]));
		}
		System.out.println("In time stamp========" + inTimeStamp2 + "Out time stamp======" + outTimeStamp2);
		ArrayList<Car> validCarList = getValidCarList(inTimeStamp2, outTimeStamp2, locationList);
		ArrayList<Car> validCarListbySeats = new ArrayList<Car>();
		Iterator<Car> iterator = validCarList.iterator();
		while (iterator.hasNext()) {
			Car car = (Car) iterator.next();
			if (seatsList.contains(car.getSeats())) {
				validCarListbySeats.add(car);
			}
		}
		return validCarListbySeats;

	}

	@Override
	public ArrayList<Car> CarsBySeatsCat(String[] categories, String[] seats) {
		ArrayList<String> categoryList = new ArrayList<String>();
		ArrayList<Integer> seatsList = new ArrayList<Integer>();
		ArrayList<Car> validCarListbyCategoryandSeats = new ArrayList<Car>();

		for (int i = 0; i < categories.length; i++) {
			categoryList.add(categories[i]);
		}
		for (int i = 0; i < seats.length; i++) {
			seatsList.add(Integer.parseInt(seats[i]));
			System.out.println("Seats :" + seatsList);
		}
		if (validCarsList.size() > 0) {
			Iterator<Car> iterator = validCarsList.iterator();
			while (iterator.hasNext()) {
				Car car = (Car) iterator.next();

				if (categoryList.contains(car.getCategory()) && seatsList.contains(car.getSeats())) {
					validCarListbyCategoryandSeats.add(car);
				}
			}

		} else {
			System.out.println("Valid cars by Location is empty!");
		}
		return validCarListbyCategoryandSeats;

	}

	@Override
	public ArrayList<Car> CarsByLoc(String[] locations) {
		ArrayList<String> locationList = new ArrayList<String>();
		for (int i = 0; i < locations.length; i++) {
			locationList.add(locations[i]);
		}
		this.locationList = locationList;
		System.out.println("In time stamp========" + inTimeStamp2 + "Out time stamp======" + outTimeStamp2);
		ArrayList<Car> validCarList = getValidCarList(inTimeStamp2, outTimeStamp2, locationList);
	//	this.validCarsList = validCarList;
		return validCarList;
	}

	@Override
	public ArrayList<Car> CarsByCat(String[] categories) {
		System.out.println("Only category selcted");
		ArrayList<String> categoryList = new ArrayList<String>();
		ArrayList<Car> validCarListbyCategory = new ArrayList<Car>();
		for (int i = 0; i < categories.length; i++) {
			categoryList.add(categories[i]);
		}

		if (validCarsList.size() > 0) {
			Iterator<Car> iterator = validCarsList.iterator();
			while (iterator.hasNext()) {
				Car car = (Car) iterator.next();
				if (categoryList.contains(car.getCategory())) {
					validCarListbyCategory.add(car);
				}
			}

		} else {
			System.out.println("Valid cars by Location is empty!");
		}
		return validCarListbyCategory;

	}
	
	@Override
	public ArrayList<Car> CarsBySeats(String[] seats) {
		System.out.println("Only Seats Selected");
		ArrayList<Integer> seatsList = new ArrayList<Integer>();
		ArrayList<Car> validCarListBySeats = new ArrayList<Car>();
		for (int i = 0; i < seats.length;i++) {
			seatsList.add(Integer.parseInt(seats[i]));
		}
		System.out.println("size" + seatsList.get(0));
		if(validCarsList.size()>0) {
			Iterator<Car> iterator = validCarsList.iterator();
			while(iterator.hasNext()) {
				Car car = (Car) iterator.next();
				if (seatsList.contains(car.getSeats())) {
					validCarListBySeats.add(car);
				}
			}
		} else {
			System.out.println("Valid cars by location is empty!");
		}
		return validCarListBySeats;
	}

}
