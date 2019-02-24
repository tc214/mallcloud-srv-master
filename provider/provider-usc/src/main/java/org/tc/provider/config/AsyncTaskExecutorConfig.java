package org.tc.provider.config;

import org.tc.config.properties.MallCloudProperties;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;


@Configuration
@EnableAsync
@EnableScheduling
public class AsyncTaskExecutorConfig implements AsyncConfigurer {
    @Resource
    private MallCloudProperties mallCloudProperties;

    @Override
    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(mallCloudProperties.getTask().getCorePoolSize());
        executor.setMaxPoolSize(mallCloudProperties.getTask().getMaxPoolSize());
        executor.setQueueCapacity(mallCloudProperties.getTask().getQueueCapacity());
        executor.setKeepAliveSeconds(mallCloudProperties.getTask().getKeepAliveSeconds());
        executor.setThreadNamePrefix(mallCloudProperties.getTask().getThreadNamePrefix());
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
