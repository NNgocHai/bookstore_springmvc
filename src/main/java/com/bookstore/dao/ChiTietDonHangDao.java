package com.bookstore.dao;

import com.bookstore.entity.ChiTietDonHangEntity;

import java.util.List;

public interface ChiTietDonHangDao extends GenericDao<Integer, ChiTietDonHangEntity>{

    List<Object[]> FindHot();

    List<ChiTietDonHangEntity> FindSpecify();
    List<ChiTietDonHangEntity> FindDetails(Integer id);


    List<Object[]> ReportDoanhThu_DauSach();

}
