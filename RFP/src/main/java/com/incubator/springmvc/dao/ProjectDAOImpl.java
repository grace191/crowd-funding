package com.incubator.springmvc.dao;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.incubator.springmvc.model.Invitation;
import com.incubator.springmvc.model.Project;
import com.incubator.springmvc.model.UploadedFile;
import com.incubator.springmvc.utils.DeclareConstant;
import com.incubator.springmvc.utils.Utilities;
import com.incubator.springmvc.utils.UtilitiesS3;

@Repository("projectDAO")
public class ProjectDAOImpl implements ProjectDAO {

	private JdbcTemplate jdbcTemplate = null;
	private UploadedFileDAO uploadedFileDAO;

	@Autowired
	public ProjectDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	@Autowired
	public void setUploadedFileDAO(UploadedFileDAO uploadedFileDAO) {
		this.uploadedFileDAO = uploadedFileDAO;
	}

	public UUID addProject(Project project, Principal principal) {
		String INSERT_SQL = "INSERT INTO projects (title,summary,description,success_criteria, input_value, invitation_status) VALUES (?,?,?,?,?,?);";
		jdbcTemplate.update(INSERT_SQL, project.getTitle(),project.getSummary(),
				project.getDescription(),project.getSuccessCriteria(),project.getInputValue(),DeclareConstant.CREATED);

		//		String insertTo = "insert into accounts_projects (project_id,user_id,email,title,role) values (?,?,?,?,?);";
		String update = "update accounts_projects set project_id=?, title=?, payment_status=?, role=? where email=? and payment_status=?;";
		UUID projectId = getProjectId(project.getTitle());
		jdbcTemplate.update(update,projectId,project.getTitle(),DeclareConstant.PAID, DeclareConstant.ROLE_PCREATOR, principal.getName(),DeclareConstant.PENDING);
		//		updatePaymentStatus(projectId, DeclareConstant.PAID);
		return projectId;
	}

	public void updatePaymentStatus(UUID projectId, String status){
		String update = "update accounts_projects set payment_status = ? where project_id=?;";
		jdbcTemplate.update(update,status, projectId);
	}
	public void updateProject(Project project) {

		if (imageExist(project.getId())) {
			String updateProject = "UPDATE projects SET title=?, summary=?, description=?, success_criteria=?, input_value=?, invitation_Status=? WHERE id=?";
			jdbcTemplate.update(updateProject, project.getTitle(),project.getSummary(),
					project.getDescription(),project.getSuccessCriteria(),project.getInputValue(),project.getInvitationStatus(),project.getId());				
		}else{
			String updateProject = "UPDATE projects SET title=?, summary=?, description=?, success_criteria=?, input_value=?, invitation_Status=?, image_id=? WHERE id=?";
			jdbcTemplate.update(updateProject, project.getTitle(),project.getSummary(),
					project.getDescription(),project.getSuccessCriteria(),project.getInputValue(),project.getInvitationStatus(),null,project.getId());				
		}


	}

	public boolean imageExist(UUID projectId){
		boolean flag = false;
		UploadedFile file = this.uploadedFileDAO.findById(getProjectById(projectId).getImageId());
		//		System.out.println(file.getName()+"null?");
		if (null != file.getName()) {
			flag = true;
		}
		return flag;
	}

	public UUID getProjectId(String title){
		String queryId = "select id from projects where title=?;";
		UUID projectId = jdbcTemplate.queryForObject(queryId, new Object[]{title},UUID.class);
		return projectId;

	}

	//list projects for project creators
	public List<Project> listProjectsForCreators(UUID creatorId) {
		//		String query ="";
		//		query = "select p.id, p.invitation_status, a.title from projects p, accounts_projects a where p.title=a.title AND a.email=?;";

		String query = "select * from accounts_projects where user_id=? and role=?;";

		List<Project> projects = new ArrayList<Project>();
		List<Map<String, Object>> proRows = jdbcTemplate.queryForList(query,creatorId,DeclareConstant.ROLE_PCREATOR);
		for (Map<String, Object> proRow : proRows) {
			Project pro = new Project();
			if (null != proRow.get("title")) {
				pro.setTitle(String.valueOf(proRow.get("title")));
				UUID projectId = UUID.fromString(String.valueOf(proRow.get("project_id")));
				pro.setId(projectId);
				Project queriedP = getProjectById(projectId);
				pro.setInvitationStatus(queriedP.getInvitationStatus());
				pro.setNumberOfParticipants(queriedP.getNumberOfParticipants());
				projects.add(pro);
			}

		}
		//		System.out.println("all projects" + projects);
		return projects;
	}

	//list projects for all users
	public List<Project> listProjectsForAllUsers(UUID userId, String permission) {
		//		String query ="";
		//		query = "select p.id, p.invitation_status, a.title from projects p, accounts_projects a where p.title=a.title AND a.email=?;";

		String query = "select * from accounts_projects where user_id=? and role=?;";

		List<Project> projects = new ArrayList<Project>();
		List<Map<String, Object>> proRows = jdbcTemplate.queryForList(query,userId,permission);
		for (Map<String, Object> proRow : proRows) {
			Project pro = new Project();
			if (null != proRow.get("title")) {
				pro.setTitle(String.valueOf(proRow.get("title")));
				UUID projectId = UUID.fromString(String.valueOf(proRow.get("project_id")));
				pro.setId(projectId);
				Project queriedP = getProjectById(projectId);
				pro.setInvitationStatus(queriedP.getInvitationStatus());
				pro.setNumberOfParticipants(queriedP.getNumberOfParticipants());
				System.out.println("participants "+queriedP.getNumberOfParticipants());
				projects.add(pro);
			}

		}
		//		System.out.println("all projects" + projects);
		return projects;
	}

	//list projects of users, including project id and offer status
	public List<Invitation> listProjectsForUsers(UUID contactId) {
		String query ="select * from invitations where contact_id=?;";


		List<Invitation> projects = new ArrayList<Invitation>();
		List<Map<String, Object>> proRows = jdbcTemplate.queryForList(query,contactId);
		for (Map<String, Object> proRow : proRows) {
			Invitation invitation = new Invitation();
			if (null != proRow.get("project_id")) {
				UUID projectId = UUID.fromString(String.valueOf(proRow.get("project_id")));
				invitation.setProjectId(projectId);
				invitation.setProjectTitle(getProjectById(projectId).getTitle());
				String offerStatus = String.valueOf(proRow.get("offer_status"));
				invitation.setOfferStatus(offerStatus);
				if (offerStatus.equals(DeclareConstant.INITIAL_STATUS)) {
					invitation.setOfferPrice(0.0);
				}else if (offerStatus.equals(DeclareConstant.DECLINEOFFER)) {
					invitation.setOfferPrice(0.0);
				}
				else{
					invitation.setOfferPrice(getOfferPrice(projectId));
				}


				projects.add(invitation);
			}

		}
		//			System.out.println("all projects" + projects);
		return projects;
	}

	//get offer price
	public double getOfferPrice(UUID projectId){
		Integer numberOfParticipants = getNumberOfPartipants(projectId);
		double price =0;
		if (numberOfParticipants>0) {
			price = Double.valueOf(getProjectById(projectId).getInputValue())/numberOfParticipants;
		}
		return price;
	}

	//list all projects for administrator, including project status and payment
	public List<Project> listProjectsForAdmin() {
		String query ="select * from accounts_projects;";


		List<Project> projects = new ArrayList<Project>();
		List<Map<String, Object>> proRows = jdbcTemplate.queryForList(query);
		for (Map<String, Object> proRow : proRows) {
			UUID projectId = null;
			Project project = new Project();
			if (proRows.size()>0) {
				if (null != proRow.get("project_id")) {
					projectId = UUID.fromString(String.valueOf(proRow.get("project_id")));
				}else{
					projectId = null;
				}

				project.setUser(String.valueOf(proRow.get("email")));
				project.setRole(String.valueOf(proRow.get("role")));
				project.setTitle(String.valueOf(proRow.get("title")));
				project.setId(projectId);
				project.setPaymentStatus(String.valueOf(proRow.get("payment_status")));
				project.setPaymentAmount(String.valueOf(proRow.get("payment_amount")));

				projects.add(project);
			}

		}
		//			System.out.println("all projects" + projects);
		return projects;
	}

	public boolean validateTitle(Project project) {
		String query = "select title from projects where title=" + "'" + project.getTitle() + "'";
		List<Map<String, Object>> proRows = jdbcTemplate.queryForList(query);
		//	System.out.println("title prorows" + proRows.size());
		Boolean flag = false;
		if (proRows.size() == 0) {
			flag = true;
		}
		return flag;
	}

	public void updateImageIdToProject(UUID imageId,UUID projectId){
		String query = "UPDATE projects SET image_id= ? WHERE id = ?;";
		jdbcTemplate.update(query, imageId,projectId);
	}

	public void updateAttachmentIdsToProject(List<UUID> attachmentIds,UUID projectId){
		String query = "insert into projects_files (project_id,attachment_id) values (?,?);";
		if (attachmentIds.size()>0) {
			for (UUID attachmentId : attachmentIds) {
				jdbcTemplate.update(query, projectId, attachmentId);
			}
		}

	}

	public void setAttachmentPath(UUID projectId){
		List<UploadedFile> imageList = this.uploadedFileDAO.listImage(UtilitiesS3.accountTempImageDirectory());
		List<UploadedFile> attachList = this.uploadedFileDAO.listFiles(UtilitiesS3.accountTempAttachDirectory());
		UUID imageId = null;
		List<UUID> attachmentIds = new ArrayList<UUID>();
		if (imageList.size()>0) {
			imageId = imageList.get(0).getId();
			updateImageIdToProject(imageId, projectId);
		}
		for (UploadedFile file : attachList) {
			attachmentIds.add(file.getId());
		}
		updateAttachmentIdsToProject(attachmentIds,projectId);
		String imagePath = UtilitiesS3.changeAccountImageDirectory(projectId);
		String attachPath = UtilitiesS3.changeAccountAttachDirectory(projectId);

		this.uploadedFileDAO.updateAttachmentPath(projectId,imageId,attachmentIds,imagePath,attachPath);
		String imageSrc = UtilitiesS3.accountTempImageDirectory();
		String attachSrc = UtilitiesS3.accountTempAttachDirectory();
		String tempFolder = UtilitiesS3.accountTempDirectory();
		System.out.println("imagesrc "+UtilitiesS3.listFileNames(imageSrc));
		System.out.println("attachSrc "+UtilitiesS3.listFileNames(attachSrc));
		System.out.println("tempFolder "+UtilitiesS3.listFileNames(tempFolder));
		if (!UtilitiesS3.listFileNames(imageSrc).isEmpty()) {
			UtilitiesS3.copyObjects(imageSrc, imagePath);
		}
		if (!UtilitiesS3.listFileNames(attachSrc).isEmpty()) {
			UtilitiesS3.copyObjects(attachSrc, attachPath);
		}
		if (!UtilitiesS3.listFileNames(tempFolder).isEmpty()) {
			UtilitiesS3.deleteFolder(tempFolder);
		}

	}

	public Project getProjectById(UUID projectId) {
		String query = "select * from projects where id=?;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query,projectId);
		Project project = new Project();
		for (Map<String, Object> row : rows) {
			project.setId(UUID.fromString(String.valueOf(row.get("id"))));
			project.setTitle(String.valueOf(row.get("title")));
			project.setSummary(String.valueOf(row.get("summary")));
			project.setDescription(String.valueOf(row.get("description")));
			//			project.setImage(UUID.fromString(String.valueOf(row.get("image"))));
			project.setSuccessCriteria(String.valueOf(row.get("success_criteria")));
			project.setInputValue(String.valueOf(row.get("input_value")));
			project.setInvitationStatus(String.valueOf(row.get("invitation_status")));
			project.setNumberOfParticipants(getNumberOfPartipants(projectId));
			project.setInputValue(String.valueOf(row.get("input_value")));
			//			System.out.println("invalid uuid "+(String.valueOf(row.get("image_id"))));
			//			System.out.println("invalid uuid "+(row.get("image_id") == null));
			if (null != row.get("image_id")) {
				project.setImageId(UUID.fromString(String.valueOf(row.get("image_id"))));
			}
		}
		//		System.out.println("art project"+project);
		return project;
	}
	/**
	 * get the number of partipants who accepted the offer
	 */
	public Integer getNumberOfPartipants(UUID projectId){
		String query = "select count(*) from invitations where project_id=? and offer_status=?;";
		Integer count = jdbcTemplate.queryForObject(query, new Object[]{projectId,DeclareConstant.ACCEPTOFFER} ,Integer.class);
		return count;
	}
	public Project getProjectByTitle(String title) {
		String query = "select * from projects where title=?;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query,title);
		Project project = new Project();
		for (Map<String, Object> row : rows) {
			project.setId(UUID.fromString(String.valueOf(row.get("id"))));
			project.setTitle(String.valueOf(row.get("title")));
			project.setSummary(String.valueOf(row.get("summary")));
			project.setDescription(String.valueOf(row.get("description")));
			//			project.setImage(UUID.fromString(String.valueOf(row.get("image"))));
			project.setSuccessCriteria(String.valueOf(row.get("success_criteria")));
			project.setInputValue(String.valueOf(row.get("input_value")));
			/*		System.out.println("invalid uuid "+(String.valueOf(row.get("image_id"))));
			System.out.println("invalid uuid "+(row.get("image_id") == null));*/
			if (null != row.get("image_id")) {
				project.setImageId(UUID.fromString(String.valueOf(row.get("image_id"))));
			}
		}
		//		System.out.println("art project"+project);
		return project;
	}

	public void changeInvitationStatus(String status,UUID projectId) {
		String update = "update projects set invitation_status=? where id=?";
		jdbcTemplate.update(update,status,projectId);

	}



	public void editPreviewLink(List<Project> projects, String inPreviewLink) {
		for (Project project : projects) {
			String previewLink = inPreviewLink+project.getId();
			project.setPreviewLink(previewLink);
		}

	}

	public void deleteProjectById(UUID projectId){
		String query = "delete from projects where id=?;";
		jdbcTemplate.update(query,projectId);
	}

	/**
	 * update payment status in accounts projects table when deleting the project 
	 */
	public void deleteAccountsProjectsById(UUID projectId){
		//		String delete = "delete from accounts_projects where project_id=?;";
		String update = "update accounts_projects set project_id=?, title=?, role=?, payment_status=? where project_id=?";
		jdbcTemplate.update(update,null,"","",DeclareConstant.PENDING,projectId);
	}

	public void addPayment(UUID userId,String email, String paymentStatus, String amount, String token){
		String insert = "insert into accounts_projects (user_id, email, payment_status, payment_amount, payment_token) values(?,?,?,?,?);";
		jdbcTemplate.update(insert,userId, email, paymentStatus,amount,token);
	}

	/**
	 * check if user has paid
	 */

	public boolean checkIfUserPaid(){
		String query = "select payment_status from accounts_projects where user_id=?;";
		boolean flag = false;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query,Utilities.getCurrentUserDetails().getId());
		for (Map<String, Object> row : rows) {
			if ((String.valueOf(row.get("payment_status")).equals(DeclareConstant.PENDING))) {
				flag = true;
			}
		}
		return flag;
	}

}
