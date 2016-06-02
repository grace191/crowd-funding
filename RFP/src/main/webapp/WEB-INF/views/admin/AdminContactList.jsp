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

<title>Contacts</title>

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
	<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="<c:url value='/resources/css/dialog.css'/>"
	type="text/css">
</head>

<body class="nav-md">

	<div class="container body">

		<div class="main_container">
			
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="index.html" class="site_title"><i class="fa fa-paw"></i>
							<span>Gentellela Alela!</span></a>
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
			<div class="right_col" role="main">
			<div id="dialog-overlay"></div>
			 <div id="dialog-box">
				<div class="dialog-content">
					<div id="dialog-message"></div>
					<a href="#" class="button" id="loadContacts">OK</a>
				</div>
			</div> 
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>
								Contacts
							</h3>
						</div>

						
					</div>

					<div class="clearfix"></div>

					<div class="row">




						<div class="clearfix"></div>


						<div class="clearfix"></div>

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									
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

									

									<div>
										<div class="form-group">

											<div class="mbr-buttons mbr-buttons--right"
												style="margin-bottom: 30px">

												<a class="mbr-buttons__btn btn btn-sm btn-success"
													data-toggle="collapse" data-target="#contact"
													onClick="changeText()">ADD A CONTACT</a>
											</div>

										</div>

										<div id="contact" class="collapse">
											<div data-form-alert="true"></div>
											<c:url var="addAccount" value="/admin/account/add" />
											<c:url var="addInvitation"
												value="/project/invitation/add/${projectId}" />
											<form:form method="post" modelAttribute="invitation"
												action="${addInvitation}">


												<div class="form-group">

													<div class="row">
														<div style="display: none">
															<input id="contactId" type="text" />
														</div>
														<div class="col-sm-3">
															<input name="First Name" type="text" class="form-control"
																id="FirstName" placeholder="FirstName*" required />
														</div>
														<div class="col-sm-3">
															<input name="Last Name" type="text" class="form-control"
																id="LastName" placeholder="LastName*" required />
														</div>
														<div class="col-sm-4">
															<input name="email" type="email" class="form-control"
																id="Email" placeholder="Email*" required />
														</div>
														<div class="col-sm-2">
															<a class="mbr-buttons__btn btn btn-sm btn-success"
																id="AddContact">ADD</a>
														</div>
													</div>

												</div>



											</form:form>
										</div>
									</div>
									<div id="target" class="table-responsive">

										<c:if test="${!empty contactList}">
											<table class="table table-striped jambo_table bulk_action">
												<thead>
													<tr>
														<th style="display: none">Id</th>
														<th class="column-title">First_Name</th>
														<th class="column-title">Last_Name</th>
														<th class="column-title">Contact_Email</th>
														<th class="column-title">Edit</th>
														<th class="column-title">Delete</th>
													</tr>
												</thead>
												<c:forEach items="${contactList}" var="contact">
													<tr class="headings">
														<td style="display: none">${contact.contactId }</td>
														<%-- 												<td>${contact.user_email}</td>
 --%>
														<td class="a-center ">${contact.first_name}</td>
														<td class="a-center ">${contact.last_name}</td>
														<td class="a-center ">${contact.participant_email}</td>
														<%-- <td><a id="edit_contact"
								href="<c:url value='/edit/${contact.participant_email}'/>">Edit</a></td> --%>
														<!-- <td><a id="edit_contact" href="#">Edit</a></td> -->
														<td>
															<%-- <a class="mbr-buttons__btn btn btn-sm btn-info"
													href="<c:url value='/project/contact/edit/${user_id}/${contact.id}'/>">Edit</a> --%>
															<input class="mbr-buttons__btn btn btn-sm btn-info"
															data-toggle="collapse" data-target="#contact"
															type="button"
															onClick="editContact('${contact.contactId}')"
															value="Edit" />
														</td>
														<td class="a-center "><a
															class="mbr-buttons__btn btn btn-sm btn-danger"
															href="<c:url value='/project/invitation/contact/remove/${user_id}/${contact.contactId}'/>">Delete</a></td>
													</tr>
												</c:forEach>
											</table>
										</c:if>
									</div>
									<%-- <div class="table-responsive">
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
																	href="<c:url value='/admin/contacts/${account.key.id}'/>">${account.value["projects"]}</a></td>
																<td><a
																	href="<c:url value='/admin/contacts/${account.key.id}'/>">${account.value["contacts"]}</a></td>
																<td class=" last"><a href="#">Edit</a></td>
																<td class=" last"><a href="#">Delete</a></td>
																<form action="${contacts }" method="post">
												<a
													href="/admin/contacts/${account.key.email}">${account.value}</a>
											</form></td>
															</tr>
														</tbody>
													</c:forEach>


												</tbody>
											</table>
										</div> --%>
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
					Gentelella - Bootstrap Admin Template by <a
						href="https://colorlib.com">Colorlib</a>
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
	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							$('#AddContact')
									.click(

											function() {
												$("#ContactId").val();
												$("#FirstName").val();
												$("#LastName").val();
												$("#Email").val();

												if ($("#AddContact").text() === "ADD") {
													var firstname = $(
															"#FirstName").val();
													var lastname = $(
															"#LastName").val();
													var email = $("#Email")
															.val();
													if (firstname && lastname
															&& email) {

														$
																.ajax(
																		{
																			type : "POST",
																			url : "<c:url value='/admin/contact/new/${user_id}'/>",
																			data : {
																				FirstName : firstname,
																				LastName : lastname,
																				Email : email
																			}

																		})
																.done(
																		function(
																				name) {
																			popup(name);
																		});
													}
												} else if ($("#AddContact")
														.text() === "UPDATE") {
													var contactId = $(
															"#contactId").val();
													console.log("contactid "
															+ contactId);
													var firstname = $(
															"#FirstName").val();
													var lastname = $(
															"#LastName").val();
													var email = $("#Email")
															.val();
													if (firstname && lastname
															&& email) {

														$
																.ajax(
																		{
																			type : "POST",
																			url : "<c:url value='/admin/contact/edit/${user_id}'/>",
																			data : {
																				ContactId : contactId,
																				FirstName : firstname,
																				LastName : lastname,
																				Email : email
																			}

																		})
																.done(
																		function(
																				name) {
																			popup(name);
																		});
													}

												}

											})

							function build(target, data) {
								var head = '', cols = '';
								var columns = [ "First Name", "Last Name",
										"Email", "Edit", "Delete" ]
								for (j = 0; j < data.length; j++) {

									var rows = '';
									for (i = 0; i < columns.length - 2; i++) {

										rows += '<td class="a-center ">'
												+ data[j].contact[i + 1]
												+ '</td>';

									}
									var id = data[j].contact[0];
									rows += '<td class="a-center ">'
											+ '<input class="mbr-buttons__btn btn btn-sm btn-info" type="button" data-toggle="collapse" data-target="#contact" onClick="editContact(\''
											+ id
											+ '\')" value="Edit"/>'
											+ '</td>'
											+ '<td class="a-center ">'
											+ '<input class="mbr-buttons__btn btn btn-sm btn-danger" type="button" onClick="deleteContact(\''
											+ id + '\')" value="Delete"/>'
											+ '</td>';
									cols += '<tr>' + rows + '</tr>';
								}
								for (i = 0; i < columns.length; i++) {
									head += '<th class="column-title">'
											+ columns[i] + '</th>';
								}
								$("#target").empty();
								$("#target").html(
										'<table class="table table-striped jambo_table bulk_action">'
												+ '<thead>' + head + '</thead>'
												+ '<tbody>' + cols + '</tbody>'
												+ '</table>');
							}

							function viewContacts() {
								$
										.get(
												"<c:url value='/admin/viewContacts/${user_id}'/>",
												function(data) {
													if (data.contacts.length !== 0) {
														build(target,
																data.contacts);
													} else {
														$("#AddContact").text(
																"ADD");
														/* 														alert("empty");
														 */$("#target").empty();
													}

												});
							}

							$("#ViewContacts").click(function() {
								viewContacts();
							})

							$("#loadContacts").click(function() {
								viewContacts();
							})
						})
		function deleteContact(id) {
			console
					.log("<c:url value='/admin/delete/Contact/"+id+"'/>");
			/* 			alert(id);
			 */$
					.get(
							"<c:url value='/admin/delete/Contact/"+id+"'/>",
							function(data) {
								/* 						alert(data);
								 */popup(data);
								/* 	$.get("<c:url value='/invitation/viewContacts'/>", function(data) {
									alert(target.html());
									build(target, data.contacts);

								});  */
							});

		}

		function editContact(id) {
			$("#AddContact").text("UPDATE");

			$.get("<c:url value='/project/restEditContact/"+id+"'/>", function(
					data) {
				console.log(data.contactId);
				$("#contactId").val(data.contactId);
				$("#FirstName").val(data.FirstName);
				$("#LastName").val(data.LastName);
				$("#Email").val(data.PEmail);

			});

		}
	</script>

	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="<c:url value='/resources/js/alert_jquery.js'/>"></script>
</body>
</html>