package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ingsw.model.OrderDAO;



@WebServlet("/AndroidCheckFeedback")
public class AndroidCheckFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AndroidCheckFeedback() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderN=Integer.parseInt(request.getParameter("orderN"));
		int index=Integer.parseInt(request.getParameter("index"));
		int rating=Integer.parseInt(request.getParameter("rating"));
		new OrderDAO().addFeedback(orderN, rating, index);
		String booleanAsJSON=new Gson().toJson(true);
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(booleanAsJSON);
        out.flush();
        
	}

}
