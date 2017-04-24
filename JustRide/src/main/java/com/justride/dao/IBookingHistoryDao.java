package com.justride.dao;

import java.util.ArrayList;

import com.justride.models.Booking;

public interface IBookingHistoryDao {
	public ArrayList<Booking> getBookingbyUserId(String email);

	public boolean deleteBooking(int bookingId);
}
