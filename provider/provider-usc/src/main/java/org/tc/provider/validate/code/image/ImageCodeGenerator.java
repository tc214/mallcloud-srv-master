package org.tc.provider.validate.code.image;

import com.google.code.kaptcha.Producer;
import org.tc.provider.validate.code.ValidateCodeGenerator;
import org.tc.properties.SecurityProperties;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * 图片验证码生成器
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;
    private Producer captchaProducer;

    /**
     * 生成图片验证码.
     *
     * @param request the request
     * @return the image code
     */
    @Override
    public ImageCode generate(ServletWebRequest requestx) {
        String kaptchaCode = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(kaptchaCode);
        return new ImageCode(image, kaptchaCode, securityProperties.getCode().getImage().getExpireIn());
    }

    /**
     * Sets security properties.
     *
     * @param securityProperties the security properties
     */
    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * Sets captcha producer.
     *
     * @param captchaProducer the captcha producer
     */
    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }


}
