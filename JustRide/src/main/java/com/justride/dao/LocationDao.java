package com.justride.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.justride.models.Car;
import com.justride.util.GetConnection;

public class LocationDao implements ILocationDao{
	
	String location;

	@Override
	public String getLocationById(int carId) {
		Connection con;
		try {
			con = GetConnection.getConnection();
			if (con != null) {
				String sql2 = "select location from car_location where carid=?";
				java.sql.PreparedStatement stmt2 = con.prepareStatement(sql2);
				stmt2.setInt(1, carId);
						ResultSet rs2 = stmt2.executeQuery();
						while (rs2.next()) {
							location = rs2.getString("location");
						}
					}
				}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return location;
	}
	
	@Override
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
			e.printStackTrace();
		} catch (SQLException e) {
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
