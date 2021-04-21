package model;

import java.sql.*;

public class Order {
	
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
		
		
		public String insertOrder(String date , String cname , String phone, String address, String email)
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
		 
		 String query =  " insert into orderdetails (`oID`,`date`,`cname`,`phone`,`address`,`email`) "
		 		         + "values (?, ?, ?, ?, ?, ?)";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, date);
		 preparedStmt.setString(3, cname);
		 preparedStmt.setString(4, phone);
		 preparedStmt.setString(5, address);
		 preparedStmt.setString(6, email);
		// execute the statement
		
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 
		 {
		           output = "Error while inserting the order.";
		           System.err.println(e.getMessage());
		 }
		 return output;
		 
	    } 
		
		
		public String readOrders()
		 {
			
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		         output = "<table border='1'><tr><th>Order Date</th><th>Customer Name</th>" +
		                  "<th>Phone number</th>" +
		                  "<th>Address</th>" +
		                   "<th>Email</th>" +
		                   "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from orderdetails";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String orderID = Integer.toString(rs.getInt("oID"));
		 String oDate = rs.getString("date");
		 String cusName = rs.getString("cname");
		 String cPhone =rs.getString("phone");
		 String cAddress = rs.getString("address");
		 String cEmail=rs.getString("email");
		 // Add into the html table
		 output += "<tr><td>" + oDate + "</td>";
		 output += "<td>" + cusName + "</td>";
		 output += "<td>" + cPhone + "</td>";
		 output += "<td>" + cAddress + "</td>";
		 output += "<td>" + cEmail + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> </td>"
		 + "<td><form method='post' action='items.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='oID' type='hidden' value='" + orderID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the orders.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		
		public String updateOrder(String ID, String date , String cname, String phone, String address, String email)
		
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE orderdetails SET date=?,cname=?,phone=?,address=? ,email=? WHERE oID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, date);
		 preparedStmt.setString(2, cname);
		 preparedStmt.setString(3, phone);
		 preparedStmt.setString(4, address);
		 preparedStmt.setString(5, email);
		 preparedStmt.setInt(6, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the order.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		public String deleteOrder(String  orderID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from orderdetails where oID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt( orderID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the order.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 

}
