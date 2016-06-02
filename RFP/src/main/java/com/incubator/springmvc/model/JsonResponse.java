package com.incubator.springmvc.model;

import java.util.Arrays;
import java.util.UUID;

public class JsonResponse {
	
	private String contactId;
	private JsonResponse Contact[];
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public JsonResponse[] getContact() {
		return Contact;
	}
	public void setContact(JsonResponse[] contact) {
		Contact = contact;
	}
	@Override
	public String toString() {
		return "JsonResponse [contactId=" + contactId + ", Contact=" + Arrays.toString(Contact) + "]";
	} 
	
	
	

}
