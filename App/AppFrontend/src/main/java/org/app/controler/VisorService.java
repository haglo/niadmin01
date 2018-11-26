package org.app.controler;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import org.app.model.dao.VisorDAO;
import org.app.model.entity.Visor;

import com.vaadin.cdi.annotation.VaadinSessionScoped;

/*
 * Managed Bean
 * In MVC the Controler-Part
 */
@VaadinSessionScoped
public class VisorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private VisorDAO visorDAO;

	private Visor visor;
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

	public VisorDAO getDAO() {
		return visorDAO;
	}
}
