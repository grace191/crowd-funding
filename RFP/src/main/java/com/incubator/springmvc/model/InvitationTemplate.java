package com.incubator.springmvc.model;

import java.util.List;


public class InvitationTemplate {
	private String sender;
	private String emailSubject;
	private List<String> receivers;
	private String previewLink;
	private String projectTitle;
	private String summary = "Yes";
	private String threshold;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public List<String> getReceivers() {
		return receivers;
	}
	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}
	public String getPreviewLink() {
		return previewLink;
	}
	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	@Override
	public String toString() {
		return "Invitation [sender=" + sender + ", emailSubject=" + emailSubject + ", receivers=" + receivers
				+ ", previewLink=" + previewLink + ", projectTitle=" + projectTitle + ", summary=" + summary + "]";
	}



}
