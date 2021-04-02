package com.demo.util.common.lock.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dp
 * @date 2021/4/2 10:57 上午
 */
@Data
@ConfigurationProperties(prefix = "spring.lock.redisson")
public class RedissonProperties {

    @Value("${spring.lock.redisson.timeout:${spring.redis.timeout}}")
    private int timeout = 3000;

    @Value("${spring.lock.redisson.address:redis://${spring.redis.host}:${spring.redis.port}}")
    private String address;

    @Value("${spring.lock.redisson.database:${spring.redis.database}}")
    private int database = 0;

    @Value("${spring.lock.redisson.connectionPoolSize:${spring.lock.redisson.connection-pool-size:${spring.redis.pool.maxIdle:${spring.redis.pool.max-idle}}}}")
    private int connectionPoolSize = 16;

    @Value("${spring.lock.redisson.connectionMinimumIdleSize:${spring.lock.redisson.connection-minimum-idle-size:${spring.redis.pool.minIdle:${spring.redis.pool.min-idle}}}}")
    private int connectionMinimumIdleSize = 4;
}
