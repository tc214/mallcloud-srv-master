package org.tc.config.properties;


import org.tc.base.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
public class MallCloudProperties {

    private AsyncTaskProperties task = new AsyncTaskProperties();
}
