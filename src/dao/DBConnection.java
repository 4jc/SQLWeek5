package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private final static String URL = "jdbc:mysql://localhost:3306/application";
	private final static String USER = "";
	private final static String PASSWORD = "";
	private static Connection connection;
	private static DBConnection instance;
	
	private DBConnection (Connection connection) {
		this.connection = connection;
	}
	
	public static Connection getConnection() {
		if(instance == null) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				instance = new DBConnection(connection);
				System.out.println("Connected.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return DBConnection.connection;
	}
}
