package com.incubator.springmvc.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incubator.springmvc.dao.UploadedFileDAO;
import com.incubator.springmvc.model.UploadedFile;

@Service
public class UploadedFileServiceImpl implements UploadedFileService{
	
	private UploadedFileDAO uploadedFileDAO;
	
	@Autowired
	public void setUploadedFileDAO(UploadedFileDAO uploadedFileDAO) {
		this.uploadedFileDAO = uploadedFileDAO;
	}

	public void addFile(UploadedFile file) {
		this.uploadedFileDAO.addFile(file);
	}

	public List<UploadedFile> listFiles(String path) {
		return this.uploadedFileDAO.listFiles(path);
	}

	
	public UploadedFile findById(UUID id) {
		return this.uploadedFileDAO.findById(id);
	}

	public void deleteById(UUID id) {
		this.uploadedFileDAO.deleteById(id);
		
	}
//	public void updateImageId(UUID projectId,UUID imageId){
//		this.uploadedFileDAO.updateImageId(projectId, imageId);
//	}
	public void deleteByNameAndPath(String name, String path){
		this.uploadedFileDAO.deleteByNameAndPath(name, path);
	}
	public List<UploadedFile> listImage(String path){
		return this.uploadedFileDAO.listImage(path);
	}

	public void updateAttachmentPath(UUID projectId, UUID imageId, List<UUID> attachmentIds,String imagePath, String attachPath) {
		this.uploadedFileDAO.updateAttachmentPath(projectId, imageId, attachmentIds, imagePath, attachPath);
		
	}

	@Override
	public UUID getImageId(UUID projectId) {
		return this.uploadedFileDAO.getImageId(projectId);
	}

	@Override
	public List<UUID> getAttachmentIds(UUID projectId) {
		return this.uploadedFileDAO.getAttachmentIds(projectId);
	}

	@Override
	public void editUploadedImage(UploadedFile file) {
		this.uploadedFileDAO.editUploadedImage(file);
		
	}

	@Override
	public void addFileWithId(UploadedFile file, UUID projectId) {
		this.uploadedFileDAO.addFileWithId(file, projectId);
		
	}

	@Override
	public List<UploadedFile> listEditedFiles(UUID projectId) {
		return this.uploadedFileDAO.listEditedFiles(projectId);
	}
}
