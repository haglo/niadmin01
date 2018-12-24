package org.app.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.envers.Audited;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity
@Audited
@NamedQueries({ 
	@NamedQuery(name = Address.QUERY_FIND_ALL, query = "SELECT c FROM Address c"), 
	@NamedQuery(name = Address.QUERY_FIND_BY_PERSONID, query = "SELECT a FROM Address a WHERE a.person.id = :personID")	
})
public class Address extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_FIND_ALL = "Address.FindAll";
	public static final String QUERY_FIND_BY_PERSONID = "Address.FindByPersonID";

	private String street;
	
	private String postbox;

	private String zip;

	private String city;

	/**
	 * Ohne Person darf keine Adresse angelegt werden
	 * Kontrolliert die Verknüpfung
	 */
	@ManyToOne()
//	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH} , optional = false)
	private Person person;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostbox() {
		return postbox;
	}

	public void setPostbox(String postbox) {
		this.postbox = postbox;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
