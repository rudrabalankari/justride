package com.justride.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.justride.models.Booking;
import com.justride.util.GetConnection;
import com.mysql.jdbc.PreparedStatement;

public class BookingHistoryDao implements IBookingHistoryDao {

	@Override
	public ArrayList<Booking> getBookingbyUserId(String email) {

		ArrayList<Booking> bookings = new ArrayList<Booking>();

		Connection con;
		try {

			con = GetConnection.getConnection();
			String sql = "select booking_id, email, carid, intimestamp, outtimestamp, pickup_location, amount from bookinginfo where email=? and intimestamp >= now()";
			java.sql.PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int bookingId = rs.getInt("booking_id");
				int carId = rs.getInt("carid");
				String inTimeStamp = rs.getString("intimestamp").substring(0, 16);
				String outTImeStamp = rs.getString("outtimestamp").substring(0, 16);
				String pickup_location = rs.getString("pickup_location");
				float amount = rs.getFloat("amount");
				CarDao carDao = new CarDao();
				String carName = carDao.getCarbyId(carId).getCarName();

				Booking booking = new Booking(inTimeStamp, outTImeStamp, bookingId, email, carName, pickup_location,
						amount);
				bookings.add(booking);

			}

		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;

	}

	@Override
	public boolean deleteBooking(int bookingId) {
		Connection con;
		try {
			con = GetConnection.getConnection();
			String sql = "delete from bookinginfo where booking_id=?";
			java.sql.PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, bookingId);
			stmt.executeUpdate();
			return true;
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
