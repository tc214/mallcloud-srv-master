package org.tc.provider.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器
 */
public interface ValidateCodeProcessor {

    void create(ServletWebRequest request) throws Exception;

    void validate(ServletWebRequest servletWebRequest);

    void check(ServletWebRequest servletWebRequest);

}
