package com.ingsw.model;


//Singleton pattern
public final class Admin extends User {
	
	private static Admin instance=null;
	
	private Admin(String firstname, String lastname, String email, String password) {
		super(firstname, lastname, email, password);
	}
	
	public static synchronized Admin getAdmin() {
		if (instance==null)
			instance=new Admin("admin","admin","admin@ec18","adminec18");
		return instance;
	}
	
}
