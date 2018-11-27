
package org.app.view.authentication;

public class AccessControlFactory {
    private static final AccessControlFactory INSTANCE = new AccessControlFactory();

    private final AccessControl accessControlDatabase = new DatabaseAccessControl();
    private final AccessControl accessControlLdap = new LdapAccessControl();

    private AccessControlFactory() {
    }

    public static AccessControlFactory getInstance() {
        return INSTANCE;
    }

    public AccessControl createAccessControlDatabase() {
        return accessControlDatabase;
    }
    
    public AccessControl createAccessControlLdap() {
        return accessControlLdap;
    }

}
