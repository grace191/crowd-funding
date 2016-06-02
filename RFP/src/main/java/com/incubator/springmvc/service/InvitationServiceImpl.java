package com.incubator.springmvc.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incubator.springmvc.dao.InvitationDAO;
import com.incubator.springmvc.model.Contact;
import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.model.InvitationTemplate;

@Service
public class InvitationServiceImpl implements InvitationService{
	private InvitationDAO invitationDAO;

	@Autowired
	public void setInvitationDAO(InvitationDAO invitationDAO) {
		this.invitationDAO = invitationDAO;
	}



	public void addInvitation(UUID projectId, List<Contact> contacts,InvitationTemplate invitation) {
		this.invitationDAO.addInvitation(projectId, contacts, invitation);
		
	}



	@Override
	public Map<String, List<Invitation>> listInvitations(UUID userId) {
		return this.invitationDAO.listInvitations(userId);
	}



	@Override
	public Invitation getInvitationById(UUID projectId) {
		return this.invitationDAO.getInvitationById(projectId);
	}



	@Override
	public List<Invitation> listContactsWithStatus(UUID projectId) {
		return this.invitationDAO.listContactsWithStatus(projectId);
	}



	@Override
	public void editInvitationContacts(UUID projectId, InvitationTemplate invitation) {
		this.invitationDAO.editInvitationContacts(projectId, invitation);
		
	}



	@Override
	public void updateFeedBack(String flag, String feedback, UUID projectId, UUID contactId) {
		this.invitationDAO.updateFeedBack(flag, feedback, projectId, contactId);
		
	}



	@Override
	public void updateInvitationStatus(String status, UUID projectId, UUID contactId) {
		this.invitationDAO.updateInvitationStatus(status, projectId, contactId);
		
	}



	@Override
	public void updateOfferStatus(String status, UUID projectId, UUID contactId) {
		this.invitationDAO.updateOfferStatus(status, projectId, contactId);
		
	}



	@Override
	public List<Invitation> listInvitationsForAdmin() {
		return this.invitationDAO.listInvitationsForAdmin();
	}



	@Override
	public void updateOfferStatusAndFeedback(String status, String feedback, UUID projectId, UUID contactId) {
		 this.invitationDAO.updateOfferStatusAndFeedback(status, feedback, projectId, contactId);
		
	}

}
