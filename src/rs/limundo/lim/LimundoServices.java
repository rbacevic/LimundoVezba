package rs.limundo.lim;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import rs.limundo.dao.DAO;
import rs.limundo.model.Aukcije;


@Path("/test")
public class LimundoServices {
	@Path("/aukcije")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public String sveAuckije() throws Exception{
			String	jsonString= null;
			JSONArray json = new JSONArray();
			
			try {
				DAO dao = new DAO();
				
				json= dao.selectSveAukcije();
				jsonString= json.toString();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			return jsonString;
		}
		
		
		@Path("/clanovi")
				@GET
				@Produces(MediaType.APPLICATION_JSON)
				public Response allClanovi() throws Exception{
					String	jsonString= null;
					JSONArray json = new JSONArray();
					
					try {
						DAO dao = new DAO();
						
						json= dao.selectSveClanove();
						jsonString= json.toString();
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					
					return Response.ok(jsonString).build();
				}
		
				
		
		/*@Path("/jeftinije")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response aukcijeJeftinijeOd(@QueryParam("cena") int cena ) {
			
			JSONArray json= new JSONArray();
		  
		  try {
			  DAO dao = new DAO();
			  json= dao.aukcijeJetinijeOd(cena);
			 

	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return Response.ok(json).build();
		
	}*/
		
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response dodajAukciju(@FormParam("naslov") String naziv_predmeta, @FormParam("kategorija") String kategorija,
			@FormParam("pocetnacena") int cena, @FormParam("id_clana") int id_clana, @FormParam("opis")String opis){
		String returnString=null;
		
		
		Aukcije a = new Aukcije();
		a.setNaziv_predmeta(naziv_predmeta);
		a.setKategorija(kategorija);
		a.setCena(cena);
		a.setId_clana(id_clana);
		a.setOpis(opis);
		int code=0;
		try {
			DAO dao= new DAO();
			code=dao.dodajAuckiju(a);
			
			if(code == 200){
				returnString="Uneto u bazu";
			
						
			}else if (code == 500){
				returnString = "Greska";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok(returnString).build();
		
		
	
		
	}
	/*@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateAukcije(@FormParam("id_aukcije") int id_aukcije,@FormParam("naslov") String naziv_predmeta, @FormParam("kategorija") String kategorija,
			@FormParam("pocetnacena") int cena, @FormParam("opis")String opis, @FormParam("id_clana") int id_clana){
String returnString=null;
		
		
		Aukcije a = new Aukcije();
		a.setId_aukcije(id_aukcije);
		a.setNaziv_predmeta(naziv_predmeta);
		a.setKategorija(kategorija);
		a.setCena(cena);
		a.setId_clana(id_clana);
		
		a.setOpis(opis);
		int code=0;	
		try {
			DAO dao= new DAO();
			code=dao.updateAukcije(a);
			if(code == 200){
				returnString="Uspesna izmena";
			}else {
				returnString="Greska";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok(returnString).build();
	}
	*/
	
	@Path("/delete/{id_aukcije}")
	@POST
	public Response deleteAukcije(@PathParam("id_aukcije") int id_aukcije){
		
		int code=0;
		String returnString=null;
		try {
			DAO dao= new DAO();
			
					
			code=dao.izbrisiAukciju(id_aukcije);
			
			if(code == 200){
				returnString="Uspesna izmena";
			}else {
				returnString="Greska";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return Response.ok(returnString).build();
	}
}
