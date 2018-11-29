package org.app.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SuppressWarnings("all")
@NamedQueries({ 
	@NamedQuery(name = SgiGroup.QUERY_FIND_ALL, query = "SELECT c FROM SgiGroup c"), 
	@NamedQuery(name = SgiGroup.QUERY_FIND_BY_PRIORITY, query = "SELECT c FROM SgiGroup c WHERE c.listPrio =  :listPrio")
})
public class SgiGroup extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_FIND_ALL = "SgiGroup.FindAll";
	public static final String QUERY_FIND_BY_PRIORITY = "SgiGroup.FindByPriority";
	
	@Column(unique = true, nullable = false)
	private int listPrio;

	@Column(unique = true, nullable = false)
	private String entityValue;

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


}
