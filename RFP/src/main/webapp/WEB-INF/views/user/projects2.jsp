<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- <%@ include file="header.jsp"%> --%>
<%-- <%@ include file="footer.jsp"%>
 --%>
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
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/style.css"
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
	width: 100%;
}
</style>
</head>

<body>
	<div id="wrapper">
		<div id="top">
			<div class="navbar navbar-default" style="background:#228B22;border-color:#228B22">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-responsive-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="hello">Home</a>
				</div>
				<div class="navbar-collapse collapse navbar-responsive-collapse">
					<ul class="nav navbar-nav">
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<spring:url value="admin/accounts" var="url" htmlEscape="true" />
							<li><a href="${url}">Accounts</a></li>
						</sec:authorize>
						<!-- <li><a href="projects">Projects</a></li> -->
					</ul>
				<!-- 	<form class="navbar-form navbar-left">
						<input type="text" class="form-control col-lg-8"
							placeholder="Search">
					</form> -->
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">My Account <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value='/user/projects'/>">Projects</a></li>
							</ul></li>
						<c:choose>
							<c:when test="${pageContext.request.userPrincipal.name != null}">
								<li><a href="javascript:formSubmit()"> Logout</a></li>
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
			<section>
				<div class="container">
					<h2 class="text-center" style="padding-bottom: 30px">Your
						projects</h2>
					<div class="col-md-10 col-md-offset-1">
						<table class="table table-striped">
							<tr>
								<th style="display:none">id</th>
								<th>Title</th>
								<th>Invitation status</th>
								<th>Offer status</th>
							</tr>
							<c:forEach items="${myprojects}" var="project">
								<tr>
									<td style="display:none"><c:out value="${project.id }" /></td>
									<td><a href="<c:url value='/user/offer/${project.id}'/>"><c:out
												value="${project.title}" /></a></td>
								<%-- 	<td><c:out value="${project.invitationStatus }" /></td> --%>
									<td></td>
									<td></td>
								</tr>
							</c:forEach>

						</table>
					</div>

				</div>

			</section>
		</div>
		<div id="bottom">
			<footer style="background:black">
				<p>Copyright 2016, All Rights Reserved</p>
			</footer>
		</div>

		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	</div>
</body>
</html>