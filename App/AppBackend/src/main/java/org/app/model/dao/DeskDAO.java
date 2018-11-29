package org.app.model.dao;

import java.util.List;

import org.app.model.entity.Desk;
import org.app.model.entity.Desk_AUD;

public interface DeskDAO {

	public Desk create(Desk table);

	public Desk update(Desk table);

	public void remove(Integer id);

	public Desk findByID(Integer id);
	
	public List<Desk_AUD> findAudById(Integer id);

}