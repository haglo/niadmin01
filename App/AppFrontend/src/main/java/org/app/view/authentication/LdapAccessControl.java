package org.app.view.authentication;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.app.common.Const;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class LdapAccessControl implements AccessControl, Const {

    @Override
    public boolean signIn(String username, String password) {
		boolean loginCorrect = false;

		Hashtable authEnv = new Hashtable();
		String dn = "uid=" + username + "," + BASE_DN;

		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		authEnv.put(Context.PROVIDER_URL, LDAP_URL);
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		authEnv.put(Context.SECURITY_PRINCIPAL, dn);
		authEnv.put(Context.SECURITY_CREDENTIALS, password);

		try {
			DirContext authContext = new InitialDirContext(authEnv);
			loginCorrect = true;
			System.out.println("Authentication Success!");
		} catch (AuthenticationException authEx) {
			System.out.println("Authentication failed!");
			loginCorrect = false;
		} catch (NamingException namEx) {
			System.out.println("Something went wrong!");
			namEx.printStackTrace();
			loginCorrect = false;
		}
		
		if (loginCorrect)
			CurrentUser.set(username);
		return loginCorrect;
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }

}
