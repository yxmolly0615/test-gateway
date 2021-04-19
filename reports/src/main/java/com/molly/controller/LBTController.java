package com.molly.controller;

import com.molly.config.LBTClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lbt")
public class LBTController {
    @Autowired
    LBTClient lc;

    @RequestMapping("/port")//  http://localhost:2266/lbt/port
    public String fegin_getPort(){
        System.out.println("report--lpt-port");
        return lc.findServicePort();
    }
}
