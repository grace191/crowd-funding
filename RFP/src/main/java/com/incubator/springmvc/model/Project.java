package com.incubator.springmvc.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.access.prepost.PreInvocationAttribute;

public class Project {
	
	
	
	@Id
	@GeneratedValue
//	@Column(name = "UUID")
	private UUID id;
	
	private String title;
	private UUID imageId;
	private String summary;
	private String description;
	private List<UUID> attachment;
	private String successCriteria;
	private String inputValue;
	private String projectStatus;
	private String user;
	private String paymentStatus;
	private String paymentAmount;
	private String role;
	private Integer numberOfParticipants;
	
	
	public Integer getNumberOfParticipants() {
		return numberOfParticipants;
	}
	public void setNumberOfParticipants(Integer numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public UUID getImageId() {
		return imageId;
	}
	public void setImageId(UUID imageId) {
		this.imageId = imageId;
	}

	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	private String invitationStatus;

	private String previewLink;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UUID> getAttachment() {
		return attachment;
	}
	public void setAttachment(List<UUID> attachment) {
		this.attachment = attachment;
	}
	public String getSuccessCriteria() {
		return successCriteria;
	}
	public void setSuccessCriteria(String successCriteria) {
		this.successCriteria = successCriteria;
	}
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	
	public String getInvitationStatus() {
		return invitationStatus;
	}
	public void setInvitationStatus(String invitationStatus) {
		this.invitationStatus = invitationStatus;
	}
	
	
	public String getPreviewLink() {
		return previewLink;
	}
	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", imageId=" + imageId + ", summary=" + summary
				+ ", description=" + description + ", attachment=" + attachment + ", successCriteria=" + successCriteria
				+ ", inputValue=" + inputValue + ", projectStatus=" + projectStatus + ", user=" + user
				+ ", paymentStatus=" + paymentStatus + ", paymentAmount=" + paymentAmount + ", role=" + role
				+ ", numberOfParticipants=" + numberOfParticipants + ", invitationStatus=" + invitationStatus
				+ ", previewLink=" + previewLink + "]";
	}
	
	
	
	


	
}

