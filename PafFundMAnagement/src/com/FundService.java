//IT19189468
package com;

import model.Fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType; 


//For JSON
import com.google.gson.*; 


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Funds")
public class FundService {
	
	
	
	//GET method  
	Fund fundObj = new Fund();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	 {
		return fundObj.readFunds();
	 }
	
	
	
	//POST method
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("name") String name,
							 @FormParam("address") String address,
							 @FormParam("amount") String amount,
							 @FormParam("email") String email,
							 @FormParam("phone") int phone,
							 @FormParam("nic") String nic)
	{
	 String output = fundObj.insertFund(name, address, amount, email, phone, nic);
	 return output;
	}
	
	
	//PUT method
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateFund(String fundData)
	{
		
	 //Convert the input string to a JSON object
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	 
	 //Read the values from the JSON object
	 String id = fundObject.get("id").getAsString();
	 String name = fundObject.get("name").getAsString();
	 String address = fundObject.get("address").getAsString();
	 String amount = fundObject.get("amount").getAsString();
	 String email = fundObject.get("email").getAsString();
	 String phone = fundObject.get("phone").getAsString();
	 String nic = fundObject.get("nic").getAsString();
	 
	 String output = fundObj.updateFund(id, name, address, amount, email,phone,nic);
	 return output;
	}
	
	
	//Delete method
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)		
	
	public String deleteFund(String fundData)
	{
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

	 //Read the value from the element <id>
	 String id = doc.select("id").text();
	 String output = fundObj.deleteFund(id);
	 return output;
	}



}
