<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
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
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/project_style.css"
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
	src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/ckeditor/plugins/wordcount/plugin.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/datakeeper.js"></script>
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
					<a href="#" class="button" id="loadContacts">OK</a>
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

						<sec:authorize access="isAuthenticated()">
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
						</sec:authorize>
						<c:if test="${!empty summary}">
							<h2 class="text-center">Executive Summary</h2>
							<div class="form-group">
								<!-- <label class="control-label col-sm-2" for="Executive Summary">Executive
								Summary:</label> -->
								<div class="col-md-8 col-sm-10 col-md-offset-2"
									style="width: 67%">${project.summary }</div>

							</div>
						</c:if>

						<sec:authorize access="isAuthenticated()">
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
									<a class="btn btn-lg btn-success"
										href="<c:url value='/project/projects'/>">Accept</a>

								</div>
								<div class="col-sm-1 col-lg-1" style="padding: 20px 0 0 20px">
									<a class="btn btn-lg btn-danger"
										href="<c:url value='/project/projects'/>">Decline</a>

								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-6 col-sm-8 col-lg-6">
									<input type="checkbox" name="Terms" id="Terms">
										 <button onclick="checkTerms(${projectId})">You must
										accept the terms and conditions before you accept the offer.</button> 
										<%-- <a href='javascript:;' onclick='checkTerms(${projectId});'>You must
										accept the terms and conditions before you accept the offer. >>></a>
										<br> --%>
								</div>

							</div>
						</sec:authorize>
					</form:form>
				</div>

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
		<div id="push"></div>
	</div>
	<div id="bottom">
		<div id="footer">
			<!-- <footer>
				<p>Copyright 2015, All Rights Reserved</p>
			</footer>
 -->
		</div>
	</div>
	<script type="text/javascript">
		

		function checkTerms(id) {

			$.get("<c:url value='/user/getTerms/"+id+"'/>", function(
					data) {
				popup(data);

			});

		}
	</script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="<c:url value='/resources/js/alert_jquery.js'/>"></script>
</body>
</html>