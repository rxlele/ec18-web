package com.ingsw.controller;

import java.io.IOException;
import com.ingsw.model.Customer;
import com.ingsw.model.CustomerDAO;
import com.ingsw.utils.EmailUtility;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CreateUser.do")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String host;
    private String port;
    private String email;
    private String name;
    private String pass;
    
    public void init() {
    	 ServletContext context = getServletContext();
         host = context.getInitParameter("host");
         port = context.getInitParameter("port");
         email = context.getInitParameter("email");
         name = context.getInitParameter("name");
         pass = context.getInitParameter("pass");
    }
    


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Customer newCustomer = new Customer(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("address"), request.getParameter("city"), request.getParameter("province"), request.getParameter("email"), request.getParameter("passwd"));
		boolean exists=new CustomerDAO().createUser(newCustomer);
		if (!exists) {
			String recipient = newCustomer.getEmail();
	        String subject = "Registrazione completata";
	        String content = "Ciao "+newCustomer.getFirstName()+", benvenuto sul sito EC-18. \n\n";
	        String htmlButton="<p>"+content+"</p><br>"+"<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" >\r\n" + 
	        		"  <tr align=\"center\">\r\n" + 
	        		"      <td align=\"center\">\r\n" + 
	        		"          <table cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\r\n" + 
	        		"              <tr align=\"center\">\r\n" + 
	        		"                  <td style=\"border-radius: 2px;\" bgcolor=\"#007bff\" align=\"center\">\r\n" + 
	        		"                      <a href=\"http://ingswprova.us-east-2.elasticbeanstalk.com\" target=\"_blank\"  style=\"padding: 8px 12px; border: 1px solid #007bff;border-radius: 2px;font-family: Helvetica, Arial, sans-serif;font-size: 14px; color: #ffffff;text-decoration: none;font-weight:bold;display: block; margin:auto;\">\r\n" + 
	        		"                          Effettua il tuo primo ordine             \r\n" + 
	        		"                      </a>\r\n" + 
	        		"                  </td>\r\n" + 
	        		"              </tr>\r\n" + 
	        		"          </table>\r\n" + 
	        		"      </td>\r\n" + 
	        		"  </tr>\r\n" + 
	        		"</table>";
	 
	        try {
	            EmailUtility.sendEmailWithHTML(host, port, email, name, pass,
	                    recipient, subject, content, htmlButton);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } 
		} else {
			request.setAttribute("exists", exists);
		}
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher view= request.getRequestDispatcher("userAdd.jsp");
		view.forward(request, response);
	}

}
