package com.justride.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.justride.models.Car;
import com.justride.util.GetConnection;

public class CarDao implements ICarDao {

	LocationDao locationDao = new LocationDao();
	int carId;

	@Override
	public ArrayList<Integer> getAllCarIds() {

		ArrayList<Integer> carIds = new ArrayList<Integer>();

		Connection con;
		try {

			con = GetConnection.getConnection();
			String sql4 = "select carid from cardetails";
			Statement stmt4 = con.createStatement();
			ResultSet rs4 = stmt4.executeQuery(sql4);
			while (rs4.next()) {
				carId = rs4.getInt("carid");
				carIds.add(carId);
			}

		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carIds;
	}

	@Override
	public Car getCarbyId(int carId) {
		Car car = null;
		Connection con;
		try {
			con = GetConnection.getConnection();
			if (con != null) {
				String sql = "select carid,carname,category, mspeed, cost, seat from cardetails where carid=?";
				java.sql.PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, carId);
				ResultSet rs = stmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						// int carIdd = rs.getInt("carid");
						String carName = rs.getString("carname");
						String imgUrl = "http://localhost:8080/justride/resources/images/" + carName + ".png";
						String category = rs.getString("category");
						String mspeed = rs.getString("mspeed");
						int cost = rs.getInt("cost");
						int seats = rs.getInt("seat");
						car = new Car(carId, cost, carName, category, mspeed, seats);
						String location = locationDao.getLocationById(carId);
						car.setImgName(imgUrl);
						car.setLocation(location);
					}
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return car;
	}

	@Override
	public float calculateRent(int carId, LocalDateTime startDateTime, LocalDateTime endDateTime) {

		float rent = 0;
		float totalHours = 0;
		Car car = getCarbyId(carId);
		int pricePerHour = car.getCostPerHour();
		long seconds = getTimeDiffSec(startDateTime, endDateTime);
		if (seconds > 0) {
			totalHours = seconds / 3600;
		}
		rent = totalHours * pricePerHour;
		return rent;
	}

	@Override
	public long getTimeDiffSec(LocalDateTime startDateTime, LocalDateTime endDateTime) {

		// Assuming to reserve a car for a maximum period of 14.99 days

		long totalSeconds = 0;
		LocalDateTime tempDateTime = LocalDateTime.from(startDateTime);

		long years = tempDateTime.until(endDateTime, ChronoUnit.YEARS);
		tempDateTime = tempDateTime.plusYears(years);

		long months = tempDateTime.until(endDateTime, ChronoUnit.MONTHS);
		tempDateTime = tempDateTime.plusMonths(months);

		long days = tempDateTime.until(endDateTime, ChronoUnit.DAYS);
		tempDateTime = tempDateTime.plusDays(days);

		long hours = tempDateTime.until(endDateTime, ChronoUnit.HOURS);
		tempDateTime = tempDateTime.plusHours(hours);

		long minutes = tempDateTime.until(endDateTime, ChronoUnit.MINUTES);
		tempDateTime = tempDateTime.plusMinutes(minutes);

		long seconds = tempDateTime.until(endDateTime, ChronoUnit.SECONDS);

		System.out.println(years + " years " + months + " months " + days + " days " + hours + " hours " + minutes
				+ " minutes " + seconds + " seconds.");

		if (years == 0 && months == 0 && days <= 14) {
			totalSeconds = (days * 86400) + (hours * 3600) + (minutes * 60) + seconds;
		}

		return totalSeconds;
	}

}
