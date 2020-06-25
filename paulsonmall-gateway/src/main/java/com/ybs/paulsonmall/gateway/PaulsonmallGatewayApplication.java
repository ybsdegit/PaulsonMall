package com.ybs.paulsonmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PaulsonmallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaulsonmallGatewayApplication.class, args);
    }

}
