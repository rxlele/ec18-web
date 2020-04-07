package com.ingsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ingsw.model.Item;
import com.ingsw.model.ItemDAO;
import com.ingsw.model.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ApplyFilter")
public class ApplyFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ApplyFilter() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Item> itemList=new ArrayList<>();
		ItemDAO items=new ItemDAO();
		List<String> brands=items.getBrands();
		if (brands!=null) {
			request.setAttribute("brandList", brands);
		}
		if (request.getParameter("type")!=null && request.getParameter("type").equals("last")) {
			itemList=items.getLatestItems();
			request.setAttribute("itemList", itemList);
		} else if (request.getParameter("type")!=null && request.getParameter("type").equals("best")) {
			itemList=new OrderDAO().getBestSellers(true); //ne mostro solo 10
			request.setAttribute("itemList", itemList);
		} else {
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			String search=request.getParameter("search");
			if (search!=null)
				itemList=items.searchItem(search);
			else
				itemList=items.filterByCategory(type, name);
			if (itemList==null) {
				String message="Non ci sono prodotti da mostrare.";
				
				request.setAttribute("message", message);
			}
			else {
				request.setAttribute("itemList", itemList);
			}
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}


}
