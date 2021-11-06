package com.bookstore.dao_impl;

import com.bookstore.dao.GioHangDao;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.GioHangEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GioHangDao_impl extends GenericDao_impl<Integer, GioHangEntity> implements GioHangDao {
    @Autowired
    SessionFactory factory;
    @Override
    public GioHangEntity GetOrder(CuonSachEntity C) {
        GioHangEntity gioHangEntity = new GioHangEntity();
        gioHangEntity.setCuonSachEntity(C);
        return gioHangEntity;
    }

    @Override
    public List<GioHangEntity> FindByMaCustomer(int id) {
        List<GioHangEntity> results =new ArrayList<GioHangEntity>();
        Transaction transaction = null;
        Session session = factory.openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object
            StringBuilder sql = new StringBuilder("FROM GioHangEntity P WHERE P.customerEntity.ma_Customer = :id");
            Query query = session.createQuery(sql.toString());
            query.setParameter("id", id);
            results = query.getResultList();


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
    public Integer DeletebyCustomer_CuonSach(int ma_Customer, int ma_CuonSach) {
        int result =0;
        Transaction transaction = null;
        Session session = factory.openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object delete from Vote where uid= :uid AND pid= :pid
            StringBuilder sql = new StringBuilder("delete from GioHangEntity P WHERE P.customerEntity.ma_Customer = :ma_Customer AND P.cuonSachEntity.ma_CuonSach = :ma_CuonSach");
            Query query = session.createQuery(sql.toString());
            query.setParameter("ma_Customer", ma_Customer);
            query.setParameter("ma_CuonSach", ma_CuonSach);
            // commit transaction
            query.executeUpdate();
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
        return result;
    }

    @Override
    public Integer DeletebyCustomer(int ma_Customer) {
        int result =0;
        Transaction transaction = null;
        Session session = factory.openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object delete from Vote where uid= :uid AND pid= :pid
            StringBuilder sql = new StringBuilder("delete from GioHangEntity P WHERE P.customerEntity.ma_Customer = :ma_Customer");
            Query query = session.createQuery(sql.toString());
            query.setParameter("ma_Customer", ma_Customer);

            // commit transaction
            query.executeUpdate();
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
        return result;
    }

    @Override
    public List<Object[]> ReportDoanhThu7Ngay() {
        LocalDateTime now = LocalDateTime.now();
        List<Object[]> results =new ArrayList<Object[]>();
        Transaction transaction = null;
        Session session = factory.openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object
            StringBuilder sql = new StringBuilder("select DATE(E.ngaydat),sum(E.tongtien) FROM DonHangEntity E WHERE E.ngaydat > :day GROUP BY  DATE(E.ngaydat) ");
            Query query = session.createQuery(sql.toString());
            query.setParameter("day", Timestamp.valueOf((now.plusDays(-7))));
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
}
