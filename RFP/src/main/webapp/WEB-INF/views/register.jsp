
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/bootstrap/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/form-elements.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/style-login.css'/>">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value='/resouces/assets/ico/apple-touch-icon-144-precomposed.png'/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<c:url value='/resouces/assets/ico/apple-touch-icon-114-precomposed.png'/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<c:url value='/resouces/assets/ico/apple-touch-icon-72-precomposed.png'/>">
<link rel="apple-touch-icon-precomposed"
	href="<c:url value='/resouces/assets/ico/apple-touch-icon-57-precomposed.png'/>">

</head>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row" style="margin-top: 80px">
					<div class="col-sm-8 col-sm-offset-2 text"></div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>Sign Up!</h3>
								<p>Enter your email and password to sign up:</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div class="form-bottom">
							<c:url var="addAccount" value="/account/add/${projectId}/${contactId }" />
							<form:form action="${addAccount}" modelAttribute="registerBean"
								method="post">
								<div class="form-group">
									<label class="sr-only" for="form-email"><spring:message
											code="username" text="Username" /></label>
									<form:input path="username" name="username" type="username"
										class="form-control form-username" placeholder="Username"
										id="form-username" require="required" value="${fullname }" />
									<font color="red"> <form:errors path="username" /></font>
								</div>
								<div class="form-group">
									<div>
										<label class="sr-only" for="form-email"><spring:message
												code="email" text="Email" /></label>
										<form:input path="email" name="email" type="email"
											class="form-control" placeholder="Email" require="required"
											value="${email }" />
										<font color="red"> <form:errors path="email" /></font>
									</div>

								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password"><spring:message
											code="pass" text="Password" /></label>
									<form:input path="password" name="password" type="password"
										class="form-control" placeholder="Password" require="required" />
									<font color="red"></font>
									<form:errors path="password" />
								</div>
								<button type="submit" class="btn">Register!</button>
								<div style="text-align: center; padding-top: 20px;">
									<p style="margin: 0">
										Have an account? <a href="<c:url value='/login'/>">Log in</a>
									</p>
								</div>
								<!-- <div class="form-group" style="margin-bottom:0">
									<div style="text-align:center; padding-top:20px; ">
											<p>Have an account? <a href="#">Log in</a>
											</p>
									</div>
								</div> -->
								<!-- <div class="form-group">


										<input type="submit" id="account_submit" value="Register"
											class="btn" />
									</div> -->


							</form:form>















							<!-- <form role="form" action="j_spring_security_check" method="post"
								class="login-form">

								


								<div class="form-group">
									<label class="sr-only" for="form-email">Email</label> <input
										type="email" name="username" placeholder="Email..."
										class="form-username form-control" id="form-username" required>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">Password</label> <input
										type="password" name="password" placeholder="Password..."
										class="form-password form-control" id="form-password" required>
								</div>
								<button type="submit" class="btn">Sign in!</button>
								<div class="form-group">
									<div id="remember-me" class="col-sm-10">
										<div class="checkbox">
											<label> <input type="checkbox"> Remember me
											</label>
										</div>
									</div>
								</div>
							</form> -->
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 social-login"></div>
				</div>
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script
		src="<c:url value='/resources/assets/js/jquery-1.11.1.min.js'/>"></script>
	<script
		src="<c:url value='/resources/assets/bootstrap/js/bootstrap.min.js'/>"></script>
	<script
		src="<c:url value='/resources/assets/js/jquery.backstretch.min.js'/>"></script>
	<%-- 	<script src="<c:url value='/resources/assets/js/scripts.js'/>"></script>
 --%>
	<script>
		jQuery(document)
				.ready(
						function() {

							/*
							    Fullscreen background
							 */
							$
									.backstretch("<c:url value='/resources/assets/images/backgrounds/1.jpg'/>");

							/*
							    Form validation
							 */
							$(
									'.login-form input[type="text"], .login-form input[type="password"], .login-form textarea')
									.on('focus', function() {
										$(this).removeClass('input-error');
									});

							$('.login-form')
									.on(
											'submit',
											function(e) {

												$(this)
														.find(
																'input[type="text"], input[type="password"], textarea')
														.each(
																function() {
																	if ($(this)
																			.val() == "") {
																		e
																				.preventDefault();
																		$(this)
																				.addClass(
																						'input-error');
																	} else {
																		$(this)
																				.removeClass(
																						'input-error');
																	}
																});

											});

						});
	</script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

</body>

</html>