package com;
import model.user;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/User")
public class UserService
{
	user userObj = new user();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return userObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
		@FormParam("userID") String userID,
		@FormParam("name") String name,
		@FormParam("email") String email,
		@FormParam("password") String password,
		@FormParam("nic") String nic)
	{
		String output = userObj.insertUser(userID, name, email, password, nic);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String uID = itemObject.get("ID").getAsString();
	 String UserID = itemObject.get("userID").getAsString();
	 String Name = itemObject.get("name").getAsString();
	 String Email = itemObject.get("email").getAsString();
	 String Password = itemObject.get("password").getAsString();
	 String Nic = itemObject.get("nic").getAsString();
	 String output = userObj.updateUser(uID, UserID, Name, Email, Password, Nic);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String ID = doc.select("ID").text();
	 String output = userObj.deleteUser(ID);
	return output;
	}
}
