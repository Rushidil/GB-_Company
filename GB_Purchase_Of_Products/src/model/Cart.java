package model;

import java.sql.*;

public class Cart {
	
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
		
		//to insert items to cart
		public String insertCart(String ptname , Double unitPrice, Integer qty)
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
		 
		 String query =  " insert into cart (`cID`,`ptname`,`unitPrice`,`qty`) "
		 		         + "values (?, ?, ?, ?)";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, ptname);
		 preparedStmt.setDouble(3, unitPrice);
		 preparedStmt.setInt(4, qty);
		
		// execute the statement
		
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 
		 {
		           output = "Error while inserting the cart.";
		           System.err.println(e.getMessage());
		 }
		 return output;
		 
	    } 
		
		//to read items in cart
		public String readCarts()
		 {
			
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		         output = "<table border='1'><tr><th>Product Name</th><th>Unit Price</th>" +
		        		 "<th>Quantity</th>" +
		                 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from cart";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String cartID = Integer.toString(rs.getInt("cID"));
		 String productname = rs.getString("ptname");
		 String uPrice = Double.toString( rs.getDouble("unitPrice"));
		 String quantity =rs.getString("qty");
		
		 // Add into the html table
		
		 output += "<tr><td>" + productname + "</td>";
		 output += "<td>" + uPrice + "</td>";
		 output += "<td>" + quantity + "</td>";
		
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> </td>"
		 + "<td><form method='post' action='items.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='cID' type='hidden' value='" + cartID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the carts.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		
		
		public String updateCart(String cartID, String productname, String uPrice, String quantity)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE cart SET ptname=?,unitPrice=?,qty=? WHERE cID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, productname);
		 preparedStmt.setDouble(2,Double.parseDouble ( uPrice));
		 preparedStmt.setInt(3, Integer.parseInt (quantity));
		 preparedStmt.setInt(4, Integer.parseInt(cartID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the cart.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		public String deleteCart(String  cartID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from cart where cID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt( cartID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the cart.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }


		
		
		

}
