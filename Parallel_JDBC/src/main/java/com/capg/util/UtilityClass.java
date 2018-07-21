package com.capg.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilityClass {

	public static Connection getConnection() {
		
	
				
				Connection conn=null;
				try {
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wallet","root","Capgemini123");
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			return conn;
			
			
			
	
		
	}
}
