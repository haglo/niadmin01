package org.app.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Audited
@NamedQueries({ 
		@NamedQuery(name = Person.QUERY_FIND_ALL, query = "SELECT c FROM Person c"),
		@NamedQuery(name = Person.QUERY_FIND_BY_ELYTRONUSER, query = "SELECT c FROM Person c WHERE c.elytronUser =  :elytronUser") 
		})
public class Person extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "Person.FindAll";
	public static final String QUERY_FIND_BY_ELYTRONUSER = "Person.FindByElytronUser";

	private String firstName;

	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Address> addresses = new HashSet<Address>();

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Communication> communications = new HashSet<Communication>();

	@OneToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private ElytronUser elytronUser;

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		address.setPerson(this);
		getAddresses().add(address);
	}

	public void removeAddress(Address address) {
		getAddresses().remove(address);
		address.setPerson(null);
	}

	public void updateAddress(Address address) {
		address.setPerson(this);
		getAddresses().remove(address);
		getAddresses().add(address);
	}

	public Set<Communication> getCommunications() {
		return communications;
	}

	public void setCommunications(Set<Communication> communications) {
		this.communications = communications;
	}

	public void addCommunication(Communication communication) {
		communication.setPerson(this);
		communications.add(communication);
	}

	public void removeCommunication(Communication communication) {
		communications.remove(communication);
		communication.setPerson(null);
	}

	public ElytronUser getElytronUser() {
		return elytronUser;
	}

	public void setElytronUser(ElytronUser elytronUser) {
		this.elytronUser = elytronUser;
	}

}
