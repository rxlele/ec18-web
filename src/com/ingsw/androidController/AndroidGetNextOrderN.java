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



@WebServlet("/AndroidGetNextOrderN")
public class AndroidGetNextOrderN extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AndroidGetNextOrderN() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String intAsJSON=new Gson().toJson(OrderDAO.getNext());
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(intAsJSON);
        out.flush();
	}

}
