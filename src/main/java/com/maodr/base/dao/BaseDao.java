package com.maodr.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 *  BaseDao接口
 *  @param <T>
 *  @param <PK>
 *  @author Administrator
 *  @created 2013年12月30日 上午6:48:45
 *  @lastModified       
 *  @history
 */
public interface BaseDao<T, PK extends Serializable> {

    public List<T> getAll();

    public List<T> getAllDistinct();

    public T get(PK id);

    public boolean exists(PK id);

    public T save(T object);

    public void remove(T object);

    public void remove(PK id);

    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

    public List<T> search(String searchTerm);

    public void reindex();

    public void reindexAll(boolean async);
}