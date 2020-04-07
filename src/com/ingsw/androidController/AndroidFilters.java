package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ingsw.model.ItemDAO;
import com.ingsw.model.Item;


@WebServlet("/AndroidFilters")
public class AndroidFilters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AndroidFilters() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String filterType=request.getParameter("filterType");
		String subFilter="null";
		if (request.getParameter("subFilter")!=null) 
			subFilter=request.getParameter("subFilter");
		ItemDAO id= new ItemDAO();
		List<Item> itemList=new ArrayList<>();
		if (filterType.equals("Categoria") || filterType.equals("Prezzo") /*|| filterType.equals("Brand")*/)
			itemList=id.getFilters(filterType, subFilter);
		else 
			itemList=id.getFilters(filterType, subFilter);
		String ItemAsJSON=new Gson().toJson(itemList);
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(ItemAsJSON);
        out.flush(); 
	}

}
