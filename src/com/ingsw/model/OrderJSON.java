package com.ingsw.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OrderJSON {
	
	@SerializedName("orderN")
	private int orderN;
	
	@SerializedName("date")
	private String date;
	
	@SerializedName("totalPrice")
	private Double totalPrice;
	
	@SerializedName("customer")
	private Customer customer;
	
	@SerializedName("products")
	private List<Item> products;
	
	@SerializedName("colors")
	private List<String> colors;

	@SerializedName("shipping")
	private List<Integer> shipping;
	
	@SerializedName("feedback")
	private List<Integer> feedback;
	
	public OrderJSON() {}
	

	public OrderJSON(int orderN, String date, Double totalPrice, Customer customer, List<Item> products, List<String> colors, List<Integer> shipping, List<Integer> feedback) {
		this.orderN=orderN;
		this.date=date;
		setTotalPrice(totalPrice);
		setProducts(products);
		setColors(colors);
		setShipping(shipping);
		setCustomer(customer);
		setFeedback(feedback);
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice=totalPrice;
	}
	public void setProducts(List<Item> products) {
		if (this.products==null)
			this.products=new ArrayList<Item>();
		this.products.addAll(products);
	}
	public void setColors(List<String> colors) {
		if (this.colors==null)
			this.colors=new ArrayList<String>();
		this.colors.addAll(colors);
	}
	public void setShipping(List<Integer> shipping) {
		if (this.shipping==null)
			this.shipping=new ArrayList<Integer>();
		this.shipping.addAll(shipping);
	}
	public void setCustomer(Customer customer) {
		if (this.customer==null)
			this.customer=new Customer();
		this.customer=customer;
	}
	public void setFeedback() {
		this.feedback=new ArrayList<>();
	}
	
	public void setFeedback(int size) {
		if (this.feedback==null) {
			feedback=new ArrayList<Integer>();
			for (int i=0;i<size;i++) {
				feedback.add(0);
			}
		}
	}
	
	public void setFeedback(List<Integer> feedback) {
		if (this.feedback==null)
			this.feedback=new ArrayList<Integer>();
		this.feedback.addAll(feedback);
	}
	
	public int getOrderN() {
		return orderN;
	}
	public String getDate() {
		return date;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public List<Item> getProducts(){
		return products;
	}
	public List<String> getColors(){
		return colors;
	}
	public List<Integer> getShipping(){
		return shipping;
	}
	public Customer getCustomer() {
		return customer;
	}
	public int getTotalShipping() {
		int totale=0;
		for (Integer i:shipping) {
			totale+=i;
		}
		return totale;
	}
	public List<Integer> getFeedback(){
		return feedback == null ? new ArrayList<Integer>() : feedback; 
	}
}

