package com.molly.repository;

import java.util.Collection;

public interface UserRepository<User,Integer> extends Repository<User, Integer> {
    boolean containsName(String name);
    public Collection<User> findByName(String name)throws Exception;

}
