package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ingsw.utils.CustomerServices;
import com.ingsw.utils.EmailUtility;


@WebServlet("/AndroidRecoveryPassword")
public class AndroidRecoveryPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String host;
    private String port;
    private String email;
    private String name;
    private String pass;
   
    public AndroidRecoveryPassword() {
        
    }
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        email = context.getInitParameter("email");
        name = context.getInitParameter("name");
        pass = context.getInitParameter("pass");
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("recuperopassword");
		String recipient = request.getParameter("mail");
        String subject = "Recupero Password";
 
        CustomerServices customerServices = new CustomerServices();
        String newPassword = customerServices.resetUserPassword(recipient);
        if (newPassword==null) {
        	String booleanAsJSON=new Gson().toJson(false);
    		PrintWriter out = response.getWriter();	
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(booleanAsJSON);
            out.flush(); 
        }
        else {
        	 String content = "Ciao! La tua password provvisoria è la seguente: \n\n" + newPassword;
             content += "\n\nPer motivi di sicurezza, "
                     + "ti consigliamo di cambiare la password al tuo prossimo accesso."
             		+"\n\n\n\n\nIl team di EC-18";
      
            
      
             try {
                 EmailUtility.sendEmail(host, port, email, name, pass,
                         recipient, subject, content);
                 
             } catch (Exception ex) {
                 ex.printStackTrace();
                 String booleanAsJSON=new Gson().toJson(false);
         		 PrintWriter out = response.getWriter();	
                 response.setContentType("application/json");
                 response.setCharacterEncoding("UTF-8");
                 out.print(booleanAsJSON);
                 out.flush(); 
             } finally {
            	 String booleanAsJSON=new Gson().toJson(true);
         		 PrintWriter out = response.getWriter();	
                 response.setContentType("application/json");
                 response.setCharacterEncoding("UTF-8");
                 out.print(booleanAsJSON);
                 out.flush(); 
             }
        }
	}

}
