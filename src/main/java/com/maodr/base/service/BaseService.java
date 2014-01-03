package com.maodr.base.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, PK extends Serializable> {

    public List<T> getAll();

    public T get(PK id);

    public boolean exists(PK id);

    public T save(T object);

    public void remove(T object);

    public void remove(PK id);

    public List<T> search(String searchTerm, Class clazz);

    public void reindex();

    public void reindexAll(boolean async);
}
