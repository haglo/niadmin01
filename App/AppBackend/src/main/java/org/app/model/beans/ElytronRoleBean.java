package org.app.model.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.model.dao.ElytronRoleDAO;
import org.app.model.entity.ElytronRole;

@Stateless
@Remote(ElytronRoleDAO.class)
public class ElytronRoleBean implements ElytronRoleDAO {

	@PersistenceContext
	private EntityManager em;

	public ElytronRole findByID(Integer id) {
		return em.find(ElytronRole.class, id);
	}

	@Override
	public ElytronRole findByName(String erole) {
		return em.createNamedQuery(ElytronRole.QUERY_FIND_BY_ROLENAME, ElytronRole.class)
				.setParameter("rolename", erole).getSingleResult();
	}

	public List<ElytronRole> findAll() {
		return em.createNamedQuery(ElytronRole.QUERY_FIND_ALL, ElytronRole.class).getResultList();
	}

}
