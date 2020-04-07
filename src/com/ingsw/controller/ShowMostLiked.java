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


@WebServlet("/ShowMostLiked")
public class ShowMostLiked extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowMostLiked() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Item> itemList=new ArrayList<Item>();
		ItemDAO id=new ItemDAO();
		itemList=id.getMostLiked();
		int total=id.getTotalFeedbacks();
		List<Integer> feedbackList=id.getFeedbackNumbers();
		request.setAttribute("feedbackList", feedbackList);
		request.setAttribute("total", total);
		request.setAttribute("itemList", itemList);
		request.getRequestDispatcher("mostLiked.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
