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
	private long version = 0L;

	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
)
	private UUID uuid;

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


	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@PrePersist
	public void prePersist() {
		if (getUuid() == null) {
			setUuid(UUID.randomUUID());
		}
	}

	@Override
	public String toString() {
		return getClass().getName() + " [uuid=" + uuid + "]";
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

		this.uuid = getUuid();
		other.uuid = other.getUuid();

		return this.uuid != null && this.uuid.equals(other.uuid);
	}
}
