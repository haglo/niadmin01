package org.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.envers.Audited;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity
@Audited
@NamedQueries({ 
	@NamedQuery(name = NiTable.QUERY_FIND_ALL, query = "SELECT c FROM NiTable c"), 
	@NamedQuery(name = NiTable.QUERY_FIND_BY_ROOMID, query = "SELECT a FROM NiTable a WHERE a.room.id = :roomID")	
})
public class NiTable extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_FIND_ALL = "NiTable.FindAll";
	public static final String QUERY_FIND_BY_ROOMID = "NiTable.FindByRoomID";

	private String tableNumber;
	
	/**
	 * Ohne Raum darf keine TableNummer angelegt werden
	 * Kontrolliert die Verknüpfung
	 */
	@ManyToOne()
	private Room room;

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}



	
}
