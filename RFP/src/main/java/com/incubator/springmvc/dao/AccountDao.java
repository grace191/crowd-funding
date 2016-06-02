package com.incubator.springmvc.dao;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import com.incubator.springmvc.model.Account;
import com.incubator.springmvc.model.Register;


public interface AccountDao {
	public boolean addAccount(Register register);
	public boolean validateAccount(Register register);
	public boolean validateEmail(Register register);
	public void updateAccount(UUID userId, Boolean disable, Register accountBean);
	public HashMap<Register,Map<String, Integer>> listAccounts();
	public Account getUserByEmail(String email);
	public Register getAccountById(UUID id);
	public void deleteAccountByName(String name);
	public UUID getCreatorIdFromProjectId(UUID projectId);
	public boolean register(Register register, UUID projectId,UUID contactId);
	public void updateAccountsProjects(UUID projectId, String email, UUID contactId);
	public List<String> checkUserAuthority(String email);
	public Integer checkUserProjects(String email, String role);
	public Register getUserRoleAndEmail(UUID userId);
	public UUID getIdByAuthorityAndEmail(String email, String authority);
}
