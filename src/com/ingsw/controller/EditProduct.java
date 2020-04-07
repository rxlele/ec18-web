package com.ingsw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ingsw.model.Item;
import com.ingsw.model.ItemDAO;


@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    public EditProduct() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ID=Integer.parseInt(request.getParameter("productID"));
		ItemDAO item=new ItemDAO();
		Item product=item.findByID(ID);
		request.setAttribute("product", product);
		request.getRequestDispatcher("editItem.jsp").forward(request, response);
	}



}
