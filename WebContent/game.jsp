<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! int counter = 1; %>
<html>
	<head>
	
		<link rel="icon" type="images/png" href="images/icons/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="libs/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/game.css"/>
		
		<script type="text/javascript" src="libs/js/jquery-3.5.1.js"></script>
				
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Juego Sopa de Letras</title>
	</head>
	<body>
		<div class="container margin-top-50">
		  <div class="row">
			  <div class="col-8">
			  	 <div id="puzzle">
			  	 	<div>
				  	 	<%
						   String[] letras = {"T", "E", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ","A", "C", "G","D","Y","Ñ","A", "C", "G","D"};
						   for (int i = 0; i < letras.length; i++) {
							  if (counter % 8 == 0) {
							      out.print("<button class='puzzleSquare'>" + letras[i] + "</button><br>");
							  } else {
							      out.print("<button class='puzzleSquare'>" + letras[i] + "</button>");  
							  }
							  counter ++;
						   }
						%>
			  	 	</div>
			  	 </div>
			  </div>
			  <div class="col-4">
             		Lista de palabras:	
             		<%
					   String[] palabras = {"test1", "test2", "test3","test4","test5"};
					   for (int i = 0; i < palabras.length; i++) {
						 out.print("<div>" + palabras[i] + "</div>");
					   }
					%> 
			  </div>
		  </div>
		</div>
	</body>
</html>

<!-- mirar este ejemplo https://github.com/bunkat/wordfind -->
<script type="text/javascript">
	var startSquare, selectedSquares = [], curOrientation, curWord = '';
	var startTurn = function () {
	    $(this).addClass('selected');
	    startSquare = this;
	    selectedSquares.push(this);
	    curWord = $(this).text();
	};
	var mouseMove = function() { 
	      select(this);
	};
	//attach events to the buttons
	// optimistically add events for windows 8 touch
	if (window.navigator.msPointerEnabled) {
	  $('.puzzleSquare').on('MSPointerDown', startTurn);
	  $('.puzzleSquare').on('MSPointerOver', select);
	  $('.puzzleSquare').on('MSPointerUp', endTurn);
	} else {
	  $('.puzzleSquare').mousedown(startTurn);
	  //$('.puzzleSquare').mouseenter(mouseMove);
	  //$('.puzzleSquare').mouseup(endTurn);
	  //$('.puzzleSquare').on("touchstart", startTurn);
	  //$('.puzzleSquare').on("touchmove", touchMove);
	 // $('.puzzleSquare').on("touchend", endTurn);
	}
</script>