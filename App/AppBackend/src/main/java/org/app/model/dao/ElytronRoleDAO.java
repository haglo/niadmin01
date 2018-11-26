package org.app.model.dao;

import java.util.List;

import org.app.model.entity.ElytronRole;

public interface ElytronRoleDAO {

	
	public ElytronRole findByID(Integer id);

	public ElytronRole findByName(String ename);

	public List<ElytronRole> findAll();

}