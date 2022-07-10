package Controllers;

import Entities.BenhNhan;
import Services.BenhNhanService;
import Services.NhanVienService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientDetailController implements Initializable {
    @FXML
    private Text cmnd;

    @FXML
    private Text diUngThuoc;

    @FXML
    private Text diaChi;

    @FXML
    private Text maBN;

    @FXML
    private Text maCSYT;

    @FXML
    private Text ngaySinh;

    @FXML
    private Text ten;

    @FXML
    private Text tieuSuBenh;

    @FXML
    private Text tieuSuBenhGD;

    BenhNhan benhNhan;
    @FXML
    void reloadHandler(ActionEvent event) {
        maBN.setText(benhNhan.getId());
        ten.setText(benhNhan.getTen());
        maCSYT.setText(benhNhan.getCsytId());
        cmnd.setText(benhNhan.getCmnd());
        if(benhNhan.getNgaySinh()!=null){
            ngaySinh.setText(benhNhan.getNgaySinh().toString());
        }
        if(benhNhan.getSoNha()!=null&&benhNhan.getTenDuong()!=null&&benhNhan.getTinhTP()!=null&&benhNhan.getQuanHuyen()!=null){

            diaChi.setText(benhNhan.getSoNha()+" "+benhNhan.getTenDuong()+", "+benhNhan.getQuanHuyen()+", "+benhNhan.getTinhTP());
        }
        diUngThuoc.setText(benhNhan.getDiUngThuoc());
        tieuSuBenh.setText(benhNhan.getTieuSuBenh());
        tieuSuBenhGD.setText(benhNhan.getTieuSuBenhGD());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maBN.setText(benhNhan.getId());
        ten.setText(benhNhan.getTen());
        maCSYT.setText(benhNhan.getCsytId());
        cmnd.setText(benhNhan.getCmnd());
        if(benhNhan.getNgaySinh()!=null){
            ngaySinh.setText(benhNhan.getNgaySinh().toString());
        }
        if(benhNhan.getSoNha()!=null&&benhNhan.getTenDuong()!=null&&benhNhan.getTinhTP()!=null&&benhNhan.getQuanHuyen()!=null){

            diaChi.setText(benhNhan.getSoNha()+" "+benhNhan.getTenDuong()+", "+benhNhan.getQuanHuyen()+", "+benhNhan.getTinhTP());
        }
        diUngThuoc.setText(benhNhan.getDiUngThuoc());
        tieuSuBenh.setText(benhNhan.getTieuSuBenh());
        tieuSuBenhGD.setText(benhNhan.getTieuSuBenhGD());
    }

    public void setValue(BenhNhan benhNhan) throws SQLException {
        this.benhNhan=benhNhan;


    }
}
