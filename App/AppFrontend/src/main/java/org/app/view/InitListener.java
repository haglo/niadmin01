package org.app.view;


import org.app.view.authentication.AccessControl;
import org.app.view.authentication.AccessControlFactory;
import org.app.view.authentication.LoginScreen;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

/**
 * This class is used to listen to BeforeEnter event of all UIs in order to
 * check whether a user is signed in or not before allowing entering any page.
 * It is registered in a file named
 * com.vaadin.flow.server.VaadinServiceInitListener in META-INF/services.
 */
public class InitListener implements VaadinServiceInitListener {

	private static final long serialVersionUID = 1L;

	@Override
    public void serviceInit(ServiceInitEvent initEvent) {
        final AccessControl accessControlBasic = AccessControlFactory.getInstance()
                .createAccessControlBasic();
        final AccessControl accessControlLdap = AccessControlFactory.getInstance()
                .createAccessControlLdap();

        initEvent.getSource().addUIInitListener(uiInitEvent -> {
            uiInitEvent.getUI().addBeforeEnterListener(enterEvent -> {
            	boolean isSignedIn = false;
            	if (accessControlBasic.isUserSignedIn() || accessControlLdap.isUserSignedIn()) {
            		isSignedIn = true;
            	}
                if (!isSignedIn && !LoginScreen.class.equals(enterEvent.getNavigationTarget()))
                    enterEvent.rerouteTo(LoginScreen.class);
            });
        });
    }
}
