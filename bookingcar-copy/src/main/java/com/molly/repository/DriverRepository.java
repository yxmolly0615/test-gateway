package com.molly.repository;

import com.molly.entity.Driver;

import java.util.Collection;

public interface DriverRepository extends Repository<Driver,Integer> {
    boolean containsName(String name);
    public Collection<Driver> findByName(String name)throws Exception;
}
