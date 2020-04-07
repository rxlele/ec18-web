package com.ingsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.ingsw.model.Admin;
import com.ingsw.model.Customer;
import com.ingsw.model.CustomerDAO;
import com.ingsw.model.Order;
import com.ingsw.model.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ShowProfile")
public class ShowProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowProfile() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		if (session.getAttribute("messageOrder")!=null) {
			request.setAttribute("messageOrder",session.getAttribute("messageOrder"));
			session.removeAttribute("order");
			session.removeAttribute("messageOrder");
			session.removeAttribute("cart");
			//Cart.deleteCart();
		}
		if(session.getAttribute("user") == null){
			
			request.getRequestDispatcher("login.jsp").forward(request,response);
			response.sendRedirect("login.jsp");
		}
		if (Admin.getAdmin().getEmail().equals(session.getAttribute("user").toString()))
			request.setAttribute("user", Admin.getAdmin());
		else {
			Customer u= CustomerDAO.findByEmail(session.getAttribute("user").toString());
			List<Order> listOrder=new ArrayList<>();
			OrderDAO od=new OrderDAO();
			listOrder=od.findByCustomer(session.getAttribute("user").toString());
			request.setAttribute("user", u);
			
			request.setAttribute("orders", listOrder);
			request.setAttribute("address", u.getAddress());
		}
		request.setAttribute("edit", false);
		request.getRequestDispatcher("profile.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
