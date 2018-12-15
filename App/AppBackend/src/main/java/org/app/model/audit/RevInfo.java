package org.app.model.audit;

import java.beans.Transient;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.app.model.entity.ElytronUser;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;


@Entity
@RevisionEntity(CustomRevisionListener.class)
public class RevInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@RevisionNumber
	private Integer id;

	@RevisionTimestamp
	private Long timestamp;


	/**
	 * Unidirectional
	 * diese:		eine
	 * gegenüber:	mehr
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ELYTRONUSER_ID")
	private ElytronUser elytronUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Transient
	public Date getRevisionDate() {
		return new Date(timestamp);
	}

	@Transient
	public Instant getRevisionInstant() {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.systemDefault())
				.toInstant(ZoneOffset.UTC);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public ElytronUser getElytronUser() {
		return elytronUser;
	}

	public void setElytronUser(ElytronUser elytronUser) {
		this.elytronUser = elytronUser;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof RevInfo)) {
			return false;
		} else {
			RevInfo that = (RevInfo) o;
			return this.id == that.id && this.timestamp == that.timestamp;
		}
	}

	public int hashCode() {
		int result = this.id;
		result = 31 * result + (int) (this.timestamp ^ this.timestamp >>> 32);
		return result;
	}

	public String toString() {
		return "RevisionEntity(revisionNumber = " + this.id + ", revisionDate = "
				+ DateFormat.getDateTimeInstance().format(this.getRevisionDate()) + ")";
	}
}