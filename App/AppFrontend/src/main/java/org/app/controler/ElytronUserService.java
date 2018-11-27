package org.app.controler;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.app.model.audit.LoggedInUser;
import org.app.model.dao.ElytronUserDAO;
import org.app.model.entity.ElytronUser;

@RequestScoped
public class ElytronUserService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ElytronUserDAO elytronUserDAO;
	
	@Inject 
	LoggedInUser loggedInUser;

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
	
	public ElytronUserDAO getDAO() {
		return elytronUserDAO;
	}
	
	public ElytronUser getLoggedInUser() {
		return loggedInUser.getElytronUser();
}
}
