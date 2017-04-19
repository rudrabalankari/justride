package com.justride.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {

	static String dbURL = "jdbc:mysql://localhost:3306/ssdi";
	static String username = "root";
	static String password = "root";
	static Connection conn = null;

	public static Connection getConnection() throws ClassNotFoundException {
		// System.out.println("In the getConnection method()");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, username, password);
			// if (conn != null) {
			// System.out.println("Connected Successfully!");
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

}
