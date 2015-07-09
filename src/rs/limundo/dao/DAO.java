package rs.limundo.dao;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;





import org.json.JSONArray;
import org.apache.tomcat.util.threads.ResizableExecutor;



/*import org.codehaus.jettison.json.JSONArray;
*/
import rs.limundo.model.Aukcije;
import rs.limundo.model.Clan;
import rs.limundo.util.ToJSON;
import rs.limundo.util.ToJSON1;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;
public class DAO {
      private DataSource ds;
      private Connection con;
       private ToJSON converter = new ToJSON();
       private ToJSON1 converter1 = new ToJSON1();
//Ranko - finalna
	/*ZA PRIJAVU*/private static String SELECTPRIJAVACLANA = "SELECT * FROM clanovi WHERE username= ?";
	private static String SELECTPRIJAVADMIN = "SELECT * FROM administrator WHERE username= ?";
	private static String SELECTCLANA = "SELECT * FROM clanovi";
	private static String SELECTAUKCIJU = "SELECT * FROM aukcije";
	private static String SELECTAUKCIJUBYUSERID = "SELECT cena FROM aukcije WHERE id_aukcije=?";
	private static String SELECTPONUDU = "SELECT * FROM ponude WHERE id_aukcije=? ORDER BY ponudjena_cena desc, vreme desc";
	private static String SELECTUSERNAMEbyIDOCENJIVACA="SELECT username FROM clanovi , ocene  WHERE id_clana=?";
	private static String SELECTOCENEByIDOCENJENOG="SELECT * FROM ocene WHERE id_ocenjenog= ?";
	private static String POZITIVNEOCENEbyIDOCENJENOG = "Select count(pozitivna_ocena) AS "+"pozitivne"+" from ocene WHERE id_ocenjenog=? AND pozitivna_ocena=1";
	private static String NEGATIVNEOCENEbyIDOCENJENOG = "Select count(negativna_ocena) AS "+"negativne"+" from ocene WHERE id_ocenjenog=? AND negativna_ocena=1";
	private static String DODAJCLANA= "INSERT INTO clanovi(ime,prezime, email, username, password, adresa, broj, drzava, opstina, datum_rodjenja, pol, mobilni_telefon,administrator,vreme_upisa) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";
	private static String DODAJAUKCIJU="INSERT INTO aukcije (trajanje, nacin_dostave, nacin_placanja, id_clana, naziv_predmeta, kategorija, stanje_predmeta, cena, slika, opis) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static String DODAJPONUDU="INSERT INTO ponude(id_clana, id_aukcije, vreme,ponudjena_cena) VALUES (?,?,CURRENT_TIMESTAMP,?)";
	private static String DODAJOCENU ="INSERT INTO ocene(id_ocenjivaca, id_ocenjenog, datum, pozitivna_ocena, negativna_ocena, komentar) VALUES (?,?,?,?,?,?)";
	private static String IZBRISICLANA ="DELETE FROM clanovi WHERE id_clana=?";
	private static String IZBRISIAUKCIJU ="DELETE FROM aukcije WHERE id_aukcije=?";
	private static String UPDATECLANA="UPDATE clanovi SET ime=?,prezime=?,password=?,adresa=?,broj=?,drzava=?,opstina=?,mobilni_telefon=?,email=?,administrator=?,ban=? WHERE id_clana=?";
	private static String UPDATEAUKCIJE="UPDATE aukcije SET ban=? WHERE id_aukcije=?";
	private static String UPDATECENEAUKCIJE= "UPDATE aukcije SET cena=? WHERE id_aukcije=?";	
	
	// DEFINICIJA KONSTRUKTORA ZA PODESAVNJE KONEKCIJE – UVEK ISTO
	//Ranko finalna
	
	//Aco
	//svi clanovi
	private static String SELECTPRIKAZCLANOVA = "SELECT id_clana, username, ime, prezime FROM clanovi";
	//jedan clan
	private static String SELECTJEDANCLAN = "SELECT id_clana, username, ime, prezime FROM clanovi WHERE username=?";

	//sve aukcije 
	private static String SELECTPRIKAZSVEAUKCIJE="SELECT id_aukcije, naziv_predmeta, cena, id_clana,slika from aukcije ORDER BY id_aukcije DESC";
	private static String SELECTIMEPRODAVCA="SELECT username FROM clanovi WHERE id_clana=?";
	
	
	
	//jedna aukcija
	private static String SELECTJEDNAAUKCIJA="SELECT id_aukcije, naziv_predmeta, cena,slika FROM aukcije WHERE naziv_predmeta=?";
	private static String SELECTPRODAVAC="SELECT c.username, c.id_clana, a.id_clana FROM clanovi c, aukcije a WHERE c.id_clana=a.id_clana AND a.naziv_predmeta=?";
	
	//ranko dodato
	
	private static String SELECTCLANABYID= "SELECT * FROM clanovi WHERE id_clana=?";
	private static String SELECTCLANALOGIN= "SELECT * FROM clanovi WHERE username=? and password=?";
	private static String SELECTMOJEAUKCIJE="SELECT id_aukcije,naziv_predmeta, slika,cena, kategorija, trajanje,slika FROM aukcije WHERE id_clana=?";
	
	//aco dodato
	private static String SELECTKATEGORIJA="SELECT * FROM aukcije WHERE LOWER(kategorija)=?";
	
	//aco dodato-na vrednost 
	
	private static String SELECTNAZIV="SELECT a.naziv_predmeta,a.slika FROM aukcije a, ponude p WHERE naziv_predmeta=? AND a.id_aukcije=p.id_aukcije";
	private static String SELECTVREMEPONUDE="SELECT p.vreme,p.vrednost_ponude FROM ponude p, clanovi c, aukcije a WHERE p.id_clana = c.id_clana AND c.username=? AND a.naziv_predmeta=? AND a.id_aukcije=p.id_aukcije";
	private static String SELECTUSERNAMEPONUDE="SELECT c.username FROM clanovi c, aukcije a, ponude p WHERE a.id_aukcije=p.id_aukcije AND p.id_clana=c.id_clana AND a.id_aukcije=?";
	
	private static String SELECTPRODAVCAPONUDE="SELECT c.username FROM clanovi c, aukcije a WHERE c.id_clana=a.id_clana AND a.id_aukcije=?";
	private static String SELECTVREMETRAJANJA="SELECT trajanje FROM aukcije WHERE id_aukcije=?";
	//linkovanje-ponuda
	
	private static String SELECTPREDMET="SELECT naziv_predmeta,slika, id_aukcije,id_clana, trajanje, cena, nacin_dostave, nacin_placanja, stanje_predmeta, opis,ban FROM aukcije WHERE naziv_predmeta=?";
	private static String SELECTCOUNTPONUDE="SELECT COUNT(id_aukcije) "+"ukupno"+" FROM ponude WHERE id_aukcije=?";
	
	//r dodato
		private static String SELECTEMAILBYEMAIL =  "SELECT email FROM clanovi WHERE email = ? ";
		private static String SELECTEUSERNAMEBYUSERNAME =  "SELECT username FROM clanovi WHERE username = ? ";
		private static String SELECTLASTIDAUKCIJE= "SELECT max(id_aukcije) AS poslednja FROM aukcije";
		private static String DODAJSLIKUNAAUKCIJU="UPDATE aukcije SET slika=? WHERE id_aukcije=? ";
		private static String SELECTRANDOMAUKCIJE="SELECT * FROM aukcije WHERE id_aukcije BETWEEN RAND()*10 AND rand()*100 OR id_aukcije BETWEEN RAND()*100 AND RAND()*1000 ORDER BY trajanje DESC LIMIT 10";
		private static String SELECTAUKCIJUBYID="SELECT * FROM aukcije WHERE id_aukcije = ?";
		
		private static String MAXPONUDEPOAUKCIJI="SELECT id_clana, id_aukcije, vreme, max(ponudjena_cena) AS ponudjena_cena FROM ponude group by id_aukcije";
		private static String OCENI= "INSERT INTO ocene(id_ocenjivaca, id_ocenjenog,id_aukcije, datum, pozitivna_ocena, negativna_ocena, komentar) VALUES (?,?,?,CURRENT_TIMESTAMP,?,?,?)";
		private static String SELECTOCENE= "SELECT id_ocene, id_ocenjivaca, id_ocenjenog, id_aukcije, datum, pozitivna_ocena, negativna_ocena, komentar FROM ocene WHERE id_aukcije=?";
		public DAO() throws SQLException{
	try {
		InitialContext cxt = new InitialContext();
		if ( cxt == null ) { 
		} 
		ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/mysql" ); 
		con=ds.getConnection();
		if ( ds == null ) { 
		} 		
		} catch (NamingException e) {
		}
	}
		

		public JSONArray selectSveKategorije() throws Exception{
			PreparedStatement pstm=null;
			ResultSet rs= null;
			 JSONArray json = new JSONArray();
		
			
			try {
				pstm= con.prepareStatement("select distinct kategorija from aukcije");
				
				rs=pstm.executeQuery();
				
				if(rs!=null){
					json= converter.toJSONArray(rs);
					
					}
					return json;
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				terminate();
			}
			
			return null;
			
			
		}
		
		public JSONArray selectOcene(int id_aukcije) throws Exception{
			PreparedStatement pstm=null;
			ResultSet rs= null;
			 JSONArray json = new JSONArray();
		
			
			try {
				pstm= con.prepareStatement(SELECTOCENE);
				pstm.setInt(1, id_aukcije);
				rs=pstm.executeQuery();
				
				if(rs!=null){
					json= converter.toJSONArray(rs);
					
					}
					return json;
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				terminate();
			}
			
			return null;
			
			
		}
		
	//nisam	
	public void oceni(int idOcenjivaca,int idOcenjenog,int idAukcije,int pozitivna,int negativna,String komentar){
		PreparedStatement pstm=null;
		
		try {
			pstm=con.prepareStatement(OCENI);
			pstm.setInt(1, idOcenjivaca);
			pstm.setInt(2, idOcenjenog);
			pstm.setInt(3, idAukcije);
			pstm.setInt(4, pozitivna);
			pstm.setInt(5, negativna);
			pstm.setString(6, komentar);
			pstm.execute();
		} catch (SQLException e) {
			// TODO: handle exception
		e.printStackTrace();
		}finally{
			terminate();
		}
		
	}
	//nisam
	public JSONArray maxPonudePoAukciji() throws Exception{
		PreparedStatement pstm=null;
		ResultSet rs= null;
		 JSONArray json = new JSONArray();
		
		try {
			pstm= con.prepareStatement(MAXPONUDEPOAUKCIJI);
			rs=pstm.executeQuery();
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
				}
				return json;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		
		return null;
	}

	public JSONArray randomAukcije() throws Exception{
		PreparedStatement pstm=null;
		ResultSet rs= null;
		 JSONArray json = new JSONArray();
		
		try {
			pstm= con.prepareStatement(SELECTRANDOMAUKCIJE);
			rs=pstm.executeQuery();
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
				}
				return json;
				
			
			
			
		} catch (SQLException e) {
e.printStackTrace();		
}
		finally{
	terminate();
}
		return null;
	}
	
	
	
	public void updateAukcije(int ban,int id_aukcije){
		PreparedStatement pstm=null;
		
		try {
			pstm=con.prepareStatement(UPDATEAUKCIJE);
			
			pstm.setInt(1, ban);
			pstm.setInt(2, id_aukcije);
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
	}
	
	
	public int izbrisiAukciju(int id_aukcije){
		PreparedStatement pstm = null;
		 try {
				//con = ds.getConnection();
				pstm = con.prepareStatement(IZBRISIAUKCIJU);
				pstm.setInt(1, id_aukcije);
				pstm.execute();
		 } catch (SQLException e) {
				e.printStackTrace();
				return 500;
			}finally{
				terminate();
			}
		 return 200;
	}
	
	
	
	public JSONArray selectAukcijeByID(int id_aukcije) throws Exception{
		//Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			 JSONArray json = new JSONArray();
			
		
	            try {
				//con = ds.getConnection();
				pstm = con.prepareStatement(SELECTAUKCIJUBYID);
				pstm.setInt(1, id_aukcije);
				 
				
				rs= pstm.executeQuery();


				rs = pstm.getResultSet();

				if(rs!=null){
					json= converter.toJSONArray(rs);
					
					}
					return json;
		
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				terminate();
			}
		
			
			return null; 
		}
	
	
	public JSONArray selectCountPonude(int id_aukcije) throws Exception{
		//Connection con=null;
		PreparedStatement pstm = null; 
		ResultSet rs=null;
		 JSONArray json = new JSONArray();
		int p=0;
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(SELECTCOUNTPONUDE);
			
			pstm.setInt(1, id_aukcije);
			pstm.execute();
			rs=pstm.getResultSet();
		
			
			rs= pstm.executeQuery();


			rs = pstm.getResultSet();

			if(rs!=null){
				json= converter.toJSONArray(rs);
				
				}
				return json;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		
		return null;
	}
	
		public JSONArray IdZadnjeUneteAukcije() throws Exception{
			PreparedStatement pstm = null; 
			ResultSet rs=null;
			 JSONArray json = new JSONArray();
			
			int id=0;
			try {
				//con=ds.getConnection();
				pstm=con.prepareStatement(SELECTLASTIDAUKCIJE);
				rs = pstm.executeQuery();

				if(rs!=null){
					json= converter.toJSONArray(rs);
					
					}
					return json;
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				terminate();
			}
			
			return null;
		}
			//nisam
		public void dodavanjeSlikeNaAukciju(String slika, int id_aukcije){
			PreparedStatement pstm = null; 
			ResultSet rs=null;
			try {
				//con=ds.getConnection();
				pstm=con.prepareStatement(DODAJSLIKUNAAUKCIJU);
				
				pstm.setString(1, slika);
				pstm.setInt(2, id_aukcije);
				pstm.execute();
				
			
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				terminate();
			}
			
			
		
			
		}
	
		
		public JSONArray selectVremeTrajanja(int id_aukcije) throws Exception{
			//Connection con=null;
			PreparedStatement pstm = null; 
			ResultSet rs=null;
			 JSONArray json = new JSONArray();
			try {
				//con=ds.getConnection();
				pstm=con.prepareStatement(SELECTVREMETRAJANJA);
				
				pstm.setInt(1, id_aukcije);
				
				rs=pstm.executeQuery();
				
			
				if(rs!=null){
					json= converter.toJSONArray(rs);
					
					}
					return json;
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				terminate();
			}
			return null;
		}
		
		public JSONArray selectProdavacPonude(int id_aukcije) throws Exception{
			//Connection con=null;
			PreparedStatement pstm = null; 
			ResultSet rs=null;
			 JSONArray json = new JSONArray();
			try {
				//con=ds.getConnection();
				pstm=con.prepareStatement(SELECTPRODAVCAPONUDE);
				
				pstm.setInt(1, id_aukcije);
				
				rs=pstm.executeQuery();
				if(rs!=null){
					json= converter.toJSONArray(rs);
					
					}
					return json;
				
			
			
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				terminate();
			}
			
			return null;
		}
		public JSONArray selectPredmet(String naziv_predmeta) throws Exception{
			//Connection con=null;
			PreparedStatement pstm = null; 
			ResultSet rs=null;
			 JSONArray json = new JSONArray();
			try {
				//con=ds.getConnection();
				pstm=con.prepareStatement(SELECTPREDMET);
				
				pstm.setString(1, naziv_predmeta);
				
				rs=pstm.executeQuery();
			
				
				if(rs!=null){
					json= converter.toJSONArray(rs);
					
					}
					return json;
					
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				terminate();
			}
			
			return null;
		}
		//lista username-ova za spisak svih ponuda za predmet
		public JSONArray selectUsernamePonude(int id_aukcije) throws Exception{
			//Connection con=null;
			PreparedStatement pstm = null; 
			ResultSet rs=null;
			 JSONArray json = new JSONArray();
			
			try {
				//con=ds.getConnection();
				pstm=con.prepareStatement(SELECTUSERNAMEPONUDE);
				
				pstm.setInt(1, id_aukcije);
				
				rs=pstm.executeQuery();
				
				
				if(rs!=null){
					json= converter.toJSONArray(rs);
					
					}
					return json;
			
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				terminate();
			}
			return null;
		}
	
	public JSONArray selectVremePonude(String naziv_predmeta, String username) throws Exception{
		//Connection con=null;
		PreparedStatement pstm = null; 
		ResultSet rs=null;
		 JSONArray json = new JSONArray();
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(SELECTVREMEPONUDE);
			
			pstm.setString(1, naziv_predmeta);
			pstm.setString(2, username);
			
			rs=pstm.executeQuery();
			
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
				}
				return json;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			terminate();
		}
		return null;
	}
	
	public JSONArray selectPonude(int id_aukcije) throws Exception{
		PreparedStatement pstm = null; 
		ResultSet rs=null;
		 JSONArray json = new JSONArray();
		
		
		try {
			pstm=con.prepareStatement(SELECTPONUDU);
			pstm.setInt(1,id_aukcije);
			
			rs=pstm.executeQuery();
			
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
				}
				return json;
			
			
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		return null;
	}
	
	//nisam
	public JSONArray selectNaziv(String naziv_predmeta) throws Exception{
		//Connection con=null;
		PreparedStatement pstm = null; 
		ResultSet rs=null;
		 JSONArray json = new JSONArray();
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(SELECTNAZIV);
			
			pstm.setString(1, naziv_predmeta);
	
			rs=pstm.executeQuery();
			
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
				}
				return json;
		
			
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			terminate();
		}
		return null;
	}
	
	
	
	
	public JSONArray selectKategorije(String kategorija) throws Exception{
		//Connection con=null;
		PreparedStatement pstm = null; 
		ResultSet rs=null;
		 JSONArray json = new JSONArray();
	
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(SELECTKATEGORIJA);
			
			pstm.setString(1, kategorija);

			rs=pstm.executeQuery();
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
				}
				return json;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		
		return null;
	}
	
	
	
	//nisam
	public JSONArray mojeAukcijePonude(int id_clana) throws Exception{
		//Connection con=null;
		PreparedStatement pstm = null; 
		ResultSet rs=null;
		 JSONArray json = new JSONArray();
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(SELECTMOJEAUKCIJE);
			
			pstm.setInt(1, id_clana);
			
			rs=pstm.executeQuery();
			
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
				}
				return json;
			
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		
		return null;
	}
	
	
	public int  izbrisiClana(int id_clana){
		//Connection con=null;
		PreparedStatement pstm= null;
		try{
			//con=ds.getConnection();
			pstm=con.prepareStatement(IZBRISICLANA);
			pstm.setInt(1, id_clana);
			pstm.execute();
		}catch(SQLException e ){
			e.printStackTrace();
			return 500;
		}finally{
			terminate();
		}
		return 200;
		
		
		
	}
	public JSONArray selectClanByUserId(int id_clana) throws Exception{
		//Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs= null;
		 JSONArray json = new JSONArray();
		try {
			//con= ds.getConnection();
			pstm=con.prepareStatement(SELECTCLANABYID);
			pstm.setInt(1, id_clana);
			
			rs=pstm.executeQuery();
			
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
				}
				return json;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		
		return null;
	}

	
	

	public JSONArray selectClanLogIn(Clan c) throws Exception{
		//Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs= null;
		 JSONArray json = new  JSONArray();
		try {
			//con= ds.getConnection();
			pstm=con.prepareStatement(SELECTCLANALOGIN);
			pstm.setString(1, c.getUsername());
			pstm.setString(2,c.getPassword());
			
			rs=pstm.executeQuery();
			
			if(rs!=null){
				json= converter1.toJSONArray(rs);
				System.out.println(json);
				}
				return json;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		
		return null;
	}
	
	
	//kraj
	
	
	
	//pocetak
	
	
	
//nisam
	
	public JSONArray selectJedanAukcija(String naziv_predmeta) throws Exception{
		//Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		 JSONArray json = new JSONArray(); 
				
            try {
            	//con=ds.getConnection();
    			pstm=con.prepareStatement(SELECTJEDNAAUKCIJA);
    			pstm.setString(1, naziv_predmeta);
    		
    			
    			rs=pstm.executeQuery();
    			if(rs!=null){
    			json = converter.toJSONArray(rs);
    			
    			}

			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
	
		return null;
		
	}
	//nisam
	public JSONArray selectImeJednogProdavca(String naziv_predmeta) throws Exception{
		//Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
	
		 JSONArray json = new JSONArray();
				
            try {
			//con = ds.getConnection();
			pstm = con.prepareStatement(SELECTPRODAVAC);

			pstm.setString(1, naziv_predmeta);
			 
		


			
			
			rs=pstm.executeQuery();
			if(rs!=null){
			json = converter.toJSONArray(rs);
			
			}
			

			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
            finally{
				terminate();
			}
		
	return null;
	}
	
	
	
	//nisam
	public JSONArray selectImeProdavca(int id_clana) throws Exception{
//Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String username=null;
		 JSONArray json = new JSONArray();
		
		
				
            try {
			//con = ds.getConnection();
			pstm = con.prepareStatement(SELECTIMEPRODAVCA);
			pstm.setInt(1, id_clana);
			 
			
			


	

			rs=pstm.executeQuery();
			if(rs!=null){
			json = converter.toJSONArray(rs);
			
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
            finally{
				terminate();
			}
		
	return null;
	}
	
	
	
	//nisam
	public JSONArray selectPrikazSveAukcije() throws Exception{
		//Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		 JSONArray json = new JSONArray();
	
				
            try {
			//con = ds.getConnection();
			pstm = con.prepareStatement(SELECTPRIKAZSVEAUKCIJE);

			 
		


			rs = pstm.executeQuery();
			if(rs!=null){
			json= converter.toJSONArray(rs);
			
			}
			return json;
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
            finally{
				terminate();
			}
		
            return null;
	}
	
	
	public JSONArray selectClan(String username) throws Exception{
		//Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		JSONArray json = new JSONArray();
				
            try {
            	//con=ds.getConnection();
    			pstm=con.prepareStatement(SELECTJEDANCLAN);
    			pstm.setString(1, username);
    		
    			rs=pstm.executeQuery();
    			if(rs!=null){
    			json= converter.toJSONArray(rs);
    			
    			
    			}
    			return json;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
	
		return null;
		
	}
	//nisam
	public JSONArray selectPrikazSvihClanova() throws Exception{
		//Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		JSONArray json = new JSONArray();
				
            try {
			//con = ds.getConnection();
			pstm = con.prepareStatement(SELECTPRIKAZCLANOVA);

			 
			
		


			rs = pstm.executeQuery();
			if(rs!=null){
			json= converter.toJSONArray(rs);
			}
			return json;
			
					

			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		
		
		return null; 
	}
	
	//kraj
	
	//Ranko finalna
	public void dodajClana(Clan c){
		//Connection con=null;
		PreparedStatement pstm = null;
		
		try {
			
			//con=ds.getConnection();
			pstm=con.prepareStatement(DODAJCLANA);
			
			pstm.setString(1, c.getIme());
			pstm.setString(2, c.getPrezime());
			pstm.setString(3, c.getEmail());
			pstm.setString(4, c.getUsername());
			pstm.setString(5, c.getPassword());
			pstm.setString(6, c.getAdresa());
			pstm.setString(7, c.getBroj());
			pstm.setString(8, c.getDrzava());
			pstm.setString(9, c.getOpstina());
			pstm.setString(10, c.getDatum_rodjenja());
			pstm.setString(11, c.getPol());
			pstm.setString(12, c.getMobilni_telefon());
			pstm.setString(13, "NE");
			
			pstm.execute();
		
			} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				terminate();
			}
		}
		
		
		
		
		
	//nisam
	public void izmenaProfila(String ime, String prezime,String drzava, String opstina, String adresa,String broj,String mobilni,String email,String sifra,int id_clana,String administrator,int ban){
		//Connection con=null;
		PreparedStatement pstm=null;
		
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(UPDATECLANA);
			pstm.setString(1, ime);
			pstm.setString(2, prezime);
			pstm.setString(3, sifra);
			pstm.setString(4, adresa);
			pstm.setString(5, broj);
			pstm.setString(6, drzava);
			pstm.setString(7, opstina);
			pstm.setString(8, mobilni);
			pstm.setString(9, email);
			
			pstm.setString(10, administrator);
			pstm.setInt(11, ban);
			pstm.setInt(12,id_clana);
			
			pstm.execute();
			
			
			
		 } catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		
		
	
		
	}
		//nisam
public int dodajAuckiju(Aukcije a){
		//Connection con=null;
		PreparedStatement pstm = null;
		
		try {
			
			//con=ds.getConnection();
			pstm=con.prepareStatement(DODAJAUKCIJU);
			
			pstm.setTimestamp(1, a.getTrajanje());
			
			pstm.setString(2, a.getNacin_dostave());
			pstm.setString(3, a.getNacin_placanja());
			
			pstm.setInt(4,a.getId_clana());
			pstm.setString(5, a.getNaziv_predmeta());
			pstm.setString(6, a.getKategorija());
			pstm.setString(7, a.getStanje_predmeta());
			pstm.setInt(8, a.getCena());
			pstm.setString(9, a.getSlika());
			pstm.setString(10,a.getOpis());
			
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return 500;
		}finally{
				terminate();
			}
		
		return 200;
	}
	//nisam
	public int pozitivneOcene(int id_ocenjenog){
		//Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int broj=0;
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(POZITIVNEOCENEbyIDOCENJENOG);
			pstm.setInt(1, id_ocenjenog);
			pstm.execute();
			rs=pstm.getResultSet();
			if(rs.next()){
				broj=rs.getInt("pozitivne");
				
				
			}
			return broj;
		 } catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
	
		
		return 0;
		
	}
	//nisam
	public int negativneOcene(int id_ocenjenog){
		//Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int broj=0;
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(NEGATIVNEOCENEbyIDOCENJENOG);
			pstm.setInt(1, id_ocenjenog);
			pstm.execute();
			rs=pstm.getResultSet();
			if(rs.next()){
			 broj=rs.getInt("negativne");
				
				
			}
			return broj;
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			terminate();
		}
		
		return 0;
		
	}	
//nisam
	public JSONArray prikaziOcene(int id_ocenjenog) throws Exception{
		//Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		JSONArray json = new JSONArray();
				
            try {
			//con = ds.getConnection();
			pstm = con.prepareStatement(SELECTOCENEByIDOCENJENOG);
			pstm.setInt(1, id_ocenjenog);
			 
			
		


			rs = pstm.executeQuery();
			if(rs!=null)
			{
				json = converter.toJSONArray(rs);
				
			}
			return json;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
	
		
		return null; 
	}
	//nisam
	public JSONArray ocenjivac(int id_ocenjivaca) throws Exception{
		//Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		JSONArray json = new JSONArray();
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(SELECTUSERNAMEbyIDOCENJIVACA);
			pstm.setInt(1, id_ocenjivaca);
			
			rs=pstm.executeQuery();
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
			}
			return json;
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			terminate();
		}
		
		return null;
	}
	
	public JSONArray selectClanByUsername(String username) throws Exception{
	//Connection con=null;
		PreparedStatement pstm= null;
		ResultSet rs=null;
		
		JSONArray json = new JSONArray();
		
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(SELECTPRIJAVACLANA);
			pstm.setString(1, username);
			
			rs=pstm.executeQuery();
			if(rs!=null){
				json= converter.toJSONArray(rs);
				
			}
			return json;
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			terminate();
		}
		
		return null;
		
	}
	
	
	public JSONArray selectSveClanove() throws Exception{
		//Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		JSONArray json = new JSONArray();
				
            try {
			//con = ds.getConnection();
			pstm = con.prepareStatement(SELECTCLANA);

			 
			
		


			rs = pstm.executeQuery();

			if(rs!=null){
				
				json= converter.toJSONArray(rs);
				
			}
	return json;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
	
		
		return null; 
	}
	public JSONArray selectSveAukcije() throws Exception{
	//Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		JSONArray json = new JSONArray();
            try {
			//con = ds.getConnection();
			pstm = con.prepareStatement(SELECTAUKCIJU);

			 
		

			rs = pstm.executeQuery();
			
			if(rs!=null){
				json = converter.toJSONArray(rs);
			}
			return json;
		} catch (SQLException e) {
			e.printStackTrace();
		}
            finally{
				terminate();
			}
		
		return null; 
	}
	//ranko finalna
	//nisam
	public JSONArray emailByEmail(String email) throws Exception{
		//Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		JSONArray json = new JSONArray();	
				try {
					//con=ds.getConnection();
					pstm=con.prepareStatement(SELECTEMAILBYEMAIL);
					pstm.setString(1, email);
				
					rs=pstm.executeQuery();
					if(rs!=null){
					json = converter.toJSONArray(rs);
					
					}
					return json;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally{
					terminate();
				}
			
			return null;	
		}
	//nisam
	public JSONArray userByUsername(String username) throws Exception{
		//Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		JSONArray json = new JSONArray();	
		try {
			//con=ds.getConnection();
			pstm=con.prepareStatement(SELECTEMAILBYEMAIL);
			pstm.setString(1, username);
		
			rs=pstm.executeQuery();
			if(rs!=null){
			json = converter.toJSONArray(rs);
			
			}
			return json;
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					terminate();
				}
		
			
			return null;	
		}
	//nisam
	public int selectCenaAukcijeById(int id_aukcije){
		//Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs= null;
		int cena= 0;		
		try {
			//con=ds.getConnection();
			pstm= con.prepareStatement(SELECTAUKCIJUBYUSERID);
			pstm.setInt(1, id_aukcije);
			pstm.execute();
			
			rs=pstm.getResultSet();
					
				if(rs.next()){
					cena=rs.getInt("cena");
					return cena;
				}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			terminate();
		}
		
		return 0;
	}
	//nisam
	public void updateCene(int cena,int id_aukcije){
		//Connection con=null;
		PreparedStatement pstm=null;
		
				
		try {
			//con=ds.getConnection();
			pstm= con.prepareStatement(UPDATECENEAUKCIJE);
			pstm.setInt(1, cena);
			pstm.setInt(2, id_aukcije);
			pstm.execute();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
			
	}
	//nisam
	public void dodajPonudu(int id_aukcije, int id_clana,int ponudjena_cena){
		//Connection con=null;
		PreparedStatement pstm=null;
		
				
		try {
			//con=ds.getConnection();
			pstm= con.prepareStatement(DODAJPONUDU);
			pstm.setInt(1, id_clana);
			pstm.setInt(2, id_aukcije);
			pstm.setInt(3, ponudjena_cena);
			pstm.execute();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			terminate();
		}
		
				
	}
	
	
	
	public void terminate(){
		try{ 
			con.close();
		}catch(SQLException e ){
			e.printStackTrace();
		}
	}
	
}
