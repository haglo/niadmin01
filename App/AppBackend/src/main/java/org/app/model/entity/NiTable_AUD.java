package org.app.model.entity;

import java.io.Serializable;

import org.app.model.audit.RevInfo;
import org.hibernate.envers.RevisionType;


public class NiTable_AUD implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private NiTable reventity;
	private RevInfo revision;
	private RevisionType revtype;
	
	public NiTable_AUD() {
		
	}

	public NiTable_AUD(NiTable reventity, RevInfo revision, RevisionType revtype) {
		this.reventity = reventity;
		this.revision =revision;
		this.revtype = revtype;
	}

	public NiTable getEntity() {
		return reventity;
	}

	public RevInfo getRevision() {
		return revision;
	}

	public RevisionType getRevType() {
		return revtype;
	}

	public NiTable_AUD getAuditQueryResult(Object[] item) {
		if (item == null || item.length < 3) {
			return null;
		}

		reventity = (NiTable) item[0];
		revision = (RevInfo) item[1];
		revtype = (RevisionType) item[2];

		NiTable_AUD person_aud = new NiTable_AUD(reventity, revision, revtype);

		return person_aud;
	}
}
