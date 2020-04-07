package com.ingsw.utils;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ingsw.model.Cart;
import com.ingsw.model.Item;
import com.ingsw.model.Order;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;




public class InvoiceGenerator {
	private static final Paragraph SPACE=new Paragraph("\n\n\n\n\n\n");
	
	public static File generateInvoice(Order order) throws IOException, URISyntaxException {
		 
		File file =File.createTempFile("prefix", "suffix");
		
		// Open PDF document in write mode
	    PdfWriter pdfWriter = new PdfWriter(file);
	    //PdfReader pdfReader=new PdfReader(objectData);
	    PdfDocument pdfDocument = new PdfDocument(pdfWriter);	
	    // Create document to add new elements
	    Document document = new Document(pdfDocument);

	    // Create Paragraph
	    Paragraph paragraph = new Paragraph();
	    Paragraph paragraph2 = new Paragraph();
	    Paragraph paragraph3 = new Paragraph();
	    paragraph.add("Fattura \nordine n° "+order.getOrderN()).setTextAlignment(TextAlignment.RIGHT);

	    ImageData imageData = ImageDataFactory.create("https://elasticbeanstalk-us-east-2-832965785648.s3.us-east-2.amazonaws.com/Immagini/newlogo.png");
	    Image pdfImg = new Image(imageData);
	    pdfImg.setWidth(64);
	    pdfImg.setHeight(64);
	    pdfImg.setFixedPosition(50, 750);
	    
	    //Add Image to Paragraph
	    paragraph2.add(SPACE);
	    paragraph2.add("Spedisci a:\n\n").setTextAlignment(TextAlignment.LEFT);
	    paragraph2.add(order.getCustomer().getFirstName()+" "+order.getCustomer().getLastName()+"\n").setTextAlignment(TextAlignment.LEFT);
	    paragraph2.add(order.getCustomer().getAddress()+"\n").setTextAlignment(TextAlignment.LEFT);
	    paragraph2.add(order.getCustomer().getCity()+" "+order.getCustomer().getProvince()).setTextAlignment(TextAlignment.LEFT);
	    
	    paragraph3.add("Data:\n\n").setTextAlignment(TextAlignment.RIGHT);
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy \nHH:mm.ss");
	    Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(new Date()); // sets calendar time/date
	    cal.add(Calendar.HOUR_OF_DAY, 1); // adds two hour, because of timezones         //REMOVE THIS METHOD CALL IF WORKING IN LOCALHOST
	    paragraph3.add(dateFormat.format(cal.getTime())).setTextAlignment(TextAlignment.RIGHT);
	    
	    paragraph.add(pdfImg);
	    document.add(paragraph);
	    document.add(paragraph2.setFixedPosition(40, 580, 200));
	    document.add(paragraph3.setFixedPosition(450, 600, 100));
	    document.add(SPACE);
	    document.add(SPACE);
        Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

	    Cell nome = new Cell(1, 3).add(new Paragraph("Nome")).setBorder(Border.NO_BORDER);
        nome.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(nome);
        Cell spedizione = new Cell(1,2).add(new Paragraph("Spedizione")).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorder(Border.NO_BORDER);
        spedizione.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(spedizione);
        Cell prezzo = new Cell().add(new Paragraph("Prezzo")).setTextAlignment(TextAlignment.RIGHT).setBorderLeft(Border.NO_BORDER).setBorder(Border.NO_BORDER);
        prezzo.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(prezzo);
        for (int i=0;i<order.getProducts().size();i++) {
        	Cell q = new Cell(1, 3).add(new Paragraph(order.getProducts().get(i).getName()+" ("+order.getColors().get(i)+") ")).setBorderRight(Border.NO_BORDER).setBorder(Border.NO_BORDER);
            table.addCell(q);
            String shipping="";
            String shippingPrice="";
            if (order.getShipping().get(i)==0) {
            	shipping="Standard (4/5 gg)";
            	shippingPrice="0.00";
            }
            else if (order.getShipping().get(i)==1) {
            	shipping="Espressa (48/72 ore)";
            	shippingPrice="4.00";
            }
            else {
            	shipping="Espressa (24 ore)";
            	shippingPrice="8.00";
            }
            	
            Cell e = new Cell(1,2).add(new Paragraph(shipping+"  € "+shippingPrice)).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorder(Border.NO_BORDER);
            table.addCell(e);
            double price=0;
            if (order.getProducts().get(i).getOnSale())
				price+=order.getProducts().get(i).getPrice()-(0.2*order.getProducts().get(i).getPrice());
			else
				price+=order.getProducts().get(i).getPrice();
            Cell r = new Cell().add(new Paragraph("€ "+Cart.round(price, 2)+"0").setTextAlignment(TextAlignment.RIGHT)).setBorderLeft(Border.NO_BORDER).setBorder(Border.NO_BORDER);
            table.addCell(r);
        }
	    document.add(table);
	    Table table2;
	    if (order.getProducts().size()<=11) {
	    	table2 = new Table(UnitValue.createPercentArray(2)).setFixedPosition(360,180,200).setBorder(Border.NO_BORDER);
	    } else if (order.getProducts().size()>11 && order.getProducts().size()<=20) {
	    	table2 = new Table(UnitValue.createPercentArray(2)).setFixedPosition(2, 360, 580, 200).setBorder(Border.NO_BORDER);
	    } else {
	    	table2 = new Table(UnitValue.createPercentArray(2)).setFixedPosition(2, 360, 180, 200).setBorder(Border.NO_BORDER);
	    }
	    Cell q = new Cell().add(new Paragraph("Totale parziale:").setBold()).setBorder(Border.NO_BORDER);
        table2.addCell(q);
        double subtotal=0;
        for (Item i:order.getProducts()) {
        	if (i.getOnSale())
        		subtotal+=i.getPrice()-(0.2*i.getPrice());
        	else 
        		subtotal+=i.getPrice();
        }
        Cell w = new Cell().add(new Paragraph("€ "+Cart.round(subtotal, 2)+"0").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER);
        table2.addCell(w);
        Cell e = new Cell().add(new Paragraph("Spedizione:").setBold()).setBorder(Border.NO_BORDER);
        table2.addCell(e);
        int totalShipping=0;
        for (Integer i:order.getShipping()) {
        	if (i==1)
        		totalShipping+=4;
        	else if (i==2)
        		totalShipping+=8;
        }
        Cell r = new Cell().add(new Paragraph("€ "+totalShipping+".00").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER);
        table2.addCell(r);
        Cell t = new Cell().add(new Paragraph("Totale:").setBold()).setBorder(Border.NO_BORDER);
        table2.addCell(t);
        double totalPrice=totalShipping+subtotal;
        Cell y = new Cell().add(new Paragraph("€ "+Cart.round(totalPrice, 2)+"0").setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER);
        table2.addCell(y);
	    document.add(table2);
	    if (order.getProducts().size()>11) {
	    	document.add(new Paragraph("Grazie per l'acquisto dal team di EC-18.").setFixedPosition(2, 180, 20, 1000).setBold());
	    } else {
	    	document.add(new Paragraph("Grazie per l'acquisto dal team di EC-18.").setFixedPosition(180, 20, 1000).setBold());
	    }
	    // Close document
	    
	    document.close();
	    
	    new AmazonS3Example().upload("", "Fattura"+order.getOrderN()+".pdf", "Fatture",file);
	    return file;
		    
	  }
}
