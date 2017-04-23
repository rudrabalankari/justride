package com.justride.dao;

import com.justride.models.User;

public interface IUserDao {
	
	public String registrationSubmit(User user);
	public boolean validateEmail(String email);
	public String validateUser(String emailId, String password);
	
	public User getUserDetails(String email);
	

}
