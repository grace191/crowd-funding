package com.incubator.springmvc.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.incubator.springmvc.model.UploadedFile;


public interface UploadedFileService {
	public void addFile(UploadedFile file);
	public List<UploadedFile> listFiles(String path);
	public UploadedFile findById(UUID id);
	public void deleteById(UUID id);
	public void deleteByNameAndPath(String name, String path);
	public List<UploadedFile> listImage(String path);
	public UUID getImageId(UUID projectId);
	public List<UUID> getAttachmentIds(UUID projectId);
	public void editUploadedImage(UploadedFile file);
	public void addFileWithId(UploadedFile file,UUID projectId);
	public List<UploadedFile> listEditedFiles(UUID projectId);
//	public void updateImageId(UUID projectId,UUID imageId);
	public void updateAttachmentPath(UUID projectId, UUID imageId, List<UUID> attachmentIds,String imagePath, String attachPath);
}
