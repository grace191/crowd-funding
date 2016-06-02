package com.incubator.springmvc.validation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.incubator.springmvc.model.Register;

@Component
public class PermissionValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Register.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
				Register register = (Register) target;
		if (register.getPermission().equals("None")) {
			
			errors.rejectValue("permission", "permission.is.none", "Please grant permission");
		}
	}

}
