package com.xdavide9.turbobuy.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.xdavide9.turbobuy.security.ApplicationPermission.*;

// only an admin shall see and modify details about other users
// a simple user will just read and post sales

// a set of permissions is attached to each role defining what it allows to do
// the grantedAuthorities are the combination of these permissions and the role itself

public enum ApplicationRole {
    USER(Set.of(SALES_READ, SALES_WRITE)),
    ADMIN(Set.of(SALES_READ, SALES_WRITE, USER_READ, USER_WRITE));

    private final Set<ApplicationPermission> permissions;

    ApplicationRole(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionString()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
