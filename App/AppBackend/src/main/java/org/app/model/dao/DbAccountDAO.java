package org.app.model.dao;

import java.util.List;

import org.app.model.entity.DbAccount;

public interface DbAccountDAO {

	public DbAccount create(DbAccount xentity);

	public DbAccount update(DbAccount xentity);

	public void remove(Integer id);

	public DbAccount findByID(Integer id);

	public DbAccount findByUserName(String username);

	public List<DbAccount> findAll();

}