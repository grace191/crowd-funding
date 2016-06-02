<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CrowdFunding Platform</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>

<body>
	<div class="navbar navbar-default">
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
				<li class="active"><a href="index.html">Dashboard</a></li>
				<li><a href="pages.html">Pages</a></li>
				<li><a href="categories.html">Categories</a></li>
				<li><a href="users.html">Users</a></li>
			</ul>
			<form class="navbar-form navbar-left">
				<input type="text" class="form-control col-lg-8"
					placeholder="Search">
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">My Account <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">Profile</a></li>
					</ul></li>
				<li><a href="login.html">Logout</a></li>
			</ul>
		</div>
	</div>

	<section>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="list-group">
						<a href="#" class="list-group-item active"> <i
							class="glyphicon glyphicon-dashboard"></i> Dashboard
						</a> <a href="#" class="list-group-item"><i
							class="glyphicon glyphicon-file"></i> Pages</a> <a href="#"
							class="list-group-item"><i
							class="glyphicon glyphicon-folder-open"></i> Categories</a> <a
							href="#" class="list-group-item"><i
							class="glyphicon glyphicon-user"></i> User Accounts</a>
					</div>
				</div>
				<div class="col-md-8">
					<h1 class="page-header">
						<i class="glyphicon glyphicon-dashboard"></i> Dashboard
					</h1>
					<h3>Latest Pages</h3>
					<table class="table table-striped">
						<tr>
							<th>Page Title</th>
							<th>Category</th>
							<th>Author</th>
						</tr>
						<tr>
							<td><a href="page.html">Sample Page One</a></td>
							<td>Category One</td>
							<td>John Doe</td>
						</tr>
						<tr>
							<td><a href="page.html">Sample Page Two</a></td>
							<td>Category Two</td>
							<td>John Doe</td>
						</tr>
						<tr>
							<td><a href="page.html">Sample Page Three</a></td>
							<td>Category Two</td>
							<td>Brad Traversy</td>
						</tr>
						<tr>
							<td><a href="page.html">Sample Page Four</a></td>
							<td>Category One</td>
							<td>John Doe</td>
						</tr>
						<tr>
							<td><a href="page.html">Sample Page Five</a></td>
							<td>Category Three</td>
							<td>James Smith</td>
						</tr>
					</table>
					<a class="btn btn-default" href="pages.html">View All Pages</a>
					<hr>

					<h3>Latest Users</h3>
					<table class="table table-striped">
						<tr>
							<th>Name</th>
							<th>Email</th>
							<th>Group</th>
						</tr>
						<tr>
							<td><a href="user.html">Brad Traversy</a></td>
							<td>techguyinfo@gmail.com</td>
							<td>Admin</td>
						</tr>
						<tr>
							<td><a href="user.html">John Doe</a></td>
							<td>jdoe@gmail.com</td>
							<td>Admin</td>
						</tr>
						<tr>
							<td><a href="user.html">James Smith</a></td>
							<td>jamessmith@gmail.com</td>
							<td>Registered</td>
						</tr>
						<tr>
							<td><a href="user.html">Lisa Gibbons</a></td>
							<td>lgibbons@gmail.com</td>
							<td>Registered</td>
						</tr>

					</table>
					<a class="btn btn-default" href="users.html">View All Users</a>
				</div>
			</div>
		</div>

	</section>

	<footer>
		<p>Copyright 2015, All Rights Reserved</p>
	</footer>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>