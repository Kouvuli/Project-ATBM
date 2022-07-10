import Services.BenhNhanService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage stage) throws IOException {
        window =stage;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/login-view.fxml"));
        scene1 = new Scene(loader.load(), 300, 200);
        stage.setTitle("Login");


        stage.setScene(scene1);
        stage.show();

//        FXMLLoader loader2 = new FXMLLoader(Main.class.getResource("/emp-main-view.fxml"));
//        scene2 = new Scene(loader2.load(), 600, 400);
//        stage.setScene(scene2);

    }

    public static void main(String[] args) {
        launch();
    }
}
