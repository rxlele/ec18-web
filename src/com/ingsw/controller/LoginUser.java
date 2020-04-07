package com.ingsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ingsw.model.Admin;
import com.ingsw.model.CustomerDAO;


@WebServlet("/LoginUser.do")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CustomerDAO cd=new CustomerDAO();
		if (cd.isValid(request.getParameter("email"), request.getParameter("passwd"))) {
			HttpSession session = request.getSession();
			
			if (session.getAttribute("cart")!=null) {
				session.removeAttribute("cart");
			}
				
			
			session.setAttribute("user", request.getParameter("email"));
			if (!Admin.getAdmin().getEmail().equals(request.getParameter("email")))
				session.setAttribute("name", CustomerDAO.findByEmail(request.getParameter("email")).getFirstName());
			else {
				session.setAttribute("name", Admin.getAdmin().getFirstName());
			}
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", request.getParameter("email"));
			response.addCookie(userName);
			if (!Admin.getAdmin().getEmail().equals(request.getParameter("email"))) {
				Cookie userName2 = new Cookie("name",  CustomerDAO.findByEmail(request.getParameter("email")).getFirstName());
				response.addCookie(userName2);
			} else {
				Cookie userName2 = new Cookie("name",  Admin.getAdmin().getFirstName());
				response.addCookie(userName2);
			}
			
			String encodedURL = response.encodeRedirectURL("loginSuccess.jsp");
			response.sendRedirect(encodedURL);
			
		} else {
			response.setCharacterEncoding("UTF-8");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.include(request, response);
		}
		
	}

}
