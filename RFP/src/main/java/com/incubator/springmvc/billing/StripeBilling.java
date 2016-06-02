package com.incubator.springmvc.billing;

import java.util.HashMap;
import java.util.Map;

import com.incubator.springmvc.utils.DeclareConstant;
import com.incubator.springmvc.utils.Utilities;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

public class StripeBilling implements BillingProvider{
	@Override
	public String charge(String token){
		Stripe.apiKey = "sk_test_yR3pnJoMKT4rONHerBHDJ67e";
		String result = "";

		// Get the credit card details submitted by the form

		// Create the charge on Stripe's servers - this will charge the user's card
		try {
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("amount", 1000); // amount in cents, again
			chargeParams.put("currency", "usd");
			chargeParams.put("source", token);
			chargeParams.put("description", "Example charge");

			Charge charge = Charge.create(chargeParams);
			result = Utilities.showPriceInUSD(Double.valueOf(String.valueOf(charge.getAmount()/100)));
		//	System.out.println("result: "+result);
		//	result = DeclareConstant.SUCCESS;
		
			
		} catch (CardException|AuthenticationException|InvalidRequestException|APIConnectionException|APIException e) {
			// The card has been declined
			result = DeclareConstant.DECLINED;
		
		} 
		return result;
		//return DeclareConstant.DECLINED;
	}


}
