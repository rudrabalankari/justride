package com.justride.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.justride.models.Car;
import com.justride.util.GetConnection;

public class CarDao implements ICarDao {
	
	LocationDao locationDao = new LocationDao();
	int carId;

	@Override
	public ArrayList<Integer> getAllCarIds() {
		
		ArrayList<Integer> carIds = new ArrayList<Integer>();
		
		Connection con;
		try {

			con = GetConnection.getConnection();
			String sql4 = "select carid from cardetails";
			Statement stmt4 = con.createStatement();
			ResultSet rs4 = stmt4.executeQuery(sql4);
			while (rs4.next()) {
				carId = rs4.getInt("carid");
				carIds.add(carId);
			}

		}
			
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return carIds;
	}
	
	
	@Override
	public Car getCarbyId(int carId) {
		Car car = null;
		Connection con;
		try {
			con = GetConnection.getConnection();
			if (con != null) {
				String sql = "select carid,carname,category, mspeed, cost, seat from cardetails where carid=?";
				java.sql.PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, carId);
				ResultSet rs = stmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						//int carIdd = rs.getInt("carid");
						String carName = rs.getString("carname");
						String imgUrl = "http://localhost:8080/justride/resources/images/" + carName + ".png";
						String category = rs.getString("category");
						String mspeed = rs.getString("mspeed");
						int cost = rs.getInt("cost");
						int seats = rs.getInt("seat");
						car = new Car(carId, cost, carName, category, mspeed, seats);
						String location = locationDao.getLocationById(carId);
						car.setImgName(imgUrl);
						car.setLocation(location);
					}
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return car;
	}
	


}
