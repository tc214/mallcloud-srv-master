package org.tc.properties;

import lombok.Data;

/**
 * 验证码配置
 */
@Data
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();

}
