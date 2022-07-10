package Controllers;

import Entities.NhanVien;
import Services.NhanVienService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;


public class EditEmpDialogController implements Initializable {
    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private TextField empCMND;

    @FXML
    private TextField empChuyenKhoa;

    @FXML
    private TextField empGender;

    @FXML
    private TextField empName;

    @FXML
    private TextField empCSYT;

    @FXML
    private TextField empRole;

    @FXML
    private TextField empQueQuan;

    @FXML
    private TextField empSDT;

    private NhanVien nhanVien;
    @FXML
    void cancelHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(nhanVien!=null){
            empSDT.setText(nhanVien.getSdt());
            empChuyenKhoa.setText(nhanVien.getChuyenKhoa());
            empQueQuan.setText(nhanVien.getQueQuan());
            empName.setText(nhanVien.getHoTen());
            empCMND.setText(nhanVien.getCmnd());
            empGender.setText(nhanVien.getPhai());
            empRole.setText(nhanVien.getVaiTro());
            empCSYT.setText(nhanVien.getCsyt());
            if(nhanVien.getNgaySinh()!=null){

                birthdayPicker.setValue(Instant.ofEpochMilli(nhanVien.getNgaySinh().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate());
            }
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
            NhanVienService service = new NhanVienService();
            NhanVien newNhanVien = new NhanVien(nhanVien.getId(), empName.getText().trim(), empGender.getText().trim(), Date.from(birthdayPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), empCMND.getText().trim(), empQueQuan.getText().trim(), empSDT.getText().trim(), empCSYT.getText().trim(), empRole.getText().trim(), empChuyenKhoa.getText().trim());

            if(!service.updateNhanVienById(nhanVien.getId(),newNhanVien)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Cập nhật thành công!");
                alert.showAndWait();
            }
            Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
            window.close();
        }
    }
    public boolean isInputValid(){
        if (empChuyenKhoa.getText().isEmpty()){
            return false;
        }
        else if(empCMND.getText().isEmpty()){
            return false;
        }
        else if(empGender.getText().isEmpty()){
            return false;

        }
        else if (empName.getText().isEmpty()){
            return false;
        }
        else if (empSDT.getText().isEmpty()){
            return false;
        }
        else if (empQueQuan.getText().isEmpty()){
            return false;
        }
        else if (birthdayPicker.getValue()==null){
            return false;
        }
        return true;
    }
    public void setValue(NhanVien nhanVien){
        this.nhanVien=nhanVien;
    }
}
