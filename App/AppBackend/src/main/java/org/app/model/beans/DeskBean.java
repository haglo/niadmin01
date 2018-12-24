package org.app.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.JoinType;

import org.app.model.audit.RevInfo;
import org.app.model.dao.DeskDAO;
import org.app.model.entity.Desk;
import org.app.model.entity.Desk_AUD;
import org.app.model.entity.ElytronUser;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;

@Stateless
@Remote(DeskDAO.class)
public class DeskBean implements DeskDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Desk create(Desk xentity) {
		em.persist(xentity);
		em.flush();
		return xentity;
	}

	@Override
	public Desk update(Desk xentity) {
		try {
			return em.merge(xentity);
		} finally {
			em.flush();
		}
	}

	@Override
	public void remove(Integer id) {
		Desk toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
		em.flush();
	}

	@Override
	public Desk findByID(Integer id) {
		return em.find(Desk.class, id);
	}

	@Override
	public List<Desk> findAll() {
		return em.createNamedQuery(Desk.QUERY_FIND_ALL, Desk.class).getResultList();
	}

	public List<Desk> findAllExpanded() {
		return em.createNamedQuery(Desk.QUERY_FIND_ALL_EXPANDED, Desk.class).getResultList();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	@TransactionAttribute
	public List<Desk_AUD> findAudById(Integer id) {
		List<Desk_AUD> auditedEntities = new ArrayList<>();

		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Object[]> revDatas = auditReader.createQuery().forRevisionsOfEntity(Desk.class, false, false)
				.add(AuditEntity.id().eq(id)).getResultList();
		for (Object[] revData : revDatas) {
			auditedEntities.add(new Desk_AUD((Desk) revData[0], (RevInfo) revData[1], (RevisionType) revData[2]));
		}
		return auditedEntities;

	}

	@Override
	@SuppressWarnings({ "unchecked" })
	@TransactionAttribute
	public List<Desk_AUD> findAudByIdExtended(Integer id) {
		List<Desk_AUD> auditedEntities = new ArrayList<>();

		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Object[]> revDatas = auditReader.createQuery().forRevisionsOfEntity(Desk.class, false, false)
				.add(AuditEntity.id().eq(id)).traverseRelation("room", JoinType.INNER).getResultList();

		for (Object[] revData : revDatas) {
			auditedEntities.add(new Desk_AUD((Desk) revData[0], (RevInfo) revData[1], (RevisionType) revData[2]));
		}
		return auditedEntities;
	}

}
