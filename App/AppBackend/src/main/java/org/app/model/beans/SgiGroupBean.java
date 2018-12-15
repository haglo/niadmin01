package org.app.model.beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.model.audit.RevInfo;
import org.app.model.dao.SgiGroupDAO;
import org.app.model.entity.SgiGroup;
import org.app.model.entity.SgiGroup_AUD;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;

@Stateless
@Remote(SgiGroupDAO.class)
public class SgiGroupBean implements SgiGroupDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public SgiGroup create(SgiGroup sgiGroup) {
		em.persist(sgiGroup);
		em.flush();
		return sgiGroup;
	}

	@Override
	public SgiGroup update(SgiGroup sgiGroup) {
		try {
			return em.merge(sgiGroup);
		} finally {
			em.flush();
		}
	}

	@Override
	public void remove(Integer id) {
		SgiGroup toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
	}

	@Override
	public List<SgiGroup> findAll() {
		return em.createNamedQuery(SgiGroup.QUERY_FIND_ALL, SgiGroup.class).getResultList();
	}

	@Override
	public SgiGroup findByID(Integer id) {
		return em.find(SgiGroup.class, id);
	}

	@Override
	public List<SgiGroup> findByPriority(Integer listPrio) {
		return em.createNamedQuery(SgiGroup.QUERY_FIND_BY_PRIORITY, SgiGroup.class).setParameter("listPrio", listPrio)
				.getResultList();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<SgiGroup_AUD> findAudById(Integer id) {
		List<SgiGroup_AUD> listAuditedEntities = new ArrayList<>();

		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Object[]> revDatas = auditReader.createQuery().forRevisionsOfEntity(SgiGroup.class, false, false)
				.add(AuditEntity.id().eq(id)).getResultList();

		for (Object[] revData : revDatas) {
			listAuditedEntities.add(new SgiGroup_AUD((SgiGroup) revData[0], (RevInfo) revData[1], (RevisionType) revData[2]));
		}
		return listAuditedEntities;

	}

}
