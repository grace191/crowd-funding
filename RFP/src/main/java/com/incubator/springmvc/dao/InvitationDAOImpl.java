package com.incubator.springmvc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.incubator.springmvc.model.Contact;
import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.model.InvitationTemplate;
import com.incubator.springmvc.utils.DeclareConstant;

@Repository
public class InvitationDAOImpl implements InvitationDAO{
	private JdbcTemplate jdbcTemplate = null;
	private ProjectDAO projectDAO;
	private AccountDao accountDao;
	private ContactDAO contactDao;



	@Autowired
	public void setContactDao(ContactDAO contactDao) {
		this.contactDao = contactDao;
	}

	@Autowired
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Autowired
	public InvitationDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public void addInvitation(UUID projectId,List<Contact> contacts, InvitationTemplate invitation){
		//	System.out.println("add invitaiton");
		String insert = "insert into invitations(project_id, contact_id, invitation_status, reason_for_decline,reason_for_acceptance, offer_status) values(?,?,?,?,?,?);";
		if (contacts.size()>0) {
			if (!checkIfInvitationInserted(projectId, contacts.get(0).getContactId())) {
				for (Contact contact : contacts) {
					jdbcTemplate.update(insert,projectId,contact.getContactId(),DeclareConstant.INVITED,DeclareConstant.NOTDECLOSED,DeclareConstant.NOTDECLOSED,DeclareConstant.INITIAL_STATUS);

				}
			}
		}

		if (!checkIfInvitationTemplateExist(projectId)) {
			String insertIntoTemplate = "insert into invitation_template(project_id, email_subject,include_summary,threshold) values(?,?,?,?);";
			jdbcTemplate.update(insertIntoTemplate,projectId,invitation.getEmailSubject(),invitation.getSummary(),invitation.getThreshold());
		}else{
			updateInvitationTemplate(projectId, invitation);
		}
	}

	/**
	 * check if the invitation has been inserted. Since the project creator may send the invitation several times.
	 */
	public boolean checkIfInvitationInserted(UUID projectId, UUID contactId){
		String query = "select count(*) from invitations where project_id=? and contact_id=?;";
		Integer count = jdbcTemplate.queryForObject(query, new Object[]{projectId,contactId},Integer.class);
		boolean flag = false;
		if (count>0) {
			flag= true;
		}
		return flag;
	}

	/**
	 * check if the invitation template has been created. Since the project creator may send the invitation several times.
	 */
	public boolean checkIfInvitationTemplateExist(UUID projectId){
		String query = "select count(*) from invitation_template where project_id=?;";
		Integer count = jdbcTemplate.queryForObject(query, new Object[]{projectId},Integer.class);
		boolean flag = false;
		if (count>0) {
			flag= true;
		}
		return flag;
	}

	public void editInvitationContacts(UUID projectId,InvitationTemplate invitation){
		String insert = "insert into invitations(project_id, contact_id, invitation_status, offer_status, reason_for_decline,reason_for_acceptance) values(?,?,?,?,?,?);";

		List<Invitation> invitationWithStatus = listContactsWithStatus(projectId);
		for (Invitation invitation2 : invitationWithStatus) {
			if (invitation2.getInvitationStatus().equals(DeclareConstant.INITIAL_STATUS)) {
				jdbcTemplate.update(insert,projectId,invitation2.getContactId(),DeclareConstant.INVITED,DeclareConstant.INITIAL_STATUS,DeclareConstant.NOTDECLOSED,DeclareConstant.NOTDECLOSED);
			}
		}

		updateInvitationTemplate(projectId, invitation);



	}
	/**
	 * update invitation template
	 */
	public void updateInvitationTemplate(UUID projectId,InvitationTemplate invitation){
		String insertIntoTemplate = "update invitation_template set email_subject =?, include_summary=? ,threshold=? where project_id=?;";
		jdbcTemplate.update(insertIntoTemplate,invitation.getEmailSubject(),invitation.getSummary(),invitation.getThreshold(),projectId);
	}

	public Invitation getInvitationById(UUID projectId){
		//	System.out.println("add invitaiton");
		String query = "select * from invitation_template where project_id=?;";
		Invitation invitation = new Invitation();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query,projectId);
		for (Map<String, Object> row : rows) {
			invitation.setEmailSubject(String.valueOf(row.get("email_subject")));
			invitation.setThreshold(String.valueOf(row.get("threshold")));
			invitation.setSummary(String.valueOf(row.get("include_summary")));
		}
		return invitation;

	}

	public Map<String, List<Invitation>> listInvitations(UUID userId){
		Map<String, List<Invitation>> invitations = new HashMap<>();
		String queryProjectIds ="select project_id from accounts_projects where user_id=?;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryProjectIds,userId);
		//	List<UUID> projectIds = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			if (null != row.get("project_id")) {
				UUID projectId = UUID.fromString(String.valueOf(row.get("project_id")));

				//			projectIds.add(UUID.fromString(String.valueOf(row.get("project_id"))));
				//		System.out.println("projectid "+projectId);
				String queryInvitations = "select p.title, c.contact_id, c.first_name, c.last_name, c.participant_email, "
						+ "i.invitation_status, i.reason_for_decline, i.reason_for_acceptance from projects p, "
						+ "contacts c, invitations i where p.id=i.project_id and i.contact_id=c.contact_id and "
						+ "i.project_id=?;";
				List<Map<String, Object>> invitationRows = jdbcTemplate.queryForList(queryInvitations,projectId);
				System.out.println("rows size "+invitationRows.size());
				List<Invitation> invitationList = new ArrayList<>();
				String title = "";
				for (Map<String, Object> invitationRow : invitationRows) {
					Invitation invitation = new Invitation();
					UUID contactId = UUID.fromString(String.valueOf(invitationRow.get("contact_id")));
					invitation.setContactId(contactId);
					invitation.setProjectId(projectId);
					String firstName = String.valueOf(invitationRow.get("first_name"));
					String lastName = String.valueOf(invitationRow.get("last_name"));
					//					invitation.setContactId(UUID.fromString(String.valueOf(invitationRow.get("contact_id"))));
					String contactName = firstName+" "+lastName;
					//					System.out.println("name "+contactName);
					invitation.setContactName(contactName);
					invitation.setFirstName(firstName);
					invitation.setLastName(lastName);
					invitation.setContactEmail(String.valueOf(invitationRow.get("participant_email")));
					invitation.setProjectTitle(String.valueOf(invitationRow.get("title")));
					invitation.setInvitationStatus(String.valueOf(invitationRow.get("invitation_status")));
					String offerStatus = getOfferStatus(projectId, contactId);
					invitation.setOfferStatus(offerStatus);
					if (null != offerStatus && offerStatus.equals(DeclareConstant.ACCEPTOFFER)) {
						invitation.setInvitationStatus(DeclareConstant.SUBSCRIBED);
						updateInvitationStatus(DeclareConstant.SUBSCRIBED, projectId, contactId);
					}

					invitation.setReasonForDecline(String.valueOf(invitationRow.get("reason_for_decline")));
					invitation.setReasonForAcceptance(String.valueOf(invitationRow.get("reason_for_acceptance")));
					title = String.valueOf(invitationRow.get("title"));
					invitationList.add(invitation);
				}
				if (!title.equals("")) {
					invitations.put(title, invitationList);
				}


			}
		}
		for (Map.Entry<String, List<Invitation>> entry: invitations.entrySet()) {
			System.out.println("key "+entry.getKey());
			System.out.println("value "+entry.getValue());
			System.out.println("size "+entry.getValue().size());
		}
		return invitations;
	}

	public List<Invitation> listContactsWithStatus(UUID projectId){
		List<Invitation> invitations = new ArrayList<>();
		String query = "select c.contact_id, c.first_name, c.last_name, c.participant_email, i.invitation_status from contacts c left join " 
				+"invitations i on c.contact_id=i.contact_id and i.project_id=?;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query,projectId);
		for (Map<String, Object> row : rows) {
			if (null != row.get("contact_id")) {

				Invitation invitation = new Invitation();
				invitation.setProjectId(projectId);
				invitation.setContactId(UUID.fromString(String.valueOf(row.get("contact_id"))));

				invitation.setFirstName(String.valueOf(row.get("first_name")));
				invitation.setLastName(String.valueOf(row.get("last_name")));
				invitation.setContactEmail(String.valueOf(row.get("participant_email")));
				if (null != row.get("invitation_status")) {
					invitation.setInvitationStatus(String.valueOf(row.get("invitation_status")));
				}else{
					invitation.setInvitationStatus(DeclareConstant.INITIAL_STATUS);
				}

				invitations.add(invitation);

			}
		}
		//		for (Invitation invitation : invitations) {
		//			System.out.println("invitatinWithStatus "+invitation);
		//		}
		return invitations;
	}

	public void updateFeedBack(String flag, String feedback, UUID projectId,UUID contactId){
		if (!feedback.equals("")) {
			if (flag.equals(DeclareConstant.REASON_FOR_ACCEPTANCE)) {

				String update = "update invitations set invitation_status=?, reason_for_acceptance =? where project_id=? and contact_id=?;";
				jdbcTemplate.update(update,DeclareConstant.REASON_FOR_ACCEPTANCE, feedback, projectId,contactId);


			}else if (flag.equals(DeclareConstant.REASON_FOR_DECLINE)) {
				String update = "update invitations set invitation_status=?, reason_for_decline =? where project_id=? and contact_id=?;";
				jdbcTemplate.update(update,DeclareConstant.REASON_FOR_DECLINE,feedback, projectId,contactId);
			}
		}
	}

	//update contact invitation status
	public void updateInvitationStatus(String status, UUID projectId,UUID contactId){
		String update = "update invitations set invitation_status=? where project_id=? and contact_id=?;";
		jdbcTemplate.update(update,status,projectId,contactId);
	}

	//update user offer status
	public void updateOfferStatus(String status, UUID projectId,UUID contactId){
		String update = "update invitations set offer_status=? where project_id=? and contact_id=?;";
		jdbcTemplate.update(update,status,projectId,contactId);
	}

	//update user offer status and feedback
	public void updateOfferStatusAndFeedback(String status, String feedback, UUID projectId,UUID contactId){
		if (!feedback.equals("")) {
			if (status.equals(DeclareConstant.REASON_FOR_ACCEPTANCE)) {

				String update = "update invitations set offer_status=?, reason_for_acceptance =? where project_id=? and contact_id=?;";
				jdbcTemplate.update(update,DeclareConstant.REASON_FOR_ACCEPTANCE, feedback, projectId,contactId);


			}else if (status.equals(DeclareConstant.REASON_FOR_DECLINE)) {
				String update = "update invitations set offer_status=?, reason_for_decline =? where project_id=? and contact_id=?;";
				jdbcTemplate.update(update,DeclareConstant.REASON_FOR_DECLINE,feedback, projectId,contactId);
			}
		}
	}

	//query offer status
	public String getOfferStatus(UUID projectId, UUID contactId){
		String query = "select offer_status from invitations where project_id=? and contact_id=?";
		String offerStatus = jdbcTemplate.queryForObject(query, new Object[]{projectId, contactId},String.class);
		return offerStatus;

	}


	//list all invitations for admin
	public List<Invitation> listInvitationsForAdmin(){
		List<Invitation> invitations = new ArrayList<>();
		String queryInvitations ="select * from invitations;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryInvitations);
		//	List<UUID> projectIds = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			Invitation invitation = new Invitation();
			if (rows.size()>0) {
				UUID projectId = UUID.fromString(String.valueOf(row.get("project_id")));
				invitation.setProjectId(projectId);
				invitation.setProjectTitle(this.projectDAO.getProjectById(projectId).getTitle());
				Contact contact = this.contactDao.getContactById(UUID.fromString(String.valueOf(row.get("contact_id"))));
				invitation.setFirstName(contact.getFirst_name());
				invitation.setLastName(contact.getLast_name());
				invitation.setContactEmail(contact.getParticipant_email());
				invitation.setInvitationStatus(String.valueOf(row.get("invitation_status")));
				invitation.setReasonForDecline(String.valueOf(row.get("reason_for_decline")));
				invitation.setReasonForAcceptance(String.valueOf(row.get("reason_for_acceptance")));
				invitation.setOfferStatus(String.valueOf(row.get("offer_status")));
				invitations.add(invitation);
			}

		}
		for (Invitation invitation : invitations) {
			System.out.println(invitation);
		}
		return invitations;
	}
}
