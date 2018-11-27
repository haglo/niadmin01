package org.app.controler;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import org.app.model.dao.ElytronRoleDAO;

/**
* Managed Bean
* In MVC the Controler-Part
*/
@RequestScoped
public class ElytronRoleService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ElytronRoleDAO elytronRoleDAO;

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

	public ElytronRoleDAO getDAO() {
		return elytronRoleDAO;
	}
}
