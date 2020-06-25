package com.ybs.paulsonmall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaulsonmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaulsonmallCouponApplication.class, args);
    }

}
