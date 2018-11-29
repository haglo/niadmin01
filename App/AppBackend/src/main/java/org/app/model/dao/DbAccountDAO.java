package org.app.model.dao;

import java.util.List;

import org.app.model.entity.DbAccount;

public interface DbAccountDAO {

	public DbAccount create(DbAccount account);

	public DbAccount update(DbAccount account);

	public void remove(int id);

	public DbAccount findByID(int id);

	public DbAccount findByUserName(String username);

	public List<DbAccount> findAll();

}