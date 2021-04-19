package com.molly.config;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="BOOKINGCAR-SERVICE",fallback=OrderClientHystrix.class)
public interface OrderClient {

    @RequestMapping("/order/name")
    public String getOrderName(@RequestParam("id")Integer id);
}
