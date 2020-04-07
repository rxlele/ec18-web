package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ingsw.model.CustomerDAO;



@WebServlet("/AndroidUpdateUser")
public class AndroidUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AndroidUpdateUser() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail=request.getParameter("mail");
		String firstname=request.getParameter("name");
		String lastname=request.getParameter("surname");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String province=request.getParameter("province");
		new CustomerDAO().updateDetails(firstname, lastname, address, city, province, mail);
		String userAsJSON=new Gson().toJson(CustomerDAO.findByEmail(mail));
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userAsJSON);
        out.flush(); 
	}

}
