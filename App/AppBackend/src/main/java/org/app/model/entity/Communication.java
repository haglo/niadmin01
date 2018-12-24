package org.app.model.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Audited
@NamedQueries({ 
	@NamedQuery(name = Communication.QUERY_FIND_ALL, query = "SELECT c FROM Communication c"), 
	@NamedQuery(name = Communication.QUERY_FIND_BY_PERSONID, query = "SELECT a FROM Communication a WHERE a.person.id = :personID")	
})
public class Communication extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_FIND_ALL = "Communication.FindAll";
	public static final String QUERY_FIND_BY_PERSONID = "Communication.FindByPersonID";

	@NotNull
	private String communication;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMMUNICATIONTYPE_ID")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private CommunicationType communicationType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KIND_ID")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Kind kind;

	
	/**
	 * Ohne Person darf keine Communication angelegt werden
	 * Kontrolliert die Verknüpfung
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH} , optional = false)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Person person;


	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
