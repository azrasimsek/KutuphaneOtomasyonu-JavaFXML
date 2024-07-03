package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

public class UyeIslemleriController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorUyeIslem;

    @FXML
    private AnchorPane anchorUyeIslemleri;

    @FXML
    private Button btn_ekle;
    
    @FXML
    private Button btn_kaydet;

    @FXML
    private Button btn_sil;

    @FXML
    void tableUyeler_MouseClick(MouseEvent event) {

    	Uyeler_table uye = new Uyeler_table();
    	uye = (Uyeler_table)tableUyeler.getItems().get(tableUyeler.getSelectionModel().getFocusedIndex());
    	txt_adSoyad.setText(uye.getAdSoyad());
    	txt_sifre.setText(uye.getSifre());
    	txt_telefon.setText(uye.getTelefon());
    	txt_yetki.setText(Integer.toString(uye.getYetki()));
    }
    @FXML
    private TableColumn<Uyeler_table, String> columnAdSoyad;

    @FXML
    private TableColumn<Uyeler_table,Integer> columnID;

    @FXML
    private TableColumn<Uyeler_table, String> columnSifre;

    @FXML
    private TableColumn<Uyeler_table, String> columnTelefon;

    @FXML
    private TableColumn<Uyeler_table, Integer> columnYetki;

    @FXML
    private TableView<Uyeler_table> tableUyeler;

    @FXML
    private TextField txt_adSoyad;

    @FXML
    private TextField txt_sifre;

    @FXML
    private TextField txt_telefon;

    @FXML
    private TextField txt_yetki;
    
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
  	  private void showAlertSil(String message) {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("SİLİNDİ");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
  	  private void showAlertGuncelle(String message) {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("GÜNCELLENDİ");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
  	  private void showAlertEkle(String message) {
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("ÜYE EKLENDİ");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
  	  
  	    PreparedStatement sorguIfadesi=null;
  	    ResultSet getirilen =null;
  	    String sql;
  	
  	//sql connection sonrası
  	public void degerleriGetir(TableView tableUyeler ) {
  		sql = "select * from uyeler";
  		ObservableList<Uyeler_table> kayitlarliste =FXCollections.observableArrayList();
  		try {
  			sorguIfadesi = connection.prepareStatement(sql);
  			 ResultSet getirilen = sorguIfadesi.executeQuery();
  			 while(getirilen.next()) {
  				kayitlarliste.add(new Uyeler_table(getirilen.getInt("ID"),getirilen.getString("adSoyad"),getirilen.getString("sifre"),getirilen.getString("telefon"),getirilen.getInt("yetki")));
  				
  			 }
  			 columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
  			columnAdSoyad.setCellValueFactory(new PropertyValueFactory<>("adSoyad"));
  			columnSifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
  			columnTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
  			columnYetki.setCellValueFactory(new PropertyValueFactory<>("yetki"));
  			tableUyeler.setItems(kayitlarliste);
		} catch(Exception e) {
			e.printStackTrace();
		}
  	}    

    @FXML
    void btn_ekle_Click(ActionEvent event) {
    	
      	 sql = "INSERT INTO uyeler (adSoyad, sifre, telefon, yetki) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Parametrize ederek sorguyu hazırla
       	 statement.setString(1, txt_adSoyad.getText());
       	 statement.setString(2, txt_sifre.getText());
       	 statement.setString(3, txt_telefon.getText());
       	 statement.setString(4, txt_yetki.getText());
            // Ekleme sorgusunu çalıştır
       	 statement.executeUpdate();
       	 showAlertEkle("Yeni Üye Başarılı Bir Şekilde Eklendi , Lütfen Sayfayı Yenileyin.");
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
      	
    }
  	
    @FXML
    void btn_kaydet_Click(ActionEvent event) {
       	
       		 // Seçilen satırları al
        ObservableList<Uyeler_table> selectedMembers = tableUyeler.getSelectionModel().getSelectedItems();

        // MySQL veritabanına bağlan
        
            // Her bir seçili üye için güncelleme işlemi yap
            for (Uyeler_table member : selectedMembers) {
                // Güncelleme sorgusu
                sql = "UPDATE uyeler SET AdSoyad = ?, Sifre = ?, Telefon = ?, Yetki = ? WHERE ID = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Yeni değerleri parametrize ederek sorguyu hazırla
                    statement.setString(1, txt_adSoyad.getText());
                    statement.setString(2, txt_sifre.getText());
                    statement.setString(3, txt_telefon.getText());
                    statement.setString(4, txt_yetki.getText());
                    statement.setInt(5, member.getID()); // Örnek olarak ID'yi buradan alınabilir
                    // Güncelleme sorgusunu çalıştır
                    statement.executeUpdate();
                    showAlertGuncelle("Üye Başarılı Bir Şekilde Güncellendi Lütfen Sayfayı Yenileyin");
                } 
                catch (SQLException e) {
            e.printStackTrace();
        }
    	
            }
       		
       	
}
    	
 
    	
    	/*ObservableList<Uyeler_table> selectedMembers = tableUyeler.getSelectionModel().getSelectedItems();
    	for (Uyeler_table uye : selectedMembers) {
    		sql = "UPDATE FROM uyeler WHERE adSoyad = ? AND sifre = ? AND telefon = ? AND yetki = ?";
    	
    	    String adSoyad = uye.getAdSoyad();
    	    String sifre = uye.getSifre();
    	    String telefon = uye.getTelefon();
    	    int yetki = uye.getYetki();
    	    
    	    try {
    	        // İstisna oluşabilecek kod bloğu
    	         sorguIfadesi = connection.prepareStatement(sql);
    	      
    	         sorguIfadesi.setString(1, uye.getAdSoyad());
    	         sorguIfadesi.setString(2, uye.getSifre());
    	         sorguIfadesi.setString(3, uye.getTelefon());
    	         sorguIfadesi.setInt(4, uye.getYetki());
    	         sorguIfadesi.executeUpdate();
    	         showAlertGuncelle("Üye Başarılı Bir Şekilde Güncellendi Lütfen Sayfayı Yenileyin");
    	    } catch (SQLException e) {
    	        e.printStackTrace(); // Hata mesajını yazdırabilirsiniz
    	    }
    	   
    	    // Silme işlemi için bu verileri kullanabilirsiniz
    	}*/
    

    @FXML
    void btn_sil_Click(ActionEvent event) {
    	//Uyeler_table uye = new Uyeler_table();
    	//uye = (Uyeler_table)tableUyeler.getItems().get(tableUyeler.getSelectionModel().getSelectedItems());
    	
    	ObservableList<Uyeler_table> selectedMembers = tableUyeler.getSelectionModel().getSelectedItems();
    	for (Uyeler_table uye : selectedMembers) {
    		sql = "DELETE FROM uyeler WHERE ID = ? AND adSoyad = ? AND sifre = ? AND telefon = ? AND yetki = ?";
    		int ID = uye.getID();
    	    String adSoyad = uye.getAdSoyad();
    	    String sifre = uye.getSifre();
    	    String telefon = uye.getTelefon();
    	    int yetki = uye.getYetki();
    	    
    	    try {
    	        // İstisna oluşabilecek kod bloğu
    	         sorguIfadesi = connection.prepareStatement(sql);
    	         sorguIfadesi.setInt(1, uye.getID());
    	         sorguIfadesi.setString(2, uye.getAdSoyad());
    	         sorguIfadesi.setString(3, uye.getSifre());
    	         sorguIfadesi.setString(4, uye.getTelefon());
    	         sorguIfadesi.setInt(5, uye.getYetki());
    	         sorguIfadesi.executeUpdate();
    	         showAlertSil("Üye başarılı bir şekilde silindi lütfen sayfayı yenileyin");
    	    } catch (SQLException e) {
    	        e.printStackTrace(); // Hata mesajını yazdırabilirsiniz
    	    }
    	   
    	    // Silme işlemi için bu verileri kullanabilirsiniz
    	}

    }

    @FXML
    void txt_adSoyad_Click(ActionEvent event) {

    }

    @FXML
    void txt_sifre_Click(ActionEvent event) {

    }

    @FXML
    void txt_telefon_Click(ActionEvent event) {

    }

    @FXML
    void txt_yetki_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	connectToDatabase();
    	degerleriGetir(tableUyeler);
    	
    }

}
