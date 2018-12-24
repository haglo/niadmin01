package org.app.model.entity.init;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.app.model.dao.ElytronUserDAO;
import org.app.model.entity.ElytronUser;
import org.app.model.entity.Person;

@Singleton
@Startup
public class InitPerson {
	@EJB
	ElytronUserDAO elytronUserDAO;
	
	@PostConstruct
	void init() {
//		ElytronUser elytronUser = new ElytronUser();
//		elytronUser = elytronUserDAO.findByID(4);
//		
//		Person person = new Person();
//		person.setFirstName("Hans-Georg");
//		person.setLastName("Glöckler");
//		elytronUser.setPerson(person);
//		
//		elytronUser = elytronUserDAO.update(elytronUser);

	}
}
