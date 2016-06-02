package com.incubator.springmvc.model;

import java.util.UUID;

public class UploadedFile {
	 
    private UUID id;
    private UUID projectId;
    private String roleInProject;
    private int length;
    private byte[] bytes;
    private String name;
    private String type;
    private String path;
    
    
	public UploadedFile() {
		super();
	}
	public UploadedFile(int length, String name, String type) {
		super();
		this.length = length;
		this.name = name;
		this.type = type;
	}
	
	public UUID getProjectId() {
		return projectId;
	}
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}
	public String getRoleInProject() {
		return roleInProject;
	}
	public void setRoleInProject(String roleInProject) {
		this.roleInProject = roleInProject;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
    
    
}
