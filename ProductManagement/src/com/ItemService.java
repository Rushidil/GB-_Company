package com;

import Model.Item;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Items")
public class ItemService
{
	Item itemObj = new Item();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return itemObj.readItems(); 
	}
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(
			@FormParam("ItemCode") String ItemCode,
			@FormParam("ItemName") String ItemName,
			@FormParam("Description") String Description,
			@FormParam("Researcher") String Researcher,
			@FormParam("Email") String Email)
	{ 
		String output = itemObj.insertItem(ItemCode, ItemName, Description, Researcher, Email);
		return output; 
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = itemObject.get("ItemID").getAsString();
	 String code = itemObject.get("ItemCode").getAsString();
	 String name = itemObject.get("ItemName").getAsString();
	 String desc = itemObject.get("Description").getAsString();
	 String researcher = itemObject.get("Researcher").getAsString();
	 String email = itemObject.get("Email").getAsString();
	 String output = itemObj.updateItem(ID,code, name, desc, researcher, email);
	return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String ID = doc.select("ItemID").text();
	 String output = itemObj.deleteItem(ID);
	return output;
	}
	
	
	
	

}

