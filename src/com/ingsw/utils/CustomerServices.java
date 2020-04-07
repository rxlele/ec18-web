package com.ingsw.utils;

import org.apache.commons.lang3.RandomStringUtils;

import com.ingsw.model.Customer;
import com.ingsw.model.CustomerDAO;

public class CustomerServices {
	
	
	public String resetUserPassword(String email) {
	    Customer u = CustomerDAO.findByEmail(email);
	     
	    String randomPassword = RandomStringUtils.randomAlphanumeric(10);
	    if (u!=null) {
	    u.setPasswd(randomPassword);
	    CustomerDAO.update(u);
	    return randomPassword;
	    }
	    return null;
	}
}
