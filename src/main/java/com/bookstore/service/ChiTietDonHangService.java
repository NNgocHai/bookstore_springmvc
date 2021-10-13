package com.bookstore.service;

import com.bookstore.entity.ChiTietDonHangEntity;

import java.util.List;

public interface ChiTietDonHangService {
    List<ChiTietDonHangEntity> findAll();
    Integer deleteList(List<Integer> ids);
    ChiTietDonHangEntity save(ChiTietDonHangEntity T);
    ChiTietDonHangEntity update(ChiTietDonHangEntity T);
    List<Object[]> FindHot();
    ChiTietDonHangEntity findById(int var1);

    List<ChiTietDonHangEntity> findSpec();
    List<ChiTietDonHangEntity> FindDetails(Integer id);

    List<Object[]> ReportDoanhThu_DauSach();

}
