package org.tc.provider.controller;


import org.tc.provider.validate.SecurityResult;
import org.tc.provider.validate.code.ValidateCodeException;
import org.tc.provider.validate.code.ValidateCodeProcessorManager;
import org.tc.provider.validate.properties.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = "/usc")
public class ValidateCodeController {

    @Resource
    ValidateCodeProcessorManager validateCodeProcessorManager;


    @PostMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createValidateCode(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable String type) throws Exception {
        log.info("type=" + type);
        System.out.println("====createValidateCode====");
        validateCodeProcessorManager.findValidateCodeProcessor(type).create(
                new ServletWebRequest(request, response));
    }

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public Object checkCode(HttpServletRequest request, HttpServletResponse response,
                            @PathVariable String type) {
        SecurityResult result = new SecurityResult(SecurityResult.SUCCESS_CODE, "校验成功", true);
        try {
            validateCodeProcessorManager.findValidateCodeProcessor(type).check(new ServletWebRequest(request, response));
        } catch (ValidateCodeException e) {
            result = SecurityResult.error(e.getMessage(), false);
        } catch (Exception e) {
            log.error("getAccessToken={}", e.getMessage(), e);
            result = SecurityResult.error("验证码错误", false);
        }
        return result;
    }

}
