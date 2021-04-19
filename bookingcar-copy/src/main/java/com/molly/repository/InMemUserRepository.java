package com.molly.repository;

import com.molly.entity.Entity;
import com.molly.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemUserRepository implements UserRepository<User,Integer> {
    Map<Integer, User> maps;

    public InMemUserRepository() {
        maps=new HashMap<Integer,User>();
        User user1=new User(1,"张三","上海","2121");
        User user2=new User(1,"李四","北京","2121");
        User user3=new User(1,"王五","宁德","2121");
        User user4=new User(1,"赵六","南阳","2121");
        maps.put(user1.getId(),user1);
        maps.put(user2.getId(),user2);
        maps.put(user3.getId(),user3);
        maps.put(user4.getId(),user4);
    }

    @Override
    public boolean containsName(String name) {
        try {
            return findByName(name).size()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<User> findByName(String name) throws Exception {
        Collection<User> lists=new ArrayList<User>();
        maps.forEach((k,v)->{
            if(v.getName().equals(name.toLowerCase().subSequence(0,name.length()))){
                lists.add(v);
            }
        });
        return lists;
    }

    @Override
    public void add(User entity) {
        maps.put(entity.getId(),entity);
    }

    @Override
    public void remove(Integer id) {
        if(maps.containsKey(id)){
            maps.remove(id);
        }
    }

    @Override
    public void update(User entity) {
        if(maps.containsKey(entity.getId())){
            maps.put(entity.getId(),entity);
        }
    }

    @Override
    public Entity get(Integer id) {
        if(maps.containsKey(id)){
            return maps.get(id);
        }
        return null;
    }


    @Override
    public Collection<User> getAll() {
        Collection<User> lists=new ArrayList<User>();
        maps.forEach((k,v)->{
           lists.add(v);
        });
        return lists;
    }
}
