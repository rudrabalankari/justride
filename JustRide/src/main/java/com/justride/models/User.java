package com.justride.models;

public class User {
	private String firstName, lastName, email, password, phone, cardNo, zip, cnfPwd;

	public User() {

	}

	public User(String firstName, String lastName, String email, String password, String phone, String cardNo,
			String zip, String cnfPwd) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.cardNo = cardNo;
		this.zip = zip;
		this.cnfPwd = cnfPwd;
	}

	public User(String firstName, String lastName, String email, String phone, String cardNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.cardNo = cardNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCnfPwd() {
		return cnfPwd;
	}

	public void setCnfPwd(String cnfPwd) {
		this.cnfPwd = cnfPwd;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + ", cardNo=" + cardNo + ", zip=" + zip + "]";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
