package com.ingsw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ingsw.model.Admin;


@WebServlet("/ManageProduct")
public class ManageProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ManageProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		if(session.getAttribute("user") == null){
			
			request.getRequestDispatcher("login.jsp").forward(request,response);
			response.sendRedirect("login.jsp");
		}
		request.setAttribute("user", Admin.getAdmin());
		request.setAttribute("edit", false);
		boolean buttonPressed=true;
		request.setAttribute("buttonPressed", buttonPressed);
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

	

}
