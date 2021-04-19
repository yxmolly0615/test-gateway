package com.molly.config;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "BOOKINGCAR-SERVICE")
public interface LBTClient {
    @RequestMapping("/lbt/port")
    public String findServicePort();
}
