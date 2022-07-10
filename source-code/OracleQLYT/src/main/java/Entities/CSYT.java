package Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CSYT")
public class CSYT {
    @Id
    @Column(name = "MACSYT")
    private String id;

    @Column(name = "TENCSYT")
    private String tenCSYT;

    @Column(name = "DCCSYT")
    private String dcCSYT;

    @Column(name = "SDTCSYT")
    private String sdtCSYT;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenCSYT() {
        return tenCSYT;
    }

    public void setTenCSYT(String tenCSYT) {
        this.tenCSYT = tenCSYT;
    }

    public String getDcCSYT() {
        return dcCSYT;
    }

    public void setDcCSYT(String dcCSYT) {
        this.dcCSYT = dcCSYT;
    }

    public String getSdtCSYT() {
        return sdtCSYT;
    }

    public void setSdtCSYT(String sdtCSYT) {
        this.sdtCSYT = sdtCSYT;
    }
}
