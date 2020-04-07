package com.ingsw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ingsw.model.CustomerDAO;


@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ChangePassword() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String message="";
		HttpSession session=request.getSession();
		CustomerDAO user=new CustomerDAO();
		if (user.changePassword(session.getAttribute("user").toString(),request.getParameter("passwd_old"),request.getParameter("passwd"))) {
			message="Password modificata con successo.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		else {
			message="Credenziali errate. Si prega di riprovare.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("changePassword.jsp").forward(request, response);
		}
	}

}
