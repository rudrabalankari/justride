package com.justride.service;

import org.springframework.stereotype.Service;

import com.justride.dao.UserDao;
import com.justride.models.User;

@Service
public class UserService implements IUserService {

	UserDao userdao = new UserDao();

	@Override
	public boolean validateEmail(String email) {
		return userdao.validateEmail(email);
	}

	@Override
	public String validateUser(User user) {

		String error = "";
		String password = user.getPassword();
		if (user.getCnfPwd().equals(password)) {
			if (validateEmail(user.getEmail())) {
				error = "email exists";
				// model.addAttribute("error", error);
				return error;

			} else {
				registrationSubmit(user);
				// model.addAttribute("email", "Registered as " +
				// user.getEmail());
				// model.addAttribute("status", "Registred Successfully");
				return "/home2";
			}
		}
		error = "invalid password!";
		// model.addAttribute("error", error);
		return "/register";

	}

	@Override
	public String registrationSubmit(User user) {

		return userdao.registrationSubmit(user);

	}

	@Override
	public String validateUser(String emailId, String password) {
		return userdao.validateUser(emailId, password);
	}

	@Override
	public User getUserDetails(String email) {
		return userdao.getUserDetails(email);
	}

}
