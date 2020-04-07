<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="com.ingsw.model.Cart" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<link rel="stylesheet" type="text/css" href="//wpcc.io/lib/1.0.2/cookieconsent.min.css"/><script src="//wpcc.io/lib/1.0.2/cookieconsent.min.js"></script><script>window.addEventListener("load", function(){window.wpcc.init({"corners":"normal","colors":{"popup":{"background":"#222222","text":"#ffffff","border":"#b3d0e4"},"button":{"background":"#b3d0e4","text":"#000000"}},"padding":"small","margin":"large","transparency":"20","fontsize":"small","position":"bottom-right","content":{"message":"Questo sito web fa uso di cookie tecnici per garantire il corretto funzionamento delle funzionalità erogate. Cliccando sul bottone o proseguendo nella navigazione accetti il loro utilizzo.","button":"Capito!","link":"Scopri di più"}})});</script>
  <head>
  
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
<link rel="icon" href="images/newlogo.png">
<style>
img {
   /* This is where magic happens */
  max-width: 100%;
  max-height: 100%;
  width: auto;

  display: block;
  margin: 0 auto;
}
.bg-primary {
 background-color: #343A40 !important;
}
.carousel.slide.carousel-fade {
	border-radius:8px;
}

#first.carousel-caption {
   position: absolute;
   top:60px;
   left:-140px
}
#second.carousel-caption {
   position: absolute;
   top:60px;
   left:170px
}
#third.carousel-caption {
   position: absolute;
   top:30px;
   left:-200px
}
.col-md-2 { 
     background-color: #ffffff;  
}
.space { 
	margin:0; 
	padding:0; 
	height:70px; 
}
.link {
   color:black;
   text-decoration: none; 
   background-color: none;
}
.logout {
    background-color:transparent;
    border:none;    
}
.price-wrap.h5 {
	font-size: 105%;
}
.ribbon {
  width: 150px;
  height: 150px;
  overflow: hidden;
  position: absolute;
}
.ribbon::before,
.ribbon::after {
  position: absolute;
  z-index: -1;
  content: '';
  display: block;
  border: none;
}
.ribbon span {
  position: absolute;
  display: block;
  width: 225px;
  padding: 15px 0;
  background-color: #3498db;
  box-shadow: 0 5px 10px rgba(0,0,0,.1);
  color: #fff;
  font: 700 10px/1 'Lato', sans-serif;
  text-shadow: 0 1px 1px rgba(0,0,0,.2);
  text-transform: uppercase;
  text-align: center;
}

/* top left*/
.ribbon-top-left {
  top: -10px;
  left: -10px;
}
.ribbon-top-left::before,
.ribbon-top-left::after {
  border-top-color: transparent;
  border-left-color: transparent;
}
.ribbon-top-left::before {
  top: 0;
  right: 0;
}
.ribbon-top-left::after {
  bottom: 0;
  left: 0;
}
.ribbon-top-left span {
  right: -25px;
  top: 30px;
  transform: rotate(-45deg);
}
/*
.navbar.navbar-expand-lg.navbar-light.bg-light {
overflow: hidden;
 position: fixed; 
  top: 0; 
  width: 100%; 
} */
.main {
margin-top: 60px;
}
#myBtn {
  display: none;
  position: fixed;
  bottom: 20px;
  right: 30px;
  z-index: 99;
  font-size: 18px;
  border: none;
  outline: none;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  padding: 15px;
  border-radius: 4px;
}

#myBtn:hover {
  background-color: #007baa;
}
.col-md-3.col-sm-6 {
  width: 33%;
  height: 530px;
  display: inline-block;
}

.box {
  width: 100%;
  height: 60%;
  display: inline-block;
}

.box > img {
  
  /* This is where magic happens */
  max-width: 100%;
  max-height: 100%;
  width: auto;
  
  display: block;
  margin: 0 auto;
  margin-bottom: 40px;
}
.blink > span{
		animation: blink 1s linear infinite;
	}
@keyframes blink{
0%{opacity: 0;}
50%{opacity: .5;}
100%{opacity: 1;}
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
</style>
<title>EC-18</title>

  </head>
  
  <body>
    <div class="navbar navbar-expand-lg navbar-light bg-light">
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
%>  <!-- 08 -->
  <!--   <a class="p-2 text-dark" href="./login.jsp"> <span class="fas fa-user-circle"></span> Login</a>
  </nav>
  <a class="btn btn-outline-primary" href="./register.html">Registrati</a> -->
</div>
<div class="main">
 <div class="container" id="p">

  <div id="carouselExampleFade" class="carousel slide carousel-fade" data-ride="carousel">
  <div class="carousel-inner" style=" width:100%; height:280px !important; border-radius:8px;">   
    <div class="carousel-item active">
      <img src="images/header2.png" alt="Designed by pikisuperstar / Freepik" class="d-block w-100" alt="header1">
      <div class="carousel-caption d-none d-md-block" id="first">
         <h3>Offerte del giorno</h3>
          <p>20% di sconto su alcuni prodotti!</p>
          <a class="btn btn-primary" href="./ApplyFilter?type=offer&name=offer" role="button">Approfitta ora!</a>
        </div>
    </div>
    <div class="carousel-item">
      <img src="images/header3.png" class="d-block w-100" alt="header2">
      <div class="carousel-caption d-none d-md-block" id="second">
      <h3>Non perderti gli ultimi arrivi!</h3><br>
          <a class="btn btn-outline-light" href="./ApplyFilter?type=last" role="button">SCOPRI SUBITO</a>
        </div>
    </div>
    <div class="carousel-item">
      <img src="images/header1.png" class="d-block w-100" alt="header3">
      <div class="carousel-caption d-none d-md-block" id="third">
          <h4>&Egrave; disponibile la nostra nuova</h4>
          <h4>app Android!</h4><br>
           <a class="btn btn-outline-light" href="https://elasticbeanstalk-us-east-2-832965785648.s3.us-east-2.amazonaws.com/android/ec18.apk" role="button">Scarica l'app</a>
        </div>
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleFade" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleFade" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

</div>

<div class="container" >
	<div class="row justify-content-center mt-5 mr-0">
        <div class="col-12 col-md-10 col-lg-8">
            <form class="card card-sm" action="./ApplyFilter?search=yes">  
                <div class="card-body row no-gutters align-items-center">
                    <div class="col-auto">

                    </div>
                   
                    <!--end of col-->
                    <div class="col">
                        <input class="form-control form-control-borderless src_fild" type="text" name="search" placeholder="Cerca un prodotto">
                    </div>
                    <!--end of col-->
                    <div class="col-auto">
                        <button class="btn btn-default"  type="submit"><span class="fas fa-search"></span></button>
                    </div>
                    
                    <!--end of col-->
                </div>
           </form>  
        </div>
        <!--end of col-->
    </div>
</div>




<br><br>

<div class="container-fluid" >

	<c:if test="${empty param.message}">
    	
    	
    </c:if>
   <center class="text-danger"><br>${message}</center><br><br>
<div class="row">
       
          <div class="col-md-2 " >
            <h4>Filtra per:</h4><br>
            <div id="menu">
                <div class="panel list-group" >
                
                 <a href="#"  class="list-group-item" data-toggle="collapse" data-target="#sm" data-parent="#menu">Categoria <span class="label label-info"></span> <span class="glyphicon glyphicon-envelope pull-right"></span></a>
                 <div id="sm" class="sublinks collapse">
                  <a href="./ApplyFilter?type=category&name=Smartphone" class="list-group-item small" > Smartphone</a>
                  <a href="./ApplyFilter?type=category&name=Notebook" class="list-group-item small" > Notebook</a>
                   <a href="./ApplyFilter?type=category&name=Accessori" class="list-group-item small" > Accessori</a>
                 </div>
                 <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sl" data-parent="#menu">Prezzo <span class="glyphicon glyphicon-tag pull-right"></span></a>
                 <div id="sl" class="sublinks collapse">
                  <a href="./ApplyFilter?type=price&name=0" class="list-group-item small">&euro; 0-100</a>
                  <a href="./ApplyFilter?type=price&name=100" class="list-group-item small">&euro; 100-200</a>
                  <a href="./ApplyFilter?type=price&name=200" class="list-group-item small">&euro; 200-300</a>
                  <a href="./ApplyFilter?type=price&name=300" class="list-group-item small">&euro; 300-400</a>
                  <a href="./ApplyFilter?type=price&name=400" class="list-group-item small">&euro; 400-500</a>
                  <a href="./ApplyFilter?type=price&name=500" class="list-group-item small">&euro; 500-600</a>
                  <a href="./ApplyFilter?type=price&name=600" class="list-group-item small">&euro; 600-700</a>
                  <a href="./ApplyFilter?type=price&name=700" class="list-group-item small">&euro; 700-800</a>
                  <a href="./ApplyFilter?type=price&name=800" class="list-group-item small">&euro; 800-900</a>
                  <a href="./ApplyFilter?type=price&name=900" class="list-group-item small">&euro; 900+</a>
                 </div>
                 <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sb" data-parent="#menu">Brand<span class="glyphicon glyphicon-tag pull-right"></span></a>
                 <div id="sb" class="sublinks collapse">
                 <c:forEach items="${brandList}" var="k" varStatus="curr">
                 
                  <a href="./ApplyFilter?type=brand&name=${k}" class="list-group-item small">${k}</a>
                  </c:forEach>
                  
                   </div>
                     <a href="./ApplyFilter?type=last" class="list-group-item" >Ultimi arrivi <span class="glyphicon glyphicon-tag pull-right"></span></a>
                     <a href="./ApplyFilter?type=best" class="list-group-item" >Più venduti <span class="glyphicon glyphicon-tag pull-right"></span></a>
                   
                </div>
                </div>
              
          </div>
           
    
    <c:forEach items="${itemList}" var="i" varStatus="curr">
 
    <!-- BEGIN PRODUCTS -->
    <c:if test="${curr.index ne 0}">
      <c:if test="${curr.index mod 3 eq 0}">  
    <!--    <% out.print("<div class=\"space\"></div>"); %> -->
        <% out.print("<div class=\"col-md-2 \"></div>"); %> 
	  </c:if> 
	</c:if>  
    <div class="col-md-3 col-sm-6"  >
    
    <c:if test="${i.onSale}">
    	<div class="ribbon ribbon-top-left"><span>offerta del giorno</span></div>
    </c:if>
    
    
      <span class="thumbnail" >
      <div class="box">
          <a href="./ShowProduct?productID=${i.ID}"><img src="${i.path[0]}" alt="..."></a>
          </div>
<a href="./ShowProduct?productID=${i.ID}" style="text-decoration: none">
<div class="link">
          <h4>  ${i.name} </h4>
          

          <div class="ratings">
                  <c:forEach var="j" begin="1" end="${i.rating}">  
  					   <% out.print("<span class=\"fas fa-star\"></span>"); %>
				  </c:forEach>
				  <c:forEach var="k" begin="${i.rating+1}" end="5">  
  					   <% out.print("<span class=\"far fa-star\"></span>"); %>
				  </c:forEach>  
              </div>
          </div>
</a>
          <hr class="line">
          <div class="row">
            <div class="col-md-6 col-sm-6">
              <div class="price-wrap h5">
              			<c:choose>
  					      <c:when test="${i.onSale}">
       				        	<% out.print("<span class=\"price-new\">&euro;");%>
       				        	<c:out value = "${ i.price - (  i.price div 100  * 20)}0"/>
       				        	<% out.print("</span> <del class=\"price-old\">&euro;"); %>
       				        	<c:out value = "${i.price}0"/>
       				        	<% out.print("</del>"); %>   
    					  </c:when>    
   					 	  <c:otherwise>
        				    	<% out.print("<span class=\"price-new\">&euro;");%>
        				    	<c:out value = "${i.price}0"/>
        				    	<% out.print("</span>"); %> 
    					  </c:otherwise>
						</c:choose>
        		
        				
        			</div> <!-- price-wrap.// -->
            </div>
            <div class="col-md-6 col-sm-6">
            
             <a href="./ShowProduct?productID=${i.ID}"  >	<button class="btn btn-sm btn-primary float-right" >Dettagli</button></a>
            
            </div>
              <div class="space"></div>
          </div>
      </span>
    </div>


  <!-- END PRODUCTS -->
    </c:forEach>
    
</div>    <!--  end of row -->

</div><!-------container----->


<br><br><br><br><br>
<button onclick="topFunction()" id="myBtn" title="Torna in cima">&nbsp;&nbsp;&#8593;&nbsp;&nbsp;</button>
<footer id="sticky-footer" class="py-4 bg-dark text-white-50">
    <div class="container-fluid bg-primary py-3">
        <div class="container">
          <div class="row py-3">
            <div class="col-md-9">
              <p class="text-white">Copyright &copy; ingsw2019</p>
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


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    
    <script>
      $(document).ready(function() {
    $('[id^=detail-]').hide();
    $('.toggle').click(function() {
        $input = $( this );
        $target = $('#'+$input.attr('data-toggle'));
        $target.slideToggle();
        if($input.find('.col-xs-2 i').attr('class')=="fa fa-chevron-down pull-right")
        {
             $input.find('.col-xs-2 i').removeClass('fa-chevron-down'); 
             $input.find('.col-xs-2 i').addClass('fa-chevron-up');
        }
        else if($input.find('.col-xs-2 i').attr('class')=="fa pull-right fa-chevron-up")
        {
             $input.find('.col-xs-2 i').removeClass('fa-chevron-up'); 
             $input.find('.col-xs-2 i').addClass('fa-chevron-down');     
        }
         else if($input.find('.col-xs-2 i').attr('class')=="fa pull-right fa-chevron-down")
        {
             $input.find('.col-xs-2 i').removeClass('fa-chevron-down'); 
             $input.find('.col-xs-2 i').addClass('fa-chevron-up');
        }
       
    });
});
      $('.carousel').carousel({
    	  interval: 5000
    	})
  // When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    document.getElementById("myBtn").style.display = "block";
  } else {
    document.getElementById("myBtn").style.display = "none";
  }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
}
    </script>
    </div>
  </body>
</html>
