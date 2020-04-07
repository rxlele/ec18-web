package com.ingsw.model;

import com.google.gson.annotations.SerializedName;

public abstract class User {
	
	@SerializedName("firstname")
	private String firstname;
	
	@SerializedName("lastname")
	private String lastname;
	
	@SerializedName("email")
	private String email;
	
	@SerializedName("password")
	private String password;
	
	public User() {}
	
	public User(String firstname, String lastname, String email, String password) {
		setFirstName(firstname);
		setLastName(lastname);
		setEmail(email);
		setPasswd(password);
	}
	public void setFirstName(String firstname) {
		this.firstname=firstname;
	}
	public void setLastName(String lastname) {
		this.lastname=lastname;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setPasswd(String password) {
		this.password=password;
	}
	public String getFirstName() {
		return firstname;
	}
	public String getLastName() {
		return lastname;
	}
	public String getEmail() {
		return email;
	}
	public String getPasswd() {
		return password;
	}
}
