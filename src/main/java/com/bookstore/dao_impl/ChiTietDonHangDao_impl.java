package com.bookstore.dao_impl;

import com.bookstore.dao.ChiTietDonHangDao;
import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangDao_impl extends GenericDao_impl<Integer, ChiTietDonHangEntity> implements ChiTietDonHangDao {
    @Override
    public List<Object[]> FindHot() {
        List<Object[]> results =new ArrayList<Object[]>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();
            // get an cuonSachEntity object
            StringBuilder sql = new StringBuilder("select E.cuonSachEntity.ma_CuonSach,sum(E.soluong) FROM ChiTietDonHangEntity E  GROUP BY E.cuonSachEntity.ma_CuonSach ORDER BY sum(E.soluong) desc");
            Query query = session.createQuery(sql.toString());
            results = query.list();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return results;
    }

    @Override
    public List<Object[]> ReportDoanhThu_DauSach() {
        List<Object[]> results =new ArrayList<Object[]>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object
            StringBuilder sql = new StringBuilder("select E.cuonSachEntity.ma_DauSach ,E.cuonSachEntity.categoryEntity.ten_DauSach,sum(E.soluong), sum(E.soluong*E.gia) FROM ChiTietDonHangEntity E  GROUP BY E.cuonSachEntity.ma_DauSach ,E.cuonSachEntity.categoryEntity.ten_DauSach");
            Query query = session.createQuery(sql.toString());
            results = query.list();


            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return results;
    }


    @Override
    public List<ChiTietDonHangEntity> FindSpecify() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ChiTietDonHangEntity> userEntities = new ArrayList<ChiTietDonHangEntity>();
        try {
            StringBuilder sql = new StringBuilder("Select distinct id.ma_DH, ");
            sql.append(" donHangEntity.customerEntity.hoten_Customer ");
            sql.append(" from ChiTietDonHangEntity ");
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

    @Override
    public List<ChiTietDonHangEntity> FindDetails(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<ChiTietDonHangEntity> userEntities = new ArrayList<ChiTietDonHangEntity>();
        try {
            StringBuilder sql = new StringBuilder("Select id.ma_DH, donHangEntity.customerEntity.hoten_Customer, cuonSachEntity.ten_CuonSach, ");
            sql.append(" soluong, gia ");
            sql.append(" from ChiTietDonHangEntity ");
            sql.append(" where id.ma_DH = :value ");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value", id);
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
