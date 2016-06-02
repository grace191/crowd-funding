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
<link rel="shortcut icon"
	href="<c:url value='/resources/assets/images/discover-mobile-350x350-16.png'/>"
	type="image/x-icon">
<meta name="description" content="bootstrap carousel">
<title>Start a Project</title>
<link
	href="<c:url value='/resources/assets/images/GroupFundLegal Logo-fiv.png'/>"
	rel="shortcut icon">
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
											<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="<c:url value='/project/invitations'/>">Invitations</a></li>
									</sec:authorize>
									<sec:authorize
										access="hasAnyRole('ROLE_PCREATOR','ROLE_PADMIN')">
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="<c:url value='/project/projects'/>">Projects</a></li>
										<li class="mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="<c:url value='/project/contacts'/>">Contacts</a></li>
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





	<%-- <form id="fileForm" method="post" enctype="multipart/form-data"></form>
	<form:form id="projectForm" method="post" modelAttribute="project"></form:form>
	<form id="imageForm" method="post" enctype="multipart/form-data"></form> --%>
	<section
		class="mbr-section mbr-section--relative mbr-section--fixed-size gr-section-margin"
		id="form1-35" style="background-color: rgb(239, 239, 239);">

		<div
			class="mbr-section__container mbr-section__container--std-padding container">
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<div class="col-sm-12 col-md-12 col-lg-122"
							data-form-type="formoid">
							<div
								class="mbr-header mbr-header--center mbr-header--std-padding">
								<h2 class="mbr-header__text">START A PROJECT</h2>
							</div>
							<div data-form-alert="true"></div>
							<c:url var="addAction" value="/project/add" />
							<form:form action="${addAction}" modelAttribute="project"
								id="projectForm" method="post" class="form-horizontal"
								role="form">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div class="form-group">
									<label class="control-label col-sm-2" for="Project title">Title:</label>
									<div class="col-md-10 col-sm-10">
										<form:input class="form-control" path="title" type="text"
											placeholder="Project Title" form="projectForm"
											id="title_input" value="${project.title }" />
										<font color="red"><form:errors path="title" /></font>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="Image">Project
										Image:</label>
									<div class="col-sm-10 col-md-10">
										<%-- 								<form method="post" action="${uploadImage }"
 --%>
										<form method="post" enctype="multipart/form-data"
											id="imageForm" name="imageForm">
											<input type="hidden" name="project_title" id="project_title"
												value="hidden" />
											<div class="row">

												<div class="col-md-4 col-md-4 col-xs-4 mbr-buttons">
													<input name="imageUpload" id="imageUpload" type="file"
														form="imageForm" class="filestyle"
														data-buttonName="btn-primary"
														data-classButton="btn btn-primary" data-input="false"
														data-classIcon="icon-plus" data-size="nr"
														data-buttonText="Choose an image"
														data-placeholder="No file" style="width: 50%" />
												</div>
												<div class="col-md-4 col-md-4 col-xs-4 mbr-buttons">
													<button value="Submit" form="imageForm"
														onclick="uploadImage()"
														class="btn btn-success uploadImage">upload</button>
												</div>

												<%-- 	<c:if test="${!empty imageFileList }">
													<c:forEach items="${imageFileList }" var="image"> --%>
												<div class="col-md-4 col-md-4 col-xs-4 mbr-buttons">
													<!-- <button value="Delete" form="imageForm" class="btn btn-danger">Delete</button> -->
													<input type="button" onClick="deleteImage()"
														class="btn btn-danger custom-width" value="delete"></input>
												</div>
												<%-- </c:forEach>
												</c:if> --%>

												<!-- 	<div style="display:none" class="col-md-4 col-md-4 col-xs-4 mbr-buttons" id="addDelete">
															<button value="Delete" form="imageForm" class="btn btn-danger">Delete</button>
															<input type="button" onClick="deleteImage()"
																class="btn btn-danger custom-width" value="delete"></input>
														</div> -->
											</div>
											<div class="row secondRow">
												<div id="result" class="col-md-12 col-xs-12">

													<c:if test="${!empty imagePath }">
														<img src="<c:url value='/${imagePath}'/>"
															class="col-md-12 col-md-xs" />
													</c:if>
												</div>
											</div>

										</form>
									</div>


								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="Executive Summary">Executive
										Summary:</label>
									<div class="col-md-10 col-sm-10">
										<form:textarea class="form-control" path="summary"
											placeholder="Executive Summary" name="summary" id="summary"
											form="projectForm" value="${project.summary }" />
										<font color="red"><form:errors path="summary" /></font>
									</div>

								</div>
								<div class="form-group">
									<div class="col-sm-2 col-md-offset-2">
										<button type="button" id="previewSummary"
											class="btn btn-primary previewButton">Preview</button>
									</div>
								</div>
								<div class="form-group">
									<div id="summaryData"
										class="col-md-10 col-sm-10 col-md-offset-2"></div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="Description">Description:</label>
									<div class="col-md-10 col-sm-10">
										<form:textarea class="form-control" path="description"
											placeholder="Description" name="description" id="description"
											form="projectForm" value="${project.description }" />

										<font color="red"><form:errors path="description" /></font>
									</div>
								</div>
								<div class="col-sm-2 col-md-offset-2">
									<button type="button" id="previewDescription"
										class="btn btn-primary previewButton">Preview</button>
								</div>
								<div class="form-group">
									<div id="descriptionData"
										class="col-md-10 col-sm-10 col-md-offset-2"></div>
								</div>



								<div class="form-group">
									<label class="control-label col-sm-2" for="document">Project
										Attachments:</label>

									<div class="col-sm-10 col-md-10">
										<form enctype="multipart/form-data" id="fileForm"
											name="fileForm">
											<div class="row">
												<div class="col-md-6 col-sm-6 col-xs-6">
													<input type="file" name="fileUpload" form="fileForm"
														id="fileUpload" class="filestyle"
														data-buttonName="btn-primary"
														data-classButton="btn btn-primary" data-input="false"
														data-classIcon="icon-plus" data-size="nr"
														data-buttonText="Choose your attachment"
														data-placeholder="No file" />
												</div>
												<div class="col-md-6 col-sm-6 col-xs-6">
													<button id="attachment" form="fileForm"
														class="btn btn-success uploadImage">upload</button>
													<!-- 												 <input form="fileForm" type="button" id="uploadAttachment" name="attachmentUpload" value="upload" onClick="uploadAttachment()" class="btn btn-success" /> 
 -->
												</div>
											</div>



										</form>
									</div>

								</div>
								<div class="form-group">
									<div class="col-md-10 col-md-offset-2 col-sm-10">
										<div class="tablecontainer">
											<div id="attachments">
												<table class="table table-hover">
													<tbody>
														<c:forEach items="${filesList}" var="file">
															<tr>
																<td style="display: none">${file.id}</td>
																<td class="col-md-6">${file.name}</td>
																<td class="col-md-3"><a
																	href="<c:url value='/download/attachment/${file.id}' />"
																	class="btn btn-success custom-width">Download</a></td>
																<td class="col-md-3 mbr-buttons mbr-buttons--right">
																	<input type="button"
																	onClick="deleteAttachment('${file.id}')"
																	class="btn btn-danger custom-width" value="Delete"></input>
																	<%-- <a
																	href="#"
																	class="btn btn-danger custom-width" onClick="deleteImage(${file.id})">delete</a> --%>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>


									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="success">Success
										Criteria:</label>
									<div class="col-md-10 col-sm-10">
										<div class="col-sm-5 col-lg-5 leftPadding">
											<form:select path="successCriteria" id="amountSelect"
												class="form-control" form="projectForm">
												<form:option value="Number of Participants"></form:option>
												<form:option value="Total Amount Pledged"></form:option>
											</form:select>
										</div>
										<div class="col-sm-1" style="width: 20px">
											<div class="input-group" id="successInput"></div>
										</div>
										<div class="col-sm-5">
											<form:input id="changePlaceholder" type="text"
												class="form-control" placeholder="Number of Participants"
												path="inputValue" form="projectForm"
												value="${project.inputValue }" />
										</div>



									</div>
								</div>
								<div class="form-group">
									<div id="insideDiv"
										class="col-sm-12 col-lg-12 mbr-buttons mbr-buttons--right">
										<button type="submit"
											class="mbr-buttons__btn btn btn-lg btn-success"
											form="projectForm" id="projectSubmit">Create</button>
									</div>
								</div>
							</form:form>


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
	<script
		src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/ckeditor/plugins/wordcount/plugin.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/localstorage.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js">
		
	</script>
	<script src="http://malsup.github.com/jquery.form.js"></script>
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
												$('#summaryData')
														.html(contents);

											});

							$('#previewDescription')
									.click(
											function() {
												var dContents = CKEDITOR.instances.description
														.getData();
												$('#descriptionData').html(
														dContents);

											});

							$('#projectSubmmit')
									.click(
											function() {
												var contents = CKEDITOR.instances.summary
														.getData();
												var dContents = CKEDITOR.instances.description
														.getData();
												$('#summary').val(contents);
												$('#description')
														.val(dContents);
											});

							$('#attachment')
									.click(
											function(e) {
												e.preventDefault();
												$('#attachments')
														.html(
																"<img src='<c:url value="/resources/images/loader1.gif"/>' class='col-md-4, col-sm-6'/>");

												var attachmentForm = new FormData();
												attachmentForm.append("file",
														fileUpload.files[0]);

												$
														.ajax({
															url : '<c:url value="/project/upload/attachment"/>',
															data : attachmentForm,
															dataType : 'text',
															processData : false,
															contentType : false,
															type : 'POST',
															success : function(
																	data) {
																$('.badge')
																		.html(
																				'');
																$(
																		'#attachments')
																		.html(
																				'');
																var obj = JSON
																		.parse(data);
																console
																		.log(obj.files[0]['filename']);
																build(
																		attachments,
																		obj.files);

																/* 	$(
																			'#attachments')
																			.html(
																					data); */
															}
														});
											});

							//build table
							function build(attachments, data) {
								var head = '', cols = '';
								for (j = 0; j < data.length; j++) {
									var rows = '';
									for (i = 1; i < 2; i++) {

										rows += '<td class="col-md-6">'
												+ data[j]['filename'] + '</td>';

									}
									var id = data[j]['fileId'];
									/* 				var link = "<c:url value='/download/attachment/"+id"'/>";
									 */rows += '<td class="col-md-3">'
											+ '<input type="button" onClick="downloadAttachment(\''
											+ id
											+ '\')" value="Download" class="btn btn-success custom-width"/>'
											+ '</td>'
											+ '<td class="col-md-3">'
											+ '<input type="button" onClick="deleteAttachment(\''
											+ id
											+ '\')" value="Delete" class="btn btn-success custom-width"/>'
											+ '</td>';
									cols += '<tr>' + rows + '</tr>';
								}

								$("#attachments").empty();
								$("#attachments").html(
										'<table class="table table-hover">'
												+ '<tbody>' + cols + '</tbody>'
												+ '</table>');
							}

						});

		/* onclick event to delete image */
		function deleteImage() {
			$('#result')
					.html(
							"<img src='<c:url value="/resources/images/loader1.gif"/>' class='col-md-4, col-sm-6'/>");
			$.get("<c:url value='/delete/image' />", function(data) {
				$('#result').html('');

			});
		}
	</script>
	<script>
		/* onclick event to upload image */
		function uploadImage() {

			$('#result')
					.html(
							"<img src='<c:url value="/resources/images/loader1.gif"/>' class='col-md-4, col-sm-6'/>");

			var oMyForm = new FormData();
			oMyForm.append("file", imageUpload.files[0]);

			$
					.ajax({
						url : '${pageContext.request.contextPath}/project/upload/image',
						data : oMyForm,
						dataType : 'text',
						processData : false,
						contentType : false,
						type : 'POST',
						success : function(data) {
							$('.badge').html('');
							$('#result').html(data);
							$("#addDelete").removeAttr("style");
						}
					});
		}

		function deleteAttachment(id) {
			$('#attachments')
					.html(
							"<img src='<c:url value="/resources/images/loader1.gif"/>' class='col-md-4, col-sm-6'/>");

			$
					.get(
							"${pageContext.request.contextPath}/delete/attachment/"
									+ id,

							function(data) {
								$('#attachments').html('');
								console.log(data.files);
								/* var obj = JSON.parse(data);
								console.log(data.files[0]['fileId']);*/

								var head = '', cols = '';
								for (j = 0; j < data.files.length; j++) {
									var rows = '';
									for (i = 1; i < 2; i++) {

										rows += '<td class="col-md-6">'
												+ data.files[j]['filename']
												+ '</td>';

									}
									var id = data.files[j]['fileId'];
									/* 				var link = "<c:url value='/download/attachment/"+id"'/>";
									 */rows += '<td class="col-md-3">'
											+ '<input type="button" onClick="downloadAttachment(\''
											+ id
											+ '\')" value="Download" class="btn btn-success custom-width"/>'
											+ '</td>'
											+ '<td class="col-md-3">'
											+ '<input type="button" onClick="deleteAttachment(\''
											+ id
											+ '\')" value="Delete" class="btn btn-success custom-width"/>'
											+ '</td>';
									cols += '<tr>' + rows + '</tr>';
								}

								$("#attachments").empty();
								$("#attachments").html(
										'<table class="table table-hover">'
												+ '<tbody>' + cols + '</tbody>'
												+ '</table>');

							});

		}

		function downloadAttachment(id) {
			window.location.href = "<c:url value='/download/attachment/"+id+"'/>";
			/* console
					.log("<c:url value='/download/attachment/"+id+"'/>"); */

		}
	</script>


</body>
</html>