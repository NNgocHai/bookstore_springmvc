package com.bookstore.service;

import com.bookstore.entity.CuonSachEntity;
import com.bookstore.entity.GioHangEntity;

import java.util.List;

public interface GioHangService {
    List<GioHangEntity> findAll();
    Integer deleteList(List<Integer> ids);
    GioHangEntity save(GioHangEntity T);
    GioHangEntity update(GioHangEntity T);
    GioHangEntity findById(int var1);
    GioHangEntity GetOrder(CuonSachEntity C);
    List<GioHangEntity> FindByMaCustomer(int id);
    Integer DeletebyCustomer_CuonSach(int ma_Customer, int ma_CuonSach);
    Integer DeletebyCustomer(int ma_Customer);

    List<Object[]> ReportDoanhThu7Ngay();

}
