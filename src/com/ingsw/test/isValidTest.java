package com.ingsw.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ingsw.model.CustomerDAO;


class isValidTest {

	@Test
	void testWithNullMailNullPassword() {
		System.out.println("testWithNullMailNullPassword");
		String mail=null;
		String password=null;
		assertThrows(NullPointerException.class, () -> {
            new CustomerDAO().isValid(mail, password);
        });

	}
	
	@Test
	void testWithWrongMailWrongPassword() {
		System.out.println("testWithWrongMailWrongPassword");
		String mail="admin@gmail.com";
		String password="12345";
		assertEquals(new CustomerDAO().isValid(mail, password), false, "Dovrebbe essere restituito false");
	}
	
	@Test
	void testWithRightMailWrongPassword() {
		System.out.println("testWithRightMailWrongPassword");
		String mail="admin@ec18";
		String password="12345";
		assertEquals(new CustomerDAO().isValid(mail, password), false, "Dovrebbe essere restituito false");
	}
	
	@Test
	void testWithWrongMailRightPassword() {
		System.out.println("testWithWrongMailRightPassword");
		String mail="admin@gmail.com";
		String password="adminec18";
		assertEquals(new CustomerDAO().isValid(mail, password), false, "Dovrebbe essere restituito false");
	}
	
	@Test
	void testWithRightMailRightPassword() {
		System.out.println("testWithRightMailRightPassword");
		String mail="admin@ec18";
		String password="adminec18";
		assertEquals(new CustomerDAO().isValid(mail, password), true, "Dovrebbe essere restituito true");
	}

}
