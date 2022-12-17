package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	Connection connection;
	
	public Connection getConnection () {
		String dbName = "bdvideojuegos";
		String userName = "root";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/" + dbName,
					userName,
					password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}
}
