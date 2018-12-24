package org.app.model.beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.model.dao.AddressDAO;
import org.app.model.entity.Address;

@Stateless
@Remote(AddressDAO.class)
public class AddressBean implements AddressDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Address create(Address xentity) {
		em.persist(xentity);
		em.flush();
		return xentity;
	}

	@Override
	public Address update(Address xentity) {
		try {
			return em.merge(xentity);
		} finally {
			em.flush();
		}
	}


	@Override
	public void remove(Integer id) {
		Address toBeDeleted = findByID(id);
		em.remove(toBeDeleted);
	}

	@Override
	public Address findByID(Integer id) {
		return em.find(Address.class, id);
	}


}
