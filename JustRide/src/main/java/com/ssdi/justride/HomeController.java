package com.ssdi.justride;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.justride.dao.DaoImplementation;
import com.justride.models.Car;
import com.justride.models.User;
import com.justride.service.JustRideService;
import com.justride.service.ServiceImplementation;
import com.justride.util.GetConnection;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	// JustRideService justRideService = new JustRideService();
	ServiceImplementation implementation = new ServiceImplementation();
	String intimeStamp = "", outtimeStamp = "";
	ArrayList<Car> validCarsByLoc = new ArrayList<Car>();
	ArrayList<String> locationList2 = new ArrayList<String>();
	ArrayList<String> categoryList2 = new ArrayList<String>();
	String email = "";
	String[] locations = {};

	DaoImplementation dao = new DaoImplementation();

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}

	@RequestMapping(value = "/justride/login", method = RequestMethod.GET)
	public ModelAndView showLoginForm() {
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/justride/register", method = RequestMethod.GET)
	public ModelAndView showRegForm() {
		return new ModelAndView("register", "user", new User());
	}

	@RequestMapping(value = "justride/registerform", method = RequestMethod.POST)
	public String registrationSubmit(@ModelAttribute("user") User user, BindingResult result, ModelMap model)
			throws ClassNotFoundException {
		String error = "";
		String password = user.getPassword();
		if (user.getCnfPwd().equals(password)) {
			if (implementation.validateEmail(user.getEmail())) {
				// if (service.validateEmail(user.getEmail())) {
				error = "email exists";
				model.addAttribute("error", error);
				return "/register";
			} else {
				implementation.registrationSubmit(user);
				// justRideService.registrationSubmit(user);
				model.addAttribute("email", "Registered as " + user.getEmail());
				model.addAttribute("status", "Registred Successfully");
				return "/home2";
			}
		}
		error = "invalid password!";
		model.addAttribute("error", error);
		return "/register";
	}

	@RequestMapping(value = "justride/loginform", method = RequestMethod.POST)
	public String loginSubmit(@ModelAttribute("user") User user, BindingResult result, ModelMap model)
			throws ClassNotFoundException {
		email = user.getEmail();
		String password = user.getPassword();
		// String validateUser = justRideService.validateUser(email, password);
		String validateUser = implementation.validateUser(email, password);
		if (validateUser.equals("home2")) {
			model.addAttribute("email", email);
		} else if (validateUser.equals("invalidPwd")) {
			model.addAttribute("error", "Invalid Password");
			return "login";
		} else if (validateUser.equals("invalidEmail")) {
			model.addAttribute("error", "Invalid User Name");
			return "login";
		}
		System.out.println("Res======" + validateUser);
		return validateUser;
	}

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
				model.addAttribute("error", "Please select valid time greater than Half an Hour!");
				model.addAttribute("email", email);
				return "home2";
			}

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
		return "viewcars";
	}

	@RequestMapping(value = "justride/filters", method = RequestMethod.GET)
	public String carDataForm2(HttpServletRequest request, ModelMap model) {
		String[] locations = request.getParameterValues("locationName");
		String[] categories = request.getParameterValues("category");
		String[] seats = request.getParameterValues("seats");

		// locations, seats and categories
		if (locations != null && categories != null && seats != null) {
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
			ArrayList<Car> validCarListbySeats = implementation.CarsByLocSeats(locations, seats);
			model.addAttribute("locationList", locations);
			model.addAttribute("carList", validCarListbySeats);
		}

		// only seats and categories
		else if (categories != null && seats != null) {
			ArrayList<Car> validCarListbyCategoryandSeats = implementation.CarsBySeatsCat(categories, seats);
			model.addAttribute("carList", validCarListbyCategoryandSeats);
			model.addAttribute("locationList", locationList2);
		}
		// only locations
		else if (locations != null) {
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
			System.out.println("Only Seats selcted");
			ArrayList<Integer> seatsList = new ArrayList<Integer>();
			ArrayList<Car> validCarListbySeats = new ArrayList<Car>();
			for (int i = 0; i < seats.length; i++) {
				seatsList.add(Integer.parseInt(seats[i]));
				System.out.println("Seats :" + seatsList);
			}
			if (validCarsByLoc.size() > 0) {
				System.out.println("Inside LOop");
				Iterator<Car> iterator = validCarsByLoc.iterator();
				while (iterator.hasNext()) {
					Car car = (Car) iterator.next();
					System.out.println(car.getSeats());
					if (seatsList.contains(car.getSeats())) {
						System.out.println("Inside LOop1");
						validCarListbySeats.add(car);
					}
					model.addAttribute("carList", validCarListbySeats);
					model.addAttribute("locationList", locationList2);
				}

			} else {
				System.out.println("Valid cars by Location is empty!");
			}
		}

		else {
			System.out.println("Locations and Categories is null");
		}
		return "viewcars";
	}

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
		if (!(intimeStamp.length() == 16 && outtimeStamp.length() == 16)) {
			model.addAttribute("error", "Please fill all the details!");
			return "home";
		}
		if (implementation.validateDate(intimeStamp)) {
			if (!(implementation.validateTimePeriod(intimeStamp2, outtimeStamp2))) {
				model.addAttribute("error", "Please select valid time greater than Half an Hour!");
				return "home";
			}

			ArrayList<Car> validCarList = implementation.getValidCarList(intimeStamp, outtimeStamp, locationList);
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
		}
		return "viewcars";
	}
}
