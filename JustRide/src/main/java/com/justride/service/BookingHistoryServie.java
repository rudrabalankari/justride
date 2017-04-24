package com.justride.service;

import java.util.ArrayList;

import com.justride.dao.BookingHistoryDao;
import com.justride.models.Booking;

public class BookingHistoryServie implements IBookingHistory {

	@Override
	public ArrayList<Booking> getBookingbyUserId(String email) {
		BookingHistoryDao bookingHistoryDao = new BookingHistoryDao();
		return bookingHistoryDao.getBookingbyUserId(email);
	}

	@Override
	public boolean deleteBooking(int bookingId) {
		BookingHistoryDao bookingHistoryDao = new BookingHistoryDao();
		return bookingHistoryDao.deleteBooking(bookingId);
	}

}
