package com.ingsw.model;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Item {
	
	
	private int productID;
	private String name;
	private String description;
	private double price;
	private List<String> path_img;
	private boolean onSale;
	private int rating;
	private String category;
	private List<String> tag;
	private List<String> colors;
	private List<Integer> feedback;
	private boolean isDeleted;
	private int clicks;
	
	public Item() {
	}
	
	@Deprecated		
	public Item(int productID, String name, String description, double price, Array path_imgs, boolean onSale,
				int rating, String category, Array tag, Array colors, Array feedback) {
		setID(productID);
		setName(name);
		setDescription(description);
		setPrice(price);
		setPath(path_imgs);
		setOnSale(onSale);
		setRating(rating);
		setCategory(category);
		setTag(tag);
		setColors(colors);
		setFeedback(feedback);
		
	}
	
	public Item(int productID, String name, String description, double price, Array path_imgs, boolean onSale,
			int rating, String category, Array tag, Array colors, Array feedback, boolean isDeleted, int clicks) {
	setID(productID);
	setName(name);
	setDescription(description);
	setPrice(price);
	setPath(path_imgs);
	setOnSale(onSale);
	setRating(rating);
	setCategory(category);
	setTag(tag);
	setColors(colors);
	setFeedback(feedback);
	setIsDeleted(isDeleted);
	setClicks(clicks);
}

	public void setID(int productID) {
		this.productID = productID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPath(Array path) {
		if (path != null) {
			try {
				String[] ar = (String[]) path.getArray();
				List<String> listTags = new ArrayList<>(Arrays.asList(ar));
				path_img = new ArrayList<String>();
				path_img.addAll(listTags);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTag(Array tags) {
		if (tags != null) {
			try {
				String[] ar = (String[]) tags.getArray();
				List<String> listTags = new ArrayList<>(Arrays.asList(ar));
				tag = new ArrayList<String>();
				tag.addAll(listTags);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public void setColors(Array colors) {
		if (colors != null) {
			try {
				String[] ar = (String[]) colors.getArray();
				List<String> listTags = new ArrayList<>(Arrays.asList(ar));
				this.colors = new ArrayList<String>();
				this.colors.addAll(listTags);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void setFeedback(Array feedback) {
		if (feedback != null) {
			try {
				Integer[] ar = (Integer[]) feedback.getArray();
				List<Integer> listFeedback = new ArrayList<>(Arrays.asList(ar));
				this.feedback = new ArrayList<Integer>();
				this.feedback.addAll(listFeedback);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public void setIsDeleted(boolean deleted) {
		this.isDeleted=deleted;
	}
	public void setClicks(int clicks) {
		this.clicks=clicks;
	}

	public int getID() {
		return productID;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionHTML() {
		return description.replace("\\t\\n", "<br/>").replace("e'\\n'", "<br/>");
	}
	public double getPrice() {
		return price;
	}

	public List<String> getPath() {
		return path_img;
	}

	public boolean getOnSale() {
		return onSale;
	}

	public int getRating() {
		return rating;
	}

	public String getCategory() {
		return category;
	}

	public List<String> getTag() {
		return tag;
	}

	public List<String> getColors() {
		return colors;
	}

	public List<Integer> getFeedback() {
		if (feedback==null)
			feedback=new ArrayList<Integer>();
		return feedback;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public int getClicks() {
		return clicks;
	}
}
