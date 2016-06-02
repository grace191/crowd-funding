package com.incubator.springmvc.controller;

import java.security.Principal;
import java.security.acl.Permission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.incubator.springmvc.model.Account;
import com.incubator.springmvc.model.Contact;
import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.model.Project;
import com.incubator.springmvc.model.Register;
import com.incubator.springmvc.service.AccountService;
import com.incubator.springmvc.service.InvitationService;
import com.incubator.springmvc.service.ProjectService;
import com.incubator.springmvc.utils.Utilities;
import com.incubator.springmvc.utils.JsonResponse;
import com.incubator.springmvc.validation.validators.EmailValidator;
import com.incubator.springmvc.validation.validators.PermissionValidator;
import com.incubator.springmvc.validation.validators.UserValidator;

@Controller
@SessionAttributes("accountBean")
public class AdminController {

	private AccountService accountService;
	private UserValidator userValidator;
	private EmailValidator emailValidator;
	private PermissionValidator permissionValidator;
	private ProjectService projectService;
	private InvitationService invitationService;	
	
	@Autowired
	public void setInvitationService(InvitationService invitationService) {
		this.invitationService = invitationService;
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
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


	@Autowired
	public void setPermissionValidator(PermissionValidator permissionValidator) {
		this.permissionValidator = permissionValidator;
	}

	@ModelAttribute("accountBean")
	public Register createAccountBean() {
		return new Register("", "", "", "");
	}

	@RequestMapping(value = { "/admin/CreateAccount" }, method = RequestMethod.GET)
	public ModelAndView createAccount(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		String name = this.accountService.getUserByEmail(principal.getName()).getUsername();
		modelAndView.addObject("username", name);
		modelAndView.setViewName("admin/CreateAccount");
	//	modelAndView.addObject("contact", new Contact());

		return modelAndView;

	}

	//enter into dashboard page
	@RequestMapping(value = { "/admin/dashboard" }, method = RequestMethod.GET)
	public ModelAndView adminDashboard(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
	//	System.out.println("admin "+principal);
		String name = this.accountService.getUserByEmail(principal.getName()).getUsername();
		modelAndView.addObject("username", name);
		modelAndView.setViewName("/admin/dashboard");

		return modelAndView;

	}
	
	//enter into all projects page
//	@RequestMapping(value = { "/admin/allProjects" }, method = RequestMethod.GET)
//	public ModelAndView adminAllProjects(Principal principal) {
//		ModelAndView modelAndView = new ModelAndView();
//	//	System.out.println("admin "+principal);
//		String name = this.accountService.getUserByEmail(principal.getName()).getUsername();
//		modelAndView.addObject("username", name);
//		modelAndView.setViewName("/admin/allProjects");
//
//		return modelAndView;
//
//	}
	/**
	 * add a new user
	 * @param accountBean
	 * @param result
	 * @param model
	 * @param redirectAttrs
	 * @return
	 */
	@RequestMapping(value = { "/admin/account/add" }, method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("accountBean") @Valid Register accountBean, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "admin/CreateAccount";
		}

		redirectAttrs.addFlashAttribute("user_email", accountBean.getEmail());
		// return new ModelAndView("success/success","message","Registered");
		this.accountService.addAccount(accountBean);
		return "redirect:/admin/accounts";
	}
	
	/**
	 * enter the account edit page
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = { "/admin/account/edit/{userId}" }, method = RequestMethod.GET)
	public ModelAndView editAccountPage(@PathVariable String userId) {
		ModelAndView modelAndView = new ModelAndView();

		
		Register register = this.accountService.getUserRoleAndEmail(UUID.fromString(userId));
		String username = this.accountService.getUserByEmail(register.getEmail()).getUsername();
//		String email = register.getEmail();
//		String permission = register.getPermission();
		register.setUsername(username);
		modelAndView.addObject("register", register);
		modelAndView.addObject("userId", userId);
		System.out.println("register "+register);
		boolean disable = false;
//		System.out.println("id "+register.getEmail());
//		System.out.println("permission "+permission);
		if (this.accountService.checkUserProjects(register.getEmail(), register.getPermission())>0) {
			
			disable = true;
		}
		System.out.println("disable "+disable);
		modelAndView.addObject("disable", disable);
		modelAndView.setViewName("/admin/editAccount");
		return modelAndView;
	}
	
	/**
	 * edit the account, check if the user with its permission has an project. If he has, 
	 * then his permission cannot be edited and his account cannot be deleted.
	 * @param accountBean
	 * @param result
	 * @param model
	 * @param redirectAttrs
	 * @return
	 */
	@RequestMapping(value = { "/admin/editContact/{userId}/{disable}" }, method = RequestMethod.POST)
	public String editAccount(@PathVariable UUID userId,@PathVariable boolean disable,@ModelAttribute("accountBean") @Valid Register accountBean, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {

//		if (result.hasErrors()) {
//			return "admin/editAccount";
//		}
		System.out.println("disable "+disable);

		this.accountService.updateAccount(userId,disable, accountBean);
		return "redirect:/admin/accounts";
	}
	
	/**
	 * list all accounts
	 * @return
	 */
	@RequestMapping(value = { "/admin/accounts" }, method = RequestMethod.GET)
	public ModelAndView listAccounts() {

		ModelAndView model = new ModelAndView();
	//	redirectAttrs.addFlashAttribute("user_email", accountBean.getEmail());
		// return new ModelAndView("success/success","message","Registered");
		HashMap<Register, Map<String, Integer>> accountMap = this.accountService.listAccounts();
//		int numberOfContacts = this.accountService.numberOfContacts();
		model.setViewName("admin/accounts");
//		model.addObject("numberOfContacts", numberOfContacts);
		model.addObject("accountMap", accountMap);
		
		return model;
	}
	
	/**
	 * view all projects
	 * 
	 */
	@RequestMapping(value = { "/admin/allProjects" }, method = RequestMethod.GET)
	public ModelAndView listProjectsAdmin(Principal principal) {

		ModelAndView model = new ModelAndView();
		String name = this.accountService.getUserByEmail(principal.getName()).getUsername();
		model.addObject("username", name);
		List<Project> projects = this.projectService.listProjectsForAdmin();
	
		model.setViewName("/admin/allProjects");
//		model.addObject("numberOfContacts", numberOfContacts);
		model.addObject("projects", projects);
		
		return model;
	}

	/**
	 * view all projects
	 * 
	 */
	@RequestMapping(value = { "/admin/allInvitations" }, method = RequestMethod.GET)
	public ModelAndView listInvitationsForAdmin(Principal principal) {

		ModelAndView model = new ModelAndView();
		String name = this.accountService.getUserByEmail(principal.getName()).getUsername();
		model.addObject("username", name);
		List<Invitation> invitations = this.invitationService.listInvitationsForAdmin();
	
		model.setViewName("/admin/allInvitations");
//		model.addObject("numberOfContacts", numberOfContacts);
		model.addObject("invitations", invitations);
		
		return model;
	}




	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.setValidator(userValidator);
		binder.addValidators(userValidator, emailValidator, permissionValidator);
	}

}
