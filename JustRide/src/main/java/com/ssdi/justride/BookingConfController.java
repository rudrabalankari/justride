package com.ssdi.justride;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.justride.dao.CarDao;
import com.justride.dao.UserDao;
import com.justride.models.Car;
import com.justride.models.User;

@Controller
public class BookingConfController {

	CarDao carDao;
	UserDao userDao = new UserDao();

	// http://localhost:8080/justride/justride/testContinue?
	@RequestMapping(value = "/justride/confirmBooking", method = RequestMethod.GET)
	public String bookingConfirmation(Locale locale, Model model, HttpServletRequest request) {
		int carId = Integer.parseInt(request.getParameter("carId"));
		System.out.println("Selected the car with id===" + carId);
		HttpSession session = request.getSession();
		// System.out.println("Test session" + (String)
		// session.getAttribute("test"));
		String email = (String) session.getAttribute("email");
		System.out.println("email==========" + email);
		// User user = userDao.getUserDetails(email);

		User user = new User("test", "test", "test", "test", "test");
		if (user != null) {
			System.out.println("User is not null");
		}

		LocalDateTime dateTime, dateTime2;
		dateTime = (LocalDateTime) session.getAttribute("dateTime1");
		dateTime2 = (LocalDateTime) session.getAttribute("dateTime2");
		System.out.println("Time stamp" + dateTime.toString());

		model.addAttribute("startTime", "" + dateTime.getHour() + ":" + dateTime.getMinute() + "");
		model.addAttribute("endTime", dateTime2.getHour() + ":" + dateTime2.getMinute() + "");
		model.addAttribute("startDate",
				dateTime.getMonthValue() + "/" + dateTime.getDayOfMonth() + "/" + dateTime.getYear());
		model.addAttribute("endDate",
				dateTime2.getMonthValue() + "/" + dateTime2.getDayOfMonth() + "/" + dateTime2.getYear());

		carDao = new CarDao();
		Car selectedCar = carDao.getCarbyId(carId);
		System.out.println("Car Location===========" + selectedCar.getLocation());
		model.addAttribute("car", selectedCar);
		// model.addAttribute("email", email);
		model.addAttribute("user", user);
		return "bookinginfo";
	}
}
