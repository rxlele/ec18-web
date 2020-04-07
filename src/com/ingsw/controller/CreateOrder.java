package com.ingsw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ingsw.model.Cart;
import com.ingsw.model.Order;


@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CreateOrder() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		Order myOrder=null;
		if (cart!=null) {
			myOrder=new Order(cart);
			request.setAttribute("order", myOrder);
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}
		else {	
		request.getRequestDispatcher("cart.jsp").forward(request, response);
		}
	}

}
