package org.app.model.dao;

import java.util.List;

import org.app.model.entity.ElytronUser;

public interface ElytronUserDAO {

	public ElytronUser create(ElytronUser elytronUser);

	public ElytronUser update(ElytronUser elytronUser);

	public void remove(Integer id);	
	
	public ElytronUser findByID(Integer id);

	public ElytronUser findByName(String ename);

	public List<ElytronUser> findAll();

	public List<ElytronUser> findAllExpanded();
}