package org.tc.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片验证码
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProperties extends BaseCodeProperties {

    /**
     * Instantiates a new Image code properties.
     */
    ImageCodeProperties() {
        super.setLength(4);
    }

}
