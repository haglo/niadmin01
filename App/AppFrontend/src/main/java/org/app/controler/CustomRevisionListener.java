package org.app.controler;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.app.model.audit.LoggedInUser;
import org.app.model.audit.RevInfo;
import org.hibernate.envers.RevisionListener;

@RequestScoped
public class CustomRevisionListener implements RevisionListener, Serializable {
	
	@Inject 
	RevInfo rev;
	
	@Inject 
	LoggedInUser loggedInUnser;

	private static final long serialVersionUID = 1L;

	public void newRevision(Object revisionEntity) {
		RevInfo rev = (RevInfo) revisionEntity;
		rev.setElytronUser(loggedInUnser.getElytronUser());
	}


}
