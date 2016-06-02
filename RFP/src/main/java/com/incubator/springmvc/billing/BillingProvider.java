package com.incubator.springmvc.billing;

public interface BillingProvider {
	public String charge(String token);

}
