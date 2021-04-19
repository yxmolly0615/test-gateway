package com.molly.controller;

import com.molly.entity.Entity;
import com.molly.entity.Order;
import com.molly.repository.InMemOrderRepository;

import java.util.ArrayList;
import  java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/order")
public class OrderController {
    InMemOrderRepository or;
    protected static final Logger logger= Logger.getLogger(OrderController.class.getName());

    public OrderController(InMemOrderRepository or) {
        this.or = or;
    }
    @RequestMapping("/findByUserid")//  http://localhost:2228/order/findByUserid/?userid=1
    public ResponseEntity<Collection<Order>> findByUserid(@RequestParam("userid")Integer userid){
    logger.info(String.format("订车管理微服务,根据用户id查看订单:invoked by {%s}, userId {%s}",or.getClass().getName(),userid));
      Collection<Order> collections=new ArrayList<Order>();
      try {
          collections=or.findByUserId(userid);
      }catch(Exception e){
          logger.log(Level.SEVERE,"异常:{0}",e);
          e.printStackTrace();
      }
      return collections.size()>0 ? new ResponseEntity<>(collections,HttpStatus.OK)
              :new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping("/findById")// http://localhost:2228/order/findById/?id=1
    public ResponseEntity<Entity> findById(@RequestParam("id")Integer id){
        logger.info(String.format("订车管理服务,通过id查看订单:invoked by {%s},order id{s}",or.getClass().getName(),id));
        Entity order=new Order(1,"");
        try{
            order=or.get(id);
        }catch(Exception e){
            logger.log(Level.WARNING,"异常:{0}",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return order !=null?new ResponseEntity<>(order,HttpStatus.OK):new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping("/name")// http://localhost:2228/order/name/?id=1
    public String getOrderName(@RequestParam("id") Integer id){
        Entity entity;
        try{
            entity=or.get(id);
        }catch(Exception e){
            return "exception happened";
        }
        return entity!=null?entity.getName():"no such order";
    }
}
