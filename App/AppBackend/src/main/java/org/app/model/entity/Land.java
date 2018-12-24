package org.app.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "LAND")
@SuppressWarnings("all")
@NamedQueries(
		{ @NamedQuery(name = Land.QUERY_FIND_ALL, query = "SELECT c FROM Land c"),
		  @NamedQuery(name = Land.QUERY_FIND_BY_PRIORITY, query = "SELECT c FROM Land c WHERE c.listPrio =  :listPrio"), 
		  @NamedQuery(name = Land.QUERY_FIND_BY_LAND, query = "SELECT c FROM Land c WHERE c.land =  :land") 
		}
)
public class Land extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "Land.FindAll";
	public static final String QUERY_FIND_BY_PRIORITY = "Land.FindByPriority";
	public static final String QUERY_FIND_BY_LAND = "Land.FindByLand";


	@NotNull
	private int listPrio;

	@Column(unique = true)
	private String land;

	@Size(max = 5)
	@Column(unique = true)
	private String isoCode;

	private String comment;

	public int getListPrio() {
		return listPrio;
	}

	public void setListPrio(int listPrio) {
		this.listPrio = listPrio;
	}
	
	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
