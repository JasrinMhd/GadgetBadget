package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Researchers")
public class ResearcherService {

	Researcher Obj = new Researcher();

	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearcher() {

		return Obj.readResearcher();

	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertResearcher(@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname,
			@FormParam("gender") String gender,
			@FormParam("email") String email,
			@FormParam("password") String password) {

		String output = Obj.insertResearcher(firstname, lastname, gender, email, password);
		return output;
	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearcher(String ResearcherData) {
		
		// Convert the input string to a JSON object 
		JsonObject Object = new JsonParser().parse(ResearcherData).getAsJsonObject();

		// Read the values from the JSON object 
		String id = Object.get("id").getAsString();
		String firstname = Object.get("firstname").getAsString();
		String lastname = Object.get("lastname").getAsString();
		String gender = Object.get("gender").getAsString();
		String email = Object.get("email").getAsString();
		String password = Object.get("password").getAsString();

		String output = Obj.updateResearcher(id, firstname, lastname, gender, email, password);

		return output;
	}

	
	
	@DELETE
    @Path("/")
	@Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN) 
	
	public String deleteResearcher(String ResearcherData) { 
		
		//Convert the input string to an XML document 
		Document doc = Jsoup.parse(ResearcherData, "", Parser.xmlParser());
	  
	  //Read the value from the element <itemID> 
		String id = doc.select("id").text(); 
		String output = Obj.deleteResearcher(id);
		return output; 
		}

}
