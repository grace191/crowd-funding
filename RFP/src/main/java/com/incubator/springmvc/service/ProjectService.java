package com.incubator.springmvc.service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.model.Project;
import com.incubator.springmvc.model.Register;

public interface ProjectService {
	public UUID addProject(Project project, Principal principal);
	public List<Project> listProjectsForCreators(UUID creatorId);
	public boolean validateTitle(Project project);
	public void setAttachmentPath(UUID projectId);
	public Project getProjectById(UUID projectId);
	public void changeInvitationStatus(String status,UUID projectId);
	public void deleteProjectById(UUID projectId);
	public void deleteAccountsProjectsById(UUID projectId);
	public void updateProject(Project project) ;
	public Project getProjectByTitle(String title);
	public void editPreviewLink(List<Project> projects,String inPreviewLink);
	public void addPayment(UUID userId,String email,String paymentStatus, String amount, String token);
	public boolean checkIfUserPaid();
	public List<Invitation> listProjectsForUsers(UUID contactId);
	public List<Project> listProjectsForAdmin();
	public void updateImageIdToProject(UUID imageId,UUID projectId);
	public List<Project> listProjectsForAllUsers(UUID userId, String permission);
}
