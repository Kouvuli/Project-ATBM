package Entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BENHNHAN")
public class BenhNhan {
    @Id
    @Column(name = "MABN")
    private String id;

    @Column(name = "MACSYT")
    private String csytId;

    @Column(name = "TENBN")
    private String ten;

    @Column(name = "CMND")
    private String cmnd;


    @Column(name = "NGAYSINH")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;


    @Column(name = "SONHA")
    private String soNha;

    @Column(name = "TENDUONG")
    private String tenDuong;

    @Column(name = "QUANHUYEN")
    private String quanHuyen;

    @Column(name = "TINHTP")
    private String tinhTP;

    @Column(name = "TIEUSUBENH")
    private String tieuSuBenh;

    @Column(name = "TIEUSUBENHGD")
    private String tieuSuBenhGD;

    @Column(name = "DIUNGTHUOC")
    private String diUngThuoc;

    public BenhNhan(String id, String csytId,  String ten, String cmnd, Date ngaySinh, String soNha, String tenDuong, String quanHuyen, String tinhTP, String tieuSuBenh, String tieuSuBenhGD, String diUngThuoc) {
        this.id = id;
        this.csytId = csytId;

        this.ten = ten;
        this.cmnd = cmnd;
        this.ngaySinh = ngaySinh;
        this.soNha = soNha;
        this.tenDuong = tenDuong;
        this.quanHuyen = quanHuyen;
        this.tinhTP = tinhTP;
        this.tieuSuBenh = tieuSuBenh;
        this.tieuSuBenhGD = tieuSuBenhGD;
        this.diUngThuoc = diUngThuoc;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCsytId() {
        return csytId;
    }

    public void setCsytId(String csytId) {
        this.csytId = csytId;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getTenDuong() {
        return tenDuong;
    }

    public void setTenDuong(String tenDuong) {
        this.tenDuong = tenDuong;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getTinhTP() {
        return tinhTP;
    }

    public void setTinhTP(String tinhTP) {
        this.tinhTP = tinhTP;
    }

    public String getTieuSuBenh() {
        return tieuSuBenh;
    }

    public void setTieuSuBenh(String tieuSuBenh) {
        this.tieuSuBenh = tieuSuBenh;
    }

    public String getTieuSuBenhGD() {
        return tieuSuBenhGD;
    }

    public void setTieuSuBenhGD(String tieuSuBenhGD) {
        this.tieuSuBenhGD = tieuSuBenhGD;
    }

    public String getDiUngThuoc() {
        return diUngThuoc;
    }

    public void setDiUngThuoc(String diUngThuoc) {
        this.diUngThuoc = diUngThuoc;
    }
}
