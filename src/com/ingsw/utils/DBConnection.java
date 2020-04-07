package com.ingsw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
	private static final String url = "jdbc:postgresql://ingswdb.cdj9oghgba30.eu-west-3.rds.amazonaws.com:5432/ingswdemo";
	private static final String user = "ingsw";
	private static final String password = "basket29";
	
	
	public static Connection connect() {
		Connection conn=null;
		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
            System.out.println("ERROR ON DBCONNECTION: " + e.getMessage());
        }
		return conn;
    }
	
}
