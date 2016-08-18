package org.braidner.runner.domain;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Braidner
 */
public class GoogleAuthentication implements Authentication {

    private String token;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAuthenticated;
    private UserDetails principal;
    private String username;

    public GoogleAuthentication(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public GoogleAuthentication(String token, boolean isAuthenticated,
                               UserDetails principal) {
        this.token = token;
        this.isAuthenticated = isAuthenticated;
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public String getName() {
        if (principal != null)
            return principal.getUsername();
        else
            return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        isAuthenticated = b;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
