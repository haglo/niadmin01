package org.app.model.dao;

import java.util.List;
import java.util.UUID;

import org.app.model.entity.Visor;
import org.app.model.entity.Visor_AUD;

public interface VisorDAO {

	public Visor create(Visor xentity);

	public Visor update(Visor xentity);

	public void remove(Integer id);

	public List<Visor> findAll();

	public Visor findByID(Integer id);

	public List<Visor> findByPriority(Integer listPrio);
	
	public List<Visor_AUD> findAudById(Integer id);

}