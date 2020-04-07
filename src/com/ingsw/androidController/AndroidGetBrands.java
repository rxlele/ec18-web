package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ingsw.model.ItemDAO;


@WebServlet("/AndroidGetBrands")
public class AndroidGetBrands extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AndroidGetBrands() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> brands=new ItemDAO().getBrands();
		String ItemAsJSON=new Gson().toJson(brands);
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(ItemAsJSON);
        out.flush(); 
	}

}
