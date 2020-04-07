<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="com.ingsw.model.Cart" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Recupero Password</title>
    <link rel="stylesheet" href="/css/style.css" >
     <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
<link rel="icon" href="images/newlogo.png">
<style>
@media (min-width:767px){
		.registration{
			max-width: 400px;
		}
	}
.bg-primary {
 background-color: #343A40 !important;
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
.blink > span{
		animation: blink 1s linear infinite;
	}
@keyframes blink{
0%{opacity: 0;}
50%{opacity: .5;}
100%{opacity: 1;}
}
</style>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.min.js"></script>
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
    <a class="p-2 text-dark" href="./login.jsp"> <span class="fas fa-user-circle"></span> Login</a>
  </nav>
  <a class="btn btn-outline-primary" href="./register.html">Registrati</a>
</nav>
 
<br><br>
<div class="container">
	<div class="registration mx-auto d-block w-100">
		<div class="page-header text-center">
			<h1>Recupero Password</h1>
		</div>
		
		<form id="resetForm" action="forgotPassword" method="post" class="form-validate form-horizontal well">
				<br><br><br>
				<div class="form-group">
					<label for="exampleInputPassword1">Inserisci l'email per il recupero:</label>
					<input type="text" class="form-control" id="email" name="email">
				</div>
				<div class="d-flex justify-content-between align-items-center">
                        <div class="form-check form-group d-flex justify-content-end">
                                <a href="./index.jsp">Annulla</a>
                            </div>
					<div class="form-group d-flex justify-content-start">
						<input type="submit" class="btn btn-primary"
						 value="Continua"></button>
					</div>
					
				</div>
			</form>
	</div>
</div>


<br><br><br><br><br>
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
    
     
     
<script type="text/javascript">
 
    $(document).ready(function() {
        $("#resetForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                }      
            },
             
            messages: {
                email: {
                    required: "Il campo è obbligatorio!",
                    email: "Inserisci un indirizzo email valido!"
                }
            }
        });
 
    });
</script>
</body>
</html>