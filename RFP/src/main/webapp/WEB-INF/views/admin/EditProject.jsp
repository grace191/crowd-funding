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
<title>Start your project</title>
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
	src="${pageContext.request.contextPath}/resources/js/localstorage.js"></script>
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
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<%-- <h2>Create a new project</h2>
<h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>
<h3>hi: ${username }</h3> --%>
			<div class="navbar navbar-default">
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
									<c:url value="/admin/accounts" var="url" />
									<li><a href="${url}">All Users</a></li>
								</ul></li>

						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_PCREATOR')">
							<li><a href="<c:url value='/project/projects'/>">Projects</a></li>
						</sec:authorize>

					</ul>
					<!-- 			<form class="navbar-form navbar-left">
				<input type="text" class="form-control col-lg-8"
					placeholder="Search">
			</form> -->
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
										access="hasAnyRole('ROLE_PCREATOR','ROLE_PADMIN')">
										<li><a href="<c:url value='/project/projects'/>">Projects</a></li>
										<li><a href="<c:url value='/project/contacts'/>">Contacts</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_USER')">
										<li><a href="/user/projects">Projects</a></li>
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
			<h2 class="text-center">Start building your project!</h2>
			<h2 id="myresult"></h2>
			<!-- 	<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#basic">Basic</a></li>
				<li><a data-toggle="tab" href="#contacts">Invitation</a></li>
				<li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
				<li><a data-toggle="tab" href="#menu3">Menu 3</a></li>
			</ul> -->
			<div class="tab-content">
				<div id="basic" class="tab-pane fade in active">
					<c:url value="/project/edit/upload/attachment/${project.id }" var="uploadAttachments" />
					<c:url value="/project/edit/upload/image/${project.id }" var="uploadImage" />
					<c:url var="addAction" value="/project/update/${project.id }" />
					<%-- 				<c:url value="/uploadFile" var="uploadAttachments" />
 --%>
					<form id="fileForm" action="${uploadAttachments }" method="post"
						enctype="multipart/form-data"></form>
					<form:form id="projectForm" action="${addAction}" method="post"
						modelAttribute="project"></form:form>
					<form id="imageForm" action="${uploadImage }" method="post"
						enctype="multipart/form-data"></form>
					<form:form action="${addAction}" modelAttribute="project"
						method="post" class="form-horizontal" role="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="form-group">
							<label class="control-label col-sm-2" for="Project title">Title:</label>
							<div class="col-md-8 col-sm-10">
								<form:input class="form-control" path="title" type="text"
									placeholder="Project Title" form="projectForm" id="title_input" value="${project.title }"/>
								<font color="red"><form:errors path="title" /></font>
								<%-- 					<form:input class="form-control"
								placeholder="Number of Participants" path="successCriteria"
								form="projectForm" />
								<font color="red"><form:errors path="successCriteria" /></font> --%>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="Image">Project
								Image:</label>
							<div class="col-sm-10 col-md-8">
								<form method="post" action="${uploadImage }"
									enctype="multipart/form-data">
									<!-- 	<input type="file" class="filestyle"
									data-classButton="btn btn-primary" data-input="false"
									data-classIcon="icon-plus" data-buttonText="Your label here."> -->
									<input type="hidden" name="project_title" id="project_title"
										value="hidden" />
									<div class="col-md-2 col-sm-2 col-xs-2"
										style="width: 25%; padding: 0">
										<input name="imageUpload" id="imageUpload" type="file"
											form="imageForm" class="filestyle"
											data-buttonName="btn-primary"
											data-classButton="btn btn-primary" data-input="false"
											data-classIcon="icon-plus" data-size="nr"
											data-buttonText="Choose an image" data-placeholder="No file"
											style="width: 50%" />
									</div>
									<div class="col-md-2 col-sm-2 col-xs-2">
										<button value="Submit" form="imageForm"
											class="btn btn-success">upload</button>
									</div>

									<c:if test="${!empty imageFileList }">
										<c:forEach items="${imageFileList }" var="image">
											<div class="col-md-2 col-sm-2 col-xs-2" style="padding: 0">
												<!-- <button value="Delete" form="imageForm" class="btn btn-danger">Delete</button> -->
												<a href="<c:url value='/edit/delete/image/${project.id }/${image.id}' />"
													class="btn btn-danger custom-width">delete</a>
											</div>
										</c:forEach>
									</c:if>


								</form>
							</div>
							<div id="result" class="col-md-8 col-md-offset-2 col-sm-10"
								style="padding-top: 20px; padding-bottom: 10px">

								<c:if test="${!empty imagePath }">
									<img src="<c:url value='/${imagePath}'/>"
										class="col-md-10, col-sm-12" />
									<%-- <img src="http://localhost:8080/RFP/${imagePath}"
										class="col-md-10, col-sm-12" /> --%>
								</c:if>
								<%--  <c:if test="${!empty imagePath }">
									<img src="${imagePath}"
										class="col-md-10, col-sm-12" />
								</c:if>  --%>


							</div>

						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="Executive Summary">Executive
								Summary:</label>
							<div class="col-md-8 col-sm-10" style="width: 67%">
								<form:textarea class="form-control" path="summary"
									placeholder="Executive Summary" name="summary" id="summary"
									form="projectForm" value="${project.summary }"/>
								<font color="red"><form:errors path="summary" /></font>
							</div>

						</div>
						<div class="col-sm-2 col-md-offset-2"
							style="width: 100%; padding: 15px">
							<button type="button" id="previewSummary" class="btn btn-primary">Preview</button>
						</div>
						<div class="form-group">
							<div id="summaryData" class="col-md-8 col-sm-10 col-md-offset-2"
								style="width: 67%"></div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="Description"
								style="padding-left: 70px">Description:</label>
							<div class="col-md-8 col-sm-10" style="width: 67%">
								<form:textarea class="form-control" path="description"
									placeholder="Description" name="description" id="description"
									form="projectForm" value="${project.description }"/>

								<font color="red"><form:errors path="description" /></font>
							</div>
						</div>
						<div class="col-sm-2 col-md-offset-2"
							style="width: 100%; padding: 15px">
							<button type="button" id="previewDescription"
								class="btn btn-primary">Preview</button>
						</div>
						<div class="form-group">
							<div id="descriptionData"
								class="col-md-8 col-sm-10 col-md-offset-2" style="width: 67%">
							</div>
						</div>
						<script>
							CKEDITOR.replace('summary');
							CKEDITOR.replace('description', {
								removePlugins : 'wordcount'
							});
						</script>
						<script type="text/javascript">
							$(document)
									.ready(
											function() {
												$('#previewSummary')
														.click(
																function() {
																	//		alert("click");
																	var contents = CKEDITOR.instances.summary
																			.getData();
																	$(
																			'#summaryData')
																			.html(
																					contents);

																});

												$('#previewDescription')
														.click(
																function() {
																	var dContents = CKEDITOR.instances.description
																			.getData();
																	$(
																			'#descriptionData')
																			.html(
																					dContents);

																});

												$('#projectSubmmit')
														.click(
																function() {
																	var contents = CKEDITOR.instances.summary
																			.getData();
																	var dContents = CKEDITOR.instances.description
																			.getData();
																	$(
																			'#summary')
																			.val(
																					contents);
																	$(
																			'#description')
																			.val(
																					dContents);
																});
												/* 	$('#summary').limit('10',
															'#charsLeft'); */
											});

						</script>


						<div class="form-group">
							<label class="control-label col-sm-2" for="document">Project
								Attachments:</label>

							<div class="col-sm-10 col-md-8">
								<form action="${uploadAttachments }" method="post"
									enctype="multipart/form-data">

									<div class="col-md-2 col-sm-2 col-xs-2"
										style="width: 35%; padding: 0">
										<input type="file" name="fileupload" form="fileForm"
											class="filestyle" data-buttonName="btn-primary"
											data-classButton="btn btn-primary" data-input="false"
											data-classIcon="icon-plus" data-size="nr"
											data-buttonText="Choose your attachment"
											data-placeholder="No file" />
									</div>
									<div class="col-md-2 col-sm-2 col-xs-2">
										<input type="submit" value="Upload" form="fileForm"
											class="btn btn-success" />
									</div>


								</form>
							</div>
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
													<td style="display: none">${file.id}</td>
													<td>${file.name}</td>
													<%-- 	<td>${file.type }</td> --%>
													<%-- 			<td>${doc.name}</td>
											<td>${doc.type}</td>
											<td>${doc.description}</td> --%>
													<td><a
														href="<c:url value='/download/attachment/${file.id}' />"
														class="btn btn-success custom-width">download</a></td>
													<td><a
														href="<c:url value='/edit/delete/attachment/${project.id}/${file.id}' />"
														class="btn btn-danger custom-width">delete</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>


							</div>

						</div>


						<div class="form-group">
							<label class="control-label col-sm-2" for="success">Success
								Criteria:</label>
							<div class="col-md-8 col-sm-10">
								<div class="col-sm-5 col-lg-4" style="padding: 0">
									<form:select path="successCriteria" id="amountSelect"
										class="form-control" form="projectForm">
										<form:option value="Number of Participants"></form:option>
										<form:option value="Total Amount Pledged"></form:option>
									</form:select>
								</div>
								<div class="col-sm-1" style="width: 20px">
									<div class="input-group" id="successInput"></div>
								</div>
								<div class="col-sm-4" style="padding-left: 0">
									<form:input id="changePlaceholder" type="text"
										class="form-control" placeholder="Number of Participants"
										path="inputValue" form="projectForm" value="${project.inputValue }"/>
								</div>



							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-9 col-sm-10 col-lg-8"
								style="padding: 20px 0">
								<button type="submit" class="btn btn-lg btn-success"
									form="projectForm" id="projectSubmit">Update</button>
							</div>
						</div>
					</form:form>

					<script>
						/*  function uploadJqueryForm(){
						    $('#result').html('');
						 
						   $("#form2").ajaxForm({
						    success:function(data) { 
						          $('#result').html(data);
						     },
						     dataType:"text"
						   }).submit();
						}  */

						/* function uploadFormData() {
							$('#result').html('');

							var oMyForm = new FormData();
							oMyForm.append("file", file2.files[0]);

							$.ajax({
								url : '${pageContext.request.contextPath}/upload',
								data : oMyForm,
								dataType : 'text',
								processData : false,
								contentType : false,
								type : 'POST',
								success : function(data) {
									$('#result').html(data);
								}
							});
						} */
					</script>
					<script>
						/*  $(document).ready(function(){
						 $("#fileSubmit").click(function(){
						 saveMedia();
						 });
						 function saveMedia() {
						 var formData = new FormData();
						 formData.append('file', $('input[type=file]')[0].files[0]);
						 console.log("form data " + formData);
						 $.ajax({
						 url : '${pageContext.request.contextPath}/uploadFile',
						 data : formData,
						 processData : false,
						 contentType : false,
						 type : 'POST',
						 success : function(data) {
						 alert(data);
						 },
						 error : function(err) {
						 alert(err);
						 }
						 });
						 } */
					</script>
				</div>
				<div id="contacts" class="tab-pane fade">
					<h3>Contacts</h3>
					<c:if test="${!empty contactList}">
						<table style="width: 400px" class="table table-striped">
							<thead>
								<tr>
									<th>Id</th>
									<th>User_Email</th>
									<th>First_Name</th>
									<th>Last_Name</th>
									<th>Contact_Email</th>
								</tr>
							</thead>
							<c:forEach items="${contactList}" var="contact">
								<tr>
									<td>${contact.id }</td>
									<td>${contact.user_email}</td>
									<td>${contact.first_name}</td>
									<td>${contact.last_name}</td>
									<td>${contact.participant_email}</td>
									<%-- <td><a id="edit_contact"
								href="<c:url value='/edit/${contact.participant_email}'/>">Edit</a></td> --%>
									<!-- <td><a id="edit_contact" href="#">Edit</a></td> -->
									<td><a
										href="<c:url value='/project/contact/edit/${user_id}/${contact.id}'/>">Edit</a></td>
									<td><a
										href="<c:url value='/project/contact/remove/${user_id}/${contact.id}'/>">Delete</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<%-- <form action="email" method="POST">
					<input type="submit" />
				</form> --%>
					<!-- <button id="button" type="button" class="btn btn-success"
					onclick="location.href='email'">Send Invitation</button> -->
					<div class="row">
						<div class="col-sm-2">
							<a href="<c:url value='/project/contact/add/${user_id}'/>"
								class="btn btn-primary" role="button">New Contact</a>
						</div>
						<div class="col-sm-2">
							<button id="button" type="button" class="btn btn-success">Send
								Invitation</button>
						</div>

					</div>
				</div>
				<script>
					$("#button").click(

					function() {
						$.post("${pageContext.request.contextPath}/email", {
							"FirstName" : "email",
							"email" : "email"
						}, function(data, status) {

							alert("Data: " + data + "\nStatus: " + status);
						});
					});
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
		<div id="push"></div>
	</div>
	<div id="bottom">
		<div id="footer">
			<footer>
				<p>Copyright 2015, All Rights Reserved</p>
			</footer>

		</div>
	</div>

</body>
</html>