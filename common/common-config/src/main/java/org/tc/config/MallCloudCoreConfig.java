package org.tc.config;

import org.tc.config.properties.MallCloudProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = MallCloudProperties.class)
public class MallCloudCoreConfig {
}
