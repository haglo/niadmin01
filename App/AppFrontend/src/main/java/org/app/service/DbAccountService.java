package org.app.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.app.model.dao.DbAccountDAO;
import org.app.model.entity.DbAccount;

/*
 * Managed Bean
 * In MVC the Controler-Part
 */
@RequestScoped
public class DbAccountService implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private DbAccountDAO dbAccountDAO;

	private DbAccount account;
	private boolean isEditing = false;

	public boolean getEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
	}

	public void toggleEditing() {
		this.isEditing = !this.isEditing;
	}

	public void remove(int id) {
		dbAccountDAO.remove(id);
	}

	public void create(DbAccount account) {
		account.setPassword(account.getPassword());
		dbAccountDAO.create(account);
	}

	public void update(DbAccount account) {
		dbAccountDAO.update(account);
	}

	public List<DbAccount> findAll() {
		List<DbAccount> list = dbAccountDAO.findAll();
		return list;
	}

	public DbAccount findByID(int id) {
		return account = dbAccountDAO.findByID(id);
	}

	public DbAccount findByUserName(String username) {
		return account = dbAccountDAO.findByUserName(username);
	}

	public DbAccountDAO getAccountDAO() {
		return dbAccountDAO;
	}

}