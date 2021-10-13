package com.bookstore.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="shipper")
public class ShipperEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_Shipper;

    @Column(name = "taikhoan_Shipper")
    private String taikhoan_Shipper;

    @Column(name = "matkhau_Shipper")
    private String matkhau_Shipper;

    @Column(name="hoten_Shipper")
    private String hoten_Shipper;

    @Column(name="gmail_Shipper")
    private String gmail_Shipper;

    @OneToMany(mappedBy = "shipperEntity", fetch = FetchType.LAZY)
    private List<GiaoHangEntity> giaoHangEntityList;

    public List<GiaoHangEntity> getGiaoHangEntityList() {
        return giaoHangEntityList;
    }

    public void setGiaoHangEntityList(List<GiaoHangEntity> giaoHangEntityList) {
        this.giaoHangEntityList = giaoHangEntityList;
    }

    public Integer getMa_Shipper() {
        return ma_Shipper;
    }

    public void setMa_Shipper(Integer ma_Shipper) {
        this.ma_Shipper = ma_Shipper;
    }

    public String getTaikhoan_Shipper() {
        return taikhoan_Shipper;
    }

    public void setTaikhoan_Shipper(String taikhoan_Shipper) {
        this.taikhoan_Shipper = taikhoan_Shipper;
    }

    public String getMatkhau_Shipper() {
        return matkhau_Shipper;
    }

    public void setMatkhau_Shipper(String matkhau_Shipper) {
        this.matkhau_Shipper = matkhau_Shipper;
    }

    public String getHoten_Shipper() {
        return hoten_Shipper;
    }

    public void setHoten_Shipper(String hoten_Shipper) {
        this.hoten_Shipper = hoten_Shipper;
    }

    public String getGmail_Shipper() {
        return gmail_Shipper;
    }

    public void setGmail_Shipper(String gmail_Shipper) {
        this.gmail_Shipper = gmail_Shipper;
    }


}
