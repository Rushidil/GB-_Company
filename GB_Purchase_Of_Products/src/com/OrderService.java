package com;


import model.Order;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/orders") 

public class OrderService {
	
	Order orderObj = new Order();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		//return "Hello";
		return orderObj.readOrders();
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertOrder(
	 		
	 @FormParam("date") String oDate,                        
	 @FormParam("cname") String cusName,
	 @FormParam("phone") String cPhone,
	 @FormParam("address") String cAddress,
	 @FormParam("email") String cEmail)
	{
	 String output = orderObj.insertOrder(oDate,cusName,cPhone,cAddress,cEmail);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrder(String orderData)
	{
	//Convert the input string to a JSON object
	 JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject();
	//Read the values from the JSON object
	 String orderID = orderObject.get("oID").getAsString();
	 String oDate = orderObject.get("date").getAsString();
	 String cusName = orderObject.get("cname").getAsString();
	 String cPhone = orderObject.get("phone").getAsString();
	 String cAddress = orderObject.get("address").getAsString();
	 String cEmail = orderObject.get("email").getAsString();

	 String output = orderObj.updateOrder(orderID, oDate, cusName, cPhone, cAddress,cEmail);
	return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOrder(String orderData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(orderData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String orderID = doc.select("oID").text();
	 String output = orderObj.deleteOrder(orderID);
	return output;
	}

}
