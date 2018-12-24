package org.app.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.app.model.entity.Desk;
import org.app.model.entity.Desk_AUD;
import org.app.model.entity.ElytronUser;

public interface DeskDAO {

	public Desk create(Desk xentity);

	public Desk update(Desk xentity);

	public void remove(Integer id);

	public Desk findByID(Integer id);
	
	public List<Desk> findAll();

	public List<Desk> findAllExpanded();

	public List<Desk_AUD> findAudById(Integer id);
	
	public List<Desk_AUD> findAudByIdExtended(Integer id);
	
}