//package com.incubator.springmvc.dao;
//
//import java.io.File;
//import java.io.IOException;
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.io.FileUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Repository;
//
//import com.incubator.springmvc.model.CustomUserDetails;
//import com.incubator.springmvc.model.Project;
//import com.incubator.springmvc.model.Register;
//import com.incubator.springmvc.model.UploadedFile;
//import com.incubator.springmvc.utils.DeclareConstant;
//import com.incubator.springmvc.utils.Utilities;
//
//@Repository("projectDAO")
//public class ProjectDAOImpl implements ProjectDAO {
//
//	private JdbcTemplate jdbcTemplate = null;
//	private UploadedFileDAO uploadedFileDAO;
//
//	@Autowired
//	public ProjectDAOImpl(DataSource dataSource) {
//		this.jdbcTemplate = (new JdbcTemplate(dataSource));
//	}
//	
//	@Autowired
//	public void setUploadedFileDAO(UploadedFileDAO uploadedFileDAO) {
//		this.uploadedFileDAO = uploadedFileDAO;
//	}
//
//	public UUID addProject(Project project, Principal principal) {
//		String INSERT_SQL = "INSERT INTO projects (title,summary,description,success_criteria, input_value, invitation_status) VALUES (?,?,?,?,?,?);";
//		jdbcTemplate.update(INSERT_SQL, project.getTitle(),project.getSummary(),
//				project.getDescription(),project.getSuccessCriteria(),project.getInputValue(),DeclareConstant.CREATED);
//		
//		
//		String queryProject = "select title from authorities where email=?;";
//		List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryProject, principal.getName());
//		boolean flag = false;
//		for (Map<String, Object> row : rows) {
//			if (String.valueOf(row.get("title")).equals(DeclareConstant.INITIAL_STATUS)) {
//				flag = true;
//			}
//		}
//		if (flag) {
//			String update = "update authorities set title=? where email=?;";
//			jdbcTemplate.update(update, project.getTitle(), principal.getName());
//		}else {
//			String insert = "insert into authorities (email,title,authority) values (?,?,?);";
//			jdbcTemplate.update(insert,principal.getName(),project.getTitle(),DeclareConstant.ROLE_PCREATOR);
//		}
//
//
//		
////		String insert = "insert into authorities (title, authority, email) values (?,?,?);";
////		jdbcTemplate.update(insert, project.getTitle(), DeclareConstant.ROLE_ADMIN, principal.getName());
////		String update = "UPDATE authorities SET title=? WHERE email = ?;";
////		jdbcTemplate.update(update, project.getTitle())
//		return getProjectId(project.getTitle());
//	}
//
//	public UUID getProjectId(String title){
//		String queryId = "select id from projects where title=?;";
//		UUID projectId = jdbcTemplate.queryForObject(queryId, new Object[]{title},UUID.class);
//		return projectId;
//		
//	}
//	public List<Project> listProjects(String email) {
//		String query ="";
//			query = "select p.id, p.invitation_status, a.title from projects p, authorities a where p.title=a.title AND a.email=?;";
//		List<Project> projects = new ArrayList<Project>();
//		List<Map<String, Object>> proRows = jdbcTemplate.queryForList(query,email);
//		for (Map<String, Object> proRow : proRows) {
//			Project pro = new Project();
//			if (null != proRow.get("title")) {
//				pro.setTitle(String.valueOf(proRow.get("title")));
//				pro.setId(UUID.fromString(String.valueOf(proRow.get("id"))));
//				pro.setInvitationStatus(String.valueOf(proRow.get("invitation_status")));
//				
//				projects.add(pro);
//			}
//
//		}
////		System.out.println("all projects" + projects);
//		return projects;
//	}
//
//	public boolean validateTitle(Project project) {
//		String query = "select title from projects where title=" + "'" + project.getTitle() + "'";
//		List<Map<String, Object>> proRows = jdbcTemplate.queryForList(query);
//	//	System.out.println("title prorows" + proRows.size());
//		Boolean flag = false;
//		if (proRows.size() == 0) {
//			flag = true;
//		}
//		return flag;
//	}
//	
//	public void setAttachmentPath(UUID projectId){
//		List<UploadedFile> imageList = this.uploadedFileDAO.listImage(Utilities.accountTempImageDirectory());
//		List<UploadedFile> attachList = this.uploadedFileDAO.listFiles(Utilities.accountTempAttachDirectory());
//		UUID imageId = null;
//		List<UUID> attachmentIds = new ArrayList<UUID>();
//		if (imageList.size()>0) {
//			imageId = imageList.get(0).getId();
//		}
//		for (UploadedFile file : attachList) {
//			attachmentIds.add(file.getId());
//		}
//		String imagePath = Utilities.changeAccountImageDirectory(projectId);
//		String attachPath = Utilities.changeAccountAttachDirectory(projectId);
//		
//		this.uploadedFileDAO.updateAttachmentPath(projectId,imageId,attachmentIds,imagePath,attachPath);
//		
//		File imageSrc = new File(Utilities.accountTempImageDirectory());
//		File imageDes = new File(imagePath);
//		File attachSrc = new File(Utilities.accountTempAttachDirectory());
//		File attachDes = new File(attachPath);
//		File tempFolder = new File(Utilities.accountTempDirectory());
//		try {
//			if (imageSrc.exists()) {
//				FileUtils.moveDirectory(imageSrc, imageDes);
//			}
//			if (attachSrc.exists()) {
//				FileUtils.moveDirectory(attachSrc, attachDes);
//			}
//			if (tempFolder.exists()) {
//				FileUtils.forceDelete(new File(Utilities.accountTempDirectory()));
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//
//	public Project getProjectById(UUID projectId) {
//		String query = "select * from projects where id=?;";
//		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query,projectId);
//		Project project = new Project();
//		for (Map<String, Object> row : rows) {
//			project.setTitle(String.valueOf(row.get("title")));
//			project.setSummary(String.valueOf(row.get("summary")));
//			project.setDescription(String.valueOf(row.get("description")));
////			project.setImage(UUID.fromString(String.valueOf(row.get("image"))));
//			project.setSuccessCriteria(String.valueOf(row.get("success_criteria")));
//			project.setInputValue(String.valueOf(row.get("input_value")));;
//		}
////		System.out.println("art project"+project);
//		return project;
//	}
//
//	public void changeInvitationStatus(UUID projectId) {
//		String update = "update projects set invitation_status=? where id=?";
//		jdbcTemplate.update(update,DeclareConstant.SENT,projectId);
//		
//	}
//
//
//
//	public void editPreviewLink(List<Project> projects, String inPreviewLink) {
//		for (Project project : projects) {
//			String previewLink = inPreviewLink+project.getId();
//			project.setPreviewLink(previewLink);
//		}
//		
//	}
//
//}
