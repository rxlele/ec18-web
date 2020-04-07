package com.ingsw.controller;
 
import java.io.IOException;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ingsw.utils.EmailUtility;
import com.ingsw.utils.CustomerServices;

 
@WebServlet("/forgotPassword")
public class ResetPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private String host;
    private String port;
    private String email;
    private String name;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        email = context.getInitParameter("email");
        name = context.getInitParameter("name");
        pass = context.getInitParameter("pass");
    }
 
    public ResetPassword() {
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String page = "forgotPassword.jsp";
        request.getRequestDispatcher(page).forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
        String recipient = request.getParameter("email");
        String subject = "Recupero Password";
 
        CustomerServices customerServices = new CustomerServices();
        String newPassword = customerServices.resetUserPassword(recipient);
        if (newPassword==null) {
        	String message = "Non esiste un utente con la mail indicata.";
        	request.setAttribute("message", message);
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
        else {
        	 String content = "Ciao! La tua password provvisoria è la seguente: \n\n" + newPassword;
             content += "\n\nPer motivi di sicurezza, "
                     + "ti consigliamo di cambiare la password al tuo prossimo accesso."
             		+"\n\n\n\n\nIl team di EC-18";
      
             String message = "";
      
             try {
                 EmailUtility.sendEmail(host, port, email, name, pass,
                         recipient, subject, content);
                 message = "La password è stata ripristinata correttamente. Controlla la tua casella di posta.";
             } catch (Exception ex) {
                 ex.printStackTrace();
                 message = "C'è stato un errore. Contattare il supporto per maggiori informazioni: " + ex.getMessage();
             } finally {
                 request.setAttribute("message", message);
                 request.getRequestDispatcher("message.jsp").forward(request, response);
             }
        }
    }
 
}