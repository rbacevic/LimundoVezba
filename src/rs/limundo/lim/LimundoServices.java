package rs.limundo.lim;

import java.sql.SQLException;

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
import javax.ws.rs.core.Response.Status;




import org.json.JSONArray;



/*import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
*/
import rs.limundo.dao.DAO;
import rs.limundo.model.Aukcije;
import rs.limundo.model.Clan;

@Path("/test")
public class LimundoServices {
	@Path("/aukcije")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sveAuckije() throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectSveAukcije();
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/randomaukcije")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String randomAuckije() throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.randomAukcije();
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/clanovi")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String allClanovi() throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectSveClanove();
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/ocenebyidaukcije/{id_aukcije}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectOcene(@PathParam("id_aukcije") int id_aukcije)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectOcene(id_aukcije);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/aukcija/{id_aukcije}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectAukcijeByIDAukcije(
			@PathParam("id_aukcije") int id_aukcije) throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectAukcijeByID(id_aukcije);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/brojponuda/{id_aukcije}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectCountPonude(@PathParam("id_aukcije") int id_aukcije)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectCountPonude(id_aukcije);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/idposlednjeaukicje")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String IdZadnjeUneteAukcije() throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.IdZadnjeUneteAukcije();
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/trajanjeaukcije/{id_aukcije}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectVremeTrajanja(@PathParam("id_aukcije") int id_aukcije)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectVremeTrajanja(id_aukcije);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/usernamepokretacaaukcije/{id_aukcije}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectProdavacPonude(@PathParam("id_aukcije") int id_aukcije)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectProdavacPonude(id_aukcije);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/predmetsaimenom/{naziv_predmeta}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectPredmet(
			@PathParam("naziv_predmeta") String naziv_predmeta)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectPredmet(naziv_predmeta);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	// lista username-ova za spisak svih ponuda za predmet
	@Path("/ponudjac/{id_aukcije}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectUsernamePonude(@PathParam("id_aukcije") int id_aukcije)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectUsernamePonude(id_aukcije);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/ponudezaaukciju/{id_aukcije}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectPonude(@PathParam("id_aukcije") int id_aukcije)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectPonude(id_aukcije);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/kategorija/{kategorija}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectKategorije(@PathParam("kategorija") String kategorija)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectKategorije(kategorija);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/kategorije")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectSveKategorije() throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectSveKategorije();
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/clan/{id_clana}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectClanByUserId(@PathParam("id_clana") int id_clana)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectClanByUserId(id_clana);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/clanbyusername/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectClan(@PathParam("username") String username)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectClan(username);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	@Path("/userwithusername/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String selectClanByUsername(@PathParam("username") String username)
			throws Exception {
		String jsonString = null;
		JSONArray json = new JSONArray();

		try {
			DAO dao = new DAO();

			json = dao.selectClanByUsername(username);
			jsonString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	/*
	 * @Path("/jeftinije")
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * aukcijeJeftinijeOd(@QueryParam("cena") int cena ) {
	 * 
	 * JSONArray json= new JSONArray();
	 * 
	 * try { DAO dao = new DAO(); json= dao.aukcijeJetinijeOd(cena);
	 * 
	 * 
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * return Response.ok(json).build();
	 * 
	 * }
	 */

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response dodajAukciju(@FormParam("naslov") String naziv_predmeta,
			@FormParam("kategorija") String kategorija,
			@FormParam("pocetnacena") int cena,
			@FormParam("id_clana") int id_clana, @FormParam("opis") String opis) {
		String returnString = null;

		Aukcije a = new Aukcije();
		a.setNaziv_predmeta(naziv_predmeta);
		a.setKategorija(kategorija);
		a.setCena(cena);
		a.setId_clana(id_clana);
		a.setOpis(opis);
		int code = 0;
		try {
			DAO dao = new DAO();
			code = dao.dodajAuckiju(a);

			if (code == 200) {
				returnString = "Uneto u bazu";

			} else if (code == 500) {
				returnString = "Greska";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.ok(returnString).build();

	}

	/*
	 * @Path("/update")
	 * 
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_FORM_URLENCODED) public Response
	 * updateAukcije(@FormParam("id_aukcije") int
	 * id_aukcije,@FormParam("naslov") String naziv_predmeta,
	 * @FormParam("kategorija") String kategorija,
	 * 
	 * @FormParam("pocetnacena") int cena, @FormParam("opis")String opis,
	 * @FormParam("id_clana") int id_clana){ String returnString=null;
	 * 
	 * 
	 * Aukcije a = new Aukcije(); a.setId_aukcije(id_aukcije);
	 * a.setNaziv_predmeta(naziv_predmeta); a.setKategorija(kategorija);
	 * a.setCena(cena); a.setId_clana(id_clana);
	 * 
	 * a.setOpis(opis); int code=0; try { DAO dao= new DAO();
	 * code=dao.updateAukcije(a); if(code == 200){
	 * returnString="Uspesna izmena"; }else { returnString="Greska"; } } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * return Response.ok(returnString).build(); }
	 */

	@Path("/delete/{id_aukcije}")
	@DELETE
	public Response deleteAukcije(@PathParam("id_aukcije") int id_aukcije) {

		int code = 0;
		String returnString = null;
		try {
			DAO dao = new DAO();

			code = dao.izbrisiAukciju(id_aukcije);

			if (code == 200) {
				returnString = "Uspesna izmena";
			} else {
				returnString = "Greska";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return Response.ok(returnString).build();
	}

	@Path("/izbrisiclana/{id_clana}")
	@DELETE
	public Response izbrisiClana(@PathParam("id_clana") int id_clana) {

		int code = 0;
		String returnString = null;
		try {
			DAO dao = new DAO();

			code = dao.izbrisiClana(id_clana);

			if (code == 200) {
				returnString = "Uspesno obrisan clan";
			} else {
				returnString = "Greska";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return Response.ok(returnString).build();
	}
	
	@Path("/registracija")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registracija(Clan clan){
		int code =0;
		try {
			DAO dao = new DAO();
			dao.dodajClana(clan);
			
			code = 201;
	

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code =500;
		}
		
		
		
		
		return Response.status(code).build();

		
		
		
		
		
		
	}
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(Clan c) throws Exception{
		JSONArray json = new JSONArray();
		
		try {
			DAO dao = new DAO();
			json=dao.selectClanLogIn(c);
			System.out.println(json);

			return json.toString();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return null;

		
		
		
		
		
		
	}
	
	
	
	
}
