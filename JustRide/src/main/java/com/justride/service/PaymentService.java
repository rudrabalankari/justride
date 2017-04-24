package com.justride.service;

import com.justride.dao.BookingDao;
import com.justride.models.Booking;

public class PaymentService implements IPaymentInterface {

	BookingDao bookingDao=new BookingDao();

	@Override
	public int insertBooking(Booking booking) {

		return bookingDao.insertBooking(booking);
	}

}
