package application;

public class Uyeler_table {
	private  int ID;
    private  String adSoyad;
    private  String sifre;
    private  String telefon;
    private  int yetki;
    
    Uyeler_table(){
    	this.yetki=0;
    }

    Uyeler_table(int ID , String adSoyad, String sifre , String telefon , int yetki ) {
        this.ID = ID;
        this.adSoyad = adSoyad;
        this.sifre = sifre;
        this.telefon = telefon ;
        this.yetki=yetki;
    }

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public int getYetki() {
		return yetki;
	}

	public void setYetki(int yetki) {
		this.yetki = yetki;
	}

}
