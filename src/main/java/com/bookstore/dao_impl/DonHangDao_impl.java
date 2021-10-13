package com.bookstore.dao_impl;

import com.bookstore.dao.DonHangDao;
import com.bookstore.entity.DonHangEntity;
import com.bookstore.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class DonHangDao_impl extends GenericDao_impl<Integer, DonHangEntity> implements DonHangDao {
    @Override
    public List<DonHangEntity> Find_DHCG() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<DonHangEntity> userEntities = new ArrayList<DonHangEntity>();
        String a = "Chưa giao";
        try {
            StringBuilder sql = new StringBuilder("From DonHangEntity ");
            sql.append(" where activeDH = :value");
            sql.append(" and ma_DH not in (select id.ma_DH from GiaoHangEntity) ");
            Query query = session.createQuery(sql.toString());
            query.setParameter("value", a);
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
