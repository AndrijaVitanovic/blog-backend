package com.blog.service.impl;

import com.blog.service.AuthorizationService;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public Collection<? extends GrantedAuthority> getActiveRoles(UserDetails user) {
        // TODO: Think about other cases, like what happens when account is expired
        // or if credentials is expired. Think about changing role system to be more
        // realistic.
        if (!user.isEnabled()) {
            throw new DisabledException("You need to verify email to login!");
        }
        return user.getAuthorities();
    }
}
