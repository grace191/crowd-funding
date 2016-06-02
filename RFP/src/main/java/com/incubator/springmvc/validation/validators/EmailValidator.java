package com.incubator.springmvc.validation.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.incubator.springmvc.model.Register;
import com.incubator.springmvc.service.AccountService;

@Component
public class EmailValidator implements Validator {

	private AccountService accountService;

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public boolean supports(Class<?> clazz) {
		return Register.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is empty");
		Register register = (Register) target;
		String email = register.getEmail();
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			errors.rejectValue("email", "", "Email is invalid");
		} else if (!accountService.validateEmail(register)) {

			errors.rejectValue("email", "email.already.exists", "Email already exists");
		}
	}

}
