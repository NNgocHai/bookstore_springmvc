package com.bookstore.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "donhang")
public class DonHangEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_DH;

    @Column(name = "ma_Customer")
    private Integer ma_Customer;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "tongtien")
    private Integer tongtien;

    @Column(name = "ngaydat")
    private Timestamp ngaydat;

    @Column(name = "tinhtrang")
    private String activeDH;


    @ManyToOne
    @JoinColumn(name = "ma_Customer", insertable=false, updatable=false)
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "donHangEntity", fetch = FetchType.LAZY)
    private List<ChiTietDonHangEntity> chiTietDonHangEntityList;

    @OneToMany(mappedBy = "donHangEntity", fetch = FetchType.LAZY)
    private List<GiaoHangEntity> giaoHangEntityList;

    public List<GiaoHangEntity> getGiaoHangEntityList() {
        return giaoHangEntityList;
    }

    public void setGiaoHangEntityList(List<GiaoHangEntity> giaoHangEntityList) {
        this.giaoHangEntityList = giaoHangEntityList;
    }

    public List<ChiTietDonHangEntity> getChiTietDonHangEntityList() {
        return chiTietDonHangEntityList;
    }

    public void setChiTietDonHangEntityList(List<ChiTietDonHangEntity> chiTietDonHangEntityList) {
        this.chiTietDonHangEntityList = chiTietDonHangEntityList;
    }

    public Integer getMa_DH() {
        return ma_DH;
    }

    public void setMa_DH(Integer ma_DH) {
        this.ma_DH = ma_DH;
    }

    public Integer getMa_Customer() {
        return ma_Customer;
    }

    public void setMa_Customer(Integer ma_Customer) {
        this.ma_Customer = ma_Customer;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getTongtien() {
        return tongtien;
    }

    public void setTongtien(Integer tongtien) {
        this.tongtien = tongtien;
    }

    public Timestamp getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(Timestamp ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getActiveDH() {
        return activeDH;
    }

    public void setActiveDH(String activeDH) {
        this.activeDH = activeDH;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

}
