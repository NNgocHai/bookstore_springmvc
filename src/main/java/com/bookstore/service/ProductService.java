package com.bookstore.service;


import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.entity.CuonSachEntity;

import java.util.List;

public interface ProductService {
    List<CuonSachEntity> findAll();
    Integer deleteList(List<Integer> ids);
    CuonSachEntity save(CuonSachEntity T);
    CuonSachEntity update(CuonSachEntity T);
    CuonSachEntity findById(int var1);
    List<CuonSachEntity> FindByCate(int Cate);
    List<ChiTietDonHangEntity> FindHot();
    List<CuonSachEntity> FindHotDiscount();
    List<CuonSachEntity> Search(String TuKhoa);

}
