package com.molly.repository;

public interface Repository<Te,T> extends ReadOnlyRepository<Te,T> {
    void add(Te entity);
    void remove(T id);
    void update(Te entity);
}
