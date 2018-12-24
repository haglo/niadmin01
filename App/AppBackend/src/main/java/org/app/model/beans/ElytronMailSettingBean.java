package org.app.model.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.model.dao.ElytronMailSettingDAO;
import org.app.model.entity.ElytronMailSetting;
import org.app.model.entity.ElytronUser;

@Stateless
@Remote(ElytronMailSettingDAO.class)
public class ElytronMailSettingBean implements ElytronMailSettingDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public ElytronMailSetting create(ElytronMailSetting xentity) {
		em.persist(xentity);
		em.flush();
		return xentity;
	}

	@Override
	public ElytronMailSetting update(ElytronMailSetting xentity) {
		try {
			return em.merge(xentity);
		} finally {
			em.flush();
		}
	}

	@Override
	public void remove(Integer id) {
		ElytronMailSetting toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
		em.flush();
	}

	@Override
	public ElytronMailSetting findByID(Integer id) {
		return em.find(ElytronMailSetting.class, id);
	}

	@Override
	public List<ElytronMailSetting> findAll() {
		return em.createNamedQuery(ElytronMailSetting.QUERY_FIND_ALL, ElytronMailSetting.class).getResultList();
	}

	@Override
	public List<ElytronMailSetting> findAllExpanded() {
		return em.createNamedQuery(ElytronMailSetting.QUERY_FIND_ALL_EXPANDED, ElytronMailSetting.class).getResultList();
	}

	@Override
	public ElytronMailSetting findByElytronUser(ElytronUser param) {
		return em.createNamedQuery(ElytronMailSetting.QUERY_FIND_BY_ELYTRONUSER, ElytronMailSetting.class).setParameter("elytronUser", param)
				.getSingleResult();
	}
}
