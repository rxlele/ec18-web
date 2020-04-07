package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ingsw.model.Order;
import com.ingsw.model.OrderDAO;
import com.ingsw.model.OrderJSON;
import com.ingsw.utils.EmailUtility;
import com.ingsw.utils.InvoiceGenerator;



@WebServlet("/AndroidPayment")
public class AndroidPayment extends HttpServlet {
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
	
    
    public AndroidPayment() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson gson = new Gson(); 
		String jSONFromAndroid=request.getReader().readLine();
		System.out.println("debugorderjson: "+jSONFromAndroid);
		OrderJSON orderJ = gson.fromJson(jSONFromAndroid,OrderJSON.class);
		System.out.println("testpay: "+orderJ.getOrderN()+orderJ.getDate());
		Order order=new Order(orderJ);
		System.out.println("testpay3: "+order.getOrderN());
		
		OrderDAO o=new OrderDAO();
		o.createOrder(order);
		
		String recipient = order.getCustomer().getEmail();
        String subject = "Ordine effettuato";
        String content = "Grazie per l'acquisto, "+order.getCustomer().getFirstName()+"!<br><br>\n\n"+
        "In allegato puoi trovare la fattura relativa al tuo ordine."
        + "<br><br><br><br><br><br>Il team di EC-18";
        try {
        	Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content, "utf-8", "html");
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(InvoiceGenerator.generateInvoice(order), "application/pdf", null);
            attachmentBodyPart.setFileName("Fattura"+order.getOrderN()+".pdf");
            multipart.addBodyPart(attachmentBodyPart);
            EmailUtility.sendEmailWithMultipart(host, port, email, name, pass,
                    recipient, subject, content, multipart);
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
		
		
		String booleanAsJSON=new Gson().toJson(true);
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(booleanAsJSON);
        out.flush(); 
		
		
	}

}
