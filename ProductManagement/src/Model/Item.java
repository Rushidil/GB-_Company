package Model;
import java.sql.*; 

public class Item {
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product_mng", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	 public String insertItem(String code, String name, String desc, String researcher,String email )
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
	 String query = " insert into product(`ItemID`,`ItemCode`,`ItemName`,`Description`,`Researcher`,`Email`)" 
			 	  + " values (?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, code);
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, desc);
	 preparedStmt.setString(5, researcher);
	 preparedStmt.setString(6, email);
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readItems()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Item Code</th><th>Item Name</th>" +
	 "<th>Item Description</th>" +
	 "<th>Item Researcher</th>" +
	 "<th>Item Email</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from product";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String ItemID = Integer.toString(rs.getInt("ItemID"));
	 String ItemCode = rs.getString("ItemCode");
	 String ItemName = rs.getString("ItemName");
	 String Description = rs.getString("Description");
	 String Researcher = rs.getString("Researcher");
	 String Email = rs.getString("Email");
	 // Add into the html table
	 output += "<tr><td>" + ItemCode + "</td>";
	 output += "<td>" + ItemName + "</td>";
	 output += "<td>" + Description + "</td>";
	 output += "<td>" + Researcher + "</td>";
	 output += "<td>" + Email + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> </td>" 
	 + "<td><form method='post' action='items.jsp'>" 
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='ItemID' type='hidden' value='" + ItemID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String updateItem(String ID, String code, String name, String desc, String researcher, String email)
	
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE product SET ItemCode=?,ItemName=?,Description=?,Researcher=?,Email=? WHERE ItemID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(1, code);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, desc);
	 preparedStmt.setString(4, researcher);
	 preparedStmt.setString(5, email);
	 preparedStmt.setInt(6, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteItem(String ItemID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from product where ItemID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ItemID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 

}
