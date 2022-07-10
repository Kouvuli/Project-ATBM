package Controllers;

import Entities.HSBA;
import Entities.NhanVien;
import Services.NhanVienService;
import javafx.collections.ObservableList;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class NewHSBADialogController implements Initializable {
    @FXML
    private TextField chanDoan;

    @FXML
    private TextField ketLuan;

    @FXML
    private TextField maBN;

    @FXML
    private TextField maBS;

    @FXML
    private TextField maHSBA;

    @FXML
    private TextField maKhoa;

    @FXML
    private DatePicker ngay;

    private String id;
    private ObservableList<String> list;
    @FXML
    void cancelHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void confirmHandler(ActionEvent event) {
        if(!isValidInput()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Dữ liệu nhập không hợp lệ!");
            alert.showAndWait();
            return;
        }
        NhanVienService service=new NhanVienService();
        try {
            NhanVien nhanVien=service.getNhanVienById(id);
            HSBA hsba = new HSBA(maHSBA.getText().trim(), maBN.getText().trim(), Date.from(ngay.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), chanDoan.getText().trim(), maBS.getText().trim(), maKhoa.getText().trim(), nhanVien.getCsyt().trim(), ketLuan.getText().trim());
            service.addHSBA(id,hsba);

            list.add(maHSBA.getText().trim());

        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Đã xảy ra lỗi!");
            alert.showAndWait();
        }

        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setValue(ObservableList<String> list, String id){
        this.list=list;
        this.id=id;
    }

    public boolean isValidInput(){
        if(chanDoan.getText().isEmpty()){
            return false;
        }else if(ketLuan.getText().isEmpty()){
            return false;
        }else if(maBN.getText().isEmpty()){
            return false;
        }else if(maBS.getText().isEmpty()){
            return false;
        }else if(maHSBA.getText().isEmpty()){
            return false;
        }else if(maKhoa.getText().isEmpty()){
            return false;
        }else if(ngay.getValue()==null){
            return false;
        }
        return true;
    }
}
