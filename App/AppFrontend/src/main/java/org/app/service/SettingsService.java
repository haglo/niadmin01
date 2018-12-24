package org.app.service;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;

import com.vaadin.cdi.annotation.VaadinSessionScoped;

/*
 * Managed Bean
 * In MVC the Controler-Part
 */
@VaadinSessionScoped
public class SettingsService implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private boolean isEditing = false;
	
	private Locale locale = new Locale("en", "GB");

	public boolean getEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
	}

	public void toggleEditing() {
		this.isEditing = !this.isEditing;
	}

	public Locale getLocale() {
		Locale loc = new Locale("de", "DE");
		locale = loc;
		return locale;
	}

	public void setLocale(Locale locale) {
		Locale loc = new Locale("de", "DE");
		this.locale = loc;
	}

 }
