package org.app.view.authentication;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class DatabaseAccessControl implements AccessControl {

	private static final long serialVersionUID = 1L;

	@Override
    public boolean signIn(String username, String password) {
        if (username == null || username.isEmpty())
            return false;

        CurrentUser.set(username);
        return false;
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
