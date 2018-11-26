package org.app.model.dao;

import java.util.List;

import org.app.model.entity.SgiGroup;
import org.app.model.entity.SgiGroup_AUD;

public interface SgiGroupDAO {

	public SgiGroup create(SgiGroup sgiGroup);

	public SgiGroup update(SgiGroup sgiGroup);

	public void remove(Integer id);

	public List<SgiGroup> findAll();

	public SgiGroup findByID(Integer id);

	public List<SgiGroup> findByPriority(Integer listPrio);
	
	public List<SgiGroup_AUD> findAudById(Integer id);

}