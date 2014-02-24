package com.maodr.framework.base.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectRetrievalFailureException;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

    protected final transient Logger log = LoggerFactory.getLogger(getClass());

    private Class<T> persistentClass;

    private SessionFactory sessionFactory;

    public BaseDaoImpl(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public BaseDaoImpl(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session sess = getSession();
        return sess.createCriteria(persistentClass).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    public T get(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    public <M> M get(Class<M> clazz, PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(clazz);
        M entity = (M) byId.load(id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        return entity != null;
    }

    @SuppressWarnings("unchecked")
    public T save(T object) {
        Session sess = getSession();
        return (T) sess.merge(object);
    }

    @SuppressWarnings("unchecked")
    public <M> M save(Class<M> clazz, M object) {
        Session sess = getSession();
        return (M) sess.merge(object);
    }

    @SuppressWarnings("unchecked")
    public List<T> saveBatch(List<T> list) {
        int i = 0;
        List<T> resultList = new ArrayList<T>();
        Session session = getSession();
        for (T object : list) {
            i++;
            T result = (T) session.merge(object);
            resultList.add(result);
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
        return resultList;
    }

    public <M> List<M> saveBatch(Class<M> clazz, List<M> list) {
        int i = 0;
        List<M> resultList = new ArrayList<M>();
        Session session = getSession();
        for (M object : list) {
            i++;
            M result = (M) session.merge(object);
            resultList.add(result);
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
        return resultList;
    }

    public void remove(T object) {
        Session sess = getSession();
        sess.delete(object);
    }

    public void remove(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        sess.delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Session sess = getSession();
        Query namedQuery = sess.getNamedQuery(queryName);

        for (String s : queryParams.keySet()) {
            namedQuery.setParameter(s, queryParams.get(s));
        }

        return namedQuery.list();
    }

    public List<T> search(String searchTerm) {
        // TODO Auto-generated method stub
        return null;
    }

    public void reindex() {
        // TODO Auto-generated method stub
    }

    public void reindexAll(boolean async) {
        // TODO Auto-generated method stub

    }

}
