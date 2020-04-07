package com.ingsw.controller;

import java.io.IOException;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ingsw.model.Order;
import com.ingsw.model.OrderDAO;
import com.ingsw.utils.EmailUtility;
import com.ingsw.utils.InvoiceGenerator;


@WebServlet("/OrderSuccess")
public class OrderSuccess extends HttpServlet {
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
    
    public OrderSuccess() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Order myOrder=new Order();
		if (session.getAttribute("order")!=null) {
			myOrder=(Order) session.getAttribute("order");
			OrderDAO o=new OrderDAO();
			o.createOrder(myOrder);
		}
		
		String recipient = myOrder.getCustomer().getEmail();
        String subject = "Ordine effettuato";
        String content = "Grazie per l'acquisto, "+myOrder.getCustomer().getFirstName()+"!<br><br>\n\n"+
        "In allegato puoi trovare la fattura relativa al tuo ordine."
        + "<br><br><br><br><br><br>Il team di EC-18";
        try {
        	Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content, "utf-8", "html");
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(InvoiceGenerator.generateInvoice(myOrder), "application/pdf", null);
            attachmentBodyPart.setFileName("Fattura"+myOrder.getOrderN()+".pdf");
            multipart.addBodyPart(attachmentBodyPart);
            EmailUtility.sendEmailWithMultipart(host, port, email, name, pass,
                    recipient, subject, content, multipart);
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
		session.setAttribute("messageOrder", "Ordine effettuato con successo.");
		request.getRequestDispatcher("ShowProfile").forward(request, response);
	}

	

}
