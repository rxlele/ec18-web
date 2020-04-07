package com.ingsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ingsw.model.Cart;
import com.ingsw.model.OrderDAO;


@WebServlet("/ShowReceipts")
public class ShowReceipts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowReceipts() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Double> receiptsList=new OrderDAO().getWeeklyReceipts();
		double totalWeekly=0.0;
		for (Double i:receiptsList)
			totalWeekly+=i;
		totalWeekly=Cart.round(totalWeekly, 2);
		double totalReceipts=Cart.round(new OrderDAO().getTotalReceipts(), 2);
		List<String> best=new OrderDAO().bestDayOfReceipt();
		String bestDate=best.get(0);
		String bestReceipt=best.get(1);
		request.setAttribute("receiptsList", receiptsList);
		request.setAttribute("totalWeekly", totalWeekly);
		request.setAttribute("totalReceipts", totalReceipts);
		request.setAttribute("bestDate", bestDate);
		request.setAttribute("bestReceipt", bestReceipt);
		request.getRequestDispatcher("receipts.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
