package com.bookstore.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//Mac dinh class nay la entity
@Entity
//Ten bang ben database
@Table(name = "customer")
public class CustomerEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ma_Customer")
    private Integer ma_Customer;

    @Column(name="taikhoan_Customer")
    private String taikhoan_Customer;

    @Column(name ="matkhau_Customer")
    private String matkhau_Customer;

    @Column(name ="hoten_Customer")
    private String hoten_Customer;

    @Column(name ="gmail_Customer")
    private String gmail_Customer;

    @Column(name ="sdt_Customer")
    private String sdt_Customer;

    @Column(name ="vitien")
    private Integer vitien;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<DonHangEntity> donHangEntityList;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<GioHangEntity> gioHangEntityList;

    public List<GioHangEntity> getGioHangEntityList() {
        return gioHangEntityList;
    }

    public void setGioHangEntityList(List<GioHangEntity> gioHangEntityList) {
        this.gioHangEntityList = gioHangEntityList;
    }

    public List<ReviewEntity> getReviewEntityList() {
        return reviewEntityList;
    }

    public void setReviewEntityList(List<ReviewEntity> reviewEntityList) {
        this.reviewEntityList = reviewEntityList;
    }

    public List<DonHangEntity> getDonHangEntityList() {
        return donHangEntityList;
    }

    public void setDonHangEntityList(List<DonHangEntity> donHangEntityList) {
        this.donHangEntityList = donHangEntityList;
    }

    public Integer getMa_Customer() {
        return ma_Customer;
    }

    public void setMa_Customer(Integer ma_Customer) {
        this.ma_Customer = ma_Customer;
    }

    public String getTaikhoan_Customer() {
        return taikhoan_Customer;
    }

    public void setTaikhoan_Customer(String taikhoan_Customer) {
        this.taikhoan_Customer = taikhoan_Customer;
    }

    public String getMatkhau_Customer() {
        return matkhau_Customer;
    }

    public void setMatkhau_Customer(String matkhau_Customer) {
        this.matkhau_Customer = matkhau_Customer;
    }

    public String getHoten_Customer() {
        return hoten_Customer;
    }

    public void setHoten_Customer(String hoten_Customer) {
        this.hoten_Customer = hoten_Customer;
    }

    public String getGmail_Customer() {
        return gmail_Customer;
    }

    public void setGmail_Customer(String gmail_Customer) {
        this.gmail_Customer = gmail_Customer;
    }

    public String getSdt_Customer() {
        return sdt_Customer;
    }

    public void setSdt_Customer(String sdt_Customer) {
        this.sdt_Customer = sdt_Customer;
    }

    public Integer getVitien() {
        return vitien;
    }

    public void setVitien(Integer vitien) {
        this.vitien = vitien;
    }
}
