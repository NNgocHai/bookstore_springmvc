package com.bookstore.dao_impl;

import com.bookstore.dao.ProductDao;
import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.entity.CuonSachEntity;
import com.bookstore.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class ProductDao_impl extends GenericDao_impl<Integer, CuonSachEntity> implements ProductDao {

    @Override
    public List<CuonSachEntity> FindHotDiscount() {
        List<CuonSachEntity> results =new ArrayList<CuonSachEntity>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object
            StringBuilder sql = new StringBuilder("FROM CuonSachEntity P order by P.discount DESC");
            Query query = session.createQuery(sql.toString());
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

    public List<CuonSachEntity> FindByCate(int Cate) {
        List<CuonSachEntity> results =new ArrayList<CuonSachEntity>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object
            StringBuilder sql = new StringBuilder("FROM CuonSachEntity P WHERE P.ma_DauSach = :Cate");
            Query query = session.createQuery(sql.toString());
            query.setParameter("Cate", Cate);
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
    public List<ChiTietDonHangEntity> FindHot() {
        List<ChiTietDonHangEntity> results =new ArrayList<ChiTietDonHangEntity>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object
            StringBuilder sql = new StringBuilder("select soluong,cuonSachEntity   FROM ChiTietDonHangEntity  ORDER BY soluong DESC");
            Query query = session.createQuery(sql.toString());
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
    public List<CuonSachEntity> Search(String TuKhoa) {
        List<CuonSachEntity> results =new ArrayList<CuonSachEntity>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try  {
            // start a transaction
            transaction = session.beginTransaction();

            // get an cuonSachEntity object
            StringBuilder sql = new StringBuilder("from CuonSachEntity a where a.ten_CuonSach like :TuKhoa or a.categoryEntity.ten_DauSach like :TuKhoa");
            Query query = session.createQuery(sql.toString());
            query.setParameter("TuKhoa", "%"+TuKhoa+"%");
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
