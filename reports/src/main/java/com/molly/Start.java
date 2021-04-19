package com.molly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableCircuitBreaker//hystrix启动注解
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class,args);
    }
}
