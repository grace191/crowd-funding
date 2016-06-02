package com.incubator.springmvc.model;

import java.util.UUID;

public class Offer {
	private UUID projectId;
	private UUID contactId;
	private String previewLink;
	private String status;
	private String reasonForDecline;
	private String reasonforAcceptance;
	private String threshold;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReasonForDecline() {
		return reasonForDecline;
	}
	public void setReasonForDecline(String reasonForDecline) {
		this.reasonForDecline = reasonForDecline;
	}
	public String getReasonforAcceptance() {
		return reasonforAcceptance;
	}
	public void setReasonforAcceptance(String reasonforAcceptance) {
		this.reasonforAcceptance = reasonforAcceptance;
	}
	
	public String getPreviewLink() {
		return previewLink;
	}
	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}
	
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	@Override
	public String toString() {
		return "Invitation [projectId=" + projectId + ", contactId=" + contactId + ", status=" + status
				+ ", reasonForDecline=" + reasonForDecline + ", reasonforAcceptance=" + reasonforAcceptance + "]";
	}

	
	
	
	

}
