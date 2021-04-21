package com;

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
@Path("/payments") 

public class PaymentService {
	
	Payment paymentObj = new Payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return paymentObj.readPayments();
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertPayment(
			
	 @FormParam("fname") String cusFname,                        
	 @FormParam("lname") String cusLname,
	 @FormParam("cardNo") String pCardno,
	 @FormParam("cdate") String pCdate,
	 @FormParam("ccv") String pCCV)
	{
	 String output = paymentObj.insertPayment(cusFname,cusLname,pCardno,pCdate,pCCV);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 String paymentID = paymentObject.get("pID").getAsString();
	 String cusFname = paymentObject.get("fname").getAsString();
	 String cusLname = paymentObject.get("lname").getAsString();
	 String pCardno = paymentObject.get("cardNo").getAsString();
	 String pCdate = paymentObject.get("cdate").getAsString();
	 String pCCV = paymentObject.get("ccv").getAsString();

	 String output = paymentObj.updatePayment(paymentID, cusFname, cusLname, pCardno, pCdate,pCCV);
	return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String paymentID = doc.select("pID").text();
	 String output = paymentObj.deletePayment(paymentID);
	return output;
	}

}
