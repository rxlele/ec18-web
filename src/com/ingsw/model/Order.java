package com.ingsw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Order {
	private int orderN;
	private Date date;
	private Double totalPrice;
	private Customer customer;
	private List<Item> products;
	private List<String> colors;
	private List<Integer> shipping;
	private List<Integer> feedback;
	
	public Order() {}
	
	public Order(OrderJSON order) {
		this.orderN=order.getOrderN();
		this.date=new Date();
		setTotalPrice(order.getTotalPrice());
		setProducts(order.getProducts());
		setColors(order.getColors());
		setShipping(order.getShipping());
		setCustomer(CustomerDAO.findByEmail(order.getCustomer().getEmail()));
		setFeedback(order.getFeedback());
	}
	
	public Order(Cart cart) {
		orderN=OrderDAO.getNext()+1;
		date=new Date();
		setTotalPrice(Double.parseDouble(cart.getTotalPrice()));
		setProducts(cart.getProducts());
		setColors(cart.getColors());
		setShipping(cart.getShipping());
		setCustomer(cart.getCustomer());
		setFeedback(cart.getProducts().size());
	}
	public Order(int orderN, Date date, Double totalPrice, Customer customer, List<Item> products, List<String> colors, List<Integer> shipping, List<Integer> feedback) {
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
	public Date getDate() {
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
