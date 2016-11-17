package banksys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector { 
    
	private static Connection connection = null;
	private final static String DATABASE_URL = "jdbc:sqlite:banksys.db";
        
	public static Connection connect()
	{
		try{
			connection = DriverManager.getConnection(DATABASE_URL);
		}
		catch(SQLException e)
		{
			System.err.println("Error in method Connector.connect: " + e.getMessage());
		}
        return connection;
	}
	
	public static void close()
	{
		try{
			connection.close();
		}catch(Exception e)
		{
			System.out.println("Error in method Connector.close: " + e.getMessage());
		}
	}
	
}
