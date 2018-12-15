package org.app.controler;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import org.app.model.dao.StudentDAO;

/*
 * Managed Bean
 * In MVC the Controler-Part
 */
@RequestScoped
public class StudentService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private StudentDAO studentDAO;

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
		return studentDAO;
	}
}
