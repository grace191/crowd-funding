package com.incubator.springmvc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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

import com.amazonaws.services.simpleemail.model.SendBounceRequest;
import com.incubator.springmvc.model.Contact;
import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.model.InvitationTemplate;
import com.incubator.springmvc.model.Register;
import com.incubator.springmvc.service.AccountService;
import com.incubator.springmvc.service.ContactService;
import com.incubator.springmvc.service.EmailService;
import com.incubator.springmvc.service.InvitationService;
import com.incubator.springmvc.service.ProjectService;
import com.incubator.springmvc.utils.DeclareConstant;
import com.incubator.springmvc.utils.Utilities;
import com.incubator.springmvc.validation.validators.EmailValidator;
import com.incubator.springmvc.validation.validators.UserValidator;

@Controller
@SessionAttributes("invitation")
public class InvitationController {

	private AccountService accountService;
	private ContactService contactService;
	private ProjectService projectService;
	private InvitationService invitationService;
	private UserValidator userValidator;
	private EmailValidator emailValidator;
	private EmailService emailService;
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@Autowired
	public void setInvitationService(InvitationService invitationService) {
		this.invitationService = invitationService;
	}
	

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
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

	@ModelAttribute("invitation")
	public InvitationTemplate createInvitation() {
		return new InvitationTemplate();
	}


	@RequestMapping(value = { "/project/invitation/{id}" }, method = RequestMethod.GET)
	public ModelAndView invitation(@PathVariable ("id") UUID projectId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
	//	System.out.println("path "+previewLink);
	//	modelAndView.addObject("previewLink", previewLink);
		modelAndView.setViewName("project/invitation");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/project/invitations" }, method = RequestMethod.GET)
	public ModelAndView invitations() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, List<Invitation>> invitations = this.invitationService.listInvitations(Utilities.getCurrentUserDetails().getId());
		modelAndView.addObject("invitationMap", invitations);

		modelAndView.setViewName("/project/invitations");
		return modelAndView;
	}
	
	private boolean sendEmail(List<Contact> contacts,InvitationTemplate invitation,HttpServletRequest httpServletRequest,UUID projectId){
		List<String> ccList = new ArrayList<String>();
		String previewLink = "";
		if (invitation.getSummary().equals(DeclareConstant.YES)) {
			previewLink = Utilities.getURLWithContextPath(httpServletRequest)+"/summary/"+projectId;
		}else{
			previewLink = Utilities.getURLWithContextPath(httpServletRequest)+"/"+projectId;	
		}
				
//		String acceptLink = Utilities.getURLWithContextPath(httpServletRequest)+"/accept/"+projectId;
//		String declineLink = Utilities.getURLWithContextPath(httpServletRequest)+"/decline";
		String acceptLink = "";
		String declineLink = "";
		boolean sendingResult = false;
//		System.out.println("previewlink "+previewLink );
		for (Contact contact : contacts) {
			ccList.add(contact.getParticipant_email());
			acceptLink = Utilities.getURLWithContextPath(httpServletRequest)+"/accept/"+projectId+"/"+contact.getContactId();
			declineLink = Utilities.getURLWithContextPath(httpServletRequest)+"/decline/"+projectId+"/"+contact.getContactId();
			System.out.println("accept link "+acceptLink);
			System.out.println("decline link "+declineLink);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("from", invitation.getSender());
			map.put("subject", invitation.getEmailSubject());
			map.put("to", contact.getParticipant_email());
//			map.put("to", "tianshiyezi@hotmail.com"); // Attention
//			map.put("ccList", ccList);
//			map.put("bccList", ccList);
			map.put("previewLink", previewLink);
			map.put("acceptLink", acceptLink);
			map.put("declineLink", declineLink);
			map.put("title", this.projectService.getProjectById(projectId).getTitle());
			sendingResult = this.emailService.sendEmail("registered.vm", map);
		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("from", invitation.getSender());
//		map.put("subject", invitation.getEmailSubject());
//		map.put("to", "tianshiyezi@hotmail.com"); // Attention
//		map.put("ccList", ccList);
//		map.put("bccList", ccList);
//		map.put("previewLink", previewLink);
//		map.put("acceptLink", acceptLink);
//		map.put("declineLink", declineLink);
//		map.put("title", this.projectService.getProjectById(projectId).getTitle());
//		boolean sendingResult = this.emailService.sendEmail("registered.vm", map);
		return sendingResult;
	}
	
	@RequestMapping(value = { "/project/invitation/add/{id}" }, method = RequestMethod.POST)
	public ModelAndView createInvitation(@ModelAttribute("invitation") InvitationTemplate invitation, BindingResult result,
			Model model, RedirectAttributes redirectAttrs,@PathVariable ("id") UUID projectId, HttpServletRequest httpServletRequest) {
	//	System.out.println("invitation "+invitation +"id "+projectId);
		ModelAndView modelAndView = new ModelAndView();
		List<Contact> contacts = this.contactService.listContacts(Utilities.getCurrentUserDetails().getId());
		boolean sendingResult = sendEmail(contacts, invitation, httpServletRequest, projectId);
		if (sendingResult) {
			//System.err.println("true?");
//			InvitationTemplate template = new InvitationTemplate();
//			template.setEmailSubject(invitation.getEmailSubject());
//			template.setSummary(invitation.getSummary());
			this.projectService.changeInvitationStatus(DeclareConstant.SENT,projectId);
			this.invitationService.addInvitation(projectId,contacts,invitation);
//			System.out.println("contacts"+contacts.size());
//			System.out.println("id "+projectId);
			
		}else{
			this.projectService.changeInvitationStatus(DeclareConstant.FAILURE,projectId);
		}
		
//		if (result == true) {
//			return "success";
			// return new ModelAndView("success/success", "message", "Email has
			// been successfully sent");
			// return new ModelAndView("project", "message", "Email has been
			// successfully sent");
			// return modelAndView;
//		}
		//return "failure";
		modelAndView.setViewName("redirect:/project/invitations");
		return modelAndView;
	}

	@RequestMapping(value = { "/accept/{projectId}/{contactId}" }, method = RequestMethod.GET)
	public ModelAndView reasonForAcceptance(@PathVariable UUID projectId, @PathVariable UUID contactId, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("contactId", contactId);
//		redirectAttributes.addFlashAttribute("projectId", projectId);
//		redirectAttributes.addFlashAttribute("contactId", contactId);
		modelAndView.setViewName("/reasonForAcceptance");
		return modelAndView;
	}
	

	@RequestMapping(value = { "/accept/register/{projectId}/{contactId}" }, method = RequestMethod.POST)
	public @ResponseBody String ajaxReasonForAcceptance(@PathVariable("projectId") UUID projectId,
			@PathVariable UUID contactId,
			@RequestParam("feedback") String feedback) {
		System.out.println("feedback "+feedback);
		this.invitationService.updateFeedBack(DeclareConstant.REASON_FOR_ACCEPTANCE, feedback,projectId, contactId);
		//		ModelAndView modelAndView = new ModelAndView();
		//		modelAndView.addObject("projectId", projectId);
		//
		//		modelAndView.setViewName("/register");
		return "Thank you for your feedback!";
	}
	
//	@RequestMapping(value = { "/reasonForAcceptance/{projectId}/{contactId}" }, method = RequestMethod.GET)
//	public ModelAndView getReasonForAcceptance(@PathVariable UUID projectId, @PathVariable UUID contactId) {
//		ModelAndView modelAndView = new ModelAndView();
////		modelAndView.addObject("projectId", projectId);
////		redirectAttributes.addFlashAttribute("projectId", projectId);
////		redirectAttributes.addFlashAttribute("contactId", contactId);
//		modelAndView.setViewName("/user/reasonForAcceptance");
//		return modelAndView;
//	}
//	
	@RequestMapping(value = { "/user/reasonForDecline/{projectId}" }, method = RequestMethod.GET)
	public ModelAndView reasonForDecline(@PathVariable UUID projectId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		modelAndView.setViewName("user/reasonForDecline");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/offer/decline" }, method = RequestMethod.POST)
	public ModelAndView declineOffer() {
		ModelAndView modelAndView = new ModelAndView();
	//	modelAndView.addObject("projectId", projectId);
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/decline/{projectId}/{contactId}" }, method = RequestMethod.GET)
	public ModelAndView giveReasonForDeclineInvitation(@PathVariable UUID projectId,@PathVariable UUID contactId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("contactId", contactId);
	//	modelAndView.addObject("projectId", projectId);
		modelAndView.setViewName("reasonForDecline");
		return modelAndView;
	}
	
	/**
	 * reason for declining the invitation
	 * @param projectId
	 * @param contactId
	 * @param feedback
	 * @return
	 */
	@RequestMapping(value = { "/project/decline/feedback/{projectId}/{contactId}" }, method = RequestMethod.POST)
	public @ResponseBody String declineFeedback(@PathVariable UUID projectId,
			@PathVariable UUID contactId,
			@RequestParam("feedback") String feedback) {
		System.out.println("feedback "+feedback);
		this.invitationService.updateFeedBack(DeclareConstant.REASON_FOR_DECLINE, feedback,projectId, contactId);
		return "Thank you for your feedback!";
	}
	
	/**
	 * reason for declining the offer
	 * @param projectId
	 * @param contactId
	 * @param feedback
	 * @return
	 */
	@RequestMapping(value = { "/decline/feedback/{projectId}" }, method = RequestMethod.POST)
	public @ResponseBody String declineFeedback(@PathVariable String projectId,
			@RequestParam("feedback") String feedback) {
		System.out.println("post?");
		this.invitationService.updateOfferStatusAndFeedback(DeclareConstant.REASON_FOR_DECLINE, 
				feedback, UUID.fromString(projectId), Utilities.getCurrentUserDetails().getId());
		return "Thank you for your feedback!";
	}
	
	@RequestMapping(value = { "/invitation/decline" }, method = RequestMethod.POST)
	public ModelAndView declineInvitation() {
		ModelAndView modelAndView = new ModelAndView();
	//	modelAndView.addObject("projectId", projectId);
		modelAndView.setViewName("redirect:/ThankYou");
		return modelAndView;
	}

	@RequestMapping(value = { "/ThankYou" }, method = RequestMethod.GET)
	public ModelAndView goToThankYouPage() {
		ModelAndView modelAndView = new ModelAndView();
	//	modelAndView.addObject("projectId", projectId);
		modelAndView.setViewName("ThankYou");
		return modelAndView;
	}


	/**
	 * Edit invitation
	 */
	@RequestMapping(value = { "/project/editInvitation/{title}" }, method = RequestMethod.GET)
	public ModelAndView editInvitation(@PathVariable String title, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		UUID projectId = this.projectService.getProjectByTitle(title).getId();
		Invitation invitation = this.invitationService.getInvitationById(projectId);
		System.out.println("project "+projectId);

		System.out.println("invitatin "+invitation);
		//modelAndView.addObject("projectId", projectId);
		redirectAttributes.addFlashAttribute("projectId", projectId);
		
		redirectAttributes.addFlashAttribute("invitation", invitation);
	//	modelAndView.addObject("previewLink", previewLink);
		modelAndView.setViewName("redirect:/project/EditInvitation/"+projectId);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/project/EditInvitation/{projectId}" }, method = RequestMethod.GET)
	public ModelAndView redirectEditInvitation(@PathVariable String projectId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		Invitation invitation = this.invitationService.getInvitationById(UUID.fromString(projectId));
		modelAndView.addObject("invitation", invitation);
		modelAndView.setViewName("/project/EditInvitation");
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/project/editInvitation/email/{id}" }, method = RequestMethod.POST, params="send_to_all")
	public ModelAndView sendEmailToAllWhenEditInvitation(@ModelAttribute("invitation") InvitationTemplate invitation, BindingResult result,
			Model model, RedirectAttributes redirectAttrs,@PathVariable ("id") UUID projectId, HttpServletRequest httpServletRequest) {
	//	System.out.println("invitation "+invitation +"id "+projectId);
		ModelAndView modelAndView = new ModelAndView();
		List<Contact> contacts = this.contactService.listContacts(Utilities.getCurrentUserDetails().getId());
		boolean sendingResult = sendEmail(contacts, invitation, httpServletRequest, projectId);
		if (sendingResult) {

			this.projectService.changeInvitationStatus(DeclareConstant.RESENT,projectId);
			this.invitationService.editInvitationContacts(projectId, invitation);
	//		this.invitationService.addInvitation(projectId,contacts,invitation);
			
		}else{
			this.projectService.changeInvitationStatus(DeclareConstant.FAILURE,projectId);
		}
		modelAndView.setViewName("redirect:/project/invitations");
		return modelAndView;
	}

	@RequestMapping(value = { "/project/editInvitation/email/{id}" }, method = RequestMethod.POST, params="send")
	public ModelAndView sendEmailToNewInvitee(@ModelAttribute("invitation") InvitationTemplate invitation, BindingResult result,
			Model model, RedirectAttributes redirectAttrs,@PathVariable ("id") UUID projectId, HttpServletRequest httpServletRequest) {
//		System.out.println("newly invitation "+invitation +"id "+projectId);
		ModelAndView modelAndView = new ModelAndView();
//		List<Contact> contacts = this.contactService.listContacts(Utilities.getCurrentUserDetails().getId());
		List<Invitation> invitations = this.invitationService.listContactsWithStatus(projectId);
		List<Contact> newlyAddedContacts = new ArrayList<>();
		for (Invitation invitation2 : invitations) {
			if (invitation2.getInvitationStatus().equals(DeclareConstant.INITIAL_STATUS)) {
				Contact contact = new Contact();
				contact.setContactId(invitation2.getContactId());
				contact.setFirst_name(invitation2.getFirstName());
				contact.setLast_name(invitation2.getLastName());
				contact.setParticipant_email(invitation2.getContactEmail());
				newlyAddedContacts.add(contact);
			}
		}

		for (Contact contact : newlyAddedContacts) {
			System.out.println("newly added contact "+contact);
		}
		boolean sendingResult = sendEmail(newlyAddedContacts, invitation, httpServletRequest, projectId);
		if (sendingResult) {

			this.projectService.changeInvitationStatus(DeclareConstant.RESENT,projectId);
			this.invitationService.editInvitationContacts(projectId, invitation);
	//		this.invitationService.addInvitation(projectId,contacts,invitation);
			
		}else{
			this.projectService.changeInvitationStatus(DeclareConstant.FAILURE,projectId);
		}
		modelAndView.setViewName("redirect:/project/invitations");
		return modelAndView;
	}
	
	/**
	 * invitation controller for admin
	 */
	
	@RequestMapping(value = { "/admin/invitation/{projectId}/{userId}" }, method = RequestMethod.GET)
	public ModelAndView adminInvitation(@PathVariable String projectId, @PathVariable String userId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("projectId", projectId);
		modelAndView.addObject("userId", userId);
	//	System.out.println("path "+previewLink);
	//	modelAndView.addObject("previewLink", previewLink);
		modelAndView.setViewName("/admin/invitation");
		return modelAndView;
	}
	
	/**
	 * send invitation by admin
	 */
	
	@RequestMapping(value = { "/admin/invitation/add/{projectId}/{userId}" }, method = RequestMethod.POST)
	public ModelAndView createInvitationByAdmin(@ModelAttribute("invitation") InvitationTemplate invitation, BindingResult result,
			Model model, RedirectAttributes redirectAttrs,@PathVariable UUID projectId, @PathVariable UUID userId, HttpServletRequest httpServletRequest) {
	//	System.out.println("invitation "+invitation +"id "+projectId);
		ModelAndView modelAndView = new ModelAndView();
		List<Contact> contacts = this.contactService.listContacts(userId);
		boolean sendingResult = sendEmail(contacts, invitation, httpServletRequest, projectId);
		if (sendingResult) {

			this.projectService.changeInvitationStatus(DeclareConstant.SENT,projectId);
			this.invitationService.addInvitation(projectId,contacts,invitation);
		
		}else{
			this.projectService.changeInvitationStatus(DeclareConstant.FAILURE,projectId);
		}
		

		modelAndView.setViewName("redirect:/admin/invitations/"+userId);
		return modelAndView;
	}
	
	/**
	 * open invitations page by admin
	 */
	
	@RequestMapping(value = { "/admin/invitations/{userId}" }, method = RequestMethod.GET)
	public ModelAndView viewInvitationsByAdmin(@PathVariable UUID userId) {
	//	System.out.println("invitation "+invitation +"id "+projectId);
		ModelAndView modelAndView = new ModelAndView();
		Map<String, List<Invitation>> invitations = this.invitationService.listInvitations(userId);
		modelAndView.addObject("invitationMap", invitations);
		modelAndView.setViewName("/admin/invitations");
		return modelAndView;
	}
}
