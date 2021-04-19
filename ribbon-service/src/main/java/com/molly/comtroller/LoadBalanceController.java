package com.molly.comtroller;

import com.molly.service.LoadBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/lbt")
public class LoadBalanceController {
    protected static final Logger logger=Logger.getLogger(LoadBalanceController.class.getName());
    @Autowired
    LoadBalanceService lbs;

    @RequestMapping("/port")
    public String TestPort(@RequestParam("microserviceName") String microserviceName){
        return lbs.testPort(microserviceName);
    }
}
