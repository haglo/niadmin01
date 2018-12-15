package org.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Audited
@NamedQueries({ @NamedQuery(name = Desk.QUERY_FIND_ALL, query = "SELECT c FROM Desk c"),
				@NamedQuery(name = Desk.QUERY_FIND_ALL_EXPANDED, query = "SELECT c FROM Desk c join fetch c.room"),
				@NamedQuery(name = Desk.QUERY_FIND_BY_ROOMID, query = "SELECT c FROM Desk c WHERE c.room.id = :roomID") })
public class Desk extends Superclass implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_FIND_ALL = "Desk.FindAll";
	public static final String QUERY_FIND_ALL_EXPANDED = "Desk.FindAllExpanded";
	public static final String QUERY_FIND_BY_ROOMID = "Desk.FindByRoomID";

	private String deskNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROOM_ID")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Room room;

	public String getDeskNumber() {
		return deskNumber;
	}

	public void setDeskNumber(String deskNumber) {
		this.deskNumber = deskNumber;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
