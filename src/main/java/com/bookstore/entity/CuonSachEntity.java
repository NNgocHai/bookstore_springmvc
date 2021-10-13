package com.bookstore.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cuonsach")
public class CuonSachEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ma_CuonSach;

    @Column(name = "ma_DauSach")
    private Integer ma_DauSach;

    @Column(name ="ten_CuonSach")
    private String ten_CuonSach;

    @Column(name ="tacgia")
    private String tacgia;

    @Column(name ="soluong")
    private Integer soluong;

    @Column(name ="giabia")
    private Integer giabia;

    @Column(name = "anh_CuonSach")
    private String anh_CuonSach;

    @Column(name="discount")
    private Integer discount;

    @Column(name = "mota")
    private String mota;




    @ManyToOne
    @JoinColumn(name = "ma_DauSach", insertable=false, updatable=false)
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "cuonSachEntity", fetch = FetchType.LAZY)
    private List<ChiTietDonHangEntity> chiTietDonHangEntityList;

    @OneToMany(mappedBy = "cuonSachEntity", fetch = FetchType.LAZY)
    private List<GioHangEntity> gioHangEntityList;

    public List<GioHangEntity> getGioHangEntityList() {
        return gioHangEntityList;
    }

    public void setGioHangEntityList(List<GioHangEntity> gioHangEntityList) {
        this.gioHangEntityList = gioHangEntityList;
    }

    public List<ChiTietDonHangEntity> getChiTietDonHangEntityList() {
        return chiTietDonHangEntityList;
    }

    public void setChiTietDonHangEntityList(List<ChiTietDonHangEntity> chiTietDonHangEntityList) {
        this.chiTietDonHangEntityList = chiTietDonHangEntityList;
    }
    public Integer getGiabia() {
        return giabia;
    }

    public void setGiabia(Integer giabia) {
        this.giabia = giabia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Integer getMa_CuonSach() {
        return ma_CuonSach;
    }

    public void setMa_CuonSach(Integer ma_CuonSach) {
        this.ma_CuonSach = ma_CuonSach;
    }

    public Integer getMa_DauSach() {
        return ma_DauSach;
    }

    public void setMa_DauSach(Integer ma_DauSach) {
        this.ma_DauSach = ma_DauSach;
    }
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getTen_CuonSach() {
        return ten_CuonSach;
    }

    public void setTen_CuonSach(String ten_CuonSach) {
        this.ten_CuonSach = ten_CuonSach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public String getAnh_CuonSach() {
        return anh_CuonSach;
    }

    public void setAnh_CuonSach(String anh_CuonSach) {
        this.anh_CuonSach = anh_CuonSach;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }


}
