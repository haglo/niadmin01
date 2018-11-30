package org.app.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
	
	private String initialPassword;

	private String mailaddress;
	
	private String startDate;
	
	private String endDate;


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "VISOR_ID")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Visor visor;


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

	public String getInitialPassword() {
		return initialPassword;
	}

	public void setInitialPassword(String initialPassword) {
		this.initialPassword = initialPassword;
	}

	public String getMailaddress() {
		return mailaddress;
	}

	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
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
