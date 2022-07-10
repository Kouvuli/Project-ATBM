package Controllers;

import Entities.CSYT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CSYTDetailController {
    @FXML
    private Text diaChiCSYT;

    @FXML
    private Text maCSYT;

    @FXML
    private Text sdtCSYT;

    @FXML
    private Text tenCSYT;


    @FXML
    void reloadHandler(ActionEvent event) {

    }

    public void setValue(CSYT csyt){

        maCSYT.setText(csyt.getId());
        sdtCSYT.setText(csyt.getSdtCSYT());
        tenCSYT.setText(csyt.getTenCSYT());
        diaChiCSYT.setText(csyt.getDcCSYT());

    }
}
