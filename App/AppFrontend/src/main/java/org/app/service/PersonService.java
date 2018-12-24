package org.app.service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

import org.app.model.dao.PersonDAO;
import org.app.model.entity.Address;
import org.app.model.entity.Person;

/*
 * Managed Bean
 * MVC: Controler-Part
 * Repräsentiert den kpmpletten Lebenszyklus eines Personendatensatz &
 * Bietet zusätzliche Funktionalität für diesen Personendatensatz an
 */
@RequestScoped
public class PersonService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private PersonDAO DAO;
	
	public void addAddress(@Observes(during=TransactionPhase.AFTER_SUCCESS) Address address, Person person) {
		person.addAddress(address);
		DAO.update(person);
	}
	
	public PersonDAO getDAO() {
		return DAO;
	}

}
