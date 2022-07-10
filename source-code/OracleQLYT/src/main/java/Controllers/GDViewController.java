package Controllers;

import Models.*;
import Services.AuditService;
import Utils.JDBCUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GDViewController implements Initializable {

    @FXML
    private TableView<StandardAuditRow> standardAuditTable;

    @FXML
    private TableColumn<StandardAuditRow, String> targetUser;

    @FXML
    private TableColumn<StandardAuditRow, String> actionName;

    @FXML
    private TableColumn<StandardAuditRow, String> sqlText;

    @FXML
    private TableColumn<StandardAuditRow, String> objectSchema;

    @FXML
    private TableColumn<StandardAuditRow, String> auditOption;


    @FXML
    private TableColumn<StandardAuditRow, String> currentUser;

    @FXML
    private TableColumn<StandardAuditRow, String> dbUsername;

    @FXML
    private TableView<FineGrainedAuditRow> fineGrainedAuditTable;

    @FXML
    private TableColumn<FineGrainedAuditRow, String> dbUsername1;


    @FXML
    private TableColumn<FineGrainedAuditRow, String> actionName1;

    @FXML
    private TableColumn<FineGrainedAuditRow, String> eventTimestamp;

    @FXML
    private TableColumn<FineGrainedAuditRow, String> fgaPolicyName;


    @FXML
    private TableColumn<FineGrainedAuditRow, String> auditType;


    @FXML
    private TableColumn<FineGrainedAuditRow, String> sqlText1;

    @FXML
    private ScrollPane content;

    @FXML
    private Text empId;


    private String id;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(empId!=null){
            empId.setText(id);
        }
//        try {
//            refreshStandardTable();
//            refreshFineGrainedTable();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
    }
    public void refreshStandardTable() throws SQLException {
        AuditService service=new AuditService();
        List<StandardAuditRow> list2=service.getStandardAudit();
        dbUsername.setCellValueFactory(new PropertyValueFactory<StandardAuditRow, String>("dbUsername"));
        actionName.setCellValueFactory(new PropertyValueFactory<StandardAuditRow, String>("actionName"));
        objectSchema.setCellValueFactory(new PropertyValueFactory<StandardAuditRow, String>("objectSchema"));
        currentUser.setCellValueFactory(new PropertyValueFactory<StandardAuditRow, String>("currentUser"));
        targetUser.setCellValueFactory(new PropertyValueFactory<StandardAuditRow, String>("targetUser"));
        sqlText.setCellValueFactory(new PropertyValueFactory<StandardAuditRow, String>("sqlText"));
        auditOption.setCellValueFactory(new PropertyValueFactory<StandardAuditRow, String>("auditOption"));
        standardAuditTable.setItems(FXCollections.observableArrayList(list2));
    }
    public void refreshFineGrainedTable() throws SQLException {
        AuditService service=new AuditService();
        List<FineGrainedAuditRow> list1=service.getFineGrainedAudit();

        dbUsername1.setCellValueFactory(new PropertyValueFactory<FineGrainedAuditRow, String>("dbUsername"));
        auditType.setCellValueFactory(new PropertyValueFactory<FineGrainedAuditRow, String>("auditType"));
        actionName1.setCellValueFactory(new PropertyValueFactory<FineGrainedAuditRow, String>("actionName"));
        eventTimestamp.setCellValueFactory(new PropertyValueFactory<FineGrainedAuditRow, String>("eventTimestamp"));
        sqlText1.setCellValueFactory(new PropertyValueFactory<FineGrainedAuditRow, String>("sqlText"));
        fgaPolicyName.setCellValueFactory(new PropertyValueFactory<FineGrainedAuditRow, String>("fgaPolicyName"));
        fineGrainedAuditTable.setItems(FXCollections.observableArrayList(list1));

    }

    @FXML
    void reloadFineGrainedAuditHandler(ActionEvent event) {
        try {

            refreshFineGrainedTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void reloadStandardAuditHandler(ActionEvent event) {
        try {
            refreshStandardTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void setValue(String id){
        this.id=id;
    }
}
