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

public class KitapIslemleriController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorKitapIslem;

    @FXML
    private AnchorPane anchorKitapIslemleri;

    @FXML
    private Button btn_guncelle;

    @FXML
    private Button btn_kaydet;

    @FXML
    private Button btn_sil;
    
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
	        alert.setTitle("KİTAP EKLENDİ");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
 	  
 	    PreparedStatement sorguIfadesi=null;
 	    ResultSet getirilen =null;
 	    String sql;
 	
 	//sql connection sonrası
 	  	public void degerleriGetir(TableView tableKitaplar ) {
 	  		sql = "select * from kitaplar";
 	  		ObservableList<Kitaplar_table> kayitlarliste =FXCollections.observableArrayList();
 	  		try {
 	  			sorguIfadesi = connection.prepareStatement(sql);
 	  			 ResultSet getirilen = sorguIfadesi.executeQuery();
 	  			 while(getirilen.next()) {
 	  				kayitlarliste.add(new Kitaplar_table(getirilen.getInt("ID"),getirilen.getString("kitapAdi"),getirilen.getString("yazarAdi"),getirilen.getString("sayfaSayisi"),getirilen.getString("turu")));
 	  				
 	  			 }
 	  			 columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
 	  			columnKitapAd.setCellValueFactory(new PropertyValueFactory<>("kitapAdi"));
 	  			columnYazarAd.setCellValueFactory(new PropertyValueFactory<>("yazarAdi"));
 	  			columnSayfaSayisi.setCellValueFactory(new PropertyValueFactory<>("sayfaSayisi"));
 	  			columnTur.setCellValueFactory(new PropertyValueFactory<>("turu"));
 	  			tableKitaplar.setItems(kayitlarliste);
 			} catch(Exception e) {
 				e.printStackTrace();
 			}
 	  	}  

    @FXML
    private TableColumn<Kitaplar_table, Integer> columnID;

    @FXML
    private TableColumn<Kitaplar_table, String> columnKitapAd;

    @FXML
    private TableColumn<Kitaplar_table, String> columnSayfaSayisi;

    @FXML
    private TableColumn<Kitaplar_table, String> columnTur;

    @FXML
    private TableColumn<Kitaplar_table, String> columnYazarAd;

    @FXML
    private TableView<Kitaplar_table> tableKitaplar;

    @FXML
    private TextField txt_kitapAd;

    @FXML
    private TextField txt_sayfaSayisi;

    @FXML
    private TextField txt_tur;

    @FXML
    private TextField txt_yazarAd;

    @FXML
    void btn_guncelle_Click(ActionEvent event) {
  		 // Seçilen satırları al
   ObservableList<Kitaplar_table> selectedMembers = tableKitaplar.getSelectionModel().getSelectedItems();

   // MySQL veritabanına bağlan
   
       // Her bir seçili üye için güncelleme işlemi yap
       for (Kitaplar_table member : selectedMembers) {
           // Güncelleme sorgusu
           sql = "UPDATE kitaplar SET kitapAdi = ?, yazarAdi = ?, sayfaSayisi = ?, turu = ? WHERE ID = ?";
           try (PreparedStatement statement = connection.prepareStatement(sql)) {
               // Yeni değerleri parametrize ederek sorguyu hazırla
               statement.setString(1, txt_kitapAd.getText());
               statement.setString(2, txt_yazarAd.getText());
               statement.setString(3, txt_sayfaSayisi.getText());
               statement.setString(4, txt_tur.getText());
               statement.setInt(5, member.getID()); // Örnek olarak ID'yi buradan alınabilir
               // Güncelleme sorgusunu çalıştır
               statement.executeUpdate();
               showAlertGuncelle("Kitap Başarılı Bir Şekilde Güncellendi Lütfen Sayfayı Yenileyin");
           } 
           catch (SQLException e) {
       e.printStackTrace();
   }
	
       }
    }

    @FXML
    void btn_kaydet_Click(ActionEvent event) {
     	 sql = "INSERT INTO kitaplar (kitapAdi, yazarAdi, sayfaSayisi, turu) VALUES (?, ?, ?, ?)";
       try (PreparedStatement statement = connection.prepareStatement(sql)) {
           // Parametrize ederek sorguyu hazırla
      	 statement.setString(1, txt_kitapAd.getText());
      	 statement.setString(2, txt_yazarAd.getText());
      	 statement.setString(3, txt_sayfaSayisi.getText());
      	 statement.setString(4, txt_tur.getText());
           // Ekleme sorgusunu çalıştır
      	 statement.executeUpdate();
      	 showAlertEkle("Yeni Kitap Başarılı Bir Şekilde Eklendi , Lütfen Sayfayı Yenileyin.");
       
   } catch (SQLException e) {
       e.printStackTrace();
   }
    }

    @FXML
    void btn_sil_Click(ActionEvent event) {
    	
    	ObservableList<Kitaplar_table> selectedMembers = tableKitaplar.getSelectionModel().getSelectedItems();
    	for (Kitaplar_table kitap : selectedMembers) {
    		sql = "DELETE FROM kitaplar WHERE ID = ? AND kitapAdi = ? AND yazarAdi = ? AND sayfaSayisi = ? AND turu = ?";
    		int ID = kitap.getID();
    	    String kitapAdi = kitap.getKitapAdi();
    	    String yazarAdi = kitap.getYazarAdi();
    	    String sayfaSayisi = kitap.getSayfaSayisi();
    	    String turu = kitap.getTuru();
    	    
    	    try {
    	        // İstisna oluşabilecek kod bloğu
    	         sorguIfadesi = connection.prepareStatement(sql);
    	         sorguIfadesi.setInt(1, kitap.getID());
    	         sorguIfadesi.setString(2, kitap.getKitapAdi());
    	         sorguIfadesi.setString(3, kitap.getYazarAdi());
    	         sorguIfadesi.setString(4, kitap.getSayfaSayisi());
    	         sorguIfadesi.setString(5, kitap.getTuru());
    	         sorguIfadesi.executeUpdate();
    	         showAlertSil("Kitap başarılı bir şekilde silindi lütfen sayfayı yenileyin");
    	    } catch (SQLException e) {
    	        e.printStackTrace(); // Hata mesajını yazdırabilirsiniz
    	    }
    	   
    	    // Silme işlemi için bu verileri kullanabilirsiniz
    	}
    }

    @FXML
    void tableKitaplar_MouseClick(MouseEvent event) {

    	Kitaplar_table kitap = new Kitaplar_table();
    	kitap = (Kitaplar_table)tableKitaplar.getItems().get(tableKitaplar.getSelectionModel().getFocusedIndex());
    	txt_kitapAd.setText(kitap.getKitapAdi());
    	txt_yazarAd.setText(kitap.getYazarAdi());
    	txt_sayfaSayisi.setText(kitap.getSayfaSayisi());
    	txt_tur.setText(kitap.getTuru());
    }

    @FXML
    void txt_kitapAd_Click(ActionEvent event) {

    }

    @FXML
    void txt_sayfaSayisi_Click(ActionEvent event) {

    }

    @FXML
    void txt_tur_Click(ActionEvent event) {

    }

    @FXML
    void txt_yazarAd_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	connectToDatabase();
    	degerleriGetir(tableKitaplar);
    }

}
