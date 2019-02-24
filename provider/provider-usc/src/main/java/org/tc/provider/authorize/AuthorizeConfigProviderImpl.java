package org.tc.provider.authorize;

import org.tc.provider.validate.properties.SecurityConstants;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 授权配置
 */
@Component
@Order(Integer.MIN_VALUE)
public class AuthorizeConfigProviderImpl implements AuthorizeConfigProvider {

    /**
     * 不需要认证的URI
     *
     * @param config the config
     * @return the boolean
     */
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/usc" + SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                "/usc" + SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*", "/usc/debug/*",
                "/usc/auth/*/*", "/usc/auth/*", "/auth/**", "/auth/*", "/swagger-ui.html",
                "/swagger-resources/**", "/v2/api-docs",
                "/usc/role/*", "/usc/action/*", "/usc/user-role/*", "usc/role-action/*",
                "/tsc/debug/*").permitAll();
        return false;
    }

}
