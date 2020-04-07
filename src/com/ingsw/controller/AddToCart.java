package com.ingsw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ingsw.model.Cart;
import com.ingsw.model.CustomerDAO;
import com.ingsw.model.Item;
import com.ingsw.model.ItemDAO;


@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddToCart() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		Item item=new ItemDAO().findByID(Integer.parseInt(request.getParameter("productID")));
		HttpSession session=request.getSession();
		Cart myCart;
		if (session.getAttribute("cart")==null) {
			myCart=new Cart();
		} else {
			myCart=(Cart) session.getAttribute("cart"); 
		}
		myCart.setProducts(item);
		myCart.setShipping(Integer.parseInt(request.getParameter("spedizione")));
		myCart.setColors(request.getParameter("colori"));
	
		
		if (session.getAttribute("user")!=null)
			myCart.setCustomer(CustomerDAO.findByEmail(session.getAttribute("user").toString()));
		else 
			request.getRequestDispatcher("login.jsp").forward(request, response);
		
		session.setAttribute("cart", myCart );
		request.setAttribute("product", item);
		response.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("./ShowProduct").forward(request, response);
		
	}

}
