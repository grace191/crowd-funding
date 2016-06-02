package com.incubator.springmvc.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Contact {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private UUID contactId;
	private UUID userId;
	private String first_name;
	private String last_name;
	private String fullName;
	private String participant_email;
	
	
	public Contact() {
		
	}
	
	
	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public Contact(UUID userId, String first_name, String last_name, String participant_email) {
		this.userId = userId;
		this.first_name = first_name;
		this.last_name = last_name;
		this.participant_email = participant_email;
	}
	public UUID getContactId() {
		return contactId;
	}
	public void setContactId(UUID contactId) {
		this.contactId = contactId;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getParticipant_email() {
		return participant_email;
	}
	public void setParticipant_email(String participant_email) {
		this.participant_email = participant_email;
	}
	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", userId=" + userId + ", first_name=" + first_name + ", last_name="
				+ last_name + ", participant_email=" + participant_email + "]";
	}

}
