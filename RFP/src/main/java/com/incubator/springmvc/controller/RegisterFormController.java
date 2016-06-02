package com.incubator.springmvc.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.incubator.springmvc.model.Account;
import com.incubator.springmvc.model.Contact;
import com.incubator.springmvc.model.Project;
import com.incubator.springmvc.model.Register;
import com.incubator.springmvc.model.UploadedFile;
import com.incubator.springmvc.service.AccountService;
import com.incubator.springmvc.service.ContactService;
import com.incubator.springmvc.service.InvitationService;
import com.incubator.springmvc.service.ProjectService;
import com.incubator.springmvc.service.UploadedFileService;
import com.incubator.springmvc.utils.DeclareConstant;
import com.incubator.springmvc.utils.Utilities;
import com.incubator.springmvc.validation.validators.EmailValidator;
import com.incubator.springmvc.validation.validators.UserValidator;

@Controller
@SessionAttributes("registerBean")
public class RegisterFormController {

	private AccountService accountService;
	private UserValidator userValidator;
	private EmailValidator emailValidator;
	private ProjectService projectService;
	private UploadedFileService uploadedFileService;
	private InvitationService invitationService;
	private ContactService contactService;
	
	
	@Autowired
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	@Autowired
	public void setInvitationService(InvitationService invitationService) {
		this.invitationService = invitationService;
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	public void setUploadedFileService(UploadedFileService uploadedFileService) {
		this.uploadedFileService = uploadedFileService;
	}

	@Autowired
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	@Autowired
	public void setEmailValidator(EmailValidator emailValidator) {
		this.emailValidator = emailValidator;
	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@ModelAttribute("registerBean")
	public Register createRegisterFormBean() {
		return new Register("", "", "");
	}

	@RequestMapping(value = { "/register" }, method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView registerForm() {
		return new ModelAndView("register");
	}

	@RequestMapping(value = { "/account/add/{projectId}/{contactId}" }, method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("registerBean") @Valid Register registerBean, BindingResult result,
			Model model, RedirectAttributes redirectAttrs, @PathVariable ("projectId") UUID projectId,
			@PathVariable ("contactId") UUID contactId) {

		if (result.hasErrors()) {
			return "register";
		}
		System.out.println("register project id "+projectId);
		// return new ModelAndView("success/success","message","Registered");
		this.accountService.register(registerBean, projectId, contactId);
		this.invitationService.updateInvitationStatus(DeclareConstant.SUBSCRIBED, projectId, contactId);
		// redirectAttrs.addFlashAttribute("register",registerBean.getEmail());
		// return new ModelAndView("project");
		// return new ModelAndView("hello");
		return "redirect:/";
	}

/**
 * After submit the reason for acceptance, the user is redirected to register page.
 * Need to check email, if it exist, then it is the project creator or another user.
 * @param projectId
 * @param contactId
 * @return
 */
	@RequestMapping(value = { "/register/{projectId}/{contactId}" }, method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView reasonForAccept(@PathVariable String projectId, @PathVariable String contactId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("contactId", contactId);
		System.out.println("invalid contactid "+contactId);
		Contact contact = this.contactService.getContactById(UUID.fromString(contactId));
		contact.setFullName(contact.getFirst_name()+" "+contact.getLast_name());
		modelAndView.addObject("fullname", contact.getFullName());
		modelAndView.addObject("email", contact.getParticipant_email());
		Account account = this.accountService.getUserByEmail(contact.getParticipant_email());
		//Register register = new Register(account.getUsername(), account.getEmail(), account.getPassword());
		if (account.getEmail() != null) {
			modelAndView.setViewName("redirect:/login");
			this.accountService.updateAccountsProjects(UUID.fromString(projectId), account.getEmail(),UUID.fromString(contactId));
			this.invitationService.updateInvitationStatus(DeclareConstant.SUBSCRIBED, UUID.fromString(projectId), UUID.fromString(contactId));
			
		}else{
			modelAndView.setViewName("/register");
		}
		
		return modelAndView;
	}
	

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.setValidator(userValidator);
		binder.addValidators(userValidator, emailValidator);
	}

}
