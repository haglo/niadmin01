package org.app.model.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.model.audit.RevInfo;
import org.app.model.dao.VisorDAO;
import org.app.model.entity.Visor;
import org.app.model.entity.Visor_AUD;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Remote(VisorDAO.class)
public class VisorBean implements VisorDAO {

	@PersistenceContext
	private EntityManager em;

	private Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

	@Override
	public Visor create(Visor visor) {
		em.persist(visor);
		return visor;
	}

	@Override
	public Visor update(Visor visor) {
		try {
			return em.merge(visor);
		} finally {
			em.flush();
			LOG.info("MyLogMessage");
		}

	}

	@Override
	public void remove(Integer id) {
		Visor toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
	}

	@Override
	public List<Visor> findAll() {
		return em.createNamedQuery(Visor.QUERY_FIND_ALL, Visor.class).getResultList();
	}

	@Override
	public Visor findByID(Integer id) {
		return em.find(Visor.class, id);

	}

//	@Override
//	public Visor findByID(UUID id) {
//		return em.createNamedQuery(Visor.QUERY_FIND_BY_UUID, Visor.class).setParameter("id", id).getSingleResult();
//	}

	@Override
	public List<Visor> findByPriority(Integer listPrio) {
		return em.createNamedQuery(Visor.QUERY_FIND_BY_PRIORITY, Visor.class).setParameter("listPrio", listPrio)
				.getResultList();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Visor_AUD> findAudById(Integer id) {
		List<Visor_AUD> listAuditedEntities = new ArrayList<Visor_AUD>();

		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Object[]> revDatas = auditReader.createQuery().forRevisionsOfEntity(Visor.class, false, false)
				.add(AuditEntity.id().eq(id)).getResultList();

		for (Object[] revData : revDatas) {
			listAuditedEntities.add(new Visor_AUD((Visor) revData[0], (RevInfo) revData[1], (RevisionType) revData[2]));
		}
		return listAuditedEntities;

	}

}
