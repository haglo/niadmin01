package org.app.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(
		{ 	@NamedQuery(name = DbAccount.QUERY_FIND_ALL, query = "SELECT c FROM DbAccount c"),
			@NamedQuery(name = DbAccount.QUERY_FIND_BY_USERNAME, query = "SELECT c FROM DbAccount c WHERE c.username =  :username") 
		})
public class DbAccount extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "DbAccount.FindAll";
	public static final String QUERY_FIND_BY_USERNAME = "DbAccount.FindByUserName";

	@Column(unique = true)
	private String username;

	private String password;
	
	private String mailaddress;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailaddress() {
		return mailaddress;
	}

	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}

}
