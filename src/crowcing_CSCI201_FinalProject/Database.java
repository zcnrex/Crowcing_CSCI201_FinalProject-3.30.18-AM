package crowcing_CSCI201_FinalProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//Database Entrance
public class Database {
	String username, password = null;
	
	public Database (String username, String password){
		this.username = username;
		this.password = password;
	}
	

	public boolean checkRegister(){
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/finalproject", "root", "");
			;
			

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User");
			ResultSet rs = stmt.executeQuery();
			
		    while (rs.next()) {
		        if(rs.getString("username").equals(username)){
		        	return false;
		        }
		    }
		    

			String query = "INSERT INTO User (username, password) VALUES (?,?)";
			PreparedStatement stmt2 = conn.prepareStatement(query);
			stmt2.setString(1, username);
			stmt2.setString(2, password);
			stmt2.executeUpdate();
		    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public int checkLogin(){
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/finalproject", "root", "");
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User");
			ResultSet rs = stmt.executeQuery();
			
		    while (rs.next()) {
		        if(rs.getString("username").equals(username)){
		        	if(rs.getString("password").equals(password)){
			        	return 0;
			        }
		        	return 2;
		        }
		    }
		    		    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
}
