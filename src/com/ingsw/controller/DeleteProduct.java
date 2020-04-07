package com.ingsw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ingsw.model.ItemDAO;


@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteProduct() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("deleteFromDB"));
		int ID=Integer.parseInt(request.getParameter("productID"));
		ItemDAO id=new ItemDAO();
		if (request.getParameter("deleteFromDB")!=null) {
			id.deleteFromDB(ID);
			request.setAttribute("message", "Prodotto eliminato con successo dal database.");
		} else {
			if (id.deleteItem(ID)>0) {
				request.setAttribute("message", "Prodotto eliminato con successo.");
			} else {
				request.setAttribute("message", "Si è verificato un problema nell'eliminazione del prodotto.");
			}
		}
		response.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/index").forward(request, response);
	}

	

}
