package com.incubator.springmvc.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.incubator.springmvc.dao.AccountDao;
import com.incubator.springmvc.dao.ProjectDAO;
import com.incubator.springmvc.model.Account;
import com.incubator.springmvc.model.CustomUserDetails;
import com.incubator.springmvc.model.Project;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	private AccountDao accountDao;
	private ProjectDAO projectDAO;
	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	@Autowired
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	@Autowired
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Transactional
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
//		System.out.println("email "+email);
		Account account = this.accountDao.getUserByEmail(email);
//		System.out.println("any accounts " + account);
		if (account == null) {
            throw new UsernameNotFoundException("account name not found");
        }
        return buildUserFromAccount(account);
    
	}
	
	 @SuppressWarnings("unchecked")
	    @Transactional(readOnly = true)
	    private User buildUserFromAccount(Account account) {

	        String email = account.getEmail();
	        String password = account.getPassword();
	        UUID id = account.getId();
//	        boolean enabled = account.isEnabled();

	        // additional information goes here
	        List<Project> projects = this.projectDAO.listProjectsForCreators(id);
	        List<String> roles = this.accountDao.checkUserAuthority(account.getEmail());

			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (String role : roles) {
				authorities.add(new SimpleGrantedAuthority(role));
			}

			/*CustomUserDetails user = new CustomUserDetails(email, password, enabled, 
					true, true, true, authorities, projects, id);*/
			CustomUserDetails userDetails= new CustomUserDetails(email, password, true, true,
			true, true, authorities, projects, id);
			System.out.println("userdetails" + userDetails);

	        return userDetails;
	    }

}
