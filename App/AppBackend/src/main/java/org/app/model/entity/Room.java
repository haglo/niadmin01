package org.app.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.envers.Audited;

@Entity
@NamedQueries({ @NamedQuery(name = Room.QUERY_FIND_ALL, query = "SELECT c FROM Room c"),
		@NamedQuery(name = Room.QUERY_FIND_BY_PRIORITY, query = "SELECT c FROM Room c WHERE c.listPrio =  :listPrio") })
@Audited
public class Room extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "Room.FindAll";
	public static final String QUERY_FIND_BY_PRIORITY = "Room.FindByPriority";

	private Integer listPrio;

	private String entityValue;

	public Integer getListPrio() {
		return listPrio;
	}

	public void setListPrio(Integer listPrio) {
		this.listPrio = listPrio;
	}


	public String getEntityValue() {
		return entityValue;
	}

	public void setEntityValue(String entityValue) {
		this.entityValue = entityValue;
	}
}
