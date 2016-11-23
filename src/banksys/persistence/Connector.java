package banksys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector { 
    
	private static Connection connection = null;
	private final static String DATABASE_URL = "jdbc:sqlite:banksys.sqlite";
 
	/*
	 * Since we only need one connection to the database
	 * we will check if one connection is already open before connecting
	 */
	public static Connection connect()
	{
		if(connection == null)
		{
			connection = connectToSQLite();
		}
		
		return connection;
	}
	
	/*
	 * Private method so only this class has direct access to open the database connection
	 */
	private static Connection connectToSQLite()
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
