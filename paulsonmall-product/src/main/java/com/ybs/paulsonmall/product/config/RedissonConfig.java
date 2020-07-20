package com.ybs.paulsonmall.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @ClassName: RedissonConfig
 * @Author Paulson
 * @Date 2020/7/20
 * @Description:
 */

@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.2.143:6379").setDatabase(1);
        return Redisson.create(config);
    }

}
