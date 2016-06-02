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
<title>Decline Offer</title>
<link href="<c:url value='/resources/assets/images/GroupFundLegal Logo-fiv.png'/>"
rel="shortcut icon" >
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
<%-- 	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script> --%>





	<%-- <form id="fileForm" method="post" enctype="multipart/form-data"></form>
	<form:form id="projectForm" method="post" modelAttribute="project"></form:form>
	<form id="imageForm" method="post" enctype="multipart/form-data"></form> --%>
	<section
		class="mbr-section mbr-section--relative mbr-section--fixed-size gr-section-margin"
		id="form1-35" style="background-color: rgb(239, 239, 239);">
		<div id="dialog-overlay"></div>
		<div id="dialog-box">
			<div class="dialog-content">
				<div id="dialog-message"></div>
				<a href="#" class="button" id="Exit">OK</a>
			</div>
		</div>
		<div
			class="mbr-section__container mbr-section__container--std-padding container">

			<div class="row">
				<div class="col-md-8 col-md-offset-2">

					<form>
						<div class="form-group row col-md-12">
							<h3>Tell us why you decline this offer</h3>
							<textarea id="feedback" name="feedback" class="form-control"
								placeholder="We value your feedback." rows="8" /></textarea>
						</div>
						<div class="row col-md-12">
							<!-- <div class="col-md-1 col-sm-1 col-md-offset-9">
								 <button type="button" class="btn btn-success" onClick="goBack()">Go
									Back</button> 
									<button type="button" class="btn btn-success" onClick="goBack()">Back</button>
							</div> -->
							<div class="mbr-buttons mbr-buttons--right">
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

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js">
		
	</script>
	<script src="http://malsup.github.com/jquery.form.js"></script>
	<script>
		$(document).ready(function() {
			$('#reasonForDecline').click(function() {
				var text = $("#feedback").val();
				$.ajax({
					type : "POST",
					url : "<c:url value='/decline/feedback/${projectId}'/>",
					data : {
						feedback : text
					}

				}).done(function(data) {
					popup(data);
				});

			})

			$('#Exit').click(function() {
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

			/* function goBack() {
				window.history.back();
			} */
		})
	</script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="<c:url value='/resources/js/alert_jquery.js'/>"></script>


</body>
</html>