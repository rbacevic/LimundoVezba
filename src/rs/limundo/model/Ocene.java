package rs.limundo.model;

import java.sql.Timestamp;

public class Ocene {
private int id_ocenjivaca,id_ocenjenog,id_aukcije;
private Timestamp datum;
private int pozitivna_ocena,negativna_ocena;
private String komentar;
public Ocene() {
	super();
	// TODO Auto-generated constructor stub
}


public Ocene(int id_ocenjivaca, int id_ocenjenog, int id_aukcije,
		Timestamp datum, int pozitivna_ocena, int negativna_ocena,
		String komentar) {
	super();
	this.id_ocenjivaca = id_ocenjivaca;
	this.id_ocenjenog = id_ocenjenog;
	this.id_aukcije = id_aukcije;
	this.datum = datum;
	this.pozitivna_ocena = pozitivna_ocena;
	this.negativna_ocena = negativna_ocena;
	this.komentar = komentar;
}


public int getId_aukcije() {
	return id_aukcije;
}
public void setId_aukcije(int id_aukcije) {
	this.id_aukcije = id_aukcije;
}
public int getId_ocenjivaca() {
	return id_ocenjivaca;
}
public void setId_ocenjivaca(int id_ocenjivaca) {
	this.id_ocenjivaca = id_ocenjivaca;
}
public int getId_ocenjenog() {
	return id_ocenjenog;
}
public void setId_ocenjenog(int id_ocenjenog) {
	this.id_ocenjenog = id_ocenjenog;
}
public Timestamp getDatum() {
	return datum;
}
public void setDatum(Timestamp datum) {
	this.datum = datum;
}
public int getPozitivna_ocena() {
	return pozitivna_ocena;
}
public void setPozitivna_ocena(int pozitivna_ocena) {
	this.pozitivna_ocena = pozitivna_ocena;
}
public int getNegativna_ocena() {
	return negativna_ocena;
}
public void setNegativna_ocena(int negativna_ocena) {
	this.negativna_ocena = negativna_ocena;
}
public String getKomentar() {
	return komentar;
}
public void setKomentar(String komentar) {
	this.komentar = komentar;
}

}
