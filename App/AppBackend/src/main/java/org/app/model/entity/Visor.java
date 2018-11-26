package org.app.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@NamedQueries({ 
		@NamedQuery(name = Visor.QUERY_FIND_ALL, query = "SELECT c FROM Visor c"),
		@NamedQuery(name = Visor.QUERY_FIND_BY_PRIORITY, query = "SELECT c FROM Visor c WHERE c.listPrio =  :listPrio"),
		})
@Audited
public class Visor extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "Visor.FindAll";
	public static final String QUERY_FIND_BY_PRIORITY = "Visor.FindByPriority";
	public static final String QUERY_FIND_BY_UUID = "Visor.FindByUUID";

	@NotNull
	private int listPrio;

	@Column(unique = true, nullable = false)
	private String mdValue;

	public int getListPrio() {
		return listPrio;
	}

	public void setListPrio(int listPrio) {
		this.listPrio = listPrio;
	}

	public String getMdValue() {
		return mdValue;
	}

	public void setMdValue(String mdValue) {
		this.mdValue = mdValue;
	}
}
