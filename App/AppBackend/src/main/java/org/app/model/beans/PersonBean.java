package org.app.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.app.model.audit.RevInfo;
import org.app.model.dao.AddressDAO;
import org.app.model.dao.CommunicationDAO;
import org.app.model.dao.PersonDAO;
import org.app.model.entity.Address;
import org.app.model.entity.Communication;
import org.app.model.entity.Person;
import org.app.model.entity.Person_AUD;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;

@Stateless
@Remote(PersonDAO.class)
public class PersonBean implements PersonDAO {

	@PersistenceContext
	private EntityManager em;

	@EJB
	AddressDAO addressDAO;

	@EJB
	CommunicationDAO communicationDAO;

	@Override
	public Person create(Person xentity) {
		em.persist(xentity);
		em.flush();
		return xentity;
	}

	@Override
	public Person update(Person xentity) {
		try {
			return em.merge(xentity);
		} finally {
			em.flush();
		}
	}

	@Override
	public void remove(Integer id) {
		Person toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
		em.flush();
	}

	@Override
	public Person findByID(Integer id) {
		return em.find(Person.class, id);
	}

	@Override
	public List<Person> findAll() {
		return em.createNamedQuery(Person.QUERY_FIND_ALL, Person.class).getResultList();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Person_AUD> findAudById(Integer id) {
		List<Person_AUD>  auditedEntities = new ArrayList<Person_AUD>();

		AuditReader auditReader = AuditReaderFactory.get(em);
		List<Object[]> revDatas = auditReader.createQuery().forRevisionsOfEntity(Person.class, false, false)
				.add(AuditEntity.id().eq(id)).getResultList();

		for (Object[] revData : revDatas) {
			 auditedEntities
					.add(new Person_AUD((Person) revData[0], (RevInfo) revData[1], (RevisionType) revData[2]));
		}
		return  auditedEntities;

	}
	
}
