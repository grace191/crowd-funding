<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
<title>CrowdFunding</title>
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


</head>
<body data-spy="scroll" data-target="#menu-20" data-offset="70">

	<section
		class="mbr-navbar mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--transparent mbr-navbar--sticky mbr-navbar--auto-collapse"
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
										<li class="dropdown mbr-navbar__item"><a
											class="mbr-buttons__link btn text-white"
											href="<c:url value='/admin/dashboard'/>">Dashboard</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<%-- <li class="dropdown mbr-navbar__item"><a href="#"
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
											</ul></li> --%>

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
											href="<c:url value='/user/projects'/>">Participant Projects</a></li>
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
											<!-- <li class="mbr-navbar__item"><a
												class="mbr-buttons__link btn text-white" href="register"><span
													class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->
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
		class="mbr-slider mbr-section mbr-section--no-padding carousel slide"
		data-ride="carousel" data-wrap="true" data-interval="5000"
		id="slider-38" style="background-color: rgb(255, 255, 255);">
		<div class="mbr-section__container">
			<div>
				<ol class="carousel-indicators">
					<li data-app-prevent-settings="" data-target="#slider-38"
						class="active" data-slide-to="0"></li>
					<li data-app-prevent-settings="" data-target="#slider-38"
						data-slide-to="1"></li>
					<li data-app-prevent-settings="" data-target="#slider-38"
						data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div
						class="mbr-box mbr-section mbr-section--relative mbr-section--fixed-size mbr-section--bg-adapted item dark center mbr-section--full-height active"
						style="background-image: url(<c:url value='/resources/assets/images/CityHall-2.jpg'/>);">
						<div
							class="mbr-box__magnet mbr-box__magnet--sm-padding mbr-after-navbar">
							<div class=" container">

								<div class="row">
									<div class="col-sm-8 col-sm-offset-2">
										<div class="mbr-hero">
											<h1 class="mbr-hero__text wow fadeInLeft"
												data-wow-duration="2s">FULL SCREEN SLIDER</h1>
											<p class="mbr-hero__subtext wow fadeInRight"
												data-wow-duration="2s">Choose from the large selection
												of latest pre-made blocks - jumbotrons, hero images,
												parallax scrolling, video backgrounds, hamburger menu,
												sticky header and more.</p>
										</div>
										<div class="mbr-buttons btn-inverse mbr-buttons--center">
											<a class="mbr-buttons__btn btn btn-lg btn-warning wow zoomIn"
												data-wow-duration="2s"
												href="https://mobirise.com/bootstrap-template/mobirise-free-template.zip">GET
												STARTED</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div
						class="mbr-box mbr-section mbr-section--relative mbr-section--fixed-size mbr-section--bg-adapted item dark center mbr-section--full-height"
						style="background-image: url(<c:url value='/resources/assets/images/CityHall-2.jpg'/>);">
						<div
							class="mbr-box__magnet mbr-box__magnet--sm-padding mbr-after-navbar">
							<div class=" container">

								<div class="row">
									<div class="col-sm-8 col-sm-offset-2">
										<div class="mbr-hero">
											<h1 class="mbr-hero__text wow fadeInLeft"
												data-wow-duration="2s">FULL SCREEN SLIDER</h1>
											<p class="mbr-hero__subtext wow fadeInRight"
												data-wow-duration="2s">Choose from the large selection
												of latest pre-made blocks - jumbotrons, hero images,
												parallax scrolling, video backgrounds, hamburger menu,
												sticky header and more.</p>
										</div>
										<div class="mbr-buttons btn-inverse mbr-buttons--center">
											<a class="mbr-buttons__btn btn btn-lg btn-warning wow zoomIn"
												data-wow-duration="2s"
												href="https://mobirise.com/bootstrap-template/mobirise-free-template.zip">GET
												STARTED</a>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
					<div
						class="mbr-box mbr-section mbr-section--relative mbr-section--fixed-size mbr-section--bg-adapted item dark center mbr-section--full-height"
						style="background-image: url(<c:url value='/resources/assets/images/CityHall-2.jpg'/>);">
						<div
							class="mbr-box__magnet mbr-box__magnet--sm-padding mbr-after-navbar">
							<div class=" container">

								<div class="row">
									<div class="col-sm-8 col-sm-offset-2">
										<div class="mbr-hero">
											<h1 class="mbr-hero__text wow fadeInLeft"
												data-wow-duration="2s">FULL SCREEN SLIDER</h1>
											<p class="mbr-hero__subtext wow fadeInRight"
												data-wow-duration="2s">Choose from the large selection
												of latest pre-made blocks - jumbotrons, hero images,
												parallax scrolling, video backgrounds, hamburger menu,
												sticky header and more.</p>
										</div>
										<div class="mbr-buttons btn-inverse mbr-buttons--center">
											<a class="mbr-buttons__btn btn btn-lg btn-warning wow zoomIn"
												data-wow-duration="2s"
												href="https://mobirise.com/bootstrap-template/mobirise-free-template.zip">GET
												STARTED</a>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<a data-app-prevent-settings="" class="left carousel-control"
					role="button" data-slide="prev" href="#slider-38"> <span
					class="glyphicon glyphicon-menu-left" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a data-app-prevent-settings="" class="right carousel-control"
					role="button" data-slide="next" href="#slider-38"> <span
					class="glyphicon glyphicon-menu-right" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</section>
	<section class="content-2 col-4" id="features1-21"
		style="background-color: rgb(255, 255, 255);">

		<div class="container">
			<div class="row">
				<div>
					<div class="thumbnail wow fadeIn" data-wow-delay="0.3s">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/Request-for-Proposal_burned.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>RFP MANAGEMENT</h3>
								<p>Sed egestas urna quam, sit amet euismod ligula commodo
									vitae. Cras hendrerit quam est, non dapibus turpis porta in.
									Fusce viverra, lectus vitae dignissim interdum, erat leo
									egestas velit, eu tincidunt tellus eros a mauris.&nbsp;</p>
							</div>
							<p class="group">
								<a href="#" class="btn btn-default">LEARN MORE</a>
							</p>
						</div>
					</div>
				</div>
				<div>
					<div class="thumbnail wow fadeIn" data-wow-delay="0.3s">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/law-portal_burned_350.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>LAW</h3>
								<p>Sed egestas urna quam, sit amet euismod ligula commodo
									vitae. Cras hendrerit quam est, non dapibus turpis porta in.
									Fusce viverra, lectus vitae dignissim interdum, erat leo
									egestas velit, eu tincidunt tellus eros a mauris.</p>
							</div>
							<p class="group">
								<a href="#" class="btn btn-default">LEARN MORE</a>
							</p>
						</div>
					</div>
				</div>
				<div>
					<div class="thumbnail wow fadeIn" data-wow-delay="0.3s">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/stock-boy_burned_350.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>HOSPITIAL</h3>
								<p>Sed egestas urna quam, sit amet euismod ligula commodo
									vitae. Cras hendrerit quam est, non dapibus turpis porta in.
									Fusce viverra, lectus vitae dignissim interdum, erat leo
									egestas velit, eu tincidunt tellus eros a mauris.</p>
							</div>
							<p class="group">
								<a href="https://mobirise.com/bootstrap-template/"
									class="btn btn-default">LEARN MORE</a>
							</p>
						</div>
					</div>
				</div>
				<div>
					<div class="thumbnail wow fadeIn" data-wow-delay="0.3s">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/welcome.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>PRIVATE PLACEMENT</h3>
								<p>Sed egestas urna quam, sit amet euismod ligula commodo
									vitae. Cras hendrerit quam est, non dapibus turpis porta in.
									Fusce viverra, lectus vitae dignissim interdum, erat leo
									egestas velit, eu tincidunt tellus eros a mauris.</p>
							</div>
							<p class="group">
								<a href="https://mobirise.com/bootstrap-template/"
									class="btn btn-default">LEARN MORE</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section
		class="mbr-box mbr-section mbr-section--relative mbr-section--fixed-size mbr-section--full-height mbr-section--bg-adapted 
mbr-parallax-background"
		id="header1-22"
		style="background-image: url(<c:url value='/resources/assets/images/crowdfunding.jpg'/>);">
		<div
			class="mbr-box__magnet mbr-box__magnet--sm-padding mbr-box__magnet--center-left">
			<div class="mbr-overlay"
				style="opacity: 0.5; background-color: rgb(41, 105, 176);"></div>
			<div class="mbr-box__container mbr-section__container container">
				<div class="mbr-box mbr-box--stretched">
					<div class="mbr-box__magnet mbr-box__magnet--center-left">
						<div class="row">
							<div class=" col-sm-6">
								<div class="mbr-hero animated fadeInUp">
									<h1 class="mbr-hero__text">FUEL YOUR DREAM PROJECT</h1>
									<p class="mbr-hero__subtext">
										Dreams need a great deal of money to take off. That’s where
										our site, a place where everyone could donate money to fund
										your dream project, can help you kickstart your dreams!<br>
									</p>
								</div>
								<div class="mbr-buttons btn-inverse mbr-buttons--left">
									<a
										class="mbr-buttons__btn btn btn-lg animated fadeInUp delay 
btn-info"
										href="#">GET STARTED</a> <a
										class="mbr-buttons__btn btn btn-lg 
btn-default animated fadeInUp delay"
										href="#">LEARN MORE</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="mbr-arrow mbr-arrow--floating text-center">
				<div class="mbr-section__container container">
					<a class="mbr-arrow__link" href="#features1-23"><i
						class="glyphicon glyphicon-menu-down"></i></a>
				</div>
			</div>
		</div>
	</section>

	<section class="content-2 col-3" id="features1-26"
		style="background-color: rgb(239, 239, 239);">

		<div class="container">
			<div class="row">
				<div>
					<div class="thumbnail wow fadeIn" data-wow-delay="0.3s">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/law-portal_burned_350.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>LAW</h3>
								<p>Sed vel vulputate lacus. Nulla et fringilla turpis. Etiam
									non urna magna. Fusce rutrum nec eros id dapibus. In tincidunt
									mi eget molestie condimentum.</p>
							</div>

						</div>
					</div>
				</div>
				<div>
					<div class="thumbnail wow fadeIn" data-wow-delay="0.3s">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/stock-boy_burned_350.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>HOSPITIAL</h3>
								<p>Sed vel vulputate lacus. Nulla et fringilla turpis. Etiam
									non urna magna. Fusce rutrum nec eros id dapibus. In tincidunt
									mi eget molestie condimentum.</p>
							</div>

						</div>
					</div>
				</div>
				<div>
					<div class="thumbnail wow fadeIn" data-wow-delay="0.3s">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/welcome.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>PRIVATE PLACEMENT</h3>
								<p>Sed vel vulputate lacus. Nulla et fringilla turpis. Etiam
									non urna magna. Fusce rutrum nec eros id dapibus. In tincidunt
									mi eget molestie condimentum.</p>
							</div>

						</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section
		class="mbr-section mbr-section--relative mbr-section--fixed-size"
		id="msg-box3-27" style="background-color: rgb(255, 255, 255);">

		<div
			class="mbr-section__container container mbr-section__container--isolated">
			<div class="mbr-header mbr-header--wysiwyg row">
				<div class="col-sm-8 col-sm-offset-2">
					<h3 class="mbr-header__text">WHO WE ARE</h3>

				</div>
			</div>
		</div>


	</section>

	<section class="mbr-gallery mbr-section mbr-section--no-padding"
		id="gallery1-28" style="background-color: rgb(239, 239, 239);">
		<!-- Gallery -->
		<div class=" mbr-gallery-layout-default">
			<div>
				<div class="row mbr-gallery-row no-gutter">
					<div
						class="col-lg-3 col-md-4 col-sm-6 col-xs-12 mbr-gallery-item wow bounceInUp"
						data-wow-delay="0.2s">
						<a href="#lb-gallery1-28" data-slide-to="0" data-toggle="modal">
							<img alt=""
							src="<c:url value='/resources/assets/images/lawyer1.jpg'/>">
							<span class="icon glyphicon glyphicon-zoom-in"></span>
						</a>
					</div>
					<div
						class="col-lg-3 col-md-4 col-sm-6 col-xs-12 mbr-gallery-item wow bounceInDown"
						data-wow-delay="0.2s">
						<a href="#lb-gallery1-28" data-slide-to="1" data-toggle="modal">
							<img alt=""
							src="<c:url value='/resources/assets/images/lawyer2.jpg'/>">
							<span class="icon glyphicon glyphicon-zoom-in"></span>
						</a>
					</div>
					<div
						class="col-lg-3 col-md-4 col-sm-6 col-xs-12 mbr-gallery-item wow bounceInUp"
						data-wow-delay="0.2s">
						<a href="#lb-gallery1-28" data-slide-to="2" data-toggle="modal">
							<img alt=""
							src="<c:url value='/resources/assets/images/lawyer3.jpg'/>">
							<span class="icon glyphicon glyphicon-zoom-in"></span>
						</a>
					</div>
					<div
						class="col-lg-3 col-md-4 col-sm-6 col-xs-12 mbr-gallery-item wow bounceInDown"
						data-wow-delay="0.2s">
						<a href="#lb-gallery1-28" data-slide-to="3" data-toggle="modal">
							<img alt=""
							src="<c:url value='/resources/assets/images/lawyer4.jpg'/>">
							<span class="icon glyphicon glyphicon-zoom-in"></span>
						</a>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>

		<!-- Lightbox -->
		<div data-app-prevent-settings=""
			class="mbr-slider modal fade carousel slide" tabindex="-1"
			data-keyboard="true" data-interval="false" id="lb-gallery1-28">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<ol class="carousel-indicators">
							<li data-app-prevent-settings="" data-target="#lb-gallery1-28"
								data-slide-to="0"></li>
							<li data-app-prevent-settings="" data-target="#lb-gallery1-28"
								data-slide-to="1"></li>
							<li data-app-prevent-settings="" data-target="#lb-gallery1-28"
								data-slide-to="2"></li>
							<li data-app-prevent-settings="" data-target="#lb-gallery1-28"
								class=" active" data-slide-to="3"></li>
						</ol>
						<div class="carousel-inner">
							<div class="item">
								<img alt=""
									src="<c:url value='/resources/assets/images/CityHall.jpg'/>">
							</div>
							<div class="item">
								<img alt=""
									src="<c:url value='/resources/assets/images/CityHall-2.jpg'/>">
							</div>
							<div class="item">
								<img alt=""
									src="<c:url value='/resources/assets/images/CityHall-3.jpg'/>">
							</div>
							<div class="item active">
								<img alt=""
									src="<c:url value='/resources/assets/images/CityHall-4.jpg'/>">
							</div>
						</div>
						<a class="left carousel-control" role="button" data-slide="prev"
							href="#lb-gallery1-28"> <span
							class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a class="right carousel-control" role="button" data-slide="next"
							href="#lb-gallery1-28"> <span
							class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a> <a class="close" href="#" role="button" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							<span class="sr-only">Close</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="msgbox-1" id="msg-box2-29"
		style="background-color: rgb(255, 255, 255);">


		<div class="container">
			<div class="row">

				<div class="col-sm-8">
					<h2>BOOTSTRAP ONE PAGE TEMPLATE</h2>

				</div>
				<div class="col-sm-4">
					<a class="btn btn-lg btn-default"
						href="https://mobirise.com/bootstrap-template/">GET IT NOW</a>
				</div>
			</div>
		</div>
	</section>

	<section
		class="mbr-section mbr-section--relative mbr-section--fixed-size"
		id="social-buttons2-30" style="background-color: rgb(240, 240, 240);">


		<div class="mbr-section__container container">
			<div class="mbr-header mbr-header--inline row">
				<div class="col-sm-4">
					<h3 class="mbr-header__text">FOLLOW US</h3>
				</div>
				<div class="mbr-social-icons mbr-social-icons--style-1 col-sm-8">
					<a class="mbr-social-icons__icon socicon-bg-twitter"
						title="Twitter" target="_blank"
						href="https://twitter.com/mobirise"><i
						class="socicon socicon-twitter"></i></a> <a
						class="mbr-social-icons__icon socicon-bg-facebook"
						title="Facebook" target="_blank"
						href="https://www.facebook.com/pages/Mobirise/1616226671953247"><i
						class="socicon socicon-facebook"></i></a> <a
						class="mbr-social-icons__icon socicon-bg-google" title="Google+"
						target="_blank" href="https://plus.google.com/u/0/+Mobirise/posts"><i
						class="socicon socicon-google"></i></a> <a
						class="mbr-social-icons__icon 
socicon-bg-youtube" title="YouTube"
						target="_blank"
						href="http://www.youtube.com/channel/UCt_tncVAetpK5JeM8L-8jyw"><i
						class="socicon 
socicon-youtube"></i></a> <a
						class="mbr-social-icons__icon socicon-bg-instagram"
						title="Instagram" target="_blank"
						href="https://instagram.com/mobirise/"><i
						class="socicon socicon-instagram"></i></a> <a
						class="mbr-social-icons__icon socicon-bg-pinterest"
						title="Pinterest" target="_blank"
						href="https://www.pinterest.com/mobirise/"><i
						class="socicon socicon-pinterest"></i></a> <a
						class="mbr-social-icons__icon socicon-bg-behance" title="Behance"
						target="_blank" href="https://www.behance.net/Mobirise"><i
						class="socicon socicon-behance"></i></a> <a
						class="mbr-social-icons__icon socicon-bg-tumblr" title="Tumblr"
						target="_blank" href="http://mobirise.tumblr.com/"><i
						class="socicon 
socicon-tumblr"></i></a> <a
						class="mbr-social-icons__icon socicon-bg-linkedin"
						title="LinkedIn" target="_blank"
						href="https://www.linkedin.com/in/mobirise"><i
						class="socicon socicon-linkedin"></i></a> <a
						class="mbr-social-icons__icon socicon-bg-android"
						title="Google Play" target="_blank"
						href="https://play.google.com/store/apps/details?id=com.mobirise.mobirise"><i
						class="socicon socicon-android"></i></a>
				</div>
			</div>
		</div>
	</section>

	<section class="content-2 col-4" id="features1-31"
		style="background-color: rgb(255, 255, 255);">

		<div class="container">
			<div class="row">
				<div>
					<div class="thumbnail wow pulse" data-wow-delay="0.3">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/anonymous.jpg'/>">
						</div>
						<div class="caption">
							<div>
								<h3>ANONYMOUS</h3>
								<p>Etiam lacinia volutpat magna, sed lobortis risus egestas
									in. Nunc fringilla urna ac libero venenatis molestie. Curabitur
									molestie mi quis mauris convallis aliquet. Cras pretium sapien
									mi, sed sagittis est ultrices at.</p>
							</div>
							<p class="group">
								<a href="#" class="btn btn-default">LEARN MORE</a>
							</p>
						</div>
					</div>
				</div>
				<div>
					<div class="thumbnail wow pulse" data-wow-delay="0.3">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/chrome-remote-desktop-350x350-23.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>FEATURE 2</h3>
								<p>Etiam lacinia volutpat magna, sed lobortis risus egestas
									in. Nunc fringilla urna ac libero venenatis molestie. Curabitur
									molestie mi quis mauris convallis aliquet. Cras pretium sapien
									mi, sed sagittis est ultrices at.</p>
							</div>
							<p class="group">
								<a href="#" class="btn btn-default">LEARN MORE</a>
							</p>
						</div>
					</div>
				</div>
				<div>
					<div class="thumbnail wow pulse" data-wow-delay="0.3">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/com-350x350-41.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>FEATURE 3</h3>
								<p>Etiam lacinia volutpat magna, sed lobortis risus egestas
									in. Nunc fringilla urna ac libero venenatis molestie. Curabitur
									molestie mi quis mauris convallis aliquet. Cras pretium sapien
									mi, sed sagittis est ultrices at.</p>
							</div>
							<p class="group">
								<a href="#" class="btn btn-default">LEARN MORE</a>
							</p>
						</div>
					</div>
				</div>
				<div>
					<div class="thumbnail wow pulse" data-wow-delay="0.3">
						<div class="image">
							<img class="undefined"
								src="<c:url value='/resources/assets/images/calculator-350x350-78.png'/>">
						</div>
						<div class="caption">
							<div>
								<h3>FEATURE 4</h3>
								<p>Etiam lacinia volutpat magna, sed lobortis risus egestas
									in. Nunc fringilla urna ac libero venenatis molestie. Curabitur
									molestie mi quis mauris convallis aliquet. Cras pretium sapien
									mi, sed sagittis est ultrices at.</p>
							</div>
							<p class="group">
								<a href="#" class="btn btn-default">LEARN MORE</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="pricing-table-1 col-4" id="pricing-table1-32"
		style="background-color: rgb(240, 240, 240);">


		<div class="container">
			<div class="row">
				<div>
					<div class="plan green wow flipInY">
						<h3>STANDARD</h3>
						<p class="price">
							<strong><sup>$</sup><span>0</span></strong> <small>per
								month</small>
						</p>
						<div>
							<ul>
								<li><strong>32 GB</strong> Storage</li>
								<li><strong>Unlimited</strong> Users</li>
								<li><strong>15 GB</strong> Bandwidth</li>
							</ul>
						</div>
						<p>
							<a href="#" class="btn btn-success">DEMO LINK</a>
						</p>
					</div>
				</div>
				<div>
					<div class="plan orange wow flipInY">
						<h3>BUSINESS</h3>
						<p class="price">
							<strong><sup>$</sup><span>0</span></strong> <small>per
								month</small>
						</p>
						<div>
							<ul>
								<li><strong>80 GB</strong> Storage</li>
								<li><strong>Unlimited</strong> Users</li>
								<li><strong>20 GB</strong> Bandwidth</li>
							</ul>
						</div>
						<p>
							<a href="#" class="btn btn-success">DEMO LINK</a>
						</p>
					</div>
				</div>
				<div>
					<div class="plan favorite wow flipInY">
						<h3>PREMIUM</h3>
						<p class="price">
							<strong><sup>$</sup><span>0</span></strong> <small>per
								month</small>
						</p>
						<div>
							<ul>
								<li><strong>100 GB</strong> Storage</li>
								<li><strong>Unlimited</strong> Users</li>
								<li><strong>50 GB</strong> Bandwidth</li>
							</ul>
						</div>
						<p>
							<a href="#" class="btn btn-success">DEMO LINK</a>
						</p>
					</div>
				</div>
				<div>
					<div class="plan blue wow flipInY">
						<h3>ULTIMATE</h3>
						<p class="price">
							<strong><sup>$</sup><span>0</span></strong> <small>per
								month</small>
						</p>
						<div>
							<ul>
								<li><strong>Unlimited</strong> Storage</li>
								<li><strong>Unlimited</strong> Users</li>
								<li><strong>1TB GB</strong> Bandwidth</li>
							</ul>
						</div>
						<p>
							<a href="#" class="btn btn-success">DEMO LINK</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section
		class="mbr-section mbr-section--relative mbr-section--fixed-size"
		id="social-buttons1-33" style="background-color: rgb(247, 218, 100);">


		<div class="mbr-section__container container">
			<div class="mbr-header mbr-header--inline row">
				<div class="col-sm-4">
					<h3 class="mbr-header__text">SHARE THIS PAGE!</h3>
				</div>
				<div class="mbr-social-icons col-sm-8">
					<div class="mbr-social-likes social-likes_style-1"
						data-counters="true">
						<div
							class="mbr-social-icons__icon social-likes__icon facebook socicon-bg-facebook"
							title="Share link on Facebook">
							<i class="socicon socicon-facebook"></i>

						</div>
						<div
							class="mbr-social-icons__icon social-likes__icon twitter socicon-bg-twitter"
							title="Share link on Twitter">
							<i class="socicon socicon-twitter"></i>

						</div>
						<div
							class="mbr-social-icons__icon social-likes__icon plusone socicon-bg-google"
							title="Share link on Google+">
							<i class="socicon socicon-google"></i>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section
		class="mbr-section mbr-section--relative mbr-section--fixed-size"
		id="testimonials1-34" style="background-color: rgb(255, 255, 255);">
		<div>

			<div
				class="mbr-section__container mbr-section__container--std-padding container">
				<div class="row">
					<div class="col-sm-12">
						<h2 class="mbr-section__header">WHAT OUR FANTASTIC USERS SAY</h2>
						<ul class="mbr-reviews mbr-reviews--wysiwyg row">
							<li class="mbr-reviews__item col-sm-6 col-md-4">
								<div class="mbr-reviews__text">
									<p class="mbr-reviews__p">“Nullam cursus sed nibh nec
										commodo. Ut mattis mi at magna vestibulum, vel mattis dolor
										vulputate. Vestibulum egestas libero sit amet nisi feugiat, id
										molestie quam cursus. ”</p>
								</div>
								<div class="mbr-reviews__author mbr-reviews__author--short">
									<div class="mbr-reviews__author-name">NANCY</div>
									<div class="mbr-reviews__author-bio">User</div>
								</div>
							</li>
							<li class="mbr-reviews__item col-sm-6 col-md-4">
								<div class="mbr-reviews__text">
									<p class="mbr-reviews__p">“Curabitur dignissim tempus
										libero at aliquam. Sed arcu nisi, maximus sit amet nulla quis,
										faucibus sollicitudin lacus. Quisque sed nulla at leo cursus
										finibus sed hendrerit risus. Maecenas euismod faucibus ornare.
										Sed tellus elit, fringilla in malesuada nec, volutpat vel
										ligula.”</p>
								</div>
								<div class="mbr-reviews__author mbr-reviews__author--short">
									<div class="mbr-reviews__author-name">JOHN</div>
									<div class="mbr-reviews__author-bio">User</div>
								</div>
							</li>
							<li class="mbr-reviews__item col-sm-6 col-md-4">
								<div class="mbr-reviews__text">
									<p class="mbr-reviews__p">“In tempus arcu a urna maximus,
										at porta felis laoreet. Vestibulum dui ipsum, varius ac ante
										sed, volutpat faucibus mauris. Morbi viverra ipsum ut lacinia
										pretium. Vivamus sit amet quam sed lorem rhoncus gravida. ”</p>
								</div>
								<div class="mbr-reviews__author mbr-reviews__author--short">
									<div class="mbr-reviews__author-name">MARK</div>
									<div class="mbr-reviews__author-bio">User</div>
								</div>
							</li>
						</ul>
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
</body>
</html>