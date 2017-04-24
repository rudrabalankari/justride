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
import javax.servlet.http.HttpSession;

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

import com.justride.dao.BookingDao;
import com.justride.models.Car;
import com.justride.models.User;
import com.justride.service.BookingService;
import com.justride.service.UserService;
import com.justride.util.GetConnection;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	// JustRideService justRideService = new JustRideService();
	BookingService implementation = new BookingService();
	UserService userService = new UserService();
	String intimeStamp = "", outtimeStamp = "";
	ArrayList<Car> validCarsByLoc = new ArrayList<Car>();
	ArrayList<String> locationList2 = new ArrayList<String>();
	ArrayList<String> categoryList2 = new ArrayList<String>();
	String email = "";
	String[] locations = {};

	BookingDao dao = new BookingDao();

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
			if (userService.validateEmail(user.getEmail())) {
				// if (service.validateEmail(user.getEmail())) {
				error = "email exists";
				model.addAttribute("error", error);
				return "/register";
			} else {
				userService.registrationSubmit(user);
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
	public String loginSubmit(@ModelAttribute("user") User user, BindingResult result, ModelMap model,
			HttpServletRequest request) throws ClassNotFoundException {
		email = user.getEmail();
		String password = user.getPassword();
		// String validateUser = justRideService.validateUser(email, password);
		String validateUser = userService.validateUser(email, password);
		if (validateUser.equals("home2")) {
			HttpSession session = request.getSession();
			// session.setAttribute("test", "Test");
			session.setAttribute("email", email);
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

}
