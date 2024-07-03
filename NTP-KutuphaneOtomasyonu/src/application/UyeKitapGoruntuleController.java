package application;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;


import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UyeKitapGoruntuleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane UyeKitapGor;

    @FXML
    private AnchorPane UyeKitapGoruntule;

    @FXML
    private Button btn_cikis;

    @FXML
    private Button btn_oduncAl;

    @FXML
    private TableColumn<Kitaplar_table, Integer> columnID;

    @FXML
    private TableColumn<Kitaplar_table, String> columnKitapAd;

    @FXML
    private TableColumn<Kitaplar_table, String> columnSayfa;

    @FXML
    private TableColumn<Kitaplar_table, String> columnTur;

    @FXML
    private TableColumn<Kitaplar_table, String> columnYazarAd;

    @FXML
    private TableView<Kitaplar_table> tableKitaplar;

    @FXML
    private TextField txt_uyeAd;

    @FXML
    private TextField txt_uyeSifre;

    @FXML
    private TextField txt_uyeTel;

    @FXML
    private TextField txt_uyeYetki;

    String kAd;
    String sfr;
    String tel;
    int ytki;
    
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
 	  			columnSayfa.setCellValueFactory(new PropertyValueFactory<>("sayfaSayisi"));
 	  			columnTur.setCellValueFactory(new PropertyValueFactory<>("turu"));
 	  			tableKitaplar.setItems(kayitlarliste);
 			} catch(Exception e) {
 				e.printStackTrace();
 			}
 	  	} 
    
    @FXML
    void btn_cikis_Click(ActionEvent event) {
    	Platform.exit(); // Pencereyi kapat
    }

    @FXML
    void btn_oduncAl_Click(ActionEvent event) {

    }

    public void setKullaniciBilgiler(String kullaniciAdi, String sifre , String telefon , int yetki) {
    	kAd=kullaniciAdi;
    	sfr=sifre;
    	tel=telefon;
    	ytki=yetki;  
    	/*
    	txt_uyeAd.setText(kAd);
        txt_uyeSifre.setText(sfr);
        txt_uyeTel.setText(tel);
        txt_uyeYetki.setText(Integer.toString(ytki));
    	txt_uyeAd.setText(kullaniciAdi);
        txt_uyeSifre.setText(sifre);
        txt_uyeTel.setText(telefon);
        txt_uyeYetki.setText(Integer.toString(yetki));*/
        
    }
    
    @FXML
    void tableKitaplar_MouseClick(MouseEvent event) {
    	Kitaplar_table kitap = new Kitaplar_table();
    	kitap = (Kitaplar_table)tableKitaplar.getItems().get(tableKitaplar.getSelectionModel().getFocusedIndex());
    
    }

    @FXML
    void txt_uyeAd_Click(ActionEvent event) {

    }

    @FXML
    void txt_uyeSifre_Click(ActionEvent event) {

    }

    @FXML
    void txt_uyeTel_Click(ActionEvent event) {

    }

    @FXML
    void txt_uyeYetki_Click(ActionEvent event) {

    }




    
    @FXML
    void initialize() {
    	connectToDatabase();
    	degerleriGetir(tableKitaplar);
    }

}
