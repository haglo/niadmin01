package org.app.model.entity;

import java.io.Serializable;
import org.app.model.audit.RevInfo;
import org.hibernate.envers.RevisionType;

public class SgiGroup_AUD implements Serializable {

	private static final long serialVersionUID = 1L;

	private SgiGroup reventity;
	private RevInfo revision;
	private RevisionType revtype;

	public SgiGroup_AUD() {

	}

	public SgiGroup_AUD(SgiGroup reventity, RevInfo revision, RevisionType revtype) {
		this.reventity = reventity;
		this.revision = revision;
		this.revtype = revtype;
	}

	public SgiGroup getReventity() {
		return reventity;
	}

	public RevInfo getRevision() {
		return revision;
	}

	public RevisionType getRevType() {
		return revtype;
	}

	public SgiGroup_AUD getAuditQueryResult(Object[] item) {
		if (item == null || item.length < 3) {
			return null;
		}

		reventity = (SgiGroup) item[0];
		revision = (RevInfo) item[1];
		revtype = (RevisionType) item[2];

		SgiGroup_AUD auditedEntity = new SgiGroup_AUD(reventity, revision, revtype);

		return auditedEntity;
	}
}
