package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UyeEkraniController {
	//SQL con işlemleri
		 @FXML
		    private TextField usernameField;

		 @FXML
		    private TextField passwordField;

		    private Connection connection;
		    
		    private void connectToDatabase() {
		        try {
		            // JDBC sürücüsünü yükle
		        	Class.forName("com.mysql.cj.jdbc.Driver");

		            // Veritabanı bağlantısı kur
		            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kutuphaneotomasyonu", "root", "");
		            System.out.println("Veritabanına bağlandı!");
		        } catch (ClassNotFoundException | SQLException e) {
		            e.printStackTrace();
		            showAlert("Veritabanına bağlanırken bir hata oluştu!");
		        }
		    }

		    private void showAlert(String message) {
		        Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Hata");
		        alert.setHeaderText(null);
		        alert.setContentText(message);
		        alert.showAndWait();
		    }

		    PreparedStatement sorguIfadesi=null;
		    ResultSet getirilen =null;
		    String sql;
		
		//sql connection sonrası

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
    private AnchorPane anchor_uyeMenu;

    @FXML
    private Button btn_hakkimizda;

    @FXML
    private Button btn_hspBilgileri;

    @FXML
    private Button btn_ktpGor;

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
    void btn_hspBilgileri_Click(ActionEvent event) {

    }

    public String kullaniciAd;
    public String Sifre;
    public String Telefon ;
    public int Yetki;
    public void setKullaniciBilgileri(String kullaniciAdi, String sifre , String telefon , int yetki) {
       // txt_uyeAd.setText(kullaniciAdi);
       // txt_uyeSifre.setText(sifre);
       // txt_uyeTel.setText(telefon);
       // txt_uyeYetki.setText(Integer.toString(yetki));
        
        kullaniciAd= kullaniciAdi;
        Sifre = sifre;
        Telefon = telefon ;
        Yetki = yetki;
    }
    
    @FXML
    void btn_ktpGor_Click(ActionEvent event) {
    	try {
			//AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("UyeKitapGoruntule.fxml"));
			//anchorSag.getChildren().setAll(panel);
			//Scene scene = new Scene(panel,591,500);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UyeKitapGoruntule.fxml"));
			Parent root = loader.load();

			UyeKitapGoruntuleController controller = loader.getController();
			controller.setKullaniciBilgiler(kullaniciAd,Sifre,Telefon,Yetki);

			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("UyeKitapGoruntule.fxml"));
			anchorSag.getChildren().setAll(panel);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }

}
