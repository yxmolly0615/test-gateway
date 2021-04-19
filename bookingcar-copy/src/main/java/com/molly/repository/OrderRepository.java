package com.molly.repository;

import java.util.Collection;

public interface OrderRepository<Order,Integer> extends Repository<Order,Integer> {
    boolean containsName(String name);
    public Collection<Order> findByName(String name)throws Exception;
    public Collection<Order> findByUserId(Integer id)throws Exception;

}
