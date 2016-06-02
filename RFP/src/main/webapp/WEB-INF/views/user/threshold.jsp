<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Login</title>

<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/animate.min.css">


<link href='https://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Pacifico'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Droid+Sans'
	rel='stylesheet' type='text/css'>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.login {
	background-image:
		url("${pageContext.request.contextPath}/resources/images/signup-background.jpg");
}
</style>
</head>
<!-- <body data-spy="scroll" data-target="#myNavbar" data-offset="70" id="login"> -->
<body class="login">


	<div class="container" style="width: 500px; margin: 200px auto 0 auto;">
		<h2 class="text-center">Sign In</h2>
		<form class="form-horizontal" name="form"
			action="j_spring_security_check" method="post">
			<security:authorize
				access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')"
				var="isUSer" />
			<font size="2" color="red"> </font> <font size="2" color="green">
			</font> <br />
			<c:if test="${not empty error}">
				<div class="text-center">

					<font size="2" color="red"><b>Either your username or
							password is wrong</b></font>
				</div>

			</c:if>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label"><spring:message
						code="email" text="Email" /></label>
				<div class="col-sm-10">
					<input name="username" type="email" class="form-control"
						id="inputEmail3" placeholder="Email" required autofocus>

				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label"><spring:message
						code="pass" text="Password" /></label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword3"
						placeholder="Password" name="password" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label> <input type="checkbox"> Remember me
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row"></div>
				<div class="row">
					<div class="col-sm-offset-2 col-sm-2"  style="padding-left:25px">
						<input type="submit" value="Sign in" class="btn btn-primary"/>
					</div>

					<div class="col-sm-2">
						<a href="register" class="btn btn-primary">Register</a>
					</div>
				</div>

			</div>
		</form>

	</div>

	<!-- END ABOUT -->




	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/js/wow.min.js"></script>
	<script>
		new WOW().init();
	</script>

</body>
</html>
