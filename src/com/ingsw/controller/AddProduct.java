package com.ingsw.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ingsw.model.ItemDAO;
import com.ingsw.utils.AmazonS3Example;


@WebServlet("/AddProduct")
@MultipartConfig(fileSizeThreshold=1024*1024*10,  // 10 MB (You can specify according to your choice)
maxFileSize=1024*1024*50,       // 50 MB  (You can specify according to your choice)
maxRequestSize=1024*1024*100)
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BASE_URL="https://elasticbeanstalk-us-east-2-832965785648.s3.us-east-2.amazonaws.com/Immagini/";
 
    public AddProduct() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		boolean err=false;
		String name=request.getParameter("productName").replace("\"", "”").replace("'", "’");
		double price=Double.parseDouble(request.getParameter("price"));
		String category=request.getParameter("category");
		boolean onSale=false;
		if (request.getParameter("onSale")!=null) 
			onSale=true;
		List<String> colors=new ArrayList<>();
		colors.add(request.getParameter("color1"));
		if (!request.getParameter("color2").equals(""))
			colors.add(request.getParameter("color2"));
		if (!request.getParameter("color3").equals(""))
			colors.add(request.getParameter("color3"));
		if (!request.getParameter("color4").equals(""))
			colors.add(request.getParameter("color4"));
		List<String> tag=new ArrayList<>();
		if (!request.getParameter("tag1").equals(""))
			tag.add(request.getParameter("tag1"));
		if (!request.getParameter("tag2").equals(""))
			tag.add(request.getParameter("tag2"));
		if (!request.getParameter("tag3").equals(""))
			tag.add(request.getParameter("tag3"));
		if (!request.getParameter("tag4").equals(""))
			tag.add(request.getParameter("tag4"));
		String description="Descrizione vuota";
		if (!request.getParameter("description").equals("")) {
			description=request.getParameter("description");
			description=description.replace("\t\n", "e'\n'");
		}
		
		List<String> paths = new ArrayList<>(); 
		if (request.getPart("file1")!=null && !request.getParameter("fileName1").equals("")) {
			String fileName=request.getParameter("fileName1");
			Part filePart=  request.getPart("file1"); 
			try {
				InputStream inputStream=null;
				if(filePart!=null){
					inputStream=filePart.getInputStream();  
				}
				ObjectMetadata md = new ObjectMetadata();
			    md.setContentLength(filePart.getSize());
			    md.setContentType(filePart.getContentType());
			    new AmazonS3Example().upload("", fileName, "Immagini", inputStream, md);   
			    paths.add(BASE_URL+fileName);
			} catch (final Exception e) {
			    err=true;
			}
		}
		
		if (request.getPart("file2")!=null && !request.getParameter("fileName2").equals("")) {
			String fileName=request.getParameter("fileName2");
			Part filePart=  request.getPart("file2"); 
			try {
				InputStream inputStream=null;
				inputStream=filePart.getInputStream();  
				ObjectMetadata md = new ObjectMetadata();
			    md.setContentLength(filePart.getSize());
			    md.setContentType(filePart.getContentType());
			    new AmazonS3Example().upload("", fileName, "Immagini", inputStream, md);    
			    paths.add(BASE_URL+fileName);
			} catch (final Exception e) {
				err=true;
			}
		}
		
		if (request.getPart("file3")!=null && !request.getParameter("fileName3").equals("")) {
			String fileName=request.getParameter("fileName3");
			Part filePart=  request.getPart("file3"); 
			try {
				InputStream inputStream=null;
				inputStream=filePart.getInputStream();  
				ObjectMetadata md = new ObjectMetadata();
			    md.setContentLength(filePart.getSize());
			    md.setContentType(filePart.getContentType());
			    new AmazonS3Example().upload("", fileName, "Immagini", inputStream, md);    
			    paths.add(BASE_URL+fileName);
			} catch (final Exception e) {
				err=true;
			}
		}
		
		if (request.getPart("file4")!=null && !request.getParameter("fileName4").equals("")) {
			String fileName=request.getParameter("fileName4");
			Part filePart=  request.getPart("file4"); 
			try {
				InputStream inputStream=null;
				inputStream=filePart.getInputStream();
				ObjectMetadata md = new ObjectMetadata();
			    md.setContentLength(filePart.getSize());
			    md.setContentType(filePart.getContentType());
			    new AmazonS3Example().upload("", fileName, "Immagini", inputStream, md);    
			    paths.add(BASE_URL+fileName);
			} catch (final Exception e) {
				err=true;
			}
		}
		if (request.getPart("file5")!=null && !request.getParameter("fileName5").equals("")) {
			String fileName=request.getParameter("fileName5");
			Part filePart=  request.getPart("file5"); 
			try {
				InputStream inputStream=null;
				inputStream=filePart.getInputStream(); 
				ObjectMetadata md = new ObjectMetadata();
			    md.setContentLength(filePart.getSize());
			    md.setContentType(filePart.getContentType());
			    new AmazonS3Example().upload("", fileName, "Immagini", inputStream, md);    
			    paths.add(BASE_URL+fileName);
			} catch (final Exception e) {
				err=true;
			}
		}
		if (request.getPart("file6")!=null && !request.getParameter("fileName6").equals("")) {
			String fileName=request.getParameter("fileName6");
			Part filePart=  request.getPart("file6"); 
			try {
				InputStream inputStream=null;
				inputStream=filePart.getInputStream();
				ObjectMetadata md = new ObjectMetadata();
			    md.setContentLength(filePart.getSize());
			    md.setContentType(filePart.getContentType());
			    new AmazonS3Example().upload("", fileName, "Immagini", inputStream, md);    
			    paths.add(BASE_URL+fileName);
			} catch (final Exception e) {
				err=true;
			}
		}
		if (paths.isEmpty()){
			paths.add(BASE_URL+"no_image.png");
		}
		ItemDAO i=new ItemDAO();
		i.adminInsertItem(name,description,price,paths,onSale,category,tag,colors);
		if (!err) {
			request.setAttribute("message", "Prodotto aggiunto con successo.");
			
		} else {
			request.setAttribute("message", "C'è stato un errore nell'inserimento del prodotto.");
		}
		ItemDAO itemDAO=new ItemDAO();
		request.setAttribute("id",itemDAO.nextProductID() );
		response.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("addItem.jsp").forward(request, response);
	}

}
