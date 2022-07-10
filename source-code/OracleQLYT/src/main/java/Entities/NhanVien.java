package Entities;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "NHANVIEN")
public class NhanVien {
    @Id
    @Column(name = "MANV")
    private String id;

    @Column(name = "HOTEN")
    private String hoTen;

    @Column(name = "PHAI")
    private String phai;

    @Column(name = "NGAYSINH")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;

    @Column(name = "CMND")
    private String cmnd;

    @Column(name = "QUEQUAN")
    private String queQuan;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "CSYT")
    private String csyt;


    @Column(name = "VAITRO")
    private String vaiTro;

    @Column(name = "CHUYENKHOA")
    private String chuyenKhoa;


    public NhanVien(String id, String hoTen, String phai, Date ngaySinh, String cmnd, String queQuan, String sdt, String csyt, String vaiTro, String chuyenKhoa) {
        this.id = id;
        this.hoTen = hoTen;
        this.phai = phai;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
        this.queQuan = queQuan;
        this.sdt = sdt;
        this.csyt = csyt;
        this.vaiTro = vaiTro;
        this.chuyenKhoa = chuyenKhoa;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCsyt() {
        return csyt;
    }

    public void setCsyt(String csyt) {
        this.csyt = csyt;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(String chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }
}
