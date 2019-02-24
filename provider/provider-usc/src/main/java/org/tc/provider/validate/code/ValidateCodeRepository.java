package org.tc.provider.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码存取器
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     */
    void saveToRedis(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     */
    void remove(ServletWebRequest request, ValidateCodeType codeType);

}
