package com.incubator.springmvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.incubator.springmvc.model.Contact;
import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.service.AccountService;
import com.incubator.springmvc.service.ContactService;
import com.incubator.springmvc.service.InvitationService;
import com.incubator.springmvc.service.ProjectService;
import com.incubator.springmvc.utils.Utilities;


@Controller
@SessionAttributes("contact")
public class ContactController {

	private ContactService contactService;
	private AccountService accountService;
	private InvitationService invitationService;
	private ProjectService projectService;

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
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@ModelAttribute("contact")
	public Contact createAccountFormBean() {
		return new Contact();
	}

	//	@RequestMapping(value = { "project/contact/add" }, method = RequestMethod.POST)
	//	public String addContact(@ModelAttribute("contact") @Valid Contact contact, Model model,
	//			RedirectAttributes redirectAttrs) {
	//		//		System.out.println("contact " + contact);
	//		UUID user_id = this.accountService.getUserByEmail(contact.getUser_email()).getId();
	//		if (contact.getId() == null) {
	//			this.contactService.addContact(contact);
	//		} else {
	//			this.contactService.updateContact(contact);
	//		}
	//		redirectAttrs.addFlashAttribute("contactList", this.contactService.listContacts(contact.getUser_email()));
	//
	//		redirectAttrs.addFlashAttribute("user_id", user_id);
	//		if (Utilities.hasRole("ROLE_ADMIN")) {
	//			return "redirect:/admin/AdminContactList";
	//		} else {
	//			return "redirect:/project/contacts";
	//		}
	//
	//	}

	@RequestMapping(value = { "project/contact/add" }, method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact") @Valid Contact contact, Model model,
			RedirectAttributes redirectAttrs) {

		this.contactService.addContact(contact);

		return "redirect:/project/contacts";

	}
	@RequestMapping(value = { "project/contact/edit" }, method = RequestMethod.POST)
	public String editContact(@ModelAttribute("contact") @Valid Contact contact, Model model,
			RedirectAttributes redirectAttrs) {
		//		System.out.println("contact " + contact);
		//		UUID user_id = this.accountService.getUserByEmail(contact.getUser_email()).getId();

		this.contactService.updateContact(contact);
		//		redirectAttrs.addFlashAttribute("contactList", this.contactService.listContacts(contact.getUser_email()));
		//
		//		redirectAttrs.addFlashAttribute("user_id", user_id);

		return "redirect:/project/contacts";


	}
	@RequestMapping("/admin/contact/add/{user_id}")
	public ModelAndView adminAddContact(@PathVariable("user_id") UUID user_id, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {

		String user_email = this.accountService.getAccountById(user_id).getEmail();
		modelAndView.addObject("user_email", user_email);
		modelAndView.addObject("user_id", user_id);

		System.out.println("user_id"+user_id);
		modelAndView.setViewName("admin/adminAddContact");
		return modelAndView;
		// return "redirect:/project/EditContact";
	}


	//	@RequestMapping(value = { "/admin/contact/new/{id}"},method = RequestMethod.POST)
	//	public ModelAndView adminAddNewContact(@PathVariable(value="id") UUID user_id, @ModelAttribute("contact") @Valid Contact contact,ModelAndView modelAndView, 
	//			RedirectAttributes redirectAttributes) {
	//		System.out.println("what's wrong");
	//		System.out.println("user_id "+user_id);
	//		ModelAndView model = new ModelAndView();
	//		//
	//		this.contactService.addContact(contact);
	//		//		model.addObject("contactList", this.contactService.listContacts(UUID.fromString(user_id)));
	//		model.addObject("user_id", user_id);
	//		redirectAttributes.addFlashAttribute("user_id",user_id);
	//		model.setViewName("redirect:/admin/AdminContactList/{id}");
	//		return model;
	//	}



	@RequestMapping(value = { "/admin/AdminContactList/{id}" }, method = RequestMethod.GET)
	public ModelAndView contactListWithUserId(@PathVariable(value="id") UUID user_id) {
		List<Contact> contactList = this.contactService.listContacts(user_id);	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("contactList", contactList);
		modelAndView.addObject("user_id", user_id);
		modelAndView.setViewName("admin/AdminContactList");
		return modelAndView;

	}
	@RequestMapping("/admin/contact/edit/{user_id}")
	public ModelAndView adminEditContact(@ModelAttribute("contact") @Valid Contact contact,
			@PathVariable("user_id") UUID user_id, ModelAndView modelAndView, 
			RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();

		this.contactService.updateContact(contact);

		model.addObject("contactList", this.contactService.listContacts(user_id));
		model.addObject("user_id", user_id);
		model.setViewName("/admin/AdminContactList");
		return model;
	}


	@RequestMapping(value = { "/admin/AdminContactList" }, method = RequestMethod.GET)
	public ModelAndView contactList(Model model) {
		List<Contact> contactList = (List<Contact>) model.asMap().get("contactList");
		UUID user_id = (UUID) model.asMap().get("user_id");
		if (null == contactList) {
			contactList = this.contactService.listContacts(user_id);
		}		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("contactList", contactList);
		modelAndView.addObject("user_id", user_id);
		modelAndView.setViewName("admin/AdminContactList");
		return modelAndView;

	}


	@RequestMapping(value = { "/project/contacts" }, method = RequestMethod.GET)
	public ModelAndView projectContactList() {
		UUID user_id = Utilities.getCurrentUserDetails().getId();
		System.out.println("userid "+user_id);
		List<Contact> contactList = this.contactService.listContacts(user_id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("contactList", contactList);
		modelAndView.addObject("user_id", user_id);
		modelAndView.setViewName("project/contacts");
		return modelAndView;

	}


	@RequestMapping(value = { "/project/EditContact" }, method = RequestMethod.GET)
	public String editContact() {
		return "project/EditContact";

	}

	@RequestMapping(value = "/admin/contacts/{id}", method = RequestMethod.GET)
	public ModelAndView findUserContacts(@PathVariable("id") UUID user_id, Model model,
			RedirectAttributes redirectAttributes) {
		// System.out.println("findContactByEmail "+userEmail);
		List<Contact> contacts = new ArrayList<Contact>();
		contacts = this.contactService.listContacts(user_id);
		// System.out.println("contacts " + contacts.size());
		ModelAndView modelAndView = new ModelAndView();
		redirectAttributes.addFlashAttribute("user_id", user_id);
		redirectAttributes.addFlashAttribute("contactList", contacts);
		modelAndView.addObject("user_id", user_id);
		modelAndView.addObject("contactList", contacts);
		modelAndView.setViewName("/admin/AdminContactList");
		return modelAndView;

	}

	@RequestMapping("/admin/contact/edit/{user_id}/{contact_id}")
	public ModelAndView adminEditContact(@PathVariable("user_id") UUID user_id, @PathVariable("contact_id") UUID contact_id,
			ModelAndView modelAndView, RedirectAttributes redirectAttributes) {

		String user_email = this.accountService.getAccountById(user_id).getEmail();
		modelAndView.addObject("user_email", user_email);
		modelAndView.addObject("contact_id", contact_id);

		modelAndView.setViewName("admin/adminEditContact");
		return modelAndView;
	}
	@RequestMapping("/admin/contact/remove/{user_id}/{contact_id}")
	public ModelAndView removeContact(@PathVariable("user_id") UUID user_id,
			@PathVariable("contact_id") UUID contact_id) {

		this.contactService.removeContact(contact_id);
		ModelAndView modelAndView = new ModelAndView();
		List<Contact> contacts = new ArrayList<Contact>();
		contacts = this.contactService.listContacts(user_id);
		modelAndView.addObject("contactList", contacts);
		// System.out.println("remove "+contacts.size());
		modelAndView.setViewName("/admin/AdminContactList");
		// return "redirect:/project/contacts";
		return modelAndView;
	}

	@RequestMapping("/project/invitation/contact/remove/{user_id}/{contact_id}")
	public ModelAndView removeInvitationContact(@PathVariable("user_id") UUID user_id,
			@PathVariable("contact_id") UUID contact_id) {

		this.contactService.removeContact(contact_id);
		ModelAndView modelAndView = new ModelAndView();
		//List<Contact> contacts = new ArrayList<Contact>();
		//contacts = this.contactService.listContacts(user_id);
		//modelAndView.addObject("contactList", contacts);
		// System.out.println("remove "+contacts.size());
		modelAndView.setViewName("redirect:/project/contacts");
		// return "redirect:/project/contacts";
		return modelAndView;
	}

	@RequestMapping("/project/contact/edit/{user_id}/{contact_id}")
	public ModelAndView editContact(@PathVariable("user_id") UUID user_id, @PathVariable("contact_id") UUID contact_id,
			ModelAndView modelAndView, RedirectAttributes redirectAttributes) {

		//	ModelAndView modelAndView = new ModelAndView();
		//		redirectAttributes.addFlashAttribute("user_id", user_id);
		//		redirectAttributes.addFlashAttribute("contact_id", contact_id);
		String user_email = this.accountService.getAccountById(user_id).getEmail();
		modelAndView.addObject("user_email", user_email);
		modelAndView.addObject("contact_id", contact_id);

		//		System.out.println("user_id"+user_id);
		//		System.out.println("contact_id"+contact_id);
		modelAndView.setViewName("project/EditContact");
		return modelAndView;
		// return "redirect:/project/EditContact";
	}

	@RequestMapping("/project/contact/add/{user_id}")
	public ModelAndView addContact(@PathVariable("user_id") UUID user_id, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {

		//	ModelAndView modelAndView = new ModelAndView();
		//		redirectAttributes.addFlashAttribute("user_id", user_id);
		//		redirectAttributes.addFlashAttribute("contact_id", contact_id);
		String user_email = this.accountService.getAccountById(user_id).getEmail();
		modelAndView.addObject("user_email", user_email);

		System.out.println("user_id"+user_id);
		modelAndView.setViewName("project/addContact");
		return modelAndView;
		// return "redirect:/project/EditContact";
	}



	@RequestMapping(value = "/project/invitation/addContact", 
			method = RequestMethod.POST)

	public @ResponseBody String addContactInInvitationTemplate(
			@RequestParam("FirstName") String FirstName,
			@RequestParam("LastName") String LastName,
			@RequestParam("Email") String Email) {
		String response ="";
		if(FirstName.equals("")){
			response = "Please input first name!";
		}else if (LastName.equals("")) {
			response = "Please input last name!";
		}else if (this.contactService.checkContactExistByEmail(Email,Utilities.getCurrentUserDetails().getId())) {
			response = Email+" already exsits!";
		}else{
			System.out.println(Utilities.getCurrentUserDetails());
			System.out.println(FirstName);
			Contact contact = new Contact(Utilities.getCurrentUserDetails().getId(),FirstName,LastName,Email);
			this.contactService.addContact(contact);
			response =FirstName +" "+LastName + " has been added to your contact list!";
		}

		//		logger.debug(person.toString());
		//		return new JsonResponse("OK","");
		return response;
	}

	/**
	 * construct JSON data
	 * @param contacts
	 */
	private StringBuffer constructJSON(List<Contact> contacts){
		StringBuffer sb = new StringBuffer();
		if (contacts.size()>0) {

			sb.append("{\"contacts\"");
			sb.append(":[");
			for (Contact contact : contacts) {
				sb.append("{");
				sb.append("\"");
				sb.append("contact");
				sb.append("\"");
				sb.append(":[");
				sb.append("\"");
				sb.append(contact.getContactId().toString());
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(contact.getFirst_name());
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(contact.getLast_name());
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(contact.getParticipant_email());
				sb.append("\"");
				sb.append("]},");

			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("]}");
			//			String response = "{\"contacts\":[{\"contactId\":[\"john\",\"smith\"]},{\"contactId1\":[\"john1\",\"smith1\"]}]}";
			System.out.println(""+sb.toString());
		}else{
			sb.append("{\"contacts\""+":"+"[]}");
		//	return "{\"contacts\""+":"+"[]}";
		}
		return sb;
	}
	//TODO
	//this url needs to be changed
	@RequestMapping(value = "/project/invitation/viewContacts", 
			method = RequestMethod.GET,produces = "application/json")

	public @ResponseBody String viewContactsInInvitation() {
		//		JsonResponse response = new JsonResponse();
		List<Contact> contacts = this.contactService.listContacts(Utilities.getCurrentUserDetails().getId());
		System.out.println("how many contacts"+contacts);
		StringBuffer sb = constructJSON(contacts);
//		if (contacts.size()>0) {
//
//			sb.append("{\"contacts\"");
//			sb.append(":[");
//			for (Contact contact : contacts) {
//				sb.append("{");
//				sb.append("\"");
//				sb.append("contact");
//				sb.append("\"");
//				sb.append(":[");
//				sb.append("\"");
//				sb.append(contact.getContactId().toString());
//				sb.append("\"");
//				sb.append(",");
//				sb.append("\"");
//				sb.append(contact.getFirst_name());
//				sb.append("\"");
//				sb.append(",");
//				sb.append("\"");
//				sb.append(contact.getLast_name());
//				sb.append("\"");
//				sb.append(",");
//				sb.append("\"");
//				sb.append(contact.getParticipant_email());
//				sb.append("\"");
//				sb.append("]},");
//
//			}
//			sb.deleteCharAt(sb.length()-1);
//			sb.append("]}");
//			//			String response = "{\"contacts\":[{\"contactId\":[\"john\",\"smith\"]},{\"contactId1\":[\"john1\",\"smith1\"]}]}";
//			System.out.println(""+sb.toString());
//		}else{
//			return "{\"contacts\""+":"+"[]}";
//		}

		//		logger.debug(person.toString());
		//		return new JsonResponse("OK","");
		return sb.toString();
	}

	@RequestMapping(value = "/invitation/viewInvitedContacts/{projectId}", 
			method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody JSONObject viewInvitedContacts(@PathVariable UUID projectId) {

		//		Map<String, List<Invitation>> map = this.invitationService.listInvitations();
		//		List<Invitation> invitations = map.get(this.projectService.getProjectById(projectId).getTitle());

		List<Invitation> invitations = this.invitationService.listContactsWithStatus(projectId);
		List<JSONObject> innerObjects = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();
		for (Invitation invitation : invitations) {
			JSONObject jsonInnerObject = new JSONObject();
			List<String> contacts = new ArrayList<>();
			contacts.add(String.valueOf(invitation.getContactId()));
			contacts.add(invitation.getFirstName());
			contacts.add(invitation.getLastName());
			contacts.add(invitation.getContactEmail());
			contacts.add(invitation.getInvitationStatus());
			//			contacts.add(invitation.getReasonforAcceptance());
			//			contacts.add(invitation.getReasonForDecline());

			jsonInnerObject.put("contact", contacts);
			System.out.println("jsoninnerobject "+jsonInnerObject);
			innerObjects.add(jsonInnerObject);
		}
		jsonObject.put("contacts", innerObjects);
		System.out.println("jsonobject" + jsonObject);
		return jsonObject;
	}

	@RequestMapping(value = "/project/invitation/deleteContact/{contactId}", 
			method = RequestMethod.GET)

	public @ResponseBody String deleteContactInInvitation(@PathVariable String contactId) {

		Contact contact = this.contactService.getContactById(UUID.fromString(contactId));
		this.contactService.removeContact(UUID.fromString(contactId));
		String response = contact.getFirst_name()+" "+contact.getLast_name()+" has been deleted!";
		return response;
	}

	@RequestMapping(value = "/project/restEditContact/{contactId}", 
			method = RequestMethod.GET,produces = "application/json")

	public @ResponseBody JSONObject restEditContact(@PathVariable UUID contactId) {
		Contact contact = this.contactService.getContactById(contactId);
		JSONObject obj = new JSONObject();
		obj.put("contactId", contact.getContactId());
		obj.put("FirstName", contact.getFirst_name());
		obj.put("LastName", contact.getLast_name());
		obj.put("PEmail", contact.getParticipant_email());
		System.out.println(obj);
		return obj;
	}

	@RequestMapping(value = "/project/restUpdateContact", 
			method = RequestMethod.POST)
	public @ResponseBody String restUpdateContact(@RequestParam("ContactId") String contactId,
			@RequestParam ("FirstName") String firstName,
			@RequestParam ("LastName") String lastName,
			@RequestParam ("Email") String pEmail) {
		Contact contact = new Contact();
		contact.setContactId(UUID.fromString(contactId));
		contact.setUserId(Utilities.getCurrentUserDetails().getId());
		contact.setFirst_name(firstName);
		contact.setLast_name(lastName);
		contact.setParticipant_email(pEmail);
		this.contactService.updateContact(contact);
		System.out.println("updated"+contact);
		String response = firstName+" "+lastName+" has been updated!";
		//		System.out.println(obj);
		return response;
	}

	/**
	 * administrator edit contact
	 * 
	 */
	//administrator adds a contact
	@RequestMapping(value = "/admin/contact/new/{userId}", 
			method = RequestMethod.POST)

	public @ResponseBody String adminAddNewContact(@PathVariable UUID userId,
			@RequestParam("FirstName") String FirstName,
			@RequestParam("LastName") String LastName,
			@RequestParam("Email") String Email) {
		String response ="";
		if(FirstName.equals("")){
			response = "Please input first name!";
		}else if (LastName.equals("")) {
			response = "Please input last name!";
		}else if (this.contactService.checkContactExistByEmail(Email,userId)) {
			response = Email+" already exsits!";
		}else{
			System.out.println(Utilities.getCurrentUserDetails());
			System.out.println(FirstName);
			Contact contact = new Contact(userId,FirstName,LastName,Email);
			this.contactService.addContact(contact);
			response =FirstName +" "+LastName + " has been added to your contact list!";
		}

		//		logger.debug(person.toString());
		//		return new JsonResponse("OK","");
		return response;
	}
	
	/**
	 * view all contacts by admin
	 */
	
	@RequestMapping(value = "/admin/viewContacts/{userId}", 
			method = RequestMethod.GET,produces = "application/json")

	public @ResponseBody String viewContactsAdmin(@PathVariable UUID userId) {
		//		JsonResponse response = new JsonResponse();
		List<Contact> contacts = this.contactService.listContacts(userId);
//		System.out.println("how many contacts"+contacts);
		StringBuffer sb = constructJSON(contacts);

		return sb.toString();
	}

	/**
	 * edit contact by admin
	 */
	
	@RequestMapping(value = "/admin/contact/edit/{userId}", 
			method = RequestMethod.POST)
	public @ResponseBody String editContactAdmin(@PathVariable UUID userId,@RequestParam("ContactId") String contactId,
			@RequestParam ("FirstName") String firstName,
			@RequestParam ("LastName") String lastName,
			@RequestParam ("Email") String pEmail) {
		Contact contact = new Contact();
		contact.setContactId(UUID.fromString(contactId));
		contact.setUserId(userId);
		contact.setFirst_name(firstName);
		contact.setLast_name(lastName);
		contact.setParticipant_email(pEmail);
		this.contactService.updateContact(contact);
		System.out.println("updated"+contact);
		String response = firstName+" "+lastName+" has been updated!";
		//		System.out.println(obj);
		return response;
	}
	
	/**
	 * delete contact by admin
	 */
	
	@RequestMapping(value = "/admin/delete/contact/{contactId}", 
			method = RequestMethod.GET)

	public @ResponseBody String deleteContactByAdmin(@PathVariable String contactId) {

		Contact contact = this.contactService.getContactById(UUID.fromString(contactId));
		this.contactService.removeContact(UUID.fromString(contactId));
		String response = contact.getFirst_name()+" "+contact.getLast_name()+" has been deleted!";
		return response;
	}
}
