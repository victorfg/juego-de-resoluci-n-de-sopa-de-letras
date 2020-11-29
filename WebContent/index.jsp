<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="icon" type="images/png" href="images/icons/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="libs/css/bootstrap.min.css"/>
		
		<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
		
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
		<link rel="stylesheet" type="text/css" href="css/util.css"/>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
		
		<script type="text/javascript" src="libs/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="libs/js/bootstrap.bundle.min.js"></script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Sopa de Letras</title>
	</head>
	<body>
		<div class="limiter">
			<div class="container-login100">
				<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
					<form action="login" method="post" class="login100-form validate-form flex-sb flex-w">
						<c:if test="${not empty message}">
					    	<div class="error-message">${message}</div>
						</c:if>	
						<span class="login100-form-title p-b-32">
							Login para empezar a jugar!
						</span>
						<span class="txt1 p-b-11">
							Usuario
						</span>
						<div class="wrap-input100 validate-input m-b-36" data-validate = "Username is required">
							<input class="input100" type="text" name="username" >
							<span class="focus-input100"></span>
						</div>
						
						<span class="txt1 p-b-11">
							Contraseña
						</span>
						<div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
							<span class="btn-show-pass">
							</span>
							<input class="input100" type="password" name="password" >
							<span class="focus-input100"></span>
						</div>
						<div class="container-login100-form-btn">
							<button class="login100-form-btn">
								Entrar
							</button>
						</div>
						<div class="margin-top-20">UserTest : Admin || 123</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
<script>
	localStorage.setItem('ldap_user',document.getElementsByName("username")[0].value);
</script>