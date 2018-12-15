package org.app.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.model.audit.RevInfo;
import org.app.model.dao.RoomDAO;
import org.app.model.entity.Room;
import org.app.model.entity.Room_AUD;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Remote(RoomDAO.class)
public class RoomBean implements RoomDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Room create(Room room) {
		em.persist(room);
		return room;
	}

	@Override
	public Room update(Room room) {
		try {
			return em.merge(room);
		} finally {
			em.flush();
		}
	}

	@Override
	public void remove(Integer id) {
		Room toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
	}

	@Override
	public Room findByID(Integer id) {
		return em.find(Room.class, id);
	}
	
	@Override
	public List<Room> findAll() {
		return em.createNamedQuery(Room.QUERY_FIND_ALL, Room.class).getResultList();
	}

	@Override
	public List<Room> findByPriority(Integer listPrio) {
		return em.createNamedQuery(Room.QUERY_FIND_BY_PRIORITY, Room.class).setParameter("listPrio", listPrio)
				.getResultList();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Room_AUD> findAudById(Integer id) {
		List<Room_AUD> listAuditedEntities = new ArrayList<>();

		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Object[]> revDatas = auditReader.createQuery().forRevisionsOfEntity(Room.class, false, false)
				.add(AuditEntity.id().eq(id)).getResultList();

		for (Object[] revData : revDatas) {
			listAuditedEntities.add(new Room_AUD((Room) revData[0], (RevInfo) revData[1], (RevisionType) revData[2]));
		}
		return listAuditedEntities;

	}

}
