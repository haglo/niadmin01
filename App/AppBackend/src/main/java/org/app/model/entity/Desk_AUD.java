package org.app.model.entity;

import java.io.Serializable;

import org.app.model.audit.RevInfo;
import org.hibernate.envers.RevisionType;


public class Desk_AUD implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Desk reventity;
	private RevInfo revision;
	private RevisionType revtype;
	
	public Desk_AUD() {
		
	}

	public Desk_AUD(Desk reventity, RevInfo revision, RevisionType revtype) {
		this.reventity = reventity;
		this.revision =revision;
		this.revtype = revtype;
	}

	public Desk getReventity() {
		return reventity;
	}

	public RevInfo getRevision() {
		return revision;
	}

	public RevisionType getRevType() {
		return revtype;
	}

	public Desk_AUD getAuditQueryResult(Object[] item) {
		if (item == null || item.length < 3) {
			return null;
		}

		reventity = (Desk) item[0];
		revision = (RevInfo) item[1];
		revtype = (RevisionType) item[2];

		Desk_AUD auditedEntity = new Desk_AUD(reventity, revision, revtype);

		return auditedEntity;
	}
}
