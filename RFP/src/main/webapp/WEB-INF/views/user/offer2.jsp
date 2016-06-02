<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<title>Preview</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js">
	
</script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/charactercount.js"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

<script src="http://malsup.github.com/jquery.form.js"></script>
<!-- <script src="//cdn.ckeditor.com/4.5.8/standard/ckeditor.js"></script> -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Building your project</title>
<style>
body {
	background-image:
		url("${pageContext.request.contextPath}/resources/images/background.jpg");
	background-image:
		url("<c:url value='/resources/images/background.jpg'/>");
}

.control-label {
	padding-right: 0 !important;
}
</style>
<link rel="stylesheet" href="<c:url value='/resources/css/dialog.css'/>"
	type="text/css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<%-- <h2>Create a new project</h2>
<h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>
<h3>hi: ${username }</h3> --%>
			<div class="navbar navbar-default"
				style="background: #228B22; border-color: #228B22">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-responsive-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">LOGO</a>
				</div>
				<div class="navbar-collapse collapse navbar-responsive-collapse">
					<ul class="nav navbar-nav">
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<spring:url value="admin/accounts" var="url" htmlEscape="true" />
							<li><a href="${url}">Accounts</a></li>
						</security:authorize>
						<!-- <li><a href="projects">Projects</a></li> -->
					</ul>
					<%-- <form class="navbar-form navbar-left">
						<input type="text" class="form-control col-lg-8"
							placeholder="Search">
					</form> --%>
					<ul class="nav navbar-nav navbar-right">
						<security:authorize
							access="hasAnyRole('ROLE_ADMIN','ROLE_PCREATOR')">
							<li><a href="project">Start a project</a></li>
						</security:authorize>
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
		<div class="container">
			<div id="dialog-overlay"></div>
			<div id="dialog-box">
				<div class="dialog-content">
					<div id="dialog-message"></div>
					<a href="#" class="button">OK</a>
				</div>
			</div>
			<h2 class="text-center">
				<c:out value="${project.title }" />
			</h2>
			<h2 id="myresult"></h2>
			<div class="tab-content">
				<div id="basic" class="tab-pane fade in active">
					<form:form action="${addAction}" modelAttribute="project"
						method="post" class="form-horizontal" role="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<security:authorize access="isAuthenticated()">
							<div class="form-group">
								<div id="result"
									class="col-sm-10 col-md-10 col-lg-10 col-md-offset-1"
									style="padding-top: 20px; padding-bottom: 10px">

									<c:if test="${!empty imagePath }">
										<img src="${pageContext.request.contextPath}/${imagePath}"
											class="col-md-10, col-sm-12" />
									</c:if>


								</div>

							</div>
						</security:authorize>
						<h2 class="text-center">Executive Summary</h2>
						<div class="form-group">
							<!-- <label class="control-label col-sm-2" for="Executive Summary">Executive
								Summary:</label> -->
							<div class="col-md-8 col-sm-10 col-md-offset-2"
								style="width: 67%">${project.summary }</div>

						</div>
						<security:authorize access="isAuthenticated()">
							<h2 class="text-center">Description</h2>
							<div class="form-group">
								<div class="col-md-8 col-sm-10 col-md-offset-2"
									style="width: 67%">${project.description }</div>
							</div>
							<h2 class="text-center">Attachments</h2>
							<div class="form-group">

								<div class="col-sm-10 col-md-8"></div>
								<div class="col-md-8 col-md-offset-2 col-sm-10"
									style="width: 67%; padding: 20px 0 10px 0">
									<div class="tablecontainer">
										<table class="table table-hover">
											<!-- <thead>
										<tr>
											<th>No.</th>
											<th>File Name</th>
											<th>Type</th>
											<th>Description</th>
											<th width="100"></th>
											<th width="100"></th>
										</tr>
									</thead> -->
											<tbody>
												<c:forEach items="${filesList}" var="file">
													<tr>
														<td>${file.id}</td>
														<td>${file.name}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>


								</div>

							</div>

							<h2 class="text-center">Success Criteria</h2>

							<div class="form-group">
								<div class="col-md-8 col-sm-10 col-md-offset-2">
									<p>${project.successCriteria }:${project.inputValue }</p>
								</div>
							</div>
							<div class="form-group">

								<div class="col-sm-offset-9 col-sm-1 col-lg-1"
									style="padding: 20px 0">
									<a id="accept" class="btn btn-lg btn-success accept"
										href="<c:url value='/user/projects'/>">Accept</a>
									<%-- 	<input type="button" id="accept"
										onClick="javascript:window.location='<c:url value='/project/projects'/>';"> --%>
									<!-- <button type="button" id="accept">click me</button> -->
								</div>

								<div class="col-sm-1 col-lg-1" style="padding: 20px 0 0 20px">
									<a class="btn btn-lg btn-danger"
										href="<c:url value='/user/reasonForDecline'/>">Decline</a>

								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-6 col-sm-8 col-lg-6">
									<input type="checkbox" name="threshold" id="terms"><a
										href='javascript:;' onclick='checkTerms("<c:out value='${projectId }'/>");'>You
										must accept the terms and conditions before you accept the
										offer.</a><br>
								</div>

							</div>

						</security:authorize>
					</form:form>
				</div>
				<script type="text/javascript">
					$(document)
							.ready(
									function() {
										$('#accept')
												.click(
														function() {

															if ($(
																	'input:checkbox[name=threshold]')
																	.is(
																			':checked')) {
																$("#accept")
																		.attr(
																				"href",
																				"<c:url value='/user/projects/${projectId}'/>");
															} else {
																popup("You must accept the terms and conditions before you accept the offer.");
/* 																alert("You must accept the terms and conditions before you accept the offer.");
 */																$("#accept")
																		.attr(
																				"href",
																				"#");
															}

														});

									});
					
					function checkTerms(id) {

						$.get("<c:url value='/user/getTerms/"+id+"'/>", function(
								data) {
							popup(data);

						});

					}
				</script>
				<div id="menu2" class="tab-pane fade">
					<h3>Menu 2</h3>
					<p>Sed ut perspiciatis unde omnis iste natus error sit
						voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
				</div>
				<div id="menu3" class="tab-pane fade">
					<h3>Menu 3</h3>
					<p>Eaque ipsa quae ab illo inventore veritatis et quasi
						architecto beatae vitae dicta sunt explicabo.</p>
				</div>
			</div>
		</div>
	</div>
	<div id="bottom">
		<footer style="background: black">
			<p>Copyright 2015, All Rights Reserved</p>
		</footer>

	</div>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="<c:url value='/resources/js/alert_jquery.js'/>"></script>
</body>
</html>