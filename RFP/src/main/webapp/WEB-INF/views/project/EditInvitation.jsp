<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<!-- Mobirise Free Bootstrap Template, https://mobirise.com -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="generator" content="Mobirise v2.6.1, mobirise.com">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value='/resources/assets/images/GroupFundLegal Logo-fiv.png'/>"
rel="shortcut icon" >
<meta name="description" content="bootstrap carousel">
<title>Manage Invitation</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/bootstrap/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/animate.css/animate.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/socicon/css/socicon.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/mobirise/css/style.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/mobirise-slider/style.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/mobirise-gallery/style.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/mobirise/css/mbr-additional.css'/>"
	type="text/css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/mystyle.css'/>" type="text/css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="<c:url value='/resources/css/dialog.css'/>"
	type="text/css">
</head>
<body data-spy="scroll" data-target="#menu-20" data-offset="70">

	<section
		class="mbr-navbar mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--sticky mbr-navbar--auto-collapse"
		id="menu-20">
		<div class="mbr-navbar__section mbr-section">
			<div class="mbr-section__container container">
				<div class="mbr-navbar__container">
					<div
						class="mbr-navbar__column mbr-navbar__column--s mbr-navbar__brand">
						<span class="mbr-navbar__brand-link mbr-brand mbr-brand--inline">
							<span class="mbr-brand__logo"><a href="#"><img
									class="mbr-navbar__brand-img 
mbr-brand__img"
									src="<c:url value='/resources/assets/images/GroupFundLegal Logo-fiv.png'/>"
									alt="Mobirise"></a></span> <span class="mbr-brand__name"><a
								class="mbr-brand__name text-white" href="#">GroupFundLegal</a></span>
						</span>
					</div>
					<div class="mbr-navbar__hamburger mbr-hamburger text-white">
						<span class="mbr-hamburger__line"></span>
					</div>
					<div class="mbr-navbar__column mbr-navbar__menu">
						<nav
							class="mbr-navbar__menu-box mbr-navbar__menu-box--inline-right">
							<div class="mbr-navbar__column">
								<ul
									class="mbr-navbar__items mbr-navbar__items--right mbr-buttons 
mbr-buttons--freeze mbr-buttons--right btn-decorator 
mbr-buttons--active">
									<sec:authorize access="isAnonymous()">
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white" href="#features1-21">About</a></li>
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white" href="#header1-22">Learn
												More</a></li>
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn 
text-white"
											href="#features1-31">Features</a></li>
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="#testimonials1-34">Reviews</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li class="dropdown mbr-navbar__item"><a href="#"
											class="dropdown-toggle mbr-buttons__link btn text-white"
											data-toggle="dropdown">Accounts<b class="caret"></b></a>
											<ul class="dropdown-menu">
												<li class="mbr-navbar__item"><a
													class="mbr-buttons__link btn text-white"
													href="<c:url value='/admin/CreateAccount'/>">New User</a></li>
												<c:url value="/admin/accounts" var="url" />
												<li class="mbr-navbar__item"><a
													class="mbr-buttons__link btn text-white" href="${url}">All
														Users</a></li>
											</ul></li>

									</sec:authorize>
									<sec:authorize access="hasAnyRole('ROLE_PCREATOR')">
										<li class="dropdown mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="<c:url value='/project'/>">Start a project</a></li>
									</sec:authorize>
									<sec:authorize
										access="hasAnyRole('ROLE_PCREATOR','ROLE_PADMIN')">
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="<c:url value='/project/projects'/>">Projects</a></li>
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="<c:url value='/project/contacts'/>">Contacts</a></li>
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="<c:url value='/project/invitations'/>">Invitations</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_USER')">
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="<c:url value='/user/projects'/>">Projects</a></li>
									</sec:authorize>
									<%-- 							<sec:authorize
										access="hasAnyRole('ROLE_PCREATOR','ROLE_PADMIN','ROLE_USER')">
										<li class="dropdown mbr-navbar__item"><a class="mbr-buttons__link btn text-white" href="#" class="dropdown-toggle"
											data-toggle="dropdown">My Account <b class="caret"></b></a>
											<ul class="dropdown-menu">
												<sec:authorize
													access="hasAnyRole('ROLE_PCREATOR','ROLE_PADMIN')">
													<li class="mbr-navbar__item"><a class="mbr-buttons__link btn text-white" href="<c:url value='/project/projects'/>">Projects</a></li>
													<li class="mbr-navbar__item"><a class="mbr-buttons__link btn text-white" href="<c:url value='/project/contacts'/>">Contacts</a></li>
												</sec:authorize>
												<sec:authorize access="hasRole('ROLE_USER')">
													<li class="mbr-navbar__item"><a class="mbr-buttons__link btn text-white" href="<c:url value='/user/projects'/>">Projects</a></li>
												</sec:authorize>
											</ul></li>
									</sec:authorize> --%>
									<c:choose>
										<c:when
											test="${pageContext.request.userPrincipal.name != null}">
											<li class="mbr-navbar__item"><a
												class="mbr-buttons__link btn text-white"
												href="javascript:formSubmit()"><span
													class="glyphicon glyphicon-log-out"></span> Logout</a></li>
										</c:when>
										<c:otherwise>
											<li class="mbr-navbar__item"><a
												class="mbr-buttons__link btn text-white" href="register"><span
													class="glyphicon glyphicon-user"></span> Sign Up</a></li>
											<li class="mbr-navbar__item"><a
												class="mbr-buttons__link btn text-white" href="login"><span
													class="glyphicon glyphicon-log-in"></span> Login</a></li>

										</c:otherwise>
									</c:choose>
								</ul>
							</div>
							<!-- <div class="mbr-navbar__column"><ul class="mbr-navbar__items mbr-navbar__items--right mbr-buttons 
mbr-buttons--freeze mbr-buttons--right btn-inverse 
mbr-buttons--active"><li class="mbr-navbar__item"><a class="mbr-buttons__btn btn btn-default" 
href="https://mobirise.com/bootstrap-template/mobirise-free-template.zip">DOWNLOAD</a></li></ul></div>-->
						</nav>
					</div>
				</div>
			</div>
		</div>
	</section>
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






	<section
		class="mbr-section mbr-section--relative mbr-section--fixed-size gr-section-margin"
		id="form1-35" style="background-color: rgb(239, 239, 239);">
		<div id="dialog-overlay"></div>
		<div id="dialog-box">
			<div class="dialog-content">
				<div id="dialog-message"></div>
				<a href="#" class="button" id="loadContacts">OK</a>
			</div>
		</div>
		<div
			class="mbr-section__container mbr-section__container--std-padding container">
			<div class="row">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-8 col-sm-offset-2" data-form-type="formoid">
							<div
								class="mbr-header mbr-header--center mbr-header--std-padding">
								<h2 class="mbr-header__text">Edit Invitation Template</h2>
							</div>
							<div data-form-alert="true"></div>
<%-- 							<c:url var="addAccount" value="/admin/account/add" />
 --%>							<c:url var="addInvitation"
								value="/project/editInvitation/email/${projectId}" />
							<form:form method="post" modelAttribute="invitation"
								action="${addInvitation}">


								<div class="form-group">
									<label>User Email</label>
									<form:input path="sender" name="sender" type="text"
										class="form-control" value="tianshiyezi@gmail.com" />
								</div>
								<div class="form-group">
									<label>Email Subject</label>
									<div>
										<form:input path="emailSubject" name="emailSubject"
											type="text" class="form-control" placeholder="Email Subject*"
											required="required" value="${invitation.emailSubject }" />
										<%-- 										<font color="red"> <form:errors path="email" /></font>
 --%>
									</div>

								</div>
								<div class="form-group">
									<label>Set a Threshold</label>
									<form:input path="threshold" name="threshold" type="text"
										class="form-control" placeholder="Set a Threshold*"
										value="${invitation.threshold }" />
								</div>
								<div class="form-group">
									<label>Include Summary</label>


								</div>
								<div class="form-group">

									<c:if test="${invitation.summary == 'Yes' }">
									Yes
									<form:radiobutton path="summary" value="Yes" checked="checked" />
									No
									<form:radiobutton path="summary" value="No" />
									</c:if>
									<c:if test="${invitation.summary == 'No' }">
									Yes
									<form:radiobutton path="summary" value="Yes" />
									No
									<form:radiobutton path="summary" value="No" checked="checked" />
									</c:if>


								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6 mbr-buttons mbr-buttons--right">
											<!-- 	<a class="mbr-buttons__btn btn btn-lg btn-success" data-toggle="collapse" data-target="#contact"
												id="AddContact">ADD</a> -->
											<a class="mbr-buttons__btn btn btn-sm btn-success"
												data-toggle="collapse" data-target="#contact"
												onClick="changeText()">ADD A CONTACT</a>
										</div>
										<div class="col-sm-6 mbr-buttons mbr-buttons--right">
											<a class="mbr-buttons__btn btn btn-sm btn-success"
												id="viewContacts">VIEW ALL INVITED CONTACTS</a>
										</div>

									</div>
								</div>
								<!-- 								<div id="contact" class="collapse">
 -->
								<div class="form-group collapse" id="contact">

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
										<div class="col-sm-2 mbr-buttons mbr-buttons--right">
											<a class="mbr-buttons__btn btn btn-lg btn-success"
												id="AddContact">ADD</a>
										</div>
									</div>

								</div>
								<!-- 								</div>
 -->
								<div id="target"></div>

								<div class="form-group">
									<div class="row">
										<div class="col-sm-6 mbr-buttons mbr-buttons--right">
											<input type="submit" id="invitation_submit" name="send"
												value="SEND"
												class="mbr-buttons__btn btn btn-lg btn-warning" />
										</div>
										<div class="col-sm-6 mbr-buttons mbr-buttons--right">
											<input type="submit" id="sendToAll" name="send_to_all"
												value="SEND TO ALL"
												class="mbr-buttons__btn btn btn-lg btn-warning" />
										</div>

									</div>


									<!-- 	<div class="mbr-buttons mbr-buttons--right">

										<input type="submit" id="invitation_submit" value="SEND TO ALL"
											class="mbr-buttons__btn btn btn-lg btn-warning" />
									</div> -->

								</div>

							</form:form>
							<!-- <a
								href="javascript:popup('Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi commodo, ipsum sed pharetra gravida, orci magna rhoncus neque, id pulvinar odio lorem non turpis. Nullam sit amet enim. Suspendisse id velit vitae ligula volutpat condimentum. Aliquam erat volutpat.')"></a> -->


						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section
		class="mbr-section mbr-section--relative mbr-section--fixed-size"
		id="contacts2-36" style="background-color: rgb(60, 60, 60);">

		<div class="mbr-section__container container">
			<div class="mbr-contacts mbr-contacts--wysiwyg row">
				<div class="col-sm-6">
					<figure
						class="mbr-figure mbr-figure--wysiwyg mbr-figure--full-width mbr-figure--no-bg">
						<div class="mbr-figure__map mbr-figure__map--short mbr-google-map">
							<p class="mbr-google-map__marker"
								data-coordinates="41.882229, -87.631218"></p>
						</div>
					</figure>
				</div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-5 col-sm-offset-1">
							<p class="mbr-contacts__text">
								<strong>ADDRESS</strong><br> 105 W. Madison 18th Floor
								Chicago, Illinois 60602<br> <br> <strong>CONTACTS</strong><br>
								Email: info@IncubatorLLC.com<br> Phone: (844) 464-6282<br>
							</p>
						</div>
						<div class="col-sm-6">
							<p class="mbr-contacts__text">
								<strong>LINKS</strong>
							</p>
							<ul class="mbr-contacts__list">
								<li><a href="#" class="text-gray">Bootstrap one page
										template</a><a class="mbr-contacts__link text-gray" href="#"></a></li>
								<li><a href="#" class="text-gray">Bootstrap basic
										template</a><a class="mbr-contacts__link text-gray" href="#"></a></li>
								<li><a href="#/" class="text-gray">Bootstrap gallery
										template</a></li>
								<li><a href="#" class="text-gray">Bootstrap responsive
										template</a></li>
								<li><br></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<footer
		class="mbr-section mbr-section--relative mbr-section--fixed-size"
		id="footer1-37" style="background-color: rgb(68, 68, 68);">

		<div class="mbr-section__container container">
			<div class="mbr-footer mbr-footer--wysiwyg row">
				<div class="col-sm-12">
					<p class="mbr-footer__copyright"></p>
					<p>
						Copyright (c) 2016 IncubatorLLC. <a
							href="https://mobirise.com/bootstrap-template/license.txt"
							class="text-gray">License</a>
					</p>
					<p></p>
				</div>
			</div>
		</div>
	</footer>


	<script src="<c:url value='/resources/assets/jquery/jquery.min.js'/>"></script>
	<script
		src="<c:url value='/resources/assets/bootstrap/js/bootstrap.min.js'/>"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC5PE01Rd_jdp72l2MHAL54r41Udi6CDJU"></script>
	<script
		src="<c:url value='/resources/assets/smooth-scroll/SmoothScroll.js'/>"></script>
	<script
		src="<c:url value='/resources/assets/bootstrap-carousel-swipe/bootstrap-carousel-swipe.js'/>"></script>
	<script src="<c:url value='/resources/assets/jarallax/jarallax.js'/>"></script>
	<script
		src="<c:url value='/resources/assets/masonry/masonry.pkgd.min.js'/>"></script>
	<script
		src="<c:url value='/resources/assets/imagesloaded/imagesloaded.pkgd.min.js'/>"></script>
	<script
		src="<c:url value='/resources/assets/social-likes/social-likes.js'/>"></script>
	<script src="<c:url value='/resources/assets/mobirise/js/script.js'/>"></script>
	<script
		src="<c:url value='/resources/assets/mobirise-gallery/script.js'/>"></script>

	<script src="<c:url value='/resources/assets/js/wow.min.js'/>"></script>

	<script>
		new WOW().init();
	</script>

	<script>
		/*  $( "#opener" ).click(function() {
		   $( "#dialog" ).dialog( "open" );
		 }); */

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
																			url : "<c:url value='/project/invitation/addContact'/>",
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
																			url : "<c:url value='/project/restUpdateContact'/>",
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
										"Email", "Status", "Edit", "Delete" ]
								for (j = 0; j < data.length; j++) {

									var rows = '';
									for (i = 0; i < columns.length - 2; i++) {

										rows += '<td>' + data[j].contact[i + 1]
												+ '</td>';

									}
									var id = data[j].contact[0];
									rows += '<td>'
											+ '<input class="mbr-buttons__btn btn btn-sm btn-info" type="button" data-toggle="collapse" data-target="#contact" onClick="editContact(\''
											+ id
											+ '\')" value="Edit"/>'
											+ '</td>'
											+ '<td>'
											+ '<input class="mbr-buttons__btn btn btn-sm btn-danger" type="button" onClick="deleteContact(\''
											+ id + '\')" value="Delete"/>'
											+ '</td>';
									cols += '<tr>' + rows + '</tr>';
								}
								for (i = 0; i < columns.length; i++) {
									head += '<th>' + columns[i] + '</th>';
								}
								$("#target").empty();
								$("#target").html(
										'<table class="table table-hover table-mc-light-blue">'
												+ '<thead>' + head + '</thead>'
												+ '<tbody>' + cols + '</tbody>'
												+ '</table>');
							}

							function viewContacts() {
								$
										.get(
												"<c:url value='/invitation/viewInvitedContacts/${projectId}'/>",
												function(data) {
													if (data.contacts.length !== 0) {
														build(target,
																data.contacts);
													} else {
														alert("empty");
														$("#target").empty();
													}

												});
							}

							$("#viewContacts").click(function() {
								viewContacts();
							})

							$("#loadContacts").click(function() {
								viewContacts();
							})
						})
	</script>
	<script type="text/javascript">
		function deleteContact(id) {
			console.log("<c:url value='/project/invitation/deleteContact/"+id+"'/>");
			/* 			alert(id);
			 */$.get("<c:url value='/project/invitation/deleteContact/"+id+"'/>",
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
/* 				console.log(data.contactId);
 */				$("#contactId").val(data.contactId);
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