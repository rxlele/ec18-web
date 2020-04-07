package com.ingsw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ingsw.model.Item;
import com.ingsw.model.ItemDAO;


@WebServlet("/ShowProduct")
public class ShowProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowProduct() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ID=Integer.parseInt(request.getParameter("productID"));
		ItemDAO item=new ItemDAO();
		HttpSession session=request.getSession();
		if (session.getAttribute("user")!=null && !session.getAttribute("user").toString().equals("admin@ec18")) {
			item.addClick(ID);
		}
		Item product=item.findByID(ID);
		request.setAttribute("product", product);
		request.getRequestDispatcher("itemDetail.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
