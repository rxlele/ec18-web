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


@WebServlet("/AndroidModifyPassword")
public class AndroidModifyPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AndroidModifyPassword() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail=request.getParameter("mail");
		String oldPass=request.getParameter("pass");
		
		
		String newPass=request.getParameter("newPass");
		boolean result=new CustomerDAO().changePassword(mail, oldPass, newPass);
		String booleanAsJSON=new Gson().toJson(result);
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(booleanAsJSON);
        out.flush();
	}

}
