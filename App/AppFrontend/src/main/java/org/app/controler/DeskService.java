package org.app.controler;

import java.io.Serializable;

import javax.ejb.EJB;

import org.app.model.dao.DeskDAO;

import com.vaadin.cdi.annotation.VaadinSessionScoped;

/*
 * Managed Bean
 * In MVC the Controler-Part
 */
@VaadinSessionScoped
public class DeskService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private DeskDAO deskDAO;
	
	public DeskDAO getDAO() {
		return deskDAO;
	}

}
