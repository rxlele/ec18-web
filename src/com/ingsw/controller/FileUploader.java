package com.ingsw.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ingsw.utils.AmazonS3Example;



@WebServlet("/FileUploader")
@MultipartConfig(fileSizeThreshold=1024*1024*10,  // 10 MB (You can specify according to your choice)
maxFileSize=1024*1024*50,       // 50 MB  (You can specify according to your choice)
maxRequestSize=1024*1024*100)
public class FileUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
		if (request.getPart("file1")!=null) {
			String fileName1=request.getParameter("fileName1");
			Part filePart=  request.getPart("file1"); 
			try {
				InputStream inputStream=null;
				if(filePart!=null){
					inputStream=filePart.getInputStream();  
				}
				ObjectMetadata md = new ObjectMetadata();
			    md.setContentLength(filePart.getSize());
			    md.setContentType(filePart.getContentType());
			    new AmazonS3Example().upload("", fileName1, "Immagini", inputStream, md);    
			} catch (final Exception e) {
			    
			}
		}
		
	}
}
