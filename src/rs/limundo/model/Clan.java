package rs.limundo.model;

import java.sql.Timestamp;

public class Clan {
private  int id_clana;
private String ime,prezime,username,password,email,adresa,broj,drzava,opstina,mobilni_telefon,datum_rodjenja,pol,administrator;
private Timestamp vreme_upisa;
private int ban;

public Clan() {
	super();

}
public Clan(String ime, String prezime, String username,
		String password, String email, String adresa, String broj,
		String drzava, String opstina, String mobilni_telefon,
		String datum_rodjenja, String pol) {
	super();
	this.ime = ime;
	this.prezime = prezime;
	this.username = username;
	this.password = password;
	this.email = email;
	this.adresa = adresa;
	this.broj = broj;
	this.drzava = drzava;
	this.opstina = opstina;
	this.mobilni_telefon = mobilni_telefon;
	this.datum_rodjenja = datum_rodjenja;
	this.pol = pol;
	
}
public int getId_clana() {
	return id_clana;
}
public void setId_clana(int id_clana) {
	this.id_clana = id_clana;
}
public String getIme() {
	return ime;
}
public void setIme(String ime) {
	this.ime = ime;
}
public String getPrezime() {
	return prezime;
}
public void setPrezime(String prezime) {
	this.prezime = prezime;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAdresa() {
	return adresa;
}
public void setAdresa(String adresa) {
	this.adresa = adresa;
}
public String getBroj() {
	return broj;
}
public void setBroj(String broj) {
	this.broj = broj;
}
public String getDrzava() {
	return drzava;
}
public void setDrzava(String drzava) {
	this.drzava = drzava;
}
public String getOpstina() {
	return opstina;
}
public void setOpstina(String opstina) {
	this.opstina = opstina;
}
public String getMobilni_telefon() {
	return mobilni_telefon;
}
public void setMobilni_telefon(String mobilni_telefon) {
	this.mobilni_telefon = mobilni_telefon;
}
public String getDatum_rodjenja() {
	return datum_rodjenja;
}
public void setDatum_rodjenja(String datum_rodjenja) {
	this.datum_rodjenja = datum_rodjenja;
}
public String getPol() {
	return pol;
}
public void setPol(String pol) {
	this.pol = pol;
}
public String getAdministrator() {
	return administrator;
}
public void setAdministrator(String administrator) {
	this.administrator = administrator;
}
public Timestamp getVreme_upisa() {
	return vreme_upisa;
}
public void setVreme_upisa(Timestamp vreme_upisa) {
	this.vreme_upisa = vreme_upisa;
}
public int getBan() {
	return ban;
}
public void setBan(int ban) {
	this.ban = ban;
}


}
