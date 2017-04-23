package com.justride.models;

public class Car {

	private int carId, costPerHour, seats;
	private String carName, category, mSpeed, location, imgName;

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public int getSeats() {
		return seats;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Car(int carId, int costPerHour, String carName, String category, String mSpeed, int seats) {
		super();
		this.carId = carId;
		this.seats = seats;
		this.costPerHour = costPerHour;
		this.carName = carName;
		this.category = category;
		this.mSpeed = mSpeed;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(int costPerHour) {
		this.costPerHour = costPerHour;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getmSpeed() {
		return mSpeed;
	}

	public void setmSpeed(String mSpeed) {
		this.mSpeed = mSpeed;
	}

}
