package org.app.view.authentication;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.app.controler.ElytronUserService;
import org.app.model.audit.LoggedInUser;
import org.app.model.entity.ElytronUser;

@RequestScoped
public class ValidateElytronUser {

	@Inject
	private ElytronUserService elytronUserService;

	@Inject
	LoggedInUser loggedInUser;

	private ElytronUser elytronUser;

	public boolean validate(String username) {

		boolean result = false;
		boolean elytronUserExists = false;
		elytronUser = new ElytronUser();

		List<ElytronUser> elytronUserList = elytronUserService.getDAO().findAll();
		for (ElytronUser entry : elytronUserList) {
			if (entry.getUsername().equals(username)) {
				elytronUser = entry;
				elytronUserExists = true;
			}
		}

		if (elytronUserExists) {
			loggedInUser.setElytronUser(elytronUser);
			result = true;
		}

		if (!elytronUserExists) {
			try {
				ElytronUser nutElytronUser = new ElytronUser();
				nutElytronUser = elytronUserService.getDAO().findByID(1);

				elytronUser.setUsername(username);
				elytronUser.setElytronRole(nutElytronUser.getElytronRole());
				elytronUser.setDefaultLanguage(nutElytronUser.getDefaultLanguage());
				elytronUser.setDefaultTheme(nutElytronUser.getDefaultTheme());
				elytronUserService.getDAO().create(elytronUser);

				loggedInUser.setElytronUser(elytronUser);
				result = true;
			} catch (Exception ex) {
				ex.printStackTrace();
				result = false;
			}
		}
		return result;
	}

}
