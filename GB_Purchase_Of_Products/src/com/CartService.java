package com;

import model.Cart;
import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/carts") 

public class CartService {

	
	Cart cartObj = new Cart();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return cartObj.readCarts();
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertCart(
			
	 @FormParam("ptname") String productname,                        
	 @FormParam("unitPrice") Double uPrice,
	 @FormParam("qty") Integer quantity)
	{
	 String output = cartObj.insertCart(productname,uPrice,quantity);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCart(String cartData)
	{
	//Convert the input string to a JSON object
	 JsonObject cartObject = new JsonParser().parse(cartData).getAsJsonObject();
	//Read the values from the JSON object
	 String cartID = cartObject.get("cID").getAsString();
	 String productname = cartObject.get("ptname").getAsString();
	 String uPrice = cartObject.get("unitPrice").getAsString();
	 String quantity = cartObject.get("qty").getAsString();
	
	 String output = cartObj.updateCart(cartID, productname, uPrice, quantity);
	return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCart(String cartData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(cartData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String cartID = doc.select("cID").text();
	 String output = cartObj.deleteCart(cartID);
	return output;
	}

	
}
