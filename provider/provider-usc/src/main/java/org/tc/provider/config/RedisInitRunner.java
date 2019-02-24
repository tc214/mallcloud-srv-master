package org.tc.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * The class Redis init runner.
 */
@Component
@Order(value = 1)
@Slf4j
public class RedisInitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info(">>>>>>>>>>>>>>>服务开始启动，执行加载数据等操作 11111111 <<<<<<<<<<<<<");
    }

}