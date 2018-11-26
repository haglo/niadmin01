package org.app.model.dao;

import java.util.List;

import org.app.model.entity.Room;
import org.app.model.entity.Room_AUD;

public interface RoomDAO {

	public Room create(Room room);

	public Room update(Room room);

	public void remove(Integer id);

	public List<Room> findAll();

	public Room findByID(Integer id);

	public List<Room> findByPriority(Integer listPrio);
	
	public List<Room_AUD> findAudById(Integer id);

}