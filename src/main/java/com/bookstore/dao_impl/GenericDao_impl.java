package com.bookstore.dao_impl;

import com.bookstore.dao.GenericDao;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GenericDao_impl<ID extends Integer,T> implements GenericDao<ID,T> {
    @Autowired
    private SessionFactory sessionFactory;
    protected  Class<T> persistenceClass;
    public String getPersistenceClassName(){
        return persistenceClass.getSimpleName();
    }


    public List<T> findAll() {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        List<T> list = new ArrayList<T>();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try{
//            StringBuilder hql = new StringBuilder("from ");
//            hql.append(this.getPersistenceClassName());
            String getTable = this.getPersistenceClassName();
            String hql = "from " + getTable;
            Query query = session.createQuery(hql);
            list = query.list();
            transaction.commit();
            session.clear();
        }
        catch (HibernateException e){
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];

        T result  = null;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try{
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        }
        catch(HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    public T save(T entity) {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try{
            session. persist(entity);
            transaction.commit();
        }
        catch(HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return entity;
    }


    public T findById(int var1) {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        T result = null;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try{
            result = (T) session.get(persistenceClass, var1);
            if(result == null){
                throw new ObjectNotFoundException("Not found "+ var1, null);
            }
            transaction.commit();
        }
        catch(HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection) {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        List<T> list = new ArrayList<T>();
        Object totalItem = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try
        {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName());
            if(property != null && value != null) {
                sql1.append(" where ").append(property).append("= :value");
            }
            if(sortExpression != null && sortDirection != null){
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" " + (sortDirection.equals("ASC")?"ASC":"DESC"));
            }
            Query query1 = session.createQuery(sql1.toString());
            query1.setParameter("value", value);
            list = query1.list();

            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(getPersistenceClassName());
            if(property != null && value != null) {
                sql2.append(" where ").append(property).append("= :value");
                Query query2 = session.createQuery(sql2.toString());
                query2.setParameter("value", value);
                totalItem = query2.list().get(0);
            }
            //totalItem = query2.list().get(0);

            transaction.commit();
        }
        catch (HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    public Integer deleteList(List<ID> ids) {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Integer count = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try{
            for(ID item: ids){
                T t = (T) session.get(persistenceClass, item);
                session.delete(t);
                count++;
            }
            transaction.commit();
        }
        catch (HibernateException e)
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return count;
    }

}
