package org.tc.provider.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证码处理器管理
 */
@Component
public class ValidateCodeProcessorManager {

    private final Map<String, ValidateCodeProcessor> validateCodeProcessors;


    @Autowired
    public ValidateCodeProcessorManager(Map<String, ValidateCodeProcessor> validateCodeProcessors) {
        this.validateCodeProcessors = validateCodeProcessors;
    }

    ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            System.out.println("====findValidateCodeProcessor===null=");
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
