package com.maodr.framework.base.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maodr.framework.base.dao.BaseDao;

public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    protected final transient Logger log = LoggerFactory.getLogger(getClass());

    protected BaseDao<T, PK> dao;

    public void setDao(BaseDao<T, PK> dao) {
        this.dao = dao;
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public T get(PK id) {
        return dao.get(id);
    }

    public boolean exists(PK id) {
        return dao.exists(id);
    }

    public T save(T object) {
        return dao.save(object);
    }

    public void remove(T object) {
        dao.remove(object);
    }

    public void remove(PK id) {
        dao.remove(id);
    }

    @SuppressWarnings("unchecked")
    public List<T> search(String q, Class clazz) {
        if (q == null || "".equals(q.trim())) {
            return getAll();
        }

        return dao.search(q);
    }

    public void reindex() {
        dao.reindex();
    }

    public void reindexAll(boolean async) {
        dao.reindexAll(async);
    }

}
