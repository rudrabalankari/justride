package com.justride.models;

import java.time.LocalDateTime;

public class Booking {

	private String stringInTime;
	private String stringOutTime;
	private int bookingId;

	public String getStringInTime() {
		return stringInTime;
	}

	public void setStringInTime(String stringInTime) {
		this.stringInTime = stringInTime;
	}

	public String getStringOutTime() {
		return stringOutTime;
	}

	public void setStringOutTime(String stringOutTime) {
		this.stringOutTime = stringOutTime;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	private String email;
	private int carId;
	private LocalDateTime intimeStamp, outTimeStamp;
	private String pickupLocation;
	private float amount;
	private String carName;

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public Booking(String stringInTime, String stringOutTime, int bookingId, String email, String carName,
			String pickupLocation, float amount) {
		super();
		this.stringInTime = stringInTime;
		this.stringOutTime = stringOutTime;
		this.bookingId = bookingId;
		this.email = email;
		this.carName = carName;
		this.pickupLocation = pickupLocation;
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public LocalDateTime getIntimeStamp() {
		return intimeStamp;
	}

	public void setIntimeStamp(LocalDateTime intimeStamp) {
		this.intimeStamp = intimeStamp;
	}

	public LocalDateTime getOutTimeStamp() {
		return outTimeStamp;
	}

	public void setOutTimeStamp(LocalDateTime outTimeStamp) {
		this.outTimeStamp = outTimeStamp;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Booking(String email, int carId, LocalDateTime intimeStamp, LocalDateTime outTimeStamp,
			String pickupLocation, float amount) {
		super();
		this.email = email;
		this.carId = carId;
		this.intimeStamp = intimeStamp;
		this.outTimeStamp = outTimeStamp;
		this.pickupLocation = pickupLocation;
		this.amount = amount;
	}

}
