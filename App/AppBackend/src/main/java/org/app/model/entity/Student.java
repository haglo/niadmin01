package org.app.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Audited
@NamedQueries({ @NamedQuery(name = Student.QUERY_FIND_ALL, query = "SELECT c FROM Student c") })
public class Student extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "Student.FindAll";

	private String firstName;

	private String lastName;
	
	private String accountName;
	
	private String initalPassword;

	private String emailAddress;
	
	private String startDate;
	
	private String endDate;

	/**
	 * Ohne Visor darf keine Student angelegt werden
	 * Kontrolliert die Verknüpfung
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH} , optional = false)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Visor visor;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SGIGROUP_ID")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private SgiGroup sgiGroup;


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
	
	public SgiGroup getSgiGroup() {
		return sgiGroup;
	}

	public void setSgiGroup(SgiGroup sgiGroup) {
		this.sgiGroup = sgiGroup;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getInitalPassword() {
		return initalPassword;
	}

	public void setInitalPassword(String initalPassword) {
		this.initalPassword = initalPassword;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Visor getVisor() {
		return visor;
	}

	public void setVisor(Visor visor) {
		this.visor = visor;
	}


}
