package com.ssdi.justride;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.justride.models.Booking;
import com.justride.models.Car;
import com.justride.models.User;
import com.justride.service.BookingHistoryServie;

@Controller
public class BookingHistoryController {
	@RequestMapping(value = "/justride/myRides", method = RequestMethod.GET)
	public String bookingConfirmation(Locale locale, Model model, HttpServletRequest request) {
		BookingHistoryServie bookingHistoryServie = new BookingHistoryServie();
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		ArrayList<Booking> bookings = bookingHistoryServie.getBookingbyUserId(email);
		if (bookings.size() == 0) {
			String error = "No bookings for " + email;
			model.addAttribute("error", error);
		}
		model.addAttribute("user", session.getAttribute("email"));
		model.addAttribute("bookings", bookings);
		System.out.println("size====" + bookings.size());
		return "mybookings";
	}

	@RequestMapping(value = "/justride/deleteBooking", method = RequestMethod.GET)
	public String deleteBooking(Locale locale, Model model, HttpServletRequest request) {

		int bookingId;
		bookingId = Integer.parseInt(request.getParameter("bookingId"));
		System.out.println(bookingId);
		BookingHistoryServie bookingHistoryServie = new BookingHistoryServie();
		HttpSession session = request.getSession();
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		if (bookingHistoryServie.deleteBooking(bookingId)) {
			String email = (String) session.getAttribute("email");
			bookings = bookingHistoryServie.getBookingbyUserId(email);
		}

		model.addAttribute("user", session.getAttribute("email"));
		model.addAttribute("bookings", bookings);
		System.out.println("size====" + bookings.size());
		return "mybookings";
	}

}
