package Controllers;

import Entities.BenhNhan;
import Entities.HSBA;
import Entities.HSBA_DV;
import Services.BenhNhanService;
import Services.HSBA_DVService;
import Services.NhanVienService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HSBADetailController implements Initializable {
    @FXML
    private Text chanDoan;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Text ketLuan;

    @FXML
    private Hyperlink maBenhNhan;

    @FXML
    private Text maCSYT;

    @FXML
    private Text maKhoa;

    @FXML
    private Text ngay;

    @FXML
    private Text hsbaId;

    @FXML
    private Text maBacSi;

    @FXML
    private Hyperlink dichVu;

    private HSBA hsba;
    private String role;
    private String id;
    private ObservableList<String> list;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteBtn.setVisible(false);


    }
    public void setValue(ObservableList<String>list,String id, HSBA hsba, String role){
        this.role=role;
        this.hsba=hsba;
        this.id=id;
        this.list=list;
        chanDoan.setText(hsba.getChanDoan());
        ketLuan.setText(hsba.getKetLuan());
        maBenhNhan.setText(hsba.getMaBN());
        if(role.equals("Co so y te")){
            deleteBtn.setVisible(true);

        }
        if(role.equals("Thanh tra")){
            maBenhNhan.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage window = new Stage();

                    window.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/patient-detail-dialog.fxml"));
                    PatientDetailController controller=new PatientDetailController();
                    try {
                        BenhNhanService service=new BenhNhanService();

                        BenhNhan benhNhan=service.getBenhNhanById(hsba.getMaBN());
                        controller.setValue(benhNhan);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loader.setController(controller);

                    Parent root= null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    window.setTitle("Bệnh nhân");
                    window.setScene(scene);
                    window.show();
                }
            });
            dichVu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage window = new Stage();
                    HSBA_DVService service=new HSBA_DVService();
                    window.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/hsba-dv-detail-dialog.fxml"));
                    HSBADVDetailDialogController controller=new HSBADVDetailDialogController();
                    try {
                        controller.setValue(service.getHSBADVById(hsba.getId()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loader.setController(controller);

                    Parent root= null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    window.setTitle("Dịch vụ");
                    window.setScene(scene);
                    window.show();
                }
            });
        }
        else{
            if(!role.equals("Co so y te") &&!role.equals("Nghien cuu")){

                maBenhNhan.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage window = new Stage();

                        window.initModality(Modality.APPLICATION_MODAL);
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("/patient-detail-dialog.fxml"));
                        PatientDetailController controller=new PatientDetailController();
                        try {
                            NhanVienService service=new NhanVienService();

                            BenhNhan benhNhan=service.getBenhNhanById(id,hsba.getMaBN());
                            controller.setValue(benhNhan);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        loader.setController(controller);

                        Parent root= null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        window.setTitle("Bệnh nhân");
                        window.setScene(scene);
                        window.show();
                    }
                });
            }

            dichVu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage window = new Stage();
                    NhanVienService service=new NhanVienService();
                    window.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/hsba-dv-detail-dialog.fxml"));
                    HSBADVDetailDialogController controller=new HSBADVDetailDialogController();
                    try {
                        HSBA_DV k=service.getHSBADVById(id);
                        controller.setValue(k);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    loader.setController(controller);

                    Parent root= null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    window.setTitle("Dịch vụ");
                    window.setScene(scene);
                    window.show();
                }
            });
        }
        maCSYT.setText(hsba.getMaCSYT());
//        HSBA_DV hsba_dv=new HSBA_DV();


        maKhoa.setText(hsba.getMaKhoa());
        hsbaId.setText(hsba.getId());
        ngay.setText(hsba.getNgay().toString());
        maBacSi.setText(hsba.getMaBS());
    }
    @FXML
    void deleteHandler(ActionEvent event) throws SQLException {
        NhanVienService service=new NhanVienService();
        service.deleteHSBADVById(id, hsba.getId());
        service.deleteHSBAById(id,hsba.getId());
        list.remove(hsba.getId());
    }


}
