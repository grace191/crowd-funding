package com.incubator.springmvc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.incubator.springmvc.dao.ContactDAO;
import com.incubator.springmvc.model.Contact;

@Service("contactService")
@Transactional
public class ContactServiceImlp implements ContactService{
	private ContactDAO contactDAO;

	@Autowired
	public void setContactDAO(ContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

	public boolean addContact(Contact contact) {
		this.contactDAO.addContact(contact);
		return true;
	}

	public List<Contact> listContacts(Object obj) {
		return this.contactDAO.listContacts(obj);

	}

	public void removeContact(UUID id) {
		this.contactDAO.removeContact(id);
		
	}

	public void updateContact(Contact contact){
		this.contactDAO.updateContact(contact);
	}
//	public List<Contact> findContactByEmail(String userEmail) {
//		return this.contactDAO.findContactByEmail(userEmail);
//		
//	}

	@Override
	public boolean checkContactExistByEmail(String email,UUID userId) {
		return contactDAO.checkContactExistByEmail(email, userId);
	}

	@Override
	public Contact getContactById(UUID contactId) {
		return this.contactDAO.getContactById(contactId);
	}

}
