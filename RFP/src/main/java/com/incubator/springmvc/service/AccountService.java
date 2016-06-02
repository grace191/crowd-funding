package com.incubator.springmvc.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import com.incubator.springmvc.model.Account;
import com.incubator.springmvc.model.Register;



public interface AccountService {
		
	public boolean addAccount(Register register);
	public boolean validateAccount(Register register);
	public boolean validateEmail(Register register);
	public Register getAccountById(UUID id);
		
	public void updateAccount(UUID userId, Boolean disable, Register accountBean);
	
	void deleteAccountByName(String name);

	public HashMap<Register, Map<String, Integer>> listAccounts(); 
	
	void deleteAllAccounts();
	
	public boolean isUserExist(Account user);
	public Account getUserByEmail(String email);
/*	public String getProjectRole(Principal principal,UUID projectId);
*/	public UUID getCreatorIdFromProjectId(UUID projectId);
	public boolean register(Register register, UUID projectId, UUID contactId);
	public void updateAccountsProjects(UUID projectId, String email, UUID contactId);
	public Integer checkUserProjects(String email, String role);
	public Register getUserRoleAndEmail(UUID userId);
	public UUID getIdByAuthorityAndEmail(String email, String authority);
}
