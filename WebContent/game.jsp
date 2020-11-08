<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! int fontSize; %>
<html>
	<head>
		<link rel="icon" type="images/png" href="images/icons/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="libs/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/game.css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Juego Sopa de Letras</title>
	</head>
	<body>
		<div class="main container">
		  <div class="row">
			  <div class="col-8">
			  	 <div id="puzzle">
			  	 	<div>
			  	 		<button class="puzzleSquare">
			  	 			
			  	 		</button>
			  	 	</div>
			  	 </div>
			  </div>
			  <div class="col-4">
                <%
				   String[] colors = {"A", "C", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ"};
				   for (int i = 0; i < colors.length; i++) {
				      out.print("<span>" + colors[i] + "</span>");
				   }
				%>
                
			  </div>
		  </div>
		</div>
	</body>
</html>