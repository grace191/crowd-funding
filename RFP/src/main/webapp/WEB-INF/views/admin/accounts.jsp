<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>All Users</title>
<link href="<c:url value='/resources/assets/images/GroupFundLegal Logo-fiv.png'/>"
rel="shortcut icon" >
<!-- Bootstrap -->
<link
	href="<c:url value='/resources/vendors/bootstrap/dist/css/bootstrap.min.css'/>"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="<c:url value='/resources/vendors/font-awesome/css/font-awesome.min.css'/>"
	rel="stylesheet">
<!-- iCheck -->
<link
	href="<c:url value='/resources/vendors/iCheck/skins/flat/green.css'/>"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="<c:url value='/resources/css/build/css/custom.min.css'/>"
	rel="stylesheet">
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="index.html" class="site_title"><i class="fa fa-paw"></i>
							<span>Projects</span></a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src="<c:url value='/resources/images/user.png'/>" alt="..."
								class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>${username }</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>General</h3>
							<ul class="nav side-menu">
								<li><a><i class="glyphicon glyphicon-home"></i>
										Dashboard <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="<c:url value='/admin/dashboard'/>">Dashboard</a></li>
									</ul></li>
								<li><a><i class="glyphicon glyphicon-user"></i> User
										Management <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="<c:url value='/admin/CreateAccount'/>">New
												User</a></li>
										<li><a href="<c:url value='/admin/accounts'/>">All
												Users</a></li>
									</ul></li>
								<li><a><i class="glyphicon glyphicon-th-list"></i>
										Project Management <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="<c:url value='/admin/allProjects'/>">Projects</a></li>
										<li><a href="<c:url value='/admin/allInvitations'/>">Invitations</a></li>
									</ul></li>




							</ul>
						</div>
						<div class="menu_section"></div>

					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Settings">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
							<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
							class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Logout">
							<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
						</a>
					</div>
					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>

						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="javascript:;"
								class="user-profile dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"> <img
									src="<c:url value='/resources/images/user.png'/>" alt="">${username }
									<span class=" fa fa-angle-down"></span>
							</a>
								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li><a href="javascript:;"> Profile</a></li>
									<li><a href="javascript:;"> <span
											class="badge bg-red pull-right">50%</span> <span>Settings</span>
									</a></li>
									<li><a href="javascript:;">Help</a></li>
									<li><a href="javascript:formSubmit()"><i
											class="fa fa-sign-out pull-right"></i> Log Out</a></li>
								</ul></li>

							<li role="presentation" class="dropdown"><a
								href="javascript:;" class="dropdown-toggle info-number"
								data-toggle="dropdown" aria-expanded="false"> <i
									class="fa fa-envelope-o"></i> <span class="badge bg-green">6</span>
							</a>
								<ul id="menu1" class="dropdown-menu list-unstyled msg_list"
									role="menu">
									<li><a> <span class="image"><img
												src="images/img.jpg" alt="Profile Image" /></span> <span> <span>John
													Smith</span> <span class="time">3 mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li><a> <span class="image"><img
												src="images/img.jpg" alt="Profile Image" /></span> <span> <span>John
													Smith</span> <span class="time">3 mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li><a> <span class="image"><img
												src="images/img.jpg" alt="Profile Image" /></span> <span> <span>John
													Smith</span> <span class="time">3 mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li><a> <span class="image"><img
												src="images/img.jpg" alt="Profile Image" /></span> <span> <span>John
													Smith</span> <span class="time">3 mins ago</span>
										</span> <span class="message"> Film festivals used to be
												do-or-die moments for movie makers. They were where... </span>
									</a></li>
									<li>
										<div class="text-center">
											<a> <strong>See All Alerts</strong> <i
												class="fa fa-angle-right"></i>
											</a>
										</div>
									</li>
								</ul></li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- top navigation -->
			<!-- page content -->
			
			<div class="right_col" role="main">
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
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>All Users</h3>
						</div>

						<!-- <div class="title_right">
							<div
								class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Search for..."> <span
										class="input-group-btn">
										<button class="btn btn-default" type="button">Go!</button>
									</span>
								</div>
							</div>
						</div> -->
					</div>

					<div class="clearfix"></div>

					<div class="row">




						<div class="clearfix"></div>


						<div class="clearfix"></div>

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<!-- <h2>
										Table design <small>Custom design</small>
									</h2> -->
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i
												class="fa fa-chevron-up"></i></a></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false"><i
												class="fa fa-wrench"></i></a>
											<ul class="dropdown-menu" role="menu">
												<li><a href="#">Settings 1</a></li>
												<li><a href="#">Settings 2</a></li>
											</ul></li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>

								<div class="x_content">

									<%-- <p>
										Add class
										<code>bulk_action</code>
										to table for bulk actions options on row select
									</p> --%>

									<div class="table-responsive">
										<table class="table table-striped jambo_table bulk_action">
											<thead>
												<tr class="headings">
													<!-- <th ><input type="checkbox" id="check-all" class="flat">
													</th> -->
													<th class="column-title" style="display: none">Id</th>
													<th class="column-title">Username</th>
													<th class="column-title">Email</th>
													<th class="column-title">Permission</th>
													<th class="column-title">Projects</th>
													<th class="column-title">Contacts</th>
													<th class="column-title no-link last"><span
														class="nobr">Edit</span></th>
													<th class="column-title no-link last"><span
														class="nobr">Delete</span></th>
													<th class="bulk-actions" colspan="7"><a class="antoo"
														style="color: #fff; font-weight: 500;">Bulk Actions (
															<span class="action-cnt"> </span> ) <i
															class="fa fa-chevron-down"></i>
													</a></th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${accountMap }" var="account">
													<tbody>
														<tr>
															<!-- <td class="a-center "><input type="checkbox"
																class="flat" name="table_records"></td> -->
															<td class="a-center " style="display: none">${account.key.id }</td>
															<td class="a-center ">${account.key.username}</td>
															<td class="a-center ">${account.key.email }</td>
															<td class="a-center ">${account.key.permission }</td>

															<td><a
																href="<c:url value='/admin/projects/${account.key.id}/${account.key.permission  }'/>">${account.value["projects"]}</a></td>
															<td><a
																href="<c:url value='/admin/contacts/${account.key.id}'/>">${account.value["contacts"]}</a></td>
															<td class=" last"><a
																href="<c:url value='/admin/account/edit/${account.key.id}'/>">Edit</a></td>
															<td class=" last"><a href="#">Delete</a></td>
															<%-- <form action="${contacts }" method="post">
												<a
													href="/admin/contacts/${account.key.email}">${account.value}</a>
											</form></td> --%>
														</tr>
													</tbody>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right">
					Copyright (c) 2016 .<a href="http://www.incubatorllc.com/">IncubatorLLC</a>
				</div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>

	<!-- jQuery -->
	<script
		src="<c:url value='/resources/vendors/jquery/dist/jquery.min.js'/>"></script>
	<!-- Bootstrap -->
	<script
		src="<c:url value='/resources/vendors/bootstrap/dist/js/bootstrap.min.js'/>"></script>
	<!-- FastClick -->
	<script
		src="<c:url value='/resources/vendors/fastclick/lib/fastclick.js'/>"></script>
	<!-- NProgress -->
	<script
		src="<c:url value='/resources/vendors/nprogress/nprogress.js'/>"></script>
	<!-- iCheck -->
	<script src="<c:url value='/resources/vendors/iCheck/icheck.min.js'/>"></script>

	<!-- Custom Theme Scripts -->
	<script src="<c:url value='/resources/js/build/js/custom.min.js'/>"></script>
</body>
</html>