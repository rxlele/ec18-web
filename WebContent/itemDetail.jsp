<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="com.ingsw.model.Cart" %>
<%@ page import="com.ingsw.model.Item" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
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

    
<title> ${product.name} </title>
<link rel="icon" href="images/newlogo.png">
<style>
.bg-primary {
 background-color: #343A40 !important;
}
.logout {
    background-color:transparent;
    border:none;    
}
footer {
    background-color: orange;
    
    height: 100px;
    width: 100%;
    overflow: hidden;
    position: absolute;
  	right: 0;bottom:0;left:0;
}
body {
	position: relative;
  min-height: 100%;
  min-height: 100vh;
}
#myCarousel .list-inline {
    white-space:nowrap;
    overflow-x:auto;
}

#myCarousel .carousel-indicators {
    position: static;
    left: initial;
    width: initial;
    margin-left: initial;
}

#myCarousel .carousel-indicators > li {
    width: initial;
    height: initial;
    text-indent: initial;
}

#myCarousel .carousel-indicators > li.active img {
    opacity: 0.7;
}
.img-fluid_thumb{
position: relative;
  height:60px;
	
}
.wrapper {
    text-align: center;
    width:100%;
}

.button {
    position: absolute;
    top: 50%;
}
.middle {
  position: relative;
  height:400px;
}

.middle img {
  max-width: 100%;
  max-height:100%;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}

.buttonz {
	margin: auto;
	display: inline-block;
}
.space { 
	margin:0; 
	padding:0; 
	height:70px; 
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
  <body>
  
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
if(session.getAttribute("user") == null || !session.getAttribute("user").toString().equals("admin@ec18")){
Cart cart=null;
if (session.getAttribute("cart")!=null){
	cart= (Cart) session.getAttribute("cart");
	request.setAttribute("cart",cart);
}  if (cart==null){
		out.print("<a  class=\"p-2 text-dark\" href=\"./cart.jsp\"> <span class=\"fas fa-shopping-cart\"></span> Carrello</a>");
	} else {
		if (!cart.getProducts().isEmpty()) {%>
    <a  class="p-2 text-blue" href="./cart.jsp"><span class="blink"> <span class="fas fa-shopping-cart"></span></span> Carrello</a>
   <% } else { %>
   <a  class="p-2 text-dark" href="./cart.jsp"> <span class="fas fa-shopping-cart"></span> Carrello</a>
   <% } } }%>
   
 <%
	String userName = null;
	//allow access only if session exists
	if(session.getAttribute("user") == null){
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
 
 <h5 align="center" class="text-danger"> ${message} </h5>
<!-- Page Content -->
<div class="container">

  <!-- Portfolio Item Heading -->

  <!-- Portfolio Item Row -->
  <div class="space"></div>
  <div class="row">

    <div class="col-md-6">
    <!--   <img class="img-fluid" src="http://placehold.it/750x500" alt=""> -->
  <div class="container py-2">
    <div class="row">
     
        <div class="col-lg-8 offset-lg-0" id="slider">
                    <h2  align="center"> ${product.name} </h2>
       
            <div id="myCarousel" class="carousel slide">
                <!-- main slider carousel items -->
                <div class="carousel-inner">
                    <div class="active carousel-item" data-slide-number="0">
                    <div class="middle">
                                            <img src="${product.path[0]}" class="img-fluid">
                    
                    </div>
                    </div>
            
                             <c:forEach items="${product.path}" var="i" varStatus="curr" begin="1">           
                    <div class="carousel-item" data-slide-number="${curr.index}">
                    <div class="middle">
                                            <img src="${i}" class="img-fluid">
                    
                    </div>
                    </div>
                 </c:forEach>

                   

                </div>
                <!-- main slider carousel nav controls -->


                <ul class="carousel-indicators list-inline mx-auto border px-2">
                    <li class="list-inline-item active">
                        <a id="carousel-selector-0" class="selected" data-slide-to="0" data-target="#myCarousel">
                            <img src="${product.path[0]}" class="img-fluid_thumb">
                        </a>
                    </li>
                    <c:forEach items="${product.path}" var="i" varStatus="curr" begin="1">
                    <li class="list-inline-item">
                        <a id="carousel-selector-${curr.index}" data-slide-to="${curr.index}" data-target="#myCarousel">
                            <img src="${i}" class="img-fluid_thumb">
                        </a>
                    </li>
                    </c:forEach>
                    
                    
                    
                </ul>
            </div>
        </div>

    </div>
    <!--/main slider carousel-->
</div>
    </div>

    <div class="col-md-4">
              <div class="wrapper">
       <div class="container">
     <div class="buttonz">        
    <div class="ratings mt-3">
                  <c:forEach var="j" begin="1" end="${product.rating}">  
  					   <% out.print("<span class=\"fas fa-star\"></span>"); %>
				  </c:forEach>
				  <c:forEach var="k" begin="${product.rating+1}" end="5">  
  					   <% out.print("<span class=\"far fa-star\"></span>"); %>
				  </c:forEach>  
				  
              </div><br><br>
              <div class="price-wrap h5">
              <c:choose>
  					      <c:when test="${product.onSale}">
  					      <h3><del class="mt-2" align="center">&euro; 	 <c:out value = "${product.price}0"/></del></h3>
  					     	
       				        	
       				       <h3 class="mt-2" align="center">&euro;   	<c:out value = "${ product.price - (  product.price div 100  * 20)}0"/>
       				        	</h3><span class="badge badge-primary">Offerta del giorno</span>
    					  </c:when>    
   					 	  <c:otherwise>
        				    	<h3 class="mt-2" align="center">&euro; 
        				    	<c:out value = "${product.price}0"/></h3>
    					  </c:otherwise>
						</c:choose>
              
              <br><br><br>
               </div>
<form id="cartForm" action="./AddToCart" method="POST" onsubmit="cartButton.disabled=true; return true;">
     <div class="btn-group btn-group-toggle" data-toggle="buttons" align="center" style="width:100%;">
      
               
  <label class="btn btn-outline-primary active"style="width:100%;">
    <input type="radio" name="colori" value="${product.colors[0]}" id="option1" autocomplete="off" checked>  <c:out value="${product.colors[0]}"/>
  </label>
   <c:forEach items="${product.colors}" var="i" varStatus="curr" begin="1"> 
  <label class="btn btn-outline-primary"style="width:100%;">
    <input type="radio" name="colori" value="${i}" id="option${curr.index+1}" autocomplete="off">  <c:out value="${i}"/>
  </label>
  </c:forEach>
</div>

<br><br>
<%
	if (session.getAttribute("user")!=null && session.getAttribute("user").toString().equals("admin@ec18")){
		
%>
     <select name="spedizione" class="custom-select" id="shippingSelection" >
        <option value="0" disabled selected hidden>Spedizione...</option>
        <option value="0">Standard (4/5 gg) Gratis</option>
        <option value="1">Espressa (48/72 ore) &euro; 4.00</option>
        <option value="2">Espressa (24 ore) &euro; 8.00</option>
      </select>
      <% } else {
    	  %>
      <select name="spedizione" required class="custom-select" id="shippingSelection" >
        <option value="" disabled selected hidden>Spedizione...</option>
        <option value="0">Standard (4/5 gg) Gratis</option>
        <option value="1">Espressa (48/72 ore) &euro; 4.00</option>
        <option value="2">Espressa (24 ore) &euro; 8.00</option>
      </select>
      <% } %>
<input type="hidden" name="productID" value="${product.ID}">
      <br><br>
      <%
	if (session.getAttribute("user")!=null && session.getAttribute("user").toString().equals("admin@ec18")){
	%>	</form><a href="./EditProduct?productID=${product.ID}"><button id="editButton" class="btn btn-outline-primary " align="center" style="width:100%;"  >Modifica prodotto</button></a>
	<%	} else { %>
	<c:choose>
    <c:when test="${!product.isDeleted && !empty product.name}">
       		<button type="submit" id="cartButton" class="btn btn-outline-primary " align="center" style="width:100%;"  >Aggiungi al carrello</button>
    </c:when>    
    <c:otherwise>
        		<button disabled id="cartButton" class="btn btn-outline-danger " align="center" style="width:100%;"  >Non disponibile</button>
    </c:otherwise>
</c:choose>
<%	}
%>

</form>
</div>
</div>
</div>
           
      <br>
     
     

    </div>
  </div>
  <!-- /.row -->

  <!-- Related Projects Row -->
 <h3 class="my-4">Descrizione</h3>

  <div class="row">
   <br><br>
      <p align="justify" style="white-space: pre-line" ><c:out value="${product.descriptionHTML}" escapeXml="false"/>  </p>
      

  </div>
  <!-- /.row -->

</div>
<!-- /.container -->

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
</script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
 	 <!-- Scripts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js" charset="utf-8"></script>
    <script charset="utf-8">
    $(document).ready(function() {

    	  $('.color-choose input').on('click', function() {
    	      var headphonesColor = $(this).attr('data-image');

    	      $('.active').removeClass('active');
    	      $('.left-column img[data-image = ' + headphonesColor + ']').addClass('active');
    	      $(this).addClass('active');
    	  });

    	});

    </script>
  </body>
</html>
