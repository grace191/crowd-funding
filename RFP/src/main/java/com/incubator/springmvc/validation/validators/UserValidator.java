package com.incubator.springmvc.validation.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.incubator.springmvc.model.Register;
import com.incubator.springmvc.service.AccountService;

@Component
public class UserValidator implements Validator {

	private AccountService accountService;

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public boolean supports(Class<?> clazz) {
		return Register.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "","Username is empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is empty");
		Register register = (Register) target;
		if (!accountService.validateAccount(register)) {
			
			errors.rejectValue("username", "username.already.exists", "Username already exists");
		}
	}

}
