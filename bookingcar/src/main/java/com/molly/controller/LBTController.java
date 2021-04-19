package com.molly.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * 负载均衡接口
 */
@RestController//    http://localhost:2228/v1/lbtest/port
@RequestMapping("/lbt")
public class LBTController {
    protected static final Logger logger=Logger.getLogger(LBTController.class.getName());
    @Value("${server.port}")
    String port;
    @RequestMapping("/port")
    public String findServicePort(){
        String prompt = String.format("Ribbon (load balancer) service test, port = {%s} ", port);
                logger.info(prompt);
        return prompt;
    }
}
