package org.app.model.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.model.dao.DbAccountDAO;
import org.app.model.entity.DbAccount;

@Stateless
@Remote(DbAccountDAO.class)
public class DbAccountBean implements DbAccountDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public DbAccount create(DbAccount account) {
		em.persist(account);
		return account;
	}

	@Override
	public DbAccount update(DbAccount account) {
		return em.merge(account);
	}

	@Override
	public void remove(int id) {
		DbAccount toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
	}

	@Override
	public List<DbAccount> findAll() {
		return em.createNamedQuery(DbAccount.QUERY_FIND_ALL, DbAccount.class).getResultList();
	}

	@Override
	public DbAccount findByID(int id) {
		return em.find(DbAccount.class, id);
	}

	@Override
	public DbAccount findByUserName(String username) {
		return em.createNamedQuery(DbAccount.QUERY_FIND_BY_USERNAME, DbAccount.class).setParameter("username", username)
				.getSingleResult();
	}

}
