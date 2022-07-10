package Controllers;

import Utils.JDBCUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jboss.jandex.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    void handleCancleButton(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    void handleLoginButton(ActionEvent event)  {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader =null;
        if(username.getText().trim().contains("DB")){
            loader = new FXMLLoader(Main.class.getResource("/gd-view.fxml"));
            GDViewController controller=new GDViewController();
            controller.setValue(username.getText().trim());
            loader.setController(controller);
        }
        else if(username.getText().trim().contains("BN")){

            loader = new FXMLLoader(Main.class.getResource("/patient-main-view.fxml"));
            PatientMainViewController controller=new PatientMainViewController();
            controller.setValue(username.getText().trim());
            loader.setController(controller);
        }
        else{
            loader = new FXMLLoader(Main.class.getResource("/emp-main-view.fxml"));
            EmpMainViewController controller=new EmpMainViewController();
            controller.setValue(username.getText().trim());
            loader.setController(controller);
        }
//        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/emp-main-view.fxml"));
        Scene main = null;
        Connection connection=null;
        Parent root=null;
        try {
            new JDBCUtils(username.getText().trim(), password.getText().trim());
            root = loader.load();


            main = new Scene(root, 600, 400);


            connection=JDBCUtils.getConnection();

        } catch (IOException | ClassNotFoundException | SQLException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Sai username/password!");
            alert.showAndWait();
        }
        if(connection!=null){
            window.setTitle("Main");
            window.setScene(main);
        }

    }
}
