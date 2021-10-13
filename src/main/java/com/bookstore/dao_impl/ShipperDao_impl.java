package com.bookstore.dao_impl;

import com.bookstore.dao.ShipperDao;
import com.bookstore.entity.ShipperEntity;
import com.bookstore.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ShipperDao_impl extends GenericDao_impl<Integer, ShipperEntity> implements ShipperDao{
    @Override
    public boolean checkShipperLogin(String username, String password) {

        boolean exist = false;
        Object a = new Object();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try{
            StringBuilder sql = new StringBuilder("select count(*) from ShipperEntity ");
            sql.append("where taikhoan_Shipper = :value1");
            sql.append(" and matkhau_Shipper = :value2");
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

    @Override
    public List<ShipperEntity> findByUser(String user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ShipperEntity> userEntities = new ArrayList<ShipperEntity>();
        try {
            StringBuilder sql = new StringBuilder("From ShipperEntity ");
            sql.append(" where taikhoan_Shipper = :value");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value", user);
            userEntities = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return userEntities;
    }

    @Override
    public List<ShipperEntity> findID() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ShipperEntity> userEntities = new ArrayList<ShipperEntity>();
        try {
            StringBuilder sql = new StringBuilder("Select ma_Shipper From ShipperEntity ");
            Query query = session.createQuery(sql.toString());
            userEntities = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return userEntities;
    }
}
