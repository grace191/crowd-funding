package com.incubator.springmvc.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.incubator.springmvc.dao.AccountDao;
import com.incubator.springmvc.model.Account;
import com.incubator.springmvc.model.Register;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {
	
	private AccountDao accountDao;
	
	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	
	public void deleteAccountByName(String name) {
		// TODO Auto-generated method stub
		
	}

	public HashMap<Register,Map<String, Integer>> listAccounts() {
		return this.accountDao.listAccounts();
	}

	public void deleteAllAccounts() {
		// TODO Auto-generated method stub
		
	}

	public boolean isUserExist(Account user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAccount(Register register) {
		this.accountDao.addAccount(register);
		return true;
	}

	public boolean validateAccount(Register register) {
		return this.accountDao.validateAccount(register);
	}

	public boolean validateEmail(Register register) {
		return this.accountDao.validateEmail(register);
	}

	public Register getAccountById(UUID id) {
		return this.accountDao.getAccountById(id);
	}

	public Account getUserByEmail(String email){
		return this.accountDao.getUserByEmail(email);
	}



	/*public String getProjectRole(Principal principal, UUID projectId) {
		return this.accountDao.getProjectRole(principal, projectId);
	}*/

	public UUID getCreatorIdFromProjectId(UUID projectId){
		return this.accountDao.getCreatorIdFromProjectId(projectId);
	}



	public boolean register(Register register, UUID projectId, UUID contactId) {
		return this.accountDao.register(register, projectId, contactId);
	}



	@Override
	public void updateAccountsProjects(UUID projectId, String email, UUID contactId) {
		this.accountDao.updateAccountsProjects(projectId, email, contactId);
		
	}



	

	@Override
	public Integer checkUserProjects(String email, String role) {
		return this.accountDao.checkUserProjects(email, role);
	}


	@Override
	public void updateAccount(UUID userId, Boolean disable, Register accountBean) {
		this.accountDao.updateAccount(userId, disable, accountBean);
		
	}


	@Override
	public Register getUserRoleAndEmail(UUID userId){
		return this.accountDao.getUserRoleAndEmail(userId);
	}


	@Override
	public UUID getIdByAuthorityAndEmail(String email, String authority) {
		return this.accountDao.getIdByAuthorityAndEmail(email, authority);
	}
}
