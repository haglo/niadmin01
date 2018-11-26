package org.app.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.model.audit.RevInfo;
import org.app.model.dao.NiTableDAO;
import org.app.model.entity.NiTable;
import org.app.model.entity.NiTable_AUD;
import org.app.model.entity.Visor;
import org.app.model.entity.Visor_AUD;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;

@Stateless
@Remote(NiTableDAO.class)
public class NiTableBean implements NiTableDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public NiTable create(NiTable table) {
		em.persist(table);

		return table;
	}

	@Override
	public NiTable update(NiTable table) {
		return em.merge(table);
	}

	@Override
	public void remove(Integer id) {
		NiTable toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
	}

	@Override
	public NiTable findByID(Integer id) {
		return em.find(NiTable.class, id);
	}


	@Override
	@SuppressWarnings({ "unchecked" })
	public List<NiTable_AUD> findAudById(Integer id) {
		List<NiTable_AUD> listAuditedEntities = new ArrayList<NiTable_AUD>();

		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Object[]> revDatas = auditReader.createQuery().forRevisionsOfEntity(Visor.class, false, false)
				.add(AuditEntity.id().eq(id)).getResultList();

		for (Object[] revData : revDatas) {
			listAuditedEntities.add(new NiTable_AUD((NiTable) revData[0], (RevInfo) revData[1], (RevisionType) revData[2]));
		}
		return listAuditedEntities;

	}

}
