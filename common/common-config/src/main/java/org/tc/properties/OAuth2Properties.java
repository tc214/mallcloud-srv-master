package org.tc.properties;

import lombok.Data;

/**
 * The class O auth 2 properties.
 */
@Data
public class OAuth2Properties {

    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "mallcloud";
    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

}
