package org.app.model.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.model.dao.ElytronUserDAO;
import org.app.model.entity.ElytronUser;

@Stateless
@Remote(ElytronUserDAO.class)
public class ElytronUserBean implements ElytronUserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public ElytronUser create(ElytronUser euser) {
		em.persist(euser);
		em.flush();
		return euser;
	}

	@Override
	public ElytronUser update(ElytronUser euser) {
		try {
			return em.merge(euser);
		} finally {
			em.flush();
		}
	}

	@Override
	public void remove(Integer id) {
		ElytronUser toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
		em.flush();
	}

	public ElytronUser findByID(Integer id) {
		return em.find(ElytronUser.class, id);
	}

	public ElytronUser findByName(String ename) {
		return em.createNamedQuery(ElytronUser.QUERY_FIND_BY_USERNAME, ElytronUser.class).setParameter("username", ename)
				.getSingleResult();
	}
	
	public List<ElytronUser> findAll() {
		return em.createNamedQuery(ElytronUser.QUERY_FIND_ALL, ElytronUser.class).getResultList();
	}

	public List<ElytronUser> findAllExpanded() {
		return em.createNamedQuery(ElytronUser.QUERY_FIND_ALL_EXPANDED, ElytronUser.class).getResultList();
	}
}
