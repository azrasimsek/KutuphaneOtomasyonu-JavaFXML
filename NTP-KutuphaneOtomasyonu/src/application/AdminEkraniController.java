package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AdminEkraniController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorSag;

    @FXML
    private AnchorPane anchorSol;

    @FXML
    private AnchorPane anchorTum;

    @FXML
    private AnchorPane anchor_adminMenu;

    @FXML
    private Button btn_hakkimizda;

    @FXML
    private Button btn_ktpIslem;

    @FXML
    private Button btn_ktpOdunc;


    @FXML
    private Button btn_uyeIslemleri;

    @FXML
    private Label lbl_islem;

    @FXML
    void btn_hakkimizda_Click(ActionEvent event) {
    	try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("hakkimizda.fxml"));
			anchorSag.getChildren().setAll(panel);
			//Scene scene = new Scene(panel,591,500);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btn_ktpIslem_Click(ActionEvent event) {
    	try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("KitapIslemleri.fxml"));
			anchorSag.getChildren().setAll(panel);
			//Scene scene = new Scene(panel,591,500);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btn_ktpOdunc_Click(ActionEvent event) {

    }

    @FXML
    void btn_uyeIslemleri_Click(ActionEvent event) {
    	try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("UyeIslemleri.fxml"));
			anchorSag.getChildren().setAll(panel);
			//Scene scene = new Scene(panel,591,500);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    	
    }

}
