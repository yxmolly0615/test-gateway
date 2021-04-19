package com.molly.repository;

import com.molly.entity.Entity;
import com.molly.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository("or")
public class InMemOrderRepository implements OrderRepository<Order,Integer>{
    private Map<Integer,Order> maps;
    public InMemOrderRepository() {
        maps=new HashMap<Integer, Order>();
        Order order1=new Order(1,"订单1",1,1,"从上海到北京",12.10);
        Order order2=new Order(2,"订单2",2,2,"从北京到福建",23.10);
        Order order3=new Order(3,"订单3",3,3,"从福建到河南",34.10);
        Order order4=new Order(4,"订单4",4,4,"从河南到上海",45.10);
        maps.put(1,order1);
        maps.put(2,order2);
        maps.put(3,order3);
        maps.put(4,order4);
    }

    @Override
    public boolean containsName(String name) {
       try{
           return this.findByName(name).size()>0;
       }catch (Exception e){
           e.printStackTrace();
       }
        return false;
    }

    @Override
    public Collection<Order> findByName(String name) throws Exception {
        Collection<Order> orders=new ArrayList<Order>();
        int noOfChars=name.length();
        maps.forEach((k,v)->{
            if(v.getName().equals(name.toLowerCase().subSequence(0,noOfChars))){
                orders.add(v);
            }
        });
        return orders;
    }

    @Override
    public Collection<Order> findByUserId(Integer id) throws Exception {
        Collection<Order> lists=new ArrayList<Order>();
        maps.forEach((k,v)->{
           if(v.getUserid()==id){
               lists.add(v);
           }
        });
        return lists;
    }

    @Override
    public void add(Order entity) {
        maps.put(entity.getId(),entity);
    }

    @Override
    public void remove(Integer id) {
        if(maps.containsKey(id)){
            maps.remove(id);
        }
    }

    @Override
    public void update(Order entity) {
        if(maps.containsKey(entity.getId())){
            maps.put(entity.getId(),entity);
        }
    }

    @Override
    public Entity get(Integer id) {
        return maps.get(id);
    }

    @Override
    public Collection<Order> getAll() {
        Collection<Order> lists=new ArrayList<Order>();
        maps.forEach((k,v)->{
            lists.add(v);
        });
        return lists;
    }
}
