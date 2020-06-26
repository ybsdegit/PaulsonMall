package com.ybs.paulsonmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 整合mybatis-plus
 */

@MapperScan("com.ybs.paulsonmall.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class PaulsonmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaulsonmallProductApplication.class, args);
    }

}
