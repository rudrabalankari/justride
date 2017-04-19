package com.justride.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.justride.models.Car;
import com.justride.models.User;
import com.justride.util.GetConnection;
import com.mysql.jdbc.PreparedStatement;

@Service
public class JustRideService {

	ArrayList<Car> validCarsList = new ArrayList<Car>();

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
						String sql = "insert into UserRegistration (firstname, lastname, email, password, phone, cardno, zip) values (?,?,?,?,?,?,?)";
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
							// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return insertStatus;
	}

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return userValidation;
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Existing Email ID============" + existingID);
		return existingID;
	}

	public ArrayList<Car> getValidCarList(String intimeStamp, String outTimeStamp, ArrayList<String> locationList) {
		// select booking_id, email, carid, intimestamp, outtimestamp,
		// pickup_location, amount from bookinginfo
		// where intimestamp < '2008-10-25 16:30:00' and outtimestamp >=
		// '2008-10-25 22:30:00';

		Connection con;
		Set<Integer> invalidCars;
		ArrayList<Integer> carIds;
		ArrayList<Integer> locationCarIds = new ArrayList<Integer>();
		ArrayList<Car> validCars = new ArrayList<Car>();
		carIds = new ArrayList<Integer>();

		invalidCars = new HashSet<Integer>();
		try {

			con = GetConnection.getConnection();
			// String intimeStamp2 = "2017-10-25 16:30:00";
			// String outTimeStamp2 = "2017-10-25 22:30:00";

			String sql = "select carid from bookinginfo where intimestamp < ? and outtimestamp >= ?";
			String sql2 = "select carid from bookinginfo where outtimestamp > ? and outtimestamp <= ?";
			String sql3 = "select carid from bookinginfo where intimestamp >= ?  and intimestamp < ?";
			String sql4 = "select carid from cardetails";

			java.sql.PreparedStatement stmt = con.prepareStatement(sql);
			java.sql.PreparedStatement stmt2 = con.prepareStatement(sql2);
			java.sql.PreparedStatement stmt3 = con.prepareStatement(sql3);
			stmt.setString(1, intimeStamp);
			stmt.setString(2, outTimeStamp);
			stmt2.setString(1, intimeStamp);
			stmt2.setString(2, outTimeStamp);
			stmt3.setString(1, intimeStamp);
			stmt3.setString(2, outTimeStamp);

			Statement stmt4 = con.createStatement();

			ResultSet rs4 = stmt4.executeQuery(sql4);

			while (rs4.next()) {
				Integer carId = rs4.getInt("carid");
				carIds.add(carId);
			}

			ResultSet rs = stmt.executeQuery();
			ResultSet rs2 = stmt2.executeQuery();
			ResultSet rs3 = stmt3.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					int carId = rs.getInt("carid");
					System.out.println(carId);
					invalidCars.add(carId);
				}
			}
			if (rs2 != null) {
				while (rs2.next()) {
					int carId = rs2.getInt("carid");
					System.out.println("2 list " + carId);
					invalidCars.add(carId);
				}
			}
			if (rs3 != null) {
				while (rs3.next()) {
					int carId = rs3.getInt("carid");
					System.out.println("3 list " + carId);
					invalidCars.add(carId);
				}
			}

			System.out.println("Invalid car Ids==========" + invalidCars.toArray().toString());
			for (int s : invalidCars) {
				System.out.println(s);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total car Ids=======" + carIds.size());
		if (invalidCars != null) {
			carIds.removeAll(invalidCars);
		}
		System.out.println("Total car Ids valid at all locations=======" + carIds.size());
		locationCarIds = getCarIdsBylocation(locationList);
		carIds.retainAll(locationCarIds);
		Iterator<Integer> iter = carIds.iterator();
		while (iter.hasNext()) {
			Integer carId = (Integer) iter.next();
			validCars.add(getCarbyID(carId));
		}
		if (validCars != null) {
			System.out.println("Valid cars length at given location(s)====" + validCars.size());
		}

		validCarsList = validCars;
		return validCars;
	}

	public Car getCarbyID(int carId) {
		Car car = null;
		Connection con;
		try {
			con = GetConnection.getConnection();
			if (con != null) {
				String sql = "select carid,carname,category, mspeed, cost, seat from cardetails where carid=?";
				String sql2 = "select location from car_location where carid=?";
				java.sql.PreparedStatement stmt = con.prepareStatement(sql);
				java.sql.PreparedStatement stmt2 = con.prepareStatement(sql2);
				stmt.setInt(1, carId);
				stmt2.setInt(1, carId);
				ResultSet rs = stmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						int carIdd = rs.getInt("carid");
						String carName = rs.getString("carname");

						String imgUrl = "http://localhost:8080/justride/resources/images/" + carName + ".png";
						String category = rs.getString("category");
						String mspeed = rs.getString("mspeed");
						int cost = rs.getInt("cost");
						int seats = rs.getInt("seat");

						car = new Car(carIdd, cost, carName, category, mspeed, seats);
						ResultSet rs2 = stmt2.executeQuery();
						String location = "";
						while (rs2.next()) {
							location = rs2.getString("location");
						}
						car.setImgName(imgUrl);
						car.setLocation(location);
					}
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return car;
	}

	public ArrayList<Integer> getCarIdsBylocation(ArrayList<String> locations) {
		// List possibleValues = ...
		// select carid, location from car_location where location in ('UNC,
		// Charlotte','Down Town');

		ArrayList<Integer> locationIds = new ArrayList<Integer>();
		Connection con;
		try {
			con = GetConnection.getConnection();
			if (con != null) {
				StringBuilder builder = new StringBuilder();

				for (int i = 0; i < locations.size(); i++) {
					builder.append("?,");
					System.out.println("Inside the loop");
					System.out.println(builder.toString());
				}

				String query = "select carid, location from car_location where location in ("
						+ builder.deleteCharAt(builder.length() - 1).toString() + ")";
				java.sql.PreparedStatement stmt = con.prepareStatement(query);

				System.out.println("Query Builder=============" + query);

				int index = 1;
				for (String location : locations) {
					stmt.setString(index++, location);
				}
				ResultSet rs = stmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						locationIds.add(rs.getInt("carid"));
					}
				}

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (locationIds.size() > 0) {
			System.out.println("Size of locationIds==========" + locationIds.size());
		} else {
			System.out.println("Size of locationIds=========== 0");
		}

		return locationIds;
	}

}
