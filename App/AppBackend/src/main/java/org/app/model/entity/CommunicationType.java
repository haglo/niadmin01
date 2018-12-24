package org.app.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

@Entity
@NamedQuery(name = CommunicationType.QUERY_GET_ALL, query = "SELECT c FROM CommunicationType c")
public class CommunicationType extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_GET_ALL = "CommunicationType.GetAll";

	@NotNull
	private int listPrio;

	@NotNull
	private String communicationType;

	public int getListPrio() {
		return listPrio;
	}

	public void setListPrio(int listPrio) {
		this.listPrio = listPrio;
	}

	public String getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

}
