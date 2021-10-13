package com.bookstore.dao_impl;

import com.bookstore.dao.AdminDao;
import com.bookstore.entity.AdminsEntity;
import com.bookstore.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class AdminDao_impl extends GenericDao_impl<Integer, AdminsEntity> implements AdminDao {

    public  boolean checkAdminLogin(String username, String password) {
        boolean exist = false;
        Object a = new Object();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try{
            StringBuilder sql = new StringBuilder("select count(*) from AdminsEntity ");
            sql.append("where taikhoan_Admin = :value1");
            sql.append(" and matkhau_Admin = :value2");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value1", username);
            query.setParameter("value2", password);
            a = query.list().get(0);
            int c =  Integer.parseInt((a).toString());
            if(c == 1)
            {
                exist = true;
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
        return exist;
    }

    public boolean checkDelete(String username, Integer id) {
        boolean exist = false;
        Object a = new Object();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try{
            StringBuilder sql = new StringBuilder("select count(*) from AdminsEntity ");
            sql.append("where taikhoan_Admin = :value1");
            sql.append(" and ma_Admin = :value2");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value1", username);
            query.setParameter("value2", id);
            a = query.list().get(0);
            int c =  Integer.parseInt((a).toString());
            if(c == 1)
            {
                exist = true;
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
        return exist;
    }

}
