package com.molly.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoadBalanceService {

    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod="TestPortFallback")//当请求路径或者其他错误了,就调用fallbackMethod的方法
    public String testPort(String microserviceName){//   http://localhost:2228/lbt/port
        System.out.println("ribbon-port:microserviceName="+microserviceName);
        String url=String.format("http://%s/lbt/port",microserviceName);
        return restTemplate.getForObject(url,String.class);
    }
    public String TestPortFallback(String microserviceName){//  http://localhost:2288/lbt/port/?microserviceName=
        System.out.println("ribbon-port:microserviceName="+microserviceName);
        String url=String.format("http://%s/lbt/port",microserviceName);
        return "Hystrix works when BOOKINGCAR-SERVICE is down";
    }
    public String TestGetOrderByUserid(String microserviceName,String userid){
        String url=String.format("http://%s/order/findByUserid?userid=%s",microserviceName,userid);
        return restTemplate.getForObject(url,String.class);
    }
}
