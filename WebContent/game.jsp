<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! int counter = 1; %>
<%! int yPosition = 0; %>
<%! int xPosition = 0; %>
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
					    %>
							      <button x=<%= xPosition %> y=<%= yPosition %> class='puzzleSquare'><%= letras[i] %></button><br>
							      					      
							     <% 
							      
							      yPosition ++;
							      xPosition = 0; 
							      		     
							      
							  } else {  %>
							      <button x=<%= xPosition %> y=<%= yPosition %> class='puzzleSquare'><%= letras[i] %></button>						      
							      <%xPosition ++;
							  }
							  counter ++;
						   }
						%>
			  	 	</div>
			  	 </div>
			  </div>
			  <div class="col-4">
           		<div>
            		Lista de palabras:	
            		<%
				   		String[] palabras = {"test1", "test2", "test3","test4","test5"};
						for (int j = 0; j < palabras.length; j++) {
							 out.print("<div>" + palabras[j] + "</div>");
						}
					%> 
           		</div>
           		<div class="div-word-selected">
           			La palabra seleccionada:
           			<div class="word-selected"></div>
            	</div>
			  </div>
		  </div>
		</div>
	</body>
</html>

<!-- mirar este ejemplo https://github.com/bunkat/wordfind -->
<script type="text/javascript">
	var selectedSquares = [];
	var arrayHorizontal = [];
	var arrayVertical = [];
	var xLocationFirstLetter = '';
	var yLocationFirstLetter = '';
	var moveVertical = true;
	var moveHorizontal = true;
	var wordSelected = "";
	var renderButtonDelete = true;
	
	
	var startTurn = function (ev) {
		if (xLocationFirstLetter == ''){
			xLocationFirstLetter = ev.currentTarget.attributes.x.value;
			yLocationFirstLetter = ev.currentTarget.attributes.y.value;
			arrayHorizontal.push(ev.currentTarget.attributes.x.value);
			arrayVertical.push(ev.currentTarget.attributes.y.value);
		    $(this).addClass('selected');
		    selectedSquares.push(this);
		    wordSelected += ev.target.innerText;
		} else {
			if (moveHorizontal && ev.currentTarget.attributes.x.value != xLocationFirstLetter && ev.currentTarget.attributes.y.value == yLocationFirstLetter){
				if (arrayHorizontal.length != 0) {
					if (arrayHorizontal.length == 1 && arrayHorizontal[0] != 0){
						if(parseInt(arrayHorizontal[0]) + 1 == ev.currentTarget.attributes.x.value){
							$(this).addClass('selected');
							arrayHorizontal.push(ev.currentTarget.attributes.x.value);
							moveVertical = false;
							wordSelected += ev.target.innerText;
						    selectedSquares.push(this);
						}
						if(parseInt(arrayHorizontal[0]) - 1 == ev.currentTarget.attributes.x.value){
							$(this).addClass('selected');
							arrayHorizontal.push(ev.currentTarget.attributes.x.value);
							moveVertical = false;
							wordSelected += ev.target.innerText;
						    selectedSquares.push(this);
						}
					} else {
						for (m=0; m < arrayHorizontal.length; m++){
							if (parseInt(arrayHorizontal[m]) + 1 == ev.currentTarget.attributes.x.value || parseInt(arrayHorizontal[m]) - 1 == ev.currentTarget.attributes.x.value){
								$(this).addClass('selected');
								arrayHorizontal.push(ev.currentTarget.attributes.x.value);
								moveVertical = false;
								wordSelected += ev.target.innerText;
							    selectedSquares.push(this);
							}
						}
					}
				} else {
					if (ev.currentTarget.attributes.x.value > xLocationFirstLetter && ev.currentTarget.attributes.x.value - xLocationFirstLetter == 1){
						$(this).addClass('selected');
						moveVertical = false;
						xLocationFirstLetter = ev.currentTarget.attributes.x.value;
						yLocationFirstLetter = ev.currentTarget.attributes.y.value;
						arrayHorizontal.push(ev.currentTarget.attributes.x.value);
						wordSelected += ev.target.innerText;
					    selectedSquares.push(this);
					}
					if (ev.currentTarget.attributes.x.value < xLocationFirstLetter && xLocationFirstLetter - ev.currentTarget.attributes.x.value == 1){
						$(this).addClass('selected');
						moveVertical = false;
						xLocationFirstLetter = ev.currentTarget.attributes.x.value;
						yLocationFirstLetter = ev.currentTarget.attributes.y.value;
						arrayHorizontal.push(ev.currentTarget.attributes.x.value);
						wordSelected += ev.target.innerText;
					    selectedSquares.push(this);
					}
				}
			} else if(moveVertical && ev.currentTarget.attributes.x.value == xLocationFirstLetter){
				if (arrayVertical.length != 0) {
					if (arrayVertical.length == 1){
						if(parseInt(arrayVertical[0]) + 1 == ev.currentTarget.attributes.y.value){
							$(this).addClass('selected');
							arrayVertical.push(ev.currentTarget.attributes.y.value);
							moveHorizontal = false;
							wordSelected += ev.target.innerText;
						    selectedSquares.push(this);
						}
						if(parseInt(arrayVertical[0]) - 1 == ev.currentTarget.attributes.y.value){
							$(this).addClass('selected');
							arrayVertical.push(ev.currentTarget.attributes.y.value);
							moveHorizontal = false;
							wordSelected += ev.target.innerText;
						    selectedSquares.push(this);
						}
					} else {
						for (m=0; m < arrayVertical.length; m++){
							if (parseInt(arrayVertical[m]) + 1 == ev.currentTarget.attributes.y.value || parseInt(arrayVertical[m]) - 1 == ev.currentTarget.attributes.y.value){
								$(this).addClass('selected');
								arrayVertical.push(ev.currentTarget.attributes.y.value);
								moveHorizontal = false;
								wordSelected += ev.target.innerText;
							    selectedSquares.push(this);
							}
						}
					}
				} else {
					if (ev.currentTarget.attributes.y.value > yLocationFirstLetter){
						$(this).addClass('selected');
						moveHorizontal = false;
						xLocationFirstLetter = ev.currentTarget.attributes.x.value;
						yLocationFirstLetter = ev.currentTarget.attributes.y.value;
						wordSelected += ev.target.innerText;
					    selectedSquares.push(this);
					}
					if (ev.currentTarget.attributes.y.value < yLocationFirstLetter){
						$(this).addClass('selected');
						moveHorizontal = false;
						xLocationFirstLetter = ev.currentTarget.attributes.x.value;
						yLocationFirstLetter = ev.currentTarget.attributes.y.value;
						wordSelected += ev.target.innerText;
					    selectedSquares.push(this);
					}
				}
			}
		}
		
		$( ".word-selected" ).html( wordSelected );
		
		if (renderButtonDelete && wordSelected != ''){
			renderButtonDelete = false;
			$( ".div-word-selected" ).append( "<button id='checkWord' type='button' class='btn btn-success margin-right-20 margin-top-50'>Comprobar</button>" );
			$( ".div-word-selected" ).append( "<button id='deleteWord' type='button' class='btn btn-danger margin-top-50'>Borrar</button>" );
			$(document).on('click', '#deleteWord', function() {
				$(".word-selected")[0].innerText = "";
				$("#deleteWord").remove();
				$("#checkWord").remove();
				
				for(p=0; p<selectedSquares.length; p++){
					selectedSquares[p].classList.remove("selected");
				}
				
				selectedSquares = [];
				arrayHorizontal = [];
				arrayVertical = [];
				xLocationFirstLetter = '';
				yLocationFirstLetter = '';
				moveVertical = true;
				moveHorizontal = true;
				renderButtonDelete = true;
				wordSelected = "";
			});
			$(document).on('click', '#checkWord', function() {

			});
		}
	};

	$('.puzzleSquare').mousedown(startTurn);

</script>