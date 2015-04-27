package rs.limundo.model;

import java.sql.Timestamp;

public class Ponude {
private int id_clana,id_aukcije;
private Timestamp vreme;

private int ponudjena_cena;
public Ponude() {
	super();
	// TODO Auto-generated constructor stub
}

public Ponude(int id_clana, int id_aukcije, Timestamp vreme, int ponudjena_cena) {
	super();
	this.id_clana = id_clana;
	this.id_aukcije = id_aukcije;
	this.vreme = vreme;
	this.ponudjena_cena = ponudjena_cena;
}

public int getId_clana() {
	return id_clana;
}
public void setId_clana(int id_clana) {
	this.id_clana = id_clana;
}
public int getId_aukcije() {
	return id_aukcije;
}
public void setId_aukcije(int id_aukcije) {
	this.id_aukcije = id_aukcije;
}

public Timestamp getVreme() {
	return vreme;
}
public void setVreme(Timestamp vreme) {
	this.vreme = vreme;
}

public int getPonudjena_cena() {
	return ponudjena_cena;
}
public void setPonudjena_cena(int ponudjena_cena) {
	this.ponudjena_cena = ponudjena_cena;
}


}
