package org.app.model.dao;

import java.util.List;
import org.app.model.entity.Communication;

public interface CommunicationDAO {

	public Communication create(Communication xentity);

	public Communication update(Communication xentity);

	public void remove(Integer id);

	public Communication findByID(Integer id);

	public List<Communication> findAll();
}