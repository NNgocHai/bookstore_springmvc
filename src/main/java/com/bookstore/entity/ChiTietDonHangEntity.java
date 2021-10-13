package com.bookstore.entity;



import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "chitietdonhang")
public class ChiTietDonHangEntity implements Serializable {
    @EmbeddedId
    private ChiTietDonHangIDKey id;

    @Column(name ="soluong")
    private Integer soluong;

    @Column(name="gia")
    private Integer gia;

    @ManyToOne
    @JoinColumn(name = "ma_DH", insertable=false, updatable=false)
    private DonHangEntity donHangEntity;

    @ManyToOne
    @JoinColumn(name = "ma_CuonSach", insertable=false, updatable=false)
    private CuonSachEntity cuonSachEntity;

    public DonHangEntity getDonHangEntity() {
        return donHangEntity;
    }

    public void setDonHangEntity(DonHangEntity donHangEntity) {
        this.donHangEntity = donHangEntity;
    }

    public CuonSachEntity getCuonSachEntity() {
        return cuonSachEntity;
    }

    public void setCuonSachEntity(CuonSachEntity cuonSachEntity) {
        this.cuonSachEntity = cuonSachEntity;
    }

    public ChiTietDonHangIDKey getId() {
        return id;
    }

    public void setId(ChiTietDonHangIDKey id) {
        this.id = id;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

}
