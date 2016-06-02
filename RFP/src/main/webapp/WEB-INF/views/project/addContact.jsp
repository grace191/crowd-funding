<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CrowdFunding Platform</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/project_bootstrap.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/project_style.css"
	rel="stylesheet">

<style>
html, body, #wrapper {
	height: 100%;
	margin: 0;
	padding: 0;
}

#wrapper {
	position: relative;
}

#top, #middle, #bottom {
	position: absolute;
}

#top {
	height: 50px;
	width: 100%;
}

#middle {
	top: 50px;
	bottom: 50px;
	width: 100%;
}

#bottom {
	bottom: 0;
	height: 80px;
	width: 100%;
}
</style>
</head>

<body>
	<div id="wrapper">
		<div id="top">
			<div class="navbar navbar-default" style="margin-bottom: 0">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-responsive-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<c:url value='/'/>">Home</a>
				</div>
				<div class="navbar-collapse collapse navbar-responsive-collapse">
					<ul class="nav navbar-nav">

						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">User Management<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value='/admin/CreateAccount'/>">New
											User</a></li>
									<%-- <c:url value="/admin/accounts" var="url" /> --%>
									<li class="active"><a
										href="<c:url value='/admin/accounts'/>">All Users</a></li>
								</ul></li>

						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_PCREATOR')">
							<li><a href="<c:url value='/project/projects'/>">Projects</a></li>
						</sec:authorize>

					</ul>
					<ul class="nav navbar-nav navbar-right">
						<sec:authorize access="hasAnyRole('ROLE_PCREATOR')">
							<li><a href="<c:url value='/project'/>">Start a project</a></li>
						</sec:authorize>
						<sec:authorize
							access="hasAnyRole('ROLE_PCREATOR','ROLE_PADMIN','ROLE_USER')">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">My Account <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<sec:authorize
										access="hasAnyRole('ROLE_ADMIN','ROLE_PCREATOR','ROLE_PADMIN')">
										<li><a href="<c:url value='/project/projects'/>">Projects</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_USER')">
										<li><a href="<c:url value='/user/projects'/>">Projects</a></li>
									</sec:authorize>
								</ul></li>
						</sec:authorize>
						<c:choose>
							<c:when test="${pageContext.request.userPrincipal.name != null}">
								<li><a href="javascript:formSubmit()"><span
										class="glyphicon glyphicon-log-out"></span> Logout</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="register"><span
										class="glyphicon glyphicon-user"></span> Sign Up</a></li>
								<li><a href="login"><span
										class="glyphicon glyphicon-log-in"></span> Login</a></li>

							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<script>
				function formSubmit() {
					document.getElementById("logoutForm").submit();
				}
			</script>
		</div>
		<div id="middle">
			<section id="title-bar">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h2>Add Contact</h2>
						</div>
					</div>
				</div>
			</section>


			<section id="contact">
				<div class="container">
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
							<c:url var="addContact" value="/project/contact/add" />
							<form:form method="post" modelAttribute="contact"
								action="${addContact}">
								<div class="form-group">

									<c:choose>

										<c:when test="${!empty user_email}">
											<label>User Email</label>
											<form:input path="user_email" id="User_Email"
												name="User_Email" type="text" value="${user_email }"
												class="form-control" placeholder="Enter User Email"
												readonly="true" />
										</c:when>
										<%-- 		<c:when test="${!empty contact_id }">
									<label>Contact ID</label>
									<form:input path="id" id="id" name="id" type="text"
										value="${contact_id }" class="form-control"
										placeholder="Enter User Email" readonly="true" />
								</c:when> --%>

										<c:otherwise>
											<label>User Email</label>
											<form:input path="user_email" id="User_Email"
												name="User_Email" type="text" value="${user_email }"
												class="form-control" placeholder="Enter User Email" />
										</c:otherwise>
									</c:choose>
									<c:if test="${!empty contact_id }">
										<label>Contact ID</label>
										<form:input path="id" id="id" name="id" type="text"
											value="${contact_id }" class="form-control" readonly="true" />
									</c:if>
								</div>
								<div class="form-group">
									<label>First Name</label>
									<form:input path="first_name" id="name" name="name" type="text"
										class="form-control" placeholder="Enter First Name" />
								</div>
								<div class="form-group">
									<label>Last Name:</label>
									<form:input path="last_name" id="Last_Name" name="Last_Name"
										type="text" class="form-control" placeholder="Enter Last Name" />
								</div>
								<div class="form-group">
									<label>Participant Email</label>
									<form:input path="participant_email" id="Participant_Email"
										name="Participant_Email" type="text" class="form-control"
										placeholder="Enter Participant Email" />
								</div>
								<div class="form-group" style="padding:20px 0 20px 80px">
								<div class="col-sm-2 col-md-offset-10">
									<button type="submit" class="btn btn-success">Submit</button>

								</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</section>
		</div>
		<div id="bottom">

			<footer>
				<p>Copyright 2015, All Rights Reserved</p>
			</footer>
		</div>
		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/project-bootstrap.js"></script>
	</div>
</body>
</html>