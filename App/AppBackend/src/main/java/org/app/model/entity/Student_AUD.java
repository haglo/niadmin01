package org.app.model.entity;

import java.io.Serializable;
import org.app.model.audit.RevInfo;
import org.hibernate.envers.RevisionType;

public class Student_AUD implements Serializable {

	private static final long serialVersionUID = 1L;

	private Student reventity;
	private RevInfo revision;
	private RevisionType revtype;

	public Student_AUD() {

	}

	public Student_AUD(Student reventity, RevInfo revision, RevisionType revtype) {
		this.reventity = reventity;
		this.revision = revision;
		this.revtype = revtype;
	}

	public Student getReventity() {
		return reventity;
	}

	public RevInfo getRevision() {
		return revision;
	}

	public RevisionType getRevType() {
		return revtype;
	}

	public Student_AUD getAuditQueryResult(Object[] item) {
		if (item == null || item.length < 3) {
			return null;
		}

		reventity = (Student) item[0];
		revision = (RevInfo) item[1];
		revtype = (RevisionType) item[2];

		Student_AUD auditedEntity = new Student_AUD(reventity, revision, revtype);

		return auditedEntity;
	}
}
