package com.blog.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface AuthorizationService {

    /**
     * Returns a list of authorities for the given user.
     *
     * @param user User for which to return authorities.
     * @throws org.springframework.security.authentication.DisabledException
     * if the user is not enabled.
     * @return A collection of authorities for the given user.
     */
    Collection<? extends GrantedAuthority> getActiveRoles(UserDetails user);
}
