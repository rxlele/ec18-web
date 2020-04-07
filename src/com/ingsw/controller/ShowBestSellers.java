package com.ingsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ingsw.model.Item;
import com.ingsw.model.OrderDAO;


@WebServlet("/ShowBestSellers")
public class ShowBestSellers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowBestSellers() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filter=0;
		if (request.getParameter("timeSelection")!=null)
			filter=Integer.parseInt(request.getParameter("timeSelection"));
		OrderDAO od=new OrderDAO();
		List<Item> itemList=new ArrayList<Item>();
		itemList=od.getBestSellers(filter);
		int total=od.getTotalSold(filter);
		List<Integer> orderData=od.getBestSellersData(filter);
		request.setAttribute("itemList", itemList);
		request.setAttribute("total", total);
		request.setAttribute("orderData", orderData);
		request.setAttribute("filter", filter );
		request.getRequestDispatcher("bestSellers.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
