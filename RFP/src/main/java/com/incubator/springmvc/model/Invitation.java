package com.incubator.springmvc.model;

import java.util.UUID;

public class Invitation extends InvitationTemplate{
	private UUID projectId;
	private UUID contactId;

	
	private String contactName;
	private String firstName;
	private String lastName;
	private String contactEmail;

	private String previewLink;
	private String invitationStatus;
	private String offerStatus;
	private Double offerPrice;

	private String reasonForDecline;
	private String reasonForAcceptance;

	
	public Double getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public UUID getProjectId() {
		return projectId;
	}
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}
	public UUID getContactId() {
		return contactId;
	}
	public void setContactId(UUID contactId) {
		this.contactId = contactId;
	}

	public String getReasonForDecline() {
		return reasonForDecline;
	}
	public void setReasonForDecline(String reasonForDecline) {
		this.reasonForDecline = reasonForDecline;
	}
	public String getReasonForAcceptance() {
		return reasonForAcceptance;
	}
	public void setReasonForAcceptance(String reasonforAcceptance) {
		this.reasonForAcceptance = reasonforAcceptance;
	}

	public String getPreviewLink() {
		return previewLink;
	}
	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}
	public String getInvitationStatus() {
		return invitationStatus;
	}
	public void setInvitationStatus(String invitationStatus) {
		this.invitationStatus = invitationStatus;
	}
	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
//	@Override
//	public String toString() {
//		return "Invitation [projectId=" + projectId + ", contactId=" + contactId + ", projectName=" + projectName
//				+ ", contactName=" + contactName + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", contactEmail=" + contactEmail + ", previewLink=" + previewLink + ", invitationStatus="
//				+ invitationStatus + ", offerStatus=" + offerStatus + ", reasonForDecline=" + reasonForDecline
//				+ ", reasonforAcceptance=" + reasonForAcceptance + "]";
//	}


}
