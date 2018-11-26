package org.app.model.dao;

import java.util.List;

import org.app.model.entity.NiTable;
import org.app.model.entity.NiTable_AUD;

public interface NiTableDAO {

	public NiTable create(NiTable table);

	public NiTable update(NiTable table);

	public void remove(Integer id);

	public NiTable findByID(Integer id);
	
	public List<NiTable_AUD> findAudById(Integer id);

}