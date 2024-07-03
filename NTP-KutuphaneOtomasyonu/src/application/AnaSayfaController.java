package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.sql.ResultSet;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnaSayfaController {
	
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
    private AnchorPane anchor1;

    @FXML
    private Button btn_giris;

    @FXML
    private TextField txt_KullaniciID;

    @FXML
    private TextField txt_sifre;

    @FXML
    void btn_giris_Click(ActionEvent event) {
    	sql ="select * from uyeler where adSoyad=? and sifre=?";
    	try {
            sorguIfadesi=connection.prepareStatement(sql);
            sorguIfadesi.setString(1, txt_KullaniciID.getText().trim());
            sorguIfadesi.setString(2, txt_sifre.getText().trim());
            
            ResultSet getirilen= sorguIfadesi.executeQuery();
            if(!getirilen.next()) {
            	showAlert("Kullanıcı adı veya şifre hatalı!");
            }
            else {
            	getirilen.getString(1);
            	System.out.println("kullanıcı ID "+getirilen.getInt("ID"));
            	System.out.println("kullanıcı ad soyadı "+getirilen.getString("adSoyad"));
            	System.out.println("kullanıcı sifresi "+getirilen.getString("sifre"));
            	System.out.println("kullanıcı yetkisi "+getirilen.getInt("yetki"));
            	
            	
            	
            	
            	if(getirilen.getInt("yetki")==0) {
            		try {
            			/*Stage stage1 = new Stage();
            			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("UyeEkrani.fxml"));
            			Scene scene = new Scene(pane1,900,500);
            			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            			stage1.setScene(scene);
            			stage1.show();
            			*/
            			
            			
            			FXMLLoader loader = new FXMLLoader(getClass().getResource("UyeEkrani.fxml"));
            			Parent root = loader.load();

            			UyeEkraniController controller = loader.getController();
            			controller.setKullaniciBilgileri(getirilen.getString("adSoyad"),getirilen.getString("sifre"),getirilen.getString("telefon"),getirilen.getInt("yetki"));

 			
            			
            			Stage stage = new Stage();
            			Scene scene = new Scene(root);
            			stage.setScene(scene);
            			stage.show();
            			
            		} catch(Exception e) {
            			e.printStackTrace();
            		}
            	}
            	else {
            		try {
            			Stage stage1 = new Stage();
            			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminEkrani.fxml"));
            			Scene scene = new Scene(pane1,900,500);
            			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            			stage1.setScene(scene);
            			stage1.show();
            		} catch(Exception e) {
            			e.printStackTrace();
            		}
            	}
            	
            }
            
        } catch (Exception e) {
            
            
        }
    }

    @FXML
    void txt_KullaniciID_Click(ActionEvent event) {

    }

    @FXML
    void txt_sifre_Click(ActionEvent event) {

    }
    
  //  public String kAdi = txt_KullaniciID.getText();
    @FXML
    void initialize() {
    	connectToDatabase();
    	

    }

}
