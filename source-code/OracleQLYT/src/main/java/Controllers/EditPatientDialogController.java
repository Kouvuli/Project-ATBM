package Controllers;

import Entities.BenhNhan;
import Entities.NhanVien;
import Services.BenhNhanService;
import Services.NhanVienService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditPatientDialogController implements Initializable {
    @FXML
    private TextField diUngThuoc;

    @FXML
    private TextField hoTen;

    @FXML
    private DatePicker ngaySinh;

    @FXML
    private TextField cmnd;
    @FXML
    private TextField maCSYT;

    @FXML
    private TextField quan;

    @FXML
    private TextField soNha;

    @FXML
    private TextField tenDuong;

    @FXML
    private TextField tieuSuBenh;

    @FXML
    private TextField tieuSuBenhGD;

    @FXML
    private TextField tinhTP;

    private BenhNhan benhNhan;
    @FXML
    void cancelHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(benhNhan!=null){
            diUngThuoc.setText(benhNhan.getDiUngThuoc());
            hoTen.setText(benhNhan.getTen());
            if(benhNhan.getNgaySinh()!=null){

                ngaySinh.setValue(Instant.ofEpochMilli(benhNhan.getNgaySinh().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
            }
            maCSYT.setText(benhNhan.getCsytId());
            cmnd.setText(benhNhan.getCmnd());
            quan.setText(benhNhan.getQuanHuyen());
            soNha.setText(benhNhan.getSoNha());
            tenDuong.setText(benhNhan.getTenDuong());
            tinhTP.setText(benhNhan.getTinhTP());
            tieuSuBenhGD.setText(benhNhan.getTieuSuBenhGD());
            tieuSuBenh.setText(benhNhan.getTieuSuBenh());
        }
    }

    @FXML
    void confirmHandler(ActionEvent event) throws SQLException {
        if(!isInputValid()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Dữ liệu nhập không hợp lệ!");
            alert.showAndWait();
        }else{
            BenhNhanService service = new BenhNhanService();


            BenhNhan newBenhNhan=new BenhNhan(benhNhan.getId(),maCSYT.getText().trim(),hoTen.getText().trim(),cmnd.getText().trim(), Date.from(ngaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),soNha.getText().trim(),tenDuong.getText().trim(), quan.getText().trim(),tinhTP.getText().trim(),tieuSuBenh.getText().trim(), tieuSuBenhGD.getText().trim(),diUngThuoc.getText().trim());
            service.updateBenhNhanById(benhNhan.getId(),newBenhNhan);
            Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
            window.close();
        }
    }

    private boolean isInputValid() {
        if (diUngThuoc.getText()==null||diUngThuoc.getText().isEmpty()){
            return false;
        }
        else if(hoTen.getText().isEmpty()||hoTen.getText()==null){
            return false;
        }
        else if(tieuSuBenh.getText().isEmpty()||tieuSuBenh.getText()==null){
            return false;

        }
        else if(tieuSuBenhGD.getText().isEmpty()||tieuSuBenhGD.getText()==null){
            return false;

        }
        else if (soNha.getText().isEmpty()||soNha.getText()==null){
            return false;
        }
        else if (tenDuong.getText().isEmpty()||tenDuong.getText()==null){
            return false;
        }
        else if (quan.getText().isEmpty()||quan.getText()==null){
            return false;
        }
        else if (tinhTP.getText().isEmpty()||tinhTP.getText()==null){
            return false;
        }
        else if (ngaySinh.getValue()==null){
            return false;
        }

        return true;
    }

    public void setValue(BenhNhan benhNhan){
        this.benhNhan=benhNhan;
    }
}
