package com.ingsw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ingsw.model.OrderDAO;


@WebServlet("/AddFeedback")
public class AddFeedback extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public AddFeedback() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new OrderDAO().addFeedback(Integer.parseInt(request.getParameter("orderNumber")), 
				Integer.parseInt(request.getParameter("rating")), Integer.parseInt(request.getParameter("feedbackIndex")));
		request.setAttribute("messageOrder", "Feedback lasciato con successo.");
		request.getRequestDispatcher("/ShowProfile").forward(request, response);
	}

}
