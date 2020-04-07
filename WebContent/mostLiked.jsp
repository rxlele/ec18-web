<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.io.*,java.lang.*,java.util.*,java.net.*,java.util.*,java.text.*"%>
<%@ page import="javax.activation.*,javax.mail.*,org.apache.commons.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ingsw.model.Cart" %>
<%@ page import="com.ingsw.model.Item" %>



<!doctype html>
<html lang="en">
  	<head>
   		<!-- Required meta tags -->
    	<meta charset="UTF-8">
   		 <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
   		 <!-- jQuery first, then Popper.js, then Bootstrap JS -->
   		 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
   		 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
 				<!-- Bootstrap CSS -->
    
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">

    
		<title>Più piaciuti</title>
		<link rel="icon" href="images/newlogo.png">
		<style>
		.bg-primary {
			background-color: #343A40 !important;
		}
		.logout {
			background-color:transparent;
			border:none;    
		}
		.main {
margin-top: 60px;
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
			if(session.getAttribute("user") == null || !session.getAttribute("user").equals("admin@ec18")){
				%> <jsp:forward page="./index"/> <%
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
		<br>
	<br>	
	
	<%
	List<Item> itemList=new ArrayList<Item>();
	if (request.getAttribute("itemList")!=null){
		itemList=(List<Item>) request.getAttribute("itemList");
	}
	%>
    <h2 align="center" >Prodotti più piaciuti</h2><br><br>
	
	<div class="container-fluid">
  <div class="row">
    <div class="col	">
    
            <div class="col-7">
            
            	<div id="menu">
            	
                <div class="panel list-group" >
                
                 <a href="./ShowBestSellers"  class="list-group-item" >Vendite <span class="label label-info"></span> <span class="glyphicon glyphicon-envelope pull-right"></span></a>
                 <a href="./ShowReceipts" class="list-group-item" >Incassi <span class="glyphicon glyphicon-tag pull-right"></span></a>
                <a href="./ShowMostClicked" class="list-group-item" >Più cliccati <span class="glyphicon glyphicon-tag pull-right"></span></a>
                 
                 <a href="#" class="list-group-item" >Feedback<span class="glyphicon glyphicon-tag pull-right"></span></a>
      
                </div>
                </div>
            </div>
            
    </div>
    
    <div class="col-6">
       <div>
    <canvas id="myChart" height="150" ></canvas>
  </div>
    </div>
    <div class="col">
      <p><b>Totale feedback:</b> <%= request.getAttribute("total") %> </p><br><br>
      <p><b>Prodotto più piaciuto:</b>  ${ itemList[0].name } </p><br><br>
      <c:set var="size" value="${fn:length(itemList)}" />
       <p><b>Prodotto meno piaciuto:</b>  ${ itemList[size-1].name }</p>
       
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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script>
function getRandomColor() {
	  var letters = '0123456789ABCDEF';
	  var color = '#';
	  for (var i = 0; i < 6; i++) {
	    color += letters[Math.floor(Math.random() * 16)];
	  }
	  return color;
	}
var ctx = document.getElementById("myChart").getContext('2d');
var array=[];
var colors=[];
var data=[];
<c:forEach items="${feedbackList}" var="id">
array.push("${id}"); 
colors.push(getRandomColor());
</c:forEach>
var myChart = new Chart(ctx, {
	type: 'pie',
  data: {
    labels: ["\u2605\u2605\u2605\u2605\u2605" ,"\u2605\u2605\u2605\u2605","\u2605\u2605\u2605","\u2605\u2605","\u2605"],
    datasets: [{
      backgroundColor: colors,
      data: array
    }]
  }
});
</script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
   
  </body>
</html>
