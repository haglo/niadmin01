package org.app.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.model.audit.RevInfo;
import org.app.model.dao.DeskDAO;
import org.app.model.entity.Desk;
import org.app.model.entity.Desk_AUD;
import org.app.model.entity.Visor;
import org.app.model.entity.Visor_AUD;
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
	public Desk create(Desk table) {
		em.persist(table);

		return table;
	}

	@Override
	public Desk update(Desk table) {
		return em.merge(table);
	}

	@Override
	public void remove(Integer id) {
		Desk toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
	}

	@Override
	public Desk findByID(Integer id) {
		return em.find(Desk.class, id);
	}


	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Desk_AUD> findAudById(Integer id) {
		List<Desk_AUD> listAuditedEntities = new ArrayList<Desk_AUD>();

		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Object[]> revDatas = auditReader.createQuery().forRevisionsOfEntity(Visor.class, false, false)
				.add(AuditEntity.id().eq(id)).getResultList();

		for (Object[] revData : revDatas) {
			listAuditedEntities.add(new Desk_AUD((Desk) revData[0], (RevInfo) revData[1], (RevisionType) revData[2]));
		}
		return listAuditedEntities;

	}

}
