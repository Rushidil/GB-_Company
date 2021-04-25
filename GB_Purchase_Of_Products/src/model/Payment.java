package model;

import java.sql.*;

public class Payment {
  
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order_management", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	
	public String insertPayment(String fname , String lname, String cardNo, String cdate, String ccv)
	 {
		
	 String output = "";
	 
	 try
	 
	 {
		 
	 Connection con = connect();
	 if (con == null)
		 
	 {
		 return "Error while connecting to the database for inserting."; 
     }
	 // create a prepared statement
	 
	 String query =  " insert into payment (`pID`,`fname`,`lname`,`cardNo`,`cdate`,`ccv`) "
	 		         + "values (?, ?, ?, ?, ?, ?)";
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, fname);
	 preparedStmt.setString(3, lname);
	 preparedStmt.setString(4, cardNo);
	 preparedStmt.setString(5, cdate);
	 preparedStmt.setString(6, ccv);
	// execute the statement
	
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 
	 {
	           output = "Error while inserting the payment.";
	           System.err.println(e.getMessage());
	 }
	 return output;
	 
    } 
	
	
	public String readPayments()
	 {
		
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	         output = "<table border='1'><tr><th>First Name</th>" +
	        		 "<th>Last Name</th>" +
	                  "<th>Card Number</th>" +
	                  "<th>Expiration Date</th>" +
	                   "<th>CCV</th>" +
	                   "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from payment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String paymentID = Integer.toString(rs.getInt("pID"));
	 String cusFname = rs.getString("fname");
	 String cusLname = rs.getString("lname");
	 String pCardno =rs.getString("cardNo");
	 String pCdate = rs.getString("cdate");
	 String pCCV =rs.getString("ccv");
	 // Add into the html table
	
	 output += "<tr><td>" + cusFname + "</td>";
	 output += "<td>" + cusLname + "</td>";
	 output += "<td>" + pCardno + "</td>";
	 output += "<td>" + pCdate + "</td>";
	 output += "<td>" + pCCV + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> </td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='pID' type='hidden' value='" + paymentID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the payments.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String updatePayment(String ID, String fname , String lname, String cardNo, String cdate, String ccv)
	
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE payment SET fname=?,lname=?,cardNo=?,cdate=? ,ccv=? WHERE pID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, fname);
	 preparedStmt.setString(2, lname);
	 preparedStmt.setString(3, cardNo);
	 preparedStmt.setString(4, cdate);
	 preparedStmt.setString(5, ccv);
	 preparedStmt.setInt(6, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String deletePayment(String  paymentID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from payment where pID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt( paymentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
}
