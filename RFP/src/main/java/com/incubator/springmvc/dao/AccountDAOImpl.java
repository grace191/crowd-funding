package com.incubator.springmvc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.incubator.springmvc.model.Account;
import com.incubator.springmvc.model.Register;
import com.incubator.springmvc.utils.DeclareConstant;

@Repository("accountDao")
public class AccountDAOImpl implements AccountDao {

	private JdbcTemplate jdbcTemplate = null;
	private ProjectDAO projectDAO;

	@Autowired
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Autowired
	public AccountDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = (new JdbcTemplate(dataSource));
	}

	/**
	 * administrator adds an account
	 */
	public boolean addAccount(Register register) {

		String INSERT_SQL = "INSERT INTO accounts (username,password,enabled, email) VALUES (?, ?, ?, ?);";
		jdbcTemplate.update(INSERT_SQL, register.getUsername(), register.getPassword(), true, register.getEmail());

		UUID userId = getUserByEmail(register.getEmail()).getId();
		String query = "insert into authorities (user_id, email,authority) values(?,?,?);";
		jdbcTemplate.update(query, userId, register.getEmail(), register.getPermission());


		return true;

	}

	public boolean register(Register register, UUID projectId, UUID contactId) {
		String INSERT_SQL = "INSERT INTO accounts (id,username,password,enabled, email) VALUES (?, ?, ?, ?,?);";
		jdbcTemplate.update(INSERT_SQL, contactId, register.getUsername(), register.getPassword(), true, register.getEmail());

		updateAccountsProjects(projectId, register.getEmail(), contactId);
		return true;

	}

	/**
	 * update accounts_projects table
	 * It is used when new user signs in or a project creator logs in as user of a project
	 * @param projectId
	 * @param email
	 * @param contactId
	 */
	public void updateAccountsProjects(UUID projectId, String email, UUID contactId){
		String title = this.projectDAO.getProjectById(projectId).getTitle();
		if (!checkUserAuthority(email).contains(DeclareConstant.ROLE_USER)) {
			String query = "insert into authorities (user_id,email,authority) values(?,?,?);";
			jdbcTemplate.update(query, contactId, email, DeclareConstant.ROLE_USER);
		}


		if (!checkRoleInserted(projectId, contactId, email, title, DeclareConstant.ROLE_USER)) {
			String insert = "insert into accounts_projects (project_id,user_id,email,title, role) values(?,?,?,?,?);";

			jdbcTemplate.update(insert,projectId, contactId,email,title, DeclareConstant.ROLE_USER);
		}

	}
	
	/**
	 * check if the role has been inserted
	 */

	public boolean checkRoleInserted(UUID projectId, UUID contactId, String email,String title, String role){
		boolean flag = false;
		String select = "select count(*) from accounts_projects where project_id=? and user_id=? and email=? and title=? and role=?;";
		Integer count = jdbcTemplate.queryForObject(select,new Object[]{projectId,contactId,email,title,role},Integer.class);
		if (count>0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * check authorities of a user in authorities table
	 * @param email
	 * @return
	 */
	public List<String> checkUserAuthority(String email){
		String queryAuthority = "select authority from authorities where email=?;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(queryAuthority, email);
		List<String> authorities = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			authorities.add(String.valueOf(row.get("authority")));

		}
		return authorities;
	}
	/**
	 * get id by authority and email
	 * @param email
	 * @param authority
	 * @return
	 */
	public UUID getIdByAuthorityAndEmail(String email, String authority){
		String select = "select user_id from authorities where email=? and authority=?";
		UUID user_id = jdbcTemplate.queryForObject(select, new Object[]{email, authority},UUID.class);
		return user_id;
	}

	//list accounts, and their contacts and projects for admin
	public HashMap<Register, Map<String, Integer>> listAccounts() {
		String query = "SELECT accounts.id, accounts.username, authorities.email, authorities.authority"
				+ " FROM accounts INNER JOIN authorities ON accounts.email = authorities.email;";

		HashMap<Register, Map<String, Integer>> map = new HashMap<Register, Map<String, Integer>>();
		List<Register> accounts = new ArrayList<Register>();
		List<Map<String, Object>> conRows = jdbcTemplate.queryForList(query);
		for (Map<String, Object> conRow : conRows) {
			Register account = new Register();
			String email = String.valueOf(conRow.get("email"));
			account.setEmail(email);
			String permission = String.valueOf(conRow.get("authority"));
			account.setPermission(permission);
		
			UUID userId = UUID.fromString(String.valueOf(conRow.get("id")));
			account.setId(getIdByAuthorityAndEmail(email, permission));
//			account.setId(userId);

			account.setUsername(String.valueOf(conRow.get("username")));


			String contactQuery = "select count(*) from contacts where user_id=?;";
			int numberOfContacts = jdbcTemplate.queryForObject(contactQuery, new Object[] { userId }, Integer.class);

			String projectQuery = "select count(*) from accounts_projects where email=? and project_id is not null and role=?;";
			System.out.println("user id: "+account.getId() +" role "+permission);
			int numberOfProjects = jdbcTemplate.queryForObject(projectQuery, new Object[] { email,permission }, Integer.class);
			Map<String, Integer> map2 = new HashMap<>();
			//			System.out.println("count " + count);
			map2.put("contacts", numberOfContacts);
			map2.put("projects", numberOfProjects);

			accounts.add(account);
			map.put(account, map2);
			System.out.println("account " + account);

		}
		System.out.println("accounts " + map);
		System.out.println("list " + accounts);
		return map;
	}

	public Account getUserByEmail(String email) {
		//		System.out.println("executed");
		String query = "SELECT id, email, username, PASSWORD, ENABLED FROM ACCOUNTS WHERE email=?;";
		List<Map<String, Object>> accountRows = jdbcTemplate.queryForList(query, email);
		//		System.out.println("accountrows "+accountRows.size());
		Account account = new Account();
		if (accountRows.size()>0) {
			for (Map<String, Object> accountRow : accountRows) {
				account.setId(UUID.fromString(String.valueOf(accountRow.get("id"))));
				account.setEmail(String.valueOf(accountRow.get("email")));
				account.setUsername(String.valueOf(accountRow.get("username")));
				account.setPassword(String.valueOf(accountRow.get("password")));
				//				account.setProjects(this.projectDAO.listProjects(String.valueOf(accountRow.get("email"))));
				//			account.setEnabled(Boolean.getBoolean(String.valueOf(accountRow.get("enabled"))));
			}
		}


		//		System.out.println("account"+account.getId()+"password "+account.getPassword()+"user "+account.getEmail());
		return account;
	}

//	/**
//	 * get user authority by id
//	 * 
//	 */
//	public String getUserRole(UUID userId) {
//		String query = "select authority from authorities where user_id = ?;";
//		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query,userId);
//		String role = "";
//		if (rows.size()>0) {
//			role = (String)rows.get(0).get(String.valueOf("authority"));
//		}
//
//		return role;
//	}

	/**
	 * get original email and authority by id
	 * when project creator also has the ROLE_USER
	 */
	public Register getUserRoleAndEmail(UUID userId) {
		String query = "select * from authorities where user_id = ?;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query,userId);
		Register register = new Register();
		if (rows.size()>0) {
			for (Map<String, Object> map : rows) {
				register.setPermission(String.valueOf(map.get(("authority"))));
				register.setEmail(String.valueOf(map.get(("email"))));
			}
		}

		return register;
	}
	public void deleteAccountByName(String name) {
		// TODO Auto-generated method stub

	}

	public boolean validateAccount(Register register) {
		String query = "select username from accounts where username=" + "'" + register.getUsername() + "'";
		List<Map<String, Object>> proRows = jdbcTemplate.queryForList(query);
		Boolean flag = false;
		if (proRows.size() == 0) {
			flag = true;
		}
		return flag;
	}

	public boolean validateEmail(Register register) {
		String query = "select email from accounts where email=" + "'" + register.getEmail() + "'";
		List<Map<String, Object>> proRows = jdbcTemplate.queryForList(query);
		Boolean flag = false;
		if (proRows.size() == 0) {
			flag = true;
		}
		return flag;
	}

	public Register getAccountById(UUID id){
		String query = "select * from accounts where id=?;";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query,id);
		Register register= new Register();
		if (rows.size()>0) {
			for (Map<String, Object> row : rows) {

				register.setId(id);
				register.setEmail(String.valueOf(row.get("email")));
				register.setUsername(String.valueOf(row.get("username")));
				register.setPassword(String.valueOf(row.get("password")));
	//			register.setPermission(String.valueOf(row.get("authority")));
			}
		}
		//	System.out.println("quriedregister "+register);
		return register;
	}

	//	public String getProjectRole(Principal principal,UUID projectId){
	//		String query = "select authority from authorities where email=? and tilte=?;";
	//		String authority = jdbcTemplate.queryForObject(query, new Object[]{principal.getName(),this.projectDAO.getProjectById(projectId).getTitle()}, String.class);
	//		return authority;
	//	}

	//need to delete this method
	public UUID getCreatorIdFromProjectId(UUID projectId){
		//		String title = this.projectDAO.getProjectById(projectId).getTitle();
		//		String select = "select email from accounts where title=?;";
		//		String email = jdbcTemplate.queryForObject(select, new Object[]{title},String.cl)
		//		String query = "select a.id from accounts a, authorities au where au.title=? and a.email=au.email and au.authority=?;";
		String query = "select user_id from accounts_projects where project_id=? and role=?";
		UUID creatorId = jdbcTemplate.queryForObject(query, new Object[]{projectId, DeclareConstant.ROLE_PCREATOR}, UUID.class);
		return creatorId;

	}
	
	public void updateAccountByEmail(Register accountBean, String originalEmail){
		String updateAccounts ="update accounts set username=?, password=?, email=? where email=?;";
		jdbcTemplate.update(updateAccounts,accountBean.getUsername(),accountBean.getPassword(),accountBean.getEmail(),originalEmail);
		
	}
	
	public void updateAuthoritiesByEmail(Register accountBean, String originalEmail){
		String updateAuthorities = "update authorities set email=? where email=?;";
		jdbcTemplate.update(updateAuthorities,accountBean.getEmail(),originalEmail);
	}

	public void updateEmailIfChange(Register accountBean, String originalEmail){
		if (!originalEmail.equals(accountBean.getEmail())) {
		String update = "update accounts_projects set email=? where email=?;";
		jdbcTemplate.update(update,accountBean.getEmail(),originalEmail);
		String updateContact = "update contacts set participant_email=? where participant_email=?;";
		jdbcTemplate.update(updateContact,accountBean.getEmail(),originalEmail);
		}
	}
	/**
	 * update the account by administrator
	 * If the account has a project, then his permission cannot be changed
	 * 
	 */
	@Override
	public void updateAccount(UUID userId, Boolean disable, Register accountBean) {
		String originalEmail = getUserRoleAndEmail(userId).getEmail();
		if (!disable) {
			updateAccountByEmail(accountBean, originalEmail);
			String updateAuthorities = "update authorities set authority = ?  where user_id=?;";
			jdbcTemplate.update(updateAuthorities,accountBean.getPermission(), userId);
			updateAuthoritiesByEmail(accountBean, originalEmail);
			updateEmailIfChange(accountBean, originalEmail);
			
		}else{		
			updateAccountByEmail(accountBean, originalEmail);
			updateAuthoritiesByEmail(accountBean, originalEmail);
			updateEmailIfChange(accountBean, originalEmail);
		}

	}
//	public void updateAccount(Account account) {
//		// TODO Auto-generated method stub
//
//	}
	/**
	 * check if the user with its permission has an project. If he has, 
	 * then his permission cannot be edited and his account cannot be deleted.
	 */

	public Integer checkUserProjects(String email, String role){
		String select = "select count(*) from accounts_projects where email=? and role=?;";
		Integer count = jdbcTemplate.queryForObject(select, new Object[]{email, role},Integer.class);
		return count;
	}
}
