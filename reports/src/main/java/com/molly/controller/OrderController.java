package com.molly.controller;

import com.molly.config.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderClient oc;
    @RequestMapping("/order")// http://localhost:2266/order/order/?id=1
    public String fegin_getOrdername(@RequestParam("id") Integer id){
        return oc.getOrderName(id);
    }
}
