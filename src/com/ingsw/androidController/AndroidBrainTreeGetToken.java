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


@WebServlet("/AndroidBrainTreeGetToken")
public class AndroidBrainTreeGetToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AndroidBrainTreeGetToken() {
        super();
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientTokenJSON=new Gson().toJson(new BrainTreeService().getToken());
		PrintWriter out = response.getWriter();	
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(clientTokenJSON);
        out.flush();
	}

}
