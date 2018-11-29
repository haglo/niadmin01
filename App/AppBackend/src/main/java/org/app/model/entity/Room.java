package org.app.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@NamedQueries({ @NamedQuery(name = Room.QUERY_FIND_ALL, query = "SELECT c FROM Room c"),
		@NamedQuery(name = Room.QUERY_FIND_BY_PRIORITY, query = "SELECT c FROM Room c WHERE c.listPrio =  :listPrio") })
@Audited
public class Room extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "Room.FindAll";
	public static final String QUERY_FIND_BY_PRIORITY = "Room.FindByPriority";

	@Column(unique = true, nullable = false)
	private int listPrio;

	@Column(unique = true, nullable = false)
	private String entityValue;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Desk> tables = new HashSet<Desk>();

	public int getListPrio() {
		return listPrio;
	}

	public void setListPrio(int listPrio) {
		this.listPrio = listPrio;
	}


	public String getEntityValue() {
		return entityValue;
	}

	public void setEntityValue(String entityValue) {
		this.entityValue = entityValue;
	}

	public void addTable(Desk entry) {
		entry.setRoom(this);
		getTables().add(entry);
	}

	public void removeTable(Desk entry) {
		getTables().remove(entry);
		entry.setRoom(null);
	}

	public void updateTable(Desk entry) {
		entry.setRoom(this);
		getTables().remove(entry);
		getTables().add(entry);
	}

	public Set<Desk> getTables() {
		return tables;
	}

	public void setTables(Set<Desk> tables) {
		this.tables = tables;
	}

}
