package banksys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;


public class ConnectorTest
{	
	private Connection connection = null;
	
	@Test
	public void connectTest() throws SQLException
	{
		connection = Connector.connect();
		Assert.assertNotNull(connection);
	}
	
	@Test
	public void closeTest() throws SQLException
	{
		connection = Connector.connect();
		Connector.close();
		Assert.assertTrue(connection.isClosed());
	}
}