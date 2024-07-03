package application;

public class Kitaplar_table {
	private  int ID;
    private  String kitapAdi;
    private  String yazarAdi;
    private  String sayfaSayisi;
    private  String turu;
    
    public Kitaplar_table() {
        // Özelliklerin başlangıç değerleri
        this.ID = 0;
        this.kitapAdi = "";
        this.yazarAdi = "";
        this.sayfaSayisi = "";
        this.turu = "";
    }
    
    Kitaplar_table(int ID , String kitapAdi, String yazarAdi , String sayfaSayisi , String turu ) {
        this.ID = ID;
        this.kitapAdi = kitapAdi;
        this.yazarAdi = yazarAdi;
        this.sayfaSayisi = sayfaSayisi ;
        this.turu=turu;
    }


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getKitapAdi() {
		return kitapAdi;
	}


	public void setKitapAdi(String kitapAdi) {
		this.kitapAdi = kitapAdi;
	}


	public String getYazarAdi() {
		return yazarAdi;
	}


	public void setYazarAdi(String yazarAdi) {
		this.yazarAdi = yazarAdi;
	}


	public String getSayfaSayisi() {
		return sayfaSayisi;
	}


	public void setSayfaSayisi(String sayfaSayisi) {
		this.sayfaSayisi = sayfaSayisi;
	}


	public String getTuru() {
		return turu;
	}


	public void setTuru(String turu) {
		this.turu = turu;
	}

	

}
