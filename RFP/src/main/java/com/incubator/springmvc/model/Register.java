package com.incubator.springmvc.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Register {

	// @NotEmpty(message="Username cannot be empty")
	// @Size(min=3,max=60,message="Min 3 and Max 60 characters for username")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	private String username;

	// @Email(message="Invalid email")
	private String email;

	// @NotEmpty(message="Password cannot be empty")
	// @Size(min=3,max=60,message="Min 6 and Max 15 characters for password")
	private String password;

	private String permission;

	private String project_title;

	public Register() {
	}

	public Register(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Register(String username, String email, String password, String permission) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.permission = permission;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getProject_title() {
		return project_title;
	}

	public void setProject_title(String project_title) {
		this.project_title = project_title;
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", permission=" + permission + ", project_title=" + project_title + "]";
	}

	
}
