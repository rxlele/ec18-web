<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="com.ingsw.model.User" %>
<%@ page import="com.ingsw.model.Cart" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
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
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  

<title>Profilo</title>
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


.blink > span{
		animation: blink 1s linear infinite;
	}
@keyframes blink{
0%{opacity: 0;}
50%{opacity: .5;}
100%{opacity: 1;}
}
.table tr {
    cursor: pointer;
}
.hiddenRow {
    padding: 0 4px !important;
    background-color: #eeeeee;
    font-size: 13px;
}
.rating {
      float:left;
    }

    /* :not(:checked) is a filter, so that browsers that don’t support :checked don’t 
      follow these rules. Every browser that supports :checked also supports :not(), so
      it doesn’t make the test unnecessarily selective */
    .rating:not(:checked) > input {
        position:absolute;
        top:-9999px;
        clip:rect(0,0,0,0);
    }

    .rating:not(:checked) > label {
        float:right;
        width:1em;
        /* padding:0 .1em; */
        overflow:hidden;
        white-space:nowrap;
        cursor:pointer;
        font-size:300%;
        /* line-height:1.2; */
        color:#ddd;
    }

    .rating:not(:checked) > label:before {
        content:"\2605";
    }

    .rating > input:checked ~ label {
        color: dodgerblue;
        
    }

    .rating:not(:checked) > label:hover,
    .rating:not(:checked) > label:hover ~ label {
        color: dodgerblue;
        
    }

    .rating > input:checked + label:hover,
    .rating > input:checked + label:hover ~ label,
    .rating > input:checked ~ label:hover,
    .rating > input:checked ~ label:hover ~ label,
    .rating > label:hover ~ input:checked ~ label {
        color: dodgerblue;
        
    }

    .rating > label:active {
        position:relative;
        top:2px;
        left:2px;
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

<h3 style="color:red;" align="center">${messageOrder}</h3>

<section id="tabs" class="project-tab">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                               <c:if test="${buttonPressed}">
                                <a class="nav-item nav-link" style="width:33%;" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Dati Utente</a>
    							</c:if>
    							<c:if test="${buttonPressed==false || buttonPressed==null}">
                                <a class="nav-item nav-link active" style="width:33%;" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Dati Utente</a>
    							</c:if>
    							<%
                                if (!userName.equals("admin@ec18"))
        							out.print("<a class=\"nav-item nav-link\" style=\"width:33%;\" id=\"nav-contact-tab\" data-toggle=\"tab\" href=\"#nav-contact\" role=\"tab\" aria-controls=\"nav-contact\" aria-selected=\"false\">Ordini Effettuati</a>");
                      			%>
                                <c:if test="${buttonPressed}">
                                <%
                                if (userName.equals("admin@ec18"))
                                out.print("<a class=\"nav-item nav-link active\"  style=\"width:33%;\" id=\"nav-contact-tab\" data-toggle=\"tab\" href=\"#nav-tools\" role=\"tab\" aria-controls=\"nav-tools\" aria-selected=\"false\">Strumenti di Amministrazione</a>");
                      			%>
                      			</c:if>
    							<c:if test="${buttonPressed==false || buttonPressed==null}">
                                <%
                                if (userName.equals("admin@ec18"))
                                out.print("<a class=\"nav-item nav-link\" style=\"width:33%;\" id=\"nav-contact-tab\" data-toggle=\"tab\" href=\"#nav-tools\" role=\"tab\" aria-controls=\"nav-tools\" aria-selected=\"false\">Strumenti di Amministrazione</a>");
                      			%>
                                </c:if>
                                </div>
                        </nav>
                        <div class="tab-content" id="nav-tabContent">
                        <c:if test="${buttonPressed}">
                                <div class="tab-pane fade show" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
    							</c:if>
    							<c:if test="${buttonPressed==false || buttonPressed==null}">
                                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
    							</c:if>
                            
		<br>
			
		<!-- Dati Utente -->	

<form action="./EditUser">
<c:if test="${edit==false}">
 <%  out.print("<fieldset disabled>"); %>
 </c:if>
 <c:if test="${edit==true}">
 <%  out.print("<fieldset>"); %>
 </c:if>

  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Nome</label>
      <input type="text" class="form-control" id="inputName4" name="firstname" value="${user.firstName}">
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Cognome</label>
      <input type="text" class="form-control" id="inputSurname4" name="lastname" value="${user.lastName}">
    </div>
  </div>
  <!-- DA MOSTRARE SOLO PER L'UTENTE NORMALE -->
  <c:if test="${user.email ne 'admin@ec18'}">
  	<div class="form-row">
    <div class="form-group col">
      <label for="inputAddress">Indirizzo</label>
      <input type="text" class="form-control" id="inputAddress" name="address" value="${user.address}">
    </div>
    <div class="form-group col">
      <label for="inputCity">Citt&agrave;</label>
      <input type="text" class="form-control" id="inputCity" name="city" value="${user.city}">
    </div>
    <div class="form-group col">
      <label for="inputProvince">Provincia</label>
      <select name="province" required class="custom-select" id="provinceSelection" >
      				  <option value="${user.province}"  selected > ${user.province} </option>
					<option value="ag">Agrigento</option>
	<option value="AL">Alessandria</option>
	<option value="AN">Ancona</option>
	<option value="AO">Aosta</option>
	<option value="AR">Arezzo</option>
	<option value="AP">Ascoli Piceno</option>
	<option value="AT">Asti</option>
	<option value="AV">Avellino</option>
	<option value="BA">Bari</option>
	<option value="BT">Barletta-Andria-Trani</option>
	<option value="BL">Belluno</option>
	<option value="BN">Benevento</option>
	<option value="BG">Bergamo</option>
	<option value="BI">Biella</option>
	<option value="BO">Bologna</option>
	<option value="BZ">Bolzano</option>
	<option value="BS">Brescia</option>
	<option value="BR">Brindisi</option>
	<option value="CA">Cagliari</option>
	<option value="CL">Caltanissetta</option>
	<option value="CB">Campobasso</option>
	<option value="CI">Carbonia-Iglesias</option>
	<option value="CE">Caserta</option>
	<option value="CT">Catania</option>
	<option value="CZ">Catanzaro</option>
	<option value="CH">Chieti</option>
	<option value="CO">Como</option>
	<option value="CS">Cosenza</option>
	<option value="CR">Cremona</option>
	<option value="KR">Crotone</option>
	<option value="CN">Cuneo</option>
	<option value="EN">Enna</option>
	<option value="FM">Fermo</option>
	<option value="FE">Ferrara</option>
	<option value="FI">Firenze</option>
	<option value="FG">Foggia</option>
	<option value="FC">Forl&igrave;-Cesena</option>
	<option value="FR">Frosinone</option>
	<option value="GE">Genova</option>
	<option value="GO">Gorizia</option>
	<option value="GR">Grosseto</option>
	<option value="IM">Imperia</option>
	<option value="IS">Isernia</option>
	<option value="SP">La spezia</option>
	<option value="AQ">L'aquila</option>
	<option value="LT">Latina</option>
	<option value="LE">Lecce</option>
	<option value="LC">Lecco</option>
	<option value="LI">Livorno</option>
	<option value="LO">Lodi</option>
	<option value="LU">Lucca</option>
	<option value="MC">Macerata</option>
	<option value="MN">Mantova</option>
	<option value="MS">Massa-Carrara</option>
	<option value="MT">Matera</option>
	<option value="ME">Messina</option>
	<option value="MI">Milano</option>
	<option value="MO">Modena</option>
	<option value="MB">Monza e della Brianza</option>
	<option value="NA">Napoli</option>
	<option value="NO">Novara</option>
	<option value="NU">Nuoro</option>
	<option value="OG">Ogliastro</option>
	<option value="OT">Olbia-Tempio</option>
	<option value="OR">Oristano</option>
	<option value="PD">Padova</option>
	<option value="PA">Palermo</option>
	<option value="PR">Parma</option>
	<option value="PV">Pavia</option>
	<option value="PG">Perugia</option>
	<option value="PU">Pesaro e Urbino</option>
	<option value="PE">Pescara</option>
	<option value="PC">Piacenza</option>
	<option value="PI">Pisa</option>
	<option value="PT">Pistoia</option>
	<option value="PN">Pordenone</option>
	<option value="PZ">Potenza</option>
	<option value="PO">Prato</option>
	<option value="RG">Ragusa</option>
	<option value="RA">Ravenna</option>
	<option value="RC">Reggio Calabria</option>
	<option value="RE">Reggio nell'Emilia</option>
	<option value="RI">Rieti</option>
	<option value="RN">Rimini</option>
	<option value="RM">Roma</option>
	<option value="RO">Rovigo</option>
	<option value="SA">Salerno</option>
	<option value="SS">Sassari</option>
	<option value="SV">Savona</option>
	<option value="SI">Siena</option>
	<option value="SR">Siracusa</option>
	<option value="SO">Sondrio</option>
	<option value="TA">Taranto</option>
	<option value="TE">Teramo</option>
	<option value="TR">Terni</option>
	<option value="TO">Torino</option>
	<option value="TP">Trapani</option>
	<option value="TN">Trento</option>
	<option value="TV">Treviso</option>
	<option value="TS">Trieste</option>
	<option value="UD">Udine</option>
	<option value="VA">Varese</option>
	<option value="VE">Venezia</option>
	<option value="VB">Verbano-Cusio-Ossola</option>
	<option value="VC">Vercelli</option>
	<option value="VR">Verona</option>
	<option value="VV">Vibo valentia</option>
	<option value="VI">Vicenza</option>
	<option value="VT">Viterbo</option>
    			    </select>
    </div>
  </div>
  </c:if>
  
  
  <div class="form-group">
    <label for="inputAddress2">Mail</label>
    <input type="email" class="form-control" id="inputEmail" disabled value="${user.email}">
  </div>
  
  <div class="form-group">

  </div>
  </fieldset>
  <c:if test="${edit==false}">
<c:if test="${user.email ne 'admin@ec18'}">
 <%  out.print("<button type=\"submit\" class=\"btn btn-primary\" name=\"modifica\">Modifica</button>"); %>
</c:if>
 </c:if>
 <c:if test="${edit==true}">
 <%  out.print("<button type=\"submit\" class=\"btn btn-primary\" name=\"salva\">Salva</button>"); %>
 </c:if>
</form>
<br><br>
<c:if test="${edit}">
<% 
	out.print("<form action= \"./changePassword.jsp\"><button type=\"submit\" class=\"btn btn-primary\">Modifica Password</button></form>");
 %>
</c:if>


                            </div>

                                 <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
                           
                               
                                    
                                    
                                    
                                   <table class="table table-condensed" style="border-collapse:collapse;">
    <thead>
       <tr>
                                            <th>NUMERO ORDINE</th>
                                            <th>EFFETTUATO IL</th>
                                            <th>TOTALE</th>
                                        </tr>
    </thead>
    <tbody>
       
		
       
   
                                      <c:forEach items="${orders}" var="i" varStatus="curr">
                                       <tr data-toggle="collapse" data-target="#demo${curr.index }" class="accordion-toggle">
                                         <td><a href="#">Ordine n&#176; ${i.orderN}</a></td>
                                            <td>${i.date}</td>
                                            <td>&euro;${i.totalPrice}0</td>
                                        </tr>   
                                       
        <tr >
            <td colspan="6" class="hiddenRow"><div class="accordian-body collapse" id="demo${curr.index }"> 
            
            
            
             <table class="table table-striped">
                    <thead>
                       
                    </thead>
                    <tbody>
                    <c:forEach items="${i.products}" var="j" varStatus="curr2">
                        <tr>

                            <td id="productN">${j.name} (${i.colors[curr2.index]}) </td>
                            <c:if test="${i.shipping[curr2.index] == 0}">
                            <td>Standard (4/5 gg) Gratis</td>
                            </c:if>
                            <c:if test="${i.shipping[curr2.index] == 1}">
                            <td>Espressa (48/72 ore) &euro; 4.00</td>
                            </c:if>
                            <c:if test="${i.shipping[curr2.index] == 2}">
                            <td>Espressa (24 ore) &euro; 8.00</td>
                            </c:if>
                            <td>&nbsp;</td>

                          <c:choose>
  					      <c:when test="${j.onSale}">
  					     	  <td class="text-right">&euro; ${ j.price - (  j.price div 100  * 20)}0</td>
  					     	
       				        	
       				        	
    					  </c:when>    
   					 	  <c:otherwise>
        				    	
        				    	 <td class="text-right">&euro; ${j.price}0</td>
    					  </c:otherwise>
						</c:choose>
                            <td class="text-right">
                             <c:if test="${i.feedback[curr2.index] eq 0}">
                             
                             	<td><a data-toggle="modal" data-target="#exampleModal" style="color:blue;" onclick="setName('${j.name}','${curr2.index}','${i.orderN}');">Lascia feedback</a></td>
                             </c:if>
                             <c:if test="${i.feedback[curr2.index] ne 0}">
                             
                             	<td>Feedback lasciato</td>
                             </c:if>
                             </td>
                        </tr>
                     </c:forEach>  
                        
                       
                       
                    </tbody>
                </table>
            
             </div> </td>
        </tr>
        
        
 										
                                        </c:forEach>
                                      </tbody>
</table>
                                   
                            </div>
                          <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Lascia feedback</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form id="feedbackForm" action="AddFeedback" onsubmit="saveButton.disabled=true; return true;" method="POST" >
      <div class="modal-body">
        <h3 id="feedbackName" ></h3>  
        <input type="text" hidden="true" name="feedbackIndex" id="feedbackIndex">
        <input type="text" hidden="true" name="orderNumber" id="orderNumber">
       <div class="container">
	<div class="row">
	<div class="rating">
      <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="5 stelle"></label>
      <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="4 stelle"></label>
      <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="3 stelle"></label>
      <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="2 stelle"></label>
      <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="1 stella"></label>
    </div>
	</div>
</div>
      </div>
      <div class="modal-footer">
        <button type="button" id="saveButton" class="btn btn-primary" onclick="checkValidation();">Salva</button>
      </div>
      </form>
    </div>
  </div>
</div>
                                     <c:if test="${buttonPressed}">
                            <div class="tab-pane fade show active" id="nav-tools" role="tabpanel" aria-labelledby="nav-tools-tab">
                            <div class="container-fluid home-content1">
			<div class="row">
				<div class="col-md-6 content1-left">
					<h3>Gestione Prodotti </h3>
					<p>Qui è possibile aggiungere, modificare o eliminare tutti i prodotti presenti nel tuo negozio...</p>
					<br>
					<!-- <div class="content1-left"></div> -->
				</div>
				<div class="col-md-6 content1-left">
					<a href="./ShowAddItem"><button type="button" class="btn btn-primary " name="addproduct" align="center" style="width:100%;">Aggiungi prodotto</button><br><br></a>
					<a href="./index"><button type="button" class="btn btn-primary " align="center" style="width:100%;">Modifica prodotti</button></a>
					<br><br>
				<a href="./ShowProfile"> <button type="button" align="center" style="width:100%;" class="btn btn-outline-primary" >Indietro</button></a>
				</div>
			</div>
		</div>
 
                            </div>
    							</c:if>
    							<c:if test="${buttonPressed==false || buttonPressed==null}">
                                 <div class="tab-pane fade show" id="nav-tools" role="tabpanel" aria-labelledby="nav-tools-tab">
                                 <div class="container-fluid home-content1">
		<div class="row">
			<div class="col-md-6 content1-left">
				<h3>Gestione Prodotti </h3>
				<p>Qui è possibile aggiungere, modificare o eliminare tutti i prodotti presenti nel tuo negozio...</p>
				<br>
				<a href="./ManageProduct"><button type="button" class="btn btn-outline-primary " align="center" style="width:100%;">Vai ></button></a>
				<!-- <div class="content1-left"></div> -->
			</div>
			<div class="col-md-6 content1-right">
				<h3>Statistiche</h3>
				<p>Qui è possibile visualizzare tutte le statistiche relative a: vendite, incassi, prodotti più cliccati e sui FeedBack rilasciati dai tuoi clienti...</p>
				<a href="./ShowBestSellers"><button type="button" class="btn btn-outline-primary " align="center" style="width:100%;">Vai ></button></a>
			</div>
		</div>
	</div>
 
                            </div>
    							</c:if>
 	
                        </div>
                    </div>
                </div>
            </div>
        </section>


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
$('.accordian-body').on('show.bs.collapse', function () {
    $(this).closest("table")
        .find(".collapse.in")
        .not(this)
        .collapse('toggle')
});

function setName(name,index,orderN){
	var nome=document.getElementById('feedbackName');
	nome.innerText = name;
	var feedbackIndex=document.getElementById('feedbackIndex');
	feedbackIndex.value=index;
	var orderNumber=document.getElementById('orderNumber');
	orderNumber.value=orderN;
	$('#exampleModal').modal();
} 
function checkValidation(){
	if (document.getElementById('star5').checked || document.getElementById('star4').checked || document.getElementById('star3').checked
			|| document.getElementById('star2').checked || document.getElementById('star1').checked)
		document.forms['feedbackForm'].submit();
}
</script>

    <!-- Optional JavaScript -->
    </body>
</html>
