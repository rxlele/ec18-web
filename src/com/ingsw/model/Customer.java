package com.ingsw.model;

import com.google.gson.annotations.SerializedName;

public class Customer extends User{
	
	//private Date birthdayDate
	@SerializedName("address")
	private String address;
	
	@SerializedName("city")
	private String city;
	
	@SerializedName("province")
	private String province;
	
	
	public Customer(String firstname, String lastname, String address, String city, String province, String email, String password) {
		super(firstname,lastname,email,password);
		setAddress(address);
		setCity(city);
		
		setProvince(province);
	}
	
	public Customer() {}

	public void setAddress(String address) {
		this.address=address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setCity(String city) {
		this.city=city;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setProvince(String province) {
		this.province=province;
	}
	
	public String getProvince() {
		return province;
	}
}
