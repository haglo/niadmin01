package org.app.model.entity;

import java.io.Serializable;

import org.app.model.audit.RevInfo;
import org.hibernate.envers.RevisionType;


public class Person_AUD implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Person reventity;
	private RevInfo revision;
	private RevisionType revtype;
	
	public Person_AUD() {
		
	}

	public Person_AUD(Person reventity, RevInfo revision, RevisionType revtype) {
		this.reventity = reventity;
		this.revision =revision;
		this.revtype = revtype;
	}

	public Person getReventity() {
		return reventity;
	}

	public RevInfo getRevision() {
		return revision;
	}

	public RevisionType getRevType() {
		return revtype;
	}

	public Person_AUD getAuditQueryResult(Object[] item) {
		if (item == null || item.length < 3) {
			return null;
		}

		reventity = (Person) item[0];
		revision = (RevInfo) item[1];
		revtype = (RevisionType) item[2];

		Person_AUD auditedEntity = new Person_AUD(reventity, revision, revtype);

		return auditedEntity;
	}
}
