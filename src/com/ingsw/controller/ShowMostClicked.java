package com.ingsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ingsw.model.Item;
import com.ingsw.model.ItemDAO;


@WebServlet("/ShowMostClicked")
public class ShowMostClicked extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowMostClicked() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDAO id=new ItemDAO();
		List<Item> itemList=id.getMostClicked();
		
		
		request.setAttribute("itemList", itemList);
		request.setAttribute("total", itemList.size());
		request.getRequestDispatcher("mostClicked.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
