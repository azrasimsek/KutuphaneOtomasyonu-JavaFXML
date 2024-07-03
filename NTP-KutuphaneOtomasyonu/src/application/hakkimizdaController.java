package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class hakkimizdaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorBilgi;

    @FXML
    private AnchorPane anchorHakkimizda;

    @FXML
    void initialize() {
        assert anchorBilgi != null : "fx:id=\"anchorBilgi\" was not injected: check your FXML file 'hakkimizda.fxml'.";
        assert anchorHakkimizda != null : "fx:id=\"anchorHakkimizda\" was not injected: check your FXML file 'hakkimizda.fxml'.";

    }

}
