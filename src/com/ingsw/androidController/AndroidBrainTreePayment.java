package com.ingsw.androidController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ingsw.utils.BrainTreeService;


@WebServlet("/AndroidBrainTreePayment")
public class AndroidBrainTreePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AndroidBrainTreePayment() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("nonce: "+request.getParameter("nonce")+"amount: "+request.getParameter("paymentAmount"));
		if (request.getParameter("nonce")!=null) {
			BrainTreeService brainTreeService=new BrainTreeService();
			boolean result=brainTreeService.createTransaction(
					request.getParameter("nonce"),request.getParameter("paymentAmount"));
			String resultJSON=new Gson().toJson(result);
			System.out.println("token3 not null:"+result);
			PrintWriter out = response.getWriter();	
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(resultJSON);
	        out.flush();
		} else {
			System.out.println("token3 null");
		}
	}

}
