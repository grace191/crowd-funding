package com.incubator.springmvc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.incubator.springmvc.model.Contact;
import com.incubator.springmvc.model.Project;

@Repository("contactDAO")
public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate = null;
	private AccountDao accountDAO;


	@Autowired
	public void setAccountDAO(AccountDao accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean addContact(Contact contact) {
		String INSERT_SQL = "INSERT INTO contacts (user_id,first_name,last_name, participant_email) VALUES (?, ?, ?, ?);";
		jdbcTemplate.update(INSERT_SQL, contact.getUserId(), contact.getFirst_name(), contact.getLast_name(),
				contact.getParticipant_email());


		return true;
	}

	public boolean checkContactExistByEmail(String email, UUID userId) {
		boolean flag = false;
		String query = "select count(*) from contacts where participant_email=? and user_id=?;";
		Integer count =jdbcTemplate.queryForObject(query, new Object[]{email, userId},Integer.class);
		if (count > 0) {
			flag = true;
		}	
		return flag;
	}

	public Contact getContactById(UUID contactId) {
		String query = "select * from contacts where contact_id=?;";
		List<Map<String, Object>> row =jdbcTemplate.queryForList(query,contactId);
		Contact contact = new Contact();
		System.out.println("contact id "+contactId);
		contact.setContactId(UUID.fromString(String.valueOf(row.get(0).get("contact_id"))));
		contact.setUserId(UUID.fromString(String.valueOf(row.get(0).get("user_id"))));
		contact.setFirst_name(String.valueOf(row.get(0).get("first_name")));
		contact.setLast_name(String.valueOf(row.get(0).get("last_name")));
		contact.setParticipant_email(String.valueOf(row.get(0).get("participant_email")));		
		return contact;
	}

	public List<Contact> listContacts(Object obj){
		String query="";
		UUID user_id = null;
		if (obj instanceof UUID) {
			user_id = (UUID)obj;
		}else if (obj instanceof String) {
			user_id = this.accountDAO.getUserByEmail((String)obj).getId();

		}
		List<Map<String, Object>> conRows = new ArrayList<Map<String,Object>>();

		query = "select contact_id, user_id, first_name,last_name,participant_email from contacts where user_id=?;";
		conRows = jdbcTemplate.queryForList(query,user_id);

		/*}else if (obj instanceof String) {
			query = "select contact_id, user_id, first_name,last_name,participant_email from contacts where user_email=?;";
			conRows = jdbcTemplate.queryForList(query,(String)obj);
		}*/
		//		String user_email = contact.getUser_email();
		//		String query = "select id, first_name,last_name,participant_email from contacts where user_email=(select email from accounts where id=?);";

		List<Contact> contacts = new ArrayList<Contact>();
		//		List<Map<String, Object>> conRows = jdbcTemplate.queryForList(query,obj);
		//System.out.println("size "+conRows.size());
		if (conRows.size()>0) {
			for (Map<String, Object> conRow : conRows) {
				Contact con = new Contact();
				con.setContactId(UUID.fromString(String.valueOf(conRow.get("contact_id"))));
				con.setUserId(UUID.fromString(String.valueOf(conRow.get("user_id"))));
				con.setFirst_name(String.valueOf(conRow.get("first_name")));
				con.setLast_name(String.valueOf(conRow.get("last_name")));
				con.setParticipant_email(String.valueOf(conRow.get("participant_email")));
				contacts.add(con);


			}
		}else{
			return contacts;
		}

		//		System.out.println("contacts in dao"+conRows.size());
		return contacts;

	}

	//	public List<Contact> listContactsByEmail(String email){
	////		String user_email = contact.getUser_email();
	//		String query = "select id, first_name,last_name,participant_email from contacts where user_email=?;";
	//
	//		List<Contact> contacts = new ArrayList<Contact>();
	//		List<Map<String, Object>> conRows = jdbcTemplate.queryForList(query,email);
	////		System.out.println("size "+conRows.size());
	//		for (Map<String, Object> conRow : conRows) {
	//			Contact con = new Contact();
	//			con.setId(UUID.fromString(String.valueOf(conRow.get("id"))));
	//			con.setUser_email(String.valueOf(conRow.get("user_email")));
	//			con.setFirst_name(String.valueOf(conRow.get("first_name")));
	//			con.setLast_name(String.valueOf(conRow.get("last_name")));
	//			con.setParticipant_email(String.valueOf(conRow.get("participant_email")));
	//			contacts.add(con);
	//			
	//			
	//		}
	////		System.out.println("contacts in dao"+conRows.size());
	//		return contacts;
	//		
	//	}

	public void removeContact(UUID id) {
		String query ="delete from contacts where contact_id=?";
		jdbcTemplate.update(query, id);

	}

	public void updateContact(Contact contact) {
		String update="update contacts set first_name=?, last_name=?, participant_email=? where contact_id =?;";
		jdbcTemplate.update(update, contact.getFirst_name(),contact.getLast_name(),
				contact.getParticipant_email(), contact.getContactId());
	}

	/*public List<Contact> findContactByEmail(String userEmail) {
		// TODO Auto-generated method stub

	}*/

}
