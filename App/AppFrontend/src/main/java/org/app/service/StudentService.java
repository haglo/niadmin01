package org.app.service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import org.app.model.dao.StudentDAO;

import com.vaadin.cdi.annotation.VaadinSessionScoped;

/*
 * Managed Bean
 * In MVC the Controler-Part
 */
@VaadinSessionScoped
public class StudentService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private StudentDAO DAO;
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

	public StudentDAO getDAO() {
		return DAO;
	}
}
