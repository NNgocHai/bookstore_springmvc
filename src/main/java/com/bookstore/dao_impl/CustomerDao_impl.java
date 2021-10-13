package com.bookstore.dao_impl;

import com.bookstore.dao.CustomerDao;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao_impl extends GenericDao_impl<Integer, CustomerEntity> implements CustomerDao {
    public boolean checkCustomerLogin(String username, String password) {
        boolean exist = false;
        Object a = new Object();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("select count(*) from CustomerEntity ");
            sql.append("where taikhoan_Customer = :value1");
            sql.append(" and matkhau_Customer = :value2");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value1", username);
            query.setParameter("value2", password);
            a = query.list().get(0);
            int c = Integer.parseInt((a).toString());
            if (c == 1) {
                exist = true;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return exist;
    }

    @Override

    public List<CustomerEntity> findByUser(String user) {
        List<CustomerEntity> results = new ArrayList<CustomerEntity>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object
            StringBuilder sql = new StringBuilder("FROM CustomerEntity P WHERE P.taikhoan_Customer = :user");
            Query query = session.createQuery(sql.toString());
            query.setParameter("user", user);
            results = query.getResultList();


            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return results;
    }

    public boolean checkAddCustomer(String username, String gmail, String sdt) {
        boolean exist = true;
        Object a = new Object();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("select count(*) from CustomerEntity");
            sql.append(" where taikhoan_Customer =:value1");
            sql.append(" or sdt_Customer =:value2");
            sql.append(" or gmail_Customer =:value3");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value1", username);
            query.setParameter("value2", sdt);
            query.setParameter("value3", gmail);
            a = query.list().get(0);
            int c = Integer.parseInt((a).toString());
            if (c > 0) {
                exist = false;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return exist;
    }

    @Override
    public boolean checkUserName(String username) {
        boolean exist = true;
        Object a = new Object();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("select count(*) from CustomerEntity");
            sql.append(" where taikhoan_Customer =:value1");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value1", username);
            a = query.list().get(0);
            int c = Integer.parseInt((a).toString());
            if (c == 1) {
                exist = false;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;

        } finally {
            session.close();
        }
        return exist;
    }

    @Override
    public boolean checkGmail(String gmail) {
        boolean exist = true;
        Object a = new Object();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("select count(*) from CustomerEntity");
            sql.append(" where gmail_Customer =:value1");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value1", gmail);
            a = query.list().get(0);
            int c = Integer.parseInt((a).toString());
            if (c == 1) {
                exist = false;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return exist;
    }

    @Override
    public boolean checkSdt(String sdt) {
        boolean exist = true;
        Object a = new Object();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("select count(*) from CustomerEntity");
            sql.append(" where sdt_Customer =:value1");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value1", sdt);
            a = query.list().get(0);
            int c = Integer.parseInt((a).toString());
            if (c == 1) {
                exist = false;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return exist;
    }

    @Override
    public boolean checkActiveAccount(String key1, String key2) {
        boolean exist = false;
        Object a = new Object();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("select count(*) from CustomerEntity");
            sql.append(" where gmail_Customer =:value1");
            sql.append(" and sdt_Customer =:value2");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value1", key1);
            query.setParameter("value2",key2);
            a = query.list().get(0);
            int c = Integer.parseInt((a).toString());
            if (c == 1) {
                exist = false;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return exist;
    }
}
