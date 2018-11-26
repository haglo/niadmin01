package org.app.model.entity;

import java.io.Serializable;
import org.app.model.audit.RevInfo;
import org.hibernate.envers.RevisionType;

public class Visor_AUD implements Serializable {

	private static final long serialVersionUID = 1L;

	private Visor reventity;
	private RevInfo revision;
	private RevisionType revtype;

	public Visor_AUD() {

	}

	public Visor_AUD(Visor reventity, RevInfo revision, RevisionType revtype) {
		this.reventity = reventity;
		this.revision = revision;
		this.revtype = revtype;
	}

	public Visor getReventity() {
		return reventity;
	}

	public RevInfo getRevision() {
		return revision;
	}

	public RevisionType getRevType() {
		return revtype;
	}

	public Visor_AUD getAuditQueryResult(Object[] item) {
		if (item == null || item.length < 3) {
			return null;
		}

		reventity = (Visor) item[0];
		revision = (RevInfo) item[1];
		revtype = (RevisionType) item[2];

		Visor_AUD auditedEntity = new Visor_AUD(reventity, revision, revtype);

		return auditedEntity;
	}
}
