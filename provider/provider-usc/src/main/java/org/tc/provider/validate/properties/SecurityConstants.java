package org.tc.provider.validate.properties;

/**
 * 安全相关的常量
 */
public interface SecurityConstants {


    /**
     * 验证码url
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/auth/code";

    /**
     * 当请求需要身份认证时，默认跳转的url
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/auth/require";
    /**
     * 登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/usc/auth/form";
    /**
     * 验证图片验证码的参数
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证邮箱验证码的参数
     */
    String DEFAULT_PARAMETER_NAME_CODE_EMAIL = "emailCode";

    /**
     * 邮箱
     */
    String DEFAULT_PARAMETER_NAME_EMAIL = "email";

    String DEFAULT_PARAMETER_NAME_OPENID = "openId";


}
