package com.bookstore.dao;

import com.bookstore.entity.ChiTietDonHangEntity;
import com.bookstore.entity.CuonSachEntity;

import java.util.List;

public interface ProductDao extends GenericDao<Integer, CuonSachEntity>{
    List<CuonSachEntity> FindHotDiscount();
    List<CuonSachEntity> FindByCate(int Cate);
    List<ChiTietDonHangEntity> FindHot();
    List<CuonSachEntity> Search(String TuKhoa);
}
