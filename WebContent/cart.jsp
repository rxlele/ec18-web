<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="com.ingsw.model.Cart" %>
<%@ page import="com.ingsw.model.Item" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.lang.Exception" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">


<title>Carrello</title>
<link rel="icon" href="images/newlogo.png">
<style>
.bg-primary {
 background-color: #343A40 !important;
}
.logout {
    background-color:transparent;
    border:none;    
}


.project-tab.container.row.col-md-12.nav.nav-tabs.nav-fill.nav-item.nav-link {
    padding: 10%;
    margin-top: -8%;
}
.project-tab #tabs{
    background: #007b5e;
    color: #eee;
}
.project-tab #tabs h6.section-title{
    color: #eee;
}
.project-tab #tabs .nav-tabs .nav-item.show .nav-link, .nav-tabs .nav-link.active {
    color: #0062cc;
    background-color: transparent;
    border-color: transparent transparent #f3f3f3;
    border-bottom: 3px solid !important;
    font-size: 16px;
    font-weight: bold;
}
.project-tab .nav-link {
    border: 1px solid transparent;
    border-top-left-radius: .25rem;
    border-top-right-radius: .25rem;
    color: #0062cc;
    font-size: 16px;
    font-weight: 600;
}
.project-tab .nav-link:hover {
    border: none;
}
.project-tab thead{
    background: #f3f3f3;
    color: #333;
}
.project-tab a{
    text-decoration: none;
    color: #333;
    font-weight: 600;
}

body {
	position: relative;
  min-height: 100%;
  min-height: 100vh;
}

.space {
	margin:0;
	padding:0;
	height:70px;
}
footer {
    background-color: orange;
    
    height: 100px;
    width: 100%;
    overflow: hidden;
    position: absolute;
  	right: 0;bottom:0;left:0;
}


		.blinker{
			animation: blinker 1.5s linear infinite;
		}
		@keyframes blinker {
		  50% {
			opacity: 0;
		  }
		}

		.home-main button:hover{
			transition: 1s ease;
			color:#5812c5;
			width: 19%;
		}
		.content1-left{
			padding:9%;
		}
		.content1-right{
			padding:9%;
			background:#e4e4e4;
		}




.bloc_left_price {
    color: #c01508;
    text-align: center;
    font-weight: bold;
    font-size: 150%;
}
.category_block li:hover {
    background-color: #007bff;
}
.category_block li:hover a {
    color: #ffffff;
}
.category_block li a {
    color: #343a40;
}
.add_to_cart_block .price {
    color: #c01508;
    text-align: center;
    font-weight: bold;
    font-size: 200%;
    margin-bottom: 0;
}
.add_to_cart_block .price_discounted {
    color: #343a40;
    text-align: center;
    text-decoration: line-through;
    font-size: 140%;
}
.product_rassurance {
    padding: 10px;
    margin-top: 15px;
    background: #ffffff;
    border: 1px solid #6c757d;
    color: #6c757d;
}
.product_rassurance .list-inline {
    margin-bottom: 0;
    text-transform: uppercase;
    text-align: center;
}
.product_rassurance .list-inline li:hover {
    color: #343a40;
}
.reviews_product .fa-star {
    color: gold;
}
.pagination {
    margin-top: 20px;
}

.blink > span{
		animation: blink 1s linear infinite;
	}
@keyframes blink{
0%{opacity: 0;}
50%{opacity: .5;}
100%{opacity: 1;}
}


</style>
  </head>
  <body >
  
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="./index">
        <img src="images/newlogo.png" width="30" height="30" alt="">
      </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="./index"> <span class="fas fa-home"></span> Home</a>
      <a class="nav-item nav-link active" href="./info.jsp"> <span class="fas fa-info-circle"></span> Contattaci</a>
    </div>
  </div>
  <nav class="my-2 my-md-0 mr-md-3">
  
<%
Cart cart=null;
if(session.getAttribute("user") != null){
	if (session.getAttribute("user").toString().equals("admin@ec18")) {
		%> 
		<jsp:forward page="./index"/>
		<%
		session.removeAttribute("cart");
	} else {
		if (session.getAttribute("cart")!=null){
			cart= (Cart) session.getAttribute("cart");
			request.setAttribute("cart",cart);
		} 
		 
     	try {
     		if (request.getParameter("toDelete")!=null){
         		int index=Integer.parseInt(request.getParameter("toDelete"));
         		if (!cart.getProducts().isEmpty()){
         			cart.getProducts().remove(index);
         			cart.getColors().remove(index);
         			cart.getShipping().remove(index);
         			if (cart.getProducts().size()==0)
         				cart.setIsEmpty(true);
         		}
         	}
     	} catch (Exception e){
     		e.printStackTrace();
     	}
     
		if (cart==null || cart.getProducts().size()==0){
			out.print("<a  class=\"p-2 text-dark\" href=\"./cart.jsp\"> <span class=\"fas fa-shopping-cart\"></span> Carrello</a>");
		} else { %>
		    <a  id="cartButton" class="p-2 text-blue" href="./cart.jsp" onsubmit="cartButton.disabled=true; return true;" ><span class="blink"> <span class="fas fa-shopping-cart"></span></span> Carrello</a>
 <%} }
}%>
 <%
	String userName = null;
	//allow access only if session exists
	if(session.getAttribute("user") == null){
		%> <jsp:forward page="./login.jsp"/> <%
		out.print("<a class=\"p-2 text-dark\" href=\"./login.jsp\"> <span class=\"fas fa-user-circle\"></span> Login</a>");
		out.print("</nav><a class=\"btn btn-outline-primary\" href=\"./register.html\">Registrati</a>");
	}
	else {
		userName = (String) session.getAttribute("user");

		out.print("<a class=\"p-2 text-dark\" href=\"./ShowProfile\"> <span class=\"fas fa-user-circle\"></span> Profilo</a>");
		out.print("</nav>");
		%>
		<form action="<%=response.encodeURL("LogoutUser") %>" method="post">
		<span class="fas fa-sign-out-alt"></span><input class="logout" type="submit" value="Logout" >
		</form>
	<% }	
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")) 
				userName = cookie.getValue();
		}
	}
%> 
</nav>
 
 
<br><br>


    <div class="container">
        <h2 align="center"> Carrello</h2><br>
     </div>


<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"> </th>
                            <th scope="col">Prodotto</th>
                            <th scope="col">Spedizione</th>
                            <th scope="col" class="text-center">&nbsp;</th>
                            <th scope="col" class="text-right">Prezzo</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                   
                    <c:forEach items="${cart.products}" var="i" varStatus="curr">
                        <tr>
                            <td><div align="center"><img src="${i.path[0]}" width="auto" height="50" /></div> </td>
                            <td> ${i.name} (${cart.colors[curr.index]}) </td>
                            <c:if test="${cart.shipping[curr.index] == 0}">
                            <td>Standard (4/5 gg) Gratis</td>
                            </c:if>
                            <c:if test="${cart.shipping[curr.index] == 1}">
                            <td>Espressa (48/72 ore) &euro; 4.00</td>
                            </c:if>
                            <c:if test="${cart.shipping[curr.index] == 2}">
                            <td>Espressa (24 ore) &euro; 8.00</td>
                            </c:if>
                            <td>&nbsp;</td>
                          <c:choose>
  					      <c:when test="${i.onSale}">
  					     	  <td class="text-right">&euro; ${ i.price - (  i.price div 100  * 20)}0</td>
  					     	
       				        	
       				        	
    					  </c:when>    
   					 	  <c:otherwise>
        				    	
        				    	 <td class="text-right">&euro; ${i.price}0</td>
    					  </c:otherwise>
						</c:choose>
                            <td class="text-right">
                            
                            <form action="./cart.jsp">
                            	<input type="hidden" name="toDelete" value="${curr.index}">
                            	<button type="submit" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button>
                            </form>
                             </td>
                        </tr>
                     </c:forEach>  
                        
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Spedizione</td>
                             <%
                           		if (cart==null) {
                           %>
                            <td class="text-right"><strong>&euro; 0.00</strong></td>
                            <% } else { %>
                             <td class="text-right"><strong>&euro; <%= cart.getTotalShipping() %>0 </strong></td>
                             <% } %>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>Totale</strong></td>
                           <%
                           		if (cart==null) {
                           %>
                            <td class="text-right"><strong>&euro; 0.00</strong></td>
                            <% } else { %>
                             <td class="text-right"><strong>&euro; <%= cart.getTotalPrice() %>0 </strong></td>
                             <% } %>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                   <a href="./index"> <button class="btn btn-lg btn-block btn-light" >Torna allo Shopping</button></a>
                </div>
               
                <div class="col-sm-12 col-md-6 text-right">
                <%if (cart==null || cart.getIsEmpty()) {
                	out.print(" <button type=\"submit\" disabled class=\"btn btn-lg btn-block btn-success\">Vai al pagamento</button>");

                %>
                <% } else {
                	out.print(" <a href=\"./CreateOrder\"> <button type=\"submit\" class=\"btn btn-lg btn-block btn-success\">Vai al pagamento</button></a>");
                }	%>
                  
                </div>
                
            </div>
        </div>
    </div>
</div>



<br><br><br><br><br>
<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
    <div class="container-fluid bg-primary py-3">
        <div class="container">
          <div class="row py-3">
            <div class="col-md-9">
              <p class="text-white">Copyright ingsw2019</p>
            </div>
            <div class="col-md-3">
              <div class="d-inline-block">
                  <a style="color:#343A40;">span</a>
                  <a class="fab fa-paypal fa-2x" style="color:white;"></a>
                  <a style="color:#343A40;">span</a>
                  <a class="fab fa-aws fa-2x" style="color:white;"></a>
              </div>
              
            </div>
          
          </div>
        </div>
      </div>
    
  </footer>
<script>
$('a').on("click", function (e) ({
    if($('a').hasClass('clicked'){
        e.preventDefault();
    } else {
        $('a').addClass('clicked');
    }
});
</script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>
