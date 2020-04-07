package com.ingsw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ingsw.model.Customer;
import com.ingsw.model.CustomerDAO;


@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public EditUser() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Customer u=CustomerDAO.findByEmail(session.getAttribute("user").toString());
		boolean edit;
		if(request.getParameter("modifica")!=null)
			edit=true;
		else {  //cliccato salva
			CustomerDAO ud=new CustomerDAO();
			ud.updateDetails(request.getParameter("firstname"),request.getParameter("lastname"),request.getParameter("address"), request.getParameter("city"), request.getParameter("province"), session.getAttribute("user").toString());
			edit=false;
			request.setAttribute("user", u);
			request.setAttribute("edit", edit);
			request.getRequestDispatcher("./ShowProfile").forward(request,response);
			return;
		}
		request.setAttribute("user", u);
		request.setAttribute("edit", edit);
		request.getRequestDispatcher("profile.jsp").forward(request, response);
		
	}


}
