package Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HSBA")
public class HSBA {
    @Id
    @Column(name = "MAHSBA")
    private String id;

    @Column(name = "MABN")
    private String maBN;

    @Column(name = "NGAY")
    @Temporal(TemporalType.DATE)
    private Date ngay;

    @Column(name = "CHANDOAN")
    private String chanDoan;

    @Column(name = "MABS")
    private String maBS;

    @Column(name = "MAKHOA")
    private String maKhoa;

    @Column(name = "MACSYT")
    private String maCSYT;

    @Column(name = "KETLUAN")
    private String ketLuan;

    public HSBA(String id, String maBN, Date ngay, String chanDoan, String maBS, String maKhoa, String maCSYT, String ketLuan) {
        this.id = id;
        this.maBN = maBN;
        this.ngay = ngay;
        this.chanDoan = chanDoan;
        this.maBS = maBS;
        this.maKhoa = maKhoa;
        this.maCSYT = maCSYT;
        this.ketLuan = ketLuan;
    }

    public HSBA(String maBN, Date ngay, String chanDoan, String maBS, String maKhoa, String maCSYT, String ketLuan) {
        this.maBN = maBN;
        this.ngay = ngay;
        this.chanDoan = chanDoan;
        this.maBS = maBS;
        this.maKhoa = maKhoa;
        this.maCSYT = maCSYT;
        this.ketLuan = ketLuan;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getChanDoan() {
        return chanDoan;
    }

    public void setChanDoan(String chanDoan) {
        this.chanDoan = chanDoan;
    }

    public String getMaBS() {
        return maBS;
    }

    public void setMaBS(String maBS) {
        this.maBS = maBS;
    }

    public String getMaCSYT() {
        return maCSYT;
    }

    public void setMaCSYT(String maCSYT) {
        this.maCSYT = maCSYT;
    }

    public String getKetLuan() {
        return ketLuan;
    }

    public void setKetLuan(String ketLuan) {
        this.ketLuan = ketLuan;
    }
}
