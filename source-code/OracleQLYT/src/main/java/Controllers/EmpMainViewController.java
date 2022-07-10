package Controllers;


import Entities.BenhNhan;
import Entities.HSBA;
import Entities.NhanVien;
import Services.BenhNhanService;
import Services.HSBAService;
import Services.NhanVienService;
import Utils.JDBCUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmpMainViewController implements Initializable {

//    @FXML
//    private ListView<String> benhNhanList;

    @FXML
    private ButtonBar buttonBar;


    @FXML
    private ScrollPane content;

    @FXML
    private Text empId;

    @FXML
    private Text empRole;

    @FXML
    private ListView<String> hsbaList;

    @FXML
    private Text notifyText;

    @FXML
    private TextField searchBar;

    List<String> danhSachIdHSBA=new ArrayList<>();
    @FXML
    void logOutHandler(ActionEvent event) throws SQLException, IOException {
        // get a handle to the stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // do what you have to do
        stage.close();
        JDBCUtils.closeConnection();
        Stage newWindow =stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        Scene scene1 = new Scene(loader.load(), 300, 200);
        stage.setTitle("Login");


        stage.setScene(scene1);
        stage.show();

    }

    @FXML
    void editHandler(ActionEvent event) throws SQLException {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/edit-emp-dialog.fxml"));
        EditEmpDialogController controller=new EditEmpDialogController();
        controller.setValue(nhanVien);
        loader.setController(controller);

        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene editScene = new Scene(root);
        window.setTitle("Sửa nhân viên");
        window.setScene(editScene);
        window.showAndWait();
        NhanVienService service=new NhanVienService();
        nhanVien=service.getNhanVienById(id);
    }
    private NhanVien nhanVien;
    private static String id;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonBar.setVisible(false);
        if(id!=null){
            NhanVienService service=new NhanVienService();
            try {
                nhanVien=service.getNhanVienById(id);
                empId.setText(id);
                empRole.setText(nhanVien.getVaiTro());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if(empRole.getText().trim().equals("Thanh tra")){
            HSBAService service=new HSBAService();
            try {
                danhSachIdHSBA=service.getDanhSachHSBAIdById(id);
                hsbaList.setItems(FXCollections.observableList(danhSachIdHSBA));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            hsbaList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String hsbaId = (String) hsbaList.getSelectionModel().getSelectedItem();
                    HSBA a= null;
                    try {
                        a = service.getHSBAById(hsbaId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/hsba-detail-view.fxml"));
                    Parent root = null;


                    try {
                        root = (Parent) loader.load();
                        HSBADetailController controller = loader.getController();
                        controller.setValue(FXCollections.observableList(danhSachIdHSBA),id,a,empRole.getText().trim());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    content.setContent(root);
                }
            });
        }else{
            if(empRole.getText().trim().equals("Co so y te")){
                buttonBar.setVisible(true);
            }

            NhanVienService service=new NhanVienService();


            try {
                danhSachIdHSBA=service.getDanhSachHSBAIdById(id);
                hsbaList.setItems(FXCollections.observableList(danhSachIdHSBA));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            hsbaList.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String hsbaId = (String) hsbaList.getSelectionModel().getSelectedItem();
                    HSBA a= null;
                    try {
                        a = service.getHSBAById(id,hsbaId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/hsba-detail-view.fxml"));
                    Parent root = null;


                    try {
                        root = (Parent) loader.load();
                        HSBADetailController controller = loader.getController();
                        controller.setValue(FXCollections.observableList(danhSachIdHSBA),id,a,empRole.getText().trim());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    content.setContent(root);
                }
            });
        }



    }
    @FXML
    void searchHandler(ActionEvent event) {

        if(searchBar.getText().isEmpty()||searchBar.getText()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Vui lòng nhập CMND hoặc ID bệnh nhân!");
            alert.showAndWait();
        }else{
            if(empRole.getText().trim().equals("Thanh tra")){
                Stage window = new Stage();

                window.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/patient-detail-dialog.fxml"));
                PatientDetailController controller=new PatientDetailController();
                BenhNhan benhNhan=null;
                try {
                    BenhNhanService service=new BenhNhanService();
                    benhNhan=service.getBenhNhanById(searchBar.getText().trim());
                    if(benhNhan==null){

                        benhNhan=service.getBenhNhanByCMND(searchBar.getText().trim());
                    }
                    if(benhNhan!=null){
                        controller.setValue(benhNhan);
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setContentText("Không có bệnh nhân cần tìm!");
                        alert.showAndWait();
                        return;
                    }
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
            else {
                Stage window = new Stage();

                window.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/patient-detail-dialog.fxml"));
                PatientDetailController controller=new PatientDetailController();
                BenhNhan benhNhan=null;
                try {
                    NhanVienService service=new NhanVienService();
                    benhNhan=service.getBenhNhanById(id,searchBar.getText().trim());
                    if(benhNhan==null){

                        benhNhan=service.getBenhNhanByCMND(id,searchBar.getText().trim());
                    }
                    if(benhNhan!=null){
                        controller.setValue(benhNhan);
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setContentText("Không có bệnh nhân cần tìm!");
                        alert.showAndWait();
                        return;
                    }
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

        }
    }

    @FXML
    void addHandler(ActionEvent event) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/new-hsba-dialog.fxml"));
        NewHSBADialogController controller=new NewHSBADialogController();
        controller.setValue(FXCollections.observableList(danhSachIdHSBA),id);
        loader.setController(controller);

        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene editScene = new Scene(root);
        window.setTitle("Thêm HSBA");
        window.setScene(editScene);
        window.showAndWait();


    }
    public void setValue(String id){
        this.id=id;
    }
}
