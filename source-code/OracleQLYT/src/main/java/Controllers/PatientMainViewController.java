package Controllers;

import Entities.BenhNhan;
import Services.BenhNhanService;
import Utils.JDBCUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientMainViewController implements Initializable {
    @FXML
    private Text cmnd;

    @FXML
    private Button deleteBtn;

    @FXML
    private Text diUngThuoc;

    @FXML
    private Text diaChi;

    @FXML
    private Button editBtn;

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

    private BenhNhan benhNhan;
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
    void editHandler(ActionEvent event) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/edit-patient-dialog.fxml"));
        EditPatientDialogController controller=new EditPatientDialogController();
        controller.setValue(benhNhan);
        loader.setController(controller);

        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene editScene = new Scene(root);
        window.setTitle("Sửa bệnh nhân");
        window.setScene(editScene);
        window.showAndWait();
        refreshData();
    }

    private static String id;
    public void refreshData(){
        if(id!=null){
            BenhNhanService service=new BenhNhanService();
            try {
                benhNhan=service.getBenhNhanById(id);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshData();

    }

    public void setValue(String id){
        this.id=id;

    }

}
