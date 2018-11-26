package org.app.model.entity;

import java.io.Serializable;
import org.app.model.audit.RevInfo;
import org.hibernate.envers.RevisionType;

public class Room_AUD implements Serializable {

	private static final long serialVersionUID = 1L;

	private Room reventity;
	private RevInfo revision;
	private RevisionType revtype;

	public Room_AUD() {

	}

	public Room_AUD(Room reventity, RevInfo revision, RevisionType revtype) {
		this.reventity = reventity;
		this.revision = revision;
		this.revtype = revtype;
	}

	public Room getReventity() {
		return reventity;
	}

	public RevInfo getRevision() {
		return revision;
	}

	public RevisionType getRevType() {
		return revtype;
	}

	public Room_AUD getAuditQueryResult(Object[] item) {
		if (item == null || item.length < 3) {
			return null;
		}

		reventity = (Room) item[0];
		revision = (RevInfo) item[1];
		revtype = (RevisionType) item[2];

		Room_AUD auditedEntity = new Room_AUD(reventity, revision, revtype);

		return auditedEntity;
	}
}
