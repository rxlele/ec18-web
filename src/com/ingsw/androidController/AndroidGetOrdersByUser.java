package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.GsonBuilder;
import com.ingsw.model.Order;
import com.ingsw.model.OrderDAO;


@WebServlet("/AndroidGetOrdersByUser")
public class AndroidGetOrdersByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AndroidGetOrdersByUser() {
        super();
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mail=request.getParameter("mail");
		List<Order> orderList=new OrderDAO().findByCustomer(mail);
		String ordersAsJSON=new GsonBuilder().setDateFormat("E MMM dd HH:mm:ss 'GMT'Z yyyy").create().toJson(orderList);
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("debug json: "+ordersAsJSON);
        out.print(ordersAsJSON);
        out.flush(); 
	}

}
