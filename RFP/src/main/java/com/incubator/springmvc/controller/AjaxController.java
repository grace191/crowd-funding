package com.incubator.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.loader.custom.Return;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.incubator.springmvc.dao.ContactDAO;
import com.incubator.springmvc.model.Contact;

//@Controller
//public class AjaxController {
//	private ContactDAO contactDAO;
//	
//	public void setContactDAO(ContactDAO contactDAO) {
//		this.contactDAO = contactDAO;
//	}
//
//
//
//	@RequestMapping(value = "/admin/addContact", method = RequestMethod.POST
////			, headers = {"Content-type=application/json"}
//	)
////	@ResponseBody
//	// public String addContact(@RequestParam("User_Email") String password) {
//	public String addContact(@RequestParam("FirstName") String FirstName,
//			@RequestParam("flag") String flag, HttpServletRequest request, Model model) {
//		request.setAttribute("flag", flag);
//		System.out.println(request.getQueryString());
//	//	model.addAttribute("flag", flag);
////		List<Contact> contacts = new ArrayList<Contact>();
////		Contact contact = new Contact();
////		contact.setUser_Email("temp");
////		contact.setFirstName(FirstName);
/////*		contact.setLastName(LastName);
////		contact.setParticipant_Email(Participant_Email);*/
////	//	contactDAO.addContact(contact);
////
////		 System.out.println(contact);
//
//		 return flag;
//	}
//	
//}
