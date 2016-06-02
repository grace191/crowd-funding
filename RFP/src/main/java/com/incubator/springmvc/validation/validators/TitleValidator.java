package com.incubator.springmvc.validation.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.incubator.springmvc.model.Project;
import com.incubator.springmvc.model.Register;
import com.incubator.springmvc.service.AccountService;
import com.incubator.springmvc.service.ProjectService;

@Component
public class TitleValidator implements Validator {

	private ProjectService projectService;

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public boolean supports(Class<?> clazz) {
		return Project.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "", "Title is empty");
		Project project = (Project) target;
		if (!projectService.validateTitle(project)) {
			
			errors.rejectValue("title", "title.already.exists", "Title already exists");
		}
	}

}
