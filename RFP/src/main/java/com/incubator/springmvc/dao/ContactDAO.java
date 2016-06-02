package com.incubator.springmvc.dao;

import java.util.List;
import java.util.UUID;

import com.incubator.springmvc.model.Contact;

public interface ContactDAO {
	public boolean addContact(Contact contact);
	public List<Contact> listContacts(Object obj);
	public void removeContact(UUID id);
//	public List<Contact> findContactByEmail(String userEmail);
	public void updateContact(Contact contact);
	public Contact getContactById(UUID contactId);
	public boolean checkContactExistByEmail(String email,UUID userId);
}
