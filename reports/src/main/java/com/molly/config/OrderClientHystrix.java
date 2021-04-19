package com.molly.config;

import org.springframework.stereotype.Component;

@Component
public class OrderClientHystrix implements OrderClient{

    @Override
    public String getOrderName(Integer id) {
        return "Hystrix works in Fegin when BOOKINGCAR-SERVICE is down";
    }
}
