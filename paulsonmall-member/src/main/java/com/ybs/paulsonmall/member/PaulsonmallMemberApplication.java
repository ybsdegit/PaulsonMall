package com.ybs.paulsonmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PaulsonmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaulsonmallMemberApplication.class, args);
    }

}
