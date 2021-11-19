package com.bookstore.dao_impl;

import com.bookstore.dao.GiaoHangDao;
import com.bookstore.entity.GiaoHangEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GiaoHangDao_impl extends GenericDao_impl<Integer, GiaoHangEntity> implements GiaoHangDao {

    @Autowired
    SessionFactory factory;
    @Override
    public List<GiaoHangEntity> findID(Integer id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<GiaoHangEntity> donHangEntities = new ArrayList<GiaoHangEntity>();
        String a = "Chưa giao";
        try {
            StringBuilder sql = new StringBuilder("Select donHangEntity.ma_DH, donHangEntity.customerEntity.hoten_Customer, ");
            sql.append("donHangEntity.diachi, donHangEntity.sdt, donHangEntity.tongtien, donHangEntity.activeDH");
            sql.append(" From GiaoHangEntity ");
            sql.append(" where shipperEntity.ma_Shipper = :value");
            sql.append(" and donHangEntity.activeDH = :value1");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value", id);
            query.setParameter("value1", a);
            donHangEntities = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return donHangEntities;
    }

    @Override
    public List<GiaoHangEntity> findID_DG(Integer id) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<GiaoHangEntity> donHangEntities = new ArrayList<GiaoHangEntity>();
        String a = "Đã giao";
        try {
            StringBuilder sql = new StringBuilder("Select donHangEntity.ma_DH, donHangEntity.customerEntity.hoten_Customer, donHangEntity.diachi, donHangEntity.sdt, ");
            sql.append(" donHangEntity.tongtien, donHangEntity.activeDH");
            sql.append(" From GiaoHangEntity ");
            sql.append(" where shipperEntity.ma_Shipper = :value");
            sql.append(" and donHangEntity.activeDH = :value1");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value", id);
            query.setParameter("value1", a);
            donHangEntities = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return donHangEntities;
    }

    @Override
    public List<GiaoHangEntity> findTT_GH() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<GiaoHangEntity> userEntities = new ArrayList<GiaoHangEntity>();
        String a = "Chưa giao";
        try {
            StringBuilder sql = new StringBuilder("Select donHangEntity.ma_DH, shipperEntity.ma_Shipper, ");
            sql.append("shipperEntity.hoten_Shipper, donHangEntity.activeDH");
            sql.append(" From GiaoHangEntity ");
            //sql.append(" where g.ma_DH = d.ma_DH ");
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
