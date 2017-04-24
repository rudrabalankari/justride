package com.ssdi.justride;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.justride.dao.BookingDao;
import com.justride.dao.CarDao;
import com.justride.models.Car;
import com.justride.service.BookingService;

@Controller
public class ViewCarsController {

	// JustRideService justRideService = new JustRideService();
	BookingService implementation = new BookingService();
	String intimeStamp = "", outtimeStamp = "";
	ArrayList<Car> validCarsByLoc = new ArrayList<Car>();
	ArrayList<String> locationList2 = new ArrayList<String>();
	ArrayList<String> categoryList2 = new ArrayList<String>();
	String email = "";
	String[] locations = {};

	BookingDao dao = new BookingDao();
	CarDao carDao = new CarDao();

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "justride/carDataForm", method = RequestMethod.GET)
	public String carDataForm(HttpServletRequest request, ModelMap model) {

		String location = request.getParameter("location");
		String inDate = request.getParameter("pickupDate");
		String outDate = request.getParameter("dropoffDate");
		String pickUpTime = request.getParameter("pickupTime");
		String dropTime = request.getParameter("dropoffTime");
		String intimeStamp2 = inDate.concat(" " + pickUpTime);

		this.intimeStamp = intimeStamp2;
		String outtimeStamp2 = outDate.concat(" " + dropTime);
		this.outtimeStamp = outtimeStamp2;
		System.out.println("in the car data controller");
		System.out.println(intimeStamp.length() + " " + outtimeStamp.length());
		ArrayList<String> locationList = new ArrayList<String>();
		locationList.add(location);

		// invalid Date Length specified
		if (!(intimeStamp.length() == 16 && outtimeStamp.length() == 16)) {
			model.addAttribute("error", "Please fill all the details!");
			model.addAttribute("email", email);
			return "home2";
		}

		// checking if the date specified is a valid future date
		DateTimeFormatter formatter;
		LocalDateTime dateTime;
		if (implementation.validateDate(intimeStamp)) {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			dateTime = LocalDateTime.parse(intimeStamp2, formatter);
			LocalDateTime dateTime2 = LocalDateTime.parse(outtimeStamp2, formatter);
			LocalDateTime dateTimeplus30 = dateTime.plusMinutes(30);
			if (!(dateTimeplus30.isBefore(dateTime2))) {
				model.addAttribute("error", "Please select valid time greater than Half an Hour and less than 15 days");
				model.addAttribute("email", email);
				return "home2";
			}

			long seconds = carDao.getTimeDiffSec(dateTime, dateTime2);

			if (seconds == 0) {
				model.addAttribute("error", "Please select valid time greater than Half an Hour and less than 15 days");
				model.addAttribute("email", email);
				return "home2";
			}

			// addding the details to session scope
			HttpSession session = request.getSession();
			// session.setAttribute("test", "Test");
			session.setAttribute("dateTime1", dateTime);
			session.setAttribute("dateTime2", dateTime2);
			// session.setAttribute("inDate", inDate);
			// session.setAttribute("outDate", outDate);
			// session.setAttribute("pickUpTime", pickUpTime);
			// session.setAttribute("dropTime", dropTime);

			// retrieving the valid cars for the given location
			ArrayList<Car> validCarList = implementation.getValidCarList(intimeStamp, outtimeStamp, locationList);
			locationList2 = locationList;
			if (validCarList != null) {
				validCarsByLoc = validCarList;
				model.addAttribute("carList", validCarList);
				model.addAttribute("locationList", locationList);
			} else {
				System.out.println("No valid cars found!");
			}
			System.out.println("Test +++++++++++++" + locationList.get(0));
		} else {
			model.addAttribute("error", "Please select a valid date(s)");
			return "home2";
		}
		return "viewcars2";
	}

	@RequestMapping(value = "justride/filters", method = RequestMethod.GET)
	public String carDataForm2(HttpServletRequest request, ModelMap model) {
		String[] locations = request.getParameterValues("locationName");
		String[] categories = request.getParameterValues("category");
		String[] seats = request.getParameterValues("seats");

		// locations, seats and categories
		if (locations != null && categories != null && seats != null) {
			System.out.println("seats, locations, categories selcted");
			ArrayList<Car> validCarListbyCategoryAndSeats = implementation.CarsByLocCatSeat(locations, categories,
					seats);
			model.addAttribute("locationList", locations);
			model.addAttribute("carList", validCarListbyCategoryAndSeats);
			return "viewcars2";
		}

		// only locations and categories
		else if (locations != null && categories != null) {
			System.out.println("Category and locations selcted");
			ArrayList<Car> validCarListbyCategory = implementation.CarsByLocCat(locations, categories);
			model.addAttribute("locationList", locations);
			model.addAttribute("carList", validCarListbyCategory);
		}

		// only seats and location
		else if (locations != null && seats != null) {
			System.out.println("seats and locations selcted");
			ArrayList<Car> validCarListbySeats = implementation.CarsByLocSeats(locations, seats);
			model.addAttribute("locationList", locations);
			model.addAttribute("carList", validCarListbySeats);
		}

		// only seats and categories
		else if (categories != null && seats != null) {
			System.out.println("seats and categories selcted");
			ArrayList<Car> validCarListbyCategoryandSeats = implementation.CarsBySeatsCat(categories, seats);
			model.addAttribute("carList", validCarListbyCategoryandSeats);
			model.addAttribute("locationList", locationList2);
		}
		// only locations
		else if (locations != null) {
			System.out.println("only locations selcted");
			System.out.println("In time stamp========" + intimeStamp + "Out time stamp======" + outtimeStamp);
			ArrayList<Car> validCarList = implementation.CarsByLoc(locations);
			model.addAttribute("carList", validCarList);
			model.addAttribute("locationList", locations);
			this.locations = locations;
			System.out.println("Location size====" + this.locations.length);
		}

		// only categories
		else if (categories != null) {
			System.out.println("Only category selcted");
			ArrayList<Car> validCarListbyCategory = new ArrayList<Car>();
			validCarListbyCategory = implementation.CarsByCat(categories);
			model.addAttribute("carList", validCarListbyCategory);
			model.addAttribute("locationList", this.locations);

		}
		// only seats
		else if (seats != null)

		{
			System.out.println("Only Seats selected");
			System.out.println("Seats in controller :" + seats[0]);
			ArrayList<Car> validCarListbySeats = new ArrayList<Car>();
			validCarListbySeats = implementation.CarsBySeats(seats);
			model.addAttribute("carList", validCarListbySeats);
			model.addAttribute("locationList", this.locations);
		}

		else {
			System.out.println("Locations,Seats and Categories is null");
		}
		return "viewcars2";
	}

	// without login, filters
	@RequestMapping(value = "filters", method = RequestMethod.GET)
	public String carDataForm3(HttpServletRequest request, ModelMap model) {
		System.out.println("filter method");

		String[] locations = request.getParameterValues("locationName");
		String[] categories = request.getParameterValues("category");
		String[] seats = request.getParameterValues("seats");

		// locations, seats and categories
		if (locations != null && categories != null && seats != null) {
			System.out.println("seats, locations, categories selcted");
			ArrayList<Car> validCarListbyCategoryAndSeats = implementation.CarsByLocCatSeat(locations, categories,
					seats);
			model.addAttribute("locationList", locations);
			model.addAttribute("carList", validCarListbyCategoryAndSeats);
			return "viewcars";
		}

		// only locations and categories
		else if (locations != null && categories != null) {
			System.out.println("Category and locations selcted");
			ArrayList<Car> validCarListbyCategory = implementation.CarsByLocCat(locations, categories);
			model.addAttribute("locationList", locations);
			model.addAttribute("carList", validCarListbyCategory);
		}

		// only seats and location
		else if (locations != null && seats != null) {
			System.out.println("seats and locations selcted");
			ArrayList<Car> validCarListbySeats = implementation.CarsByLocSeats(locations, seats);
			model.addAttribute("locationList", locations);
			model.addAttribute("carList", validCarListbySeats);
		}

		// only seats and categories
		else if (categories != null && seats != null) {
			System.out.println("seats and categories selcted");
			ArrayList<Car> validCarListbyCategoryandSeats = implementation.CarsBySeatsCat(categories, seats);
			model.addAttribute("carList", validCarListbyCategoryandSeats);
			model.addAttribute("locationList", locationList2);
		}
		// only locations
		else if (locations != null) {
			System.out.println("only locations selcted");
			System.out.println("In time stamp========" + intimeStamp + "Out time stamp======" + outtimeStamp);
			ArrayList<Car> validCarList = implementation.CarsByLoc(locations);
			model.addAttribute("carList", validCarList);
			model.addAttribute("locationList", locations);
			this.locations = locations;
			System.out.println("Location size====" + this.locations.length);
		}

		// only categories
		else if (categories != null) {
			System.out.println("Only category selcted");
			ArrayList<Car> validCarListbyCategory = new ArrayList<Car>();
			validCarListbyCategory = implementation.CarsByCat(categories);
			model.addAttribute("carList", validCarListbyCategory);
			model.addAttribute("locationList", this.locations);

		}
		// only seats
		else if (seats != null)

		{
			System.out.println("Only Seats selected");
			System.out.println("Seats in controller :" + seats[0]);
			ArrayList<Car> validCarListbySeats = new ArrayList<Car>();
			validCarListbySeats = implementation.CarsBySeats(seats);
			model.addAttribute("carList", validCarListbySeats);
			model.addAttribute("locationList", this.locations);
		}

		else {
			System.out.println("Locations,Seats and Categories is null");
		}
		return "viewcars";
	}

	// without login, home page
	@RequestMapping(value = "carDataForm", method = RequestMethod.GET)
	public String logincarDataForm(HttpServletRequest request, ModelMap model) {

		String location = request.getParameter("location");
		String inDate = request.getParameter("pickupDate");
		String outDate = request.getParameter("dropoffDate");
		String pickUpTime = request.getParameter("pickupTime");
		String dropTime = request.getParameter("dropoffTime");
		String intimeStamp2 = inDate.concat(" " + pickUpTime);

		this.intimeStamp = intimeStamp2;
		String outtimeStamp2 = outDate.concat(" " + dropTime);
		this.outtimeStamp = outtimeStamp2;
		System.out.println("in the car data controller");
		System.out.println(intimeStamp.length() + " " + outtimeStamp.length());
		ArrayList<String> locationList = new ArrayList<String>();
		locationList.add(location);

		// invalid Date Length specified
		if (!(intimeStamp.length() == 16 && outtimeStamp.length() == 16)) {
			model.addAttribute("error", "Please fill all the details!");
			model.addAttribute("email", email);
			return "home";
		}

		// checking if the date specified is a valid future date
		DateTimeFormatter formatter;
		LocalDateTime dateTime;
		if (implementation.validateDate(intimeStamp)) {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			dateTime = LocalDateTime.parse(intimeStamp2, formatter);
			LocalDateTime dateTime2 = LocalDateTime.parse(outtimeStamp2, formatter);
			LocalDateTime dateTimeplus30 = dateTime.plusMinutes(30);
			if (!(dateTimeplus30.isBefore(dateTime2))) {
				model.addAttribute("error", "Please select valid time greater than Half an Hour and less than 15 days");
				model.addAttribute("email", email);
				return "home";
			}

			long seconds = carDao.getTimeDiffSec(dateTime, dateTime2);

			if (seconds == 0) {
				model.addAttribute("error", "Please select valid time greater than Half an Hour and less than 15 days");
				model.addAttribute("email", email);
				return "home";
			}

			// addding the details to session scope
			HttpSession session = request.getSession();
			// session.setAttribute("test", "Test");
			session.setAttribute("dateTime1", dateTime);
			session.setAttribute("dateTime2", dateTime2);
			// session.setAttribute("inDate", inDate);
			// session.setAttribute("outDate", outDate);
			// session.setAttribute("pickUpTime", pickUpTime);
			// session.setAttribute("dropTime", dropTime);

			// retrieving the valid cars for the given location
			ArrayList<Car> validCarList = implementation.getValidCarList(intimeStamp, outtimeStamp, locationList);
			locationList2 = locationList;
			if (validCarList != null) {
				validCarsByLoc = validCarList;
				model.addAttribute("carList", validCarList);
				model.addAttribute("locationList", locationList);
			} else {
				System.out.println("No valid cars found!");
			}
			System.out.println("Test +++++++++++++" + locationList.get(0));
		} else {
			model.addAttribute("error", "Please select a valid date(s)");
			return "home";
		}
		return "viewcars";
	}

}
