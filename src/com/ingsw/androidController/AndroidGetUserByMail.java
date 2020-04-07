package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ingsw.model.Customer;
import com.ingsw.model.CustomerDAO;


@WebServlet("/AndroidGetUserByMail")
public class AndroidGetUserByMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AndroidGetUserByMail() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mail=request.getParameter("mail");
		Customer customer=CustomerDAO.findByEmail(mail);
		String userAsJSON=new Gson().toJson(customer);
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userAsJSON);
        out.flush(); 
	}

}
