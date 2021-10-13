package com.bookstore.entity;

import javax.persistence.*;
import java.io.Serializable;

//Mac dinh class nay la entity
@Entity
//Ten bang ben database
@Table(name = "admins")
public class AdminsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ma_Admin")
    private Integer ma_Admin;

    @Column(name="taikhoan_Admin")
    private String taikhoan_Admin;

    @Column(name ="matkhau_Admin")
    private String matkhau_Admin;

    @Column(name ="hoten_Admin")
    private String hoten_Admin;

    @Column(name ="gmail_Admin")
    private String gmail_Admin;


    public Integer getMa_Admin() {
        return ma_Admin;
    }

    public void setMa_Admin(Integer ma_Admin) {
        this.ma_Admin = ma_Admin;
    }

    public String getTaikhoan_Admin() {
        return taikhoan_Admin;
    }

    public void setTaikhoan_Admin(String taikhoan_Admin) {
        this.taikhoan_Admin = taikhoan_Admin;
    }

    public String getMatkhau_Admin() {
        return matkhau_Admin;
    }

    public void setMatkhau_Admin(String matkhau_Admin) {
        this.matkhau_Admin = matkhau_Admin;
    }


    public String getGmail_Admin() {
        return gmail_Admin;
    }

    public void setGmail_Admin(String gmail_Admin) {
        this.gmail_Admin = gmail_Admin;
    }

    public String getHoten_Admin() {
        return hoten_Admin;
    }

    public void setHoten_Admin(String hoten_Admin) {
        this.hoten_Admin = hoten_Admin;
    }
}
