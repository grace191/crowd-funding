<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Payment</title>
<!-- Latest compiled and minified CSS -->
<script src="//code.jquery.com/jquery-2.0.2.min.js"></script>
<link href="<c:url value='/resources/assets/images/GroupFundLegal Logo-fiv.png'/>"
rel="shortcut icon" >
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.payment/1.2.3/jquery.payment.min.js"></script>


<link rel="stylesheet"
	href="<c:url value='/resources/css/payment/style.css'/>">
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- You can make it whatever width you want. I'm making it full width
             on <= small devices and 4/12 page width on >= medium devices -->
			<div class="col-xs-12 col-md-4 col-md-offset-4">


				<!-- CREDIT CARD FORM STARTS HERE -->
				<div class="panel panel-default credit-card-box">
					<div class="panel-heading display-table">
						<div class="row display-tr">
							<h3 class="panel-title display-td">Payment Details</h3>
							<div class="display-td">
								<img class="img-responsive pull-right"
									src="http://i76.imgup.net/accepted_c22e0.png">
							</div>
						</div>
					</div>
					<div class="panel-body">
						<form role="form" id="payment-form" method="POST"
							action="javascript:void(0);">
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label for="cardNumber">CARD NUMBER</label>
										<div class="input-group">
											<input type="tel" class="form-control" name="cardNumber"
												placeholder="Valid Card Number" autocomplete="cc-number"
												required autofocus /> <span class="input-group-addon"><i
												class="fa fa-credit-card"></i></span>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-7 col-md-7">
									<div class="form-group">
										<label for="cardExpiry"><span class="hidden-xs">EXPIRATION</span><span
											class="visible-xs-inline">EXP</span> DATE</label> <input type="tel"
											class="form-control" name="cardExpiry" placeholder="MM / YY"
											autocomplete="cc-exp" required />
									</div>
								</div>
								<div class="col-xs-5 col-md-5 pull-right">
									<div class="form-group">
										<label for="cardCVC">CV CODE</label> <input type="tel"
											class="form-control" name="cardCVC" placeholder="CVC"
											autocomplete="cc-csc" required />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-group">
										<label for="couponCode">COUPON CODE</label> <input type="text"
											class="form-control" name="couponCode" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<button class="subscribe btn btn-success btn-lg btn-block"
										type="button">Pay $10</button>
								</div>
							</div>
							<div class="row" style="display: none;">
								<div class="col-xs-12">
									<p class="payment-errors"></p>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- CREDIT CARD FORM ENDS HERE -->


			</div>

		</div>
	</div>
	<!-- If you're using Stripe for payments -->
	<script src="https://js.stripe.com/v2/"></script>
	<script>
		(function() {
			Stripe.setPublishableKey('pk_test_8EmuyRTHdNqILbpnxzNGjhaK');
		})();
	</script>
	<script type="text/javascript">
	        var $form = $('#payment-form');
        $form.find('.subscribe').on('click', payWithStripe);

        /* If you're using Stripe for payments */

        function payWithStripe(e) {
            e.preventDefault();

            /* Abort if invalid form data */
            if (!validator.form()) {
                return;
            }

            /* Visual feedback */
            $form.find('.subscribe').html(
                'Validating <i class="fa fa-spinner fa-pulse"></i>').prop(
                'disabled', true);

            var PublishableKey = 'pk_test_8EmuyRTHdNqILbpnxzNGjhaK'; // Replace with your API publishable key
            Stripe.setPublishableKey(PublishableKey);

            /* Create token */
            var expiry = $form.find('[name=cardExpiry]').payment(
                'cardExpiryVal');
            var ccData = {
                number: $form.find('[name=cardNumber]').val().replace(/\s/g,
                    ''),
                cvc: $form.find('[name=cardCVC]').val(),
                exp_month: expiry.month,
                exp_year: expiry.year
            };

            Stripe.card
                .createToken(
                ccData, function stripeResponseHandler(status, response) {
                if (response.error) {
                    /* Visual feedback */
                    $form.find('.subscribe').html('Try again')
                        .prop('disabled', false);
                    /* Show Stripe errors on the form */
                    $form.find('.payment-errors').text(
                        response.error.message);
                    $form.find('.payment-errors').closest(
                        '.row').show();
                } else {
                    /* Visual feedback */
                    $form
                        .find('.subscribe')
                        .html(
                        'Processing <i class="fa fa-spinner fa-pulse"></i>');
                    /* Hide Stripe errors on the form */
                    $form.find('.payment-errors').closest(
                        '.row').hide();
                    $form.find('.payment-errors').text("");
                    // response contains id and card, which contains additional card details            
                    console.log(response.id);
                    console.log(response.card);
                    var token = response.id;
                    // AJAX - you would send 'token' to your server here.
                    $
                        .post('<c:url value="/account/stripe_card_token"/>', {
                        token: token
                    })
                    // Assign handlers immediately after making the request,
                    .done(function(data, textStatus,
                        jqXHR) {
                        
							
							if (data === "success") {
								$form
	                            .find(
	                            '.subscribe')
	                            .html(
	                            'Payment successful <i class="fa fa-check"></i>');
                                window.location.assign('<c:url value="/project"/>');
                            }else{
                            	$form
                                .find(
                                '.subscribe')
                                .html(
                                'Payment declined <i class="fa fa-close"></i>');
                            	
								$form
                            .find(
                            '.payment-errors')
                            .text(
                            'Your payment was declined. Please try again');
                        $form
                            .find(
                            '.payment-errors')
                            .closest('.row')
                            .show();
								
							}

                        /* $.get('<c:url value="/project"/>', function(data) {
                            alert("Load was performed.");
                        }); */

                    })
                        .fail(function(jqXHR, textStatus,
                        errorThrown) {
                        $form
                            .find(
                            '.subscribe')
                            .html(
                            'There was a problem')
                            .removeClass(
                            'success')
                            .addClass(
                            'error');
                        /* Show Stripe errors on the form */
                        $form
                            .find(
                            '.payment-errors')
                            .text(
                            'Try refreshing the page and trying again.');
                        $form
                            .find(
                            '.payment-errors')
                            .closest('.row')
                            .show();
                    });
                }
            });
        }
        /* Fancy restrictive input formatting via jQuery.payment library*/
        $('input[name=cardNumber]').payment('formatCardNumber');
        $('input[name=cardCVC]').payment('formatCardCVC');
        $('input[name=cardExpiry').payment('formatCardExpiry');

        /* Form validation using Stripe client-side validation helpers */
        jQuery.validator.addMethod("cardNumber", function(value, element) {
            return this.optional(element) || Stripe.card.validateCardNumber(value);
        }, "Please specify a valid credit card number.");

        jQuery.validator.addMethod("cardExpiry", function(value, element) {
            /* Parsing month/year uses jQuery.payment library */
            value = $.payment.cardExpiryVal(value);
            return this.optional(element) || Stripe.card.validateExpiry(value.month, value.year);
        }, "Invalid expiration date.");

        jQuery.validator.addMethod("cardCVC", function(value, element) {
            return this.optional(element) || Stripe.card.validateCVC(value);
        }, "Invalid CVC.");

        validator = $form.validate({
            rules: {
                cardNumber: {
                    required: true,
                    cardNumber: true
                },
                cardExpiry: {
                    required: true,
                    cardExpiry: true
                },
                cardCVC: {
                    required: true,
                    cardCVC: true
                }
            },
            highlight: function(element) {
                $(element).closest('.form-control').removeClass('success')
                    .addClass('error');
            },
            unhighlight: function(element) {
                $(element).closest('.form-control').removeClass('error')
                    .addClass('success');
            },
            errorPlacement: function(error, element) {
                $(element).closest('.form-group').append(error);
            }
        });

        paymentFormReady = function() {
            if ($form.find('[name=cardNumber]').hasClass("success") && $form.find('[name=cardExpiry]').hasClass("success") && $form.find('[name=cardCVC]').val().length > 1) {
                return true;
            } else {
                return false;
            }
        }

        $form.find('.subscribe').prop('disabled', true);
        var readyInterval = setInterval(function() {
            if (paymentFormReady()) {
                $form.find('.subscribe').prop('disabled', false);
                clearInterval(readyInterval);
            }
        }, 250);</script>
</body>
</html>