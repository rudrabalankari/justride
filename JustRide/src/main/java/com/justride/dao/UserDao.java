package com.justride.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import com.justride.models.User;
import com.justride.util.GetConnection;

public class UserDao implements IUserDao {

	@Override
	public String registrationSubmit(User user) {

		String insertStatus = "invalid";
		System.out.println("In the registration submit");
		String fName = user.getFirstName();
		String lName = user.getLastName();
		String eMail = user.getEmail();
		String password = user.getPassword();
		String phone = user.getPhone();
		String cardNo = user.getCardNo();
		String zip = user.getZip();

		Connection conn;
		try {
			conn = GetConnection.getConnection();
			if (conn != null) {
				System.out.println("Connected");
				if (!(fName == null || lName == null || eMail == null || password == null || phone == null
						|| cardNo == null || zip == null)) {

					if (!validateEmail(eMail)) {
						String sql = "insert into UserRegistration (firstname, lastname, email, password, phone, cardno, zip) values (?,?,?,?,?,?,?);";
						try {
							java.sql.PreparedStatement statement = conn.prepareStatement(sql);
							statement.setString(1, fName);
							statement.setString(2, lName);
							statement.setString(3, eMail);
							statement.setString(4, password);
							statement.setString(5, phone);
							statement.setString(6, cardNo);
							statement.setString(7, zip);
							int count = statement.executeUpdate();
							if (count > 0) {
								System.out.println("User registered Successfully!");
								insertStatus = "inserted";
							} else {
								System.out.println("User registration failed!");
							}

						} catch (SQLException e) {
							e.printStackTrace();
						}

					} else {
						insertStatus = "existingEmail";
						return insertStatus;
					}

				} else {
					insertStatus = "emptyField";
				}

			}
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		return insertStatus;
	}

	@Override
	public boolean validateEmail(String email) {

		boolean existingID = false;
		Connection con;
		try {
			con = GetConnection.getConnection();
			if (con != null) {
				String sql = "select email from userregistration";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String eMailTmp = rs.getString("email");
					if (eMailTmp.equalsIgnoreCase(email)) {
						existingID = true;
					}
				}
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Existing Email ID============" + existingID);
		return existingID;

	}

	@Override
	public String validateUser(String emailId, String password) {
		String userValidation = "invalidEmail";
		if (validateEmail(emailId)) {
			Connection conn;
			try {
				conn = GetConnection.getConnection();
				if (conn != null) {
					String sql = "select password from userregistration where email=?";
					java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, emailId);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						if (password.equals(rs.getString("password"))) {
							userValidation = "home2";
						} else {
							userValidation = "invalidPwd";
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userValidation;
	}

	@Override
	public User getUserDetails(String email) {
		User user = new User();
		Connection conn;
		String firstName;
		String lastName;
		String phone;
		String cardNo;

		try {
			conn = GetConnection.getConnection();
			if (conn != null) {
				System.out.println("Connected");
				String sql = "select firstname, lastname, phone, cardno, zip from UserRegistration where email=?";
				Connection con = GetConnection.getConnection();
				java.sql.PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, email);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					firstName = rs.getString("firstname");
					lastName = rs.getString("lastname");
					phone = rs.getString("phone");
					cardNo = rs.getString("cardno");
					user = new User(firstName, lastName, email, phone, cardNo);
				}
				System.out.println("User=========" + user.getCardNo());
			}

		} catch (Exception e) {
		}
		return user;
	}
}
