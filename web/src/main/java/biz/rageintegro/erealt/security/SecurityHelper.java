package biz.rageintegro.erealt.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import biz.rageintegro.erealt.domain.AccessUser;

/**
 * Yuriy Ribitskiy (yury.ribitsky@gmail.com)
 */
public class SecurityHelper {
    public static AccessUser getCurrentUser() {
        String username = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        }
        AccessUser user = AccessUser.findAccessUserByName(username);
        if (user == null) {
            throw new IllegalArgumentException("An user object is required");
        }
        return user;
    }
}
