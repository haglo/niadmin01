package org.app.model.entity.init;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.app.model.dao.ElytronMailSettingDAO;
import org.app.model.dao.ElytronUserDAO;
import org.app.model.entity.ElytronMailSetting;
import org.app.model.entity.ElytronUser;

@Singleton
@Startup
public class InitElytronMailSetting {

	@EJB
	ElytronMailSettingDAO elytronMailSettingDAO;

	@EJB
	ElytronUserDAO elytronUserDAO;
	
	@PostConstruct
	void init() {
		if (elytronMailSettingDAO.findAll().size() == 1) {

			ElytronMailSetting newEntry; 
			
			ElytronUser subEntry = new ElytronUser();
			subEntry = elytronUserDAO.findByID(4);
			
			newEntry = new ElytronMailSetting();
			newEntry.setImapHost("imap.uni-ulm.de");
//			newEntry = elytronMailSettingDAO.findByID(1);
			newEntry.setElytronUser(subEntry);
			subEntry.setMailSetting(newEntry);
			newEntry = elytronMailSettingDAO.update(newEntry);
			
			System.out.println("!!! Done0 !!!" + newEntry.getImapHost());
			System.out.println("!!! Done0 !!!" + subEntry.getUsername());
			System.out.println("!!! Done1 !!!");
		}
		System.out.println("!!! Done2 !!!");
	}
}
