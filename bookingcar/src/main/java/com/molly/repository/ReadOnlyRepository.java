package com.molly.repository;

import com.molly.entity.Entity;

import java.util.Collection;

public interface ReadOnlyRepository<Te,T> {
    Entity get(T id);
    Collection<Te> getAll();
}
