package com.justride.service;

import com.justride.models.User;

public interface IUserService {

	public String registrationSubmit(User user);

	public String validateUser(User user);

	public boolean validateEmail(String email);

	public String validateUser(String emailId, String password);

	public User getUserDetails(String email);

}
