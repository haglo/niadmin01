package org.app.service;

import java.io.Serializable;

import javax.ejb.EJB;
import org.app.model.dao.ElytronMailSettingDAO;
import com.vaadin.cdi.annotation.VaadinSessionScoped;

@VaadinSessionScoped
public class ElytronMailSettingService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ElytronMailSettingDAO DAO;
	

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

	public ElytronMailSettingDAO getDAO() {
		return DAO;
	}

}
