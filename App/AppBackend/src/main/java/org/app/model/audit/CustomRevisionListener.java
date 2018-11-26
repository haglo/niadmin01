package org.app.model.audit;

import java.io.Serializable;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private LoggedInUser userBean;

	public void newRevision(Object revisionEntity) {
		userBean = getUserBean();
		RevInfo rev = (RevInfo) revisionEntity;
		rev.setElytronUser(userBean.getElytronUser());
	}

	
	@SuppressWarnings("unchecked")
	private LoggedInUser getUserBean() {
		BeanManager beanManager = CDI.current().getBeanManager();
		Bean<LoggedInUser> bean = ((Bean<LoggedInUser>) beanManager.getBeans(LoggedInUser.class).iterator().next());
		CreationalContext<LoggedInUser> context = beanManager.createCreationalContext(bean);
		userBean = (LoggedInUser) beanManager.getReference(bean, LoggedInUser.class, context);
		return userBean;
	}

}
