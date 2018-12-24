package org.app.model.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.app.model.dao.CommunicationDAO;
import org.app.model.entity.Communication;

@Stateless
@Remote(CommunicationDAO.class)
public class CommunicationBean implements CommunicationDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Communication create(Communication xentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Communication update(Communication xentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Communication findByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Communication> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
