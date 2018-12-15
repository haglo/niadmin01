package org.app.model.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;


@MappedSuperclass
public abstract class Superclass implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Version
	@Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
	private Long version = 0L;

	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
)
	private UUID uuidEntry;

	private String comment;

	public Superclass() {
		this.prePersist();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UUID getUuidEntry() {
		return uuidEntry;
	}

	public void setUuidEntry(UUID uuidEntry) {
		this.uuidEntry = uuidEntry;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@PrePersist
	public void prePersist() {
		if (getUuidEntry() == null) {
			setUuidEntry(UUID.randomUUID());
		}
	}

	@Override
	public String toString() {
		return getClass().getName() + " [uuid=" + uuidEntry + "]";
	}

	@Override
	public int hashCode() {
		this.id = getId();
		return this.id != null ? this.id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final Superclass other = (Superclass) obj;

		this.uuidEntry = getUuidEntry();
		other.uuidEntry = other.getUuidEntry();

		return this.uuidEntry != null && this.uuidEntry.equals(other.uuidEntry);
	}
}
