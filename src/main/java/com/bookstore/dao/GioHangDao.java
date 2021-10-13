package com.bookstore.dao;

import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.GioHangEntity;

import java.util.List;

public interface GioHangDao extends GenericDao<Integer, GioHangEntity>{
    GioHangEntity GetOrder(CuonSachEntity C);
    List<GioHangEntity> FindByMaCustomer(int id);
    Integer DeletebyCustomer_CuonSach(int ma_Customer, int ma_CuonSach);
    Integer DeletebyCustomer(int ma_Customer);
    List<Object[]> ReportDoanhThu7Ngay();


}
