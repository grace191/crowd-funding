package com.incubator.springmvc.service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.incubator.springmvc.dao.ProjectDAO;
import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.model.Project;
import com.incubator.springmvc.model.Register;

@Service("projectService")

public class ProjectServiceImpl implements ProjectService {
	
	private ProjectDAO projectDAO;	
	
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	@Autowired
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}


	@Transactional
	public UUID addProject(Project project, Principal principal) {
		return this.getProjectDAO().addProject(project, principal);
	}

	public List<Project> listProjectsForCreators(UUID creatorId) {
		return this.getProjectDAO().listProjectsForCreators(creatorId);
	}

	public boolean validateTitle(Project project) {
		return this.getProjectDAO().validateTitle(project);
	}

	public void setAttachmentPath(UUID projectId) {
		this.projectDAO.setAttachmentPath(projectId);
		
	}

	public Project getProjectById(UUID projectId) {
		return this.projectDAO.getProjectById(projectId);
				
	}

	public void changeInvitationStatus(String status,UUID projectId) {
		this.projectDAO.changeInvitationStatus(status,projectId);
		
	}


	public void editPreviewLink(List<Project> projects,String inPreviewLink) {
		this.projectDAO.editPreviewLink(projects,inPreviewLink);
		
	}

	@Override
	public void deleteProjectById(UUID projectId) {
		this.projectDAO.deleteProjectById(projectId);
		
	}

	@Override
	public void deleteAccountsProjectsById(UUID projectId) {
		this.projectDAO.deleteAccountsProjectsById(projectId);
		
	}

	@Override
	public void updateProject(Project project) {
		this.projectDAO.updateProject(project);
		
	}

	@Override
	public Project getProjectByTitle(String title) {
		return this.projectDAO.getProjectByTitle(title);
	}

	@Override
	public void addPayment(UUID userId,String email,String paymentStatus, String amount, String token) {
		this.projectDAO.addPayment(userId, email, paymentStatus, amount, token);
		
	}

	@Override
	public boolean checkIfUserPaid() {
		return this.projectDAO.checkIfUserPaid();
	}

	@Override
	public List<Invitation> listProjectsForUsers(UUID contactId) {
		return this.projectDAO.listProjectsForUsers(contactId);
	}

	@Override
	public List<Project> listProjectsForAdmin() {
		return this.projectDAO.listProjectsForAdmin();
	}

	@Override
	public void updateImageIdToProject(UUID imageId, UUID projectId) {
		this.projectDAO.updateImageIdToProject(imageId, projectId);
		
	}

	@Override
	public List<Project> listProjectsForAllUsers(UUID userId, String permission) {
		return this.projectDAO.listProjectsForAllUsers(userId, permission);
	}

}
