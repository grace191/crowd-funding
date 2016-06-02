package com.incubator.springmvc.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.incubator.springmvc.model.UploadedFile;
import com.incubator.springmvc.utils.DeclareConstant;
import com.incubator.springmvc.utils.Utilities;
import com.incubator.springmvc.utils.UtilitiesS3;

@Repository
public class UploadedFileDAOImpl implements UploadedFileDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addFile(UploadedFile file) {
		if (!getFileByName(file.getName(), file.getPath())) {
			String insert = "insert into uploaded_files(file_length,file_name,file_type,file_path,role_in_project) values(?,?,?,?,?);";
			jdbcTemplate.update(insert, file.getLength(), file.getName(), file.getType(), file.getPath(), file.getRoleInProject());
		}
	}
	
	public void editUploadedImage(UploadedFile file) {
//		if (!getFileByName(file.getName(), file.getPath())) {
//			String insert = "insert into uploaded_files(file_length,file_name,file_type,file_path,role_in_project) values(?,?,?,?,?);";
//			jdbcTemplate.update(insert, file.getLength(), file.getName(), file.getType(), file.getPath(), file.getRoleInProject());
//		}
		String updateImagePath = "update uploaded_files set file_name=? where id=?;";
		System.out.println("changed filename" + file.getName());
		jdbcTemplate.update(updateImagePath,file.getName(),file.getId());
	}

	public List<UploadedFile> listFiles(String path) {
		// File[] attachments = Utilities.listFiles(path);
		List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
		List<String> diskFiles = UtilitiesS3.listFileNames(path);

		for (String fileName : diskFiles) {
			UploadedFile file = new UploadedFile();
			file.setName(fileName);
//			System.out.println("name " + diskFile.getName() + " path" + path);
			file.setId(getFileId(fileName, path));

			uploadedFiles.add(file);
		}
		// String select = "";
		return uploadedFiles;
	}
	
	public List<UploadedFile> listEditedFiles(UUID projectId) {
		
		String query = "select id, file_name from uploaded_files where project_id=? and role_in_project=?";
		
		// File[] attachments = Utilities.listFiles(path);
		List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, projectId,DeclareConstant.ATTACHMENTS);
		for (Map<String, Object> row : rows) {
			UploadedFile file = new UploadedFile();
			if (null != row.get("id")) {
				file.setId(UUID.fromString(String.valueOf(row.get("id"))));
				file.setName(String.valueOf(row.get("file_name")));
				uploadedFiles.add(file);
			}
			
			
		}
//		List<String> diskFiles = UtilitiesS3.listFileNames(path);

//		for (String fileName : diskFiles) {
//			UploadedFile file = new UploadedFile();
//			file.setName(fileName);
////			System.out.println("name " + diskFile.getName() + " path" + path);
//			file.setId(getFileId(fileName, path));
//
//			uploadedFiles.add(file);
//		}
		// String select = "";
		return uploadedFiles;
	}

	public List<UploadedFile> listImage(String parentFolder) {
	//	File[] files = Utilities.listFiles(path);
		List<String> fileNames = UtilitiesS3.listFileNames(parentFolder);
		System.out.println("file names "+ fileNames);
				
		List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
		UploadedFile uploadedFile = new UploadedFile();
		if (fileNames.size() > 0) {
			uploadedFile.setName(fileNames.get(0));
			uploadedFile.setId(getFileId(fileNames.get(0), parentFolder));
			uploadedFiles.add(uploadedFile);
		}

		return uploadedFiles;
	}

	public boolean getFileByName(String name, String path) {
		String query = "select count(*) from uploaded_files where file_name=? and file_path=?;";
		int count = jdbcTemplate.queryForObject(query, new Object[] { name, path }, Integer.class);
		return (count > 0) ? true : false;
	}

	public UUID getFileId(String name, String path) {
		String queryId = "select id from uploaded_files where file_name=? and file_path=?;";
		System.out.println("name " + name + "path " + path);
		
		System.out.println("what is the result? "+jdbcTemplate.queryForObject(queryId, new Object[] { name, path }, UUID.class));
		UUID uuid = jdbcTemplate.queryForObject(queryId, new Object[] { name, path }, UUID.class);
		return uuid;
	}

	public UploadedFile findById(UUID id) {
		String selectFile = "select * from uploaded_files where id=?;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(selectFile, id);
		UploadedFile file = new UploadedFile();
		for (Map<String, Object> row : rows) {
			file.setLength(Integer.valueOf(String.valueOf(row.get("file_length"))));
			file.setName(String.valueOf(row.get("file_name")));
			file.setPath(String.valueOf(row.get("file_path")));
			file.setType(String.valueOf(row.get("file_type")));
		}
		return file;
	}

	public void deleteById(UUID id) {
		String delete = "delete from uploaded_files where id=?;";
		jdbcTemplate.update(delete, id);
		
		// System.out.println("file is deleted");
	}
	
	public void addFileWithId(UploadedFile file,UUID projectId){
		if (null == file.getId()) {
			String insert = "insert into uploaded_files(project_id, file_length,file_name,file_type,file_path,role_in_project) values(?,?,?,?,?,?);";
			jdbcTemplate.update(insert, projectId, file.getLength(), file.getName(), file.getType(), file.getPath(), file.getRoleInProject());
		}else{
			String insert = "insert into uploaded_files(id, project_id, file_length,file_name,file_type,file_path,role_in_project) values(?,?,?,?,?,?,?);";
			jdbcTemplate.update(insert, file.getId(), projectId, file.getLength(), file.getName(), file.getType(), file.getPath(), file.getRoleInProject());
		}
		
	}
	
//	public void addAttachmentWithId(UploadedFile file,UUID projectId){
//		String insert = "insert into uploaded_files(project_id, file_length,file_name,file_type,file_path,role_in_project) values(?,?,?,?,?,?);";
//		jdbcTemplate.update(insert, projectId, file.getLength(), file.getName(), file.getType(), file.getPath(), file.getRoleInProject());
//	}
//	public void updateImageId(UUID projectId,UUID imageId){
//		String update ="update projects set image_id=? where id=?;";
//		jdbcTemplate.update(update, null, projectId);
//	}

//	public void updateImageIdWhenUpload(UUID projectId,UUID imageId){
//		String update ="update projects set image_id=? where id=?;";
//		jdbcTemplate.update(update, null, projectId);
//	}

	public void deleteByNameAndPath(String name, String path) {
		String delete = "delete from uploaded_files where file_name=? and file_path=?;";
		jdbcTemplate.update(delete, name, path);
		// System.out.println("file is deleted");
	}

	public void updateAttachmentPath(UUID projectId, UUID imageId, List<UUID> attachmentIds, String imagePath, String attachPath) {
		String update ="update uploaded_files set project_id=?, role_in_project=?, file_path=? where id=?;";
		if (null != imageId) {
			jdbcTemplate.update(update,projectId,DeclareConstant.IMAGE,imagePath,imageId);
		}
		if (attachmentIds.size()>0) {
			for (UUID uuid : attachmentIds) {
				jdbcTemplate.update(update,projectId,DeclareConstant.ATTACHMENTS,attachPath,uuid);
			}
		}
		
	}
	
	/**
	 * get image by project id and image role
	 */
	public UUID getImageId(UUID projectId){
		String query = "select id from uploaded_files where project_id=? and role_in_project=?";
		UUID image_id = jdbcTemplate.queryForObject(query, new Object[]{projectId,DeclareConstant.IMAGE}, UUID.class);
		return image_id;
	}
	
//	public List<UUID> getAttachmentIds(UUID projectId){
//		String query = "select attachment_id from projects_files where project_id=?";
//		List<UUID> attachmentIds = new ArrayList<>();
//		List<Map<String,Object>> rows = jdbcTemplate.queryForList(query, projectId);
//		for (Map<String, Object> map : rows) {
//			attachmentIds.add((UUID)map.get("attachment_id"));
//		}
//		return attachmentIds;
//	}
	public List<UUID> getAttachmentIds(UUID projectId){
		String query = "select id from uploaded_files where project_id=? and role_in_project=?";
		List<UUID> attachmentIds = new ArrayList<>();
		List<Map<String,Object>> rows = jdbcTemplate.queryForList(query, projectId,DeclareConstant.ATTACHMENTS);
		for (Map<String, Object> map : rows) {
			attachmentIds.add((UUID)map.get("id"));
		}
		return attachmentIds;
	}

}
