package biz.rageintegro.erealt.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.HashSet;

import biz.rageintegro.erealt.domain.AccessUser;

/**
 * Yuriy Ribitskiy (yury.ribitsky@gmail.com)
 */
public class DbUserDetailsService implements UserDetailsService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        if (username.equals("admin")) {
            return getAdminDetails();
        } else {
            return getUserDetails(username);
        }
    }

    private UserDetails getAdminDetails() {
        String password = "ganjahh1";
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        return new User("admin", password, true, true, true, true, authorities);
    }

    private UserDetails getUserDetails(String username) {
        AccessUser user = AccessUser.findAccessUserByName(username);
        if (user != null) {
            Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
            return new User(user.getName(), user.getPassword(), true, true, true, true, authorities);
        } else {
            return null;
        }
    }
}
