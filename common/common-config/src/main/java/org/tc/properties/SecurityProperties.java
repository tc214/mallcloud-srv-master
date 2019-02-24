package org.tc.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * The class Security properties.
 */
@Component
@ConfigurationProperties(prefix = "mallcloud.security")
public class SecurityProperties {

    /**
     * OAuth2认证服务器配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();

    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();


    /**
     * Gets code.
     *
     * @return the code
     */
    public ValidateCodeProperties getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }


    /**
     * Gets oauth 2.
     *
     * @return the oauth 2
     */
    public OAuth2Properties getOauth2() {
        return oauth2;
    }

    /**
     * Sets oauth 2.
     *
     * @param oauth2 the oauth 2
     */
    public void setOauth2(OAuth2Properties oauth2) {
        this.oauth2 = oauth2;
    }

}

