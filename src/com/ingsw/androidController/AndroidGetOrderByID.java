package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.ingsw.model.Order;
import com.ingsw.model.OrderDAO;


@WebServlet("/AndroidGetOrderByID")
public class AndroidGetOrderByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AndroidGetOrderByID() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int id=Integer.parseInt(request.getParameter("id"));
		Order order=new OrderDAO().findByOrderN(id);
		String orderAsJSON=new GsonBuilder().setDateFormat("E MMM dd HH:mm:ss 'GMT'Z yyyy").create().toJson(order);
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(orderAsJSON);
        out.flush(); 
	}

}
