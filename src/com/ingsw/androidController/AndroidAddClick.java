package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ingsw.model.ItemDAO;


@WebServlet("/AndroidAddClick")
public class AndroidAddClick extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AndroidAddClick() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("ID")!=null) {
			ItemDAO item=new ItemDAO();
			item.addClick(Integer.parseInt(request.getParameter("ID")));
			String resultJSON=new Gson().toJson(true);
			PrintWriter out = response.getWriter();	
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(resultJSON);
	        out.flush();
		} else {
			String resultJSON=new Gson().toJson(false);
			PrintWriter out = response.getWriter();	
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(resultJSON);
	        out.flush();
		}
	}

}
