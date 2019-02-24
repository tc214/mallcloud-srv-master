package org.tc.provider.validate.code;

import com.google.code.kaptcha.Producer;
import org.tc.properties.SecurityProperties;
import org.tc.provider.validate.code.image.ImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private Producer captchaProducer;

    /**
     * 图片验证码图片生成器
     *
     * @return validate code generator
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        codeGenerator.setCaptchaProducer(captchaProducer);
        return codeGenerator;
    }


}
