package Controllers;

import Entities.HSBA_DV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HSBADVDetailDialogController implements Initializable {
    @FXML
    private Text ketQua;

    @FXML
    private Text maDichVu;

    @FXML
    private Text maKTV;

    @FXML
    private Text ngay;

    HSBA_DV hsba_dv;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ketQua.setText(hsba_dv.getKetQua());
        maDichVu.setText(hsba_dv.getMaDV());
        maKTV.setText(hsba_dv.getMaKTV());
        ngay.setText(hsba_dv.getNgay().toString());
    }

    public void setValue(HSBA_DV hsba_dv){
        this.hsba_dv=hsba_dv;
    }
}
