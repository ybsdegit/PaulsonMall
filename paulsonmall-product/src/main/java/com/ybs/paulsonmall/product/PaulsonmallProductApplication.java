package com.ybs.paulsonmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 整合mybatis-plus
 */

@MapperScan("com.ybs.paulsonmall.product.dao")
@SpringBootApplication
public class PaulsonmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaulsonmallProductApplication.class, args);
    }

}
