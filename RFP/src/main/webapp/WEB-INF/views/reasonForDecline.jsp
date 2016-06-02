<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="<c:url value='/resources/css/dialog.css'/>"
	type="text/css">
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
			<section id="title-bar">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h2 class="text-center">Your feedback is important to us!</h2>
						</div>
					</div>
				</div>
			</section>
		</div>
		<div id="middle">

			<section id="contact" style="margin-top: 60px">
				<div id="dialog-overlay"></div>
				<div id="dialog-box">
					<div class="dialog-content">
						<div id="dialog-message"></div>
						<a href="#" class="button" id="Exit">Exit</a>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
							<c:url var="decline" value="/offer/decline" />
							<%-- 							<form method="post" action="${decline}">
 --%>
							<form>
								<div class="form-group">
									<label>Tell us why you decline this offer</label>
									<textarea id="feedback" name="feedback" class="form-control"
										placeholder="We value your feedback." rows="8" /></textarea>
								</div>
								<div class="row" style="margin-top: 30px; margin-left: 100px">
									<div class="col-md-1 col-sm-1 col-md-offset-7"
										style="margin-right: 0">
										<button type="button" class="btn btn-success"
											onClick="goBack()">Go Back</button>
									</div>
									<div class="col-md-1 col-sm-1 col-md-offset-2">
										<button type="button" class="btn btn-danger"
											id="reasonForDecline">Exit</button>
									</div>
								</div>

								<script>
									function goBack() {
										window.history.back();
									}
								</script>


							</form>
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
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<script src="<c:url value='/resources/js/alert_jquery.js'/>"></script>
		<script>
			$(document)
					.ready(
							function() {
								$('#reasonForDecline')
										.click(
												function() {
													var text = $("#feedback")
															.val();
													$
															.ajax(
																	{
																		type : "POST",
																		url : "<c:url value='/project/decline/feedback/${projectId}/${contactId}'/>",
																		data : {
																			feedback : text
																		}

																	})
															.done(
																	function(
																			data) {
																		popup(data);
																	});

												})

								$('#Exit')
										.click(
												function() {
													window.location.href = "<c:url value='/'/>";
													/* var text = $("#feedback").val();
													$.ajax({
														type : "POST",
														url : "<c:url value='/accept/register/${projectId}'/>",
														data : {
															feedback : text
														}

													}).done(function(data) {
														popup(data);
													});
													 */
												})
							})
		</script>

	</div>
</body>
</html>