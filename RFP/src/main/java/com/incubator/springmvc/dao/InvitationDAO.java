package com.incubator.springmvc.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.incubator.springmvc.model.Contact;
import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.model.InvitationTemplate;

public interface InvitationDAO {
	public void addInvitation(UUID projectId,List<Contact> contacts,InvitationTemplate invitation);
	public Map<String, List<Invitation>> listInvitations(UUID userId);
	public Invitation getInvitationById(UUID projectId);
	public List<Invitation> listContactsWithStatus(UUID projectId);
	public void editInvitationContacts(UUID projectId,InvitationTemplate invitation);
	public void updateFeedBack(String flag, String feedback, UUID projectId,UUID contactId);
	public void updateInvitationStatus(String status, UUID projectId,UUID contactId);
	public void updateOfferStatus(String status, UUID projectId,UUID contactId);
	public List<Invitation> listInvitationsForAdmin();
	public void updateOfferStatusAndFeedback(String status, String feedback, UUID projectId,UUID contactId);

}
