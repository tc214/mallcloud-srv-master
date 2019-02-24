package org.tc.provider.security;

import org.tc.util.StringUtil;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * The class authentication provider.
 */
public class MyAuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyAuthenticationToken authenticationToken = (MyAuthenticationToken) authentication;

        String userName = (String) authenticationToken.getPrincipal();
        if (userName == null || StringUtil.isBlank(userName)) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        UserDetails user = userDetailsService.loadUserByUsername(userName);
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        MyAuthenticationToken authenticationResult = new MyAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MyAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


}
