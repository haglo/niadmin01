
package org.app.view.authentication;

public class AccessControlFactory {
    private static final AccessControlFactory INSTANCE = new AccessControlFactory();

    private final AccessControl accessControlBasic = new BasicAccessControl();
    private final AccessControl accessControlLdap = new LdapAccessControl();

    private AccessControlFactory() {
    }

    public static AccessControlFactory getInstance() {
        return INSTANCE;
    }

    public AccessControl createAccessControlBasic() {
        return accessControlBasic;
    }
    
    public AccessControl createAccessControlLdap() {
        return accessControlLdap;
    }

}
