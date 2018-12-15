package org.app.model.audit;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.hibernate.envers.RevisionListener;

@SessionScoped
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
