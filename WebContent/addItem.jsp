<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="java.io.*,java.lang.*,java.util.*,java.net.*,java.util.*,java.text.*"%>
<%@ page import="javax.activation.*,javax.mail.*,org.apache.commons.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ingsw.model.Cart" %>


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

    
		<title>Aggiungi prodotto</title>
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
		.hide-text{font:0/0 a;color:transparent;text-shadow:none;background-color:transparent;border:0;}
		.input-block-level{display:block;width:100%;min-height:30px;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;}
		.btn-file{overflow:hidden;position:relative;vertical-align:middle;}.btn-file>input{position:absolute;top:0;right:0;margin:0;opacity:0;filter:alpha(opacity=0);transform:translate(-300px, 0) scale(4);font-size:23px;direction:ltr;cursor:pointer;}
		.fileupload{margin-bottom:9px;}.fileupload .uneditable-input{display:inline-block;margin-bottom:0px;vertical-align:middle;cursor:text;}
		.fileupload .thumbnail{overflow:hidden;display:inline-block;margin-bottom:5px;vertical-align:middle;text-align:center;}.fileupload .thumbnail>img{display:inline-block;vertical-align:middle;max-height:100%;}
		.fileupload .btn{vertical-align:middle;}
		.fileupload-exists .fileupload-new,.fileupload-new .fileupload-exists{display:none;}
		.fileupload-inline .fileupload-controls{display:inline;}
		.fileupload-new .input-append .btn-file{-webkit-border-radius:0 3px 3px 0;-moz-border-radius:0 3px 3px 0;border-radius:0 3px 3px 0;}
		.thumbnail-borderless .thumbnail{border:none;padding:0;-webkit-border-radius:0;-moz-border-radius:0;border-radius:0;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;}
		.fileupload-new.thumbnail-borderless .thumbnail{border:1px solid #ddd;}
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
		<!-- BODY -->
 		<h2 align="center">Aggiungi prodotto</h2>
 		<div class="space"></div>
		<h5 align="center" class="text-danger"> ${message} </h5>
		<form action="AddProduct" method="post" enctype="multipart/form-data" onsubmit="button1id.disabled=true; addPhoto.disabled=true; return true;">
			<div class="container" align="center">
 				<div class="row">
    				<div class="col">
     					<div class="form-horizontal">
 							<div class="form-group">
  								<div class="col-md-8">
  									<input name="productID" class="form-control input-md" id="textinput" type="text" placeholder="ID prodotto: ${id}" disabled>
  								</div>	
							</div>
							<div class="form-group">
								<div class="col-md-8">
									<input name="productName" class="form-control input-md" id="textinput" type="text" placeholder="Nome prodotto" required>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-8">
									<input name="price" class="form-control input-md" id="textinput" pattern="^[0-9.,]+$" type="text" placeholder="Prezzo (&euro;)" required>
								</div>
							</div>
							<div class="form-group">
  								<div class="col-md-8">
     								<select required name="category" class="custom-select" >
											<option value="" disabled selected hidden>Categoria</option>
											<option value="Smartphone">Smartphone</option>
											<option value="Notebook">Notebook</option>
											<option value="Accessori">Accessori</option>
									</select>
  								</div>
							</div>
							<div class="form-group">
  								<div class="col-md-8">
   									<div class="custom-control custom-checkbox">
  										<input type="checkbox" name="onSale" class="custom-control-input" id="customCheck1">
  										<label class="custom-control-label" for="customCheck1">In offerta</label>
									</div>
  								</div>
							</div>
						</div>
    				</div>
    				<div class="col">
     					<div class="form-horizontal">
							<div class="form-group">
  								<div class="col-md-8">
  									<input name="color1" class="form-control input-md" id="textinput" type="text" placeholder="Colore principale" required>
  								</div>
							</div>
							<div class="form-group">
  								<div class="col-md-8">
  									<input name="color2" class="form-control input-md" id="textinput" type="text" placeholder="Colore #2 (opzionale)">
  								</div> 
							</div>
							<div class="form-group">
  								<div class="col-md-8">
  									<input name="color3" class="form-control input-md" id="textinput" type="text" placeholder="Colore #3 (opzionale)">
  								</div>
							</div>
							<div class="form-group">
  								<div class="col-md-8">
  									<input name="color4" class="form-control input-md" id="textinput" type="text" placeholder="Colore #4 (opzionale)">
  								</div>
							</div>
						</div>
    				</div>
    				<div class="col">
    					<div class="form-horizontal">
							<div class="form-group">
  								<div class="col-md-8">
  									<input name="tag1" class="form-control input-md" id="textinput" type="text" placeholder="Brand (Consigliato)">
  								</div>
							</div>
							<div class="form-group">
  								<div class="col-md-8">
  									<input name="tag2" class="form-control input-md" id="textinput" type="text" placeholder="Tag #2 (opzionale)">
  								</div>
							</div>
							<div class="form-group">
  								<div class="col-md-8">
  									<input name="tag3" class="form-control input-md" id="textinput" type="text" placeholder="Tag #3 (opzionale)">
  								</div>
							</div>
							<div class="form-group">
  								<div class="col-md-8">
  									<input name="tag4" class="form-control input-md" id="textinput" type="text" placeholder="Tag #4 (opzionale)">
  								</div>
							</div>
						</div>
    				</div>
  				</div>
				<h3><label for="exampleFormControlTextarea1">Descrizione</label></h3>
    			<textarea name="description" class="form-control" id="exampleFormControlTextarea1" rows="9" ></textarea>
    			<div class="form-group" >
    				<div class="space"></div>
   					<div class="container">
  						<div class="row">
    						<div class="col">
            					<button type="button" id="addPhoto"class="btn btn-outline-primary" data-toggle="modal" data-target="#exampleModal">Carica foto</button>
    						</div>
    						<!-- Modal -->
							<div class="modal fade bd-example-modal-sm" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Carica foto</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<!-- Foto 1 -->
											<div class="fileupload fileupload-new" data-provides="fileupload">
												<span class="btn btn-outline-primary btn-file">
													<span class="fileupload-new">Foto 1</span>
													<span class="fileupload-exists">Cambia</span>         
													<input name="file1" id="file1" type="file" />
													<input type="text" name="fileName1" id="fileName1" hidden="true"/>
												</span>
												<span class="fileupload-preview"></span>
												<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>
											</div>
											<!-- Foto 2 -->
											<div class="fileupload fileupload-new" data-provides="fileupload">
												<span class="btn btn-outline-primary btn-file">
													<span class="fileupload-new">Foto 2</span>
													<span class="fileupload-exists">Cambia</span>         
													<input name="file2" type="file" />
													<input type="text" name="fileName2" id="fileName2" hidden="true"/>
												</span>
												<span class="fileupload-preview"></span>
												<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>
											</div>
											<!-- Foto 3 -->
											<div class="fileupload fileupload-new" data-provides="fileupload">
												<span class="btn btn-outline-primary btn-file">
													<span class="fileupload-new">Foto 3</span>
													<span class="fileupload-exists">Cambia</span>         
													<input name="file3" type="file" />
													<input type="text" name="fileName3" id="fileName3" hidden="true"/>
												</span>
												<span class="fileupload-preview"></span>
												<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>
											</div>
											<!-- Foto 4 -->
											<div class="fileupload fileupload-new" data-provides="fileupload">
												<span class="btn btn-outline-primary btn-file">
													<span class="fileupload-new">Foto 4</span>
													<span class="fileupload-exists">Cambia</span>         
													<input name="file4" type="file" />
													<input type="text" name="fileName4" id="fileName4" hidden="true"/>
												</span>
												<span class="fileupload-preview"></span>
												<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>
											</div>
											<!-- Foto 5 -->
											<div class="fileupload fileupload-new" data-provides="fileupload">
												<span class="btn btn-outline-primary btn-file">
													<span class="fileupload-new">Foto 5</span>
													<span class="fileupload-exists">Cambia</span>         
													<input name="file5" type="file" />
													<input type="text" name="fileName5" id="fileName5" hidden="true"/>
												</span>
												<span class="fileupload-preview"></span>
												<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>
											</div>
											<!-- Foto 6 -->
											<div class="fileupload fileupload-new" data-provides="fileupload">
												<span class="btn btn-outline-primary btn-file">
													<span class="fileupload-new">Foto 6</span>
													<span class="fileupload-exists">Cambia</span>         
													<input name="file6" type="file" />
													<input type="text" name="fileName6" id="fileName6" hidden="true"/>
												</span>
												<span class="fileupload-preview"></span>
												<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
										</div>
									</div>
								</div>
							</div>
    						<div class="col"></div>
    						<div class="col">   <!--???-->
     							<button type="submit" name="button1id" class="btn btn-primary"  id="button1id">Aggiungi prodotto</button>
   							</div>
 	 					</div>
					</div>
					<br> 
				</div>
			</div>
    	</form>
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

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
   
    <script charset="utf-8">
    $(document).ready(function() {

    	  $('.color-choose input').on('click', function() {
    	      var headphonesColor = $(this).attr('data-image');

    	      $('.active').removeClass('active');
    	      $('.left-column img[data-image = ' + headphonesColor + ']').addClass('active');
    	      $(this).addClass('active');
    	  });

    	});
    
      var file = undefined;
! function(e) {
    var t = function(t, n) {
        this.$element = e(t), this.type = this.$element.data("uploadtype") || (this.$element.find(".thumbnail").length > 0 ? "image" : "file"), this.$input = this.$element.find(":file");
        if (this.$input.length === 0) return;
        this.name = this.$input.attr("name") || n.name, this.$hidden = this.$element.find('input[type=hidden][name="' + this.name + '"]'), this.$hidden.length === 0 && (this.$hidden = e('<input type="hidden" />'), this.$element.prepend(this.$hidden)), this.$preview = this.$element.find(".fileupload-preview");
        var r = this.$preview.css("height");
        this.$preview.css("display") != "inline" && r != "0px" && r != "none" && this.$preview.css("line-height", r), this.original = {
            exists: this.$element.hasClass("fileupload-exists"),
            preview: this.$preview.html(),
            hiddenVal: this.$hidden.val()
        }, this.$remove = this.$element.find('[data-dismiss="fileupload"]'), this.$element.find('[data-trigger="fileupload"]').on("click.fileupload", e.proxy(this.trigger, this)), this.listen()
    };
    t.prototype = {
        listen: function() {
            this.$input.on("change.fileupload", e.proxy(this.change, this)), e(this.$input[0].form).on("reset.fileupload", e.proxy(this.reset, this)), this.$remove && this.$remove.on("click.fileupload", e.proxy(this.clear, this))
        },
        change: function(e, t) {
            if (t === "clear") return;
            var n = e.target.files !== undefined ? e.target.files[0] : e.target.value ? {
                name: e.target.value.replace(/^.+\\/, ""),
                size: e.target.value.size,
            } : null;
            if (!n) {
                this.clear();
                return
            }
            this.$hidden.val(""), 
            this.$hidden.attr("name", ""), 
            this.$input.attr("name", this.name);
            if (typeof FileReader != "undefined") {
                var r = new FileReader,
                    i = this.$preview,
                    s = this.$element;
                r.onload = function(e) {
                    var result = {
                        name: n.name,
                        data: e.target.result,
                        size: n.size,
                    }
                    i.text(result.name), s.addClass("fileupload-exists").removeClass("fileupload-new")
                }, r.readAsDataURL(n)
            } else this.$preview.text(n.name), this.$element.addClass("fileupload-exists").removeClass("fileupload-new")
        },
        clear: function(e) {
            this.$hidden.val(""), this.$hidden.attr("name", this.name), this.$input.attr("name", "");
            if (navigator.userAgent.match(/msie/i)) {
                var t = this.$input.clone(!0);
                this.$input.after(t), this.$input.remove(), this.$input = t
            } else this.$input.val("");
            this.$preview.html(""), this.$element.addClass("fileupload-new").removeClass("fileupload-exists"), e && (this.$input.trigger("change", ["clear"]), e.preventDefault())
            file = undefined;
        },
        reset: function(e) {
            this.clear(), this.$hidden.val(this.original.hiddenVal), this.$preview.html(this.original.preview), this.original.exists ? this.$element.addClass("fileupload-exists").removeClass("fileupload-new") : this.$element.addClass("fileupload-new").removeClass("fileupload-exists")
        },
        trigger: function(e) {
            this.$input.trigger("click"), e.preventDefault()
        }
    }, e.fn.fileupload = function(n) {
        return this.each(function() {
            var r = e(this),
                i = r.data("fileupload");
            i || r.data("fileupload", i = new t(this, n)), typeof n == "string" && i[n]()
        })
    }, e.fn.fileupload.Constructor = t, e(document).on("click.fileupload.data-api", '[data-provides="fileupload"]', function(t) {
        var n = e(this);
        if (n.data("fileupload")) return;
        n.fileupload(n.data());
        var r = e(t.target).closest('[data-dismiss="fileupload"],[data-trigger="fileupload"]');
        r.length > 0 && (r.trigger("click.fileupload"), t.preventDefault())
    })
}(window.jQuery)
//foto 1
$(document).ready(function(){
        $('input[name="file1"]').change(function(e){
            var fileName = e.target.files[0].name;
            document.getElementById('fileName1').value=fileName;
        });
    });
//foto 2
$(document).ready(function(){
        $('input[name="file2"]').change(function(e){
            var fileName = e.target.files[0].name;
            document.getElementById('fileName2').value=fileName;
        });
    });
//foto 3
$(document).ready(function(){
        $('input[name="file3"]').change(function(e){
            var fileName = e.target.files[0].name;
            document.getElementById('fileName3').value=fileName;
        });
    });
//foto 4
$(document).ready(function(){
        $('input[name="file4"]').change(function(e){
            var fileName = e.target.files[0].name;
            document.getElementById('fileName4').value=fileName;
        });
    });
//foto 5
$(document).ready(function(){
        $('input[name="file5"]').change(function(e){
            var fileName = e.target.files[0].name;
            document.getElementById('fileName5').value=fileName;
        });
    });
//foto 6
$(document).ready(function(){
        $('input[name="file6"]').change(function(e){
            var fileName = e.target.files[0].name;
            document.getElementById('fileName6').value=fileName;
        });
    });
  </script>
  </body>
</html>
