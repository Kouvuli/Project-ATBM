package Entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HSBA_DV")
public class HSBA_DV {
    @Id
    @Column(name = "MAHSBA")
    private String id;

    @Column(name = "MADV")
    private String maDV;

    @Column(name = "NGAY")
    @Temporal(TemporalType.DATE)
    private Date ngay;

    @Column(name = "MAKTV")
    private String maKTV;

    @Column(name = "KETQUA")
    private String ketQua;

    public HSBA_DV(String id, String maDV, Date ngay, String maKTV, String ketQua) {
        this.id = id;
        this.maDV = maDV;
        this.ngay = ngay;
        this.maKTV = maKTV;
        this.ketQua = ketQua;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getMaKTV() {
        return maKTV;
    }

    public void setMaKTV(String maKTV) {
        this.maKTV = maKTV;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }
}
