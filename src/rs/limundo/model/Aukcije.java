package rs.limundo.model;



import java.sql.Time;
import java.sql.Timestamp;

public class Aukcije {
private int id_aukcije;
private Timestamp trajanje;
private String ponavljanje;
private String nacin_dostave,nacin_placanja;
private int br_ponuda;
private int id_clana,cena;
private int zabrana;
private String naziv_predmeta,kategorija,stanje_predmeta,opis;
private int ban;

private String slika;
public int getBr_ponuda() {
	return br_ponuda;
}
public void setBr_ponuda(int br_ponuda) {
	this.br_ponuda = br_ponuda;
}


public Aukcije() {
	super();
	// TODO Auto-generated constructor stub
}
public Aukcije(Timestamp trajanje, String ponavljanje,
		String nacin_dostave, String nacin_placanja, int cena,String kategorija, String stanje_predmeta,
		String opis,String slika) {
	super();
	
	this.trajanje = trajanje;
	this.ponavljanje = ponavljanje;
	this.nacin_dostave = nacin_dostave;
	this.nacin_placanja = nacin_placanja;

	this.kategorija = kategorija;
	this.stanje_predmeta = stanje_predmeta;
	this.cena = cena;
	this.opis = opis;
	this.slika=slika;
}
public int getId_aukcije() {
	return id_aukcije;
}
public void setId_aukcije(int id_aukcije) {
	this.id_aukcije = id_aukcije;
}
public Timestamp getTrajanje() {
	return trajanje;
}
public void setTrajanje(Timestamp trajanje) {
	this.trajanje = trajanje;
}
public String getPonavljanje() {
	return ponavljanje;
}
public void setPonavljanje(String ponavljanje) {
	this.ponavljanje = ponavljanje;
}
public String getNacin_dostave() {
	return nacin_dostave;
}
public void setNacin_dostave(String nacin_dostave) {
	this.nacin_dostave = nacin_dostave;
}
public String getNacin_placanja() {
	return nacin_placanja;
}
public void setNacin_placanja(String nacin_placanja) {
	this.nacin_placanja = nacin_placanja;
}
public int getBan() {
	return ban;
}
public void setBan(int ban) {
	this.ban = ban;
}


public int getId_clana() {
	return id_clana;
}
public void setId_clana(int id_clana) {
	this.id_clana = id_clana;
}

public String getNaziv_predmeta() {
	return naziv_predmeta;
}
public void setNaziv_predmeta(String naziv_predmeta) {
	this.naziv_predmeta = naziv_predmeta;
}
public String getKategorija() {
	return kategorija;
}
public void setKategorija(String kategorija) {
	this.kategorija = kategorija;
}
public String getStanje_predmeta() {
	return stanje_predmeta;
}
public void setStanje_predmeta(String stanje_predmeta) {
	this.stanje_predmeta = stanje_predmeta;
}
public int getCena() {
	return cena;
}
public void setCena(int pocetna_cena) {
	this.cena = pocetna_cena;
}
public String getOpis() {
	return opis;
}
public void setOpis(String opis) {
	this.opis = opis;
}

public String getSlika() {
	return slika;
}

public void setSlika(String slika) {
	this.slika = slika;
}
public int getZabrana() {
	return zabrana;
}
public void setZabrana(int zabrana) {
	this.zabrana = zabrana;
}






}
