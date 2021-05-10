package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Researcher {
	
	private Connection connect()
	 {
	 
		Connection con = null;
	
	try{
	    Class.forName("com.mysql.jdbc.Driver");

	    //Provide the correct details: DBServer/DBName, username, password
	    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher", "root", "");
	    
	 }catch (Exception e){
		 e.printStackTrace();}
	     return con;
	 } 
	
	public String insertResearcher(String firstname, String lastname, String gender, String email, String password)
	 {
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for inserting.";
				}
	 
			// create a prepared statement
	
			String query = " INSERT INTO `researcher_details`(`id`, `firstname`, `lastname`, `gender`, `email`, `password`)  VALUES  (?, ?, ?, ?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, firstname);
	        preparedStmt.setString(3,lastname );
	        preparedStmt.setString(4, gender);
	        preparedStmt.setString(5, email);
	        preparedStmt.setString(6, password);
	        // execute the statement
	
	        preparedStmt.execute();
	        con.close();
	        output = "Inserted successfully";
	
		}catch (Exception e){
	 
			output = "Error while inserting ";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String readResearcher(){
	 
		String output = "";
	 
		try{
	
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 
			// Prepare the html table to be displayed
	
			output = "<table border='1' align='center'><tr><th>First Name</th><th>Last Name</th>" +
	                "<th>Gender</th>" +
	                "<th>Email</th>" +
	                "<th>Password</th>" +
	                "<th>Update</th><th>Remove</th></tr>";

	 
			String query = "SELECT `id`, `firstname`, `lastname`, `gender`, `email`, `password` FROM `researcher_details` ";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	            String id=rs.getString("id");
	        	String firstname = rs.getString("firstname");
	            String lastname = rs.getString("lastname");
	            String gender = rs.getString("gender");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            
	            // Add into the html table
	
	            output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='hidden' value='" + id + "'>"
	            		 + firstname + "</td>";
	            output += "<td>" + lastname + "</td>";
	            output += "<td>" + gender + "</td>";
	            output += "<td>" + email + "</td>";
	            output += "<td>" + password + "</td>";
	
	            // buttons
	           output +="<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td>"
	        			 + "<td><form method='post' action='Researchers.jsp'>"
	        			 +"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	        			 + "<input name='hididDelete' type='hidden' value='" + id + "'></form></td></tr>";
	  
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	
	public String updateResearcher(String id,String firstname, String lastname, String gender, String email, String password){
		 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for updating.";
				}
	 
			// create a prepared statement
	        String query = "UPDATE `researcher_details` SET `firstname`=?,`lastname`=?,`gender`=?,`email`=?,`password`=? WHERE `id`=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	        // binding values
	        preparedStmt.setString(1, firstname);
	        preparedStmt.setString(2, lastname);
	        preparedStmt.setString(3, gender);
	        preparedStmt.setString(4, email);
	        preparedStmt.setString(5, password);
	        preparedStmt.setInt(6, Integer.parseInt(id));
	
	        // execute the statement
	        preparedStmt.execute();
	        con.close();
	        output = "Updated successfully";
	 
		}catch (Exception e){
	 
			output = "Error while updating.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String deleteResearcher(String id){
	 
		String output = "";
	
		try {
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	
			 // create a prepared statement
	         String query = "DELETE FROM `researcher_details` WHERE `id`=?";
	         PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	         // binding values
	         preparedStmt.setInt(1, Integer.parseInt(id));
	 
	         // execute the statement
	         preparedStmt.execute();
	         con.close();
	         output = "Deleted successfully";
	
		}catch (Exception e){
			
	 
			output = "Error while deleting";
	        System.err.println(e.getMessage());
	
		}
	
		return output;
	 } 
	

}
