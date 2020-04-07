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
import com.ingsw.model.ItemDAO;


@WebServlet("/index")
public class ShowIndex extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
       
  
    public ShowIndex() {
       super();
       
    }
    public void init() throws ServletException {
    	super.init();
    	//OffersTimer.createOffers();   //viene eseguito solo una volta
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDAO id=new ItemDAO();
		ArrayList<Item> itemList=id.refreshItems();
		if (itemList==null) {
			String message="Non ci sono prodotti da mostrare.";
			request.setAttribute("message", message);
		}
		List<String> brands=id.getBrands();
		if (brands!=null) {
			request.setAttribute("brandList", brands);
		}
		request.setAttribute("itemList", itemList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
