package com.justride.service;

import java.util.ArrayList;

import com.justride.models.Booking;

public interface IBookingHistory {
	public ArrayList<Booking> getBookingbyUserId(String email);

	public boolean deleteBooking(int bookingId);

}
