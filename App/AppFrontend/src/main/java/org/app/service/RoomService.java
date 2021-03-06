package org.app.service;

import java.io.Serializable;

import javax.ejb.EJB;

import org.app.model.dao.RoomDAO;

import com.vaadin.cdi.annotation.VaadinSessionScoped;

/*
 * Managed Bean
 * In MVC the Controler-Part
 */
@VaadinSessionScoped
public class RoomService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private RoomDAO DAO;

	public RoomDAO getDAO() {
		return DAO;
	}

}
