package com.incubator.springmvc.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener extends SimpleUrlAuthenticationFailureHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	private static final Logger LOG = Logger.getLogger(MyApplicationListener.class);

	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		Object userName = event.getAuthentication().getPrincipal();
		Object credentials = event.getAuthentication().getCredentials();
		System.out.println("Failed login using USERNAME [" + userName + "]");
		System.out.println("Failed login using PASSWORD [" + credentials + "]");
/*		LOG.debug("Failed login using USERNAME [" + userName + "]");
		LOG.debug("Failed login using PASSWORD [" + credentials + "]");*/
	}
	
	public void onAuthenticationFailure(HttpServletRequest request){
		System.out.println(request.getParameter("username"));
		
	}
}